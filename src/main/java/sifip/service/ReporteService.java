package sifip.service;

import sifip.model.Ingreso;
import sifip.model.Gasto;
import sifip.model.Reporte;
import sifip.dao.IngresoDAO;
import sifip.dao.GastoDAO;

import java.util.List;

public class ReporteService {
    private IngresoDAO ingresoDAO;
    private GastoDAO gastoDAO;

    public ReporteService(IngresoDAO ingresoDAO, GastoDAO gastoDAO) {
        this.ingresoDAO = ingresoDAO;
        this.gastoDAO = gastoDAO;
    }

    public Reporte generarReporte(int idUsuario) {
        Reporte reporte = new Reporte();

        // Calcular balance (RF4)
        List<Ingreso> ingresos = ingresoDAO.obtenerIngresosPorUsuario(idUsuario);
        List<Gasto> gastos = gastoDAO.obtenerGastosPorUsuario(idUsuario);
        float totalIngresos = (float) ingresos.stream().mapToDouble(Ingreso::getMonto).sum();
        float totalGastos = (float) gastos.stream().mapToDouble(Gasto::getMonto).sum();
        reporte.setBalance(totalIngresos - totalGastos);

        // Generar reporte de gastos por categoría (RF5)
        for (Gasto gasto : gastos) {
            reporte.agregarGasto(gasto.getCategoria(), (float) gasto.getMonto());
        }

        return reporte;
    }
}