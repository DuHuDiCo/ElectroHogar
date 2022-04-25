package Datos;

import Dominio.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Dao {

    private static final String SQL_SELECT_INICIARSESION = "SELECT usuario.email, usuario.password, rol.nombre_rol FROM usuario INNER JOIN rol ON usuario.id_rol = rol.idRol WHERE usuario.email = ?";

    public Usuario iniciarSesion(String email) throws ClassNotFoundException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Usuario user = null;

        try {
            con = Conexion.getConnection();
            stmt = con.prepareStatement(SQL_SELECT_INICIARSESION);
            stmt.setString(1, email);

            rs = stmt.executeQuery();

            boolean valid = rs.next();
            if (valid) {
                String correo = rs.getString("email");
                String password = rs.getString("password");
                String nombreRol = rs.getString("nombre_rol");
                
                user = new Usuario(email, password, nombreRol);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(con);
            Conexion.close(stmt);
            Conexion.close(rs);
        }
        return user;
    }

}
