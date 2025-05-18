package sifip.model;

import java.time.LocalDate;

public class Ingreso {
    private int id;
    private double monto;
    private String descripcion;
    private String periodicidad;
    private LocalDate fecha;
    private int idUsuario;

    public Ingreso(double monto, String descripcion, String periodicidad, LocalDate fecha, int idUsuario) {
        this.monto = monto;
        this.descripcion = descripcion;
        this.periodicidad = periodicidad;
        this.fecha = fecha;
        this.idUsuario = idUsuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMonto() {
        return monto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getPeriodicidad() {
        return periodicidad;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public int getIdUsuario() {
        return idUsuario;
    }
} 
