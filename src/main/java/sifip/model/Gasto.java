package sifip.model;

import java.util.Date;

/**
 * Clase Gasto
 * 
 * Representa una transacción de gasto (salida de dinero) en el sistema financiero.
 * Extiende de la clase abstracta Transaccion e implementa la interfaz Calculable.
 * 
 * Características específicas de los gastos:
 * - Tienen un valor negativo en el balance
 * - Pueden ser categorizados (alimentación, transporte, etc.)
 * - Representan salidas de dinero como compras, servicios, etc.
 * 
 * Esta clase implementa el patrón Template Method heredando de Transaccion
 * y proporciona su propia implementación del método calcular().
 */
public class Gasto extends Transaccion {
    // Atributo específico de los gastos
    private String categoria;  // Categoría del gasto (alimentación, transporte, etc.)

    /**
     * Constructor de la clase Gasto
     * 
     * @param monto Cantidad monetaria del gasto
     * @param descripcion Descripción del gasto (ej: "Compra supermercado", "Gasolina")
     * @param categoria Categoría del gasto (ej: "Alimentación", "Transporte")
     * @param fecha Fecha en que se realizó el gasto
     * @param idUsuario ID del usuario que realizó el gasto
     */
    public Gasto(float monto, String descripcion, String categoria, Date fecha, int idUsuario) {
        // Llamada al constructor de la clase padre (Transaccion)
        super(monto, descripcion, fecha, idUsuario);
        this.categoria = categoria;
    }

    /**
     * Implementación del método calcular() para gastos
     * Los gastos siempre tienen un valor negativo en el balance
     * 
     * @return float - El monto del gasto como valor negativo
     */
    @Override
    public float calcular() {
        return -monto; // Retorna el monto como valor negativo para gastos
    }

    // ========== GETTERS Y SETTERS ==========
    
    /**
     * Obtiene la categoría del gasto
     * @return String - La categoría del gasto
     */
    public String getCategoria() { return categoria; }
    
    /**
     * Establece la categoría del gasto
     * @param categoria La nueva categoría del gasto
     */
    public void setCategoria(String categoria) { this.categoria = categoria; }
}