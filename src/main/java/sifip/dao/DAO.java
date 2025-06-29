package sifip.dao;

import java.util.List;

/**
 * Interfaz DAO (Data Access Object)
 * 
 * Define el contrato para todas las operaciones de acceso a datos en el sistema.
 * Esta interfaz implementa el patrón DAO, que separa la lógica de negocio
 * de la lógica de acceso a datos.
 * 
 * El patrón DAO proporciona:
 * - Abstracción de la fuente de datos (base de datos, archivos, etc.)
 * - Facilita el testing mediante mocks
 * - Permite cambiar la implementación de acceso a datos sin afectar el negocio
 * 
 * @param <T> Tipo genérico que representa la entidad a manejar
 */
public interface DAO<T> {
    /**
     * Guarda una entidad en la fuente de datos
     * 
     * @param entity La entidad a guardar
     * @throws Exception Si ocurre un error durante el guardado
     */
    void guardar(T entity) throws Exception;
    
    /**
     * Obtiene todas las entidades de la fuente de datos
     * 
     * @return List<T> Lista de todas las entidades
     * @throws Exception Si ocurre un error durante la consulta
     */
    List<T> obtenerTodos() throws Exception;
}