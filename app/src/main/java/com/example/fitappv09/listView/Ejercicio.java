package com.example.fitappv09.listView;

public class Ejercicio {

    private int idEjercicio;
    private String nombreEjercicio;
    private int series;
    private int repeticiones;
    private float peso;

    public Ejercicio(){ }

    public Ejercicio(int idEjercicio, String nombreEjercicio, int series, int repeticiones, float peso) {
        this.idEjercicio = idEjercicio;
        this.nombreEjercicio = nombreEjercicio;
        this.series = series;
        this.repeticiones = repeticiones;
        this.peso = peso;
    }

    public int getIdEjercicio() {
        return idEjercicio;
    }

    public void setIdEjercicio(int idEjercicio) {
        this.idEjercicio = idEjercicio;
    }

    public String getNombreEjercicio() {
        return nombreEjercicio;
    }

    public void setNombreEjercicio(String nombreEjercicio) {
        this.nombreEjercicio = nombreEjercicio;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public int getRepeticiones() {
        return repeticiones;
    }

    public void setRepeticiones(int repeticiones) {
        this.repeticiones = repeticiones;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }
}
