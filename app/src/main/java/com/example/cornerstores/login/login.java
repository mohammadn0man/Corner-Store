package com.example.cornerstores.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cornerstores.HomePage;
import com.example.cornerstores.R;

public class login extends AppCompatActivity {

    Button signin , login ;
    EditText email , password;
    protected void onCreate(final Bundle SavedInstanceState) {

        super.onCreate(SavedInstanceState);
        setContentView(R.layout.login);

        signin = (Button) findViewById(R.id.signupbutton);
        login = findViewById(R.id.loginbutton);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(login.this, "sign in please", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(login.this , signin.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email_ = email.getText().toString();
                String password_ = password.getText().toString();
                System.out.println(email_.equals("poornima") && password_ .equals("poornima"));
                if(email_.equals("poornima") && password_ .equals("poornima")) {
                    Intent intent = new Intent(login.this, HomePage.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(login.this, "Email"+email_+" or Password Incorrect"+password_, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
