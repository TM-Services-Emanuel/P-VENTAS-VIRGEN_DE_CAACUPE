package Componentes;


import java.sql.DriverManager;
import java.sql.SQLException;
import org.mariadb.jdbc.MariaDbConnection;

public class ConexionBD_3 {
    
    private ConexionBD_3(){
        
    }
    
    
    private static MariaDbConnection conexion;
    
    private static ConexionBD_3 instancia;
    
    private static final String URL = "jdbc:mariadb://127.0.0.1:3306/p-ventabd_v_c";
    //private final String URL = "jdbc:mariadb://192.168.0.1:3306/p-ventabd_v_c";
    private static final String URL_movil = "jdbc:mariadb://127.0.0.1:3306/bd_v_c";
    //private final String URL_movil = "jdbc:mariadb://192.168.0.1:3306/bd_v_c";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public MariaDbConnection getConexion() {
        try {
            //MySQL
            Class.forName("org.mariadb.jdbc.Driver");
            conexion = (MariaDbConnection) DriverManager.getConnection(URL,USER,PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            conexion = null;
            Mensajes.error("Error conexión: "+e.getMessage());
        }
        return conexion;
    }
    
    public void CerrarConexion() throws SQLException{
        try{
            conexion.close();
        }catch(SQLException e){
            Mensajes.error("Error cerrando conexión");
            conexion.close();
        }
    }
    
    //patron singleton
    public static ConexionBD_3 getInstancia(){
        if(instancia == null){
            instancia = new ConexionBD_3();
        }
        return instancia;
    }

}
