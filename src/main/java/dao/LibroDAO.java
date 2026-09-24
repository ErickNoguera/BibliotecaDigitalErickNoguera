package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Libro;
import util.ConexionBD;

public class LibroDAO {

    // PARA LISTAR TODOS LIBROS 
    public List<Libro> listarTodos() throws SQLException {
        List<Libro> libros = new ArrayList<>();
        ConexionBD.getInstancia();
        Connection conexion = ConexionBD.getConexion();

        String sql = "SELECT id, titulo, autor, isbn, disponible FROM libros";
        PreparedStatement stmt = conexion.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Libro libro = new Libro(
                rs.getInt("id"),
                rs.getString("titulo"),
                rs.getString("autor"),
                rs.getString("isbn"),
                rs.getBoolean("disponible")
            );
            libros.add(libro);
        }
        return libros;
    }

    // PARA BUSCAR LIBRO POR ID
    public Libro buscarPorId(int id) throws SQLException {
        ConexionBD.getInstancia();
        Connection conexion = ConexionBD.getConexion();

        String sql = "SELECT id, titulo, autor, isbn, disponible FROM libros WHERE id = ?";
        PreparedStatement stmt = conexion.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return new Libro(
                rs.getInt("id"),
                rs.getString("titulo"),
                rs.getString("autor"),
                rs.getString("isbn"),
                rs.getBoolean("disponible")
            );
        }
        return null;
    }

    // INSERTAR UN NUEVO LIBRO
    public void insertar(Libro libro) throws SQLException {
        ConexionBD.getInstancia();
        Connection conexion = ConexionBD.getConexion();

        String sql = "INSERT INTO libros (titulo, autor, isbn, disponible) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = conexion.prepareStatement(sql);
        stmt.setString(1, libro.getTitulo());
        stmt.setString(2, libro.getAutor());
        stmt.setString(3, libro.getIsbn());
        stmt.setBoolean(4, libro.isDisponible());
        stmt.executeUpdate();
    }

    // ACTUALIZAR
    public void actualizar(Libro libro) throws SQLException {
        ConexionBD.getInstancia();
        Connection conexion = ConexionBD.getConexion();

        String sql = "UPDATE libros SET titulo = ?, autor = ?, isbn = ?, disponible = ? WHERE id = ?";
        PreparedStatement stmt = conexion.prepareStatement(sql);
        stmt.setString(1, libro.getTitulo());
        stmt.setString(2, libro.getAutor());
        stmt.setString(3, libro.getIsbn());
        stmt.setBoolean(4, libro.isDisponible());
        stmt.setInt(5, libro.getId());
        stmt.executeUpdate();
    }

    // ELIMINAR LIBRO
    public void eliminar(int id) throws SQLException {
        ConexionBD.getInstancia();
        Connection conexion = ConexionBD.getConexion();

        String sql = "DELETE FROM libros WHERE id = ?";
        PreparedStatement stmt = conexion.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }
}