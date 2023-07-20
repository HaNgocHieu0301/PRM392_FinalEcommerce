package Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prm392_finalecommerce.Admin_OrderDetail;
import com.example.prm392_finalecommerce.R;

import java.util.List;

import Helper.StatusValue;
import models.Order;

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
        Order order = orderList.get(position);
        holder.orderDate.setText(order.orderDate);
        holder.shippedDate.setText(order.shippedDate);
        holder.totalPrice.setText("" + order.total);
        holder.status.setText("" + StatusValue.GetStatusValue(order.statusId));
        if(order.statusId == 3)
            holder.status.setTextColor(Color.parseColor("#FC1818"));
        else
            holder.status.setTextColor(Color.parseColor("#ff99cc00"));
        holder.viewDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Admin_OrderDetail.class);
                intent.putExtra("orderId", order.orderId);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView orderDate, shippedDate, totalPrice, status;
        LinearLayout viewDetail;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            orderDate = itemView.findViewById(R.id.orderDate);
            shippedDate = itemView.findViewById(R.id.shippedDate);
            totalPrice = itemView.findViewById(R.id.totalPrice);
            status = itemView.findViewById(R.id.status);
            viewDetail = itemView.findViewById(R.id.row_admin_order);
        }
    }
}
