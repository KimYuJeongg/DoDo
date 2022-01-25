package com.sample.dodo;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.sample.dodo.data.ToDo;
import com.sample.dodo.data.ToDoDatabase;

public class AddActivity extends AppCompatActivity {

    ImageButton backButton;
    Button completeButton;
    EditText toDoInput;
    LinearLayout alarmLayout;
    ImageView timeIcon;

    ToDoDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        backButton = findViewById(R.id.backButton);
        completeButton = findViewById(R.id.completeButton);
        toDoInput = findViewById(R.id.toDoInput);
        alarmLayout = findViewById(R.id.alarmLayout);
        timeIcon = findViewById(R.id.timeIcon);

        db = ToDoDatabase.getInstance(this);
    }

    public void completeWriting(View view) {
        if (!(toDoInput.getText().toString().isEmpty())) {
            new Thread(() -> {
                ToDo todo = new ToDo(toDoInput.getText().toString());
                db.toDoDao().insert(todo);
            }).start();
        } else {
            Toast.makeText(getApplicationContext(), "내용을 입력해주세요.", Toast.LENGTH_SHORT).show();
        }
    }

    public void setDeadLine(View view) {
//        TODO: DatePicker Dialog
//        TODO: alarmLayout set visibility to visible
//        TODO: set icon color
        timeIcon.setColorFilter(R.color.state_blue);
        alarmLayout.setVisibility(View.VISIBLE);
    }

    public void setAlarm(View view) {
//        TODO: TimePicker Dialog
//        TODO: set icon color
    }
}