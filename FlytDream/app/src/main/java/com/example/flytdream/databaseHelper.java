package com.example.flytdream;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class databaseHelper extends SQLiteOpenHelper {
    //Fields
    public static final String DATABASE_NAME = "FlytDream.db";

    public static final String USER_INFO_TABLE = "USER_INFO";
    public static final String USER_INFO_TABLE_COL_1 = "USER_NAME";
    public static final String USER_INFO_TABLE_COL_2 = "USER_EMAIL";
    public static final String USER_INFO_TABLE_COL_3 = "USER_PASSWORD";
    public static final String USER_INFO_TABLE_COL_4 = "USER_PHONE";

    public static final String CITY_INFO_TABLE = "CITY_INFO";
    public static final String CITY_INFO_TABLE_COL_1 = "CITY_ALIAS";
    public static final String CITY_INFO_TABLE_COL_2 = "CITY_NAME";
    public static final String CITY_INFO_TABLE_COL_3 = "CITY_COUNTRY";

    //Constructor
    public databaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //Method onCreate() create the databases if they don't exist
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + USER_INFO_TABLE + "(USER_NAME TEXT, USER_EMAIL TEXT PRIMARY KEY, USER_PASSWORD TEXT, USER_PHONE TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + CITY_INFO_TABLE + "(CITY_ALIAS TEXT PRIMARY KEY, CITY_NAME TEXT, CITY_COUNTRY TEXT)");
    }

    //Method onUpgrade() upgrade the database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USER_INFO_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + CITY_INFO_TABLE);
        onCreate(db);
    }

    //Method tableHasContent() checks if the table already has content before insert
    public boolean tableHasContent(String tableName) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + tableName, null);
        if (!res.moveToFirst()) {
            return false;
        } else {
            return true;
        }
    }

    //Method insertUser() inserts the user into the database
    public void insertUser(String name, String email, String password, String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_INFO_TABLE_COL_1, name);
        contentValues.put(USER_INFO_TABLE_COL_2, email);
        contentValues.put(USER_INFO_TABLE_COL_3, password);
        contentValues.put(USER_INFO_TABLE_COL_4, phone);

        db.insert(USER_INFO_TABLE, null, contentValues);
    }

    //Method checkUserExistence() check if the user exist in the database
    public boolean checkUserExistence(String email) {
        boolean userExist = false;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + USER_INFO_TABLE + " WHERE USER_EMAIL=?", new String[]{email});
        if (res.moveToFirst()) {
            userExist = true;
        }
        res.close();
        return userExist;
    }

    //Method checkPasswordMatch() checks if the password match
    public boolean checkPasswordMatch(String email, String inputPassword) {
        boolean passwordMatch = false;
        String password = "";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + USER_INFO_TABLE + " WHERE USER_EMAIL=?", new String[]{email});
        while (res.moveToNext()) {
            password = res.getString(2);
        }
        if (password.equals(inputPassword)) {
            passwordMatch = true;
        }
        res.close();
        return passwordMatch;
    }

    //Method to insert a city into the database
    public void insertCity(String cityAlias, String cityName, String cityCountry) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CITY_INFO_TABLE_COL_1, cityAlias);
        contentValues.put(CITY_INFO_TABLE_COL_2, cityName);
        contentValues.put(CITY_INFO_TABLE_COL_3, cityCountry);
        db.insert(CITY_INFO_TABLE, null, contentValues);
    }

    //Method to search a city based on the input of user
    public Cursor onSearchCity(String input) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + CITY_INFO_TABLE + " WHERE " + CITY_INFO_TABLE_COL_2 + " LIKE '%" + input + "%'", null);
        return res;
    }
}
