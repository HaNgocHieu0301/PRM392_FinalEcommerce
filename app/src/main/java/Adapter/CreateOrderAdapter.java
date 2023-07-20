package Adapter;

import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.prm392_finalecommerce.R;

import java.util.List;

import Repository.ProductRepository;
import models.Product;
import models.Wish;

public class CreateOrderAdapter extends RecyclerView.Adapter<CreateOrderAdapter.ViewHolder>{
    private Context context;
    private Application application;
    private List<Wish> wishList;

    public CreateOrderAdapter(Context context, Application application, List<Wish> wishList) {
        this.context = context;
        this.application = application;
        this.wishList = wishList;
    }

    @NonNull
    @Override
    public CreateOrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CreateOrderAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.create_order_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CreateOrderAdapter.ViewHolder holder, int position) {
        ProductRepository productRepository = new ProductRepository(application);
        Product product = productRepository.getProductById(wishList.get(position).productId);
        Glide.with(context).load(product.image).into(holder.image);
        holder.name.setText(product.productName);
        holder.price.setText("$ "+product.price);
        holder.discount.setText("-"+ product.discount + "%");
        holder.quantity.setText("x "+wishList.get(position).quantity);
    }

    @Override
    public int getItemCount() {
        return wishList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name, price, discount, quantity;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.productImageOrder);
            name = itemView.findViewById(R.id.productNameOrder);
            price = itemView.findViewById(R.id.productPriceOrder);
            discount = itemView.findViewById(R.id.productDiscountOrder);
            quantity = itemView.findViewById(R.id.quantityOrder);
        }
    }
}
