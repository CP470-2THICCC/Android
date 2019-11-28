package com.example.a2thiccc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/*TODO:
    add bmi
    add water intake
    add calorie counter
    maybe recipies
* */
public class NutritionActivity extends AppCompatActivity {

    private Button waterButton;
    private Button calButton;
    private TextView waterLeft;
    private TextView calLeft;
    private EditText waterIn;
    private EditText calIn;

    String water = "";
    int waterEntered = 0;
    String cals = "";
    int calsEntered = 0;
    String waterInput = "";
    int waterResult = 0;
    String calInput = "";
    int calResult = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition);

        waterLeft = findViewById(R.id.waterLeftText);
        waterButton = findViewById(R.id.waterButton);
        waterIn = findViewById(R.id.waterEditText);


        calLeft = findViewById(R.id.calLeftText);
        calButton = findViewById(R.id.calButton);
        waterIn = findViewById(R.id.waterEditText);




        waterButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                waterInput = waterIn.getText().toString();
                waterEntered = Integer.parseInt(waterInput);
                waterResult = 8 - waterEntered;
                waterLeft.setText("Glasses left to drink: " +waterResult);
            }
        });

        calButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                calInput = calIn.getText().toString();
                calsEntered = Integer.parseInt(calInput);
                calResult = 2000 - calsEntered;
                calLeft.setText("Max calories left: " +calResult);
            }
        });
    }
}

