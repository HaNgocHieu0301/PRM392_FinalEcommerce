package com.example.prm392_finalecommerce;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import DAOs.IUserDAO;
import DAOs.UserRoomDatabase;
import models.User;

public class LogoutActivity extends AppCompatActivity {

    //Button logoutBtn = findViewById(R.id.logoutBtn);
    public void logout() {
        // Xóa dữ liệu đăng nhập khỏi bộ nhớ cache
        SharedPreferences preferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();

        // Khởi động lại ứng dụng
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
