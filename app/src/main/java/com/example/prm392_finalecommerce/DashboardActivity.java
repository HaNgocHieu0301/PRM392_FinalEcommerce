package com.example.prm392_finalecommerce;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import DAOs.DataInsertionCallback;
import DAOs.IProductDAO;
import DAOs.ProductRoomDatabase;
import Repository.ProductRepository;
import Repository.UserRepository;
import models.Product;

public class DashboardActivity extends AppCompatActivity implements DataInsertionCallback {
    ProductRoomDatabase db;
    IProductDAO productDao;

    ProductAdapter productAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        ProductRepository productRepository = new ProductRepository(DashboardActivity.this.getApplication());
        productRepository.InsertDataFromFirebaseToSqlite(DashboardActivity.this.getApplication(), DashboardActivity.this);
    }
    @Override
    public void onDataInserted() {
        // Các câu lệnh findViewById() và xử lý UI khác
        Toolbar myToolbar = findViewById(R.id.myToolbar);
        TextView textViewAmount = findViewById(R.id.textViewAmount);
        setSupportActionBar(myToolbar);

        textViewAmount.setText(""+ProductRoomDatabase.getDatabase(DashboardActivity.this).productDAO().getAll().size());
        RecyclerView recyclerView = findViewById(R.id.productRecycler);
        List<Product> productList = new ArrayList<>(ProductRoomDatabase.getDatabase(DashboardActivity.this).productDAO().getAll());
        productAdapter = new ProductAdapter(productList, DashboardActivity.this);
        recyclerView.setAdapter(productAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}