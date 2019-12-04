package com.example.a2thiccc;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import Adapter.MainAdapter;

/*TODO:
    add view


 */
public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    MainAdapter adapter;
    ArrayList<Integer> items = new ArrayList<Integer>();
    protected static final String ACTIVITY_NAME = "MainActivity";
    Toolbar tb;
    ImageButton mainInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tb = findViewById(R.id.mainScreenToolbar);
        mainInfo = findViewById(R.id.editButton);

        //editor.setVisibility(View.INVISIBLE);


        setSupportActionBar(tb);

        Log.i(ACTIVITY_NAME,"in onCreate");

        recyclerView = (RecyclerView)findViewById(R.id.recyclerViewMain);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //TODO get items from the database here


        //hardcode for now

        items.add(0);
        items.add(1);
        items.add(2);
        items.add(-1);



        adapter = new MainAdapter(items);

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

    public void startNutritionActivity(@Nullable View view){
        Intent nextA = new Intent(MainActivity.this, NutritionActivity.class);
        startActivityForResult(nextA, 10);
    }

    public void addItem(@Nullable View view){

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        LayoutInflater inflator =MainActivity.this.getLayoutInflater();
        View customView = inflator.inflate(R.layout.edit_dialog,null);
        builder
                .setCancelable(true)
                .setView(customView)
                .show();
    }
    public void addProfile(@Nullable View view){
        adapter.addNewItem(0);
        adapter.notifyDataSetChanged();

    }
    public void addNutrition(@Nullable View view){
        adapter.addNewItem(1);
        adapter.notifyDataSetChanged();

    }
    public void addFitness(@Nullable View view){
        adapter.addNewItem(2);
        adapter.notifyDataSetChanged();

    }






    public void removeItem(@Nullable View view){
        adapter.removeLastItem();
        adapter.notifyDataSetChanged();
    }

    public void showProfileInfo(@Nullable View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        String dText = getResources().getString(R.string.profileInfoText);
        CharSequence dialog_title = getResources().getString(R.string.profileInfoTitle);
        final TextView dialogText = new TextView(this);
        dialogText.setText(dText);
        builder
                .setCancelable(true)
                .setView(dialogText)
                .setTitle(dialog_title)
                .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss(); //dismisses the alertdialog
                    }
                })
                .show();
    }

    public void showNutritionInfo(@Nullable View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        String dText = getResources().getString(R.string.nutritionInfoText);
        CharSequence dialog_title = getResources().getString(R.string.nutritionInfoTitle);
        final TextView dialogText = new TextView(this);
        dialogText.setText(dText);
        builder
                .setCancelable(true)
                .setView(dialogText)
                .setTitle(dialog_title)
                .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss(); //dismisses the alertdialog
                    }
                })
                .show();
    }

    public void showFitnessInfo(@Nullable View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        String dText = getResources().getString(R.string.fitnessInfoText);
        CharSequence dialog_title = getResources().getString(R.string.fitnessInfoTitle);
        final TextView dialogText = new TextView(this);
        dialogText.setText(dText);
        builder
                .setCancelable(true)
                .setView(dialogText)
                .setTitle(dialog_title)
                .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss(); //dismisses the alertdialog
                    }
                })
                .show();
    }

    public void showMainInfo(@Nullable View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        String dText = getResources().getString(R.string.mainInfoText);
        CharSequence dialog_title = getResources().getString(R.string.mainInfoTitle);
        final TextView dialogText = new TextView(this);
        dialogText.setText(dText);
        builder
                .setCancelable(true)
                .setView(dialogText)
                .setTitle(dialog_title)
                .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss(); //dismisses the alertdialog
                    }
                })
                .show();
    }


    //toolbar stuff

}
