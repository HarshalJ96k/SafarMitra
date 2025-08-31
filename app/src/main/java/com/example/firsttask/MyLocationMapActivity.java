package com.example.firsttask;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.firsttask.databinding.ActivityMyLocationMapBinding;
import com.google.android.gms.maps.model.PolylineOptions;

public class MyLocationMapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMyLocationMapBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Home Screen");

        binding = ActivityMyLocationMapBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng yourLocation = new LatLng(20.899301113562654, 77.76382760393676);
        mMap.addMarker(new MarkerOptions().position(yourLocation).title("Your Location").icon(setIcon(MyLocationMapActivity.this,R.drawable.user_marker_map)));
        LatLng carLocation =new LatLng(20.24239462212712, 76.20643126201038);
        mMap.addMarker(new MarkerOptions().position(carLocation).title("Car Location").icon(setIcon(MyLocationMapActivity.this,R.drawable.car_marker_map)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(yourLocation));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(yourLocation,15),5000,null);
        mMap.addCircle(new CircleOptions().center(yourLocation).radius(150).fillColor(0x220000FF).strokeColor(Color.BLUE));
        mMap.addCircle(new CircleOptions().center(carLocation).radius(150).fillColor(0x220000FF).strokeColor(Color.BLUE));
        mMap.addPolyline(new PolylineOptions().add(yourLocation,carLocation).color(Color.BLUE).width(10));
    }

    public BitmapDescriptor setIcon(Activity context,int drawableID)
    {
        Drawable drawable= ActivityCompat.getDrawable(context,drawableID);
        drawable.setBounds(0,0,drawable.getIntrinsicHeight(),drawable.getIntrinsicWidth());
        Bitmap bitmap=Bitmap.createBitmap(drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight(),Bitmap.Config.ARGB_8888);
        Canvas canvas=new Canvas(bitmap);
        drawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);

    }
}