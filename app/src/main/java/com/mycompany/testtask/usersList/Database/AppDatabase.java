package com.mycompany.testtask.usersList.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.mycompany.testtask.POJO.User;

@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDao getUserDao();
}
