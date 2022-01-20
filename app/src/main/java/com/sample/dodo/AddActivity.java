package com.sample.dodo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {

    ImageButton backButton;
    Button completeButton;
    EditText toDoInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        backButton = findViewById(R.id.backButton);
        completeButton = findViewById(R.id.completeButton);
        toDoInput = findViewById(R.id.toDoInput);

    }

    public void completeWriting(View view) {
        if (toDoInput.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "내용을 입력해주세요.", Toast.LENGTH_SHORT).show();
        } else {

        }
    }

    public void setDeadLine(View view) {
//        TODO: DatePicker Dialog
//        TODO: alarmLayout set visibility to visible
//        TODO: set icon color
    }

    public void setAlarm(View view) {
//        TODO: TimePicker Dialog
//        TODO: set icon color
    }
}

















