package com.mycompany.testtask.usersList.Database;

import android.content.Context;
import android.util.Log;

import androidx.room.Room;

public class DatabaseCreator {

    private static volatile AppDatabase INSTANCE;

    public synchronized static AppDatabase getDatabase(Context context) {
        Log.d("Tag", "INSTANCE" + INSTANCE);
        if (INSTANCE == null) {
            Log.d("Tag", "INSTANCE" + INSTANCE);
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "database")
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        Log.d("Tag", "INSTANCE" + INSTANCE);
        return INSTANCE;
    }
}
