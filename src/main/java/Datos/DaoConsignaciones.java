
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
    private static final String SQL_SELECT_CONSIGNACIONESBYESTADO = "SELECT consignacion.idConsignacion, consignacion.num_recibo, consignacion.fecha_creacion, consignacion.fecha_pago, consignacion.valor, actualizacion.fecha_actualizacion, estado.nombre_estado, plataforma.nombre_plataforma, obligacion.nombre_titular FROM consignacion INNER JOIN actualizacion ON consignacion.id_actualizacion = actualizacion.idActualizacion INNER JOIN estado ON actualizacion.id_estado = estado.idEstado INNER JOIN plataforma ON consignacion.id_plataforma = plataforma.idPlataforma INNER JOIN obligacion ON consignacion.id_obligacion = obligacion.idObligacion WHERE estado.nombre_estado = ? ORDER BY consignacion.fecha_creacion DESC ";
    private static final String SQL_SELECT_CONSIGNACIONESBYCEDULA = "SELECT consignacion.idConsignacion, consignacion.num_recibo, consignacion.fecha_creacion, consignacion.fecha_pago, consignacion.valor, actualizacion.fecha_actualizacion, estado.nombre_estado, plataforma.nombre_plataforma, obligacion.nombre_titular FROM consignacion INNER JOIN actualizacion ON consignacion.id_actualizacion = actualizacion.idActualizacion INNER JOIN estado ON actualizacion.id_estado = estado.idEstado INNER JOIN plataforma ON consignacion.id_plataforma = plataforma.idPlataforma INNER JOIN obligacion ON consignacion.id_obligacion = obligacion.idObligacion WHERE obligacion.n_documento = ? ORDER BY consignacion.fecha_creacion DESC";
    private static final String SQL_SELECT_CLIENTEBYID = "SELECT obligacion.idObligacion, obligacion.nombre_titular, obligacion.saldo_capital, obligacion.fecha_obligacion, sede.idSede, sede.nombre_sede FROM obligacion INNER JOIN sede ON obligacion.id_sede = sede.idSede WHERE n_documento = ?";
    
    
    
    
    public List<Consignacion> listarConsignaciones() throws ClassNotFoundException{
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
                

                consignaciones = new Consignacion(idConsignacion, num_recibo, fecha_creacion, fecha_pago, valor, fecha_actualizacion, nombre_estado ,nombre_plataforma, nombre_titular);
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
    
    
    public List<Consignacion> listarConsignacionesByEstado(String estado) throws ClassNotFoundException{
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
                

                consignaciones = new Consignacion(idConsignacion, num_recibo, fecha_creacion, fecha_pago, valor, fecha_actualizacion, nombre_estado ,nombre_plataforma, nombre_titular);
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
    
    
    public List<Consignacion> listarConsignacionesByCedula(String cedula) throws ClassNotFoundException{
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
                

                consignaciones = new Consignacion(idConsignacion, num_recibo, fecha_creacion, fecha_pago, valor, fecha_actualizacion, nombre_estado ,nombre_plataforma, nombre_titular);
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
    
    
    public List<Obligaciones> listarClienteByCedula(String cedula) throws ClassNotFoundException{
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
}
