package com.example.ronan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
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
    ModelView vm;
    user u;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String userName = intent.getStringExtra("username");
        showToast("Welcome "+userName);
        u.setName(userName);


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

        vm = new ViewModelProvider(this).get(ModelView.class);
        e= new exercise();
        vm.Vnum1.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                integer = vm.getVnum1();
              num1.setText(integer+"");
            }
        });
        vm.Vnum2.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                integer = vm.getVnum2();
                num2.setText(integer+"");
            }
        });

        etgar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  vm.VEtgar();
            }
        });
        multiply20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vm.Vmultiply20();
            }
        });
        timeTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vm.Vtimetable();
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
                int ark = vm.getVnum1()*vm.getVnum2();
                if(ark == vm.getRes()){
                    showToast("Good job");
                }else{
                    showToast("Try harder");}
                num1.setText("");
                num2.setText("");
                num3.setText("");
            }
        });
    }





}