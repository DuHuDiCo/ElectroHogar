package Web;

import Datos.DaoSedes;
import Dominio.Sedes;
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


@WebServlet(urlPatterns = {"/ServletSede"})

public class ServletSedes extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String accion = req.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "listarSede": {
                    try {
                        this.listarSede(req, resp);
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

    private void listarSede(HttpServletRequest req, HttpServletResponse resp) throws ClassNotFoundException, IOException {

        List<Sedes> Sedes = new DaoSedes().listarSedes();
        Gson gson = new Gson();
        String json = gson.toJson(Sedes);
        PrintWriter out = resp.getWriter();

        resp.setContentType("application/json");
        out.print(json);
        out.flush();
    }

    private void accionDefaul(HttpServletRequest req, HttpServletResponse resp) throws IOException {

    }

}
