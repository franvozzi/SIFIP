package sifip.dao;

import sifip.model.Activo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ActivoDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/sifip_db";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public boolean save(Activo activo) {
        String sql = "INSERT INTO Activo (nombre, tipo, cantidad, precio_inicial, precio_actual, fecha, id_usuario) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, activo.getNombre());
            stmt.setString(2, activo.getTipo());
            stmt.setDouble(3, activo.getCantidad());
            stmt.setDouble(4, activo.getPrecioInicial());
            stmt.setDouble(5, activo.getPrecioActual());
            stmt.setDate(6, java.sql.Date.valueOf(activo.getFecha()));
            stmt.setInt(7, activo.getIdUsuario());

            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.err.println("Error al guardar el activo: " + e.getMessage());
            return false;
        }
    }
} 
