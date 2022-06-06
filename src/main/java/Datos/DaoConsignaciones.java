package Datos;

import Dominio.Consignacion;
import Dominio.Obligaciones;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoConsignaciones {

    private static final String SQL_SELECT_CONSIGNACIONES = "SELECT consignacion.idConsignacion, consignacion.num_recibo, consignacion.fecha_creacion, consignacion.fecha_pago, consignacion.valor, actualizacion.fecha_actualizacion, estado.nombre_estado, plataforma.nombre_plataforma, obligacion.nombre_titular FROM consignacion INNER JOIN actualizacion ON consignacion.id_actualizacion = actualizacion.idActualizacion INNER JOIN estado ON actualizacion.id_estado = estado.idEstado INNER JOIN plataforma ON consignacion.id_plataforma = plataforma.idPlataforma INNER JOIN obligacion ON consignacion.id_obligacion = obligacion.idObligacion ORDER BY consignacion.fecha_creacion DESC";
    private static final String SQL_SELECT_CONSIGNACIONESBYESTADO = "SELECT consignacion.idConsignacion, consignacion.num_recibo, consignacion.fecha_creacion, consignacion.fecha_pago, consignacion.valor, actualizacion.fecha_actualizacion, estado.nombre_estado, plataforma.nombre_plataforma, obligacion.nombre_titular, sede.nombre_sede FROM consignacion INNER JOIN actualizacion ON consignacion.id_actualizacion = actualizacion.idActualizacion INNER JOIN estado ON actualizacion.id_estado = estado.idEstado INNER JOIN plataforma ON consignacion.id_plataforma = plataforma.idPlataforma INNER JOIN obligacion ON consignacion.id_obligacion = obligacion.idObligacion INNER JOIN sede ON obligacion.id_sede = sede.idSede WHERE estado.nombre_estado = ? ORDER BY consignacion.fecha_creacion DESC ";
    private static final String SQL_SELECT_CONSIGNACIONESBYCEDULA = "SELECT consignacion.idConsignacion, consignacion.num_recibo, consignacion.fecha_creacion, consignacion.fecha_pago, consignacion.valor, actualizacion.fecha_actualizacion, estado.nombre_estado, plataforma.nombre_plataforma, obligacion.nombre_titular, sede.nombre_sede FROM consignacion INNER JOIN actualizacion ON consignacion.id_actualizacion = actualizacion.idActualizacion INNER JOIN estado ON actualizacion.id_estado = estado.idEstado INNER JOIN plataforma ON consignacion.id_plataforma = plataforma.idPlataforma INNER JOIN obligacion ON consignacion.id_obligacion = obligacion.idObligacion INNER JOIN sede ON obligacion.id_sede = sede.idSede WHERE obligacion.n_documento = ? ORDER BY consignacion.fecha_creacion DESC";
    private static final String SQL_SELECT_CLIENTEBYID = "SELECT obligacion.idObligacion, obligacion.nombre_titular, obligacion.saldo_capital, obligacion.fecha_obligacion, sede.idSede, sede.nombre_sede FROM obligacion INNER JOIN sede ON obligacion.id_sede = sede.idSede WHERE n_documento = ?";
    private static final String SQL_UPDATE_CONSIGNACION = "UPDATE consignacion SET id_actualizacion = ? WHERE idConsignacion = ?";
    private static final String SQL_SELECT_CONSIGNACIONESBYSEDE = "SELECT consignacion.idConsignacion, consignacion.num_recibo, consignacion.fecha_creacion, consignacion.fecha_pago, consignacion.valor, actualizacion.fecha_actualizacion, estado.nombre_estado, plataforma.nombre_plataforma, obligacion.nombre_titular, sede.nombre_sede FROM consignacion INNER JOIN actualizacion ON consignacion.id_actualizacion = actualizacion.idActualizacion INNER JOIN estado ON actualizacion.id_estado = estado.idEstado INNER JOIN plataforma ON consignacion.id_plataforma = plataforma.idPlataforma INNER JOIN obligacion ON consignacion.id_obligacion = obligacion.idObligacion INNER JOIN sede ON obligacion.id_sede = sede.idSede WHERE sede.nombre_sede = ? AND estado.nombre_estado = 'Comprobado' ORDER BY consignacion.fecha_creacion DESC ";
    private static final String SQL_SELECT_CONSIGNACIONESBYID = "SELECT * FROM consignacion WHERE idConsignacion = ?";
    private static final String SQL_INSERT_CONSIGNACIONTEMP = "INSERT INTO temporal_consignacion(idConsignacion, num_recibo, fecha_creacion, fecha_pago, valor, id_files, id_actualizacion, id_usuario, id_plataforma, id_obligacion) VALUES (?,?,?,?,?,?,?,?,?,?)";
    private static final String SQL_DELETE_CONSIGNACIONESTEMP = "DELETE FROM temporal_consignacion";
    private static final String SQL_SELECT_CONSIGNACIONESTEMP = "SELECT * FROM temporal_consignacion"; 
    private static final String SQL_SELECT_CONSIGNACIONESTEMPPDF = "SELECT temporal_consignacion.idConsignacion, temporal_consignacion.num_recibo, temporal_consignacion.fecha_creacion, temporal_consignacion.fecha_pago, temporal_consignacion.valor, actualizacion.fecha_actualizacion, estado.nombre_estado, plataforma.nombre_plataforma, obligacion.nombre_titular, sede.nombre_sede FROM temporal_consignacion INNER JOIN actualizacion ON temporal_consignacion.id_actualizacion = actualizacion.idActualizacion INNER JOIN estado ON actualizacion.id_estado = estado.idEstado INNER JOIN plataforma ON temporal_consignacion.id_plataforma = plataforma.idPlataforma INNER JOIN obligacion ON temporal_consignacion.id_obligacion = obligacion.idObligacion INNER JOIN sede ON obligacion.id_sede = sede.idSede ORDER BY temporal_consignacion.fecha_creacion DESC ";
    private static final String SQL_SELECT_NOMBREUSUARIO = "SELECT nombre FROM usuario WHERE email = ?";
    
    public List<Consignacion> listarConsignaciones() throws ClassNotFoundException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Consignacion consignaciones = null;

        List<Consignacion> consigna = new ArrayList<>();

        try {
            con = Conexion.getConnection();
            stmt = con.prepareStatement(SQL_SELECT_CONSIGNACIONES);

            rs = stmt.executeQuery();

            while (rs.next()) {
                int idConsignacion = rs.getInt("idConsignacion");
                String num_recibo = rs.getString("num_recibo");
                Date fecha_creacion = rs.getDate("fecha_creacion");
                Date fecha_pago = rs.getDate("fecha_pago");
                float valor = rs.getFloat("valor");
                Date fecha_actualizacion = rs.getDate("fecha_actualizacion");
                String nombre_estado = rs.getString("nombre_estado");
                String nombre_plataforma = rs.getString("nombre_plataforma");
                String nombre_titular = rs.getString("nombre_titular");
                String nombre_sede = rs.getString("nombre_sede");

                consignaciones = new Consignacion(idConsignacion, num_recibo, fecha_creacion, fecha_pago, valor, fecha_actualizacion, nombre_estado, nombre_plataforma, nombre_titular, nombre_sede);
                consigna.add(consignaciones);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(con);
            Conexion.close(stmt);
            Conexion.close(rs);
        }

        return consigna;

    }

    public List<Consignacion> listarConsignacionesByEstado(String estado) throws ClassNotFoundException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Consignacion consignaciones = null;

        List<Consignacion> consigna = new ArrayList<>();

        try {
            con = Conexion.getConnection();
            stmt = con.prepareStatement(SQL_SELECT_CONSIGNACIONESBYESTADO);
            stmt.setString(1, estado);

            rs = stmt.executeQuery();

            while (rs.next()) {
                int idConsignacion = rs.getInt("idConsignacion");
                String num_recibo = rs.getString("num_recibo");
                Date fecha_creacion = rs.getDate("fecha_creacion");
                Date fecha_pago = rs.getDate("fecha_pago");
                float valor = rs.getFloat("valor");
                Date fecha_actualizacion = rs.getDate("fecha_actualizacion");
                String nombre_estado = rs.getString("nombre_estado");
                String nombre_plataforma = rs.getString("nombre_plataforma");
                String nombre_titular = rs.getString("nombre_titular");
                String nombre_sede = rs.getString("nombre_sede");

                consignaciones = new Consignacion(idConsignacion, num_recibo, fecha_creacion, fecha_pago, valor, fecha_actualizacion, nombre_estado, nombre_plataforma, nombre_titular, nombre_sede);
                consigna.add(consignaciones);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(con);
            Conexion.close(stmt);
            Conexion.close(rs);
        }

        return consigna;

    }

    public List<Consignacion> listarConsignacionesByCedula(String cedula) throws ClassNotFoundException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Consignacion consignaciones = null;

        List<Consignacion> consigna = new ArrayList<>();

        try {
            con = Conexion.getConnection();
            stmt = con.prepareStatement(SQL_SELECT_CONSIGNACIONESBYCEDULA);
            stmt.setString(1, cedula);

            rs = stmt.executeQuery();

            while (rs.next()) {
                int idConsignacion = rs.getInt("idConsignacion");
                String num_recibo = rs.getString("num_recibo");
                Date fecha_creacion = rs.getDate("fecha_creacion");
                Date fecha_pago = rs.getDate("fecha_pago");
                float valor = rs.getFloat("valor");
                Date fecha_actualizacion = rs.getDate("fecha_actualizacion");
                String nombre_estado = rs.getString("nombre_estado");
                String nombre_plataforma = rs.getString("nombre_plataforma");
                String nombre_titular = rs.getString("nombre_titular");
                String nombre_sede = rs.getString("nombre_sede");

                consignaciones = new Consignacion(idConsignacion, num_recibo, fecha_creacion, fecha_pago, valor, fecha_actualizacion, nombre_estado, nombre_plataforma, nombre_titular, nombre_sede);
                consigna.add(consignaciones);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(con);
            Conexion.close(stmt);
            Conexion.close(rs);
        }

        return consigna;

    }

    public List<Obligaciones> listarClienteByCedula(String cedula) throws ClassNotFoundException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Obligaciones obliga = null;

        List<Obligaciones> obligaciones = new ArrayList<>();

        try {
            con = Conexion.getConnection();
            stmt = con.prepareStatement(SQL_SELECT_CLIENTEBYID);
            stmt.setString(1, cedula);

            rs = stmt.executeQuery();

            while (rs.next()) {
                int idObligacion = rs.getInt("idObligacion");
                String nombre_titular = rs.getString("nombre_titular");
                float saldo_capital = rs.getFloat("saldo_capital");
                Date fecha_obligacion = rs.getDate("fecha_obligacion");
                int idSede = rs.getInt("idSede");
                String nombre_sede = rs.getString("nombre_sede");

                obliga = new Obligaciones(idObligacion, nombre_titular, saldo_capital, fecha_obligacion, idSede, nombre_sede);
                obligaciones.add(obliga);

            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(con);
            Conexion.close(stmt);
            Conexion.close(rs);
        }

        return obligaciones;

    }

    public int actualizarEstadoConsig(int idActu, int idCon) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stmt = null;

        int rown = 0;
        try {
            con = Conexion.getConnection();
            stmt = con.prepareStatement(SQL_UPDATE_CONSIGNACION);
            stmt.setInt(1, idActu);
            stmt.setInt(2, idCon);

            rown = stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(con);
            Conexion.close(stmt);

        }

        return rown;

    }

    
    
    public List<Consignacion> listarConsignacionesBySede(String sede) throws ClassNotFoundException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Consignacion consignaciones = null;

        List<Consignacion> consigna = new ArrayList<>();

        try {
            con = Conexion.getConnection();
            stmt = con.prepareStatement(SQL_SELECT_CONSIGNACIONESBYSEDE);
            stmt.setString(1, sede);

            rs = stmt.executeQuery();

            while (rs.next()) {
                int idConsignacion = rs.getInt("idConsignacion");
                String num_recibo = rs.getString("num_recibo");
                Date fecha_creacion = rs.getDate("fecha_creacion");
                Date fecha_pago = rs.getDate("fecha_pago");
                float valor = rs.getFloat("valor");
                Date fecha_actualizacion = rs.getDate("fecha_actualizacion");
                String nombre_estado = rs.getString("nombre_estado");
                String nombre_plataforma = rs.getString("nombre_plataforma");
                String nombre_titular = rs.getString("nombre_titular");
                String nombre_sede = rs.getString("nombre_sede");

                consignaciones = new Consignacion(idConsignacion, num_recibo, fecha_creacion, fecha_pago, valor, fecha_actualizacion, nombre_estado, nombre_plataforma, nombre_titular, nombre_sede);
                consigna.add(consignaciones);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(con);
            Conexion.close(stmt);
            Conexion.close(rs);
        }

        return consigna;

    }
    
    public Consignacion listarConsignacionesById(int id) throws ClassNotFoundException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Consignacion consignaciones = null;

       

        try {
            con = Conexion.getConnection();
            stmt = con.prepareStatement(SQL_SELECT_CONSIGNACIONESBYID);
            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            while (rs.next()) {
                int idConsignacion = rs.getInt("idConsignacion");
                String num_recibo = rs.getString("num_recibo");
                Date fecha_creacion = rs.getDate("fecha_creacion");
                Date fecha_pago = rs.getDate("fecha_pago");
                float valor = rs.getFloat("valor");
                int id_files = rs.getInt("id_files");
                int id_actualizacion = rs.getInt("id_actualizacion");
                int id_usuario = rs.getInt("id_usuario");
                int id_plataforma = rs.getInt("id_plataforma");
                int id_obligacion = rs.getInt("id_obligacion");

                consignaciones = new Consignacion(idConsignacion, num_recibo, fecha_creacion, fecha_pago, valor, id_files, id_actualizacion, id_usuario, id_plataforma, id_obligacion);
                
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(con);
            Conexion.close(stmt);
            Conexion.close(rs);
        }

        return consignaciones;

    }
    
    
    public int guardarConsigTemp(Consignacion cons) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stmt = null;

        int rown = 0;
        try {
            con = Conexion.getConnection();
            stmt = con.prepareStatement(SQL_INSERT_CONSIGNACIONTEMP);
            stmt.setInt(1, cons.getIdConsignacion());
            stmt.setString(2, cons.getNum_recibo());
            stmt.setDate(3, cons.getFecha_creacion());
            stmt.setDate(4, cons.getFecha_pago());
            stmt.setFloat(5, cons.getValor());
            stmt.setInt(6, cons.getId_files());
            stmt.setInt(7, cons.getId_actualizacion());
            stmt.setInt(8, cons.getId_usuario());
            stmt.setInt(9, cons.getId_plataforma());
            stmt.setInt(10, cons.getId_obligacion());
            
            

            rown = stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(con);
            Conexion.close(stmt);

        }
        return rown;
    }
    
    
    public int eliminarConsigTemp() throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stmt = null;

        int rown = 0;
        try {
            con = Conexion.getConnection();
            stmt = con.prepareStatement(SQL_DELETE_CONSIGNACIONESTEMP);
            
            
            

            rown = stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(con);
            Conexion.close(stmt);

        }
        return rown;
    }
    
    
     public List<Consignacion> listarConsinacionesTemp() throws ClassNotFoundException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Consignacion consignaciones = null;

        List<Consignacion> consigna = new ArrayList<>();

        try {
            con = Conexion.getConnection();
            stmt = con.prepareStatement(SQL_SELECT_CONSIGNACIONESTEMP);
            

            rs = stmt.executeQuery();

            while (rs.next()) {
                int idConsignacion = rs.getInt("idConsignacion");
                String num_recibo = rs.getString("num_recibo");
                Date fecha_creacion = rs.getDate("fecha_creacion");
                Date fecha_pago = rs.getDate("fecha_pago");
                float valor = rs.getFloat("valor");
                int id_files = rs.getInt("id_files");
                int id_actualizacion = rs.getInt("id_actualizacion");
                int id_usuario = rs.getInt("id_usuario");
                int id_plataforma = rs.getInt("id_plataforma");
                int id_obligacion = rs.getInt("id_obligacion");

                consignaciones = new Consignacion(idConsignacion, num_recibo, fecha_creacion, fecha_pago, valor, id_files, id_actualizacion, id_usuario, id_plataforma, id_obligacion);
                consigna.add(consignaciones);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(con);
            Conexion.close(stmt);
            Conexion.close(rs);
        }

        return consigna;

    }
     
     public List<Consignacion> listarConsinacionesTempPdf() throws ClassNotFoundException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Consignacion consignaciones = null;

        List<Consignacion> consigna = new ArrayList<>();

        try {
            con = Conexion.getConnection();
            stmt = con.prepareStatement(SQL_SELECT_CONSIGNACIONESTEMPPDF);
            

            rs = stmt.executeQuery();

             while (rs.next()) {
                int idConsignacion = rs.getInt("idConsignacion");
                String num_recibo = rs.getString("num_recibo");
                Date fecha_creacion = rs.getDate("fecha_creacion");
                Date fecha_pago = rs.getDate("fecha_pago");
                float valor = rs.getFloat("valor");
                Date fecha_actualizacion = rs.getDate("fecha_actualizacion");
                String nombre_estado = rs.getString("nombre_estado");
                String nombre_plataforma = rs.getString("nombre_plataforma");
                String nombre_titular = rs.getString("nombre_titular");
                String nombre_sede = rs.getString("nombre_sede");

                consignaciones = new Consignacion(idConsignacion, num_recibo, fecha_creacion, fecha_pago, valor, fecha_actualizacion, nombre_estado, nombre_plataforma, nombre_titular, nombre_sede);
                consigna.add(consignaciones);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(con);
            Conexion.close(stmt);
            Conexion.close(rs);
        }

        return consigna;

    }
     
    public String obtenerNombreUsuario(String email) throws ClassNotFoundException{
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String nombre = null;
        try {
            con = Conexion.getConnection();
            stmt = con.prepareStatement(SQL_SELECT_NOMBREUSUARIO);
            stmt.setString(1, email);

            rs = stmt.executeQuery();

             while (rs.next()) {
                String name = rs.getString("nombre");
                nombre = name;
                
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(con);
            Conexion.close(stmt);
            Conexion.close(rs);
        }
        return nombre;
    }
}
