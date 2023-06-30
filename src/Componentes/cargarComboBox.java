package Componentes;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import org.mariadb.jdbc.MariaDbConnection;
import org.mariadb.jdbc.MariaDbStatement;
public class cargarComboBox {
    
    static DefaultComboBoxModel modeloCombo;
    
    public static void cargar(JComboBox cb, String sql)
    {
        try {
            try (MariaDbConnection con = (MariaDbConnection) new ConexionBD().getConexion() //Nos conectamos
            ) {
                MariaDbStatement st = (MariaDbStatement) con.createStatement();
                try (ResultSet rs = (ResultSet) st.executeQuery(sql)) {
                    modeloCombo = new DefaultComboBoxModel();
                    modeloCombo.addElement("SELEC. UNA OPCIÓN");
                    while(rs.next()){//recorremos la tabla
                        //Agregamos al combo los valores
                        modeloCombo.addElement(new Combo(Integer.parseInt(rs.getString(1)), rs.getString(2)));
                    }   cb.setModel(modeloCombo);
                    //Cerramos el recorrido
                    //Cerramos la conexion
                    rs.close();
                }
                st.close();
                con.close();
            }
        } catch (NumberFormatException | SQLException e) {
            //Excepcion en caso haya conexion
            System.out.println("Algunos formularios no estan activos, para actualizarse, o no hay conexión");
        }
    }
    
    public static void cargar2(JComboBox cb, String sql)
    {
        try {
            try (MariaDbConnection con = (MariaDbConnection) new ConexionBD().getConexion() //Nos conectamos
            ) {
                MariaDbStatement st = (MariaDbStatement) con.createStatement();
                try (ResultSet rs = (ResultSet) st.executeQuery(sql)) {
                    modeloCombo = new DefaultComboBoxModel();
                    modeloCombo.addElement("TODOS LOS VENDEDORES");
                    while(rs.next()){//recorremos la tabla
                        //Agregamos al combo los valores
                        modeloCombo.addElement(new Combo(Integer.parseInt(rs.getString(1)), rs.getString(2)));
                    }   cb.setModel(modeloCombo);
                    //Cerramos el recorrido
                    //Cerramos la conexion
                    rs.close();
                }
                st.close();
                con.close();
            }
        } catch (NumberFormatException | SQLException e) {
            //Excepcion en caso haya conexion
            System.out.println("Algunos formularios no estan activos, para actualizarse, o no hay conexión");
        }
    }
    public static void cargarCliente(JComboBox cb, String sql)
    {
        try {
            try (MariaDbConnection con = (MariaDbConnection) new ConexionBD().getConexion() //Nos conectamos
            ) {
                MariaDbStatement st = (MariaDbStatement) con.createStatement();
                try (ResultSet rs = (ResultSet) st.executeQuery(sql)) {
                    modeloCombo = new DefaultComboBoxModel();
                    modeloCombo.addElement("SELECCIONE UN CLIENTE");
                    while(rs.next()){//recorremos la tabla
                        //Agregamos al combo los valores
                        modeloCombo.addElement(new Combo(Integer.parseInt(rs.getString(1)),rs.getString(4)+" - "+ rs.getString(3)));
                    }   cb.setModel(modeloCombo);
                    //Cerramos el recorrido
                    //Cerramos la conexion
                    rs.close();
                }
                st.close();
                con.close();
            }
        } catch (NumberFormatException | SQLException e) {
            //Excepcion en caso haya conexion
            System.out.println("Algunos formularios no estan activos, para actualizarse, o no hay conexión");
        }
    }
    public static void cargarResponsable(JComboBox cb, String sql)
    {
        try {
            try (MariaDbConnection con = (MariaDbConnection) new ConexionBD().getConexion() //Nos conectamos
            ) {
                MariaDbStatement st = (MariaDbStatement) con.createStatement();
                try (ResultSet rs = (ResultSet) st.executeQuery(sql)) {
                    modeloCombo = new DefaultComboBoxModel();
                    modeloCombo.addElement("SELECCIONE UN RESPONSABLE");
                    while(rs.next()){//recorremos la tabla
                        //Agregamos al combo los valores
                        modeloCombo.addElement(new Combo(Integer.parseInt(rs.getString(1)),rs.getString(6)));
                    }   cb.setModel(modeloCombo);
                    //Cerramos el recorrido
                    //Cerramos la conexion
                    rs.close();
                }
                st.close();
                con.close();
            }
        } catch (NumberFormatException | SQLException e) {
            //Excepcion en caso haya conexion
            System.out.println("Algunos formularios no estan activos, para actualizarse, o no hay conexión");
        }
    }
    
    public static void cargarFuncionarios(JComboBox cb, String sql)
    {
        try {
            try (MariaDbConnection con = (MariaDbConnection) new ConexionBD().getConexion() //Nos conectamos
            ) {
                MariaDbStatement st = (MariaDbStatement) con.createStatement();
                try (ResultSet rs = (ResultSet) st.executeQuery(sql)) {
                    modeloCombo = new DefaultComboBoxModel();
                    modeloCombo.addElement("SELECCIONE UN FUNCIONARIO");
                    while(rs.next()){//recorremos la tabla
                        //Agregamos al combo los valores
                        modeloCombo.addElement(new Combo(Integer.parseInt(rs.getString(1)),rs.getString(6)));
                    }   cb.setModel(modeloCombo);
                    //Cerramos el recorrido
                    //Cerramos la conexion
                    rs.close();
                }
                st.close();
                con.close();
            }
        } catch (NumberFormatException | SQLException e) {
            //Excepcion en caso haya conexion
            System.out.println("Algunos formularios no estan activos, para actualizarse, o no hay conexión");
        }
    }
    public static void cargarList(JList cb, String sql)
    {
        try {
            try (MariaDbConnection con = (MariaDbConnection) new ConexionBD().getConexion() //Nos conectamos
            ) {
                MariaDbStatement st = (MariaDbStatement) con.createStatement();
                try (ResultSet rs = (ResultSet) st.executeQuery(sql)) {
                    modeloCombo = new DefaultComboBoxModel();
                    cb.setModel(modeloCombo);
                    while (rs.next()) {
                        //recorremos la tabla
                        //Agregamos al combo los valores
                        modeloCombo.addElement(new Combo(Integer.parseInt(rs.getString(1)), rs.getString(2)));
                    }
                    //Cerramos el recorrido
                    //Cerramos la conexion
                    rs.close();
                }
                st.close();
                con.close();
            }
        } catch (Exception e) {
            //Excepcion en caso haya conexion
            System.out.println("Algunos formularios no estan activos, para actualizarse, o no hay conexión");
        }
    }
    public static void cargarProv(JComboBox cb, String sql)
    {
        try {
            try (MariaDbConnection con = (MariaDbConnection) new ConexionBD().getConexion() //Nos conectamos
            ) {
                MariaDbStatement st = (MariaDbStatement) con.createStatement();
                try (ResultSet rs = (ResultSet) st.executeQuery(sql)) {
                    modeloCombo = new DefaultComboBoxModel();
                    cb.setModel(modeloCombo);
                    while (rs.next()) {
                        //recorremos la tabla
                        //Agregamos al combo los valores
                        modeloCombo.addElement(new Combo(Integer.parseInt(rs.getString(1)), rs.getString(2)+" -- RUC: "+rs.getString(3)));
                    }
                    //Cerramos el recorrido
                    //Cerramos la conexion
                    rs.close();
                }
                st.close();
                con.close();
            }
        } catch (Exception e) {
            //Excepcion en caso haya conexion
            System.out.println("Algunos formularios no estan activos, para actualizarse, o no hay conexión");
        }
    }
    public static void cargarCli(JComboBox cb, String sql)
    {
        try {
            try (MariaDbConnection con = (MariaDbConnection) new ConexionBD().getConexion() //Nos conectamos
            ) {
                MariaDbStatement st = (MariaDbStatement) con.createStatement();
                try (ResultSet rs = (ResultSet) st.executeQuery(sql)) {
                    modeloCombo = new DefaultComboBoxModel();
                    modeloCombo.addElement("SELEC. UNA OPCIÓN");
                    cb.setModel(modeloCombo);
                    while (rs.next()) {
                        //recorremos la tabla
                        //Agregamos al combo los valores
                        modeloCombo.addElement(new Combo(Integer.parseInt(rs.getString(1)), rs.getString(3)+" -- RUC/C.I.: "+rs.getString(4)));
                    }
                    //Cerramos el recorrido
                    //Cerramos la conexion
                    rs.close();
                }
                st.close();
                con.close();
            }
        } catch (Exception e) {
            //Excepcion en caso haya conexion
            System.out.println("Algunos formularios no estan activos, para actualizarse, o no hay conexión");
        }
    }
    
    public static void cargarCaja(JComboBox cb, String sql)
    {
        try {
            try (MariaDbConnection con = (MariaDbConnection) new ConexionBD().getConexion() //Nos conectamos
            ) {
                MariaDbStatement st = (MariaDbStatement) con.createStatement();
                try (ResultSet rs = (ResultSet) st.executeQuery(sql)) {
                    modeloCombo = new DefaultComboBoxModel();
                    modeloCombo.addElement("SELEC.");
                    while(rs.next()){//recorremos la tabla
                        //Agregamos al combo los valores
                        //modeloCombo.addElement(new Combo(Integer.parseInt(rs.getString(1)), rs.getString(1)));
                        modeloCombo.addElement(rs.getString(1));
                    }   cb.setModel(modeloCombo);
                    //Cerramos el recorrido
                    //Cerramos la conexion
                    rs.close();
                }
                st.close();
                con.close();
            }
        } catch (NumberFormatException | SQLException e) {
            //Excepcion en caso haya conexion
            System.out.println("Algunos formularios no estan activos, para actualizarse, o no hay conexión");
        }
    }
    
    public static String getCodidgo(JComboBox cb)//Metodo para Obtener el id
    {
        Combo c = (Combo)cb.getSelectedItem();//Seleccionamos
        int id = c.getCodigo();//Obtenermos el id
        //System.out.println(id);
        return String.valueOf(id);//Retornamos el codigo
    }
    
    public static String getDesc(JComboBox cb)//Metodo para Obtener el id
    {
        Combo c = (Combo)cb.getSelectedItem();//Seleccionamos
        String id = c.getDesc();//Obtenermos el id
        //System.out.println(id);
        return (id);//Retornamos el codigo
    }
    public static String getCodidgoL(JList cb)//Metodo para Obtener el id
    {
        Combo c = (Combo)cb.getSelectedValue();//Seleccionamos
        int id = c.getCodigo();//Obtenermos el id
        //System.out.println(id);
        return String.valueOf(id);//Retornamos el codigo
    }
    public static String getDescList(JList cb)//Metodo para Obtener el id
    {
        Combo c = (Combo)cb.getSelectedValue();//Seleccionamos
        String des = c.getDesc();//Obtenermos el id
        //System.out.println(des);
        return String.valueOf(des);//Retornamos el codigo
    }
    
}