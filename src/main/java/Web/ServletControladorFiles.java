package Web;

import Datos.DaoCartera;
import Datos.DaoObligaciones;
import Dominio.Archivo;
import Dominio.Obligaciones;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import static oracle.jrockit.jfr.events.Bits.floatValue;

@MultipartConfig
@WebServlet(urlPatterns = {"/ServletControladorFiles"})
public class ServletControladorFiles extends HttpServlet {

    private final String rutaFiles = "C:\\Users\\DUVAN\\Documents\\GitHub\\ElectroHogar\\src\\main\\webapp\\files\\txt\\";
    private final File uploads = new File(rutaFiles);
    private final String[] extens = {".txt"};

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String accion = req.getParameter("accion");
        if (accion != null) {
            switch (accion) {
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String accion = req.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "guardartxt": {
                    try {
                        this.guardarTxt(req, resp);
                    } catch (ClassNotFoundException | SQLException ex) {
                        Logger.getLogger(ServletControladorFiles.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }
        }
    }

    private void guardarTxt(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, ClassNotFoundException, SQLException {
        Part part = req.getPart("file");

        if (part == null) {
            System.out.println("no ha seleccionado un archivo");
            return;
        }

        if (isExtension(part.getSubmittedFileName(), extens)) {
            String name = part.getSubmittedFileName();
            String photo = saveFile(part, uploads);

            Archivo file = new Archivo(name, photo);
            int save = new DaoCartera().guardarArchivo(file);

            //obtenemos el nombre del archivo
            String nombre = crearNombreArchivo();
            //leemos el archivo y guardamos en base datos
            int leerArchivo = leerTxt(nombre);

            String respuesta = Integer.toString(leerArchivo);
            resp.setContentType("text/plain");

            PrintWriter out = resp.getWriter();

            out.print(respuesta);
            out.flush();
        }
    }

    private String saveFile(Part part, File pathUploads) {
        String rutaAbsoluta = "";

        try {

            Path path = Paths.get(part.getSubmittedFileName());
            String fileName = path.getFileName().toString();
            InputStream input = part.getInputStream();

            if (input != null) {
                File file = new File(pathUploads, fileName);
                rutaAbsoluta = file.getAbsolutePath();
                Files.copy(input, file.toPath());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return rutaAbsoluta;
    }

    private boolean isExtension(String fileName, String[] extensions) {
        for (String et : extensions) {
            if (fileName.toLowerCase().endsWith(et)) {
                return true;
            }
        }
        return false;
    }

    private int leerTxt(String nombre) throws FileNotFoundException, IOException {
        String linea = "";
        String delimitante = ",";
        String ruta = "C:\\Users\\DUVAN\\Documents\\GitHub\\ElectroHogar\\src\\main\\webapp\\files\\txt\\" + nombre;

        Obligaciones obligacion = null;
        int guardarObliga = 0;
       

        try {
            FileReader fileReader = new FileReader(ruta);
            BufferedReader reader = new BufferedReader(fileReader);

            while ((linea = reader.readLine()) != null) {
                String[] campo = linea.split(delimitante);
                String nombreTitular = campo[0];
                String tipoDoc = campo[1];
                String documento = campo[2];
                
                String telefono = campo[3];
                String email = campo[4];
                String direccion = campo[5];
                String clasi_cliente = campo[6];
                String codigo_cliente = campo[7];
                String valorCuota = campo[8];
                float valor_cuota = Float.valueOf(valorCuota);
                String saldoCapital = campo[9];
                float saldo_capital = Float.valueOf(saldoCapital);
                String saldoMora = campo[10];
                float saldo_mora = Float.valueOf(saldoMora);
                String diasMora = campo[11];
                int dias_mora = Integer.parseInt(diasMora);
                String dato_perso = campo[12];
                String idSede = campo[13];
                int id_sede = Integer.parseInt(idSede);
                
                obligacion = new Obligaciones(nombreTitular, tipoDoc, documento, telefono, email, direccion, clasi_cliente, codigo_cliente, valor_cuota, saldo_capital, saldo_mora, dias_mora, id_sede);
                guardarObliga = new DaoObligaciones().guardarObligaciones(obligacion);
                
        
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return guardarObliga;
    }

    private String crearNombreArchivo() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Calendar calendar = Calendar.getInstance();

        java.util.Date datObj = calendar.getTime();
        String fecha = sdf.format(datObj);

        String nombreArc = "txtDatos-" + fecha + ".txt";
        return nombreArc;

    }

}
