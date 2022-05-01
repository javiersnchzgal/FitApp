package com.example.fitappv09;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class activity_register extends AppCompatActivity implements View.OnClickListener {

    //Componentes Activity
    Button btnRegistro;
    TextView linkLogin;
    TextView username;
    TextView email;
    TextView password1;
    TextView password2;

    Intent intent;
    DBHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.txtRegistroNombreUsuario);
        email = findViewById(R.id.txtRegistroCorreo);
        password1 = findViewById(R.id.txtRegistroContrasena);
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
                String nombreUsuario = (String) username.getText();
                String correo = (String) email.getText();
                String contraseña = (String)  password1.getText();
                String confirmacionContraseña = (String) password2.getText();

                if (nombreUsuario=="" || correo=="" || contraseña=="" || confirmacionContraseña==""){
                    Toast.makeText(this,"Para registrarse debe completar todos los campos", Toast.LENGTH_SHORT).show();
                } else {
                    if (password1 != password2) {
                        Toast.makeText(this,"Las contraseñas introducidas no son iguales", Toast.LENGTH_SHORT).show();
                    } else {
                        db.insertarUsuario(nombreUsuario, correo, contraseña);
                        Toast.makeText(this,"Se ha registrado correctamente", Toast.LENGTH_SHORT).show();
                    }
                }

                //Cambiar de activity
                intent = new Intent(this, activity_login.class);
                startActivity(intent);
                break;
            case R.id.linkCambioIniciaSesion:
                intent = new Intent(this, activity_login.class);
                startActivity(intent);
                break;
        }
    }

}
