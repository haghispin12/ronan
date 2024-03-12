//package com.example.ronan.MathProject;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.SeekBar;
//
//import com.example.ronan.R;
//
//public  class RateActivity extends AppCompatActivity  {
//
//    SeekBar seekbar;
//    Button save;
//    private int sp;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_rate);
//
//        save = findViewById(R.id.save);
//        seekbar = findViewById(R.id.seekBar);
//        save.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent inn = new Intent();
//                inn.putExtra("rate", seekbar.getProgress());
//
//                setResult(RESULT_OK, inn);
//
//                finish();
//            }
//        });
//    }
//
//}
