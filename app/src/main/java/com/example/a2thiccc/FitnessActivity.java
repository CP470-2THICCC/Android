package com.example.a2thiccc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class FitnessActivity extends AppCompatActivity {
    Button button;
    BeginnerLevel fragment1;
    IntermediateLevel fragment2;
    AdvancedLevel fragment3;
    final int REQUEST_CODE = 10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitness);
        button = findViewById(R.id.proceedButton);
        Spinner spinner = (Spinner) findViewById(R.id.difficulty_level);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FitnessActivity.this, FitnessActivity2.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        fragment1 = new BeginnerLevel();
        fragment2 = new IntermediateLevel();
        fragment3 = new AdvancedLevel();

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(this, R.layout.spinner_item, getResources().getStringArray(R.array.difficulties));
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(spinnerArrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                switch (i) {
                    case 0:
                        setFragment(fragment1);
                        break;
                    case 1:
                        setFragment(fragment2);
                        break;
                    case 2:
                        setFragment(fragment3);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    public void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.descriptionBox, fragment);
        fragmentTransaction.commit();
    }
}
