package com.example.sellerdemo.sellerhome;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
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

public class SellerLogin extends AppCompatActivity implements View.OnClickListener {
    EditText UserEmail, Password;
    ProgressBar pro;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seller_login);
        UserEmail = findViewById(R.id.u_email);
        Password = findViewById(R.id.u_pass);
        pro = findViewById(R.id.progress_bar);
        pro.setVisibility(View.GONE);
        findViewById(R.id.textview_signup).setOnClickListener(this);
        findViewById(R.id.login_btn).setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
    }

    private void userLogin(){
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
        pro.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(user_email,user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    if(mAuth.getCurrentUser().isEmailVerified()){
                    pro.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "Welcome to Corner-Stores", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent (SellerLogin.this,SellerHome.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // doing this because if the user press the back button then he will again come to the login screen --Rahul
                    startActivity(intent);
                    }
                    else{
                        pro.setVisibility(View.GONE);
                        UserEmail.setText(" ");
                        Password.setText("");
                        Toast.makeText(SellerLogin.this, "Please verify your email", Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(),task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.textview_signup:
                startActivity(new Intent (this,SellerSignup.class));
                break;

            case R.id.login_btn:
                userLogin();
                break;
        }
    }
}
