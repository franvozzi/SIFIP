package sifip.service;

import sifip.dao.GastoDAO;
import sifip.model.Gasto;

import java.time.LocalDate;

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

        Gasto gasto = new Gasto(monto, descripcion, categoria, fecha, idUsuario);
        return gastoDAO.save(gasto);
    }
} 
