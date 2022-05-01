package com.example.fitappv09;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class activity_login extends AppCompatActivity implements View.OnClickListener{

    Button btnLogin;
    TextView linkRegistro;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.btnIniciar);
        btnLogin.setOnClickListener(this);

        linkRegistro = findViewById(R.id.linkCambioIniciaSesion);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnIniciar:
                intent = new Intent(this, MainActivity.class); /* modificar con la activity del calendario*/
                startActivity(intent);
                break;
            case R.id.linkCambioIniciaSesion:
                intent = new Intent(this, activity_register.class);
                startActivity(intent);
                break;
        }
    }
}
