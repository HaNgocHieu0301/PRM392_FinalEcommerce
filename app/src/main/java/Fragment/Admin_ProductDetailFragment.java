package Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.prm392_finalecommerce.CreateProduct;
import com.example.prm392_finalecommerce.R;

import Helper.InputFilterMinMax;
import Repository.ProductRepository;
import Repository.WishRepository;
import models.Product;
import models.Wish;

public class Admin_ProductDetailFragment extends Fragment {
    private int productId;
    public Admin_ProductDetailFragment(int productId){
        this.productId = productId;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_admin__product_detail, container, false);

        ProductRepository productRepository = new ProductRepository(getActivity().getApplication());
        Product product = productRepository.getProductById(productId);

        ImageView imageView = view.findViewById(R.id.productImage);
        Glide.with(getContext())
                .load(product.image)
                .into(imageView);
        TextView productName = view.findViewById(R.id.productName);
        productName.setText(product.productName);
        TextView productPrice = view.findViewById(R.id.productPrice);
        productPrice.setText("$ "+product.price);
        TextView productDiscount = view.findViewById(R.id.productDiscount);
        productDiscount.setText("Discount: "+product.discount+"%");
        TextView productDescription = view.findViewById(R.id.productDescription);
        productDescription.setText(product.description);
        TextView productUnitsInStock = view.findViewById(R.id.productUnitsInStock);
        productUnitsInStock.setText(" "+product.unitsInStock +" available");
        TextView btnEdit = view.findViewById(R.id.btnEdit);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CreateProduct.class);
                intent.putExtra("productId", productId);
                startActivity(intent);
            }
        });
        TextView btnHideProduct = view.findViewById(R.id.btnHideProduct);
        btnHideProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ---------------------GO TO ORDER-----------------------------------
            }
        });
        return view;
    }
}