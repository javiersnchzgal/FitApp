package com.example.fitappv09.listView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fitappv09.R;
import com.example.fitappv09.activity_exercises;
import com.example.fitappv09.database.DBHelper;

import java.util.ArrayList;


public class ListAdapter extends BaseAdapter implements android.widget.ListAdapter {


    private Context context;
    private ArrayList<String> list = new ArrayList<String>();
    private Entrenamiento entrenamiento;


    public Intent intent;
    AppCompatActivity activity = new AppCompatActivity();
    DBHelper db;

    String fecha;
    String username;
    Cursor consultaIdEntrenamiento;
    long idEntrenamiento;


    public ListAdapter(ArrayList<String> list, Context context, Entrenamiento entrenamiento){
        this.list = list;
        this.context = context;
        this.entrenamiento = entrenamiento;
        db = new DBHelper(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;

        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.custom_list,null);
        }

        TextView tvEjercicio = view.findViewById(R.id.tvDatosEjercicio);
        Button btnBorrar = view.findViewById(R.id.btnExerciseDelete);
        Button btnEditar = view.findViewById(R.id.btnExerciseEdit);

        tvEjercicio.setText((CharSequence) getItem(position));
        btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cadenaEjercicio = list.get(position);
                String[] subcadena = cadenaEjercicio.split(" ");
                String idEjercicio = subcadena[0];
                int ejercicioEliminado = db.eliminarEjercicio(idEjercicio);
                if(ejercicioEliminado != 0) {
                    Toast.makeText(context, "Se ha eliminado el ejercicio", Toast.LENGTH_SHORT).show();
                    list.remove(position);
                    notifyDataSetChanged();
                } else {
                    Toast.makeText(context, "No se ha podido eliminar el ejercicio", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fecha = entrenamiento.getFecha();
                username = entrenamiento.getNombreUsuario();

                boolean existeEntrenamiento = db.comprobarEntrenamiento(fecha, username);
                if (existeEntrenamiento) {
                    consultaIdEntrenamiento = db.consultarIdEntrenamiento(fecha, username);
                    if (consultaIdEntrenamiento != null && consultaIdEntrenamiento.moveToFirst()) {
                        do {
                            idEntrenamiento = consultaIdEntrenamiento.getInt(0);
                        } while (consultaIdEntrenamiento.moveToNext());

                        //Obtener id Ejercicio
                        String cadenaEjercicio = list.get(position);
                        String[] subcadena = cadenaEjercicio.split(" ");
                        String idEjercicio = subcadena[0];

                        //Enviar dato a la pantalla de ejercicios
                        intent = new Intent(context, activity_exercises.class);
                        intent.putExtra("username", entrenamiento.getNombreUsuario());
                        intent.putExtra("fecha", entrenamiento.getFecha());
                        intent.putExtra("idEntrenamiento", idEntrenamiento);
                        intent.putExtra("idEjercicio", idEjercicio );
                        intent.putExtra("editar", true);
                        context.startActivity(intent);
                    }
                }
            }
        });


        return view;
    }
}
