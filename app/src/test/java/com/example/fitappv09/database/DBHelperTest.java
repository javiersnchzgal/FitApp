package com.example.fitappv09.database;

import static org.junit.Assert.*;


import android.content.Context;

import org.junit.Test;

public abstract class DBHelperTest extends Context {

    @Test
    public void insertarUsuario() {
        DBHelper db = new DBHelper(this);
        long idUsuario = db.insertarUsuario("javiersg", "javier@example.com", "1234");
        assertEquals(2, idUsuario);
    }

    @Test
    public void comprobarNombreUsuario() {
        DBHelper db = new DBHelper(this);
        boolean existeUsuario = db.comprobarNombreUsuario("admin");
        assertTrue(existeUsuario);
    }

    @Test
    public void comprobarLogin() {
        DBHelper db = new DBHelper(this);
        boolean loginCorrecto = db.comprobarLogin("admin", "1");
        assertTrue(loginCorrecto);
    }

    @Test
    public void insertarEntrenamiento() {
        DBHelper db = new DBHelper(this);
        long idEntrenamiento = db.insertarEntrenamiento("2022-01-01", "admin");
        assertEquals(2, idEntrenamiento);
    }

    @Test
    public void comprobarEntrenamiento() {
        DBHelper db = new DBHelper(this);
        boolean user = db.comprobarLogin("admin", "1");
        assertTrue(user);
    }

    @Test
    public void insertarEjercicio() {
        DBHelper db = new DBHelper(this);
        long idEjercicio = db.insertarEjercicio("Dominadas", 3, 10, 5.0F,"Texto prueba", 1);
        assertEquals(2, idEjercicio);
    }

    @Test
    public void eliminarEjercicio() {
        DBHelper db = new DBHelper(this);
        int ejercicioEliminado = db.eliminarEjercicio("2");
        assertEquals(1, ejercicioEliminado);
    }

    @Test
    public void editarEjercicio() {
        DBHelper db = new DBHelper(this);
        int ejercicioEditado = db.editarEjercicio("1", "Dominadas", 3 ,10, 10.0F);
        assertEquals(1, ejercicioEditado);
    }

}