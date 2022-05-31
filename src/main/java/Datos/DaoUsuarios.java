package Datos;

import Dominio.Usuario;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.joda.time.DateTime;

public class DaoUsuarios {
    
    private static final String SQL_SELECT_USUARIOS = "SELECT usuario.idUsuario, usuario.nombre, usuario.email, usuario.n_documento, usuario.telefono, usuario.estado_conexion, usuario.status, usuario.ultima_sesion, sede.nombre_sede FROM usuario INNER JOIN sede ON usuario.id_sede = sede.idSede";
    
    public List<Usuario> listarUsuarios() throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Usuario user;

        List<Usuario> usuarios = new ArrayList<>();

        try {
            con = Conexion.getConnection();
            stmt = con.prepareStatement(SQL_SELECT_USUARIOS);

            rs = stmt.executeQuery();

            while (rs.next()) {
                int idUsuario = rs.getInt("idUsuario");
                String nombreUsuario = rs.getString("nombre");
                String email = rs.getString("email");
                String n_documento = rs.getString("n_documento");
                String telefono = rs.getString("telefono");
                String estado_conexion = rs.getString("estado_conexion");
                int status = rs.getInt("status");
                String ultima_sesion = rs.getString("ultima_sesion");
                String sede = rs.getString("nombre_sede");

                user = new Usuario(idUsuario, nombreUsuario, n_documento, email, telefono, estado_conexion, ultima_sesion, status, status, sede);
                usuarios.add(user);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(con);
            Conexion.close(stmt);
            Conexion.close(rs);
        }
        return usuarios;
    }
    
}
