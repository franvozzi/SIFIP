package sifip.service;

import sifip.dao.ActivoDAO;
import sifip.model.Activo;

import java.time.LocalDate;
import java.util.Date;

public class ActivoService {

    private final ActivoDAO activoDAO;

    public ActivoService() {
        this.activoDAO = new ActivoDAO();
    }

    public boolean registrarActivo(String nombre, String tipo, double cantidad, double precioInicial, double precioActual, LocalDate fecha, int idUsuario) {
        if (nombre == null || nombre.isBlank() || tipo == null || tipo.isBlank()
            || cantidad <= 0 || precioInicial < 0 || precioActual < 0 || fecha == null) {
            System.out.println("Datos inválidos. Verifique e intente nuevamente.");
            return false;
        }

        try {
            Activo activo = new Activo(nombre, tipo, (float)cantidad, (float)precioInicial, Date.from(fecha.atStartOfDay().toInstant(java.time.ZoneOffset.UTC)), idUsuario);
            activo.setPrecioActual((float)precioActual);
            activoDAO.guardar(activo);
            return true;
        } catch (Exception e) {
            System.out.println("Error al registrar activo: " + e.getMessage());
            return false;
        }
    }
} 