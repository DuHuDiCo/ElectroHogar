
package Dominio;

import java.sql.Date;


public class Archivo {
    
    private int idFile;
    private String nombre;
    private String ruta;
    private Date fecha;
    private int id_usuario;

    public Archivo() {
    }

    public Archivo(String nombre, String ruta, Date fecha, int id_usuario) {
        this.nombre = nombre;
        this.ruta = ruta;
        this.fecha = fecha;
        this.id_usuario = id_usuario;
    }
    
    

    public Archivo(int idFile, String nombre, String ruta) {
        this.idFile = idFile;
        this.nombre = nombre;
        this.ruta = ruta;
    }

    public Archivo(String nombre, String ruta) {
        this.nombre = nombre;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }
    
    
    
    
}
