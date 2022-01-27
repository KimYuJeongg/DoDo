package com.sample.dodo.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ToDo {
//TODO: Migration
    public ToDo(String contents, int importance, String deadline, String alarmTime, int currentState) {
        this.contents = contents;
        this.importance = importance;
        this.deadline = deadline;
        this.alarmTime = alarmTime;
        this.currentState = currentState;
    }

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "contents")
    @NonNull
    public String contents;

    @ColumnInfo(name = "importance")
    public int importance;

    @ColumnInfo(name = "deadline")
    public String deadline;

    @ColumnInfo(name = "alarm_time")
    public String alarmTime;

    @ColumnInfo(name = "currentState")
    public int currentState;

    public String getContents() {
        return contents;
    }

    public int getImportance() {
        return importance;
    }

    public String getDeadline() {
        return deadline;
    }

    public String getAlarmTime() {
        return alarmTime;
    }

    public int getCurrentState() {
        return currentState;
    }

    public void setContents(@NonNull String contents) {
        this.contents = contents;
    }

    public void setImportance(int importance) {
        this.importance = importance;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public void setAlarmTime(String alarmTime) {
        this.alarmTime = alarmTime;
    }

    public void setCurrentState(int currentState) {
        this.currentState = currentState;
    }

}
