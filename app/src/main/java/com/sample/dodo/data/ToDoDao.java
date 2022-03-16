package com.sample.dodo.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ToDoDao {

    @Query("SELECT * FROM todo ORDER BY importance DESC, deadline IS NULL ASC")
    LiveData<List<ToDo>> getAll();

    @Query("SELECT * FROM todo ORDER BY CASE WHEN deadline IS NULL THEN 1 ELSE 0 END")
    List<ToDo> getAllByDate();

    @Insert
    void insert(ToDo toDo);

    @Update
    void update(ToDo toDo);

    @Delete
    void delete(ToDo toDo);

}
