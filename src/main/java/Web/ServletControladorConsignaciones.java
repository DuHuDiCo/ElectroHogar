package Web;

import Datos.DaoConsignaciones;
import Dominio.Consignacion;
import Dominio.Obligaciones;
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

@WebServlet(urlPatterns = {"/ServletControladorConsignaciones"})
public class ServletControladorConsignaciones extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String accion = req.getParameter("accion");

        if (accion != null) {
            switch (accion) {
                case "listarConsignaciones": {
                    try {
                        this.listarConsignaciones(req, resp);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(ServletControladorConsignaciones.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
                case "listarConsignacionesByEstado":
                {
                    try {
                        this.listarConsignacionesByEstado(req, resp);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(ServletControladorConsignaciones.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                    break;
                case "listarConsignacionesByCedula":
                {
                    try {
                        this.listarConsignacionesCedula(req, resp);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(ServletControladorConsignaciones.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                    break;
                    
                case "listarClienteByCedula":
                {
                    try {
                        this.listarClientesByCedula(req, resp);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(ServletControladorConsignaciones.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                    break;




            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
    }

    private void listarConsignaciones(HttpServletRequest req, HttpServletResponse resp) throws ClassNotFoundException, IOException {
        List<Consignacion> consignaciones = new DaoConsignaciones().listarConsignaciones();

        Gson gson = new Gson();

        

        String json = gson.toJson(consignaciones);
        resp.setContentType("application/json");

        PrintWriter out = resp.getWriter();

        out.print(json);
        out.flush();
    }

    private void listarConsignacionesByEstado(HttpServletRequest req, HttpServletResponse resp) throws IOException, ClassNotFoundException {
        
        String estado = req.getParameter("estado");
        
        List<Consignacion> consignaciones = new DaoConsignaciones().listarConsignacionesByEstado(estado);

        Gson gson = new Gson();

        

        String json = gson.toJson(consignaciones);
        resp.setContentType("application/json");

        PrintWriter out = resp.getWriter();

        out.print(json);
        out.flush();
    }

    private void listarConsignacionesCedula(HttpServletRequest req, HttpServletResponse resp) throws ClassNotFoundException, IOException {
        String cedula = req.getParameter("cedula");
        
        List<Consignacion> consignaciones = new DaoConsignaciones().listarConsignacionesByCedula(cedula);

        Gson gson = new Gson();

        

        String json = gson.toJson(consignaciones);
        resp.setContentType("application/json");

        PrintWriter out = resp.getWriter();

        out.print(json);
        out.flush();
    }

    private void listarClientesByCedula(HttpServletRequest req, HttpServletResponse resp) throws ClassNotFoundException, IOException {
        
        String cedula = req.getParameter("cedula");
        
        List<Obligaciones> obligaciones = new DaoConsignaciones().listarClienteByCedula(cedula);
        
        Gson gson = new Gson();

        

        String json = gson.toJson(obligaciones);
        resp.setContentType("application/json");

        PrintWriter out = resp.getWriter();

        out.print(json);
        out.flush();
    }

}
