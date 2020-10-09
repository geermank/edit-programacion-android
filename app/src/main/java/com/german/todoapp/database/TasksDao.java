package com.german.todoapp.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.german.todoapp.models.Task;

import java.util.List;

@Dao
public interface TasksDao {

    @Query("SELECT * FROM tasks")
    List<Task> getAll();

    @Query("SELECT * FROM tasks WHERE id = :id")
    Task getById(long id);

    @Insert
    void insert(Task task);
}
