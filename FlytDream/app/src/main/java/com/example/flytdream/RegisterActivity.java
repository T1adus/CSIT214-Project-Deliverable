package com.example.flytdream;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    //Field
    private databaseHelper myDB;
    private EditText emailRegisterEdit;
    private EditText passWordRegisterEdit;
    private EditText passWordConfirmEdit;
    private EditText phoneRegisterEdit;
    private ImageButton createAccountButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        myDB = new databaseHelper(RegisterActivity.this, "FlytDream.db", null, 1);

        emailRegisterEdit = findViewById(R.id.emailRegisterEdit);
        passWordRegisterEdit = findViewById(R.id.passwordRegisterEdit);
        passWordConfirmEdit = findViewById(R.id.confirmPasswordEdit);
        phoneRegisterEdit = findViewById(R.id.phoneRegisterEdit);
        createAccountButton = findViewById(R.id.createAccountButton);
        createAccountButton.setOnClickListener(clickListener);
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.createAccountButton) {
                if (validateData() == true) {
                    Intent toSignIn = new Intent(RegisterActivity.this, SignInActivity.class);
                    startActivity(toSignIn);
                }
            }
        }
    };

    //Function for data validation
    public boolean validateData() {
        String emailRegister = emailRegisterEdit.getText().toString();
        String passWordRegister = passWordRegisterEdit.getText().toString();
        String passWordConfirm = passWordConfirmEdit.getText().toString();
        String phoneRegister = phoneRegisterEdit.getText().toString();

        if (!emailRegister.equals("")) {
            if (!passWordRegister.equals("")) {
                if (!passWordConfirm.equals("")) {
                    if (!phoneRegister.equals("")) {
                        if (passWordRegister.equals(passWordConfirm)) {

                        } else {
                            Toast.makeText(this, "Password And Confirm Must Match!", Toast.LENGTH_SHORT).show();
                            return false;
                        }
                    } else {
                        Toast.makeText(this, "Please Input Phone Number!", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                } else {
                    Toast.makeText(this, "Please Confirm Password!", Toast.LENGTH_SHORT).show();
                    return false;
                }
            } else {
                Toast.makeText(this, "Please Input Password!", Toast.LENGTH_SHORT).show();
                return false;
            }
        } else {
            Toast.makeText(this, "Please Input Email!", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (myDB.checkUserExistence(emailRegister) == false) {
            myDB.insertUser("", emailRegister, passWordConfirm, phoneRegister);
            return true;
        } else {
            Toast.makeText(this, "Email Has Been Registered!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}