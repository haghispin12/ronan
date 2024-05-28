package com.example.ronan;

import static android.widget.Toast.LENGTH_SHORT;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.Manifest;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;


import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class AFTMain extends AppCompatActivity implements OnMapReadyCallback {

    private static int LOCATION_PERMISSION_REQUEST_CODE = 100;
    private GoogleMap googleMap;
    private final int FINE_PERMISSION_CODE = 1;
    int n = 10;
    Location currentLocation;
    private List<LatLng> list;
    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private Button buttonG;
    private final LatLng Tiberia = new LatLng(32.79221,35.53124);
    private final LatLng Gamla = new LatLng(32.9052,35.7487);
    private Button buttonH;
    private Button buttonT;
    private FusedLocationProviderClient fusedLocationClient;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aftmain);
        buttonG = findViewById(R.id.Gamla);
        buttonH = findViewById(R.id.Haspin);
        buttonT = findViewById(R.id.Tzemach);



        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            requestLocationPermission();
        }
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        getLastLocation();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.id_map);
        mapFragment.getMapAsync(this);

        buttonG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLocation(32.9052,35.7487);
            }
        });
        buttonH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLocation(32.84571,35.79295);
                addPolygon(32.84571,35.79295);
            }
        });
        buttonT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLocation(35.587569,32.704864);
            }
        });
    }


    @SuppressLint("MissingPermission")
    private void getLastLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        fusedLocationClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
          @Override
           public void onSuccess(Location location) {
                if (location != null) {
                currentLocation = location;
                setLocation(currentLocation.getLatitude(), currentLocation.getLongitude());
         } else {
                 Toast.makeText(AFTMain.this, "location not found", LENGTH_SHORT).show();
         }
         }
         }
        );
    }


    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        this.googleMap=googleMap;
        LatLng myLocation = new LatLng(32.79221, 35.53124);
        googleMap.addMarker(new MarkerOptions().position(myLocation).title("My Location"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myLocation, 12));
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.getUiSettings().isCompassEnabled();
    }
    public void addPolygon(double latitude, double longitude){
        LatLng latLng = new LatLng(latitude, longitude);
//        Polygon polygon = googleMap.addPolygon(new PolygonOptions().add(latLng,Tiberia,Gamla)
//                        .strokeColor(Color.parseColor("#FFC107")).fillColor(Color.parseColor("#FFC107")));
        PolygonOptions polygonOptions = new PolygonOptions();
        polygonOptions.addAll(createRoundPolygon(latLng));
        polygonOptions.strokeWidth(5);
        polygonOptions.fillColor(0x5500ff00);
        Polygon polygon = googleMap.addPolygon(polygonOptions);
    }


    public void setLocation(double latitude, double longitude) {
        LatLng newLocation = new LatLng(latitude, longitude);
         googleMap.addMarker(new MarkerOptions().position(newLocation).title("My Location"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(newLocation, 12));
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
                getLastLocation();
            } else {
                Toast.makeText(this, "Location permission denied", LENGTH_SHORT).show();
            }
        }
    }
    public List<LatLng> createRoundPolygon(LatLng centre){
        double sides = 36;
        double radiusKm = 5;
        double radius = radiusKm/111.32;
        double angleStep = 2*Math.PI/sides;
        List<LatLng> points = new ArrayList<>();
        for(int i = 0; i<sides;i++){
            double theta = i*angleStep;
            double lat = centre.latitude+radius*Math.cos(theta);
            double lng = centre.longitude+radius*Math.sin(theta)/Math.cos(Math.toRadians(centre.latitude));
            points.add(new LatLng(lat, lng));
        }points.add(points.get(0));
        return points;
    }

}

