package com.example.flytdream;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class SignInActivity extends AppCompatActivity {

    ImageButton signInButton;
    ImageButton createAccountButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        signInButton = findViewById(R.id.signInButton);
        signInButton.setOnClickListener(buttonClickListener);
        createAccountButton = findViewById(R.id.createAccountButton);
        createAccountButton.setOnClickListener(buttonClickListener);
    }

    View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.signInButton) {
                Intent toHome = new Intent(SignInActivity.this, CoreActivity.class);
                startActivity(toHome);
            } else if (v.getId() == R.id.createAccountButton) {
                Intent toCreateAccount = new Intent(SignInActivity.this, RegisterActivity.class);
                startActivity(toCreateAccount);
            }
        }
    };
}