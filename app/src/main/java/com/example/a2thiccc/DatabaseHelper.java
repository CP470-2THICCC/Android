package com.example.a2thiccc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * DatabaseHelper class which extends SQLiteOpenHelper
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "users.db";
    private static final String TABLE_NAME = "users";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_UNAME = "uname";
    private static final String COLUMN_PASS = "pass";
    private static final String TABLE_CREATE = "CREATE TABLE users (id integer primary key not null, " +
            "name text not null, uname text not null, pass text not null)";
    SQLiteDatabase db;

    /**
     * Constructor for DatabaseHelper
     * @param context, not null
     */
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Called when DB doesn't exist and creates tables
     * @param db, not null
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.db = db;
    }

    /**
     * Inserts given user into the DB
     * @param u, not null
     */
    public void insertUser(User u){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String query = "select * from users";
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();

        values.put(COLUMN_ID, count);
        values.put(COLUMN_NAME , u.getName());
        values.put(COLUMN_UNAME, u.getuName());
        values.put(COLUMN_PASS, u.getPass());

        db.insert(TABLE_NAME, null, values);
        cursor.close();
        db.close();


    }

    /**
     * Searches for the password that corresponds to the username given
     * @param uname, not null
     * @return b
     */
    public String searchPass(String uname){
        db = this.getReadableDatabase();
        //gets the username and pass columns from the table
        String query = "select uname, pass from " +TABLE_NAME;
        //cursor for the query
        Cursor cursor = db.rawQuery(query, null);
        String a,b;
        //in case loop goes through entire string
        b = "not found";
        //linear search through the columns
        if(cursor.moveToFirst()){
            //just iterates through the rows
            while(cursor.moveToNext()) {
                //checks first row being the username
                a = cursor.getString(0);

                //if matches then get password
                if(a.equals(uname)) {
                    b = cursor.getString(1);
                    break;
                }
            }

        }
        db.close();
        cursor.close();
        return b;
    }
    /************* Used to try to retrieve name from database, kept getting null reference in ProfileActivity.java
    public String searchName(String name){
        String query = "select " + COLUMN_NAME + " from " + TABLE_NAME + " where "+ "COLUMN_UNAME = name";
        Cursor cursor = db.rawQuery(query, null);
        String pname = cursor.getString(1);
        return pname;
    }
    *************/
    /**
     * Checks if the table exists
     * @param tName, not null
     * @return true or false
     */
    public boolean tableExists(String tName){
        db = getReadableDatabase();
        String query = "select DISTINCT tbl_name from sqlite_master where tbl_name = '" + tName +"'";
        try (Cursor cursor = db.rawQuery(query, null)){
            if(cursor!=null){
                if(cursor.getCount()>0){
                    cursor.close();
                    db.close();
                    return true;
                }
            }
            cursor.close();
            db.close();
            return false;
        }
    }

    /**
     * Called when version number increases
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " +TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }
}
