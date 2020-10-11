package com.fermeli.bootcamp;

public class Location {
    private android.location.Location location;
    public Location(android.location.Location location) {
        this.location = location;
    }

    public double getLatitude() {
        return location.getLatitude();
    }

    public double getLongitude() {
        return location.getLongitude();
    }
}
