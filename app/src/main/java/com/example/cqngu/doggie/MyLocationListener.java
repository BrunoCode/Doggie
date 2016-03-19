package com.example.cqngu.doggie;

import android.app.Activity;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

/**
 * Created by cqngu on 3/19/2016.
 */
public class MyLocationListener implements LocationListener {


    @Override
    public void onLocationChanged(Location location) {
        System.out.println("L:" + location.getLatitude() + " Long: " + location.getLongitude());
    }

    @Override
    public void onProviderDisabled(String provider) {
    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }
}
