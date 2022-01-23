package com.sample.dodo.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Date;
import java.sql.Time;

@Entity
public class ToDo {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "todo")
    @NonNull
    public String todo;

    @ColumnInfo(name = "deadline")
    public Date deadline;

    @ColumnInfo(name = "importance")
    public int importance;

    @ColumnInfo(name = "currentState")
    public String currentState;

    @ColumnInfo(name = "alarm_time")
    public Time alarmTime;

    public String getContents() {
        return todo;
    }

    public Date getDeadline() {
        return deadline;
    }

    public int getImportance() {
        return importance;
    }

    public String getCurrentState() {
        return  currentState;
    }

}
