package sifip.model;

/**
 * Interfaz Calculable
 * 
 * Define el contrato para todas las entidades que pueden realizar cálculos financieros.
 * Esta interfaz es parte del patrón Strategy, permitiendo que diferentes tipos de
 * entidades (Ingresos, Gastos, Activos) implementen sus propios algoritmos de cálculo.
 * 
 * Implementada por:
 * - Ingreso: Calcula el valor positivo del ingreso
 * - Gasto: Calcula el valor negativo del gasto
 * - Activo: Calcula el rendimiento (precio actual - precio inicial) * cantidad
 */
public interface Calculable {
    /**
     * Método que debe implementar cada entidad para realizar su cálculo específico
     * @return float - El resultado del cálculo financiero
     */
    float calcular();
}