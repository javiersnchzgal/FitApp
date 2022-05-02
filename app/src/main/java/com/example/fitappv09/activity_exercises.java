package com.example.fitappv09;


import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import java.util.Calendar;

public class activity_exercises extends AppCompatActivity implements View.OnClickListener {

    ImageButton btnMenu, btnTemporizador, btnRetroceder;
    Button  btnGuardarEjercicio;
    EditText txtFecha, txtEjercicio, txtSeries, txtRepeticiones, txtPeso, txtComentarios;
    Intent intent;
    DBHelper db;
    Context context;


    @SuppressLint("WrongViewCast")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);

        db = new DBHelper(this);
        context = getApplicationContext();

        //Campos de texto
        txtFecha = findViewById(R.id.txtFecha);
        txtFecha.setOnClickListener(this);

        txtEjercicio = findViewById(R.id.txtEjercicio);
        txtSeries = findViewById(R.id.txtSeries);
        txtRepeticiones = findViewById(R.id.txtRepeticiones);
        txtPeso = findViewById(R.id.txtPeso);
        txtComentarios = findViewById(R.id.txtComentarios);

        //Botones
        btnMenu = findViewById(R.id.btnMenuExercises);

        btnTemporizador = findViewById(R.id.btnTemporizadorExercises);

        btnGuardarEjercicio = findViewById(R.id.btnGuardarEjercicio);

        btnRetroceder = findViewById(R.id.btnRetocederExercises);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.txtFecha:
                showDatePickerDialog();
                break;
        }
    }

    private void showDatePickerDialog(){
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                final String selectedDate = day + " / " + month + " / " + year;
                txtFecha.setText(selectedDate);
            }
        });
        newFragment.show(getSupportFragmentManager(),"datePicker");
    }
}

