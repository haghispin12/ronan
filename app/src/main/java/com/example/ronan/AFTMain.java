package com.example.ronan;

import static android.widget.Toast.LENGTH_SHORT;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.Manifest;
import android.os.IBinder;
import android.os.PowerManager;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import java.util.ArrayList;
import java.util.List;

public class AFTMain extends AppCompatActivity implements OnMapReadyCallback {

    private static int LOCATION_PERMISSION_REQUEST_CODE = 100;
    private GoogleMap googleMap;
    private final int FINE_PERMISSION_CODE = 1;
    int n = 10;
    Location currentLocation;
    List<double[]> LAT;
    double VerticesSize;
    LatLng temporaryLocation;
    private Button buttonG;
    private Button buttonH;
    private Button buttonT;
    private LatLng centrePolygon;
    private List<LatLng> verticesPolygon;
    private List<Polygon> polygons = new ArrayList<>();
    private List<Marker> markers = new ArrayList<>();
    private Button ss;
    private Button buttonN;
    private Intent serviceIntent;
    private FusedLocationProviderClient fusedLocationClient;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aftmain);
        buttonG = findViewById(R.id.SetPolygon);
        buttonH = findViewById(R.id.check);
        buttonT = findViewById(R.id.CurrentLocation);
        buttonN = findViewById(R.id.Nov);
        serviceIntent = new Intent(this, SoundService.class);
        ss = findViewById(R.id.stop_Sound);


        ss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService(serviceIntent);
            }
        });


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
               addPolygon(temporaryLocation.latitude,temporaryLocation.longitude);
            }
        });
        buttonH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isInside = PolygonUtils.isPointInPolygon(temporaryLocation,verticesPolygon);
                if(isInside){
                    Toast.makeText(AFTMain.this, "u arein range of destination", LENGTH_SHORT).show();
                    startService(serviceIntent);
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(temporaryLocation, 15));
                }
                    Toast.makeText(AFTMain.this,"U are not in range",LENGTH_SHORT).show();
            }
        });

        buttonT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLocation(currentLocation.getLatitude(),currentLocation.getLongitude());
            }
        });
        buttonN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLocation(32.8325, 35.783097);
                temporaryLocation = new LatLng(32.8325, 35.783097);
                List<LatLng> polygonVertices = new ArrayList<>();
                polygonVertices.addAll(createRoundPolygon(temporaryLocation));

                boolean isInside = PolygonUtils.isPointInPolygon(temporaryLocation, polygonVertices);
                if (isInside) {
                    Toast.makeText(AFTMain.this, "U are within range of the destination", LENGTH_SHORT).show();
                    startService(serviceIntent);
                } else {
                    Toast.makeText(AFTMain.this, "U are not within range of your destination", LENGTH_SHORT).show();
                }
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
        this.googleMap = googleMap;
        LatLng myLocation = new LatLng(32.79221, 35.53124);
        googleMap.addMarker(new MarkerOptions().position(myLocation).title("My Location"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myLocation, 12));
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.getUiSettings().isCompassEnabled();
        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(@NonNull LatLng latLng) {
                addMarker(latLng);
                temporaryLocation = latLng;


            }
        });
    }

    public void addPolygon(double latitude, double longitude) {
        for(Polygon polygon : polygons){
            polygon.remove();
        }
        polygons.clear();

        LatLng latLng = new LatLng(latitude, longitude);
        centrePolygon = latLng;
        verticesPolygon = createRoundPolygon(centrePolygon);
        PolygonOptions polygonOptions = new PolygonOptions();
        polygonOptions.addAll(createRoundPolygon(latLng));
        polygonOptions.strokeWidth(5);
        polygonOptions.fillColor(0x5500ff00);
        Polygon polygon = googleMap.addPolygon(polygonOptions);
        verticesPolygon = createRoundPolygon(latLng);

        polygons.add(polygon);
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

    public List<LatLng> createRoundPolygon(LatLng centre) {
        double sides = 36;
        double radiusKm = 5;
        double radius = radiusKm / 111.32;
        double angleStep = 2 * Math.PI / sides;
        List<LatLng> points = new ArrayList<>();
        for (int i = 0; i < sides; i++) {
            double theta = i * angleStep;
            double lat = centre.latitude + radius * Math.cos(theta);
            double lng = centre.longitude + radius * Math.sin(theta) / Math.cos(Math.toRadians(centre.latitude));
            points.add(new LatLng(lat, lng));
        }
        points.add(points.get(0));
        VerticesSize = points.size();
        return points;
    }
    public void addMarker(LatLng latLng){
        for(Marker marker : markers){
            marker.remove();
        }
        markers.clear();
        Marker marker = googleMap.addMarker(new MarkerOptions().position(latLng).title("New Marker"));
        markers.add(marker);
    }
}

