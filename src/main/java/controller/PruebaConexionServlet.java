package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.ConexionBD;

@WebServlet("/prueba")
public class PruebaConexionServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		try {
			ConexionBD.getInstancia();
			Connection conexion = ConexionBD.getConexion();
			out.println("<h2>¡Conexión exitosa a MySQL!</h2>");
		} catch (SQLException e) {
			out.println("<h2>Error al conectar:</h2>");
			out.println("<p>" + e.getMessage() + "</p>");
		}
	}

}
