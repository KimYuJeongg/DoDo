package com.sample.dodo.data;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

public interface ToDoDao {

    @Query("SELECT * FROM todo")
    List<ToDo> getAll();

    @Query("SELECT * FROM todo ORDER BY deadline")
    List<ToDo> getAllByDate();

    @Query("SELECT * FROM todo ORDER BY importance DESC")
    List<ToDo> getAllByImportance();

    @Insert
    void insertAll(ToDo... toDos);

    @Delete
    void delete(ToDo toDo);

}
