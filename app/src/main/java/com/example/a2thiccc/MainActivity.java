package com.example.a2thiccc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

/*TODO:
    add view


 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageButton settingsButton = findViewById(R.id.settingsB);
        final ImageButton profileButton = findViewById(R.id.profileB);
        final ImageButton fitnessButton = findViewById(R.id.fitnessB);
        final ImageButton nutritionButton = findViewById(R.id.nutritionB);

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
