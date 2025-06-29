package sifip.service;

import sifip.dao.GastoDAO;
import sifip.model.Gasto;

import java.time.LocalDate;
import java.util.Date;

public class GastoService {

    private final GastoDAO gastoDAO;

    public GastoService() {
        this.gastoDAO = new GastoDAO();
    }

    public boolean registrarGasto(double monto, String descripcion, String categoria, LocalDate fecha, int idUsuario) {
        if (monto <= 0 || descripcion == null || descripcion.isBlank() || categoria == null || categoria.isBlank() || fecha == null) {
            System.out.println("Datos inválidos. Verifique e intente nuevamente.");
            return false;
        }

        try {
            Gasto gasto = new Gasto((float)monto, descripcion, categoria, Date.from(fecha.atStartOfDay().toInstant(java.time.ZoneOffset.UTC)), idUsuario);
            gastoDAO.guardar(gasto);
            return true;
        } catch (Exception e) {
            System.out.println("Error al registrar gasto: " + e.getMessage());
            return false;
        }
    }
} 
