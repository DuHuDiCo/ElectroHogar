
package Dominio;

import java.sql.Date;


public class Archivo extends Usuario{
    
    private int idFile;
    private String nombre_archivo;
    private String ruta;
    private Date fecha;
    private int id_usuario;

    public Archivo() {
        
    }

    public Archivo(int idFile, String nombre_archivo, Date fecha, int idUsuario, String nombre) {
        super(idUsuario, nombre);
        this.idFile = idFile;
        this.nombre_archivo = nombre_archivo;
        this.fecha = fecha;
    }

    

  

    
    
    
    
    public Archivo(String nombre, String ruta, Date fecha, int id_usuario) {
        this.nombre_archivo = nombre;
        this.ruta = ruta;
        this.fecha = fecha;
        this.id_usuario = id_usuario;
    }
    
    

    public Archivo(int idFile, String nombre, String ruta) {
        this.idFile = idFile;
        this.nombre_archivo = nombre;
        this.ruta = ruta;
    }

    public Archivo(String nombre, String ruta) {
        this.nombre_archivo = nombre;
        this.ruta = ruta;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }
    
    

    public int getIdFile() {
        return idFile;
    }

    public void setIdFile(int idFile) {
        this.idFile = idFile;
    }

    public String getNombreArchivo() {
        return nombre_archivo;    }

    public void setNombreArchivo(String nombre) {
        this.nombre_archivo = nombre;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }
    
    
    
    
}
