package DAOs;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import models.Product;

@Dao
public interface IProductDAO {
    @Query("SELECT * FROM Product")
    public List<Product> getAll();
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(Product... products);
    @Update
    public void update(Product... products);
    @Delete
    public void delete(Product... products);
}
