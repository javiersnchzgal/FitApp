package com.example.fitappv09.listView;

public class Entrenamiento {

    private int idEntrenamiento;
    private String fecha;
    private String nombreUsuario;

    public Entrenamiento() {}

    public Entrenamiento(int idEntrenamiento, String fecha, String nombreUsuario) {
        this.idEntrenamiento = idEntrenamiento;
        this.fecha = fecha;
        this.nombreUsuario = nombreUsuario;
    }

    public int getIdEntrenamiento() {
        return idEntrenamiento;
    }

    public void setIdEntrenamiento(int idEntrenamiento) {
        this.idEntrenamiento = idEntrenamiento;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
}
