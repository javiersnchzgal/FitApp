package com.example.fitappv09.database;

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

    public DBHelper(@Nullable Context context, SQLiteDatabase.CursorFactory factory) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
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
                "(Id_usuario INTEGER PRIMARY KEY autoincrement,"+
                "Nombre_usuario VARCHAR(30)," +
                "Email VARCHAR(40) UNIQUE NOT NULL,"+
                "Contraseña VARCHAR(20))");

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
                "Comentario VARCHAR(300)," +
                "Id_entrenamiento INTEGER REFERENCES Entrenamiento(Id_entrenamiento))");
    }


    //SENTENCIAS USUARIO
    public long insertarUsuario(String username, String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valor = new ContentValues();
        valor.put("Nombre_usuario", username);
        valor.put("Email", email);
        valor.put("Contraseña", password);
        long idUsuario = db.insert(TABLA_USUARIO,null,valor);
        return  idUsuario;
    }

    public Boolean comprobarNombreUsuario(String username){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+ TABLA_USUARIO +" WHERE Nombre_usuario=?", new String[]{username});
        if (cursor.getCount() > 0){
            return  true;
        } else {
            return false;
        }
    }


    public Boolean comprobarLogin(String username, String password){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLA_USUARIO + " WHERE Nombre_usuario=? AND Contraseña=?", new String[]{username, password});
        int login = cursor.getCount();
        if(login > 0){
            return true;
        }else{
            return false;
        }
    }

    //SENTENCIAS ENTRENAMIENTOS
    public long insertarEntrenamiento(String fecha, String nombreUsuario) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valor = new ContentValues();
        valor.put("Fecha", fecha);
        valor.put("Nombre_usuario", nombreUsuario);
        long idEntrenamiento = db.insert(TABLA_ENTRENAMIENTO,null,valor);
        return idEntrenamiento;
    }

    public Cursor consultarIdEntrenamiento(String fecha, String nombreUsuario) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT Id_Entrenamiento FROM " + TABLA_ENTRENAMIENTO + " WHERE Fecha=? AND Nombre_usuario=?", new String[]{fecha, nombreUsuario});
    }

    public Boolean comprobarEntrenamiento(String fecha, String nombreUsuario){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLA_ENTRENAMIENTO+ " WHERE Fecha=? AND Nombre_usuario=?", new String[]{fecha, nombreUsuario});
        int entrenamiento = cursor.getCount();
        if(entrenamiento > 0){
            return true;
        }else{
            return false;
        }
    }


    //SENTENCIAS EJERCICIOS
    public long insertarEjercicio(String nombreEjercicio, int series, int repeticiones, float peso, String comentario, int idEntrenamiento) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valor = new ContentValues();
        valor.put("Nombre_ejercicio", nombreEjercicio);
        valor.put("Series", series);
        valor.put("Repeticiones", repeticiones);
        valor.put("Peso", peso);
        //valor.put("Comentario", comentario);
        valor.put("Id_entrenamiento", idEntrenamiento);
        long id = db.insert(TABLA_EJERCICIO,null,valor);
        return id;
    }

    public Cursor consultarEjerciciosEntrenamiento(String id){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLA_EJERCICIO + " WHERE Id_entrenamiento=?", new String[]{id});
    }

}
