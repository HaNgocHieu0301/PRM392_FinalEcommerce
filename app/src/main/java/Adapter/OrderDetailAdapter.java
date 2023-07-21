package Adapter;

import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.prm392_finalecommerce.R;

import java.util.List;

import Repository.ProductRepository;
import models.OrderDetail;
import models.Product;
import models.Wish;

public class OrderDetailAdapter extends RecyclerView.Adapter<OrderDetailAdapter.ViewHolder>{
    private Application application;
    private List<OrderDetail> orderDetailList;

    public OrderDetailAdapter(Application application, List<OrderDetail> orderDetailList) {
        this.application = application;
        this.orderDetailList = orderDetailList;
    }

    @NonNull
    @Override
    public OrderDetailAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OrderDetailAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.order_detail_item, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull OrderDetailAdapter.ViewHolder holder, int position) {
        ProductRepository productRepository = new ProductRepository(application);
        Product product = productRepository.getProductById(orderDetailList.get(position).productId);
        Glide.with(application.getApplicationContext()).load(product.image).into(holder.image);
        holder.name.setText(product.productName);
        holder.price.setText("$ "+orderDetailList.get(position).unitPrice);
        holder.discount.setText("-"+ orderDetailList.get(position).discount + "%");
        holder.quantity.setText("x "+orderDetailList.get(position).quantity);
    }

    @Override
    public int getItemCount() {
        return orderDetailList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name, price, discount, quantity;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.productImageOrderDetail);
            name = itemView.findViewById(R.id.productNameOrderDetail);
            price = itemView.findViewById(R.id.productPriceOrderDetail);
            discount = itemView.findViewById(R.id.productDiscountOrderDetail);
            quantity = itemView.findViewById(R.id.quantityOrderDetail);
        }
    }
}
