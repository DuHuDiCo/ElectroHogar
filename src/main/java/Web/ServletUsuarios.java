package Web;
import Dominio.Usuario;
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

@WebServlet(urlPatterns = {"/ServletUsuarios"})

public class ServletUsuarios extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String accion = req.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "registrarUsuario": {
                    try {
                        this.registrarUsuario(req, resp);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(ServletUsuarios.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;

                default:

            }
        }
    }

    private void registrarUsuario(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ClassNotFoundException {
        
        String nombre = req.getParameter("nombre");
        String Identificacion = req.getParameter("Identificacion");
        String Email = req.getParameter("Email");
        String telefono = req.getParameter("telefono");
        String password = req.getParameter("password");
        String RepetirPassword = req.getParameter("RepetirPassword");

        Usuario nuevusu = new Usuario(0, nombre, telefono, nombre, Email, password, telefono, fecha_creacion, telefono, ultima_sesion, Email, 0, 0, nombre)
        
  
        // crear el objeto cliente
        Cliente cliente = new Cliente(nombre, celular, email);
        //enviamos el cliente creado
        int registroMod = new ClienteDaoJDBC().insertarCliente(cliente);
        System.out.println(registroMod);
        this.accionDefaultCliente(req, resp);
        
        
//        if (email != null && pass != null) {
//
//            HttpSession session = req.getSession();
//            //obtenemos los datos de la base datos
//            Usuario user = new Dao().iniciarSesion(email);
//
//            if (user.getEmail().equals(email) && user.getPassword().equals(pass)) {
//                //recuperamos la sesion
//
//                session.setAttribute("usuario", email);
//                crearCookie(req, resp);
//                Gson gson = new Gson();
//
//                Rol rolJson = new Rol(user.getNombre_rol());
//
//                String json = gson.toJson(rolJson);
//                resp.setContentType("application/json");
//
//                PrintWriter out = resp.getWriter();
//
//                out.print(json);
//                out.flush();
//
//            } else {
//                resp.sendRedirect("login.html");
//            }
//        }
    }

    private void sesion(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ClassNotFoundException {

    }

    private void accionDefaul(HttpServletRequest req, HttpServletResponse resp) throws IOException {

    }

}
