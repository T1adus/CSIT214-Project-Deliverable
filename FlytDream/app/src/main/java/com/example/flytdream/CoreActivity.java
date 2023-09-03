package com.example.flytdream;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class CoreActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;

    long departureCityId;
    String departureCityAlias;
    long arrivalCityId;
    String arrivalCityAlias;
    String date;
    long classId;
    String classSelected;
    int adult;
    int child;
    int infant;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_core);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        departureCityId = 0;
        departureCityAlias = "";
        arrivalCityId = 0;
        arrivalCityAlias = "";
        date = "";
        classId = 0;
        classSelected = "";
        adult = 0;
        child = 0;
        infant = 0;

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(navigationMenuListener);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new HomeFragment())
                .commit();
    }

    public long getDepartureCityId() {
        return departureCityId;
    }

    public void setDepartureCity(long newDepartureCityId) {
        departureCityId = newDepartureCityId;
    }

    public long getArrivalCityId() {
        return arrivalCityId;
    }

    public void setArrivalCityId(long newArrivalCityId) {
        arrivalCityId = newArrivalCityId;
    }

    public void setDepartureCityAlias(String newDepartureCityAlias) {
        departureCityAlias = newDepartureCityAlias;
    }

    public void setArrivalCityAlias(String newArrivalCityAlias) {
        arrivalCityAlias = newArrivalCityAlias;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String newDate) {
        date = newDate;
    }

    public void setClassId(long newClassId) {
        classId = newClassId;
    }

    public long getClassId() {
        return classId;
    }

    public void setClass(String newClass) {
        classSelected = newClass;
    }

    public int[] getPassenger() {
        int[] passenger = {adult, child, infant};
        return passenger;
    }

    public void setPassenger(int newAdult, int newChild, int newInfant) {
        adult = newAdult;
        child = newChild;
        infant = newInfant;
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