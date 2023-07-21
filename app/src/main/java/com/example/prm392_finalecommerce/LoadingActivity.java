package com.example.prm392_finalecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import net.sourceforge.jtds.jdbc.DateTime;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import Repository.DataInsertionByFirebaseCallback;
import Repository.OrderRepository;
import Repository.ProductRepository;
import Repository.UserRepository;
import models.Order;

public class LoadingActivity extends AppCompatActivity implements DataInsertionByFirebaseCallback{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        ProductRepository productRepository = new ProductRepository(LoadingActivity.this.getApplication());
        productRepository.InsertDataFromFirebaseToSqlite(LoadingActivity.this.getApplication(), this);
        //UserRepository userRepository = new UserRepository(LoadingActivity.this.getApplication());
    }

    @Override
    public void onDataInserted() {
        OrderRepository repo = new OrderRepository(getApplication());
//        repo.removeAll();
//
//        Order od = new Order();
//        od.userId = 1;
//        LocalDateTime currentDateTime = LocalDateTime.now();
//
//        // Định dạng ngày theo ý muốn (ví dụ: "dd/MM/yyyy")
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        String dateString = currentDateTime.format(formatter);
//        od.orderDate = dateString;
//        od.shipAddress = "HN";
//        od.shipName = "Ha Ngoc Hieu";
//        od.shipPhone = "0944112306";
//        od.shippedDate = dateString;
//        od.statusId = 2;
//        od.total = 100000;
//        repo.insertOrder(od);
//
//        Order od2 = new Order();
//        od2.userId = 1;
//        od2.orderDate = dateString;
//        od2.shipAddress = "HN2";
//        od2.shipName = "Ha Ngoc Hieu 2";
//        od2.shipPhone = "0944112306";
//        od2.shippedDate = dateString;
//        od2.statusId = 2;
//        od2.total = 222222;
//        repo.insertOrder(od2);

        Intent intent = new Intent(LoadingActivity.this, AdminActivity.class);
        startActivity(intent);
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