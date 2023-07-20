package com.example.prm392_finalecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import DAOs.IUserDAO;
import DAOs.UserRoomDatabase;
import Repository.UserRepository;
import models.User;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        EditText username = findViewById(R.id.username);
        EditText password = findViewById(R.id.password);
        Button loginBtn = findViewById(R.id.loginBtn);
        TextView signup = findViewById(R.id.login_create);
        TextView fgPass = findViewById(R.id.login_forgetPass);
        fgPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);            }
        });
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Validate validate = new Validate();
                CookieManager cookieManager = CookieManager.getInstance();
                UserRepository userRepository = new UserRepository(LoginActivity.this.getApplication());
                String un = username.getText().toString();
                User user = userRepository.getUserByUsername(un);
                if (user != null) {
                    if (Pass.equals(user.password)) {
                        cookieManager.setCookie("https://login.com", "username=Uname; password=Pass");
                        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, "Wrong password", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(LoginActivity.this, "Login fail! User dose not exist", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}