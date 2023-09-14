package Controladores;

import Componentes.Login;
import Componentes.Mensajes;
import Datos.GestionarMoviles;
import IU.dlgMovilesReparto;
import Modelo.Moviles;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class controlMoviles {

    public static String addMoviles() {
        String msg;
        int cod = Integer.parseInt(dlgMovilesReparto.txtCod.getText());
        String nombre = dlgMovilesReparto.txtNombre.getText().toUpperCase();
        String chapa = dlgMovilesReparto.txtChapa.getText().toUpperCase();
        String marca = dlgMovilesReparto.txtMarca.getText().toUpperCase();
        String modelo = dlgMovilesReparto.txtModelo.getText().toUpperCase();
        String chasis = dlgMovilesReparto.txtChasis.getText().toUpperCase();
        String anho = dlgMovilesReparto.txtAnho.getText().toUpperCase();
        String color = dlgMovilesReparto.txtColor.getText().toUpperCase();
        String capacidad = dlgMovilesReparto.txtCapacidad.getText().toUpperCase();
        String usuario = Login.getUsuarioLogueado();
        Moviles e = new Moviles(cod, nombre, chapa, marca, modelo, chasis, anho, color, capacidad, usuario);
        msg = GestionarMoviles.addMovil(e);
        if (msg == null) {
            Mensajes.informacion("Punto de Control & Logística Registrado");
        } else {
            Mensajes.error(msg);
        }
        return msg;
    }

    public static String actMovil() {
        String msg;
        int cod = Integer.parseInt(dlgMovilesReparto.txtCod.getText());
        String nombre = dlgMovilesReparto.txtNombre.getText().toUpperCase();
        String chapa = dlgMovilesReparto.txtChapa.getText().toUpperCase();
        String marca = dlgMovilesReparto.txtMarca.getText().toUpperCase();
        String modelo = dlgMovilesReparto.txtModelo.getText().toUpperCase();
        String chasis = dlgMovilesReparto.txtChasis.getText().toUpperCase();
        String anho = dlgMovilesReparto.txtAnho.getText().toUpperCase();
        String color = dlgMovilesReparto.txtColor.getText().toUpperCase();
        String capacidad = dlgMovilesReparto.txtCapacidad.getText().toUpperCase();
        String usuario = Login.getUsuarioLogueado();
        Moviles e = new Moviles(cod, nombre, chapa, marca, modelo, chasis, anho, color, capacidad, usuario);
        msg = GestionarMoviles.actMoviles(e);
        if (msg == null) {
            Mensajes.informacion("Punto de Control & Logística Actualizado");
        } else {
            Mensajes.error(msg);
        }
        return msg;
    }

    public static String delMovil() {
        String msg;
        String cod = (dlgMovilesReparto.txtCod.getText());
        String usuario = Login.getUsuarioLogueado();
        msg = GestionarMoviles.delMoviles(cod, usuario);
        if (msg == null) {
            Mensajes.informacion("Punto de Control & Logística Eliminado");
        } else {
            Mensajes.error(msg);
        }
        return msg;
    }

    public static void lisMoviles(JTable tabla) {
        List lista;
        lista = GestionarMoviles.listMoviles();
        for (int i = 1; i < lista.size(); i++) {
            DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
            Object[] fila = (Object[]) lista.get(i);
            tb.addRow(fila);
        }
    }

}
