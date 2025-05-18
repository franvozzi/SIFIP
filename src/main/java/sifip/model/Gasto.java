package sifip.model;

import java.time.LocalDate;

public class Gasto {
    private int id;
    private double monto;
    private String descripcion;
    private String categoria;
    private LocalDate fecha;
    private int idUsuario;

    public Gasto(double monto, String descripcion, String categoria, LocalDate fecha, int idUsuario) {
        this.monto = monto;
        this.descripcion = descripcion;
        this.categoria = categoria;
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

    public String getCategoria() {
        return categoria;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public int getIdUsuario() {
        return idUsuario;
    }
} 
