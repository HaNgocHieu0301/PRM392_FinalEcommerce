package com.example.prm392_finalecommerce;

import static DAOs.ProductRoomDatabase.getDatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.util.List;

import DAOs.IProductDAO;
import Repository.ProductRepository;
import Repository.WishRepository;
import models.Product;
import models.Wish;

public class ViewProductDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_product_detail);

//        ProductRepository repo = new ProductRepository(getApplication());
//        repo.insertProducts(
//                new Product("Candy", 0, 10, "", 10, 100, "Candy des"),
//                new Product("Laptop", 0, 20, "", 30, 200, "Laptop des"),
//                new Product("Clothes", 0, 10, "", 200, 300, "Clothes des"),
//                new Product("Giay", 0, 10, "", 200, 300, "Clothes des")
//                );
//        WishRepository repo2 = new WishRepository(getApplication(), 0);
//        repo2.insertItem(new Wish(0, 0, 1));
//        repo2.insertItem(new Wish(0, 1, 2));
//        repo2.insertItem(new Wish(0, 2, 3));
//        repo2.insertItem(new Wish(0, 3, 4));
//        Bundle extra = getIntent().getExtras();
//        Product product = (Product) extra.getSerializable("productDetail");

//        Product product = new Product();
////        product.setImgUrl("https://cdn2.cellphones.com.vn/x358,webp,q100/media/catalog/product/1/4/14_1_12_1.jpg");
//        product.productName = "Iphone 13 PRM";
//        product.price = (2399);
//        product.discount = (5);
//        product.description = ("iPhone 13\n" +
//                "\n" +
//                "Hệ thống camera kép tiên tiến nhất từng có trên iPhone. Chip A15 Bionic thần tốc. Bước nhảy vọt về\n" +
//                "\n" +
//                "thời lượng pin. Thiết kế bền bỉ. Mạng 5G siêu nhanh.1 Cùng với màn hình Super Retina XDR sáng hơn.\n" +
//                "\n" +
//                "\n" +
//                "\n" +
//                "Tính năng nổi bật\n" +
//                "\n" +
//                "• Màn hình Super Retina XDR 6.1 inch2\n" +
//                "\n" +
//                "• Chế độ Điện Ảnh làm tăng thêm độ sâu trường ảnh nông và tự động thay đổi tiêu cự trong video\n" +
//                "\n" +
//                "• Hệ thống camera kép tiên tiến với camera Wide và Ultra Wide 12MP; Phong Cách Nhiếp Ảnh, HDR thông\n" +
//                "\n" +
//                "minh thế hệ 4, chế độ Ban Đêm, khả năng quay video HDR Dolby Vision 4K\n" +
//                "\n" +
//                "• Camera trước TrueDepth 12MP với chế độ Ban Đêm và khả năng quay video HDR Dolby Vision 4K" +
//                "\n" +
//                "• Chip A15 Bionic cho hiệu năng thần tốc\n" +
//                "\n" +
//                "• Thời gian xem video lên đến 19 giờ3\n" +
//                "\n" +
//                "• Thiết kế bền bỉ với Ceramic Shield\n" +
//                "\n" +
//                "• Khả năng chống nước đạt chuẩn IP68 đứng đầu thị trường4\n" +
//                "\n" +
//                "• Mạng 5G cho tốc độ tải xuống siêu nhanh, xem video và nghe nhạc trực tuyến chất lượng cao1\n" +
//                "\n" +
//                "• iOS 15 tích hợp nhiều tính năng mới cho phép bạn làm được nhiều việc hơn bao giờ hết với iPhone5");
//        product.unitsInStock = (100);
//
//        IProductDAO productDao =getDatabase(ViewProductDetail.this).productDAO();
////        productDao.insert(product);
//        List<Product> items = productDao.getAll();
//        Log.d("number", items.size()+"");

//        ImageView img = findViewById((R.id.productImage));
//        TextView txtName = findViewById(R.id.productName);
//        TextView txtPrice = findViewById(R.id.productPrice);
//        TextView txtDiscount = findViewById(R.id.productDiscount);
//        TextView txtDescription = findViewById(R.id.productDescription);
//        NumberPicker npQuantity = findViewById(R.id.wishQuantity);
//        TextView txtUnitsInStock = findViewById(R.id.productUnitsInStock);
//
//        //        img.setImageResource(R.drawable.image1);
//        img.setImageURI(Uri.parse("https://cdn2.cellphones.com.vn/x358,webp,q100/media/catalog/product/1/4/14_1_12_1.jpg"));
//        txtName.setText(product.getProductName());
//        txtPrice.setText("$" + product.getPrice());
//        txtDiscount.setText("Discount: " + product.getDiscount() +"%");
//        txtDescription.setText(product.getDescription());
//        npQuantity.setMinValue(1);
//        npQuantity.setMaxValue(product.getUnitsInStock());
//        txtUnitsInStock.setText(" "+product.getUnitsInStock()+" ");
    }
}