package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity3 extends AppCompatActivity {

    Button buttonBeranda2;
    Button buttonArtikel2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);


        buttonBeranda2 = findViewById(R.id.buttonBrd3);
        buttonArtikel2 = findViewById(R.id.buttonArt2);
        buttonBeranda2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity3.this, MainActivity);
                startActivity(intent);
            }
        });
        buttonArtikel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity3.this, MainActivity2);
                startActivity(intent);
            }
        });

    }
}