package com.example.sellerdemo.sellerhome;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sellerdemo.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class PasswordReset extends AppCompatActivity {

    EditText reset_mail;
    Button reset_button;
    ProgressBar pro;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.password_reset_layout);
        reset_mail = findViewById(R.id.reset_email);
        pro = findViewById(R.id.pro_bar);
        pro.setVisibility(View.GONE);
        reset_button = findViewById(R.id.reset_btn);

        mAuth = FirebaseAuth.getInstance();

        reset_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pro.setVisibility(View.VISIBLE);
                mAuth.sendPasswordResetEmail(reset_mail.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {
                            pro.setVisibility(View.GONE);
                            Toast.makeText(PasswordReset.this, "Password reset Email has been sent to you", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(PasswordReset.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }
}
