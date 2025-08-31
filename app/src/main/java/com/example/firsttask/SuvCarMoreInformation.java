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

public class SuvCarMoreInformation extends AppCompatActivity
{
    TextView tvSuv1,tvSuv2,tvSuv3;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suv_car_more_information);

        tvSuv1=findViewById(R.id.tvSUV1);
        tvSuv2=findViewById(R.id.tvSUV2);
        tvSuv3=findViewById(R.id.tvSUV3);


        tvSuv1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent=new Intent(SuvCarMoreInformation.this,SuvCarOwner1.class);
                startActivity(intent);

            }
        });
        tvSuv2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent=new Intent(SuvCarMoreInformation.this,SuvCarOwner2.class);
                startActivity(intent);


            }
        });
        tvSuv3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent=new Intent(SuvCarMoreInformation.this,SuvCarOwner3.class);
                startActivity(intent);


            }
        });



    }
}