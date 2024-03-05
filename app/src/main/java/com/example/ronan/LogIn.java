package com.example.ronan;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LogIn extends AppCompatActivity {

    private EditText Name;
    private Button Submit;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        Name = findViewById(R.id.name);
        Submit = findViewById(R.id.submit);

        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
        String s1= sharedPreferences.getString("name","");
        Name.setText(s1);



        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putString("name",Name.getText().toString() );
                myEdit.apply();



                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                intent.putExtra("username",Name.getText().toString());
                startActivity(intent);

                finish();

            }
        });

    }
    }
