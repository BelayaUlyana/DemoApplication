package com.mycompany.testtask.usersList.Database;

import android.content.Context;
import android.util.Log;

import androidx.room.Room;

public class DatabaseCreator {

    private static volatile AppDatabase INSTANCE;

    public synchronized static AppDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "database")
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        Log.e("Tag", "INSTANCE" + INSTANCE);
        return INSTANCE;
    }
}
