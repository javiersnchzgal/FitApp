package com.example.fitappv09;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fitappv09.database.DBHelper;
import com.example.fitappv09.listView.Entrenamiento;
import com.example.fitappv09.listView.ListAdapter;

import java.util.ArrayList;


public class activity_workout extends AppCompatActivity implements View.OnClickListener {

    ImageButton btnRetrocederCalendar;
    Button btnAgregarEjercicio;
    TextView tvFechaEntrenamiento;
    ListView listaEjercicios;

    Context context;
    Intent intent;
    DBHelper db;

    String fecha;
    String username;
    long idEntrenamiento;



    Cursor consultaIdEntrenamiento;

    ArrayList<String> ejercicios = new ArrayList<String>();

    @Override
    protected void  onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        context = getApplicationContext();
        db = new DBHelper(this, null);

        btnRetrocederCalendar = findViewById(R.id.btnRetocederCalendar);
        btnRetrocederCalendar.setOnClickListener(this);

        btnAgregarEjercicio = findViewById(R.id.btnAgregarEjercicio);
        btnAgregarEjercicio.setOnClickListener(this);

        tvFechaEntrenamiento = findViewById(R.id.tvFechaEntrenamiento);
        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        fecha = intent.getStringExtra("fecha");

        Entrenamiento e = new Entrenamiento((int) idEntrenamiento,fecha,username);

        tvFechaEntrenamiento.setText(fecha);

        //ListView

        listaEjercicios = findViewById(R.id.listaEjecicios);
        generateListContent();
        listaEjercicios.setAdapter(new ListAdapter(ejercicios, this, e));
    }

    private void generateListContent(){
        Cursor c1 = db.consultarIdEntrenamiento(fecha, username);
        try {
            if( c1 != null && c1.moveToFirst()) {
                ArrayList<Integer> id = new ArrayList<Integer>();
                do {
                    id.add(c1.getInt(0));
                } while (c1.moveToNext());

                Cursor c2 = db.consultarEjerciciosEntrenamiento(String.valueOf(id.get(0)));

                while (c2.moveToNext()){
                    ejercicios.add(c2.getInt(0) + " " + c2.getString(1) + ":  "
                            + c2.getInt(2)  + " x " + c2.getInt(3) + "  "
                            + c2.getFloat(4) + " kg");
                }

                c2.close();
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            c1.close();
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnRetocederCalendar:
                intent = new Intent(this, activity_calendar.class);
                intent.putExtra("username", username);
                startActivity(intent);
                break;
            case R.id.btnAgregarEjercicio:
                boolean existeEntrenamiento = db.comprobarEntrenamiento(fecha, username);
                if (existeEntrenamiento) {
                    consultaIdEntrenamiento = db.consultarIdEntrenamiento(fecha, username);
                    if (consultaIdEntrenamiento != null && consultaIdEntrenamiento.moveToFirst()) {
                        do {
                            idEntrenamiento = consultaIdEntrenamiento.getInt(0);
                        } while (consultaIdEntrenamiento.moveToNext());

                        intent = new Intent(this, activity_exercises.class);
                        intent.putExtra("username", username);
                        intent.putExtra("fecha", fecha);
                        intent.putExtra("idEntrenamiento", idEntrenamiento);
                        startActivity(intent);
                    }
                } else {
                    idEntrenamiento = db.insertarEntrenamiento(fecha, username);
                    if (idEntrenamiento == -1){
                        Toast.makeText(context, "No se ha podido crear el entrenamiento",Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "Se ha creado el entrenamiento con id "+idEntrenamiento,Toast.LENGTH_SHORT).show();
                    }

                    intent = new Intent(this, activity_exercises.class);
                    intent.putExtra("username", username);
                    intent.putExtra("fecha", fecha);
                    intent.putExtra("idEntrenamiento", idEntrenamiento);
                    startActivity(intent);
                }
                break;
        }
    }
}
