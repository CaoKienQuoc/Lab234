package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ListLabActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnRandom;
    private Button btnCalculate;
    private Button btnCours;
    private Button btnFruits;
    private Button btnRestaurant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_lab);
 
        // Ánh xạ các nút từ XML
        btnRandom = findViewById(R.id.btnRandom);
        btnCalculate = findViewById(R.id.btnCalculate);
        btnCours = findViewById(R.id.btnCours);
        btnFruits = findViewById(R.id.btnFruits);
        btnRestaurant = findViewById(R.id.btnRestaurant);
 
        // Đăng ký lắng nghe sự kiện click
        btnRandom.setOnClickListener(this);
        btnCalculate.setOnClickListener(this);
        btnCours.setOnClickListener(this);
        btnFruits.setOnClickListener(this);
        btnRestaurant.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btnRandom) {
            startActivity(new Intent(this, RandomNumberActivity.class));
        } else if (id == R.id.btnCalculate) {
            startActivity(new Intent(this, CalculateNumberActivity.class));
        } else if (id == R.id.btnCours) {
            startActivity(new Intent(this, ListViewActivity.class));
        } else if (id == R.id.btnFruits) {
            startActivity(new Intent(this, CustomListViewActivity.class));
        } else if (id == R.id.btnRestaurant) {
            startActivity(new Intent(this, RestaurantActivity.class));
        }
    }
}