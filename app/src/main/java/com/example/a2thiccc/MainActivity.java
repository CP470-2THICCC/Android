package com.example.a2thiccc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

//TODO: everything lol
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button settingsButton = findViewById(R.id.settingsB);
        final Button profileButton = findViewById(R.id.profileB);
        final Button fitnessButton = findViewById(R.id.fitnessB);
        final Button nutritionButton = findViewById(R.id.nutritionB);

        //Settings intent
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextA = new Intent(MainActivity.this, SettingsActivity.class);
                startActivityForResult(nextA, 10);
            }
        });

        //Profile intent
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextA = new Intent(MainActivity.this, ProfileActivity.class);
                startActivityForResult(nextA, 10);

            }
        });

        //Fitness intent
        fitnessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextA = new Intent(MainActivity.this, FitnessActivity.class);
                startActivityForResult(nextA, 10);
            }
        });

        //Nutrition intent
        nutritionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextA = new Intent(MainActivity.this, NutritionActivity.class);
                startActivityForResult(nextA, 10);
            }
        });
    }
}
