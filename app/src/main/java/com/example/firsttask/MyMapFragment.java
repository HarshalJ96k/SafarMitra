package com.example.firsttask;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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



public class MyMapFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMyLocationMapBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = ActivityMyLocationMapBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        return view;
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng yourLocation = new LatLng(20.899301113562654, 77.76382760393676);
        mMap.addMarker(new MarkerOptions().position(yourLocation).title("Your Location").icon(setIcon(getActivity(),R.drawable.user_marker_map)));
        LatLng carLocation =new LatLng(20.24239462212712, 76.20643126201038);
        mMap.addMarker(new MarkerOptions().position(carLocation).title("Car Location").icon(setIcon(getActivity(),R.drawable.car_marker_map)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(yourLocation));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(yourLocation,15),5000,null);
        mMap.addCircle(new CircleOptions().center(yourLocation).radius(150).fillColor(0x220000FF).strokeColor(Color.BLUE));
        mMap.addCircle(new CircleOptions().center(carLocation).radius(150).fillColor(0x220000FF).strokeColor(Color.BLUE));
        mMap.addPolyline(new PolylineOptions().add(yourLocation,carLocation).color(Color.BLUE).width(10));
    }



    public BitmapDescriptor setIcon(Activity context, int drawableID)
    {
        Drawable drawable= ActivityCompat.getDrawable(context,drawableID);
        drawable.setBounds(0,0,drawable.getIntrinsicHeight(),drawable.getIntrinsicWidth());
        Bitmap bitmap=Bitmap.createBitmap(drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight(),Bitmap.Config.ARGB_8888);
        Canvas canvas=new Canvas(bitmap);
        drawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);

    }
}