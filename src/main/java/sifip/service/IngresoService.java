package sifip.service;

import sifip.dao.IngresoDAO;
import sifip.model.Ingreso;

import java.time.LocalDate;

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

        Ingreso ingreso = new Ingreso(monto, descripcion, periodicidad, fecha, idUsuario);
        return ingresoDAO.save(ingreso);
    }
} 
