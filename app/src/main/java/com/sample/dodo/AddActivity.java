package com.sample.dodo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


import android.widget.DatePicker;
import android.app.DatePickerDialog;
import java.util.Calendar;



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


/// 데이트피커 다이얼로그
//    Calendar c = Calendar.getInstance();
//        int mYear = c.get(Calendar.YEAR);
//        int mMonth = c.get(Calendar.MONTH);
//        int mDay = c.get(Calendar.DAY_OF_MONTH);

//        DatePickerDialog DatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                Date.setText(dayOfMonth+"/" + (month+1) + "/" + year);
//            }
//        }, mYear,mMonth, mDay);

//        Date.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void OnClick(View view) {
//               if(Date.IsClickable()) {
//                    DatePickerDialog.show();
//                }

//    }








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

















