package Fragment;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.example.prm392_finalecommerce.R;
import com.example.prm392_finalecommerce.databinding.FragmentHomeBinding;
import com.example.prm392_finalecommerce.databinding.FragmentWishListBinding;

import java.util.ArrayList;
import java.util.List;

import Adapter.PopularAdapters;
import Adapter.WishListAdapters;
import DAOs.ProductRoomDatabase;
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
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_wish_list, container, false);
        //----------------------------------- get userId from session----------------------------
        int userId = 0; //get user id
        //
        binding = FragmentWishListBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        wishListRec = binding.wishListRec;
        wishList = new WishRepository(getActivity().getApplication()).getWishList(userId);
        wishListAdapters = new WishListAdapters(getActivity(), wishList, getActivity().getApplication(),this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        wishListRec.setAdapter(wishListAdapters);
        wishListRec.setLayoutManager(linearLayoutManager);

        CheckBox cbAll = binding.cbAll;
//        cbAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                setRecyclerViewItemsChecked(isChecked);
//            }
//    });

        cbAll.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                setRecyclerViewItemsChecked(cbAll.isChecked());
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

    public void setRecyclerViewItemsChecked(boolean checked) {
        for (int i = 0; i < wishListRec.getChildCount(); i++) {
            View view = wishListRec.getChildAt(i);
            RecyclerView.ViewHolder viewHolder = wishListRec.getChildViewHolder(view);
            CheckBox checkBox = viewHolder.itemView.findViewById(R.id.cbCheckWL);
            if(checkBox.isClickable())
                checkBox.setChecked(checked);
        }
    }

}