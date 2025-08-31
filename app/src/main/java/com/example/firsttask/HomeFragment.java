package com.example.firsttask;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.AnimationTypes;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.interfaces.ItemClickListener;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.firsttask.RecyclerView.CarAdapter;
import com.example.firsttask.RecyclerView.CarModel;
import com.example.firsttask.R;
import com.example.firsttask.common.urls;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class HomeFragment extends Fragment {

    ImageSlider imageSlider;
    RecyclerView rvCars;
    CarAdapter carAdapter;
    List<CarModel> carList;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // 🔹 Image Slider
        imageSlider = view.findViewById(R.id.isHomeImageSlider);
        ArrayList<SlideModel> slideModelArrayList = new ArrayList<>();
        slideModelArrayList.add(new SlideModel(R.drawable.cardiscount, "Slide 1", ScaleTypes.FIT));
        slideModelArrayList.add(new SlideModel("https://cdn.smartslider3.com/wp-content/uploads/2022/03/rentacar.png", "Slide 2", ScaleTypes.FIT));
        slideModelArrayList.add(new SlideModel("https://img.freepik.com/free-vector/car-rental-service-facebook-template_23-2150503820.jpg?w=360", "Slide 3", ScaleTypes.FIT));

        imageSlider.setImageList(slideModelArrayList);
        imageSlider.setSlideAnimation(AnimationTypes.ZOOM_IN);

        imageSlider.setItemClickListener(new ItemClickListener() {
            @Override
            public void doubleClick(int i) {}

            @Override
            public void onItemSelected(int position) {
                Toast.makeText(getActivity(), "Slide " + (position + 1) + " Clicked", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getActivity(), DiscountActivity.class);
                intent.putExtra("slide_position", position);
                startActivity(intent);
            }
        });

        // 🔹 RecyclerView setup
        rvCars = view.findViewById(R.id.rvCars);
        rvCars.setLayoutManager(new LinearLayoutManager(getContext()));

        carList = new ArrayList<>();
        carAdapter = new CarAdapter(getContext(), carList);
        rvCars.setAdapter(carAdapter);

        // 🔹 Load data from PHP API
        loadCarData();

        return view;
    }

    private void loadCarData() {
        AsyncHttpClient client = new AsyncHttpClient();

        client.get(urls.carDetailsWebService, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    android.util.Log.d("API_RESPONSE", response.toString());  // 👈 check in Logcat
                    if (response.getBoolean("success")) {
                        JSONArray carsArray = response.getJSONArray("cars");
                        carList.clear();

                        for (int i = 0; i < carsArray.length(); i++) {
                            JSONObject obj = carsArray.getJSONObject(i);

                            String carType = obj.getString("car_type");
                            String seats = obj.getString("car_seats");
                            String rent = obj.getString("rent");
                            String image = obj.getString("car_image");

                            carList.add(new CarModel(carType, seats, rent, image));
                        }

                        carAdapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(getContext(), "No cars found", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getContext(), "Parsing error!", Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Toast.makeText(getContext(), "Failed to connect server!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
