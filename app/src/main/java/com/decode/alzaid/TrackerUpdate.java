package com.decode.alzaid;

import android.app.IntentService;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.security.Provider;

public class TrackerUpdate extends IntentService {

    private static final String TAG = "TrackerUpdate";
    
    public TrackerUpdate() {
        super(TrackerUpdate.class.getName());
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d("Service","Started");
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference locref = FirebaseDatabase.getInstance().getReference().child("users").child(user.getUid());
        locref.child("location").setValue("testtest");
        // Fetch a reference to the system Location Manager
        LocationManager locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);

        // Identify a listener that responds to location updates
        LocationListener locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                // Called when a new location is found by the network location provider.
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                DatabaseReference locref = FirebaseDatabase.getInstance().getReference().child("users").child(user.getUid());
                String latitude = Double.toString(location.getLatitude());
                //Log.d(TAG, "onLocationChanged: "+user.getUid());
                String longitude = Double.toString(location.getLongitude());
                Log.d("Service","JJJ");
                locref.child("location").setValue(latitude + " " + longitude);
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            public void onProviderEnabled(String provider) {
            }

            public void onProviderDisabled(String provider) {
            }
        };

        // Register the listener with the Location Manager to receive location updates
        /*if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            Log.d("Service","Error permission");
            return;
        }*/

        try {
            Criteria criteria = new Criteria();
            String provider = locationManager.getBestProvider(criteria,false);
            locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER,20000,1, locationListener);
        }
        catch (SecurityException e){
            Log.d("Service","Error security");
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }
}
