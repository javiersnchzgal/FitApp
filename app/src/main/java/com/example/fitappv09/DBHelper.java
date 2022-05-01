package com.example.fitappv09;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;


public class DBHelper extends SQLiteOpenHelper {

    static String DATABASE_NAME = "DBFitApp";
    static  int DATABASE_VERSION = 1;
    static String TABLA_USUARIO = "Usuario";
    static String TABLA_ENTRENAMIENTO = "Entrenamiento";
    static String TABLA_EJERCICIO = "Ejercicio";

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Crear tabla usuario
        db.execSQL("CREATE TABLE " + TABLA_USUARIO +
                "(Nombre_usuario VARCHAR(30)," +
                "Email VARCHAR(40) UNIQUE NOT NULL,"+
                "Contraseña VARCHAR(20)," +
                "CONSTRAINT usuario_pk PRIMARY KEY(Nombre_usuario))");

        //Crear tabla entrenamiento
        db.execSQL("CREATE TABLE " + TABLA_ENTRENAMIENTO +
                "(Id_Entrenamiento INTEGER CONSTRAINT entrenamiento_pk PRIMARY KEY autoincrement,"+
                "Fecha DATE," +
                "Nombre_usuario VARCHAR(30) REFERENCES Usuario(Nombre_usuario))");

        //Crear tabla ejercicio
        db.execSQL("CREATE TABLE " + TABLA_EJERCICIO +
                "(Id_ejercicio INTEGER CONSTRAINT ejercicio_pk PRIMARY KEY autoincrement," +
                "Nombre_ejercicio VARCHAR(30)," +
                "Series INTEGER," +
                "Repeticiones INTEGER," +
                "Peso NUMBER(5,2)," +
                "Comentarios TEXT," +
                "Id_entrenamiento INTEGER REFERENCES Entrenamiento(Id_entrenamiento))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + TABLA_USUARIO);
        db.execSQL("DROP TABLE " + TABLA_ENTRENAMIENTO);
        db.execSQL("DROP TABLE " + TABLA_EJERCICIO);

        //Crear tabla usuario
        db.execSQL("CREATE TABLE " + TABLA_USUARIO +
                "(Nombre_usuario VARCHAR(30)," +
                "Email VARCHAR(40) UNIQUE NOT NULL,"+
                "Contraseña VARCHAR(20)," +
                "CONSTRAINT usuario_pk PRIMARY KEY(Nombre_usuario))");

        //Crear tabla entrenamiento
        db.execSQL("CREATE TABLE " + TABLA_ENTRENAMIENTO +
                "(Id_Entrenamiento INTEGER CONSTRAINT entrenamiento_pk PRIMARY KEY autoincrement,"+
                "Fecha DATE," +
                "Nombre_usuario VARCHAR(30) REFERENCES Usuario(Nombre_usuario))");

        //Crear tabla ejercicio
        db.execSQL("CREATE TABLE " + TABLA_EJERCICIO +
                "(Id_ejercicio INTEGER CONSTRAINT ejercicio_pk PRIMARY KEY autoincrement," +
                "Nombre_ejercicio VARCHAR(30)," +
                "Series INTEGER," +
                "Repeticiones INTEGER," +
                "Peso NUMBER(5,2)," +
                "Comentarios TEXT," +
                "Id_entrenamiento INTEGER REFERENCES Entrenamiento(Id_entrenamiento))");
    }

    public long insertarUsuario(String username, String email, String password){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues valor = new ContentValues();
        valor.put("Nombre_usuario", username);
        valor.put("Email", email);
        valor.put("Contraseña", password);
        return db.insert(TABLA_USUARIO,null,valor);
    }

    public Cursor getUsuario(String username){
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT * FROM "+ TABLA_USUARIO +" WHERE Nombre_usuario="+ username, null);
    }

}
