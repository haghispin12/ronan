package com.example.ronan;

//import android.annotation.SuppressLint;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.google.android.gms.maps.model.LatLng;
//
//public class SettingsAFT extends AppCompatActivity {
//    private Button buttonH;
//    private Button buttonM;
//    private Button buttonT;
//
//    @SuppressLint("MissingInflatedId")
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.settingsaft);
//        buttonH = findViewById(R.id.Haspin);
//        buttonM = findViewById(R.id.Gamla);
//        buttonT = findViewById(R.id.Tzemach);
//
//        buttonH.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(),AFTMain.class);
//                LatLng Haspin = new LatLng(32.84571,35.79295);
//                intent.putExtra("LatLngHaspin",Haspin);
//                finish();
//            }
//        });
//        buttonM.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(),AFTMain.class);
//                LatLng Gamla = new LatLng(32.84571,35.79295);
//                intent.putExtra("LatLngHaspin",Gamla);
//                finish();
//            }
//        });
//        buttonT.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
//    }
//}
