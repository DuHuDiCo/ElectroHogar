package Web;

import Datos.DaoRoles;
import Dominio.Rol;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/ServletRol"})

public class ServletRol extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String accion = req.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "listarRol": {
                    try {
                        this.listarRol(req, resp);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(ServletUsuarios.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;

                default:

            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    private void listarRol(HttpServletRequest req, HttpServletResponse resp) throws ClassNotFoundException, IOException {

        List<Rol> roles = new DaoRoles().listarRoles();
        Gson gson = new Gson();
        String json = gson.toJson(roles);
        PrintWriter out = resp.getWriter();

        resp.setContentType("application/json");
        out.print(json);
        out.flush();
    }

    private void accionDefaul(HttpServletRequest req, HttpServletResponse resp) throws IOException {

    }

}