package Fragment;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.prm392_finalecommerce.CreateProduct;
import com.example.prm392_finalecommerce.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import Adapter.OrderAdapter;
import Adapter.ProductAdapter;
import DAOs.OrderRoomDatabase;
import DAOs.ProductRoomDatabase;
import models.Order;
import models.Product;

public class Admin_OrderFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin__order, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.orderRecycler);
        List<Order> orderList = new ArrayList<>(OrderRoomDatabase.getDatabase(getActivity().getApplication()).orderDAO().getAll());
        //List<Order> orderList = new ArrayList<>();
        OrderAdapter orderAdapter = new OrderAdapter(orderList, getActivity().getApplication());
        recyclerView.setAdapter(orderAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }
}