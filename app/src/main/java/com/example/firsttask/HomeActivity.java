package com.example.firsttask;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.airbnb.lottie.LottieAnimationView;
import com.example.firsttask.databinding.ActivityMyLocationMapBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.zip.Inflater;

public class HomeActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener
{
    SharedPreferences preferences ;
    SharedPreferences.Editor editor;

    LottieAnimationView lottieHome;


    boolean doubletap =false;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        preferences = PreferenceManager.getDefaultSharedPreferences(HomeActivity.this);
        editor=preferences.edit();

        BottomNavigationView bottomNavigationView;
        bottomNavigationView=findViewById(R.id.home_bottom_nav_menu);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.bottom_menu_home);

        setTitle("Home Screen");
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        boolean FirstTime=preferences.getBoolean("firstTime",true);

if (FirstTime)
{
    welcome();

}

    }

    private void welcome()
    {
        AlertDialog.Builder ab =new AlertDialog.Builder(HomeActivity.this);
        ab.setTitle("Jadhav Tours and Travels");
        ab.setMessage("Welcome To Our App");
        ab.setPositiveButton("Thank You", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                dialogInterface.cancel();

            }
        }).create().show();
        editor.putBoolean("firstTime",false).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
       MenuInflater inflater = getMenuInflater();
       inflater.inflate(R.menu.home_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
       if (item.getItemId()==R.id.home_menu_MyProfile)
       {
           Toast.makeText(HomeActivity.this,"User Profile open Successfully",Toast.LENGTH_SHORT).show();
           Intent i=new Intent(HomeActivity.this, My_profile.class);
           startActivity(i);
       }
       else if (item.getItemId()==R.id.home_menu_mic)
       {
           Toast.makeText(HomeActivity.this,"Mic Open Successfully",Toast.LENGTH_SHORT).show();
           Intent i=new Intent(HomeActivity.this, MicActivity.class);
           startActivity(i);

       }
       else if (item.getItemId()==R.id.home_menu_Setting)
       {
           Toast.makeText(HomeActivity.this,"Setting Open Successfully",Toast.LENGTH_SHORT).show();
           Intent i=new Intent(HomeActivity.this, Settings.class);
           startActivity(i);
       }
       else if (item.getItemId()==R.id.home_menu_ContactUs)
        {
            Toast.makeText(HomeActivity.this,"Contact Us open Successfully",Toast.LENGTH_SHORT).show();
            Intent i=new Intent(HomeActivity.this, Contact_Us.class);
            startActivity(i);
        }
       else if (item.getItemId()==R.id.home_menu_AboutUs)
       {
           Toast.makeText(HomeActivity.this,"About Us open Successfully",Toast.LENGTH_SHORT).show();

           Intent i=new Intent(HomeActivity.this, About_US.class);
           startActivity(i);
       }
       else if (item.getItemId()==R.id.home_menu_QR)
       {
           Intent intent=new Intent(HomeActivity.this,QRCode.class);
           startActivity(intent);
       }
       else if (item.getItemId()==R.id.home_menu_LogOut)
       {
           Logout();
       }

        return true;
    }

    private void Logout()
    {
        AlertDialog.Builder ab=new AlertDialog.Builder(HomeActivity.this);
        ab.setTitle("Log Out Alert");
        ab.setMessage("Are you sure want to Log Out");
        ab.setPositiveButton("No", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                dialogInterface.cancel();
            }

        });
        ab.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                Toast.makeText(HomeActivity.this,"Log Out Successfully",Toast.LENGTH_SHORT).show();
                editor.putBoolean("isLogin",false).commit();
                Intent intent=new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();

            }
        }).create().show();
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();

        if (doubletap) {
            finishAffinity();
        } else {
            Intent i =new Intent(HomeActivity.this,HomeActivity.class);
            startActivity(i);
            Toast.makeText(HomeActivity.this, "Press again to exit App...", Toast.LENGTH_SHORT).show();
            doubletap = true;
            Handler h = new Handler();
            h.postDelayed(new Runnable() {
                @Override
                public void run() {
                    doubletap = false;
                }
            }, 2000);

        }
    }


    HomeFragment homeFragment=new HomeFragment();
    HomeFuelFinderFragment homeFuelFinderFragment= new HomeFuelFinderFragment();
    MyMapFragment myMapFragment= new MyMapFragment();
    HomeProfileFragment homeProfileFragment=new HomeProfileFragment();

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {

        if (item.getItemId()==R.id.bottom_menu_home)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_home,homeFragment).commit();

        } else if (item.getItemId()==R.id.bottom_menu_bookARide)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_home,homeFuelFinderFragment).commit();

        } else if (item.getItemId()==R.id.bottom_menu_MyRides)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_home,myMapFragment).commit();
        } else if (item.getItemId()==R.id.bottom_menu_profile)
        {

            Intent i = new Intent(HomeActivity.this,My_profile.class);
            startActivity(i);
            //getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_home,homeProfileFragment).commit();
        }
        return false;

    }

}