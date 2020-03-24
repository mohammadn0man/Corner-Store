package com.example.cornerstores.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cornerstores.HomePage;
import com.example.cornerstores.MainActivity;
import com.example.cornerstores.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {

    Button signin , login ;
    EditText UserEmail , Password;
    FirebaseAuth mAuth;
    protected void onCreate(final Bundle SavedInstanceState) {

        super.onCreate(SavedInstanceState);
        setContentView(R.layout.login);

        signin = (Button) findViewById(R.id.signupbutton);
        login = findViewById(R.id.loginbutton);
        UserEmail = findViewById(R.id.email);
        Password = findViewById(R.id.password);

        mAuth = FirebaseAuth.getInstance();

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(login.this, "sign in please", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(login.this , SignUp.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String user_email = UserEmail.getText().toString().trim();
                String user_password = Password.getText().toString().trim();
                if(user_email.isEmpty()){
                    UserEmail.setError("Email is required!");
                    UserEmail.requestFocus();
                    return; }

                if(!Patterns.EMAIL_ADDRESS.matcher(user_email).matches()){
                    UserEmail.setError("Enter a valid email");
                    UserEmail.requestFocus();
                    return; }

                if(user_password.length()<6){
                    Password.setError("Minimum length of password should be 6");
                    Password.requestFocus();
                    return; }

                if(user_password.isEmpty()){
                    Password.setError("Password is required!");
                    Password.requestFocus();
                    return;}
                mAuth.signInWithEmailAndPassword(user_email,user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            if(mAuth.getCurrentUser().isEmailVerified()){
                                Toast.makeText(getApplicationContext(), "Welcome to Corner-Stores", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent (login.this, MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);// doing this because if the user press the back button then he will again come to the login screen --Rahul
                                startActivity(intent);

                            }
                            else{
                                UserEmail.setText(" ");
                                Password.setText("");
                                Toast.makeText(login.this, "Please verify your email", Toast.LENGTH_LONG).show();
                            }
                        }
                        else{
                            Toast.makeText(getApplicationContext(),task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
    }
}
