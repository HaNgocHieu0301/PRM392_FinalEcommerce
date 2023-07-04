package com.example.prm392_finalecommerce;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;

import java.util.ArrayList;
import java.util.List;

import Adapter.PopularAdapters;
import DAOs.ProductRoomDatabase;
import models.Product;

public class HomeActivity extends AppCompatActivity {
    RecyclerView popularRec;
    List<Product> productList;
    PopularAdapters popularAdapters;
    SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        popularRec = findViewById(R.id.pop_rec);
        productList = new ArrayList<>(ProductRoomDatabase.getDatabase(this).productDAO().getAll());

        popularAdapters = new PopularAdapters(this, productList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        popularRec.setAdapter(popularAdapters);
        popularRec.setLayoutManager(linearLayoutManager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                popularAdapters.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                popularAdapters.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

    @Override
    public void onBackPressed() {
        if(!searchView.isIconified())
        {
            searchView.setIconified(true);
            return;
        }
        super.onBackPressed();
    }
}