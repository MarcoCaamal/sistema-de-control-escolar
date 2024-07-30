package ce22test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author marco
 */
public class Conexion {
    public Connection cnx;
    public ResultSet rs;
    public PreparedStatement ps;
    
    public Connection conectar() {
        try {
            this.cnx = DriverManager.getConnection("jdbc:mysql://localhost/controlescolar", "root", "");
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERROR HA OCCURIDO AL CONECTAR");
        }
        return this.cnx;
    }
    
    public void desconectar() {
        try {
            this.cnx.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERROR AL DESCONECTAR");
        }
    }
}
