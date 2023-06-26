package DAOs;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import models.User;
@Dao
public interface IUserDAO {
    @Query("SELECT * FROM User")
    public List<User> getAll();
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(User... users);
    @Update
    public void update(User... users);
    @Delete
    public void delete(User... users);
}
