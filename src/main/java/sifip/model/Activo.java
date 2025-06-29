package sifip.model;

import java.util.Date;

/**
 * Clase Activo
 * 
 * Representa un activo financiero en el sistema (acciones, bonos, propiedades, etc.).
 * Implementa la interfaz Calculable para poder calcular su rendimiento.
 * 
 * Características de los activos:
 * - Tienen un precio inicial y un precio actual
 * - Se pueden comprar en cantidades específicas
 * - Su valor puede fluctuar con el tiempo
 * - Se pueden categorizar por tipo (acciones, bonos, etc.)
 * 
 * Esta clase es fundamental para el seguimiento de inversiones y
 * el cálculo de rendimientos financieros.
 */
public class Activo implements Calculable {
    // Atributos del activo
    private int id;                    // Identificador único del activo
    private String nombre;             // Nombre del activo (ej: "Apple Inc.", "Tesla")
    private String tipo;               // Tipo de activo (ej: "Acciones", "Bonos", "Propiedad")
    private float cantidad;            // Cantidad de unidades del activo
    private float precioInicial;       // Precio por unidad al momento de la compra
    private float precioActual;        // Precio por unidad actual
    private Date fecha;                // Fecha de registro del activo
    private int idUsuario;             // ID del usuario propietario del activo

    /**
     * Constructor de la clase Activo
     * 
     * @param nombre Nombre del activo
     * @param tipo Tipo de activo (acciones, bonos, etc.)
     * @param cantidad Cantidad de unidades compradas
     * @param precioInicial Precio por unidad al momento de la compra
     * @param fecha Fecha de registro del activo
     * @param idUsuario ID del usuario propietario
     */
    public Activo(String nombre, String tipo, float cantidad, float precioInicial, Date fecha, int idUsuario) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.precioInicial = precioInicial;
        this.precioActual = precioInicial; // Inicialmente igual al precio inicial
        this.fecha = fecha;
        this.idUsuario = idUsuario;
    }

    /**
     * Implementación del método calcular() para activos
     * Calcula el rendimiento total del activo basado en la diferencia
     * entre el precio actual y el precio inicial, multiplicado por la cantidad
     * 
     * @return float - El rendimiento total del activo (ganancia o pérdida)
     */
    @Override
    public float calcular() {
        return (precioActual - precioInicial) * cantidad; // Rendimiento total
    }

    // ========== GETTERS Y SETTERS ==========
    
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    
    public float getCantidad() { return cantidad; }
    public void setCantidad(float cantidad) { this.cantidad = cantidad; }
    
    public float getPrecioInicial() { return precioInicial; }
    public void setPrecioInicial(float precioInicial) { this.precioInicial = precioInicial; }
    
    public float getPrecioActual() { return precioActual; }
    public void setPrecioActual(float precioActual) { this.precioActual = precioActual; }
    
    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }
    
    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }
}