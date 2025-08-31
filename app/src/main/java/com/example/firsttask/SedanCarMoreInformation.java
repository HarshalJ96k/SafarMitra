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

public class SedanCarMoreInformation extends AppCompatActivity
{

    TextView tvSedan1, tvSedan2, tvSedan3;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sedan_car_more_information);

        tvSedan1 = findViewById(R.id.tvSedan1);
        tvSedan2 = findViewById(R.id.tvSedan2);
        tvSedan3 = findViewById(R.id.tvSedan3);

        tvSedan1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(SedanCarMoreInformation.this,SedanCarOwner1.class);
                startActivity(intent);
            }
        });
        tvSedan2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(SedanCarMoreInformation.this,SedanCarOwner2.class);
                startActivity(intent);
            }
        });
        tvSedan3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(SedanCarMoreInformation.this,SedanCarOwner3.class);
                startActivity(intent);
            }
        });


    }
}