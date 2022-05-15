package com.example.fitappv09;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fitappv09.database.DBHelper;

public class activity_exercises extends AppCompatActivity implements View.OnClickListener {

    ImageButton btnMenu, btnTemporizador, btnRetroceder;
    Button  btnGuardarEjercicio;
    TextView lblFecha;
    EditText txtEjercicio, txtSeries, txtRepeticiones, txtPeso, txtComentarios;

    Intent intent;
    DBHelper db;
    Context context;

    String username;
    String fecha;
    String nombreEjercicio;
    int series;
    int repeticiones;
    float peso;
    String comentarios;
    int idEntrenamiento;


    @SuppressLint("WrongViewCast")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);

        db = new DBHelper(this, null);
        context = getApplicationContext();

        lblFecha = findViewById(R.id.lblFecha);

        //Datos de la otra activity
        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        idEntrenamiento = (int) intent.getLongExtra("idEntrenamiento", 0);
        fecha = intent.getStringExtra("fecha");
        lblFecha.setText(fecha);

        //Campos de texto
        txtEjercicio = findViewById(R.id.txtEjercicio);
        txtSeries = findViewById(R.id.txtSeries);
        txtRepeticiones = findViewById(R.id.txtRepeticiones);
        txtPeso = findViewById(R.id.txtPeso);
        txtComentarios = findViewById(R.id.txtComentarios);

        //Botones
        btnMenu = findViewById(R.id.btnMenuExercises);
        btnTemporizador = findViewById(R.id.btnTemporizadorExercises);

        btnGuardarEjercicio = findViewById(R.id.btnGuardarEjercicio);
        btnGuardarEjercicio.setOnClickListener(this);

        btnRetroceder = findViewById(R.id.btnRetocederExercises);
        btnRetroceder.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btnRetocederExercises:
                intent = new Intent(this, activity_workout.class);
                intent.putExtra("username", username);
                intent.putExtra("fecha", fecha);
                startActivity(intent);
                break;
            case R.id.btnGuardarEjercicio:
                nombreEjercicio = String.valueOf(txtEjercicio.getText());
                series = Integer.parseInt(String.valueOf(txtSeries.getText()));
                repeticiones = Integer.parseInt(String.valueOf(txtRepeticiones.getText()));
                peso = Float.parseFloat(String.valueOf(txtPeso.getText()));
                comentarios = String.valueOf(txtComentarios.getText());

                long idEjercicio = db.insertarEjercicio(nombreEjercicio, series, repeticiones, peso, comentarios, idEntrenamiento);
                if (idEjercicio == -1){
                    Toast.makeText(context, "Se ha producido un error al guardar el ejercicio", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "El ejercicio se ha guardado correctamente", Toast.LENGTH_SHORT).show();
                }


                intent = new Intent(this, activity_workout.class);
                startActivity(intent);
        }
    }
}

