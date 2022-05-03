package com.example.fitappv09;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;

import androidx.appcompat.app.AppCompatActivity;

public class activity_calendar extends AppCompatActivity implements View.OnClickListener {

    Button btnCrearEntrenamiento;
    CalendarView calendarView;

    DBHelper db;
    Context context;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        db = new DBHelper(this);
        context = getApplicationContext();

        btnCrearEntrenamiento = findViewById(R.id.btnCrearEntrenamiento);
        btnCrearEntrenamiento.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btnCrearEntrenamiento:
                    calendarView.getDate();

                    intent = new Intent(this, activity_exercises.class);
                    startActivity(intent);
                break;

        }
    }
}
