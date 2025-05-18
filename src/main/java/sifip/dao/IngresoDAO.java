package sifip.dao;

import sifip.model.Ingreso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.DriverManager;

public class IngresoDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/sifip_db";
    private static final String USER = "root";
    private static final String PASSWORD = ""; // Reemplazar si corresponde

    public boolean save(Ingreso ingreso) {
        String sql = "INSERT INTO Ingreso (monto, descripcion, periodicidad, fecha, id_usuario) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, ingreso.getMonto());
            stmt.setString(2, ingreso.getDescripcion());
            stmt.setString(3, ingreso.getPeriodicidad());
            stmt.setDate(4, java.sql.Date.valueOf(ingreso.getFecha()));
            stmt.setInt(5, ingreso.getIdUsuario());

            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.err.println("Error al guardar el ingreso: " + e.getMessage());
            return false;
        }
    }
} 