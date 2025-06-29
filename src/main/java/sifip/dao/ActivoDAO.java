package sifip.dao;

import sifip.model.Activo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase ActivoDAO
 * 
 * Implementa el patrón DAO para la entidad Activo, proporcionando métodos
 * para persistir y recuperar datos de activos financieros desde la base de datos PostgreSQL.
 * 
 * Esta clase es responsable de:
 * - Guardar nuevos activos en la base de datos
 * - Recuperar todos los activos
 * - Actualizar precios de activos existentes
 * - Manejar la conversión entre objetos Java y registros de base de datos
 * - Gestionar las transacciones de base de datos de manera segura
 * 
 * Características de seguridad:
 * - Usa PreparedStatement para prevenir inyección SQL
 * - Maneja recursos de base de datos con try-with-resources
 * - Proporciona manejo de errores centralizado
 */
public class ActivoDAO implements DAO<Activo> {
    
    /**
     * Guarda un activo en la base de datos
     * 
     * @param entity El objeto Activo a guardar
     * @throws Exception Si ocurre un error durante el guardado
     */
    @Override
    public void guardar(Activo entity) throws Exception {
        // SQL para insertar un nuevo activo
        String sql = "INSERT INTO Activo (nombre, tipo, cantidad, precio_inicial, precio_actual, fecha, id_usuario) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DBConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            // Configuración de parámetros en el PreparedStatement
            stmt.setString(1, entity.getNombre());
            stmt.setString(2, entity.getTipo());
            stmt.setFloat(3, entity.getCantidad());
            stmt.setFloat(4, entity.getPrecioInicial());
            stmt.setFloat(5, entity.getPrecioActual());
            stmt.setDate(6, new java.sql.Date(entity.getFecha().getTime()));
            stmt.setInt(7, entity.getIdUsuario());
            
            // Ejecuta la inserción
            stmt.executeUpdate();
        }
    }

    /**
     * Obtiene todos los activos de la base de datos
     * 
     * @return List<Activo> Lista de todos los activos
     * @throws Exception Si ocurre un error durante la consulta
     */
    @Override
    public List<Activo> obtenerTodos() throws Exception {
        List<Activo> activos = new ArrayList<>();
        String sql = "SELECT * FROM Activo";
        
        try (Connection conn = DBConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                // Creación del objeto Activo desde los datos de la base de datos
                Activo activo = new Activo(
                    rs.getString("nombre"),
                    rs.getString("tipo"),
                    rs.getFloat("cantidad"),
                    rs.getFloat("precio_inicial"),
                    rs.getDate("fecha"),
                    rs.getInt("id_usuario")
                );
                activo.setId(rs.getInt("id_activo"));
                activo.setPrecioActual(rs.getFloat("precio_actual"));
                activos.add(activo);
            }
        }
        return activos;
    }

    /**
     * Actualiza el precio actual de un activo específico
     * 
     * @param id ID del activo a actualizar
     * @param nuevoPrecio Nuevo precio por unidad
     * @throws Exception Si ocurre un error durante la actualización
     */
    public void actualizarPrecio(int id, float nuevoPrecio) throws Exception {
        // SQL para actualizar el precio de un activo
        String sql = "UPDATE Activo SET precio_actual = ? WHERE id_activo = ?";
        
        try (Connection conn = DBConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            // Configuración de parámetros en el PreparedStatement
            stmt.setFloat(1, nuevoPrecio);
            stmt.setInt(2, id);
            
            // Ejecuta la actualización
            stmt.executeUpdate();
        }
    }
}