package Fragment;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import com.example.prm392_finalecommerce.R;
import com.example.prm392_finalecommerce.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;
import Adapter.PopularAdapters;
import Repository.CategoryRepository;
import Repository.ProductRepository;
import Repository.UserRepository;
import models.Category;
import models.Product;
import models.User;

public class HomeFragment extends Fragment implements PopularAdapters.onClickListener {
    private FragmentHomeBinding binding;
    RecyclerView popularRec;
    List<Product> productList;
    PopularAdapters popularAdapters;
    SearchView searchView;
    MenuItem menuItem;
    AutoCompleteTextView autoCompleteTxt;
    ArrayAdapter<String> adapterItems;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        //products
        popularRec = binding.popRec;
        ProductRepository repo = new ProductRepository(getActivity().getApplication());
        productList = new ArrayList<>(repo.getAllProducts());
//        repo.insertProducts(
//                new Product("Candy", 1, 10, "https://cdn.tgdd.vn/Products/Images/42/251192/iphone-14-pro-max-den-thumb-600x600.jpg", 10, 100, "Candy des"),
//                new Product("Laptop", 2, 20, "https://cdn.tgdd.vn/Products/Images/42/251192/iphone-14-pro-max-den-thumb-600x600.jpg", 30, 200, "Laptop des"),
//                new Product("Clothes", 3, 10, "https://cdn.tgdd.vn/Products/Images/42/251192/iphone-14-pro-max-den-thumb-600x600.jpg", 200, 300, "Clothes des"),
//                new Product("Giay", 3, 10, "https://cdn.tgdd.vn/Products/Images/42/251192/iphone-14-pro-max-den-thumb-600x600.jpg", 200, 300, "Clothes des")
//                );
        popularAdapters = new PopularAdapters(getActivity(), productList, getActivity().getApplication(), this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        popularRec.setAdapter(popularAdapters);
        popularRec.setLayoutManager(linearLayoutManager);
        //categories
        CategoryRepository categoryRepository = new CategoryRepository(getActivity().getApplication());

//        categoryRepository.insertCategories(
//                new Category("Technology"),
//                new Category("Candy"),
//                new Category("Fashion")
//                );

//        UserRepository userRepository = new UserRepository(getActivity().getApplication());
//        userRepository.insertUser(
//                new User("maintt","123", "Mai", "Nguyen",null, false, "Tran Nhat Duat, Kim Tan, Lao Cai", "0294482384", null, null, false)
//        );
        List<Category> categories = categoryRepository.getAllCategories();
        List<String> strCategories = new ArrayList<>();
        strCategories.add("All");
        for(Category cate : categories) {
            strCategories.add(cate.getCategoryName());
        }
        autoCompleteTxt = binding.autoCompleteTxt;
        adapterItems = new ArrayAdapter<>(getContext(), R.layout.category_item, strCategories);
        autoCompleteTxt.setAdapter(adapterItems);
        autoCompleteTxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                ProductRepository repo = new ProductRepository(getActivity().getApplication());
                if(item.equals("All"))
                    productList = new ArrayList<>(repo.getAllProducts());
                else
                    productList = new ArrayList<>(repo.getAllProductsByCategoryName(getActivity().getApplication(), item));
                popularAdapters = new PopularAdapters(getActivity(), productList, getActivity().getApplication(), HomeFragment.this::viewProductDetail);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                popularRec.setAdapter(popularAdapters);
                popularRec.setLayoutManager(linearLayoutManager);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu, menu);
        menuItem = menu.findItem(R.id.action_search);

        searchView = (SearchView)MenuItemCompat.getActionView(menuItem);
        searchView.setIconified(true);

        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                popularAdapters.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                popularAdapters.getFilter().filter(newText);
                return false;
            }
        });
        super.onCreateOptionsMenu(menu,inflater);
    }

    @Override
    public void viewProductDetail(int productId) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment newFragment = new ProductDetail(productId);
        fragmentTransaction.replace(R.id.nav_host_fragment_content_main, newFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}