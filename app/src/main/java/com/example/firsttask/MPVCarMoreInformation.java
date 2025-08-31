package com.example.firsttask;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MPVCarMoreInformation extends AppCompatActivity
{
    TextView tvMPV1,tvMPV2,tvMPV3;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mpvcar_more_information);

        tvMPV1=findViewById(R.id.tvMPV1);
        tvMPV2=findViewById(R.id.tvMPV2);
        tvMPV3=findViewById(R.id.tvMPV3);

        tvMPV1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent=new Intent(MPVCarMoreInformation.this, MpvCarOwner1.class);
                startActivity(intent);

            }
        });
        tvMPV2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent=new Intent(MPVCarMoreInformation.this, MpvCarOwner2.class);
                startActivity(intent);

            }
        });
        tvMPV3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent=new Intent(MPVCarMoreInformation.this, MpvCarOwner3.class);
                startActivity(intent);

            }
        });


    }
}