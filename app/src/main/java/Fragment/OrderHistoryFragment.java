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
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.example.prm392_finalecommerce.R;
import com.example.prm392_finalecommerce.databinding.FragmentHomeBinding;
import com.example.prm392_finalecommerce.databinding.FragmentOrderHistoryBinding;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Adapter.OrderHistoryAdapters;
import Adapter.PopularAdapters;
import Repository.OrderRepository;
import Repository.ProductRepository;
import models.Order;
import models.Product;


public class OrderHistoryFragment extends Fragment {
    private FragmentOrderHistoryBinding binding;
    RecyclerView rec;
    List<Order> orderList;
    OrderHistoryAdapters adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentOrderHistoryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        //userID
        int userId = 1;
        rec = binding.orderHistoryRec;
        OrderRepository repo = new OrderRepository(getActivity().getApplication());
        //--init data--
//        Order o = new Order();
//        o.userId = 1;
//        o.total = 123.12;
//        o.statusId = 0;
//        o.shippedDate = "21/07/2023";
//        o.orderDate = "21/07/2023";
//        o.shipAddress = "address";
//        o.shipPhone = "123";
//        o.shipName = "khanh";
//        repo.InsertOrder(o);
//        o.statusId = 3;
//        repo.InsertOrder(o);

        orderList = new ArrayList<>(repo.GetOrdersByUserId(userId));
        adapter = new OrderHistoryAdapters(getContext(), orderList, getActivity().getApplication());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rec.setAdapter(adapter);
        rec.setLayoutManager(linearLayoutManager);
        return root;
    }
}