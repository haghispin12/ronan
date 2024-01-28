package com.example.ronan;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
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
    private Button rate;
    private Button check;
    private Button Msave;
    private Button show;
    private TextView score;
    exercise e;
    ModelView vm;



    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    int nyrate = result.getData().getIntExtra("rate", 0);
                    vm.setRate(nyrate);
                    rate.setText(nyrate+"");
                }
            }
    );
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        String userName = intent.getStringExtra("username");
        showToast("Welcome "+userName);




        etgar = findViewById(R.id.etgar);
        multiply20 = findViewById(R.id.multiply20);
        timeTable = findViewById(R.id.timeTable);
        num1 = findViewById(R.id.num1);
        num2 = findViewById(R.id.num2);
        num3 = findViewById(R.id.num3);
        check = findViewById(R.id.check);
        Msave = findViewById(R.id.MSave);
        show = findViewById(R.id.show);
        score = findViewById(R.id.Score);
        rate = findViewById(R.id.Rate);




        vm = new ViewModelProvider(this).get(ModelView.class);
        e= new exercise();
        vm.updateName(userName);
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
        vm.VScore.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {

                score.setText(integer+"");
            }
        });
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//               Intent intent1 = new Intent(getApplicationContext(),ShowAllFruits.class);
//               startActivity(intent1);
                FragmentTransaction trans = getSupportFragmentManager().beginTransaction();

                trans.add(R.id.frameLayout, new fragment());

                trans.commit();


            }
        });
        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),RateActivity.class);
                activityResultLauncher.launch(intent);
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
        Msave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = num3.getText().toString();
                int ark = Integer.valueOf(s);
                if(ark == vm.getRes()){
                    showToast("Good job");
                    score.setText(vm.VgetScore()+"");

                }else{
                    showToast("Try harder");}


                num1.setText("");
                num2.setText("");
                num3.setText("");
            }
        });

    }





}