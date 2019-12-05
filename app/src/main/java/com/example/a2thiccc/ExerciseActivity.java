package com.example.a2thiccc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.fragment.app.Fragment;


public class ExerciseActivity extends AppCompatActivity {
    PushUps fragment1;
    SquatThrusts fragment2;
    Squats fragment3;
    LegRaises fragment4;
    Crunches fragment5;
    MountainClimbers fragment6;
    Plank fragment7;
    HighKnees fragment8;

    String[] listitem;
    ListView simpleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);
        simpleList = (ListView)findViewById(R.id.listView);
        listitem = getResources().getStringArray(R.array.exercises);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,R.layout.activity_exercises, R.id.textView, listitem);
        simpleList.setAdapter(arrayAdapter);

        fragment1 = new PushUps();
        fragment2 = new SquatThrusts();
        fragment3 = new Squats();
        fragment4 = new LegRaises();
        fragment5 = new Crunches();
        fragment6 = new MountainClimbers();
        fragment7 = new Plank();
        fragment8 = new HighKnees();

        /**
         * This Listener takes all the different fragments available and prints out a description of
         * the exercise in a FrameLayout
         *
         * @param adapterView AdapterView parameter
         * @param view View parameter
         * @param i Int parameter of the position of the element
         */

        simpleList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                    case 3:
                        setFragment(fragment4);
                        break;
                    case 4:
                        setFragment(fragment5);
                        break;
                    case 5:
                        setFragment(fragment6);
                        break;
                    case 6:
                        setFragment(fragment7);
                        break;
                    case 7:
                        setFragment(fragment8);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    /**
     * Void function that processes a fragment transaction using the FragmentManager, commits the
     * fragment selected from the listView above
     *
     * @param fragment
     */
    public void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.exerciseDetails, fragment);
        fragmentTransaction.commit();
    }
}