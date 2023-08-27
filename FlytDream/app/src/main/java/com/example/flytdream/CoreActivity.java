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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_core);


        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(navigationMenuListener);
    }

    NavigationBarView.OnItemSelectedListener navigationMenuListener = new NavigationBarView.OnItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            if (item.getItemId() == R.id.menu_home) {
                return true;
            } else if (item.getItemId() == R.id.menu_trip) {
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