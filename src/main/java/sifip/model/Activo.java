package sifip.model;

import java.time.LocalDate;

public class Activo {
    private int id;
    private String nombre;
    private String tipo;
    private double cantidad;
    private double precioInicial;
    private double precioActual;
    private LocalDate fecha;
    private int idUsuario;

    public Activo(String nombre, String tipo, double cantidad, double precioInicial, double precioActual, LocalDate fecha, int idUsuario) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.precioInicial = precioInicial;
        this.precioActual = precioActual;
        this.fecha = fecha;
        this.idUsuario = idUsuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public double getCantidad() {
        return cantidad;
    }

    public double getPrecioInicial() {
        return precioInicial;
    }

    public double getPrecioActual() {
        return precioActual;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public int getIdUsuario() {
        return idUsuario;
    }
} 
