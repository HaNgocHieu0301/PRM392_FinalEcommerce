package com.example.prm392_finalecommerce;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prm392_finalecommerce.databinding.ActivityOrderBinding;
import com.example.prm392_finalecommerce.databinding.FragmentWishListBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Adapter.CreateOrderAdapter;
import Adapter.WishListAdapters;
import Repository.OrderDetailRepository;
import Repository.OrderRepository;
import Repository.ProductRepository;
import Repository.UserRepository;
import Repository.WishRepository;
import models.Order;
import models.OrderDetail;
import models.Product;
import models.User;
import models.Wish;

public class OrderActivity extends AppCompatActivity {
    private ActivityOrderBinding binding;
    RecyclerView createOrderRec;
    List<Wish> wishList;
    CreateOrderAdapter createOrderAdapter;
    double total = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayList<Integer> wishIdList = getIntent().getIntegerArrayListExtra("wishIdList");
        wishList = new ArrayList<>();
        WishRepository wishRepository = new WishRepository(this.getApplication());
        ProductRepository productRepository = new ProductRepository(this.getApplication());
        for (int id : wishIdList) {
            Wish wish = wishRepository.getWishById(id);
            wishList.add(wish);
            Product product = productRepository.getProductById(wish.productId);
            total += product.price * wish.quantity;
        }

//----------------------------------- get userId from session----------------------------
        int userId = 1; //get user id
        //
        UserRepository userRepository = new UserRepository(this.getApplication());
        User user = userRepository.getUserByUserId(userId);

        EditText name = binding.name;
        name.setText(user.first_name + " " + user.last_name);
        EditText address = binding.address;
        address.setText(user.address != null ? user.address : "");
        EditText phone = binding.phone;
        phone.setText(user.phoneNumber);

        TextView totalOrder = binding.totalOrder;
        totalOrder.setText("$ " + total);

        TextView btnBuy = binding.btnBuyOrder;
        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    //tao order va add
                    OrderRepository orderRepository = new OrderRepository(getApplication());
                    Order order = new Order();
                    order.userId = userId;
                    if(binding.address.getText().toString().equals("")||binding.name.getText().toString().equals("")||binding.phone.getText().toString().equals(""))
                        throw new Exception();
                    order.shipAddress = binding.address.getText().toString();
                    order.shipName = binding.name.getText().toString();
                    order.shipPhone = binding.phone.getText().toString();
                    order.total = total;
                    order.statusId = 0;
                    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm");
                    order.orderDate = format.format(new Date());
                    long orderId = orderRepository.insertOrder(order);

                    //tao list order detail va add
                    OrderDetailRepository orderDetailRepository = new OrderDetailRepository(getApplication());
                    for (Wish wish : wishList) {
                        OrderDetail orderDetail = new OrderDetail();
                        orderDetail.orderId = (int)orderId;
                        orderDetail.productId = wish.productId;
                        Product product = productRepository.getProductById(wish.productId);
                        // giam unitsInStock
                        if(product.unitsInStock<wish.quantity)
                            throw new Exception();
                        product.unitsInStock -= wish.quantity;
                        productRepository.updateProduct(product);
                        orderDetail.unitPrice = product.price;
                        orderDetail.quantity = wish.quantity;
                        orderDetail.discount = product.discount;
                        orderDetailRepository.insertOrderDetail(orderDetail);
                        wishRepository.deleteItem(wish);
                    }
                    Toast.makeText(getApplication(), "Order Success", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(OrderActivity.this,HomeActivity.class);
                    startActivity(intent,null);
                }catch (Exception exception){
                    Toast.makeText(getApplication(), "Order Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
        createOrderRec = binding.createOrderRec;
        createOrderAdapter = new CreateOrderAdapter(this.getApplicationContext(), this.getApplication(), wishList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        createOrderRec.setAdapter(createOrderAdapter);
        createOrderRec.setLayoutManager(linearLayoutManager);
    }
}