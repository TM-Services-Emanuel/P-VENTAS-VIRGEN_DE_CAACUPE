package Componentes;

import java.sql.*;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JList;

public class cargarComboBoxMovil {

    static DefaultComboBoxModel modeloCombo;
    static DataSourceService1 dss = new DataSourceService1();

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

    public static void cargarPE(JComboBox cb, String sql) {
        try (Connection con = dss.getDataSource().getConnection(); Statement st = con.createStatement(); ResultSet rs = (ResultSet) st.executeQuery(sql)) {
            modeloCombo = new DefaultComboBoxModel();
            modeloCombo.addElement("SELEC. P. EMISIÓN");
            while (rs.next()) {
                modeloCombo.addElement(new Combo(Integer.parseInt(rs.getString(1)), rs.getString(2) + "-" + rs.getString(3) + " REF: " + rs.getString(4)));
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
        try (Connection con = dss.getDataSource().getConnection(); Statement st = con.createStatement(); ResultSet rs = (ResultSet) st.executeQuery(sql)) {
            modeloCombo = new DefaultComboBoxModel();
            cb.setModel(modeloCombo);
            while (rs.next()) {
                modeloCombo.addElement(new Combo(Integer.parseInt(rs.getString(1)), rs.getString(2)));
            }
            rs.close();
            st.close();
            con.close();
        } catch (Exception e) {
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
        } catch (Exception e) {
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
        } catch (Exception e) {
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
