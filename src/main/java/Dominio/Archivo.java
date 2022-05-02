
package Dominio;


public class Archivo {
    
    private int idFile;
    private String nombre;
    private String ruta;

    public Archivo() {
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
