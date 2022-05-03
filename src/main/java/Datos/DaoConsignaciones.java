
package Datos;

import Dominio.Consignacion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DaoConsignaciones {
    
    private static final String SQL_SELECT_CONSIGNACIONES = "SELECT consignacion.idConsignacion, consignacion.num_recibo, consignacion.fecha_creacion, consignacion.fecha_pago, consignacion.valor, actualizacion.fecha_actualizacion, estado.nombre_estado, plataforma.nombre_plataforma FROM consignacion INNER JOIN actualizacion ON consignacion.id_actualizacion = actualizacion.idActualizacion INNER JOIN estado ON actualizacion.id_estado = estado.idEstado INNER JOIN plataforma ON consignacion.id_plataforma = plataforma.idPlataforma ORDER BY consignacion.fecha_creacion DESC";
    
    
    
    
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
                

                consignaciones = new Consignacion(idConsignacion, num_recibo, fecha_creacion, fecha_pago, valor, fecha_actualizacion, nombre_estado ,nombre_plataforma);
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
}
