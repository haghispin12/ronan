package com.example.ronan;

import static android.widget.Toast.LENGTH_SHORT;

import androidx.appcompat.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.annotation.SuppressLint;
import android.util.Log;


import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationRequest;
import android.os.Bundle;
import android.Manifest;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.AutocompletePrediction;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.FetchPlaceRequest;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest;
import com.google.android.libraries.places.api.net.PlacesClient;

import java.util.Arrays;
import java.util.List;

public class AFTMain extends AppCompatActivity implements OnMapReadyCallback {

    private static int LOCATION_PERMISSION_REQUEST_CODE = 100;
    private GoogleMap googleMap;
    private final int FINE_PERMISSION_CODE = 1;
    private SearchView searchView;
    private LocationCallback locationCallback;
    Location currentLocation;
    private PlacesClient placesClient;
    private FusedLocationProviderClient fusedLocationClient;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aftmain);

//        searchView = findViewById(R.id.searchView);
//
//        Places.initialize(getApplicationContext(),getString(R.string.google_maps_key));
//        placesClient = Places.createClient(this);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //asks for location permissions
            requestLocationPermission();
        }
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        requestLocationPermission();
        getLastLocation();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String s) {
//                searchLocation(s);
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                return false;
//            }
//        });
    }
    private void searchLocation(String query){
        FindAutocompletePredictionsRequest request = FindAutocompletePredictionsRequest.builder().setQuery(query).build();
        placesClient.findAutocompletePredictions(request).addOnSuccessListener(response ->{
            List<AutocompletePrediction> predictions =
        response.getAutocompletePredictions();
        if(!predictions.isEmpty()){
            AutocompletePrediction prediction = predictions.get(0);
           placesClient.fetchPlace(FetchPlaceRequest.newInstance(prediction.getPlaceId(), Arrays.asList(Place.Field.LAT_LNG))).addOnSuccessListener(fetchPlaceResponse -> {
              LatLng latLng = fetchPlaceResponse.getPlace().getLatLng();
              if(latLng !=null ){
                  googleMap.clear();
                  googleMap.addMarker(new MarkerOptions().position(latLng).title(prediction.getPrimaryText(null).toString()));
                  googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,12));
              }
           });
        }
        }).addOnFailureListener(e -> Log.e("MainActivity","place not found"+e.getMessage()));
    }
    private void getLastLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},FINE_PERMISSION_CODE);
            return;
        }
        Task<Location> task = fusedLocationClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                    if(location != null) {
                        currentLocation = location;
                        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
                        mapFragment.getMapAsync(AFTMain.this);

                    }
            }
        });
    }


    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        LatLng myLocation = new LatLng( 32.79221, 35.53124);
        googleMap.addMarker(new MarkerOptions().position(myLocation).title("My Location"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myLocation,12));
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.getUiSettings().isCompassEnabled();
    }


    private void requestLocationPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
            Toast.makeText(this, "Location is required for this app to function properly", LENGTH_SHORT).show();
        }
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
    }




    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == FINE_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //gets user's current location
                getLastLocation();
            } else {
                Toast.makeText(this, "Location permission denied", LENGTH_SHORT).show();
            }
        }
    }
    }

