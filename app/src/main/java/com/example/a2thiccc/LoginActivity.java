package com.example.a2thiccc;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import java.lang.ref.WeakReference;

//TODO: Help Toolbar
public class LoginActivity extends AppCompatActivity {
    View view, view2;
    EditText usernameEdit, passwordEdit, nameEdit, confirmPassEdit,a,b;
    DatabaseHelper helper = new DatabaseHelper(this);
    String user,pass;
    ProgressBar loginBar;
    String res = "";
    String namestr,unamestr,pwstr,confpwstr;
    String newUser;
    Toolbar loginTB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final Button loginButton = findViewById(R.id.loginButton);
        final Button createUserButton = findViewById(R.id.newUserButton);
        loginBar = findViewById(R.id.loginBar);
        loginBar.setVisibility(View.VISIBLE);
        a = findViewById(R.id.UserNameEditText);
        b = findViewById(R.id.PasswordEditText);
        loginTB = findViewById(R.id.loginTB);
        setSupportActionBar(loginTB);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        loginTB.setLogo(R.drawable.logo2);
        //gets MYPREFS
        SharedPreferences pref = getSharedPreferences("LoginPREFS", MODE_PRIVATE);
        //gets the string with key email else default
        user = pref.getString("user", "username");
        //sets retrieved value to email edittext view
        if(user.equals("username")){
            //if no previously entered username in sharedpref
            a.setHint(user);
        }
        else{
            a.setText(user);
        }
        //login service
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Login", "Login button clicked.");
                //gets the user and pass from the login activity
                user = a.getText().toString();
                pass = b.getText().toString();
                //get the current email entered into email editview
                newUser = a.getText().toString();
                validateLogin v = new validateLogin(LoginActivity.this);
                v.execute();


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

    private class validateLogin extends AsyncTask<String, Integer, String> {

        private WeakReference<LoginActivity> loginActivity;
        public validateLogin(LoginActivity loginActivity){
            this.loginActivity = new WeakReference<>(loginActivity);
        }
        @Override
        protected String doInBackground(String... strings) {

            publishProgress(50);
            if (helper.tableExists("users")) {
                String pass2 = helper.searchPass(user);
                publishProgress(75);

                if (user.equals("") || pass.equals("")) {
                    publishProgress(100);
                    return "1";
            } else {
                if (pass2.equals(pass)) {
                    publishProgress(100);
                    return "2";
                } else {
                    publishProgress(100);
                    return "3";
                }
            }
        } else {
            publishProgress(100);
            return "0";
        }
        }
        @Override
        protected void onPostExecute(String a) {
                loginBar.setVisibility(View.INVISIBLE);
                res = a;
                if (loginActivity.get() != null){
                    //processes what to do after running validateLogin AsyncTask
                    if (res.equals("1")) {
                        Toast temp = Toast.makeText(LoginActivity.this, "Username or password are empty", Toast.LENGTH_SHORT);
                        temp.show();
                    }
                    else if (res.equals("2")) {
                        //gets MYPREFS
                        SharedPreferences pref = getSharedPreferences("LoginPREFS", MODE_PRIVATE);

                        //editor
                        SharedPreferences.Editor editor = pref.edit();

                        //puts in user email into user key in sharedprefences
                        editor.putString("user", newUser);
                        editor.commit();

                        Intent nextA = new Intent(LoginActivity.this, MainActivity.class);
                        nextA.putExtra("Username", user);
                        Toast temp = Toast.makeText(LoginActivity.this, "Login successful!", Toast.LENGTH_SHORT);
                        temp.show();
                        startActivity(nextA);
                    }
                    else if (res.equals("3")) {
                        Toast temp = Toast.makeText(LoginActivity.this, "Username and password don't match", Toast.LENGTH_SHORT);
                        temp.show();
                    }
                    else if (res.equals("0")) {
                        Toast temp = Toast.makeText(LoginActivity.this, "No users are registered. Please register.", Toast.LENGTH_SHORT);
                        temp.show();
                    }
                }
        }
        @Override
        protected void onProgressUpdate(Integer... values) {
                loginBar.setProgress(values[0]);
        }
    }

    private class register extends AsyncTask<String, Integer, String> {
        @Override
        protected String doInBackground(String... strings) {
            //creates the new user
            User u = new User();
            u.setName(namestr);
            u.setuName(unamestr);
            u.setPass(pwstr);
            //inserts into the database
            helper.insertUser(u);
            return "";
        }
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
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                dialog.dismiss();
                            }
                        }
                );
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

                namestr = nameEdit.getText().toString();
                unamestr = usernameEdit.getText().toString();
                pwstr = passwordEdit.getText().toString();
                confpwstr = confirmPassEdit.getText().toString();
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
                    register r = new register();
                    r.execute();
                    closeFlag = true;
                    toast = Toast.makeText(LoginActivity.this, "Your account has been created! Please log in.", Toast.LENGTH_SHORT);
                    toast.show();
                }
                if(closeFlag){
                    dialog.dismiss();
                }
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu m){
        getMenuInflater().inflate(R.menu.toolbar_menu, m);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem mi) {
        int id = mi.getItemId();
        switch (id) {
            case R.id.action_1:
                Log.d("Toolbar", "Question mark selected.");
                //Create builder pattern for alert dialog
                AlertDialog.Builder custom2 = new AlertDialog.Builder(LoginActivity.this);

                //Inflater from LoginActivity
                LayoutInflater inflater2 = LoginActivity.this.getLayoutInflater();

                //Inflate the custom signup dialog
                view2 = inflater2.inflate(R.layout.help_dialog, null);

                //Set properties
                custom2.setView(view2)
                        .setCancelable(true)
                        .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss(); //dismisses the alertdialog
                            }
                        });
                final AlertDialog d2 = custom2.create();
                d2.show();
//                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
//                String dText = "1.Create an account before logging in.\n" +
//                        "2. Enter correct credentials to log in\n";
//                CharSequence dialog_title = "Help";
//                final TextView dialogText = new TextView(this);
//                dialogText.setText(dText);
//                builder
//                        .setCancelable(true)
//                        .setView(dialogText)
//                        .setTitle(dialog_title)
//                        .setNegativeButton("OK", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                dialogInterface.dismiss(); //dismisses the alertdialog
//                            }
//                        })
//                        .show();
                break;
        }
        return true;
    }

}
