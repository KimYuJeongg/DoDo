package com.sample.dodo;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.sample.dodo.data.ToDo;
import com.sample.dodo.data.ToDoDatabase;

public class AddActivity extends AppCompatActivity {

    ImageButton backButton;
    Button completeButton, importance1, importance2, importance3;
    EditText toDoInput;
    LinearLayout alarmLayout;
    ImageView timeIcon;
    Context context;

    ToDoDatabase db;

    boolean[] isSetImportance = {false, false, false};
    int importance = 0;

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
        importance1 = findViewById(R.id.importance1);
        importance2 = findViewById(R.id.importance2);
        importance3 = findViewById(R.id.importance3);

        db = ToDoDatabase.getInstance(this);
    }

    public void completeWriting(View view) {
        if (!(toDoInput.getText().toString().isEmpty())) {
            new Thread(() -> {
                ToDo todo = new ToDo(toDoInput.getText().toString(), importance);
                db.toDoDao().insert(todo);
                finish();
            }).start();
        } else {
            Toast.makeText(getApplicationContext(), "내용을 입력해주세요.", Toast.LENGTH_SHORT).show();
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
//        TODO: DatePicker Dialog
//        TODO: alarmLayout set visibility to visible
//        TODO: set icon color
        DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

            }
        }, 2022, 1, 25);

        timeIcon.setColorFilter(R.color.state_blue);
        alarmLayout.setVisibility(View.VISIBLE);
    }

    public void setAlarm(View view) {
//        TODO: TimePicker Dialog
//        TODO: set icon color
    }
}