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
    String flightType,flightCode,boardingTime,gate,terminal,departCityAlias,departCityName,departTime,flightTime,arriveCityAlias,arriveCityName,arriveTime,passengers,seats;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirm);

        viewTicket = findViewById(R.id.view_ticket_button);
        viewTicket.setOnClickListener(clickListener);

        Intent intent = getIntent();
        passengers = intent.getStringExtra("passenger name");
        flightType = intent.getStringExtra("flight type");
        flightCode = intent.getStringExtra("flight code");
        boardingTime = intent.getStringExtra("boarding time");
        gate = intent.getStringExtra("gate");
        terminal = intent.getStringExtra("terminal");
        seats = intent.getStringExtra("seat number");
        departCityAlias = intent.getStringExtra("depart city alias");
        departCityName = intent.getStringExtra("depart city name");
        departTime = intent.getStringExtra("depart time");
        flightTime = intent.getStringExtra("flight time");
        arriveCityAlias = intent.getStringExtra("arrival city alias");
        arriveCityName = intent.getStringExtra("arrival city name");
        arriveTime = intent.getStringExtra("arrive time");
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(OrderConfirmActivity.this,DownloadInvoiceActivity.class);
            intent.putExtra("passenger name",passengers);
            intent.putExtra("flight type",flightType);
            intent.putExtra("flight code",flightCode);
            intent.putExtra("boarding time",boardingTime);
            intent.putExtra("gate",gate);
            intent.putExtra("terminal",terminal);
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