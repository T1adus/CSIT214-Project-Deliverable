package com.example.flytdream;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

public class CoreActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private BookingSession bookingSession;
    private FlightAdapter flightAdapter;
    private List<Flight> flights;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_core);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(navigationMenuListener);

        bookingSession = new BookingSession();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new HomeFragment())
                .commit();
    }

    public BookingSession getBookingSession() {
        return bookingSession;
    }

    public void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }

    public FlightAdapter createFlight() {
        flights = new ArrayList<>();
        flights.add(new Flight("SYD", "11:30", "08h:40m", "HAN", "20:10", 3000));
        flights.add(new Flight("SYD", "10:30", "11h:50m", "HAN", "22:20", 1000));
        flights.add(new Flight("SYD", "15:30", "12h:20m", "HAN", "03:50", 2000));
        flights.add(new Flight("SYD", "18:30", "09h:10m", "HAN", "03:40", 4000));

        flightAdapter = new FlightAdapter(flights);
        return flightAdapter;
    }

    public void selectFlight(int position) {
        bookingSession.setFlight(flights.get(position));
    }

    NavigationBarView.OnItemSelectedListener navigationMenuListener = new NavigationBarView.OnItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            if (item.getItemId() == R.id.menu_home) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new HomeFragment())
                        .commit();
                return true;
            } else if (item.getItemId() == R.id.menu_trip) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new TripFragment())
                        .commit();
                return true;
            } else if (item.getItemId() == R.id.menu_explore) {
                return true;
            } else if (item.getItemId() == R.id.menu_profile) {
                return true;
            }
            return false;
        }
    };
}