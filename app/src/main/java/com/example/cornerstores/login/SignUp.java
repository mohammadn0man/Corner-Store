package com.example.cornerstores.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cornerstores.HomePage;
import com.example.cornerstores.MainActivity;
import com.example.cornerstores.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity {

    Button signup;
    TextView tname, temail, tphone, tpassword;
    String sname, semail, sphone, spassword, userId;
    private FirebaseAuth mAuth;
    FirebaseFirestore firebaseFirestore;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);

        tname = findViewById(R.id.name);
        temail = findViewById(R.id.email);
        tphone = findViewById(R.id.number);
        tpassword = findViewById(R.id.password);
        signup = findViewById(R.id.register);
        mAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerSeller();
            }
        });

    }
    private void registerSeller() {
        semail = temail.getText().toString().trim();
        spassword = tpassword.getText().toString().trim();
        sphone = tphone.getText().toString();
        sname = tname.getText().toString();

        if (semail.isEmpty()) {
            temail.setError("Email is required!");
            temail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(semail).matches()) {
            temail.setError("Enter a valid email");
            temail.requestFocus();
            return;
        }

        if (spassword.length() < 6) {
            tpassword.setError("Minimum length of password should be 6");
            tpassword.requestFocus();
            return;
        }

        if (spassword.isEmpty()) {
            tpassword.setError("Password is required!");
            tpassword.requestFocus();
            return;
        }
        mAuth.createUserWithEmailAndPassword(semail, spassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    mAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                temail.setText(" ");
                                tpassword.setText("");
                                Toast.makeText(getApplicationContext(), "You have been registered. Kindly check your mail for verification", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SignUp.this, login.class);
                                userId = mAuth.getCurrentUser().getUid();
                                DocumentReference documentReference = firebaseFirestore.collection("user").document(userId);
                                Map<String, Object> user = new HashMap<>();
                                user.put("Name",sname);
                                user.put("Email",semail);
                                user.put("phone",sphone);
                                documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Log.d("insert data into database","data inserted into cloud firestore");
                                    }
                                });

                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                            } else {
                                Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                } else {
                    if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                        temail.setText(" ");
                        tpassword.setText("");
                        Toast.makeText(getApplicationContext(), "This email is already registered", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}
