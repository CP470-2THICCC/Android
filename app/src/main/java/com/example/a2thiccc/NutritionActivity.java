package com.example.a2thiccc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;


//TODO: everything lol
public class NutritionActivity extends AppCompatActivity {

    private Button waterButton;
    private Button calButton;
    private TextView waterLeft;
    private TextView calLeft;
    private EditText waterIn;
    private EditText calIn;
    private ProgressBar waterProgressBar;
    private ProgressBar calProgressBar;
    private CalendarView simpleCalendarView;

    //private View v = findViewById(android.R.id.content);

    String water = "";
    int waterEntered = 0;
    String cals = "";
    int calsEntered = 0;
    String waterInput = "";
    int waterResult = 0;
    String calInput = "";
    int calResult = 0;

    int waterProgress = 0;
    int calProgress = 0;

    int[][][] waterArray = new int [2030][13][32];
    int[][][] calArray = new int [2030][13][32];

    int currentYear = 2019;
    int currentDay = 1;
    int currentMonth = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition);

        simpleCalendarView = findViewById(R.id.calendarView);

        for (int i = 0; i < 2030; i++) {
            for (int j = 0; j < 13; j++) {
                for (int k = 0; k < 32; k++) {
                    waterArray[i][j][k] = 0;
                    calArray[i][j][k] = 0;
                }
            }
        }

        waterLeft = findViewById(R.id.waterLeftText);
        waterButton = findViewById(R.id.waterButton);
        waterIn = findViewById(R.id.waterEditText);
        waterProgressBar = (ProgressBar) findViewById(R.id.waterProgressBar);


        calLeft = findViewById(R.id.calLeftText);
        calButton = findViewById(R.id.calButton);
        calIn = findViewById(R.id.calEditText);
        calProgressBar = (ProgressBar) findViewById(R.id.calProgressBar);


        /**
         * Checks if the date was changed on the calendar and updates water and cal progress
         */
        simpleCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                currentYear = year;
                currentMonth = month;
                currentDay = dayOfMonth;

                waterProgress = waterArray[currentYear][currentMonth][currentDay];
                waterResult = 8 - waterProgress;
                waterProgressBar.setProgress(waterProgress);
                waterLeft.setText("Glasses left to drink: " +waterResult);

                calProgress = calArray[currentYear][currentMonth][currentDay];
                calResult = 2000 - calProgress;
                calProgressBar.setProgress(calProgress);
                calLeft.setText("Max calories left: " +calResult);
            }
        });


        /**
         * After waterbutton is pressed the amount of water entered is added to the total water intake for that day
         */
        waterButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                waterInput = waterIn.getText().toString();
                waterEntered = Integer.parseInt(waterInput);
                waterArray[currentYear][currentMonth][currentDay] = waterArray[currentYear][currentMonth][currentDay] + waterEntered;
                waterProgress = waterArray[currentYear][currentMonth][currentDay];
                //waterProgress = waterProgress + waterEntered;
                if (waterProgress > 8) {
                    waterProgress = 8;
                }
                waterResult = 8 - waterProgress;
                waterProgressBar.setProgress(waterProgress);
                waterLeft.setText("Glasses left to drink: " +waterResult);
                if (waterResult == 0) {
                    Toast toast = Toast.makeText(getApplicationContext(), "You drank the daily recommended amount of water", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });

        /**
         * After waterbutton is pressed the amount of water entered is added to the total water intake for that day
         */
        calButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                calInput = calIn.getText().toString();
                calsEntered = Integer.parseInt(calInput);
                calArray[currentYear][currentMonth][currentDay] = calArray[currentYear][currentMonth][currentDay] + calsEntered;
                calProgress = calArray[currentYear][currentMonth][currentDay];
                //calProgress = calProgress + calsEntered;
                if (calProgress > 2000) {
                    calProgress = 2000;
                    /*
                    Snackbar snackbar = Snackbar
                            .make(v, "You've exceeded your daily calorie limit!", Snackbar.LENGTH_LONG)
                            .setAction("I will try to do better", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Snackbar snackbar1 = Snackbar.make(v, "Good, otherwise you will stay 2Thicc!", Snackbar.LENGTH_SHORT);
                                    snackbar1.show();
                                }
                            });

                    snackbar.show();
                     */
                }
                calResult = 2000 - calProgress;
                calProgressBar.setProgress(calProgress);
                calLeft.setText("Max calories left: " +calResult);
            }
        });
    }
}

