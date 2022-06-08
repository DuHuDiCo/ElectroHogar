

package Datos;

import Dominio.Observaciones;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoObservacion {
    
    private static final String SQL_INSERT_OBSERVACIONES = "INSERT INTO observaciones(observacion, fecha, id_usuario, id_consignacion) VALUES (?,NOW(),?,?)";
    private static final String SQL_SELECT_IDOBSERVACION = "SELECT MAX(idObservaciones) FROM observaciones ";
    
    
    
    
    
    
    
    public int guardarObservacion(Observaciones obs) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stmt = null;

        int rown = 0;
        try {
            con = Conexion.getConnection();
            stmt = con.prepareStatement(SQL_INSERT_OBSERVACIONES);
            stmt.setString(1, obs.getObservacion());
           
            stmt.setInt(2, obs.getId_usuario());
            stmt.setInt(3, obs.getId_consignacion());
            

            rown = stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(con);
            Conexion.close(stmt);

        }
        return obtenerIdObservacion();
    }
    
    
     public int obtenerIdObservacion() throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        int rown = 0;
        try {
            con = Conexion.getConnection();
            stmt = con.prepareStatement(SQL_SELECT_IDOBSERVACION);
            

            rs = stmt.executeQuery();
            
             while (rs.next()) {
                int idObservacion = rs.getInt("MAX(idObservaciones)");
                
                rown = idObservacion;
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(con);
            Conexion.close(stmt);

        }
        return rown;
    }
}
