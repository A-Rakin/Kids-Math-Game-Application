package com.example.mathgame;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.QuickContactBadge;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button addition;
    Button subtract;
    Button multi;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        addition = findViewById(R.id.additionButton);
        subtract = findViewById(R.id.subtractionButton);
        multi = findViewById(R.id.multiplicationButton);
        addition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AdditionGame.class);
                startActivity(intent);
            }
        });
        subtract.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SubtractionGame.class);
                startActivity(intent);
            }
        });
        multi.setOnClickListener(new View.OnClickListener() {
            //Change 1

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MultiplicationGame.class);
                startActivity(intent);
            }
        });
    }
}