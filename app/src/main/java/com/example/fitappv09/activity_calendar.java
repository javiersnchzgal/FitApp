package com.example.fitappv09;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class activity_calendar extends AppCompatActivity implements View.OnClickListener {

    Button btnCrearEntrenamiento;

    DBHelper db;
    Context context;

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
                //cod
                break;

        }
    }
}
