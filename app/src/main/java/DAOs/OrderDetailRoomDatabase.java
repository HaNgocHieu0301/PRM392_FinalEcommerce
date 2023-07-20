package DAOs;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import models.OrderDetail;

@Database(entities = {OrderDetail.class}, version = 1)
public abstract class OrderDetailRoomDatabase extends RoomDatabase {
    private static OrderDetailRoomDatabase INSTANCE;
    public abstract IOrderDetailDAO orderDetailDAO();
    public static OrderDetailRoomDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), OrderDetailRoomDatabase.class,"OrderDetail")
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