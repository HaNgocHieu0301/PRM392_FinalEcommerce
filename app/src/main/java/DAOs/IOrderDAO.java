package DAOs;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import models.Product;

@Dao
public interface IOrderDAO {
    @Query("SELECT * FROM `Order`")
    List<Product> getAll();
}
