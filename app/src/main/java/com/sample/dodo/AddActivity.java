package com.sample.dodo;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.sample.dodo.data.ToDo;
import com.sample.dodo.data.ToDoDatabase;

import java.util.Calendar;

public class AddActivity extends AppCompatActivity {

    ImageButton backButton;
    Button completeButton, importance1, importance2, importance3;
    EditText toDoInput;
    LinearLayout alarmLayout;
    ImageView timeIcon, alarmIcon;
    Context context;

    ToDoDatabase db;

    boolean[] isSetImportance = {false, false, false};
    int importance = 0;
    String deadline = null;
    String alarmTime = null;
    int currentState = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        context = getApplicationContext();
        backButton = findViewById(R.id.backButton);
        completeButton = findViewById(R.id.completeButton);
        toDoInput = findViewById(R.id.toDoInput);
        alarmLayout = findViewById(R.id.alarmLayout);
        timeIcon = findViewById(R.id.timeIcon);
        alarmIcon = findViewById(R.id.alarmIcon);
        importance1 = findViewById(R.id.importance1);
        importance2 = findViewById(R.id.importance2);
        importance3 = findViewById(R.id.importance3);

        db = ToDoDatabase.getInstance(this);
    }

    public void completeWriting(View view) {
        if (!(toDoInput.getText().toString().trim().isEmpty())) {
            new Thread(() -> {
                ToDo todo = new ToDo(toDoInput.getText().toString(), importance, deadline, alarmTime, currentState);
                db.toDoDao().insert(todo);
                finish();
            }).start();
        } else {
            Toast.makeText(context, "내용을 입력해주세요.", Toast.LENGTH_SHORT).show();
        }
    }

//    TODO: simplify
    public void setImportance(View view) {
        if (view.getId() == R.id.importance1) {
            if(!isSetImportance[0]) {
                importance1.setTextColor(ContextCompat.getColor(context, R.color.importance1));
                importance2.setTextColor(ContextCompat.getColor(context, R.color.gray_2));
                importance3.setTextColor(ContextCompat.getColor(context, R.color.gray_2));

                isSetImportance[0] = true;
                isSetImportance[1] = false;
                isSetImportance[2] = false;

                importance = 1;
            } else {
                importance1.setTextColor(ContextCompat.getColor(context, R.color.gray_2));
                isSetImportance[0] = false;
                importance = 0;
            }

        } else if (view.getId() == R.id.importance2) {
            if(!isSetImportance[1]) {
                importance2.setTextColor(ContextCompat.getColor(context, R.color.importance2));
                importance1.setTextColor(ContextCompat.getColor(context, R.color.gray_2));
                importance3.setTextColor(ContextCompat.getColor(context, R.color.gray_2));

                isSetImportance[1] = true;
                isSetImportance[0] = false;
                isSetImportance[2] = false;

                importance = 2;
            } else {
                importance2.setTextColor(ContextCompat.getColor(context, R.color.gray_2));
                isSetImportance[1] = false;
                importance = 0;
            }

        } else if (view.getId() == R.id.importance3) {
            if(!isSetImportance[2]) {
                importance3.setTextColor(ContextCompat.getColor(context, R.color.importance3));
                importance1.setTextColor(ContextCompat.getColor(context, R.color.gray_2));
                importance2.setTextColor(ContextCompat.getColor(context, R.color.gray_2));

                isSetImportance[2] = true;
                isSetImportance[0] = false;
                isSetImportance[1] = false;

                importance = 3;
            } else {
                importance3.setTextColor(ContextCompat.getColor(context, R.color.gray_2));
                isSetImportance[2] = false;
                importance = 0;
            }

        }
    }

    public void setDeadLine(View view) {
        Calendar calendar = Calendar.getInstance();

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                deadline = year + "." + (month + 1) + "." + dayOfMonth;
                timeIcon.setColorFilter(getResources().getColor(R.color.state_blue));
                alarmLayout.setVisibility(View.VISIBLE);
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    public void setAlarm(View view) {
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, android.R.style.Theme_Holo_Light_Dialog_NoActionBar, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                alarmTime = i + ":" + i1;
                alarmIcon.setColorFilter(getResources().getColor(R.color.state_blue));
            }
        }, 0, 0, false);
        timePickerDialog.getWindow().setBackgroundDrawableResource(R.color.transparent);
        timePickerDialog.show();
    }
}