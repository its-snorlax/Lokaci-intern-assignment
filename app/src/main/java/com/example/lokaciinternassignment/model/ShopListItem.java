package com.example.lokaciinternassignment.model;

public class ShopListItem {
    private int imageid;
    private String shopName;
    private double rating;
    private double distance;
    private String address;
    private double latitude;
    private double longitude;


    public ShopListItem(int imageid, String shopName, double rating, double distance, String address, double latitude, double longitude) {
        this.imageid = imageid;
        this.shopName = shopName;
        this.rating = rating;
        this.distance = distance;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getShopName(){
        return shopName;
    }

    public int getImageid() {
        return imageid;
    }

    public double getRating() {
        return rating;
    }

    public double getDistance() {
        return distance;
    }

    public String getAddress() {
        return address;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
