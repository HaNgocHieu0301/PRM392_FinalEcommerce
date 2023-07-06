package DAOs;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import models.Category;
import models.Product;

@Dao
public interface ICategoryDAO {
    @Query("SELECT * FROM Category")
    List<Category> getAll();
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Category... categories);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertACategory(Category category);
    @Update
    void update(Category... categories);
    @Delete
    void delete(Category... categories);
}
