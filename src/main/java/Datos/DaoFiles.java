
package Datos;

import Dominio.Archivo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DaoFiles {
    
    
    private static final String SQL_INSERT_ARCHIVOTXT = "INSERT INTO filestxt(nombre, ruta, fecha, id_usuario) VALUES (?,?,?,?)";
    private static final String SQL_SELECT_OBTENERIDTXT = "SELECT idFile FROM filestxt WHERE nombre = ?"; 
    
    
    
    public int guardarArchivoTxt(Archivo file) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stmt = null;

        int rown = 0;
        try {
            con = Conexion.getConnection();
            stmt = con.prepareStatement(SQL_INSERT_ARCHIVOTXT);
            stmt.setString(1, file.getNombre());
            stmt.setString(2, file.getRuta());
            stmt.setDate(3, file.getFecha());
            stmt.setInt(4, file.getId_usuario());
            

            rown = stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(con);
            Conexion.close(stmt);

        }
        return rown;
    }
    
    
    public int obtenerIdFileTxt(String name) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        int rown = 0;
        try {
            con = Conexion.getConnection();
            stmt = con.prepareStatement(SQL_SELECT_OBTENERIDTXT);
            stmt.setString(1, name);

            rs = stmt.executeQuery();
            
             while (rs.next()) {
                int idFileTxt = rs.getInt("idFile");
                
                rown = idFileTxt;
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
