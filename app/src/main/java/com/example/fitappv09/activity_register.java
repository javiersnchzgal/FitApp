package com.example.fitappv09;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fitappv09.database.DBHelper;

public class activity_register extends AppCompatActivity implements View.OnClickListener {

    //Componentes Activity
    Button btnRegistro;
    TextView linkLogin;
    EditText username, email, password1, password2;

    Intent intent;
    DBHelper db;
    Context context;
    long idUsuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DBHelper(this);
        context = getApplicationContext();

        username = findViewById(R.id.txtInicioNombreUsuario);
        email = findViewById(R.id.txtRegistroCorreo);
        password1 = findViewById(R.id.txtInicioContrasena);
        password2 = findViewById(R.id.txtRegistroConfirmarContrasena);

        btnRegistro = findViewById(R.id.btnRegistro);
        btnRegistro.setOnClickListener(this);

        linkLogin = findViewById(R.id.linkCambioIniciaSesion);
        linkLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnRegistro:
                //Guardar valores campos de texto en variables
                String nombreUsuario = username.getText().toString();
                String correo = email.getText().toString();
                String contraseña = password1.getText().toString();
                String confirmacionContraseña = password2.getText().toString();


                if (nombreUsuario.equals("") || correo.equals("") || contraseña.equals("") || confirmacionContraseña.equals("")){
                    Toast.makeText(context,"Para registrarse debe completar todos los campos", Toast.LENGTH_SHORT).show();
                } else {
                    boolean existeUsuario = db.comprobarNombreUsuario(nombreUsuario);
                    if (existeUsuario == false) {
                        if (contraseña.equals(confirmacionContraseña)) {
                            idUsuario = db.insertarUsuario(nombreUsuario, correo, contraseña);
                            if(idUsuario == -1){
                                Toast.makeText(context,"Se ha producido un error al registrarse", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context,"Se ha registrado correctamente", Toast.LENGTH_SHORT).show();
                            }

                            //Borrar Campos
                            username.setText("");
                            email.setText("");
                            password1.setText("");
                            password2.setText("");

                            //Cambiar de activity
                            intent = new Intent(this, activity_login.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(context,"Las dos contraseñas introducidas deben ser iguales", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(context, "El usuario introducido ya existe", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.linkCambioIniciaSesion:
                intent = new Intent(this, activity_login.class);
                startActivity(intent);
                break;
        }
    }

}
