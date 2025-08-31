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

public class MiniBusCarMoreInformation extends AppCompatActivity
{
    TextView tvMiniBus1, tvMiniBus2, tvMiniBus3;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_mini_bus_car_more_information);
        tvMiniBus1=findViewById(R.id.tvMiniBus1);
        tvMiniBus2=findViewById(R.id.tvMiniBus2);
        tvMiniBus3=findViewById(R.id.tvMiniBus3);

        tvMiniBus1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MiniBusCarMoreInformation.this,MiniBusCarOwner1.class);
                startActivity(intent);
            }
        });
        tvMiniBus2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MiniBusCarMoreInformation.this,MiniBusCarOwner2.class);
                startActivity(intent);
            }
        });
        tvMiniBus3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MiniBusCarMoreInformation.this,MiniBusCarOwner3.class);
                startActivity(intent);
            }
        });


    }
}