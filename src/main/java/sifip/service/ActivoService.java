package sifip.service;

import sifip.dao.ActivoDAO;
import sifip.model.Activo;

import java.time.LocalDate;

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

        Activo activo = new Activo(nombre, tipo, cantidad, precioInicial, precioActual, fecha, idUsuario);
        return activoDAO.save(activo);
    }
} 