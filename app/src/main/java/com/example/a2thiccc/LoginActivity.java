package com.example.a2thiccc;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//TODO: solve why opening alert dialog skips ~70 frames and to mainactivity skips ~40 frames
public class LoginActivity extends AppCompatActivity {
    View view;
    EditText usernameEdit, passwordEdit, nameEdit, confirmPassEdit;
    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final Button loginButton = findViewById(R.id.loginButton);
        final Button createUserButton = findViewById(R.id.newUserButton);

        //login service
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Login", "Login button clicked.");

                //gets the user and pass from the login activity
                EditText a = findViewById(R.id.UserNameEditText);
                EditText b = findViewById(R.id.PasswordEditText);

                String user = a.getText().toString();
                String pass = b.getText().toString();

                if (helper.tableExists("users")){
                    String pass2 = helper.searchPass(user);

                    if(user.equals("")  || pass.equals("")){
                        Toast temp = Toast.makeText(LoginActivity.this, "Username or password are empty", Toast.LENGTH_SHORT);
                        temp.show();
                    }
                    else{
                        if(pass2.equals(pass)){
                            Intent nextA = new Intent(LoginActivity.this, MainActivity.class);
                            nextA.putExtra("Username", user);
                            startActivity(nextA);
                        }
                        else{
                            Toast temp = Toast.makeText(LoginActivity.this, "Username and password don't match", Toast.LENGTH_SHORT);
                            temp.show();
                        }
                    }
                }
                else{
                    Toast temp = Toast.makeText(LoginActivity.this, "No users are registered. Please register.", Toast.LENGTH_SHORT);
                    temp.show();
                }


            }
        });
        //new User Service
        createUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Login", "Create account button clicked.");

                //start signup method
                signUp();
            }
        });

    }
    public void signUp(){
        //Create builder pattern for alert dialog
        AlertDialog.Builder custom = new AlertDialog.Builder(LoginActivity.this);

        //Inflater from LoginActivity
        LayoutInflater inflater = LoginActivity.this.getLayoutInflater();
        CharSequence dialog_confirm = getString(R.string.dialogSU_confirm);

        //Inflate the custom signup dialog
        view = inflater.inflate(R.layout.signup_dialog, null);

        //Set properties
        custom.setView(view)
                .setCancelable(true)
                .setPositiveButton(dialog_confirm, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //do nothing because we will be overriding it
                    }
                });
        final AlertDialog dialog = custom.create();
        dialog.show();
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Boolean closeFlag = false;

                //Get the edit texts from the inflated view
                nameEdit = view.findViewById(R.id.nameSU);
                usernameEdit = view.findViewById(R.id.uNameSU);
                passwordEdit = view.findViewById((R.id.passSU));
                confirmPassEdit = view.findViewById(R.id.confirmpassSU);

                String namestr = nameEdit.getText().toString();
                String unamestr = usernameEdit.getText().toString();
                String pwstr = passwordEdit.getText().toString();
                String confpwstr = confirmPassEdit.getText().toString();
                Toast toast;

                //if name is empty then toast message
                if (namestr == ""){
                    toast = Toast.makeText(LoginActivity.this, "Please enter your name", Toast.LENGTH_SHORT);
                    toast.show();
                }
                //if username empty
                else if (unamestr == ""){
                    toast = Toast.makeText(LoginActivity.this, "Please enter your username", Toast.LENGTH_SHORT);
                    toast.show();
                }
                //if pw empty
                else if (pwstr == ""){
                    toast = Toast.makeText(LoginActivity.this, "Please enter your password", Toast.LENGTH_SHORT);
                    toast.show();
                }
                //if confirm pw empty
                else if (confpwstr == ""){
                    toast = Toast.makeText(LoginActivity.this, "Please confirm your password", Toast.LENGTH_SHORT);
                    toast.show();
                }
                //if pw dont match
                else if (!pwstr.equals(confpwstr)){
                    toast = Toast.makeText(LoginActivity.this, "Your passwords do not match", Toast.LENGTH_SHORT);
                    toast.show();
                }
                //insert details in database
                else {
                    //creates the new user
                    User u = new User();
                    u.setName(namestr);
                    u.setuName(unamestr);
                    u.setPass(pwstr);

                    //inserts into the database
                    helper.insertUser(u);
                    closeFlag = true;
                }

                if(closeFlag){
                    dialog.dismiss();
                }

            }
        });
    }
}
