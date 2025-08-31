package com.example.firsttask.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firsttask.R;

import java.util.List;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.CarViewHolder> {

    private Context context;
    private List<CarModel> carList;

    public CarAdapter(Context context, List<CarModel> carList) {
        this.context = context;
        this.carList = carList;
    }

    @NonNull
    @Override
    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.car_item, parent, false);
        return new CarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarViewHolder holder, int position) {
        CarModel car = carList.get(position);
        holder.tvCarType.setText("Car Type: " + car.getCarType());
        holder.tvSeats.setText("No. of Seats: " + car.getSeats());
        holder.tvRent.setText("Rent per KM: " + car.getRent());

        // Load image from URL (use Glide or Picasso)
        Glide.with(context)
                .load(car.getImageUrl())
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.ivCarImage);

        holder.tvMoreInfo.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {


                Toast.makeText(context, "More info about " + car.getCarType(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return carList.size();
    }

    public static class CarViewHolder extends RecyclerView.ViewHolder {
        ImageView ivCarImage;
        TextView tvCarType, tvSeats, tvRent, tvMoreInfo;

        public CarViewHolder(@NonNull View itemView) {
            super(itemView);
            ivCarImage = itemView.findViewById(R.id.ivCarImage);
            tvCarType = itemView.findViewById(R.id.tvCarType);
            tvSeats = itemView.findViewById(R.id.tvSeats);
            tvRent = itemView.findViewById(R.id.tvRent);
            tvMoreInfo = itemView.findViewById(R.id.tvMoreInfo);
        }
    }
}
