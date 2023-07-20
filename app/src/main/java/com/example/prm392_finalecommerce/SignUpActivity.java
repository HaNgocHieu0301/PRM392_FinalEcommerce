package com.example.prm392_finalecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Address;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.sql.Date;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import DAOs.IUserDAO;
import DAOs.UserRoomDatabase;
import Repository.UserRepository;
import models.User;

public class SignUpActivity extends AppCompatActivity {
    EditText email, uname, fname, lname, phone, address, pass, cpass;
    Button signUp;
    TextView login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        email = findViewById(R.id.signup_email);
        uname = findViewById(R.id.signup_username);
        fname = findViewById(R.id.signup_fname);
        lname = findViewById(R.id.signup_lname);
        phone = findViewById(R.id.signup_phone);
        address = findViewById(R.id.signup_address);
        pass = findViewById(R.id.signup_password);
        cpass = findViewById(R.id.signup_cpass);
        signUp = findViewById(R.id.signup_button);
        login = findViewById(R.id.login);

        Date currentTime = new Date(new java.util.Date().getTime());



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Semail = email.getText().toString();
                String Suname = uname.getText().toString();
                String Sfname = fname.getText().toString();
                String Slname = lname.getText().toString();
                String Sphone = phone.getText().toString();
                String Saddress = address.getText().toString();
                String Spass = pass.getText().toString();
                String Scpass = cpass.getText().toString();

                Validate validate = new Validate();
                String hasingPW = validate.doHashing(Spass);
                String hasingCPW = validate.doHashing(Scpass);

                boolean gender = false;
                RadioGroup genderGroup = findViewById(R.id.gender_group);
                int selectedId = genderGroup.getCheckedRadioButtonId();
                if (selectedId == R.id.male_button) {
                    gender = false;
                } else if (selectedId == R.id.female_button) {
                    gender = true;
                }

                UserRepository userRepository = new UserRepository(SignUpActivity.this.getApplication());
                //if (checkInfo(Suname, Spass, Sfname, Slname, Semail, Sphone, Saddress)) {
                    if (userRepository.getUserByUsername(Suname)==null){
                        if (hasingPW.equals(hasingCPW)) {
                            User user = new User(Suname, hasingPW, Sfname, Slname, Semail, gender, Saddress, Sphone, currentTime, currentTime, false);
                            userRepository.insertUser(user);
                            Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                            startActivity(intent);
                        } else {
                            cpass.requestFocus();
                            cpass.setError("Confirm password and password are not the same");
                        }
                    }else{
                        uname.requestFocus();
                        uname.setError("User existed! Please re-enter username");
                    }
                }
            }
        });
    }

    private Boolean checkInfo(String Cuname, String Cpass, String Cfname, String Clname, String Cemail, String Cphone, String Caddress) {
        Validate v = new Validate();

        if (v.checkPass(Cpass) == false) {
            pass.requestFocus();
            pass.setError("Wrong format password");
            return false;
        }
        if (v.checkName(Cuname) == false) {
            uname.requestFocus();
            uname.setError("Please re-enter Username");
            return false;
        }
        if (v.checkString(Cfname) == false) {
            fname.requestFocus();
            fname.setError("Please re-enter Firstname");
            return false;
        }
        if (v.checkString(Clname) == false) {
            lname.requestFocus();
            lname.setError("Please re-enter Lastname");
            return false;
        }
        if (v.checkEmail(Cemail) == false) {
            email.requestFocus();
            email.setError("Please re-enter Email");
            return false;
        }
        if (v.checkPhone(Cphone) == false) {
            phone.requestFocus();
            phone.setError("Please re-enter Phone");
            return false;
        }
        if (v.checkString(Caddress) == false) {
            address.requestFocus();
            address.setError("Please re-enter Address");
            return false;
        }
        return true;
    }
}