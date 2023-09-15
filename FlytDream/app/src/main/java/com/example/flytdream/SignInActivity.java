package com.example.flytdream;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class SignInActivity extends AppCompatActivity {
    //Fields
    private databaseHelper myDB;
    private EditText emailInput;
    private EditText passwordInput;
    private ImageButton signInButton;
    private ImageButton createAccountButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        myDB = new databaseHelper(SignInActivity.this, "FlytDream.db", null, 1);

        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);

        signInButton = findViewById(R.id.signInButton);
        signInButton.setOnClickListener(buttonClickListener);
        createAccountButton = findViewById(R.id.createAccountButton);
        createAccountButton.setOnClickListener(buttonClickListener);
    }

    View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.signInButton) {
                if (validateData() == true) {
                    Intent toHome = new Intent(SignInActivity.this, CoreActivity.class);
                    startActivity(toHome);
                }
            } else if (v.getId() == R.id.createAccountButton) {
                Intent toCreateAccount = new Intent(SignInActivity.this, RegisterActivity.class);
                startActivity(toCreateAccount);
            }
        }
    };

    //Function for data validation
    public boolean validateData() {
        String email = emailInput.getText().toString();
        String password = passwordInput.getText().toString();
        if (!email.equals("")) {
            if (!password.equals("")) {

            } else {
                Toast.makeText(this, "Please Input Password!", Toast.LENGTH_SHORT).show();
                return false;
            }
        } else {
            Toast.makeText(this, "Please Input Email!", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (myDB.checkUserExistence(email) == true) {
            if (myDB.checkPasswordMatch(email, password) == true) {
                return true;
            } else if (myDB.checkPasswordMatch(email, password) == false) {
                Toast.makeText(this, "Password Does Not Match!", Toast.LENGTH_SHORT).show();
                return false;
            }
        } else {
            Toast.makeText(this, "User Does Not Exist!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return false;
    }
}