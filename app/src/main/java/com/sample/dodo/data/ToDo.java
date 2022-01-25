package com.sample.dodo.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Time;

@Entity
public class ToDo {

    public ToDo(String contents) {
        this.contents = contents;
    }

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "contents")
    @NonNull
    public String contents;

    @ColumnInfo(name = "deadline")
    public Long deadline;

    @ColumnInfo(name = "importance")
    public int importance;

    @ColumnInfo(name = "currentState")
    public int currentState;

    @ColumnInfo(name = "alarm_time")
    public Long alarmTime;

    public String getContents() {
        return contents;
    }

    public Long getDeadline() {
        return deadline;
    }

    public int getImportance() {
        return importance;
    }

    public int getCurrentState() {
        return currentState;
    }

    public void setContents(@NonNull String contents) {
        this.contents = contents;
    }

    public void setCurrentState(int currentState) {
        this.currentState = currentState;
    }
}
