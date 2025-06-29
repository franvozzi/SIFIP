package sifip.model;

import java.util.Date;

/**
 * Clase abstracta Transaccion
 * 
 * Esta es la clase base para todas las transacciones financieras en el sistema.
 * Implementa el patrón Template Method, definiendo la estructura común que
 * deben tener todas las transacciones (Ingresos y Gastos).
 * 
 * Características principales:
 * - Clase abstracta que no puede ser instanciada directamente
 * - Define atributos comunes a todas las transacciones
 * - Declara el método abstracto calcular() que debe ser implementado por las subclases
 * - Proporciona getters y setters para todos los atributos
 * 
 * Esta clase es fundamental en el diseño del sistema ya que:
 * 1. Permite polimorfismo entre Ingresos y Gastos
 * 2. Facilita el procesamiento uniforme de transacciones
 * 3. Reduce la duplicación de código
 */
public abstract class Transaccion {
    // Atributos protegidos accesibles por las subclases
    protected int id;              // Identificador único de la transacción
    protected float monto;         // Cantidad monetaria de la transacción
    protected String descripcion;  // Descripción o concepto de la transacción
    protected Date fecha;          // Fecha en que ocurrió la transacción
    protected int idUsuario;       // ID del usuario propietario de la transacción

    /**
     * Constructor de la clase Transaccion
     * 
     * @param monto Cantidad monetaria de la transacción
     * @param descripcion Descripción o concepto de la transacción
     * @param fecha Fecha en que ocurrió la transacción
     * @param idUsuario ID del usuario propietario de la transacción
     */
    public Transaccion(float monto, String descripcion, Date fecha, int idUsuario) {
        this.monto = monto;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.idUsuario = idUsuario;
    }

    /**
     * Método abstracto que debe ser implementado por las subclases
     * Cada tipo de transacción define su propia lógica de cálculo
     * 
     * @return float - El resultado del cálculo específico de la transacción
     */
    public abstract float calcular();

    // ========== GETTERS Y SETTERS ==========
    // Estos métodos permiten acceder y modificar los atributos de la transacción
    
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public float getMonto() { return monto; }
    public void setMonto(float monto) { this.monto = monto; }
    
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    
    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }
    
    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }
}