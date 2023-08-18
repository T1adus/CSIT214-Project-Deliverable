package com.example.flydreamprototype;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FlightSelectionActivity extends AppCompatActivity {

    TextView flight1;
    TextView flight2;
    TextView flight3;
    Button confirmButton;
    LayerDrawable notSelected;
    LayerDrawable selected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_selection);

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Flight Selection");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        flight1 = findViewById(R.id.flight1TextView);
        flight2 = findViewById(R.id.flight2TextView);
        flight3 = findViewById(R.id.flight3TextView);
        confirmButton = findViewById(R.id.confirmFlightButton);
        flight1.setOnClickListener(clickListener);
        flight2.setOnClickListener(clickListener);
        flight3.setOnClickListener(clickListener);
        confirmButton.setOnClickListener(clickListener);

        GradientDrawable border = new GradientDrawable();
        GradientDrawable notSelectedBackground = new GradientDrawable();
        GradientDrawable selectedBackground = new GradientDrawable();
        border.setStroke(2, Color.BLUE);
        notSelectedBackground.setColor(Color.WHITE);
        selectedBackground.setColor(Color.CYAN);

        Drawable[] layer1 = {notSelectedBackground, border};
        notSelected = new LayerDrawable(layer1);
        Drawable[] layer2 = {selectedBackground, border};
        selected = new LayerDrawable(layer2);

        flight1.setBackground(notSelected);
        flight2.setBackground(notSelected);
        flight3.setBackground(notSelected);

        inflateFlight();
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.flight1TextView) {
                setUserChoice(flight1.getText().toString());
            } else if (v.getId() == R.id.flight2TextView) {
                setUserChoice(flight2.getText().toString());
            } else if (v.getId() == R.id.flight3TextView) {
                setUserChoice(flight3.getText().toString());
            } else if (v.getId() == R.id.confirmFlightButton) {
                Intent intent = new Intent(FlightSelectionActivity.this, SeatSelectionActivity.class);
                startActivity(intent);
            }
        }
    };

    public void setUserChoice(String userChoice) {
        if (userChoice.equals(flight1.getText().toString())) {
            flight1.setBackground(selected);
            flight2.setBackground(notSelected);
            flight3.setBackground(notSelected);
        } else if (userChoice.equals(flight2.getText().toString())) {
            flight1.setBackground(notSelected);
            flight2.setBackground(selected);
            flight3.setBackground(notSelected);
        } else if (userChoice.equals(flight3.getText().toString())) {
            flight1.setBackground(notSelected);
            flight2.setBackground(notSelected);
            flight3.setBackground(selected);
        }
    }

    public void inflateFlight() {
        String flight1Text = String.format("Flight 1:\n11:30 - 20:30\nNon Stop");
        String flight2Text = String.format("Flight 2:\n15:30 - 03:30\nTransition");
        String flight3Text = String.format("Flight 3:\n18:30 - 06:30\nTransition");

        flight1.setText(flight1Text);
        flight2.setText(flight2Text);
        flight3.setText(flight3Text);
    }
}