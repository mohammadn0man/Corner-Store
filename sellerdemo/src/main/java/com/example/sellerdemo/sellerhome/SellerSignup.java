package com.example.sellerdemo.sellerhome;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sellerdemo.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

import java.util.regex.Pattern;

public class  SellerSignup extends AppCompatActivity implements View.OnClickListener {
   public ProgressBar pro;
    EditText UserEmail, Password;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seller_signup);
        UserEmail = findViewById(R.id.u_email);
        Password = findViewById(R.id.u_pass);
        pro = findViewById(R.id.progress_bar);
        pro.setVisibility(View.GONE);
        mAuth = FirebaseAuth.getInstance();
        findViewById(R.id.signup_btn).setOnClickListener(this);
        findViewById(R.id.clickable_login_text).setOnClickListener(this);
    }
    private void registerSeller(){
        String user_email = UserEmail.getText().toString().trim();
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
        pro.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(user_email,user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                pro.setVisibility(View.GONE);
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Welcome to Corner-Stores", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent (SellerSignup.this,SellerHome.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // doing this because if the user press the back button then he will again come to the login screen --Rahul
                    startActivity(intent);
                }
                else{ //if email is already registered --rahul
                    if(task.getException() instanceof FirebaseAuthUserCollisionException){
                        Toast.makeText(getApplicationContext(), "This email is already registered", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.clickable_login_text:
                startActivity(new Intent(this,SellerLogin.class));
                break;
            case R.id.signup_btn:
                registerSeller();
                break;
        }

    }
}
