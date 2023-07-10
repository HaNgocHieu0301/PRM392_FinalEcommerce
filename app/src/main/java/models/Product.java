package models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity
public class Product {
    @PrimaryKey(autoGenerate = true)
    public int productId;
    @ColumnInfo(name = "ProductName")
    public String productName;
    @ColumnInfo(name = "CategoryId")
    public int categoryId;
    @ColumnInfo(name = "Price")
    public double price;
    @ColumnInfo(name = "Image")
    public String image;
    @ColumnInfo(name = "Discount")
    public double discount;
    @ColumnInfo(name = "UnitsInStock")
    public int unitsInStock;

    @ColumnInfo(name = "Description")
    public String description;

    @ColumnInfo(name = "IsShow")
    public boolean isShow;
    public Product() {

    }
    public Product(String productName, int categoryId, double price, String image, double discount, int unitsInStock, String description) {
        this.productName = productName;
        this.categoryId = categoryId;
        this.price = price;
        this.image = image;
        this.discount = discount;
        this.unitsInStock = unitsInStock;
        this.description = description;
        isShow = true;
    }
}
