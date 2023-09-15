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
    public List<Flight> flights;
    public databaseHelper myDB;
    public int citySelectScreenController;
    public int currentPassenger;
    public boolean currentTripFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_core);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(navigationMenuListener);

        citySelectScreenController = -1;

        bookingSession = new BookingSession();
        myDB = new databaseHelper(CoreActivity.this, "FlytDream.db", null, 1);

        currentPassenger = 0;

        currentTripFragment = false;

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new HomeFragment())
                .commit();
    }

    public void createPassengerList() {
        int totalPassenger = bookingSession.getTotalPassenger();
        ArrayList<Passenger> passengers = new ArrayList<>();
        for (int i = 0; i < totalPassenger; i++) {
            Passenger aPassenger = new Passenger();
            passengers.add(aPassenger);
        }
        bookingSession.setPassengers(passengers);
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

    public FlightAdapter createFlight(String departAlias, String arriveAlias) {
        flights = new ArrayList<>();
        flights.add(new Flight(departAlias, "11:30", "08h:40m", arriveAlias, "20:10", 3000));
        flights.add(new Flight(departAlias, "10:30", "11h:50m", arriveAlias, "22:20", 1000));
        flights.add(new Flight(departAlias, "15:30", "12h:20m", arriveAlias, "03:50", 2000));
        flights.add(new Flight(departAlias, "18:30", "09h:10m", arriveAlias, "03:40", 4000));

        flightAdapter = new FlightAdapter(flights);
        return flightAdapter;
    }

    NavigationBarView.OnItemSelectedListener navigationMenuListener = new NavigationBarView.OnItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            if (item.getItemId() == R.id.menu_home) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new HomeFragment())
                        .commit();
                currentTripFragment = false;
                return true;
            } else if (item.getItemId() == R.id.menu_trip) {
                if (currentTripFragment == false) {
                    currentTripFragment = true;
                    bookingSession = new BookingSession();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, new TripFragment())
                            .commit();
                }
                return true;
            } else if (item.getItemId() == R.id.menu_explore) {
                currentTripFragment = false;
                return true;
            } else if (item.getItemId() == R.id.menu_profile) {
                currentTripFragment = false;
                return true;
            }
            return false;
        }
    };
}