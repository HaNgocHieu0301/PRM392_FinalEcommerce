package DAOs;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import models.Category;

@Database(entities = {Category.class}, version = 1)
public abstract class CategoryRoomDatabase extends RoomDatabase {
    private static CategoryRoomDatabase INSTANCE;
    public abstract ICategoryDAO categoryDAO();
    public static CategoryRoomDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), CategoryRoomDatabase.class, "Category")
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
