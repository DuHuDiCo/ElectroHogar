package Web;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig
@WebServlet(urlPatterns = {"/ServletControladorCartera"})
public class ServletControladorCartera extends HttpServlet {
    private final String rutaFiles = "C:\\Users\\DUVAN\\Documents\\GitHub\\ElectroHogar\\src\\main\\webapp\\files\\";
    private File uploads = new File(rutaFiles);
    private String[] extens = {".ico", ".png", ".jpg", ".jpeg"};
    

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
                case "guardarConsignacion":
                    this.guardarConsignacion(req, resp);
                    break;
            }
        }
    }

    private void guardarConsignacion(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String num_recibo = req.getParameter("num_recibo");
        float valor = Float.valueOf(req.getParameter("valor"));
        String fecha = req.getParameter("fecha_pago");
        Date fecha_pago = fechaSQL(fecha);
        Date fecha_consignacion = obtenerFechaServer();
        String plataforma = req.getParameter("plataforma");
        String tipo_pago = req.getParameter("tip_pago");
        Part part = req.getPart("file");
        
        
        if(part == null){
            System.out.println("no ha seleccionado un archivo");
            return;
        }
        
        if(isExtension(part.getSubmittedFileName(), extens)){
            String photo = saveFile(part, uploads);
        }
        

    }
    
    
    private String saveFile(Part part, File pathUploads){
        String rutaAbsoluta = "";
        
        try {
            
            Path path = Paths.get(part.getSubmittedFileName());
            String fileName = path.getFileName().toString();
            InputStream input = part.getInputStream();
            
            if(input != null){
                File file = new File(pathUploads, fileName);
                rutaAbsoluta = file.getAbsolutePath();
                Files.copy(input, file.toPath());
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return rutaAbsoluta;
    }
    
    
    private boolean isExtension(String fileName, String[] extensions){
        for(String et:extensions){
            if(fileName.toLowerCase().endsWith(et)){
                return true;
            }
        }
        return false;
    }

    private Date fechaSQL(String fecha) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date javaDate = null;
        try {
            javaDate = sdf.parse(fecha);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        java.sql.Date sqlDate = new java.sql.Date(javaDate.getTime());
        return sqlDate;
    }
    
    private Date obtenerFechaServer(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        
        java.util.Date datObj = calendar.getTime();
        String formattedDate = sdf.format(datObj);
        java.sql.Date dateformated = fechaSQL(formattedDate);
        
        return dateformated;
    }
}
