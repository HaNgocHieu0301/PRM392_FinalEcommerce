package DAOs;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import models.Product;

@Database(entities = {Product.class}, version = 2)
public abstract class ProductRoomDatabase extends RoomDatabase {
    private static ProductRoomDatabase INSTANCE;
    public abstract IProductDAO productDAO();
    public static ProductRoomDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), ProductRoomDatabase.class,"Product")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
    public static void destroyInstance() {
        INSTANCE = null;
    }
}
