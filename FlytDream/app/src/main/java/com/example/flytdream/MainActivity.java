package com.example.flytdream;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    databaseHelper myDB;
    ImageButton getStartButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDB = new databaseHelper(MainActivity.this, "FlytDream.db", null, 1);
        loadCities();

        getStartButton = findViewById(R.id.getStartButton);
        getStartButton.setOnClickListener(buttonClickListener);
    }

    private void loadCities() {
        if (!myDB.tableHasContent(databaseHelper.CITY_INFO_TABLE)) {
            myDB.insertCity("AMS","Amsterdam","Netherlands");
            myDB.insertCity("HAN","Hanoi","Vietnam");
            myDB.insertCity("HCM","Ho Chi Minh City","Vietnam");
            myDB.insertCity("LDN","London","United Kingdom");
            myDB.insertCity("MEL","Melbourne","Australia");
            myDB.insertCity("PAR","Paris","France");
            myDB.insertCity("BEI","Beijing","China");
            myDB.insertCity("PER","Perth","Australia");
            myDB.insertCity("SYD","Sydney","Australia");
            myDB.insertCity("TKY","Tokyo","Japan");
        }
    }

    View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.getStartButton) {
                Intent intent = new Intent(MainActivity.this, SignInActivity.class);
                startActivity(intent);
            }
        }
    };
}