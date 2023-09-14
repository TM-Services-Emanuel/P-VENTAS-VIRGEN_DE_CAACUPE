package Controladores;

import Componentes.DataSourceService1;
import Componentes.Mensajes;
import Datos.GestionarCiudadMovil;
import IU.dlgCiudadMovil;
import Modelo.CiudadMovil;
import java.sql.*;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class controlCiudadMovil {

    static DataSourceService1 dss = new DataSourceService1();

    public static String addCiudad() {
        String msg;
        int cod = Integer.parseInt(dlgCiudadMovil.txtCod.getText());
        String nombre = dlgCiudadMovil.txtCiudad.getText().toUpperCase();

        int IdDepartamento = 0;
        String sql = "SELECT * FROM departamento WHERE departamento='" + dlgCiudadMovil.cboDepartamento.getSelectedItem().toString() + "'";
        try (Connection cn = dss.getDataSource().getConnection(); Statement st = cn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            rs.last();
            IdDepartamento = rs.getInt("iddepartamento");
            rs.close();
            st.close();
            cn.close();
        } catch (SQLException ex) {
            Mensajes.error("Error al querer obtener valor del departamento: " + ex.getMessage());
        }

        CiudadMovil c = new CiudadMovil(cod, nombre, IdDepartamento);
        msg = GestionarCiudadMovil.addCiudad(c);
        if (msg == null) {
            Mensajes.informacion("Ciudad Registrada");
        } else {
            Mensajes.error(msg);
        }
        return msg;
    }

    public static String actCiudad() {
        String msg;
        int cod = Integer.parseInt(dlgCiudadMovil.txtCod.getText());
        String nombre = dlgCiudadMovil.txtCiudad.getText().toUpperCase();
        int IdDepartamento = 0;
        String sql = "SELECT * FROM departamento WHERE departamento='" + dlgCiudadMovil.cboDepartamento.getSelectedItem().toString() + "'";
        try (Connection cn = dss.getDataSource().getConnection(); Statement st = cn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            rs.last();
            IdDepartamento = rs.getInt("iddepartamento");
            rs.close();
            st.close();
            cn.close();
        } catch (SQLException ex) {
            Mensajes.error("Error al querer obtener valor del departamento: " + ex.getMessage());
        }

        CiudadMovil c = new CiudadMovil(cod, nombre, IdDepartamento);
        msg = GestionarCiudadMovil.actCiudad(c);
        if (msg == null) {
            Mensajes.informacion("Ciudad Actualizada");
        } else {
            Mensajes.error(msg);
        }
        return msg;
    }

    public static String delCiudad() {
        String msg;
        String cod = dlgCiudadMovil.txtCod.getText();
        msg = GestionarCiudadMovil.delCiudad(cod);
        if (msg == null) {
            Mensajes.informacion("Ciudad Eliminada");
        } else {
            Mensajes.error(msg);
        }
        return msg;
    }

    public static void listCiudad(JTable tabla) {
        List lista;
        lista = GestionarCiudadMovil.listCiudad();
        for (int i = 1; i < lista.size(); i++) {
            DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
            Object[] fila = (Object[]) lista.get(i);
            tb.addRow(fila);
        }
    }
}
