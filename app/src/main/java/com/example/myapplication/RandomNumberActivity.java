package com.example.myapplication;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Random;

public class RandomNumberActivity  extends AppCompatActivity {
    private EditText editMin, editMax;
    private TextView editResults;
    private Button generator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.random_number);

        editMin = findViewById(R.id.editMin);
        editMax = findViewById(R.id.editMax);
        editResults = findViewById(R.id.editResults);
        generator = findViewById(R.id.generator);

        generator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateRandomNumber();
            }
        });
    }

    private void generateRandomNumber() {
        try {
            int min = Integer.parseInt(editMin.getText().toString());
            int max = Integer.parseInt(editMax.getText().toString());

            Random random = new Random();
            int randomNumber = random.nextInt((max - min) + 1) + min;

            editResults.setText(String.valueOf(randomNumber));
        } catch (NumberFormatException e) {
            editResults.setText("Invalid input");
        }
    }
}
