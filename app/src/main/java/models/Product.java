package models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "Product")
public class Product {
    @PrimaryKey(autoGenerate = true)
    @NotNull
    private int productId;
    @NotNull
    private String productName;
    @NotNull
    private int categoryId;
    @NotNull
    private double price;
    private double discount;
    @NotNull
    private int unitsInStock;
    @NotNull
    private String description;

    @NotNull
    public int getProductId() {
        return productId;
    }

    public void setProductId(@NotNull int productId) {
        this.productId = productId;
    }

    @NotNull
    public String getProductName() {
        return productName;
    }

    public void setProductName(@NotNull String productName) {
        this.productName = productName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getUnitsInStock() {
        return unitsInStock;
    }

    public void setUnitsInStock(int unitsInStock) {
        this.unitsInStock = unitsInStock;
    }

    @NotNull
    public String getDescription() {
        return description;
    }

    public void setDescription(@NotNull String description) {
        this.description = description;
    }
}
