package com.example.a2thiccc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.ToggleButton;

//TODO: everything lol
public class ProfileActivity extends AppCompatActivity implements body_metrics.OnListFragmentInteractionListener {
    Fragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        final ToggleButton bTog = (ToggleButton) findViewById(R.id.toggleButton3);
        bTog.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v){
                if (bTog.isChecked()){

                    FragmentManager fm = getSupportFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    fragment = fm.findFragmentById(R.id.body_metrics);
                    ft.show(fragment);
                    ft.commit();
                }
                else{
                    FragmentManager fm = getSupportFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    fragment = fm.findFragmentById(R.id.body_metrics);
                    ft.hide(fragment);

                    ft.commit();
                }
            };
        });

    }

    public void onStart(){
        super.onStart();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        fragment = fm.findFragmentById(R.id.body_metrics);
        ft.hide(fragment);

        ft.commit();

    };




}
