package DAOs;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import models.Order;

@Database(entities = {Order.class}, version = 1)
public abstract class OrderRoomDatabase extends RoomDatabase {
    private static OrderRoomDatabase INSTANCE;
    public abstract IOrderDAO orderDAO();
    public static OrderRoomDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), OrderRoomDatabase.class,"Order")
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