package sifip.model;

import java.util.Date;

/**
 * Clase Ingreso
 * 
 * Representa una transacción de ingreso (entrada de dinero) en el sistema financiero.
 * Extiende de la clase abstracta Transaccion e implementa la interfaz Calculable.
 * 
 * Características específicas de los ingresos:
 * - Tienen un valor positivo en el balance
 * - Pueden tener periodicidad (mensual, semanal, etc.)
 * - Representan fuentes de dinero como salarios, inversiones, etc.
 * 
 * Esta clase implementa el patrón Template Method heredando de Transaccion
 * y proporciona su propia implementación del método calcular().
 */
public class Ingreso extends Transaccion {
    // Atributo específico de los ingresos
    private String periodicidad;  // Frecuencia del ingreso (mensual, semanal, etc.)

    /**
     * Constructor de la clase Ingreso
     * 
     * @param monto Cantidad monetaria del ingreso
     * @param descripcion Descripción del ingreso (ej: "Salario", "Renta")
     * @param periodicidad Frecuencia del ingreso (ej: "Mensual", "Semanal")
     * @param fecha Fecha en que se recibió el ingreso
     * @param idUsuario ID del usuario que recibe el ingreso
     */
    public Ingreso(float monto, String descripcion, String periodicidad, Date fecha, int idUsuario) {
        // Llamada al constructor de la clase padre (Transaccion)
        super(monto, descripcion, fecha, idUsuario);
        this.periodicidad = periodicidad;
    }

    /**
     * Implementación del método calcular() para ingresos
     * Los ingresos siempre tienen un valor positivo en el balance
     * 
     * @return float - El monto del ingreso (valor positivo)
     */
    @Override
    public float calcular() {
        return monto; // Retorna el monto como valor positivo
    }

    // ========== GETTERS Y SETTERS ==========
    
    /**
     * Obtiene la periodicidad del ingreso
     * @return String - La frecuencia del ingreso
     */
    public String getPeriodicidad() { return periodicidad; }
    
    /**
     * Establece la periodicidad del ingreso
     * @param periodicidad La nueva frecuencia del ingreso
     */
    public void setPeriodicidad(String periodicidad) { this.periodicidad = periodicidad; }
}