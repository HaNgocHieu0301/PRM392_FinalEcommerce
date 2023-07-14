package com.example.prm392_finalecommerce;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import models.Product;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {

    private Context context;
    private List<Product> productList;

    public ProductAdapter(List<Product> mProducts,Context context) {
        this.context = context;
        productList = mProducts;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Product p = productList.get(position);
        holder.productName.setText(p.productName);
        holder.description.setText(p.description);
        holder.price.setText("" + p.price);
        Glide.with(context).load(p.image).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView productName, description, price;
        ImageView image;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.tvProductNameRow);
            description = itemView.findViewById(R.id.tvDescriptionRow);
            price = itemView.findViewById(R.id.tvPriceRow);
            image = itemView.findViewById(R.id.imageViewProductRow);
        }
    }
}
