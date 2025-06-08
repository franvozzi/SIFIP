package sifip.dao;

import sifip.model.Ingreso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class IngresoDAO {

    private static final String URL = "jdbc:postgresql://localhost:5432/sifip";
    private static final String USER = "franvozzi";
    private static final String PASSWORD = "fran";

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

    public List<Ingreso> obtenerIngresosPorUsuario(int idUsuario) {
        List<Ingreso> ingresos = new ArrayList<>();
        String sql = "SELECT * FROM Ingreso WHERE id_usuario = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Ingreso ingreso = new Ingreso(
                    rs.getDouble("monto"),
                    rs.getString("descripcion"),
                    rs.getString("periodicidad"),
                    rs.getDate("fecha").toLocalDate(),
                    rs.getInt("id_usuario")
                );
                ingreso.setId(rs.getInt("id"));
                ingresos.add(ingreso);
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener ingresos: " + e.getMessage());
        }

        return ingresos;
    }
} 