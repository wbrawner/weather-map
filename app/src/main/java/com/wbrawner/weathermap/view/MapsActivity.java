package com.wbrawner.weathermap.view;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.wbrawner.weathermap.R;
import com.wbrawner.weathermap.service.FetchWeatherIntentService;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final int PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION = 0;
    private static final String TAG = MapsActivity.class.getSimpleName();
    private boolean mapLoaded = false;
    private FusedLocationProviderClient mFusedLocationClient;
    private GoogleMap mMap;
    private LatLng currentLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        // Begin loading the map asynchronously
        mapFragment.getMapAsync(this);
        // Set Sydney as the default loading location in case we don't have access to the user location
        currentLocation = new LatLng(-34, 151);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        // While the map is loading, check that we have permissions to access user location
        checkLocationPermission();
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        mapLoaded = true;
        mMap.addMarker(new MarkerOptions().position(currentLocation));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(currentLocation));
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                Log.d(TAG, "Map clicked");
                updateLocationMarker(latLng);
            }
        });
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Log.d(TAG, "Marker tapped");
                Intent weatherDetailIntent = new Intent(MapsActivity.this, WeatherActivity.class);
                startActivity(weatherDetailIntent);
                return true;
            }
        });
    }

    /**
     * Ensure that we have the permissions needed, otherwise request them
     */
    public void checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(MapsActivity.this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MapsActivity.this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION);
        } else {
            getLocation();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION:
                getLocation();
        }
    }

    public void getLocation() {
        try {
            mFusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            // Got last known location. In some rare situations this can be null.
                            if (location != null) {
                                updateLocationMarker(
                                        new LatLng(
                                                location.getLatitude(),
                                                location.getLongitude()
                                        )
                                );
                            }
                        }
                    });
        } catch (SecurityException e) {
            Log.e(TAG, "Security exception: ", e);
        }
    }
    public void updateLocationMarker(LatLng latLng) {
        // Update the location
        currentLocation = latLng;
        // Fetch the weather in the background
        FetchWeatherIntentService.startActionFetch(
                MapsActivity.this,
                String.valueOf(latLng.latitude),
                String.valueOf(latLng.longitude)
        );
        if (mapLoaded) {
            mMap.clear();
            mMap.addMarker(new MarkerOptions().position(currentLocation));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(currentLocation));
        }
    }
}
