package com.example.prm392_finalecommerce;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.prm392_finalecommerce.databinding.ActivityOrderBinding;
import com.example.prm392_finalecommerce.databinding.ActivityOrderDetailBinding;

import java.util.List;

import Adapter.CreateOrderAdapter;
import Adapter.OrderDetailAdapter;
import Repository.OrderDetailRepository;
import Repository.OrderRepository;
import models.Order;
import models.OrderDetail;
import models.Wish;

public class OrderDetailActivity extends AppCompatActivity {
    private ActivityOrderDetailBinding binding;
    RecyclerView orderDetailRec;
    List<OrderDetail> orderDetailList;
    OrderDetailAdapter orderDetailAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrderDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        int orderId = Integer.parseInt(getIntent().getStringExtra("orderId"));
        OrderRepository orderRepository = new OrderRepository(this.getApplication());
        Order order = orderRepository.getOrderById(orderId);
        TextView name = binding.name;
        TextView address = binding.address;
        TextView phone = binding.phone;
        name.setText(""+order.shipName);
        address.setText(""+order.shipAddress);
        phone.setText(""+order.shipPhone);
        OrderDetailRepository orderDetailRepository = new OrderDetailRepository(this.getApplication());
        orderDetailList = orderDetailRepository.getListByOrderId(orderId);

        orderDetailRec = binding.orderDetailRec;
        orderDetailAdapter = new OrderDetailAdapter(this.getApplication(), orderDetailList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        orderDetailRec.setAdapter(orderDetailAdapter);
        orderDetailRec.setLayoutManager(linearLayoutManager);
    }
}