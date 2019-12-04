package com.example.a2thiccc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ToggleButton;

import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

//TODO: everything lol
public class ProfileActivity extends AppCompatActivity implements body_metrics.OnListFragmentInteractionListener, timer.OnFragmentInteractionListener, goals.OnFragmentInteractionListener {
    //Fragment variables
    Fragment bodyFragment;
    Fragment timerFragment;
    Fragment goalsFragment;
    DatabaseHelper helper = new DatabaseHelper(this);

    //body metric variables
    EditText profileName;
    EditText bmi;
    EditText weight;
    EditText height;
    ImageButton profile;

    //timer variables
    private EditText mEditTextInput;
    private TextView mTextViewCountDown;
    private Button mButtonSet;
    private Button mButtonStartPause;
    private Button mButtonReset;
    private CountDownTimer mCountDownTimer;

    private boolean mTimerRunning;

    private long mStartTimeInMillis;
    private long mTimeLeftInMillis;
    private long mEndTime;
    //goals variables
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //====================BODY METRIC CODE====================================
        final ToggleButton bTog = (ToggleButton) findViewById(R.id.toggleButton3);
        final ToggleButton tTog = (ToggleButton) findViewById(R.id.toggleButton2);
        //final ToggleButton gTog = (ToggleButton) findViewById(R.id.toggleButton); //Goals button



        bmi = (EditText) findViewById(R.id.editText);
        weight = (EditText) findViewById(R.id.editText3);
        height = (EditText) findViewById(R.id.editText4);
        profile = (ImageButton) findViewById(R.id.imageButton);
        profileName = (EditText) findViewById(R.id.UserNameEditText);

        bmi.setEnabled(false);
        bmi.setText("Enter above info for BMI");
        /************* Tried to retrieve name from database but kept getting null reference for String user. Defaults value to "username"

        SharedPreferences pref = getSharedPreferences("LoginPREFS", MODE_PRIVATE);

        String user = pref.getString("user ", "username");
        Log.i("username is", user);
        //String pName = helper.searchName(user);

        **************/

        weight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                calcBMI();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                calcBMI();
            }
        });

        height.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                calcBMI();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                calcBMI();
            }
        });

        //onClick listener for body button
        bTog.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v){
                if (bTog.isChecked()){

                    FragmentManager fm = getSupportFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    bodyFragment = fm.findFragmentById(R.id.body_metrics);
                    ft.show(bodyFragment);
                    ft.hide(timerFragment);
                    ft.commit();
                }
                else{
                    FragmentManager fm = getSupportFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    bodyFragment = fm.findFragmentById(R.id.body_metrics);
                    ft.hide(bodyFragment);
                    if(tTog.isChecked()){
                        ft.show(timerFragment);
                    }
                    ft.commit();
                }
            };
        });

        //=======================TIMER CODE======================================
        //onClick listener for timer button
        tTog.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v){
                if (tTog.isChecked()){

                    FragmentManager fm = getSupportFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    timerFragment = fm.findFragmentById(R.id.timer);
                    ft.show(timerFragment);
                    ft.hide(bodyFragment);
                    ft.commit();
                }
                else{
                    FragmentManager fm = getSupportFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    timerFragment = fm.findFragmentById(R.id.timer);
                    ft.hide(timerFragment);
                    if(bTog.isChecked()){
                        ft.show(bodyFragment);
                    }
                    ft.commit();
                }
            };
        });

        mEditTextInput = findViewById(R.id.edit_text_input);
        mTextViewCountDown = findViewById(R.id.text_view_countdown);

        mButtonSet = findViewById(R.id.button_set);
        mButtonStartPause = findViewById(R.id.button_start_pause);
        mButtonReset = findViewById(R.id.button_reset);

        mButtonSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = mEditTextInput.getText().toString();
                if (input.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Field can't be empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                long millisInput = Long.parseLong(input) * 60000;
                if (millisInput == 0) {
                    Toast.makeText(getApplicationContext(), "Please enter a positive number", Toast.LENGTH_SHORT).show();
                    return;
                }

                setTime(millisInput);
                mEditTextInput.setText("");
            }
        });

        mButtonStartPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTimerRunning) {
                    pauseTimer();
                } else {
                    startTimer();
                }
            }
        });

        mButtonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });
        /** Tried to make a dynamic progress bar with tasklist but too difficult, work on later.
        //=======================GOALS CODE=====================================
        //onClick listener for goals button
        gTog.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v){
                if (gTog.isChecked()){

                    FragmentManager fm = getSupportFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    goalsFragment = fm.findFragmentById(R.id.goals);
                    ft.show(goalsFragment);
                    ft.commit();
                }
                else{
                    FragmentManager fm = getSupportFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    timerFragment = fm.findFragmentById(R.id.goals);
                    ft.hide(goalsFragment);

                    ft.commit();
                }

                recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

                // use this setting to improve performance if you know that changes
                // in content do not change the layout size of the RecyclerView
                recyclerView.setHasFixedSize(true);

                // use a linear layout manager
                layoutManager = new LinearLayoutManager(getApplicationContext()); //unsure about context. maybe needs to be r.id.my_recyclerview
                recyclerView.setLayoutManager(layoutManager);

                // specify an adapter (see also next example)
                mAdapter = new MyAdapter(myDataset);
                recyclerView.setAdapter(mAdapter);
            }

            };
        });
         */
    }
    //=======================BODY METRIC FUNCTIONS========================================
    private void calcBMI(){
        if (!(weight.getText().toString().matches("")) && !(height.getText().toString().matches(""))){
            int lbs = Integer.parseInt(weight.getText().toString());
            int inch = Integer.parseInt(height.getText().toString());

            double bodyMassIndex = (703 * lbs) / Math.pow(inch,2);

            bmi.setText(Double.toString(bodyMassIndex));
        }
        else{
            bmi.setText("Enter above info for BMI");
        }
    }
    //=======================TIMER FUNCTIONS========================================

    private void setTime(long milliseconds) {
        mStartTimeInMillis = milliseconds;
        resetTimer();
        closeKeyboard();
    }

    private void startTimer() {
        mEndTime = System.currentTimeMillis() + mTimeLeftInMillis;

        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                mTimerRunning = false;
                updateWatchInterface();
            }
        }.start();

        mTimerRunning = true;
        updateWatchInterface();
    }

    private void pauseTimer() {
        mCountDownTimer.cancel();
        mTimerRunning = false;
        updateWatchInterface();
    }

    private void resetTimer() {
        mTimeLeftInMillis = mStartTimeInMillis;
        updateCountDownText();
        updateWatchInterface();
    }

    private void updateCountDownText() {
        int hours = (int) (mTimeLeftInMillis / 1000) / 3600;
        int minutes = (int) ((mTimeLeftInMillis / 1000) % 3600) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

        String timeLeftFormatted;
        if (hours > 0) {
            timeLeftFormatted = String.format(Locale.getDefault(),
                    "%d:%02d:%02d", hours, minutes, seconds);
        } else {
            timeLeftFormatted = String.format(Locale.getDefault(),
                    "%02d:%02d", minutes, seconds);
        }

        mTextViewCountDown.setText(timeLeftFormatted);
    }

    private void updateWatchInterface() {
        if (mTimerRunning) {
            mEditTextInput.setVisibility(View.INVISIBLE);
            mButtonSet.setVisibility(View.INVISIBLE);
            mButtonReset.setVisibility(View.INVISIBLE);
            mButtonStartPause.setText("Pause");
        } else {
            mEditTextInput.setVisibility(View.VISIBLE);
            mButtonSet.setVisibility(View.VISIBLE);
            mButtonStartPause.setText("Start");

            if (mTimeLeftInMillis < 1000) {
                mButtonStartPause.setVisibility(View.INVISIBLE);
            } else {
                mButtonStartPause.setVisibility(View.VISIBLE);
            }

            if (mTimeLeftInMillis < mStartTimeInMillis) {
                mButtonReset.setVisibility(View.VISIBLE);
            } else {
                mButtonReset.setVisibility(View.INVISIBLE);
            }
        }
    }

    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putLong("startTimeInMillis", mStartTimeInMillis);
        editor.putLong("millisLeft", mTimeLeftInMillis);
        editor.putBoolean("timerRunning", mTimerRunning);
        editor.putLong("endTime", mEndTime);

        editor.apply();

        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
        }
    }

    //==========================ON START=========================================
    public void onStart(){
        super.onStart();


        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        bodyFragment = fm.findFragmentById(R.id.body_metrics);
        timerFragment = fm.findFragmentById(R.id.timer);
        goalsFragment = fm.findFragmentById(R.id.goals);
        ft.hide(bodyFragment);
        ft.hide(timerFragment);
        ft.hide(goalsFragment);

        ft.commit();


        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);

        mStartTimeInMillis = prefs.getLong("startTimeInMillis", 600000);
        mTimeLeftInMillis = prefs.getLong("millisLeft", mStartTimeInMillis);
        mTimerRunning = prefs.getBoolean("timerRunning", false);

        updateCountDownText();
        updateWatchInterface();

        if (mTimerRunning) {
            mEndTime = prefs.getLong("endTime", 0);
            mTimeLeftInMillis = mEndTime - System.currentTimeMillis();

            if (mTimeLeftInMillis < 0) {
                mTimeLeftInMillis = 0;
                mTimerRunning = false;
                updateCountDownText();
                updateWatchInterface();
            } else {
                startTimer();
            }


        }
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
