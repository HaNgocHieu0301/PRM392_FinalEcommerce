package Adapter;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prm392_finalecommerce.OrderDetailActivity;
import com.example.prm392_finalecommerce.R;

import java.util.List;

import Helper.StatusValue;
import Repository.WishRepository;
import models.Order;
import models.Product;
import models.Wish;

public class OrderHistoryAdapters extends RecyclerView.Adapter<OrderHistoryAdapters.ViewHolder>{
    private Context context;
    private List<Order> orderList;
    private Application application;

    public OrderHistoryAdapters(Context context, List<Order> orderList, Application application) {
        this.context = context;
        this.application = application;
        this.orderList = orderList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OrderHistoryAdapters.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.order_history_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Order order = orderList.get(position);
        holder.orderDate.setText(order.orderDate);
        holder.shippedDate.setText(order.shippedDate);
        holder.totalPrice.setText("" + order.total);
        holder.status.setText("" + StatusValue.GetStatusValue(order.statusId));
        if(order.statusId == 3)
            holder.status.setTextColor(Color.parseColor("#FC1818"));
        else
            holder.status.setTextColor(Color.parseColor("#ff99cc00"));
        holder.orderId.setText(""+order.orderId);
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView orderDate, shippedDate, totalPrice, status, orderId;
        Button btnDetail;
        ViewGroup rootView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            orderDate = itemView.findViewById(R.id.orderDate);
            shippedDate = itemView.findViewById(R.id.shippedDate);
            totalPrice = itemView.findViewById(R.id.totalPrice);
            status = itemView.findViewById(R.id.status);
            orderId = itemView.findViewById(R.id.orderId);
            btnDetail = itemView.findViewById(R.id.btnOrderDetail);
            btnDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, OrderDetailActivity.class);
                    intent.putExtra("orderId", orderId.getText().toString());
                    v.getContext().startActivity(intent,null);
                }
            });
        }
    }
}