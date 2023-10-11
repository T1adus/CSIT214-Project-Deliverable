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

    public static final String FLIGHT_INFO_TABLE = "FLIGHT_INFO";
    public static final String FLIGHT_INFO_TABLE_COL_1 = "FLIGHT_CODE";
    public static final String FLIGHT_INFO_TABLE_COL_2 = "DEPART_CITY";
    public static final String FLIGHT_INFO_TABLE_COL_3 = "DEPART_TIME";
    public static final String FLIGHT_INFO_TABLE_COL_4 = "FLIGHT_TIME";
    public static final String FLIGHT_INFO_TABLE_COL_5 = "ARRIVE_CITY";
    public static final String FLIGHT_INFO_TABLE_COL_6 = "ARRIVE_TIME";
    public static final String FLIGHT_INFO_TABLE_COL_7 = "FLIGHT_PASSENGER";
    public static final String FLIGHT_INFO_TABLE_COL_8 = "FLIGHT_TYPE";
    public static final String FLIGHT_INFO_TABLE_COL_9 = "FLIGHT_CLASS";
    public static final String FLIGHT_INFO_TABLE_COL_10 = "FLIGHT_SEAT";
    public static final String FLIGHT_INFO_TABLE_COL_11 = "BOARDING_TIME";
    public static final String FLIGHT_INFO_TABLE_COL_12 = "FLIGHT_PRICE";
    public static final String FLIGHT_INFO_TABLE_COL_13 = "DEPART_DATE";

    //Constructor
    public databaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //Method onCreate() create the databases if they don't exist
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + USER_INFO_TABLE + "(USER_NAME TEXT, USER_EMAIL TEXT PRIMARY KEY, USER_PASSWORD TEXT, USER_PHONE TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + CITY_INFO_TABLE + "(CITY_ALIAS TEXT PRIMARY KEY, CITY_NAME TEXT, CITY_COUNTRY TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + FLIGHT_INFO_TABLE + "(FLIGHT_CODE TEXT PRIMARY KEY, DEPART_CITY TEXT, DEPART_TIME TEXT, FLIGHT_TIME TEXT, ARRIVE_CITY TEXT, ARRIVE_TIME TEXT, FLIGHT_PASSENGER TEXT, FLIGHT_TYPE TEXT, FLIGHT_CLASS TEXT, FLIGHT_SEAT TEXT, BOARDING_TIME TEXT, FLIGHT_PRICE TEXT, DEPART_DATE TEXT)");
    }

    //Method onUpgrade() upgrade the database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USER_INFO_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + CITY_INFO_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + FLIGHT_INFO_TABLE);
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

    public void insertFlight(String flightCode, String departCity, String departTime, String flightTime, String arriveCity, String arriveTime, String passenger, String type, String flightClass, String seats, String board, String price, String departDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FLIGHT_INFO_TABLE_COL_1, flightCode);
        contentValues.put(FLIGHT_INFO_TABLE_COL_2, departCity);
        contentValues.put(FLIGHT_INFO_TABLE_COL_3, departTime);
        contentValues.put(FLIGHT_INFO_TABLE_COL_4, flightTime);
        contentValues.put(FLIGHT_INFO_TABLE_COL_5, arriveCity);
        contentValues.put(FLIGHT_INFO_TABLE_COL_6, arriveTime);
        contentValues.put(FLIGHT_INFO_TABLE_COL_7, passenger);
        contentValues.put(FLIGHT_INFO_TABLE_COL_8, type);
        contentValues.put(FLIGHT_INFO_TABLE_COL_9, flightClass);
        contentValues.put(FLIGHT_INFO_TABLE_COL_10, seats);
        contentValues.put(FLIGHT_INFO_TABLE_COL_11, board);
        contentValues.put(FLIGHT_INFO_TABLE_COL_12, price);
        contentValues.put(FLIGHT_INFO_TABLE_COL_13, departDate);

        db.insert(FLIGHT_INFO_TABLE, null, contentValues);
    }

    public Cursor getFlight(String flightCode) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + FLIGHT_INFO_TABLE + " WHERE FLIGHT_CODE=?", new String[]{flightCode});
        return res;
    }
}
