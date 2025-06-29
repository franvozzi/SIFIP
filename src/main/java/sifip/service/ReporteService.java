package sifip.service;

import sifip.model.Ingreso;
import sifip.model.Gasto;
import sifip.model.Reporte;
import sifip.dao.IngresoDAO;
import sifip.dao.GastoDAO;

import java.util.List;

/**
 * Clase ReporteService
 * 
 * Implementa la lógica de negocio para la generación de reportes financieros.
 * Esta clase coordina múltiples DAOs para consolidar información de ingresos
 * y gastos, creando reportes completos para los usuarios.
 * 
 * Características principales:
 * - Calcula balances totales (ingresos - gastos)
 * - Agrupa gastos por categoría para análisis
 * - Proporciona una vista consolidada de la situación financiera
 * - Implementa el patrón Service Layer para separar lógica de negocio
 * 
 * Esta clase es fundamental para el análisis financiero y la toma de decisiones
 * basada en datos históricos de transacciones.
 */
public class ReporteService {
    // Dependencias inyectadas - DAOs para acceso a datos
    private IngresoDAO ingresoDAO;  // Para obtener datos de ingresos
    private GastoDAO gastoDAO;      // Para obtener datos de gastos

    /**
     * Constructor del servicio de reportes
     * 
     * @param ingresoDAO DAO para acceso a datos de ingresos
     * @param gastoDAO DAO para acceso a datos de gastos
     */
    public ReporteService(IngresoDAO ingresoDAO, GastoDAO gastoDAO) {
        this.ingresoDAO = ingresoDAO;
        this.gastoDAO = gastoDAO;
    }

    /**
     * Genera un reporte financiero completo para un usuario específico
     * 
     * Este método:
     * 1. Obtiene todos los ingresos del usuario
     * 2. Obtiene todos los gastos del usuario
     * 3. Calcula el balance total
     * 4. Agrupa gastos por categoría
     * 5. Retorna un objeto Reporte con toda la información consolidada
     * 
     * @param idUsuario ID del usuario para el cual generar el reporte
     * @return Reporte Objeto con el balance y gastos por categoría
     * @throws Exception Si ocurre un error al obtener los datos
     */
    public Reporte generarReporte(int idUsuario) throws Exception {
        // Creación de un nuevo reporte vacío
        Reporte reporte = new Reporte();

        // Obtención de datos desde la base de datos
        List<Ingreso> ingresos = ingresoDAO.obtenerPorUsuario(idUsuario);
        List<Gasto> gastos = gastoDAO.obtenerPorUsuario(idUsuario);
        
        // Cálculo del balance total
        // Los ingresos tienen valores positivos, los gastos valores negativos
        for (Ingreso ingreso : ingresos) {
            reporte.actualizarBalance(ingreso.calcular());
        }
        for (Gasto gasto : gastos) {
            reporte.actualizarBalance(gasto.calcular());
        }

        // Generación del reporte de gastos por categoría
        // Esto permite análisis detallado de patrones de gasto
        for (Gasto gasto : gastos) {
            reporte.agregarGasto(gasto.getCategoria(), gasto.getMonto());
        }

        return reporte;
    }
}