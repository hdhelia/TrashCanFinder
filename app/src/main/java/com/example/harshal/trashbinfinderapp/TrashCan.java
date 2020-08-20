package com.example.harshal.trashbinfinderapp;

public class TrashCan {

    private double Lat;
    private double Long;

    public TrashCan() {
    }

    public TrashCan(double lat, double aLong) {
        Lat = lat;
        Long = aLong;
    }



    public double getLat() {
        return Lat;
    }

    public void setLat(double lat) {
        Lat = lat;
    }

    public double getLong() {
        return Long;
    }

    public void setLong(double aLong) {
        Long = aLong;
    }
    
}
