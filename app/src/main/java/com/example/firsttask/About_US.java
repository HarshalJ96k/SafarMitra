package com.example.firsttask;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.denzcoskun.imageslider.BuildConfig;

public class About_US extends AppCompatActivity {

    TextView versionText, emailText, socialText;
    ScrollView aboutScroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us); // Reuse same XML or rename if needed

        aboutScroll = findViewById(R.id.about_scroll);

        // Set version
     ;

        // Animate scroll view
        AlphaAnimation animation = new AlphaAnimation(0.0f, 1.0f);
        animation.setDuration(800);
        aboutScroll.startAnimation(animation);

           }
}
