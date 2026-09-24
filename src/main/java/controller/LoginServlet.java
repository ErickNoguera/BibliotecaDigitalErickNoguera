package controller;

import java.io.IOException;
import java.sql.SQLException;

import dao.UsuarioDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Usuario;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	    String correo = request.getParameter("correo");
	    String password = request.getParameter("password");
	    
	    UsuarioDAO usuarioDao = new UsuarioDAO();
	    try {
	    	Usuario usuario = usuarioDao.autenticar(correo, password);
	    	if (usuario != null) {
	    	    HttpSession sesion = request.getSession();
	    	    sesion.setAttribute("usuario", usuario);
	    	    response.sendRedirect(request.getContextPath() + "/libros.jsp");
	    	    
	    	} else {
	    	    request.setAttribute("error", "Correo o contraseña incorrectos");
	    	    request.getRequestDispatcher("/index.jsp").forward(request, response);
	    	    
	    	}
	    } catch (SQLException e) {
	    	e.printStackTrace();
	        request.setAttribute("error", "Error al conectar con la base de datos");
	        request.getRequestDispatcher("/index.jsp").forward(request, response);
	    }
	    

	}

}
