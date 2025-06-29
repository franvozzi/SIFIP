package sifip.dao;

import sifip.model.Ingreso;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase IngresoDAO
 * 
 * Implementa el patrón DAO para la entidad Ingreso, proporcionando métodos
 * para persistir y recuperar datos de ingresos desde la base de datos PostgreSQL.
 * 
 * Esta clase es responsable de:
 * - Guardar nuevos ingresos en la base de datos
 * - Recuperar todos los ingresos de un usuario específico
 * - Manejar la conversión entre objetos Java y registros de base de datos
 * - Gestionar las transacciones de base de datos de manera segura
 * 
 * Características de seguridad:
 * - Usa PreparedStatement para prevenir inyección SQL
 * - Maneja recursos de base de datos con try-with-resources
 * - Proporciona manejo de errores centralizado
 */
public class IngresoDAO implements DAO<Ingreso> {
    
    /**
     * Guarda un ingreso en la base de datos
     * 
     * @param entity El objeto Ingreso a guardar
     * @throws Exception Si ocurre un error durante el guardado
     */
    @Override
    public void guardar(Ingreso entity) throws Exception {
        // SQL con RETURNING para obtener el ID generado automáticamente
        String sql = "INSERT INTO Ingreso (monto, descripcion, periodicidad, fecha, id_usuario) VALUES (?, ?, ?, ?, ?) RETURNING id_ingreso";
        
        try (Connection conn = DBConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            // Configuración de parámetros en el PreparedStatement
            stmt.setFloat(1, entity.getMonto());
            stmt.setString(2, entity.getDescripcion());
            stmt.setString(3, entity.getPeriodicidad());
            stmt.setDate(4, new java.sql.Date(entity.getFecha().getTime()));
            stmt.setInt(5, entity.getIdUsuario());
            
            // Ejecuta la inserción y obtiene el ID generado
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                entity.setId(rs.getInt("id_ingreso")); // Asigna el ID generado al objeto
            }
        }
    }

    /**
     * Obtiene todos los ingresos de la base de datos
     * NOTA: Este método usa un ID de usuario hardcodeado (1) por simplicidad
     * 
     * @return List<Ingreso> Lista de todos los ingresos del usuario
     * @throws Exception Si ocurre un error durante la consulta
     */
    @Override
    public List<Ingreso> obtenerTodos() throws Exception {
        List<Ingreso> ingresos = new ArrayList<>();
        String sql = "SELECT * FROM Ingreso WHERE id_usuario = ?"; // Filtra por idUsuario
        
        try (Connection conn = DBConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            // Aquí deberías pasar el idUsuario, por ejemplo, desde un parámetro
            stmt.setInt(1, 1); // Valor por defecto, ajusta según el contexto
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    // Creación del objeto Ingreso desde los datos de la base de datos
                    Ingreso ingreso = new Ingreso(
                        rs.getFloat("monto"),
                        rs.getString("descripcion"),
                        rs.getString("periodicidad"),
                        rs.getDate("fecha"),
                        rs.getInt("id_usuario")
                    );
                    ingreso.setId(rs.getInt("id_ingreso"));
                    ingresos.add(ingreso);
                }
            }
        }
        return ingresos;
    }

    /**
     * Obtiene todos los ingresos de un usuario específico
     * 
     * @param idUsuario ID del usuario cuyos ingresos se quieren obtener
     * @return List<Ingreso> Lista de ingresos del usuario especificado
     * @throws Exception Si ocurre un error durante la consulta
     */
    public List<Ingreso> obtenerPorUsuario(int idUsuario) throws Exception {
        List<Ingreso> ingresos = new ArrayList<>();
        String sql = "SELECT * FROM Ingreso WHERE id_usuario = ?";
        
        try (Connection conn = DBConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idUsuario);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    // Mapeo de cada fila de la base de datos a un objeto Ingreso
                    Ingreso ingreso = new Ingreso(
                        rs.getFloat("monto"),
                        rs.getString("descripcion"),
                        rs.getString("periodicidad"),
                        rs.getDate("fecha"),
                        rs.getInt("id_usuario")
                    );
                    ingreso.setId(rs.getInt("id_ingreso"));
                    ingresos.add(ingreso);
                }
            }
        }
        return ingresos;
    }
}