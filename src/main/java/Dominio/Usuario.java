
package Dominio;

import java.sql.Date;


public class Usuario {
    
    private int idUsuario;
    private String nombre;
    private String tipo_documento;
    private String n_documento;
    private String email;
    private String password;
    private String telefono;
    private Date fecha_creacion;
    private String estado_conexion;
    private Date ultima_sesion;
    private String status;
    private int id_rol;
    private int id_sede;
    private String nombre_rol;

    public Usuario() {
    }

   
    

    public Usuario(String email, String password, String nombre_rol) {
        this.email = email;
        this.password = password;
        this.nombre_rol = nombre_rol;
    }

    public String getNombre_rol() {
        return nombre_rol;
    }

    public void setNombre_rol(String nombre_rol) {
        this.nombre_rol = nombre_rol;
    }

    
    
    
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo_documento() {
        return tipo_documento;
    }

    public void setTipo_documento(String tipo_documento) {
        this.tipo_documento = tipo_documento;
    }

    public String getN_documento() {
        return n_documento;
    }

    public void setN_documento(String n_documento) {
        this.n_documento = n_documento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Date getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(Date fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public String getEstado_conexion() {
        return estado_conexion;
    }

    public void setEstado_conexion(String estado_conexion) {
        this.estado_conexion = estado_conexion;
    }

    public Date getUltima_sesion() {
        return ultima_sesion;
    }

    public void setUltima_sesion(Date ultima_sesion) {
        this.ultima_sesion = ultima_sesion;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId_rol() {
        return id_rol;
    }

    public void setId_rol(int id_rol) {
        this.id_rol = id_rol;
    }

    public int getId_sede() {
        return id_sede;
    }

    public void setId_sede(int id_sede) {
        this.id_sede = id_sede;
    }
    
    
    
    
}
