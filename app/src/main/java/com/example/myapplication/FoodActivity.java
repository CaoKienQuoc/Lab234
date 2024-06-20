package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;

public class FoodActivity extends AppCompatActivity {
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_activity);

        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        // Hiển thị nút quay lại trên Toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // Thiết lập tiêu đề cho Toolbar
        TextView toolbarTitle = findViewById(R.id.toolbar_title);
        getSupportActionBar().setDisplayShowTitleEnabled(false); // Tắt tiêu đề mặc định
        toolbarTitle.setText("Đặt đồ uống"); // Thiết lập tiêu đề tùy chỉnh

        // Xử lý sự kiện khi nhấn vào nút quay lại
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        Button btnPho = findViewById(R.id.button4);
        btnPho.setOnClickListener(v -> returnResult("Lẩu Bò"));
        Button btnBunBo = findViewById(R.id.button5);
        btnBunBo.setOnClickListener(v -> returnResult("Lẩu Thái"));
        Button btnMiQuang = findViewById(R.id.button6);
        btnMiQuang.setOnClickListener(v -> returnResult("Lẩu Riêu Cua"));
        Button btnHuTieu = findViewById(R.id.button7);
        btnHuTieu.setOnClickListener(v -> returnResult("Lẩu Hải Sản"));
    }

    private void returnResult(String food) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("selectedFood", food);
        setResult(RESULT_OK, returnIntent);
        finish();
    }
}
