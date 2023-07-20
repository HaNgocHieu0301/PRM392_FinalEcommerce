package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prm392_finalecommerce.R;

import java.util.List;

import models.Order;
import models.Product;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.MyViewHolder> {
    private Context context;
    private List<Order> orderList;

    public OrderAdapter(List<Order> mProducts,Context context) {
        this.context = context;
        orderList = mProducts;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_product, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
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
}
