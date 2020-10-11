package com.fermeli.bootcamp;

import java.io.IOException;
import java.net.MalformedURLException;

public interface WeatherService {
    public String getWeather(Location location) throws IOException;
}
