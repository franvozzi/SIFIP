package sifip.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Clase Reporte
 * 
 * Representa un reporte financiero que consolida información de ingresos, gastos
 * y activos para un usuario específico. Esta clase es fundamental para el análisis
 * financiero y la toma de decisiones.
 * 
 * Características del reporte:
 * - Calcula el balance total (ingresos - gastos)
 * - Agrupa gastos por categoría para análisis
 * - Proporciona una vista consolidada de la situación financiera
 * 
 * Esta clase implementa el patrón Value Object, ya que representa
 * un conjunto de datos calculados sin comportamiento complejo.
 */
public class Reporte {
    // Atributos del reporte
    private float balance;                           // Balance total (ingresos - gastos)
    private Map<String, Float> gastosPorCategoria;  // Gastos agrupados por categoría

    /**
     * Constructor de la clase Reporte
     * Inicializa un reporte vacío con balance en cero y mapa de gastos vacío
     */
    public Reporte() {
        this.balance = 0.0f;
        this.gastosPorCategoria = new HashMap<>();
    }

    /**
     * Actualiza el balance total del reporte
     * Se usa para acumular ingresos (valores positivos) y gastos (valores negativos)
     * 
     * @param monto Cantidad a sumar al balance (puede ser positiva o negativa)
     */
    public void actualizarBalance(float monto) {
        this.balance += monto;
    }

    /**
     * Agrega un gasto a la categoría correspondiente
     * Si la categoría no existe, la crea. Si ya existe, suma el monto.
     * 
     * @param categoria Categoría del gasto (ej: "Alimentación", "Transporte")
     * @param monto Cantidad del gasto
     */
    public void agregarGasto(String categoria, float monto) {
        // getOrDefault devuelve 0.0f si la categoría no existe, o el valor actual si existe
        gastosPorCategoria.put(categoria, gastosPorCategoria.getOrDefault(categoria, 0.0f) + monto);
    }

    // ========== GETTERS Y SETTERS ==========
    
    /**
     * Obtiene el balance total del reporte
     * @return float - El balance total (ingresos - gastos)
     */
    public float getBalance() { return balance; }
    
    /**
     * Establece el balance total del reporte
     * @param balance El nuevo balance total
     */
    public void setBalance(float balance) { this.balance = balance; }
    
    /**
     * Obtiene el mapa de gastos por categoría
     * @return Map<String, Float> - Mapa con categorías como claves y montos como valores
     */
    public Map<String, Float> getGastosPorCategoria() { return gastosPorCategoria; }
    
    /**
     * Establece el mapa de gastos por categoría
     * @param gastosPorCategoria El nuevo mapa de gastos
     */
    public void setGastosPorCategoria(Map<String, Float> gastosPorCategoria) { 
        this.gastosPorCategoria = gastosPorCategoria; 
    }

    /**
     * Representación en texto del reporte
     * Útil para mostrar el reporte en consola o logs
     * 
     * @return String - Representación del reporte con balance y gastos por categoría
     */
    @Override
    public String toString() {
        return "Reporte{balance=" + balance + ", gastosPorCategoria=" + gastosPorCategoria + "}";
    }
}