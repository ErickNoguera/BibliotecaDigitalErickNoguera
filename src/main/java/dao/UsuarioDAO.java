package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Usuario;
import util.ConexionBD;

public class UsuarioDAO {
	
    public Usuario autenticar(String correo, String password) throws SQLException {
        ConexionBD.getInstancia();
        Connection conexion = ConexionBD.getConexion();
        
        String sql =
        "SELECT id, nombre, correo, rol FROM usuarios WHERE correo = ? AND password = ?";
        
        PreparedStatement stmt = conexion.prepareStatement(sql);
        stmt.setString(1, correo);
        stmt.setString(2, password);
        
        ResultSet rs = stmt.executeQuery();
        
        if (rs.next()) {
            Usuario usuario = new Usuario(
                rs.getInt("id"),
                rs.getString("nombre"),
                rs.getString("correo"),
                rs.getString("rol")
            );
            return usuario;
        } else {
            return null;
        }

    }	

}
