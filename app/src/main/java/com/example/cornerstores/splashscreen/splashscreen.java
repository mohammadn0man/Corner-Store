package com.example.cornerstores.splashscreen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cornerstores.R;
import com.example.cornerstores.login.login;
import com.example.cornerstores.tempforpractice.DatabaseTest;

import pl.droidsonroids.gif.GifImageView;

public class splashscreen extends AppCompatActivity {
    private static int SPLASH_SCREEN = 5000;
    ImageView image;
    TextView logo, slogan;
    Animation topAni, bottomAni;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            setContentView(R.layout.splash_activity);

            topAni = AnimationUtils.loadAnimation(this,R.anim.top_animation);
            bottomAni= AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

            image = findViewById(R.id.imageView);
            logo = findViewById(R.id.textView);
            slogan = findViewById(R.id.textView2);

           image.setAnimation(topAni);
           logo.setAnimation(bottomAni);
           slogan.setAnimation(bottomAni);

            new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(splashscreen.this, DatabaseTest.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_SCREEN);
    }
}
