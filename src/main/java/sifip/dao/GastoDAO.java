package sifip.dao;

import sifip.model.Gasto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase GastoDAO
 * 
 * Implementa el patrón DAO para la entidad Gasto, proporcionando métodos
 * para persistir y recuperar datos de gastos desde la base de datos PostgreSQL.
 * 
 * Esta clase es responsable de:
 * - Guardar nuevos gastos en la base de datos
 * - Recuperar todos los gastos de un usuario específico
 * - Manejar la conversión entre objetos Java y registros de base de datos
 * - Gestionar las transacciones de base de datos de manera segura
 * 
 * Características de seguridad:
 * - Usa PreparedStatement para prevenir inyección SQL
 * - Maneja recursos de base de datos con try-with-resources
 * - Proporciona manejo de errores centralizado
 */
public class GastoDAO implements DAO<Gasto> {
    
    /**
     * Guarda un gasto en la base de datos
     * 
     * @param entity El objeto Gasto a guardar
     * @throws Exception Si ocurre un error durante el guardado
     */
    @Override
    public void guardar(Gasto entity) throws Exception {
        // SQL con RETURNING para obtener el ID generado automáticamente
        String sql = "INSERT INTO Gasto (monto, descripcion, categoria, fecha, id_usuario) VALUES (?, ?, ?, ?, ?) RETURNING id_gasto";
        
        try (Connection conn = DBConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            // Configuración de parámetros en el PreparedStatement
            stmt.setFloat(1, entity.getMonto());
            stmt.setString(2, entity.getDescripcion());
            stmt.setString(3, entity.getCategoria());
            stmt.setDate(4, new java.sql.Date(entity.getFecha().getTime()));
            stmt.setInt(5, entity.getIdUsuario());
            
            // Ejecuta la inserción y obtiene el ID generado
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                entity.setId(rs.getInt("id_gasto")); // Asigna el ID generado al objeto
            }
        }
    }

    /**
     * Obtiene todos los gastos de la base de datos
     * NOTA: Este método usa un ID de usuario hardcodeado (1) por simplicidad
     * 
     * @return List<Gasto> Lista de todos los gastos del usuario
     * @throws Exception Si ocurre un error durante la consulta
     */
    @Override
    public List<Gasto> obtenerTodos() throws Exception {
        List<Gasto> gastos = new ArrayList<>();
        String sql = "SELECT * FROM Gasto WHERE id_usuario = ?"; // Filtra por idUsuario
        
        try (Connection conn = DBConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, 1); // Valor por defecto, ajusta según el contexto
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    // Creación del objeto Gasto desde los datos de la base de datos
                    Gasto gasto = new Gasto(
                        rs.getFloat("monto"),
                        rs.getString("descripcion"),
                        rs.getString("categoria"),
                        rs.getDate("fecha"),
                        rs.getInt("id_usuario")
                    );
                    gasto.setId(rs.getInt("id_gasto"));
                    gastos.add(gasto);
                }
            }
        }
        return gastos;
    }

    /**
     * Obtiene todos los gastos de un usuario específico
     * 
     * @param idUsuario ID del usuario cuyos gastos se quieren obtener
     * @return List<Gasto> Lista de gastos del usuario especificado
     * @throws Exception Si ocurre un error durante la consulta
     */
    public List<Gasto> obtenerPorUsuario(int idUsuario) throws Exception {
        List<Gasto> gastos = new ArrayList<>();
        String sql = "SELECT * FROM Gasto WHERE id_usuario = ?";
        
        try (Connection conn = DBConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idUsuario);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    // Mapeo de cada fila de la base de datos a un objeto Gasto
                    Gasto gasto = new Gasto(
                        rs.getFloat("monto"),
                        rs.getString("descripcion"),
                        rs.getString("categoria"),
                        rs.getDate("fecha"),
                        rs.getInt("id_usuario")
                    );
                    gasto.setId(rs.getInt("id_gasto"));
                    gastos.add(gasto);
                }
            }
        }
        return gastos;
    }
}