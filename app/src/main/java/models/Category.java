package models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "Category")
public class Category {
    @PrimaryKey(autoGenerate = true)
    @NotNull
    private int categoryId;
    @NotNull
    private String categoryName;
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @NotNull
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(@NotNull String categoryName) {
        this.categoryName = categoryName;
    }
    public Category() {

    }
    public Category(String categoryName) {
        this.categoryName = categoryName;
    }
}
