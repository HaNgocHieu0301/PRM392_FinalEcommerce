package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prm392_finalecommerce.R;
import java.util.List;
import models.Category;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {
    private Context context;
    private List<Category> categoryList;

    public CategoryAdapter(List<Category> mCategoryList,Context context) {
        this.context = context;
        categoryList = mCategoryList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_category, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Category p = categoryList.get(position);
        holder.categoryId.setText(p.getCategoryId());
        holder.categoryName.setText(p.getCategoryName());
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView categoryId, categoryName;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryId = itemView.findViewById(R.id.tvCategoryId);
            categoryName = itemView.findViewById(R.id.tvCategoryName);
        }
    }
}
