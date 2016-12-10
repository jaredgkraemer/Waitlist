package com.example.jason.myapplication;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;

/**
 * Created by Sam on 12/10/2016.
 * Gets users lat and long with network or gps
 *
 */
public class GPSTracker extends Service implements LocationListener {
    private final Context mContext;
    Location mLocation;
    double lat, lon;

    //flags
    boolean networkEnabled = false;
    boolean getSetLocation = false;
    boolean gpsEnabled = false;

    //Constants for gps
    private static final long UPDATE_MIN_DISTANCE = 10;
    private static final long UPDATE_TIME = 60000;

    protected LocationManager mLocationManager;

    public GPSTracker(Context context){
        this.mContext = context;
        getLocation();
    }

    public Location getLocation(){
        try{
            mLocationManager = (LocationManager) mContext.getSystemService(LOCATION_SERVICE);

            //Set flags
            gpsEnabled = mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            networkEnabled = mLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if(Build.VERSION.SDK_INT >= 23 &&
                    ContextCompat.checkSelfPermission( mContext,
                    Manifest.permission.ACCESS_FINE_LOCATION ) == PackageManager.PERMISSION_GRANTED &&
                    (gpsEnabled || networkEnabled)){
                this.getSetLocation = true;

                if(networkEnabled){
                    mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                            UPDATE_TIME, UPDATE_MIN_DISTANCE, this);
                    Log.d("Network", "Network");
                    if(mLocationManager != null){
                        mLocation = mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        if(mLocation != null){
                            lat = mLocation.getLatitude();
                            lon = mLocation.getLongitude();
                        }
                    }
                }
                if(gpsEnabled){
                    if(mLocation == null){
                        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                                UPDATE_TIME, UPDATE_MIN_DISTANCE, this);
                        Log.d("GPS Enabled", "GPS Enabled");
                        if(mLocationManager != null){
                            mLocation = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                            if(mLocation != null){
                                lat = mLocation.getLatitude();
                                lon = mLocation.getLongitude();
                            }
                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return mLocation;
    }

    public double getLat(){
        if(mLocation != null){
            lat = mLocation.getLatitude();
        }

        return lat;
    }

    public double getLon(){
        if(mLocation != null){
            lon = mLocation.getLongitude();
        }

        return lon;
    }

    public boolean getSetLocation(){
        return this.getSetLocation;
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


}
