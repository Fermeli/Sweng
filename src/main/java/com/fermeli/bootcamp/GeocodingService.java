package com.fermeli.bootcamp;

public interface GeocodingService {
    public String fromLocToAdress(Location location);
    public Location fromAdressToLoc(String adress);
}
