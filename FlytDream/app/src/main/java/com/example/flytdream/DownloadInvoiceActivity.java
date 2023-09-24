package com.example.flytdream;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DownloadInvoiceActivity extends AppCompatActivity {
    ImageButton download,back;
    String flightType,flightClass,boardingTime,departCityAlias,departCityName,departTime,flightTime,arriveCityAlias,arriveCityName,arriveTime,passengers,seats,price,departDate;
    TextView flightType1,flightClass1,boardingTime1,departCityAlias1,departTime1,flightTime1,arriveCityAlias1,arriveTime1,passengers1,seats1,bookingDate,price1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_invoice);

        download = findViewById(R.id.download_button);
        download.setOnClickListener(clickListener);
        back = findViewById(R.id.back_to_home);
        back.setOnClickListener(clickListener);

        //get booking details from previous screen
        Intent intent = getIntent();
        passengers = intent.getStringExtra("passenger name");
        flightType = intent.getStringExtra("flight type");
        flightClass = intent.getStringExtra("flight class");
        boardingTime = intent.getStringExtra("boarding time");
        price = intent.getStringExtra("price");
        seats = intent.getStringExtra("seat number");
        departCityAlias = intent.getStringExtra("depart city alias");
        departCityName = intent.getStringExtra("depart city name");
        departTime = intent.getStringExtra("depart time");
        flightTime = intent.getStringExtra("flight time");
        arriveCityAlias = intent.getStringExtra("arrival city alias");
        arriveCityName = intent.getStringExtra("arrival city name");
        arriveTime = intent.getStringExtra("arrive time");
        departDate = intent.getStringExtra("departDate");

        departCityAlias1 = findViewById(R.id.depart_alias);
        departTime1 = findViewById(R.id.depart_time);
        flightTime1 = findViewById(R.id.flight_time);
        arriveCityAlias1 = findViewById(R.id.arrive_alias);
        arriveTime1 = findViewById(R.id.arrive_time);
        passengers1 = findViewById(R.id.names);
        flightClass1 = findViewById(R.id.flightClass);
        flightType1 = findViewById(R.id.type);
        seats1 = findViewById(R.id.seat);
        boardingTime1 = findViewById(R.id.board_time);
        price1 = findViewById(R.id.price);

        //display booking details on screen load
        departCityAlias1.setText(departCityAlias);
        departTime1.setText(departTime);
        flightTime1.setText(flightTime);
        arriveCityAlias1.setText(arriveCityAlias);
        arriveTime1.setText(arriveTime);
        passengers1.setText(passengers);
        flightType1.setText(flightType);
        flightClass1.setText(flightClass);
        seats1.setText(seats);
        boardingTime1.setText(boardingTime);
        price1.setText(price);

        //display current booking date
        bookingDate = findViewById(R.id.booking_date);

        // Define the input date format
        SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

        // Define the output date format
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault());

        String formatDate = "";
        try {
            // Parse the input date string into a Date object
            Date date = inputFormat.parse(departDate);

            // Format the Date object into the desired output format
            formatDate = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        bookingDate.setText(formatDate);
    }

    //button functions
    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(view.getId() == R.id.download_button) {
                Toast.makeText(DownloadInvoiceActivity.this, "Invoice downloaded", Toast.LENGTH_LONG).show();
            } else if(view.getId() == R.id.back_to_home){
                Intent intent = new Intent(DownloadInvoiceActivity.this,CoreActivity.class);
                startActivity(intent);
            }
        }
    };
}