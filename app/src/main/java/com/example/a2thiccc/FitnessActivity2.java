package com.example.a2thiccc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class FitnessActivity2 extends AppCompatActivity {
    Button button;
    Button button2;
    final int REQUEST_CODE = 10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitness2);
        button = findViewById(R.id.list_exercises);
        button2 = findViewById(R.id.list_exercises2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FitnessActivity2.this, ExerciseActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FitnessActivity2.this, ExerciseActivity2.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }
}