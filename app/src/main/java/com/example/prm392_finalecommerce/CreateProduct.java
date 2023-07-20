package com.example.prm392_finalecommerce;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.List;
import java.util.UUID;

import DAOs.IProductDAO;
import DAOs.ProductRoomDatabase;
import Repository.ProductRepository;
import models.Product;

public class CreateProduct extends AppCompatActivity {

    EditText editTextProductName, editTextCategory, editTextUnitsInStock,
            editTextDescription, editTextPrice, editTextDiscount;
    ImageView imageProductImage;
    Button buttonAddProduct, buttonUploadImage;
    Uri uri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_product);

        editTextCategory = findViewById(R.id.editTextProductCategory);
        editTextProductName = findViewById(R.id.editTextProductName);
        editTextUnitsInStock = findViewById(R.id.editTextUnitsInStock);
        editTextDescription = findViewById(R.id.editTextDescription);
        editTextPrice = findViewById(R.id.editTextPrice);
        editTextDiscount= findViewById(R.id.editTextDiscount);
        imageProductImage = findViewById(R.id.imageProductImages);
        buttonAddProduct = findViewById(R.id.buttonAddProduct);
        buttonUploadImage = findViewById(R.id.buttonUploadImage);

        buttonAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addProduct();
            }
        });

        imageProductImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("/image/*");
                startActivityForResult(intent, 100);
            }
        });

        buttonUploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //uploadImage();
            }
        });
    }
//    private void uploadImage(){
//        StorageReference storageReference = FirebaseStorage.getInstance().getReference("products/"+ UUID.randomUUID()+".png");
//        storageReference.putFile(uri)
//                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                    @Override
//                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                        storageReference.getDownloadUrl()
//                                .addOnSuccessListener(new OnSuccessListener<Uri>() {
//                                    @Override
//                                    public void onSuccess(Uri uri) {
//                                        FirebaseFirestore.getInstance()
//                                                .collection("products")
//                                                .update("image", uri.toString());
//                                    }
//                                })
//                    }
//                })
//    }
    private void addProduct(){
        Product p = new Product();
        p.productName = editTextProductName.getText().toString();
//        p.categoryName = editTextCategory.getText().toString();
        p.price =  Double.parseDouble(editTextPrice.getText().toString());
        p.discount = Double.parseDouble(editTextDiscount.getText().toString());
        p.unitsInStock = Integer.parseInt(editTextUnitsInStock.getText().toString());
        p.description = editTextDescription.getText().toString();
        //Product p = new Product(name,category, price, null, discount,unitsInStock, description);
        //ProductRepository repository = new ProductRepository();
        //repository.insertProducts(p);
        //productRepository.insertProduct(p);
        ProductRepository repository = new ProductRepository(CreateProduct.this.getApplication());
        repository.insertProducts(p);
        //long result = ProductRoomDatabase.getDatabase(CreateProduct.this).productDAO().insertAProduct(p);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 100){
            uri = data.getData();
            imageProductImage.setImageURI(uri);
        }
    }
}