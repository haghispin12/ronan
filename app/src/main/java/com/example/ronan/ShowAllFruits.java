package com.example.ronan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class ShowAllFruits extends AppCompatActivity {

    private RecyclerView rcShowFruits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_fruits);
          rcShowFruits = findViewById(R.id.rcShowFruits);

        ArrayList<Fruit> fruits = new ArrayList<>();
        fruits.add (new Fruit())
    }
}