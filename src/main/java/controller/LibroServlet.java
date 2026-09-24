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

    private LibroDAO libroDao = new LibroDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sesion = request.getSession();
        Usuario usuario = (Usuario) sesion.getAttribute("usuario");

        if (usuario == null) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            return;
        }

        String accion = request.getParameter("accion");

        try {
            if ("nuevo".equals(accion)) {
                request.getRequestDispatcher("/libro-form.jsp").forward(request, response);

            } else if ("editar".equals(accion)) {
                int id = Integer.parseInt(request.getParameter("id"));
                Libro libro = libroDao.buscarPorId(id);
                request.setAttribute("libro", libro);
                request.getRequestDispatcher("/libro-form.jsp").forward(request, response);

            } else {
                List<Libro> libros = libroDao.listarTodos();
                request.setAttribute("libros", libros);
                request.getRequestDispatcher("/libros.jsp").forward(request, response);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al procesar la solicitud");
            request.getRequestDispatcher("/libros.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sesion = request.getSession();
        Usuario usuario = (Usuario) sesion.getAttribute("usuario");

        if (usuario == null) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            return;
        }

        String accion = request.getParameter("accion");

        try {
            if ("guardar".equals(accion)) {
                String idParam = request.getParameter("id");
                String titulo = request.getParameter("titulo");
                String autor = request.getParameter("autor");
                String isbn = request.getParameter("isbn");
                boolean disponible = request.getParameter("disponible") != null;

                if (idParam == null || idParam.isEmpty()) {
                    Libro nuevoLibro = new Libro(0, titulo, autor, isbn, disponible);
                    libroDao.insertar(nuevoLibro);
                    response.sendRedirect(request.getContextPath() + "/libros?msg=creado");
                } else {
                    int id = Integer.parseInt(idParam);
                    Libro libroExistente = new Libro(id, titulo, autor, isbn, disponible);
                    libroDao.actualizar(libroExistente);
                    response.sendRedirect(request.getContextPath() + "/libros?msg=actualizado");
                }
                return;

            } else if ("eliminar".equals(accion)) {
                int id = Integer.parseInt(request.getParameter("id"));
                libroDao.eliminar(id);
                response.sendRedirect(request.getContextPath() + "/libros?msg=eliminado");
                return;
            }

            response.sendRedirect(request.getContextPath() + "/libros");

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/libros");
        }
    }
}