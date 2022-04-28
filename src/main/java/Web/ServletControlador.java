package Web;

import Datos.Dao;
import Dominio.Rol;
import Dominio.Usuario;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/ServletControlador"})
public class ServletControlador extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
    String accion = req.getParameter("accion");
    if (accion != null) {
            switch (accion) {
                case "cerrarSesion": {
                    try {
                        this.cerrarSesion(req, resp);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(ServletControlador.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
                case "sesion": {
                    try {
                        this.sesion(req, resp);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(ServletControlador.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
                default:
                    System.out.println("ENTRAAA");
                    this.accionDefaul(req, resp);

            }
        } else {
            this.accionDefaul(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String accion = req.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "iniciarSesion": {
                    try {
                        this.iniciarSesion(req, resp);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(ServletControlador.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;

                default:

            }
        }
    }

    private void sesion(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ClassNotFoundException {
        HttpSession session = req.getSession(true);

        if (session == null) {

            resp.sendRedirect("login.html");
        }

        String usuario = (String) session.getAttribute("usuario");
        resp.setContentType("text/plain");

        PrintWriter out = resp.getWriter();

        out.print(usuario);
        out.flush();

    }

    private void iniciarSesion(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ClassNotFoundException {
        String email = req.getParameter("email");
        String pass = req.getParameter("password");

        if (email != null && pass != null) {

            HttpSession session = req.getSession();

            //obtenemos los datos de la base datos
            Usuario user = new Dao().iniciarSesion(email);

            if (user.getEmail().equals(email) && user.getPassword().equals(pass)) {
                //recuperamos la sesion

                session.setAttribute("usuario", email);
                crearCookie(req, resp);
                Gson gson = new Gson();

                Rol rolJson = new Rol(user.getNombre_rol());

                String json = gson.toJson(rolJson);
                resp.setContentType("application/json");

                PrintWriter out = resp.getWriter();

                out.print(json);
                out.flush();

            } else {
                resp.sendRedirect("login.html");
            }
        }
    }

    private void cerrarSesion(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ClassNotFoundException {
        HttpSession session = req.getSession();

        String usuario = (String) session.getAttribute("usuario");

        if (usuario != null) {

            session.removeAttribute("usuario");
            resp.setContentType("text/plain");

            PrintWriter out = resp.getWriter();

            out.print(session);
            out.flush();
        }

    }

    private void crearCookie(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ClassNotFoundException {
        Cookie[] cookiees = req.getCookies();

        boolean nuevoUsuario = true;

        if (cookiees != null) {
            for (Cookie c : cookiees) {
                if (c.getName().equals("visitanteRecurrente") && c.getValue().equals("si")) {
                    nuevoUsuario = false;
                    break;
                }
            }
        }

        if (nuevoUsuario) {
            Cookie vistanteCookie = new Cookie("visitanteRecurrente", "si");
            resp.addCookie(vistanteCookie);
        }
    }

    private void accionDefaul(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        HttpSession session = req.getSession();

        if (session.getAttribute("usuario") != null) {

//            resp.sendRedirect("inicio.html");
        } else {
            resp.sendRedirect("login.html");
        }

    }

}
