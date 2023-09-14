package Controladores;

import Componentes.DataSourceService1;
import Componentes.Login;
import Componentes.Mensajes;
import Datos.GestionarEmpresa;
import IU.dlgEmpresa;
import Modelo.Empresa;
import java.sql.*;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class controlEmpresa {

    static DataSourceService1 dss1 = new DataSourceService1();

    public static String addEmpresa() {
        String msg;
        int cod = Integer.parseInt(dlgEmpresa.txtCod.getText());
        String nombre = dlgEmpresa.txtNombre.getText().toUpperCase();
        String ruc = dlgEmpresa.txtRuc.getText().toUpperCase();
        String direccion = dlgEmpresa.txtDireccion.getText().toUpperCase();
        String telefono = dlgEmpresa.txtTelefono.getText().toUpperCase();
        int idciudad = Integer.parseInt(dlgEmpresa.lbCiudad.getText());
        String visual = dlgEmpresa.lbvisual.getText().toUpperCase();
        Empresa e = new Empresa(cod, nombre, ruc, direccion, telefono, idciudad);
        msg = GestionarEmpresa.addEmpresa(e);
        if (msg == null) {
            Mensajes.informacion("Empresa Registrada");
        } else {
            Mensajes.error(msg);
        }
        return msg;
    }

    public static String actEmpresa() {
        String msg;
        int cod = Integer.parseInt(dlgEmpresa.txtCod.getText());
        String nombre = dlgEmpresa.txtNombre.getText().toUpperCase();
        String ruc = dlgEmpresa.txtRuc.getText().toUpperCase();
        String direccion = dlgEmpresa.txtDireccion.getText().toUpperCase();
        String telefono = dlgEmpresa.txtTelefono.getText().toUpperCase();

        int codCiudad = 0;
        String clas = dlgEmpresa.cboCiudad.getSelectedItem().toString();
        String sql = "SELECT * FROM ciudad WHERE ciudad='" + clas + "'";
        try (Connection cn = dss1.getDataSource().getConnection(); Statement st = cn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            rs.last();
            codCiudad = rs.getInt("idciudad");
            rs.close();
            st.close();
            cn.close();
        } catch (Exception pr) {
            Mensajes.error("Error al querer obtener valor de la ciudad: " + pr.getMessage());
        }
        String visual = dlgEmpresa.lbvisual.getText().toUpperCase();
        System.out.println(visual);
        Empresa e = new Empresa(cod, nombre, ruc, direccion, telefono, codCiudad);
        msg = GestionarEmpresa.actEmpresa(e);
        if (msg == null) {
            Mensajes.informacion("Empresa Actualizada");
        } else {
            Mensajes.error(msg);
        }
        return msg;
    }

    public static String delEmpresa() {
        String msg;
        String cod = dlgEmpresa.txtCod.getText();
        msg = GestionarEmpresa.delEmpresa(cod, Login.getUsuarioLogueado());
        if (msg == null) {
            Mensajes.informacion("Empresa Eliminada");
        } else {
            Mensajes.error(msg);
        }
        return msg;
    }

    public static void lisEmpresa(JTable tabla) {
        List lista;
        lista = GestionarEmpresa.listEmpresa();
        for (int i = 1; i < lista.size(); i++) {
            DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
            Object[] fila = (Object[]) lista.get(i);
            fila[0].toString();
            fila[1].toString();
            tb.addRow(fila);
        }
    }

}
