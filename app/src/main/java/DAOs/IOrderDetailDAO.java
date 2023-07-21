package DAOs;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import models.OrderDetail;
@Dao
public interface IOrderDetailDAO {
    @Query("SELECT * FROM OrderDetail")
    List<OrderDetail> getAll();
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insert(OrderDetail... orderDetails);
    @Query("SELECT * FROM OrderDetail WHERE orderId = :orderId")
    List<OrderDetail> getListByOrderId(int orderId);
}
