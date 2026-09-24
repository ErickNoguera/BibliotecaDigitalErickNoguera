package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.PrestamoDTO;
import util.ConexionBD;

public class PrestamoDAO {

    public void prestar(int usuarioId, int libroId) throws SQLException {
        ConexionBD.getInstancia();
        Connection conexion = ConexionBD.getConexion();

        try {
            conexion.setAutoCommit(false);

            // Paso 1: registrar el préstamo
            String sqlInsert = "INSERT INTO prestamos (usuario_id, libro_id, fecha_prestamo) VALUES (?, ?, CURDATE())";
            PreparedStatement stmtInsert = conexion.prepareStatement(sqlInsert);
            stmtInsert.setInt(1, usuarioId);
            stmtInsert.setInt(2, libroId);
            stmtInsert.executeUpdate();

            // Paso 2: marcar el libro como no disponible
            String sqlUpdate = "UPDATE libros SET disponible = false WHERE id = ?";
            PreparedStatement stmtUpdate = conexion.prepareStatement(sqlUpdate);
            stmtUpdate.setInt(1, libroId);
            stmtUpdate.executeUpdate();

            conexion.commit();

        } catch (SQLException e) {
            conexion.rollback();
            throw e;

        } finally {
            conexion.setAutoCommit(true);
        }
    }

    public void devolver(int prestamoId, int libroId) throws SQLException {
        ConexionBD.getInstancia();
        Connection conexion = ConexionBD.getConexion();

        try {
            conexion.setAutoCommit(false);

            // Paso 1: registrar la fecha de devolución
            String sqlUpdatePrestamo = "UPDATE prestamos SET fecha_devolucion = CURDATE() WHERE id = ?";
            PreparedStatement stmtPrestamo = conexion.prepareStatement(sqlUpdatePrestamo);
            stmtPrestamo.setInt(1, prestamoId);
            stmtPrestamo.executeUpdate();

            // Paso 2: marcar el libro como disponible de nuevo
            String sqlUpdateLibro = "UPDATE libros SET disponible = true WHERE id = ?";
            PreparedStatement stmtLibro = conexion.prepareStatement(sqlUpdateLibro);
            stmtLibro.setInt(1, libroId);
            stmtLibro.executeUpdate();

            conexion.commit();

        } catch (SQLException e) {
            conexion.rollback();
            throw e;

        } finally {
            conexion.setAutoCommit(true);
        }
    }

    public List<PrestamoDTO> listarActivosPorUsuario(int usuarioId) throws SQLException {
        List<PrestamoDTO> prestamos = new ArrayList<>();
        ConexionBD.getInstancia();
        Connection conexion = ConexionBD.getConexion();

        String sql = "SELECT p.id, p.libro_id, p.fecha_prestamo, l.titulo " +
                     "FROM prestamos p " +
                     "JOIN libros l ON p.libro_id = l.id " +
                     "WHERE p.usuario_id = ? AND p.fecha_devolucion IS NULL";

        PreparedStatement stmt = conexion.prepareStatement(sql);
        stmt.setInt(1, usuarioId);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            PrestamoDTO dto = new PrestamoDTO(
                rs.getInt("id"),
                rs.getInt("libro_id"),
                rs.getString("titulo"),
                rs.getString("fecha_prestamo")
            );
            prestamos.add(dto);
        }

        return prestamos;
    }
}