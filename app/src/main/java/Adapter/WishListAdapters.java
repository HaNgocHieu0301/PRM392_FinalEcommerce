package Adapter;

import android.app.Application;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AdapterListUpdateCallback;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.prm392_finalecommerce.R;

import java.util.ArrayList;
import java.util.List;

import Repository.ProductRepository;
import Repository.WishRepository;
import models.Product;
import models.Wish;

public class WishListAdapters extends RecyclerView.Adapter<WishListAdapters.ViewHolder>{

    private Context context;
    private Application application;
    private List<Wish> wishList;
    private ViewGroup parent;
    IWishListData listener;

    private float totalPayment = 0;
    public WishListAdapters(Context context, List<Wish> wishList, Application application, IWishListData listener) {
        this.context = context;
        this.wishList = wishList;
        this.application = application;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.parent = parent;
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_wishlist_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProductRepository productRepository = new ProductRepository(application);
        Product product = productRepository.getProductById(wishList.get(position).productId);
        Glide.with(context).load(product.image).into(holder.image);
        holder.name.setText(product.productName);
        holder.price.setText("$ "+product.price);
        holder.discount.setText("Discount: "+ product.discount + " %");
        holder.wishQuantity.setMinValue(0);
        int unitsInStock = product.unitsInStock;
        holder.wishQuantity.setMaxValue(unitsInStock);
        int qty = wishList.get(position).quantity;
        holder.wishQuantity.setValue(qty>unitsInStock?unitsInStock:qty);
        holder.unitsInStock.setText(" "+unitsInStock+" available");
        if(unitsInStock==0)
        {
            holder.unitsInStock.setTextColor(Color.RED);
            holder.checked.setClickable(false);
        }
        holder.remove.setOnClickListener(view->{
//            WishRepository wishRepository = new WishRepository(application);
//            wishRepository.deleteItem(wishList.get(position));

            wishList.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, wishList.size());
        });

        holder.checked.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                TextView total = holder.itemView.findViewById(R.id.totalWL);
                if(isChecked)
                    totalPayment += product.price * wishList.get(holder.getAdapterPosition()).quantity;
                else
                {
                    totalPayment -= product.price * wishList.get(holder.getAdapterPosition()).quantity;
                    listener.changeCheckBoxState();
                }
                listener.changeTotalInWL(totalPayment);

            }
        });

        holder.wishQuantity.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                if(holder.checked.isChecked()){
                    totalPayment += (newVal-oldVal)*product.price;
                    listener.changeTotalInWL(totalPayment);
                }
                wishList.get(holder.getAdapterPosition()).quantity = newVal;
                WishRepository wishRepository = new WishRepository(application);
                wishRepository.updateItem(wishList.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return wishList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name, price, discount, unitsInStock;
        NumberPicker wishQuantity;
        Button remove;
        CheckBox checked;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.productImageWL);
            name = itemView.findViewById(R.id.productNameWL);
            price = itemView.findViewById(R.id.productPriceWL);
            discount = itemView.findViewById(R.id.productDiscountWL);
            unitsInStock = itemView.findViewById(R.id.productUnitsInStockWL);
            wishQuantity = itemView.findViewById(R.id.wishQuantityWL);

            remove = itemView.findViewById(R.id.btnRemoveWL);
            checked = itemView.findViewById(R.id.cbCheckWL);

            TextView total = itemView.findViewById(R.id.totalWL);

        }
    }

    public interface IWishListData{
        void changeTotalInWL(float total);
        void changeCheckBoxState();
    }
}
