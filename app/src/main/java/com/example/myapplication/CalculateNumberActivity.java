package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.WindowInsetsCompat;

public class CalculateNumberActivity extends AppCompatActivity {
    EditText editNumber1, editNumber2, editKetQua;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculate_number);

        // Lấy tham chiếu đến các phần tử trong XML
        editNumber1 = findViewById(R.id.editNumber1);
        editNumber2 = findViewById(R.id.editNumber2);
        editKetQua = findViewById(R.id.editKetQua);
        Button cong = findViewById(R.id.cong);
        Button tru = findViewById(R.id.tru);
        Button nhan = findViewById(R.id.nhan);
        Button chia = findViewById(R.id.chia);

        // Gắn sự kiện click cho nút Cộng
        cong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate('+');
            }
        });

        // Gắn sự kiện click cho nút Trừ
        tru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate('-');
            }
        });

        // Gắn sự kiện click cho nút Nhân
        nhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate('*');
            }
        });

        // Gắn sự kiện click cho nút Chia
        chia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate('/');
            }
        });
    }
    // Phương thức thực hiện các phép tính
    private void calculate(char operator) {
        double num1 = Double.parseDouble(editNumber1.getText().toString());
        double num2 = Double.parseDouble(editNumber2.getText().toString());
        double result = 0;

        switch (operator) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                if (num2 != 0)
                    result = num1 / num2;
                else
                    editKetQua.setText("Không thể chia cho 0!");
                break;
        }

        editKetQua.setText(String.valueOf(result));
    }
}
