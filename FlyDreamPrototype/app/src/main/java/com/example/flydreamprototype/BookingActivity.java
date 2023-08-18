package com.example.flydreamprototype;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class BookingActivity extends AppCompatActivity {
    Spinner departureCountry;
    Spinner arrivalCountry;
    Spinner date;
    Spinner month;
    Spinner year;
    Button bookingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Booking");

        departureCountry = findViewById(R.id.departureCountry);
        arrivalCountry = findViewById(R.id.arrivalCountry);
        date = findViewById(R.id.date);
        month = findViewById(R.id.month);
        year = findViewById(R.id.year);

        bookingButton = findViewById(R.id.bookingButton);
        bookingButton.setOnClickListener(buttonClickListener);

        inflateSpinner();
    }
    View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.bookingButton) {
                Intent intent = new Intent(BookingActivity.this, FlightSelectionActivity.class);
                startActivity(intent);
            }
        }
    };

    public void inflateSpinner() {
        String[] departureCountryList = new String[] {"Sydney", "Melbourne"};
        String[] arrivalCountryList = new String[] {"Hanoi", "HCM"};
        String[] dateList = new String[31];
        for (int i = 1; i <= 31; i++) {
            dateList[i - 1] = String.format("%02d", i);
        }
        String[] monthList = new String[12];
        for (int month = 1; month <= 12; month++) {
            String formattedMonth = String.format("%02d", month);
            monthList[month - 1] = formattedMonth;
        }
        String[] yearList = new String[]{"2023", "2024"};

        ArrayAdapter<String> departureAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, departureCountryList);
        ArrayAdapter<String> arrivalAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, arrivalCountryList);
        ArrayAdapter<String> dateAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, dateList);
        ArrayAdapter<String> monthAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, monthList);
        ArrayAdapter<String> yearAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, yearList);

        departureCountry.setAdapter(departureAdapter);
        arrivalCountry.setAdapter(arrivalAdapter);
        date.setAdapter(dateAdapter);
        month.setAdapter(monthAdapter);
        year.setAdapter(yearAdapter);
    }
}