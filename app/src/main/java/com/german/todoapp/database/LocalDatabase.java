package com.german.todoapp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.german.todoapp.models.Task;

@Database(entities = {Task.class}, version = 1)
public abstract class LocalDatabase extends RoomDatabase {

    private static LocalDatabase sInstance;

    public static LocalDatabase getInstance(Context context) {
        if (sInstance == null) {
            sInstance = Room.databaseBuilder(context, LocalDatabase.class, "LocalDatabase")
                    .allowMainThreadQueries() // <- testing
                    .build();
        }
        return sInstance;
    }

    public abstract TasksDao getTasksDao();
}
