package com.example.prm392_finalecommerce;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import Adapter.ProductAdapter;
import DAOs.DataInsertionCallback;
import DAOs.ProductRoomDatabase;
import Repository.ProductRepository;
import models.Product;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_Admin_Product#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Admin_Product extends Fragment{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment_Admin_Product() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_Admin_Product.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_Admin_Product newInstance(String param1, String param2) {
        Fragment_Admin_Product fragment = new Fragment_Admin_Product();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment__admin__product, container, false);

        FloatingActionButton addNewProduct = (FloatingActionButton) view.findViewById(R.id.buttonAddNewProduct);
        addNewProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CreateProduct.class);
                startActivity(intent);
            }
        });
        RecyclerView recyclerView = view.findViewById(R.id.productRecycler);
        List<Product> productList = new ArrayList<>(ProductRoomDatabase.getDatabase(getActivity().getApplication()).productDAO().getAll());
        ProductAdapter productAdapter = new ProductAdapter(productList, getActivity().getApplication());
        recyclerView.setAdapter(productAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

}