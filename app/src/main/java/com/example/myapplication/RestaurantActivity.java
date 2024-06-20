package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class RestaurantActivity extends AppCompatActivity {

    TextView tvFoods, tvDrinks;
    int REQUEST_CODE_FOOD = 1;
    int REQUEST_CODE_DRINK = 2;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dat_mon);
    
        tvFoods = findViewById(R.id.tvFoods);
        tvDrinks = findViewById(R.id.tvDrinks);

        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        // Hiển thị nút quay lại trên Toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // Thiết lập tiêu đề cho Toolbar
        TextView toolbarTitle = findViewById(R.id.toolbar_title);
        getSupportActionBar().setDisplayShowTitleEnabled(false); // Tắt tiêu đề mặc định
        toolbarTitle.setText("Đặt món ăn Restaurant"); // Thiết lập tiêu đề tùy chỉnh

        // Xử lý sự kiện khi nhấn vào nút quay lại
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
    
        Button btnFoods = findViewById(R.id.btnFoods);
        btnFoods.setOnClickListener(v -> {
            Intent intent = new Intent(RestaurantActivity.this, FoodActivity.class);
            startActivityForResult(intent, REQUEST_CODE_FOOD);
        });
    
        Button btnDrinks = findViewById(R.id.btnDrinks);
        btnDrinks.setOnClickListener(v -> {
            Intent intent = new Intent(RestaurantActivity.this, DrinkActivity.class);
            startActivityForResult(intent, REQUEST_CODE_DRINK);
        });
    
//        Button btnExit = findViewById(R.id.btnExit);
//        btnExit.setOnClickListener(v -> {
//            showExitConfirmation();
//        });
    }
    
//    private void showExitConfirmation() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setMessage("Bạn có chắc chắn muốn thoát không?");
//        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int id) {
//                finish();
//            }
//        });
//        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int id) {
//                dialog.dismiss();
//            }
//        });
//
//        AlertDialog dialog = builder.create();
//        dialog.show();
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            if (requestCode == REQUEST_CODE_FOOD) {
                String selectedFood = data.getStringExtra("selectedFood");
                tvFoods.setText(selectedFood);
            } else if (requestCode == REQUEST_CODE_DRINK) {
                String selectedDrink = data.getStringExtra("selectedDrink");
                tvDrinks.setText(selectedDrink);
            }
        }
    }
}