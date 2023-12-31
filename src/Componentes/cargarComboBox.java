package Componentes;

import java.sql.*;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JList;

public class cargarComboBox {

    static DefaultComboBoxModel modeloCombo;
    static DataSourceService dss = new DataSourceService();

    public static void cargar(JComboBox cb, String sql) {
        try (Connection con = dss.getDataSource().getConnection(); Statement st = con.createStatement(); ResultSet rs = (ResultSet) st.executeQuery(sql)) {
            modeloCombo = new DefaultComboBoxModel();
            modeloCombo.addElement("SELEC. UNA OPCIÓN");
            while (rs.next()) {
                modeloCombo.addElement(new Combo(Integer.parseInt(rs.getString(1)), rs.getString(2)));
            }
            cb.setModel(modeloCombo);
            rs.close();
            st.close();
            con.close();
        } catch (NumberFormatException | SQLException e) {
            System.out.println("Algunos formularios no estan activos, para actualizarse, o no hay conexión");
        }
    }
    
    public static void cargarBocaCobranza(JComboBox cb, String sql) {
        try (Connection con = dss.getDataSource().getConnection(); Statement st = con.createStatement(); ResultSet rs = (ResultSet) st.executeQuery(sql)) {
            modeloCombo = new DefaultComboBoxModel();
            modeloCombo.addElement("TODAS LAS BOCAS DE COBRANZAS");
            while (rs.next()) {
                modeloCombo.addElement(new Combo(Integer.parseInt(rs.getString(1)), rs.getString(2)));
            }
            cb.setModel(modeloCombo);
            rs.close();
            st.close();
            con.close();
        } catch (NumberFormatException | SQLException e) {
            System.out.println("Algunos formularios no estan activos, para actualizarse, o no hay conexión");
        }
    }

    public static void cargar2(JComboBox cb, String sql) {
        try (Connection con = dss.getDataSource().getConnection(); Statement st = con.createStatement(); ResultSet rs = (ResultSet) st.executeQuery(sql)) {
            modeloCombo = new DefaultComboBoxModel();
            modeloCombo.addElement("TODOS LOS VENDEDORES");
            while (rs.next()) {
                modeloCombo.addElement(new Combo(Integer.parseInt(rs.getString(1)), rs.getString(2)));
            }
            cb.setModel(modeloCombo);
            rs.close();
            st.close();
            con.close();
        } catch (NumberFormatException | SQLException e) {
            System.out.println("Algunos formularios no estan activos, para actualizarse, o no hay conexión");
        }
    }

    public static void cargarCliente(JComboBox cb, String sql) {
        try (Connection con = dss.getDataSource().getConnection(); Statement st = con.createStatement(); ResultSet rs = (ResultSet) st.executeQuery(sql)) {
            modeloCombo = new DefaultComboBoxModel();
            modeloCombo.addElement("SELECCIONE UN CLIENTE");
            while (rs.next()) {
                modeloCombo.addElement(new Combo(Integer.parseInt(rs.getString(1)), rs.getString(4) + " - " + rs.getString(3)));
            }
            cb.setModel(modeloCombo);
            rs.close();
            st.close();
            con.close();
        } catch (NumberFormatException | SQLException e) {
            System.out.println("Algunos formularios no estan activos, para actualizarse, o no hay conexión");
        }
    }

    public static void cargarResponsable(JComboBox cb, String sql) {
        try (Connection con = dss.getDataSource().getConnection(); Statement st = con.createStatement(); ResultSet rs = (ResultSet) st.executeQuery(sql)) {
            modeloCombo = new DefaultComboBoxModel();
            modeloCombo.addElement("SELECCIONE EL FUNCIONARIO");
            while (rs.next()) {
                modeloCombo.addElement(new Combo(Integer.parseInt(rs.getString(1)), rs.getString(6)));
            }
            cb.setModel(modeloCombo);
            rs.close();
            st.close();
            con.close();
        } catch (NumberFormatException | SQLException e) {
            System.out.println("Algunos formularios no estan activos, para actualizarse, o no hay conexión");
        }
    }

    public static void cargarFuncionarios(JComboBox cb, String sql) {
        try (Connection con = dss.getDataSource().getConnection(); Statement st = con.createStatement(); ResultSet rs = (ResultSet) st.executeQuery(sql)) {
            modeloCombo = new DefaultComboBoxModel();
            modeloCombo.addElement("SELECCIONE UN FUNCIONARIO");
            while (rs.next()) {
                modeloCombo.addElement(new Combo(Integer.parseInt(rs.getString(1)), rs.getString(6)));
            }
            cb.setModel(modeloCombo);
            rs.close();
            st.close();
            con.close();
        } catch (NumberFormatException | SQLException e) {
            System.out.println("Algunos formularios no estan activos, para actualizarse, o no hay conexión");
        }
    }

    public static void cargarList(JList cb, String sql) {
        try {
            try (Connection con = dss.getDataSource().getConnection(); Statement st = con.createStatement(); ResultSet rs = (ResultSet) st.executeQuery(sql)) {
                modeloCombo = new DefaultComboBoxModel();
                cb.setModel(modeloCombo);
                while (rs.next()) {
                    modeloCombo.addElement(new Combo(Integer.parseInt(rs.getString(1)), rs.getString(2)));
                }
                rs.close();
                st.close();
                con.close();
            }
        } catch (NumberFormatException | SQLException e) {
            //Excepcion en caso haya conexion
            System.out.println("Algunos formularios no estan activos, para actualizarse, o no hay conexión");
        }
    }

    public static void cargarProv(JComboBox cb, String sql) {
        try (Connection con = dss.getDataSource().getConnection(); Statement st = con.createStatement(); ResultSet rs = (ResultSet) st.executeQuery(sql)) {
            modeloCombo = new DefaultComboBoxModel();
            cb.setModel(modeloCombo);
            while (rs.next()) {
                modeloCombo.addElement(new Combo(Integer.parseInt(rs.getString(1)), rs.getString(2) + " -- RUC: " + rs.getString(3)));
            }
            rs.close();
            st.close();
            con.close();
        } catch (NumberFormatException | SQLException e) {
            System.out.println("Algunos formularios no estan activos, para actualizarse, o no hay conexión");
        }
    }

    public static void cargarCli(JComboBox cb, String sql) {
        try (Connection con = dss.getDataSource().getConnection(); Statement st = con.createStatement(); ResultSet rs = (ResultSet) st.executeQuery(sql)) {
            modeloCombo = new DefaultComboBoxModel();
            modeloCombo.addElement("SELEC. UNA OPCIÓN");
            cb.setModel(modeloCombo);
            while (rs.next()) {
                modeloCombo.addElement(new Combo(Integer.parseInt(rs.getString(1)), rs.getString(3) + " -- RUC/C.I.: " + rs.getString(4)));
            }
            rs.close();
            st.close();
            con.close();
        } catch (NumberFormatException | SQLException e) {
            System.out.println("Algunos formularios no estan activos, para actualizarse, o no hay conexión");
        }

    }

    public static void cargarCaja(JComboBox cb, String sql) {
        try (Connection con = dss.getDataSource().getConnection(); Statement st = con.createStatement(); ResultSet rs = (ResultSet) st.executeQuery(sql)) {
            modeloCombo = new DefaultComboBoxModel();
            modeloCombo.addElement("SELEC.");
            while (rs.next()) {
                modeloCombo.addElement(rs.getString(1));
            }
            cb.setModel(modeloCombo);
            rs.close();
            st.close();
            con.close();
        } catch (NumberFormatException | SQLException e) {
            System.out.println("Algunos formularios no estan activos, para actualizarse, o no hay conexión");
        }

    }

    public static String getCodidgo(JComboBox cb)//Metodo para Obtener el id
    {
        Combo c = (Combo) cb.getSelectedItem();//Seleccionamos
        int id = c.getCodigo();//Obtenermos el id
        //System.out.println(id);
        return String.valueOf(id);//Retornamos el codigo
    }

    public static String getDesc(JComboBox cb)//Metodo para Obtener el id
    {
        Combo c = (Combo) cb.getSelectedItem();//Seleccionamos
        String id = c.getDesc();//Obtenermos el id
        //System.out.println(id);
        return (id);//Retornamos el codigo
    }

    public static String getCodidgoL(JList cb)//Metodo para Obtener el id
    {
        Combo c = (Combo) cb.getSelectedValue();//Seleccionamos
        int id = c.getCodigo();//Obtenermos el id
        //System.out.println(id);
        return String.valueOf(id);//Retornamos el codigo
    }

    public static String getDescList(JList cb)//Metodo para Obtener el id
    {
        Combo c = (Combo) cb.getSelectedValue();//Seleccionamos
        String des = c.getDesc();//Obtenermos el id
        //System.out.println(des);
        return String.valueOf(des);//Retornamos el codigo
    }

}
