package com.example.prm392_finalecommerce;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.List;
import java.util.UUID;

import DAOs.CategoryRoomDatabase;
import DAOs.IProductDAO;
import DAOs.ProductRoomDatabase;
import Repository.ProductRepository;
import models.Category;
import models.Product;
import models.ProductTmp;

public class CreateProduct extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    EditText editTextProductName, editTextCategory, editTextUnitsInStock,
            editTextDescription, editTextPrice, editTextDiscount;
    ImageView imageProductImage;
    Spinner spinnerCategory;
    Button buttonAddProduct, buttonUploadImage;
    Uri uri;
    String imageUrl;
    int categoryId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_product);

        editTextCategory = findViewById(R.id.editTextProductCategory);
        editTextProductName = findViewById(R.id.editTextProductName);
        editTextUnitsInStock = findViewById(R.id.editTextUnitsInStock);
        editTextDescription = findViewById(R.id.editTextDescription);
        editTextPrice = findViewById(R.id.editTextPrice);
        imageProductImage = findViewById(R.id.imageProductImages);
        editTextDiscount = findViewById(R.id.editTextDiscount);
        buttonAddProduct = findViewById(R.id.buttonAddProduct);
        buttonUploadImage = findViewById(R.id.buttonUploadImage);
        //spinnerCategory = findViewById(R.id.spinnerCategory);
        Intent intent = getIntent();
        dbId = intent.getIntExtra("productId", -1);
        ProductRepository repository = new ProductRepository(getApplication());
        Product p = repository.getProductById((int) dbId);
        if (p != null) {
            editTextCategory.setText(""+p.categoryId);
            editTextProductName.setText(p.productName);
            editTextUnitsInStock.setText(""+p.unitsInStock);
            editTextDescription.setText(p.description);
            editTextPrice.setText("" + p.price);
            editTextDiscount.setText("" + p.discount);
            Glide.with(getApplicationContext())
                    .load(p.image)
                    .into(imageProductImage);
            buttonAddProduct.setText("Update");
            buttonAddProduct.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    updateProduct();
                }
            });
            imageProductImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("image/*");
                    startActivityForResult(intent, 100);
                }
            });
            buttonUploadImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    uploadImage();
                }
            });
            //spinnerCategory.setOnItemSelectedListener(this);
            //ArrayAdapter<Category> adapter = new ArrayAdapter<>(CreateProduct.this, android.R.layout.simple_spinner_dropdown_item, CategoryRoomDatabase.getDatabase(getApplication()).categoryDAO().getAll());
            //adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
            //spinnerCategory.setAdapter(adapter);
        } else {
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
                    intent.setType("image/*");
                    startActivityForResult(intent, 100);
                }
            });
            buttonUploadImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    uploadImage();
                }
            });
        }
    }

    private void updateProduct() {
        ProductRepository repository = new ProductRepository(getApplication());
        Product p = repository.getProductById(dbId);
        p.productName = editTextProductName.getText().toString();
        p.price = Double.parseDouble(editTextPrice.getText().toString());
        p.discount = Double.parseDouble(editTextDiscount.getText().toString());
        p.unitsInStock = Integer.parseInt(editTextUnitsInStock.getText().toString());
        p.description = editTextDescription.getText().toString();
//        FirebaseFirestore.getInstance()
//                .collection("products")
//                .document(id)
//                .set(p);
        repository.updateProduct(p);

        Toast.makeText(CreateProduct.this, "Update successfully!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(CreateProduct.this, HomeActivity.class);
        getApplicationContext().startActivity(intent);
    }

    public String id;

    private void uploadImage() {
        StorageReference storageReference = FirebaseStorage
                .getInstance()
                .getReference("products/" + id + ".png");
        storageReference.putFile(uri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        storageReference.getDownloadUrl()
                                .addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        FirebaseFirestore.getInstance()
                                                .collection("products")
                                                .document(id)
                                                .update("image", uri.toString());
                                        Toast.makeText(CreateProduct.this, "Done", Toast.LENGTH_SHORT).show();
                                        ProductRepository repository = new ProductRepository(getApplication());
                                        Product p = repository.getProductById((int) dbId);
                                        p.image = uri.toString();
                                        repository.updateProduct(p);
                                    }
                                });
                    }
                });
    }
    private void uploadImage2() {
        StorageReference storageReference = FirebaseStorage
                .getInstance()
                .getReference("products/" + id + ".png");
        storageReference.putFile(uri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        storageReference.getDownloadUrl()
                                .addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        FirebaseFirestore.getInstance()
                                                .collection("products")
                                                .document(id)
                                                .update("image", uri.toString());
                                        Toast.makeText(CreateProduct.this, "Done", Toast.LENGTH_SHORT).show();
                                        ProductRepository repository = new ProductRepository(getApplication());
                                        Product p = repository.getProductById((int) dbId);
                                        p.image = uri.toString();
                                        repository.updateProduct(p);
                                    }
                                });
                    }
                });
    }
    private void addProduct() {
        ProductTmp p = new ProductTmp();
        id = UUID.randomUUID().toString();
        p.productId = id;
        p.productName = editTextProductName.getText().toString();
        p.price = Double.parseDouble(editTextPrice.getText().toString());
        p.discount = Double.parseDouble(editTextDiscount.getText().toString());
        p.unitsInStock = Integer.parseInt(editTextUnitsInStock.getText().toString());
        p.description = editTextDescription.getText().toString();
        FirebaseFirestore.getInstance()
                .collection("products")
                .document(id)
                .set(p);
        Toast.makeText(CreateProduct.this, "Product Added!", Toast.LENGTH_SHORT).show();

        FirebaseFirestore.getInstance()
                .collection("products")
                .document(id)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        ProductTmp pTmp = document.toObject(ProductTmp.class);
                        Product px = new Product();
                        px.productName = pTmp.productName;
                        px.price = pTmp.price;
                        px.discount = pTmp.discount;
                        px.unitsInStock = pTmp.unitsInStock;
                        px.description = pTmp.description;
                        ProductRepository repository = new ProductRepository(getApplication());
                        dbId = (int)repository.insertProducts(px);
                    }
                });
    }

    private int dbId;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            uri = data.getData();
            imageProductImage.setImageURI(uri);
            // Tải ảnh lên Firebase Storage và lấy URL ảnh
            StorageReference imageRef = FirebaseStorage.getInstance().getReference().child("images/" + UUID.randomUUID().toString() + ".jpg");
            imageRef.putFile(uri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            // Lấy URL ảnh đã tải lên
                            imageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    imageUrl = uri.toString();
                                    Log.d("AddProductActivity", "Image URL: " + imageUrl);
                                }
                            });
                        }
                    });
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(getApplication(), "I chosen: " + i + " - " + l, Toast.LENGTH_SHORT).show();
        Category selectedCategory = (Category) adapterView.getItemAtPosition(i);
        categoryId = selectedCategory.getCategoryId();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}