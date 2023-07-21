package com.example.prm392_finalecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import Repository.ProductRepository;
import models.Product;

public class Admin_ProductDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_product_detail);

        Intent intent = getIntent();
        int id = intent.getIntExtra("productId", -1);

        ProductRepository productRepository = new ProductRepository(getApplication());
        Product product = productRepository.getProductById(id);

        ImageView imageView = findViewById(R.id.productImage);
        Glide.with(this.getApplicationContext())
                .load(product.image)
                .into(imageView);
        TextView productName = findViewById(R.id.productName);
        productName.setText(product.productName);
        TextView productPrice = findViewById(R.id.productPrice);
        productPrice.setText("$ "+product.price);
        TextView productDiscount = findViewById(R.id.productDiscount);
        productDiscount.setText("Discount: "+product.discount+"%");
        TextView productDescription = findViewById(R.id.productDescription);
        productDescription.setText(product.description);
        TextView productUnitsInStock = findViewById(R.id.productUnitsInStock);
        productUnitsInStock.setText(" "+product.unitsInStock +" available");
        TextView btnEdit = findViewById(R.id.btnEdit);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CreateProduct.class);
                intent.putExtra("productId", id);
                startActivity(intent);
            }
        });
        TextView btnHideProduct = findViewById(R.id.btnHideProduct);
        btnHideProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}