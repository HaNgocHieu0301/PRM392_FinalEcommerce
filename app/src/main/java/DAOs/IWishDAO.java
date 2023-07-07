package DAOs;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import models.Wish;
@Dao
public interface IWishDAO {
    @Query("SELECT * FROM Wish WHERE UserId = :userId")
    List<Wish> getWishList(int userId);
    @Query("SELECT * FROM Wish")
    List<Wish> getAll();
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Wish... wishes);
    @Update
    void update(Wish... wishes);
    @Delete
    void delete(Wish... wish);
}
