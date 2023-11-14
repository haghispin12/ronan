package com.example.ronan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    private void showToast(String s){
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
    }
    private Button etgar;
    private Button multiply20;
    private Button timeTable;
    private TextView num1;
    private TextView num2;
    private EditText num3;
    private Button check;
    private Button save;
    private Button show;
    exercise e;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        e= new exercise();
        setContentView(R.layout.activity_main);
        etgar = findViewById(R.id.etgar);
        multiply20 = findViewById(R.id.multiply20);
        timeTable = findViewById(R.id.timeTable);
        num1 = findViewById(R.id.num1);
        num2 = findViewById(R.id.num2);
        num3 = findViewById(R.id.num3);
        check = findViewById(R.id.check);
        save = findViewById(R.id.save);
        show = findViewById(R.id.show);

        etgar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                e.r3();
                num1.setText(e.getNum1()+"");
                num2.setText(e.getNum2()+"");
            }
        });
        multiply20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                e.r2();
                num1.setText(e.getNum1()+"");
                num2.setText(e.getNum2()+"");

            }
        });
        timeTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                e.r1();
                num1.setText(e.getNum1()+"");
                num2.setText(e.getNum2()+"");
            }
        });
        num3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num3.setText("");
                boolean b = e.GoodAnswer(num3.getText().toString());
                if(b==true)
                    showToast("Correct Answer");
                showToast("Wrong Answer");
                num1.setText("");
                num2.setText("");
                num3.setText("");
            }
        });
    }





}