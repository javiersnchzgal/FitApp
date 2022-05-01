package com.example.fitappv09;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    Intent intent;
    Button buttonLogin;
    Button buttonRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonLogin = findViewById(R.id.btnLogin);
        buttonLogin.setOnClickListener(this);

        buttonRegister = findViewById(R.id.btnSignUp);
        buttonRegister.setOnClickListener(this);
    }

    public void onClick(View view){

        switch (view.getId()) {
            case R.id.btnLogin:
                intent = new Intent(MainActivity.this, activity_login.class);
                startActivity(intent);
                break;
            case R.id.btnSignUp:
                intent = new Intent(MainActivity.this, activity_register.class);
                startActivity(intent);
                break;
        }
    }

}