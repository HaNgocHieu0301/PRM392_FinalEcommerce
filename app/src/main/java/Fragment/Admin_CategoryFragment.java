package Fragment;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.prm392_finalecommerce.CreateCategory;
import com.example.prm392_finalecommerce.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import Adapter.CategoryAdapter;
import DAOs.CategoryRoomDatabase;
import models.Category;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Admin_CategoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class Admin_CategoryFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_admin__category, container, false);

        FloatingActionButton addNewCategory = (FloatingActionButton) view.findViewById(R.id.buttonAddNewCategory);
        addNewCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CreateCategory.class);
                startActivity(intent);
            }
        });
        RecyclerView recyclerView = view.findViewById(R.id.categoryRecycler);
        List<Category> categoryList = new ArrayList<>(CategoryRoomDatabase.getDatabase(getActivity().getApplication()).categoryDAO().getAll());
        CategoryAdapter categoryAdapter = new CategoryAdapter(categoryList, getActivity().getApplication());
        recyclerView.setAdapter(categoryAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }
}