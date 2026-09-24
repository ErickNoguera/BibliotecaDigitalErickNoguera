package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

	private static final String URL = "jdbc:mysql://localhost:3306/db_biblioteca";
	private static final String USUARIO = "root";
	private static final String PASSWORD = "admin1234";

	private static ConexionBD instancia;

	private ConexionBD() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Error: La clase no fue encontrada.");
		}
	}

	public static ConexionBD getInstancia() {
		if (instancia == null) {
			instancia = new ConexionBD();
		}
		return instancia;
	}
	
	public static Connection getConexion() throws SQLException {
	    return DriverManager.getConnection(URL, USUARIO, PASSWORD);
	}
}
