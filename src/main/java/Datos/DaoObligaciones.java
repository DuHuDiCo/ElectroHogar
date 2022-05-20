
package Datos;

import Dominio.Obligaciones;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DaoObligaciones {
    
    private static final String SQL_INSERT_OBLIGACIONES = "INSERT INTO obligacion(nombre_titular, tipo_documento, n_documento, telefono, email, direccion, " 
            +"clasificacion_cliente, codigo_cliente, valor_cuota, saldo_capital, saldo_mora, dias_mora, id_sede, id_filesTxt) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String SQL_INSERT_OBBLIGACIONPORCLIENTE = "INSERT INTO obligacion(nombre_titular, n_documento ,id_sede) VALUES (?,?,?)";
    private static final String SQL_SELECT_IDOBLIGACIONCREAD = "SELECT idObligacion FROM obligacion WHERE n_documento = ?";
    
    
    public int guardarObligaciones (Obligaciones obliga) throws ClassNotFoundException, SQLException{
        Connection con = null;
        PreparedStatement stmt = null;

        int rown = 0;
        try {
            con = Conexion.getConnection();
            stmt = con.prepareStatement(SQL_INSERT_OBLIGACIONES);
            stmt.setString(1, obliga.getNombre_titular());
            stmt.setString(2, obliga.getTipo_documento());
            stmt.setString(3, obliga.getN_documento());
            stmt.setString(4, obliga.getTelefono_titular());
            stmt.setString(5, obliga.getEmail());
            stmt.setString(6, obliga.getDireccion());
            stmt.setString(7, obliga.getClasificacion_cliente());
            stmt.setString(8, obliga.getCodigo_cliente());
            stmt.setFloat(9, obliga.getValor_cuota());
            stmt.setFloat(10, obliga.getSaldo_capital());
            stmt.setFloat(11, obliga.getSaldo_mora());
            stmt.setInt(12, obliga.getDias_mora());
            stmt.setInt(13, obliga.getId_sede());
            stmt.setInt(14, obliga.getId_fileTxt());           
            
            
            rown = stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(con);
            Conexion.close(stmt);

        }
        return rown;
    }
    
    
    public int guardarObligacionPorCliente(String cliente, int id_sede, String cedula) throws ClassNotFoundException, SQLException{
        Connection con = null;
        PreparedStatement stmt = null;

        int rown = 0;
        try {
            con = Conexion.getConnection();
            stmt = con.prepareStatement(SQL_INSERT_OBBLIGACIONPORCLIENTE);
            stmt.setString(1, cliente);
            stmt.setString(2, cedula);
            stmt.setInt(3, id_sede);
               
            
            
            rown = stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(con);
            Conexion.close(stmt);

        }
        return rown;
    }
    
    
    public int obtenerIdObligacionCreada(String cedula) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        int rown = 0;
        try {
            con = Conexion.getConnection();
            stmt = con.prepareStatement(SQL_SELECT_IDOBLIGACIONCREAD);
            stmt.setString(1, cedula);

            rs = stmt.executeQuery();
            
             while (rs.next()) {
                int idObligacion = rs.getInt("idObligacion");
                
                rown = idObligacion;
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
