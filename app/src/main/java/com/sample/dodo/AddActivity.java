package com.sample.dodo;

import android.os.Bundle;
import android.view.View;

import android.widget.Toast;
import android.widget.EditText;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {


    EditText userInput;
    Button displayInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);


        userInput = (EditText) findViewById(R.id.toDoContents);
        displayInput = (Button) findViewById(R.id.completeButton);

        displayInput.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), userInput.getText(), Toast.LENGTH_SHORT).show();

            }

        });
    }
}

















