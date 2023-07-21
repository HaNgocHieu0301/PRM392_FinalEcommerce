package DAOs;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import models.Order;
import models.Product;
@Dao
public interface IOrderDAO {
    @Query("SELECT * FROM `Order`")
    List<Order> getAll();
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Order order);
    @Update
    void update(Order... products);
    @Delete
    void delete(Order... products);
    @Query("SELECT * FROM `Order` WHERE orderId = (SELECT MAX(orderId) FROM `Order`)")
    Order getLastestOrder();

    @Query("SELECT * FROM `Order` WHERE userId = :userId")
    List<Order> getOrderByUserId(int userId);
    
    @Query("SELECT * FROM `Order` WHERE orderId = :orderId")
    Order getOrderById(int orderId);

    @Query("DELETE FROM `Order`")
    void removeAll();
}
