package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import dao.PrestamoDAO;
import dto.PrestamoDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Usuario;

@WebServlet("/prestamos")
public class PrestamoServlet extends HttpServlet {

    private PrestamoDAO prestamoDao = new PrestamoDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sesion = request.getSession();
        Usuario usuario = (Usuario) sesion.getAttribute("usuario");

        try {
            List<PrestamoDTO> prestamos = prestamoDao.listarActivosPorUsuario(usuario.getId());
            request.setAttribute("prestamos", prestamos);
            request.getRequestDispatcher("/prestamos.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al cargar los préstamos");
            request.getRequestDispatcher("/prestamos.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        HttpSession sesion = request.getSession();
        Usuario usuario = (Usuario) sesion.getAttribute("usuario");

        try {
            switch (accion) {
                case "prestar":
                    int libroId = Integer.parseInt(request.getParameter("libroId"));
                    prestamoDao.prestar(usuario.getId(), libroId);
                    break;

                case "devolver":
                    int prestamoId = Integer.parseInt(request.getParameter("prestamoId"));
                    int libroIdDevolver = Integer.parseInt(request.getParameter("libroId"));
                    prestamoDao.devolver(prestamoId, libroIdDevolver);
                    break;
            }

            response.sendRedirect(request.getContextPath() + "/libros");

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/libros");
        }
    }
}