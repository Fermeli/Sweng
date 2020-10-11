package com.fermeli.bootcamp;

import android.util.Log;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.net.ssl.HttpsURLConnection;

import static java.sql.DriverManager.println;

public class Weather implements WeatherService {
    private JSONObject weather;
    public Weather() {
        //this.weather = weather;
    }

    private String readStream(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader r = new BufferedReader(new InputStreamReader(is),1000);
        for (String line = r.readLine(); line != null; line =r.readLine()){
            sb.append(line);
        }
        is.close();
        return sb.toString();
    }

    @Override
    public String getWeather(Location location) throws IOException {
        String queryUrl = "https://www.google.fr";//"https://api.openweathermap.org/data/2.5/onecall?lat="+ location.getLatitude() + "&lon=" + location.getLongitude() +"&appid=9e5ff569627f328dcaa04bb99cc4d25b";
        URL url = new URL(queryUrl);
        InputStream stream = null;
        HttpsURLConnection connection = null;
        String result = "";

        /*connection = (HttpsURLConnection) url.openConnection();
        connection.setReadTimeout(3000);
        connection.setConnectTimeout(3000);
        connection.setRequestMethod("GET");

// Already true by default but setting just in case; needs to be true since this request
// is carrying an input (response) body.
        connection.setDoInput(true);

        int responseCode = connection.getResponseCode();
// Do something with responseCode

        stream = connection.getInputStream();
        StringBuilder s = new StringBuilder();
        int i = stream.read();
        while(i != -1) {
            s.append((char) i);
            i = stream.read();
        }

        Log.i("tag","aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa : " + s.toString());
// Do something with the stream

        stream.close();
        connection.disconnect();
        return null;*/
        try {
            connection = (HttpsURLConnection) url.openConnection();
            // Timeout for reading InputStream arbitrarily set to 3000ms.
            connection.setReadTimeout(3000);
            // Timeout for connection.connect() arbitrarily set to 3000ms.
            connection.setConnectTimeout(3000);
            // For this use case, set HTTP method to GET.
            connection.setRequestMethod("GET");
            // Already true by default but setting just in case; needs to be true since this request
            // is carrying an input (response) body.
            connection.setDoInput(true);
            // Open communications link (network traffic occurs here).
            //System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
            connection.connect();


            //publishProgress(DownloadCallback.Progress.CONNECT_SUCCESS);
            int responseCode = connection.getResponseCode();
            if (responseCode != HttpsURLConnection.HTTP_OK) {
                throw new IOException("HTTP error code: " + responseCode);
            }
            // Retrieve the response body as an InputStream.
            stream = connection.getInputStream();
            //publishProgress(DownloadCallback.Progress.GET_INPUT_STREAM_SUCCESS, 0);
            if (stream != null) {
                // Converts Stream to String with max length of 500.
                result = readStream(stream);
            }
        } finally {
            // Close Stream and disconnect HTTPS connection.
            if (stream != null) {
                stream.close();
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
        return result;
    }
}
