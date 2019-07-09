package com.example.lokaciinternassignment.model;

public class ShopListItem {
    private int imageid;
    private String shopName;
    private double rating;
    private double distance;
    private String address;


    public ShopListItem(int imageid, String shopName, double rating, double distance, String address) {
        this.imageid = imageid;
        this.shopName = shopName;
        this.rating = rating;
        this.distance = distance;
        this.address = address;
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
}
