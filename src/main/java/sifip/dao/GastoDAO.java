package sifip.dao;

import sifip.model.Gasto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GastoDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/sifip_db";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public boolean save(Gasto gasto) {
        String sql = "INSERT INTO Gasto (monto, descripcion, categoria, fecha, id_usuario) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, gasto.getMonto());
            stmt.setString(2, gasto.getDescripcion());
            stmt.setString(3, gasto.getCategoria());
            stmt.setDate(4, java.sql.Date.valueOf(gasto.getFecha()));
            stmt.setInt(5, gasto.getIdUsuario());

            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.err.println("Error al guardar el gasto: " + e.getMessage());
            return false;
        }
    }
} 
