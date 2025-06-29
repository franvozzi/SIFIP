package sifip.service;

import sifip.dao.IngresoDAO;
import sifip.model.Ingreso;

import java.time.LocalDate;
import java.util.Date;

public class IngresoService {

    private final IngresoDAO ingresoDAO;

    public IngresoService() {
        this.ingresoDAO = new IngresoDAO();
    }

    public boolean registrarIngreso(double monto, String descripcion, String periodicidad, LocalDate fecha, int idUsuario) {
        if (monto <= 0 || descripcion == null || descripcion.isBlank() || fecha == null) {
            System.out.println("Datos inválidos. Verifique e intente nuevamente.");
            return false;
        }

        try {
            Ingreso ingreso = new Ingreso((float)monto, descripcion, periodicidad, Date.from(fecha.atStartOfDay().toInstant(java.time.ZoneOffset.UTC)), idUsuario);
            ingresoDAO.guardar(ingreso);
            return true;
        } catch (Exception e) {
            System.out.println("Error al registrar ingreso: " + e.getMessage());
            return false;
        }
    }
} 
