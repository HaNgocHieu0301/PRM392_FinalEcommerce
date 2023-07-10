package DAOs;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import models.Wish;

@Database(entities = {Wish.class}, version = 1)
public abstract class WishRoomDatabase extends RoomDatabase {
    private static WishRoomDatabase INSTANCE;
    public abstract IWishDAO wishDAO();
    public static WishRoomDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), WishRoomDatabase.class,"Wish")
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