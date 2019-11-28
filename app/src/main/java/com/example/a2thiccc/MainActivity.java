package com.example.a2thiccc;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

<<<<<<< HEAD

//TODO: everything lol
=======
import Adapter.MainAdapter;

/*TODO:
    add view


 */
>>>>>>> origin/MainScreen
public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    int[] items = {0,1,2};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerViewMain);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        MainAdapter adapter= new MainAdapter(items);
        recyclerView.setAdapter(adapter);


    }


    public void startFitnessActivity(@Nullable View view){
        Intent nextA = new Intent(MainActivity.this, FitnessActivity.class);
        startActivityForResult(nextA, 10);
    }

    public void startProfileActivity(@Nullable View view){
        Intent nextA = new Intent(MainActivity.this, ProfileActivity.class);
        startActivityForResult(nextA, 10);
    }

<<<<<<< HEAD
=======
    public void startNutritionActivity(@Nullable View view){
        Intent nextA = new Intent(MainActivity.this, NutritionActivity.class);
        startActivityForResult(nextA, 10);
    }

>>>>>>> origin/MainScreen

}
