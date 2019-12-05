package com.example.a2thiccc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

//TODO: everything lol
public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final Button loginButton = findViewById(R.id.loginB);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextA = new Intent(LoginActivity.this, MainActivity.class);
                startActivityForResult(nextA, 10);
            }
        });
    }
}
