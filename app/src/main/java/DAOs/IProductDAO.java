package DAOs;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.DeleteTable;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import models.Product;

@Dao
public interface IProductDAO {
    @Query("SELECT * FROM Product")
    List<Product> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
	long[] insert(Product... products);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertAProduct(Product product);
    @Update
    void update(Product... products);
    @Delete
    void delete(Product... products);

    @Query("DELETE FROM Product")
    void removeAll();

    @Query("SELECT * FROM Product WHERE productId = :pId")
    Product getProductById(int pId);

}
