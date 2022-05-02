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

public class activity_login extends AppCompatActivity implements View.OnClickListener{

    EditText txtInicioNombreUsuario, txtInicioContrasena;
    Button btnLogin;
    TextView linkRegistro;

    Intent intent;
    DBHelper db;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new DBHelper(this);
        context = getApplicationContext();

        txtInicioNombreUsuario = findViewById(R.id.txtInicioNombreUsuario);
        txtInicioContrasena = findViewById(R.id.txtInicioContrasena);

        btnLogin = findViewById(R.id.btnIniciar);
        btnLogin.setOnClickListener(this);

        linkRegistro = findViewById(R.id.linkCambioIniciaSesion);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnIniciar:
                String username = txtInicioNombreUsuario.getText().toString();
                String password = txtInicioContrasena.getText().toString();

                Boolean permitirAcceso = db.comprobarLogin(username, password);

                if (permitirAcceso == true) {
                    txtInicioNombreUsuario.setText("");
                    txtInicioContrasena.setText("");
                    Toast.makeText(context,"Se ha iniciado sesión con éxito", Toast.LENGTH_SHORT).show();
                    intent = new Intent(activity_login.this, activity_calendar.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(context,"El usuario o la contraseña introducidos son erróneos", Toast.LENGTH_SHORT).show();
                }


                break;
            case R.id.linkCambioIniciaSesion:
                intent = new Intent(this, activity_register.class);
                startActivity(intent);
                break;
        }
    }
}
