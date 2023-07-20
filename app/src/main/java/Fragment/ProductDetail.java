package Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.prm392_finalecommerce.R;
import com.example.prm392_finalecommerce.databinding.FragmentHomeBinding;
import com.example.prm392_finalecommerce.databinding.FragmentProductDetailBinding;
import com.google.android.material.navigation.NavigationView;

import Helper.InputFilterMinMax;
import Repository.ProductRepository;
import Repository.WishRepository;
import models.Product;
import models.Wish;

public class ProductDetail extends NavHelperFragment{
    private int productId;
    public ProductDetail(int productId) {
        this.productId = productId;
    }
    private FragmentProductDetailBinding binding;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProductDetailBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        super.NavigationFragments();
        ProductRepository productRepository = new ProductRepository(getActivity().getApplication());
        Product product = productRepository.getProductById(productId);
        binding.productName.setText(product.productName);
        Glide.with(getContext())
                .load(product.image)
                .into(binding.productImage);
        binding.productPrice.setText("$ "+product.price);
        binding.productDiscount.setText("Discount: "+product.discount+"%");
        binding.productDescription.setText(product.description);
        binding.productUnitsInStock.setText(" "+product.unitsInStock +" available");
//        binding.wishQuantity.setMinValue(1);
//        binding.wishQuantity.setValue(1);
//        binding.wishQuantity.setMaxValue(product.unitsInStock);
//        binding.wishQuantity.setTextSize(35);
        binding.txtWishQuantity.setFilters(new InputFilter[]{ new InputFilterMinMax(1, product.unitsInStock)});
        binding.txtWishQuantity.setText("1");
        binding.btnMinus.setOnClickListener(view->{
            int newVal = Integer.parseInt(binding.txtWishQuantity.getText().toString()) - 1;
            if(newVal>0)
            {
                binding.txtWishQuantity.setText(newVal+"");
            }
        });
        binding.btnAdd.setOnClickListener(view->{
            int newVal = Integer.parseInt(binding.txtWishQuantity.getText().toString()) + 1;
            if(newVal<=product.unitsInStock)
            {
                binding.txtWishQuantity.setText(newVal+"");
            }
        });

        binding.btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ---------------------------- GET USER ID --------------------------------
                int userId = 1;
                WishRepository wishRepository = new WishRepository(getActivity().getApplication());
                Wish wish = wishRepository.getWishByUserIdAndProductId(userId, productId);
                int wishQuantity = Integer.parseInt(binding.txtWishQuantity.getText().toString());
                if(wish!=null){
                    wish.quantity += wishQuantity;
                    wishRepository.updateItem(wish);
                }
                else {
                    wish = new Wish(userId, productId, wishQuantity);
                    wishRepository.insertItem(wish);
                }
                Toast.makeText(getContext(), "Added "+wishQuantity+" to cart !", Toast.LENGTH_SHORT).show();
            }
        });
        binding.btnBuyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ---------------------GO TO ORDER-----------------------------------
            }
        });
        return root;
    }

}