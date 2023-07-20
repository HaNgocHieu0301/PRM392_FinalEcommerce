package Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prm392_finalecommerce.OrderActivity;
import com.example.prm392_finalecommerce.R;
import com.example.prm392_finalecommerce.databinding.FragmentHomeBinding;
import com.example.prm392_finalecommerce.databinding.FragmentWishListBinding;

import java.util.ArrayList;
import java.util.List;

import Adapter.PopularAdapters;
import Adapter.WishListAdapters;
import DAOs.ProductRoomDatabase;
import Repository.ProductRepository;
import Repository.WishRepository;
import models.Product;
import models.Wish;

public class WishListFragment extends Fragment implements WishListAdapters.IWishListData {
    public WishListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private FragmentWishListBinding binding;
    RecyclerView wishListRec;
    List<Wish> wishList;
    WishListAdapters wishListAdapters;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Trong Fragment Detail

//        ProductRepository repo = new ProductRepository(getActivity().getApplication());
//        repo.insertProducts(
//                new Product("Candy", 0, 10, "https://www.daysoftheyear.com/wp-content/uploads/candy-day.jpg", 10, 100, "Candy des"),
//                new Product("Laptop", 0, 20, "https://hanoicomputercdn.com/media/product/68198_laptop_lenovo_ideapad_slim_5_pro_37.png", 30, 200, "Laptop des"),
//                new Product("Clothes", 0, 10, "https://www.thespruce.com/thmb/zgCEkzj4shXVo4G_20yzPplwU_I=/6558x0/filters:no_upscale():max_bytes(150000):strip_icc()/wash-new-clothes-before-wearing-2146345-03-999483b3d51a435ba53c8d9ef5c2d5c4.jpg", 200, 300, "Clothes des"),
//                new Product("Giay", 0, 10, "https://www.thespruce.com/thmb/zgCEkzj4shXVo4G_20yzPplwU_I=/6558x0/filters:no_upscale():max_bytes(150000):strip_icc()/wash-new-clothes-before-wearing-2146345-03-999483b3d51a435ba53c8d9ef5c2d5c4.jpg", 200, 300, "Clothes des")
//                );
//        WishRepository repo2 = new WishRepository(getActivity().getApplication());
//        repo2.insertItem(new Wish(0, 1, 1));
//        repo2.insertItem(new Wish(0, 2, 2));
//        repo2.insertItem(new Wish(0, 3, 3));
//        repo2.insertItem(new Wish(0, 4, 4));

        //----------------------------------- get userId from session----------------------------
        int userId = 1; //get user id
        //
        binding = FragmentWishListBinding.inflate(inflater, container, false);
        wishList = new WishRepository(getActivity().getApplication()).getWishList(userId);
        View root = binding.getRoot();
        if(wishList.size()==0){
            setEmptyView();
        }
        wishListRec = binding.wishListRec;
        wishListAdapters = new WishListAdapters(getActivity(), wishList, getActivity().getApplication(),this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        wishListRec.setAdapter(wishListAdapters);
        wishListRec.setLayoutManager(linearLayoutManager);
        CheckBox cbAll = binding.cbAll;
        cbAll.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                setRecyclerViewItemsChecked(cbAll.isChecked());
            }
        });

        TextView btnBuy = binding.btnBuy;
        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Integer> wishIdList = (ArrayList<Integer>)GetWishIdsFromCheckedBox();
                if(wishIdList.size()==0)
                {
                    Toast.makeText(getActivity(), "No products selected yet!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(getActivity(), OrderActivity.class);
                intent.putIntegerArrayListExtra("wishIdList", wishIdList);
                startActivity(intent,null);
            }
        });
        return root;
    }

    @Override
    public void changeTotalInWL(float total) {
        binding.totalWL.setText("$ "+total);
    }

    @Override
    public void changeCheckBoxState(){
        binding.cbAll.setChecked(false);
    }

    @Override
    public void setEmptyView() {
        View root = binding.getRoot();
        root.findViewById(R.id.empty).setVisibility(View.VISIBLE);
        root.findViewById(R.id.notEmpty).setVisibility(View.GONE);
    }

    public void setRecyclerViewItemsChecked(boolean checked) {
        for (int i = 0; i < wishListRec.getChildCount(); i++) {
            View view = wishListRec.getChildAt(i);
            RecyclerView.ViewHolder viewHolder = wishListRec.getChildViewHolder(view);
            CheckBox checkBox = viewHolder.itemView.findViewById(R.id.cbCheckWL);
            if(checkBox.isClickable())
                checkBox.setChecked(checked);
        }
    }

    public List<Integer> GetWishIdsFromCheckedBox()
    {
        List<Integer> wishIds = new ArrayList<Integer>();
        for (int i = 0; i < wishListRec.getChildCount(); i++) {
            View view = wishListRec.getChildAt(i);
            RecyclerView.ViewHolder viewHolder = wishListRec.getChildViewHolder(view);
            CheckBox checkBox = viewHolder.itemView.findViewById(R.id.cbCheckWL);
            if(checkBox.isChecked())
            {
                TextView text = viewHolder.itemView.findViewById(R.id.wishId);
                String idStr = text.getText().toString();
                if(idStr != null && idStr != "")
                {
                    int wishId = Integer.parseInt(idStr);
                    wishIds.add(wishId);
                }
            }
        }
        return wishIds;
    }

}