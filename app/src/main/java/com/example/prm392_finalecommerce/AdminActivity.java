package com.example.prm392_finalecommerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import Adapter.ProductAdapter;
import Fragment.HomeFragment;
import Repository.DataInsertionByFirebaseCallback;
import Repository.ProductRepository;
import Fragment.Admin_CategoryFragment;
import Fragment.Admin_ProductFragment;
import Fragment.Admin_OrderFragment;
import Fragment.Admin_ProductDetailFragment;
public class AdminActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, ProductAdapter.onClickListener {

    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;

    ProductAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        Toolbar toolbar = findViewById(R.id.admin_toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.admin_drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.nav_home) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();

        } else if (item.getItemId() == R.id.nav_productManagement) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Admin_ProductFragment()).commit();

        } else if (item.getItemId() == R.id.nav_categoryManagement) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Admin_CategoryFragment()).commit();

        } else if (item.getItemId() == R.id.nav_orderManagement) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Admin_OrderFragment()).commit();

        } else if (item.getItemId() == R.id.nav_statistic) {
            //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Fragment_Admin_Statistic()).commit();
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void viewProductDetail(int productId) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new Admin_ProductDetailFragment(productId))
                .commit();
    }
}