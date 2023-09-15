package com.example.flytdream;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class OrderConfirmActivity extends AppCompatActivity {
    ImageButton viewTicket;
    String flightType,flightClass,boardingTime,price,departCityAlias,departCityName,departTime,flightTime,arriveCityAlias,arriveCityName,arriveTime,passengers,seats;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirm);

        viewTicket = findViewById(R.id.view_ticket_button);
        viewTicket.setOnClickListener(clickListener);

        //retrieve and unbox data from OrderReview
        Intent intent = getIntent();
        passengers = intent.getStringExtra("passenger name");
        flightClass = intent.getStringExtra("flight class");
        flightType = intent.getStringExtra("flight type");
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
    }
    //button function to pass the data above for the next activity and start it
    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(OrderConfirmActivity.this,DownloadInvoiceActivity.class);
            intent.putExtra("passenger name",passengers);
            intent.putExtra("flight class",flightClass);
            intent.putExtra("flight type",flightType);
            intent.putExtra("boarding time",boardingTime);
            intent.putExtra("price",price);
            intent.putExtra("seat number",seats);
            intent.putExtra("depart city alias",departCityAlias);
            intent.putExtra("depart city name",departCityName);
            intent.putExtra("depart time",departTime);
            intent.putExtra("flight time",flightTime);
            intent.putExtra("arrival city alias",arriveCityAlias);
            intent.putExtra("arrival city name",arriveCityName);
            intent.putExtra("arrive time",arriveTime);
            startActivity(intent);
        }
    };
}