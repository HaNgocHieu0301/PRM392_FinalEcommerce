package Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.prm392_finalecommerce.Admin_ProductDetail;
import com.example.prm392_finalecommerce.CreateProduct;
import com.example.prm392_finalecommerce.R;

import java.util.List;

import Repository.ProductRepository;
import models.Product;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {

    private Context context;
    private List<Product> productList;
    private onClickListener listener;

    public ProductAdapter(List<Product> mProducts,Context context, onClickListener listener) {
        this.context = context;
        productList = mProducts;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_product, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Product p = productList.get(position);
        holder.productName.setText(p.productName);
        holder.description.setText(p.description);
        holder.price.setText("" + p.price);
        Glide.with(context).load(p.image).into(holder.image);
        holder.productId = p.productId;
        holder.llProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Admin_ProductDetail.class);
                intent.putExtra("productId", p.productId);
                intent.putExtra("productName", p.productId);
                view.getContext().startActivity(intent, null);
                //listener.viewProductDetail(productId);


            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView productName, description, price;
        LinearLayout llProduct;
        ImageView image;
        int productId;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.tvProductNameRow);
            description = itemView.findViewById(R.id.tvDescriptionRow);
            price = itemView.findViewById(R.id.tvPriceRow);
            image = itemView.findViewById(R.id.imageViewProductRow);
            llProduct = itemView.findViewById(R.id.llProduct);
        }
    }

    public interface onClickListener{
        void viewProductDetail(int productId);
    }
}
