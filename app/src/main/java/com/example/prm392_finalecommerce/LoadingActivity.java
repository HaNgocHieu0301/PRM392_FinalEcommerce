package com.example.prm392_finalecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import Repository.DataInsertionByFirebaseCallback;
import Repository.ProductRepository;
import Repository.UserRepository;

public class LoadingActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        ProductRepository productRepository = new ProductRepository(LoadingActivity.this.getApplication());
        productRepository.InsertDataFromFirebaseToSqlite(LoadingActivity.this.getApplication());
        UserRepository userRepository = new UserRepository(LoadingActivity.this.getApplication());
        //userRepository.InsertDataFromFirebaseToSqlite(LoadingActivity.this.getApplication());
        Button nextPage = findViewById(R.id.buttonNextToLogin);
        nextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoadingActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}