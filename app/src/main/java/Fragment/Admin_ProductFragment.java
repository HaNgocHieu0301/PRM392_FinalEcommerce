package Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.prm392_finalecommerce.CreateProduct;
import Adapter.ProductAdapter;
import com.example.prm392_finalecommerce.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import DAOs.ProductRoomDatabase;
import models.Product;
public class Admin_ProductFragment extends Fragment implements ProductAdapter.onClickListener {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_admin__product, container, false);

        FloatingActionButton addNewProduct = (FloatingActionButton) view.findViewById(R.id.buttonAddNewProduct);
        addNewProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CreateProduct.class);
                startActivity(intent);
            }
        });
        RecyclerView recyclerView = view.findViewById(R.id.productRecycler);
        List<Product> productList = new ArrayList<>(ProductRoomDatabase.getDatabase(getActivity().getApplication()).productDAO().getAll());
        ProductAdapter productAdapter = new ProductAdapter(productList, getActivity().getApplication(), this::viewProductDetail);
        recyclerView.setAdapter(productAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    @Override
    public void viewProductDetail(int productId) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment newFragment = new Admin_ProductDetailFragment(productId);
        fragmentTransaction.replace(R.id.llConatainer, newFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}