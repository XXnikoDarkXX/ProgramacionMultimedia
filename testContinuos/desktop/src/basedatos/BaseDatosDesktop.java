package basedatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.plaf.nimbus.State;

public class BaseDatosDesktop extends BaseDatos {

    private static final String cadenaConexion="jdbc:mysql://127.0.0.1:3306/alienvscateto";
    private static final String usuario="root";
    private  static final String password="admin";
    private static Connection conexion;
    private static Statement conectar(){
        try {
             conexion= DriverManager.getConnection(cadenaConexion,usuario,password);
        return    conexion.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    private void desconectar(){
        try {
            conexion.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    @Override
    public void cargarCuenta() {
        Statement smt=null;
        try {
         smt=conectar();
    ResultSet resultados= smt.executeQuery("select * from aliensGenerados");

            resultados.next();
        this.setAlienGenerados((short)resultados.getInt("numero"));


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                smt.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        desconectar();
    }

    @Override
    public void persistir() {
        Statement smt=null;
        try {
     smt=conectar();
        smt.executeUpdate("update aliensGenerados set numero="+this.getAlienGenerados());

            smt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {

            try {
                smt.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        desconectar();
    }
}
