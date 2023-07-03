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
    @ColumnInfo(name = "CategoryName")
    public String categoryName;
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
}
