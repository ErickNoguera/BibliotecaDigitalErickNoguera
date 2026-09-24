package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import dao.LibroDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Libro;
import model.Usuario;

@WebServlet("/libros")
public class LibroServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sesion = request.getSession();
        Usuario usuario = (Usuario) sesion.getAttribute("usuario");

        if (usuario == null) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            return;
        }
        
        LibroDAO libroDao = new LibroDAO();

        try {
            List<Libro> libros = libroDao.listarTodos();
            request.setAttribute("libros", libros);
            request.getRequestDispatcher("/libros.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al cargar los libros");
            request.getRequestDispatcher("/libros.jsp").forward(request, response);
        }
    }
}