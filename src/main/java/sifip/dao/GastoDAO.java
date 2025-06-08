package sifip.dao;

import sifip.model.Gasto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GastoDAO {

    private static final String URL = "jdbc:postgresql://localhost:5432/sifip";
    private static final String USER = "franvozzi";
    private static final String PASSWORD = "fran";

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

    public List<Gasto> obtenerGastosPorUsuario(int idUsuario) {
        List<Gasto> gastos = new ArrayList<>();
        String sql = "SELECT * FROM Gasto WHERE id_usuario = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Gasto gasto = new Gasto(
                    rs.getDouble("monto"),
                    rs.getString("descripcion"),
                    rs.getString("categoria"),
                    rs.getDate("fecha").toLocalDate(),
                    rs.getInt("id_usuario")
                );
                gasto.setId(rs.getInt("id"));
                gastos.add(gasto);
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener gastos: " + e.getMessage());
        }

        return gastos;
    }
} 
