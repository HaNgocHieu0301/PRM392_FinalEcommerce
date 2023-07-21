package com.example.prm392_finalecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.UUID;

import Repository.CategoryRepository;
import Repository.ProductRepository;
import models.Category;
import models.Product;
import models.ProductTmp;

public class CreateCategory extends AppCompatActivity {
    EditText name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_category);

        name = findViewById(R.id.editTextCategoryName);
        Button addButton = findViewById(R.id.addNewCategoryButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddCategory();
                Intent intent = new Intent(CreateCategory.this, AdminActivity.class);
                startActivity(intent);
            }
        });
    }
    private void AddCategory(){
        Category c = new Category();
        c.setCategoryName(name.getText().toString());
        CategoryRepository repo = new CategoryRepository(getApplication());
        repo.insertCategories(c);
        Toast.makeText(CreateCategory.this, "Category Added!", Toast.LENGTH_SHORT).show();
    }
}