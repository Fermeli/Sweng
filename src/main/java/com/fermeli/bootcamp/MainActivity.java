package com.fermeli.bootcamp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.IOException;

import static java.sql.DriverManager.println;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "Hello !";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        println("aaaaaaaaaaaaaaaaaaaaaaaa");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Context context = new Context();

    }



    /** Called when the user taps the Send button */
    public void sendMessage(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, GreetingActivity.class);
        EditText editText = (EditText) findViewById(R.id.mainName);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void ownLocation(View view) throws IOException {
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
            ActivityCompat.requestPermissions(this, permissions, 10);
            //return;
        }
        Location l = new Location(locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER));

        Weather weather = new Weather();
        String message = weather.getWeather(l);
        Intent intent = new Intent(this, GreetingActivity.class);
        //EditText editText = (EditText) findViewById(R.id.mainName);
        //String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}