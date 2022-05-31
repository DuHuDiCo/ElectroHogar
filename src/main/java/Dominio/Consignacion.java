
package Dominio;

import java.sql.Date;


public class Consignacion {
    private int idConsignacion;
    private String num_recibo;
    private Date fecha_creacion;
    private Date fecha_pago;
    private float valor;
    private int id_files;
    private String nombre;
    private String ruta;
    private int id_actualizacion;
    private Date fecha_actualizacion;
    private int id_estado;
    private String nombre_estado;
    private int id_usuario;
    private int id_plataforma;
    private String nombre_plataforma;
    private int id_tipoPago;
    private String tipo_pago;
    private int id_obligacion;
    private String nombre_titular;
    private String nombre_sede;

    public Consignacion() {
        
        
    }

    public Consignacion(int idConsignacion, String num_recibo, Date fecha_creacion, Date fecha_pago, float valor, Date fecha_actualizacion, String nombre_estado ,String nombre_plataforma, String nombre_titular, String nombre_sede) {
        this.idConsignacion = idConsignacion;
        this.num_recibo = num_recibo;
        this.fecha_creacion = fecha_creacion;
        this.fecha_pago = fecha_pago;
        this.valor = valor;
        this.fecha_actualizacion = fecha_actualizacion;
        this.nombre_estado = nombre_estado;
        this.nombre_plataforma = nombre_plataforma;
        this.nombre_titular = nombre_titular;
        this.nombre_sede = nombre_sede;
    }

    public Consignacion(int idConsignacion, String num_recibo, Date fecha_creacion, Date fecha_pago, float valor, Date fecha_actualizacion, String nombre_estado, String nombre_plataforma, String nombre_titular) {
        this.idConsignacion = idConsignacion;
        this.num_recibo = num_recibo;
        this.fecha_creacion = fecha_creacion;
        this.fecha_pago = fecha_pago;
        this.valor = valor;
        this.fecha_actualizacion = fecha_actualizacion;
        this.nombre_estado = nombre_estado;
        this.nombre_plataforma = nombre_plataforma;
        this.nombre_titular = nombre_titular;
    }
    
    
    
    
    

    public Consignacion(String num_recibo, Date fecha_creacion, Date fecha_pago, float valor, int id_files, int id_actualizacion,  int id_usuario, int id_plataforma, int id_obligacion) {
        this.num_recibo = num_recibo;
        this.fecha_creacion = fecha_creacion;
        this.fecha_pago = fecha_pago;
        this.valor = valor;
        this.id_files = id_files;
        this.id_actualizacion = id_actualizacion;
        
        this.id_usuario = id_usuario;
        this.id_plataforma = id_plataforma;
        this.id_obligacion = id_obligacion;
    }
    
    

    public Consignacion(int idConsignacion, String num_recibo, Date fecha_creacion, Date fecha_pago, float valor, int id_files, String nombre, String ruta, int id_actualizacion, Date fecha_actualizacion, int id_estado, String nombre_estado, int id_usuario, int id_plataforma, String nombre_plataforma, int id_tipoPago, String tipo_pago) {
        this.idConsignacion = idConsignacion;
        this.num_recibo = num_recibo;
        this.fecha_creacion = fecha_creacion;
        this.fecha_pago = fecha_pago;
        this.valor = valor;
        this.id_files = id_files;
        this.nombre = nombre;
        this.ruta = ruta;
        this.id_actualizacion = id_actualizacion;
        this.fecha_actualizacion = fecha_actualizacion;
        this.id_estado = id_estado;
        this.nombre_estado = nombre_estado;
        this.id_usuario = id_usuario;
        this.id_plataforma = id_plataforma;
        this.nombre_plataforma = nombre_plataforma;
        this.id_tipoPago = id_tipoPago;
        this.tipo_pago = tipo_pago;
    }

    public int getId_obligacion() {
        return id_obligacion;
    }

    public void setId_obligacion(int id_obligacion) {
        this.id_obligacion = id_obligacion;
    }

    public String getNombre_titular() {
        return nombre_titular;
    }

    public void setNombre_titular(String nombre_titular) {
        this.nombre_titular = nombre_titular;
    }

    public String getNombre_sede() {
        return nombre_sede;
    }

    public void setNombre_sede(String nombre_sede) {
        this.nombre_sede = nombre_sede;
    }
    
    
    
    

    public int getIdConsignacion() {
        return idConsignacion;
    }

    public void setIdConsignacion(int idConsignacion) {
        this.idConsignacion = idConsignacion;
    }

    public String getNum_recibo() {
        return num_recibo;
    }

    public void setNum_recibo(String num_recibo) {
        this.num_recibo = num_recibo;
    }

    public Date getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(Date fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public Date getFecha_pago() {
        return fecha_pago;
    }

    public void setFecha_pago(Date fecha_pago) {
        this.fecha_pago = fecha_pago;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public int getId_files() {
        return id_files;
    }

    public void setId_files(int id_files) {
        this.id_files = id_files;
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

    public int getId_actualizacion() {
        return id_actualizacion;
    }

    public void setId_actualizacion(int id_actualizacion) {
        this.id_actualizacion = id_actualizacion;
    }

    public Date getFecha_actualizacion() {
        return fecha_actualizacion;
    }

    public void setFecha_actualizacion(Date fecha_actualizacion) {
        this.fecha_actualizacion = fecha_actualizacion;
    }

    public int getId_estado() {
        return id_estado;
    }

    public void setId_estado(int id_estado) {
        this.id_estado = id_estado;
    }

    public String getNombre_estado() {
        return nombre_estado;
    }

    public void setNombre_estado(String nombre_estado) {
        this.nombre_estado = nombre_estado;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getId_plataforma() {
        return id_plataforma;
    }

    public void setId_plataforma(int id_plataforma) {
        this.id_plataforma = id_plataforma;
    }

    public String getNombre_plataforma() {
        return nombre_plataforma;
    }

    public void setNombre_plataforma(String nombre_plataforma) {
        this.nombre_plataforma = nombre_plataforma;
    }

    public int getId_tipoPago() {
        return id_tipoPago;
    }

    public void setId_tipoPago(int id_tipoPago) {
        this.id_tipoPago = id_tipoPago;
    }

    public String getTipo_pago() {
        return tipo_pago;
    }

    public void setTipo_pago(String tipo_pago) {
        this.tipo_pago = tipo_pago;
    }
    
    
    
    
}
