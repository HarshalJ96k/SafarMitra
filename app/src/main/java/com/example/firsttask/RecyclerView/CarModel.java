package com.example.firsttask.RecyclerView;

public class CarModel {
    private String carType;
    private String seats;
    private String rent;
    private String imageUrl;

    public CarModel(String carType, String seats, String rent, String imageUrl) {
        this.carType = carType;
        this.seats = seats;
        this.rent = rent;
        this.imageUrl = imageUrl;
    }

    public String getCarType() { return carType; }
    public String getSeats() { return seats; }
    public String getRent() { return rent; }
    public String getImageUrl() { return imageUrl; }
}
