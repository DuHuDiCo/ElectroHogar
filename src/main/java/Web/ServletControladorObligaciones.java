
package Web;

import Datos.DaoObligaciones;
import Dominio.Obligaciones;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/ServletControladorObligaciones"})
public class ServletControladorObligaciones extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
         String accion = req.getParameter("accion");

        if (accion != null) {
            switch (accion) {
                case "listarObligaciones":
                    this.listarObligaciones(req, resp);
                    break;
                    
                default:    
            }
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
         String accion = req.getParameter("accion");

        if (accion != null) {
            switch (accion) {
            }
        }
    }

    private void listarObligaciones(HttpServletRequest req, HttpServletResponse resp) throws ClassNotFoundException {
        List<Obligaciones> obligaciones new DaoObligaciones().listarObligaciones();
    }
    
}
