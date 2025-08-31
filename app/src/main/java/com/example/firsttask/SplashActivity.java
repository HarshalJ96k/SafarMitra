package com.example.firsttask;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

public class SplashActivity extends AppCompatActivity
{
    ImageView ivLogo;
    TextView tvTitle,tvSubtitle;
    Animation fadeAnim;
    SharedPreferences preferences ;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        setTitle("Login Screen");
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);


        preferences = PreferenceManager.getDefaultSharedPreferences(SplashActivity.this);
        editor=preferences.edit();



        ivLogo = findViewById(R.id.ivmain);
        tvTitle = findViewById(R.id.tvmain);
        tvSubtitle = findViewById(R.id.tv2main);

        fadeAnim=AnimationUtils.loadAnimation(SplashActivity.this,R.anim.fadein);
        ivLogo.setAnimation(fadeAnim);
        tvTitle.setAnimation(fadeAnim);
        tvSubtitle.setAnimation(fadeAnim);


        Handler h1 =new Handler();
        h1.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                Intent i =new Intent(SplashActivity.this,LoginActivity.class);
                startActivity(i);
            }
        },3000);



    }
}
