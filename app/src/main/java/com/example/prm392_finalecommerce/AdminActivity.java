package com.example.prm392_finalecommerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

import Adapter.AdminTabAdapter;
import DAOs.DataInsertionCallback;
import DAOs.ProductRoomDatabase;
import Repository.ProductRepository;
import models.Product;

public class AdminActivity extends AppCompatActivity implements DataInsertionCallback {
    ProductAdapter productAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        ProductRepository productRepository = new ProductRepository(AdminActivity.this.getApplication());
        productRepository.InsertDataFromFirebaseToSqlite(AdminActivity.this.getApplication(), AdminActivity.this);
    }

    @Override
    public void onDataInserted() {
        // Các câu lệnh findViewById() và xử lý UI khác
//        Toolbar myToolbar = findViewById(R.id.myToolbar);
//        TextView textViewAmount = findViewById(R.id.textViewAmount);
//        setSupportActionBar(myToolbar);
//
//        textViewAmount.setText(""+ ProductRoomDatabase.getDatabase(AdminActivity.this).productDAO().getAll().size());
//        RecyclerView recyclerView = findViewById(R.id.productRecycler);
//        List<Product> productList = new ArrayList<>(ProductRoomDatabase.getDatabase(AdminActivity.this).productDAO().getAll());
//        productAdapter = new ProductAdapter(productList, AdminActivity.this);
//        recyclerView.setAdapter(productAdapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        TabLayout tabLayout = findViewById(R.id.adminTabLayout);
        //ViewPager viewPager = findViewById(R.id.viewPaperTest);

        //create a new tab named "Category"
        TabLayout.Tab firstTab = tabLayout.newTab();
        firstTab.setText("Category");
        tabLayout.addTab(firstTab);

        //create a new tab named "Category"
        TabLayout.Tab secondTab = tabLayout.newTab();
        secondTab.setText("Product");
        tabLayout.addTab(secondTab);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.simpleFrameLayout, new Fragment_Admin_Category());
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // get the current selected tab's position and replace the fragment accordingly
                Fragment fragment = null;
                switch (tab.getPosition()) {
                    case 0:
                        fragment = new Fragment_Admin_Category();
                        break;
                    case 1:
                        fragment = new Fragment_Admin_Product();
                        break;
                }
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.simpleFrameLayout, fragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        //AdminTabAdapter adapter = new AdminTabAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        //viewPager.setAdapter(adapter);
        //viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }
}