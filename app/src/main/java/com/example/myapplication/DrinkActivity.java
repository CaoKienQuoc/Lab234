package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;

public class DrinkActivity extends AppCompatActivity
{
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drink_activity);

        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        // Hiển thị nút quay lại trên Toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // Thiết lập tiêu đề cho Toolbar
        TextView toolbarTitle = findViewById(R.id.toolbar_title);
        getSupportActionBar().setDisplayShowTitleEnabled(false); // Tắt tiêu đề mặc định
        toolbarTitle.setText("Đặt đồ ăn"); // Thiết lập tiêu đề tùy chỉnh

        // Xử lý sự kiện khi nhấn vào nút quay lại
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        Button btnPepsi = findViewById(R.id.button);
        btnPepsi.setOnClickListener(v -> returnResult("Nước Lọc"));
        Button btnHeineken = findViewById(R.id.button2);
        btnHeineken.setOnClickListener(v -> returnResult("333"));
        Button btnTiger = findViewById(R.id.button3);
        btnTiger.setOnClickListener(v -> returnResult("Tiger"));
        Button btnSaigonRed = findViewById(R.id.button8);
        btnSaigonRed.setOnClickListener(v -> returnResult("Sài Gòn Xanh"));
    }

    private void returnResult(String drink) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("selectedDrink", drink);
        setResult(RESULT_OK, returnIntent);
        finish();
    }
}