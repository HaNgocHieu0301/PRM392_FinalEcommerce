package Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.prm392_finalecommerce.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

//import Helper.ImageDownloader;
import Helper.ImageDownloader;
import Repository.ProductRepository;
import models.Product;

public class PopularAdapters extends RecyclerView.Adapter<PopularAdapters.ViewHolder> implements Filterable {
    private Context context;
    private List<Product> productList;
    private List<Product> productListOld;

    public PopularAdapters(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
        this.productListOld = productList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PopularAdapters.ViewHolder holder, int position) {
        ImageDownloader i = new ImageDownloader(holder.popImg);
        i.execute("https://en.kepoper.com/wp-content/uploads/2020/11/g-dragon-comeback-4-e1604306039472.jpg");
//        String imageUrl = productList.get(position).image;
//        Glide.with(context)
//                .load(imageUrl)
//                .into(holder.popImg);
        holder.name.setText(productList.get(position).productName);
        holder.description.setText(productList.get(position).description);
        int dis = (int) Math.round(productList.get(position).discount);
        holder.discount.setText("Discount "+ dis + "% Off");
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String search = constraint.toString();
                if(search.isEmpty())
                {
                    productList = productListOld;
                }
                else 
                {
                    List<Product> list = new ArrayList<>();
                    for(Product p : productListOld)
                    {
                        if(p.productName.toLowerCase().contains(search.toLowerCase()))
                        {
                            list.add(p);
                        }
                    }
                    productList = list;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = productList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                productList = (List<Product>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView popImg;
        TextView name, description, discount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            popImg = itemView.findViewById(R.id.pop_img);
            name = itemView.findViewById(R.id.pop_name);
            description = itemView.findViewById(R.id.pop_des);
            discount = itemView.findViewById(R.id.pop_dis);
        }
    }
}
