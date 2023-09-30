package Controladores;

import Componentes.Fecha;
import Componentes.Login;
import Componentes.Mensajes;
import Datos.GestionarGasto;
import IU.dlgGastos;
import Modelo.Gasto;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ControlGasto {

    public static String addGasto() {
        String msg;
        int caj = Integer.parseInt(dlgGastos.txtCaja.getText());
        //String fecha = dlgGastos.dcFecha.getText();
        String gaFecha = Fecha.formatoFecha(dlgGastos.dcFecha.getText());
        int motivo_gasto = Integer.parseInt(dlgGastos.txtMotivo.getText().trim());
        int otorgado_a = Integer.parseInt(dlgGastos.txtFuncionario.getText().trim());
        int gaImporte = Integer.parseInt(dlgGastos.txtImporteL.getText());
        String Observacion = dlgGastos.txtObservacion.getText().toUpperCase().trim();
        String usuario = Login.getUsuarioLogueado(); 
        String generado = dlgGastos.txtOrigen.getText().trim();
        Gasto gasto = new Gasto(caj, gaFecha, motivo_gasto, otorgado_a, gaImporte, Observacion, usuario, generado);

        msg = GestionarGasto.addGasto(gasto);

        if (msg == null) {
            Mensajes.informacion("Registrado");
        } else {
            Mensajes.error("No se pudo guardar");
        }

        return "";

    }

    public static String anularGasto(int cod) {
        String msg;
        String usuario = Login.getUsuarioLogueado();
        msg = GestionarGasto.delGasto(cod, usuario);
        if (msg == null) {
            Mensajes.informacion("El Gasto fue anulado satisfactoriamente");
            //dlgGestRepartos.Cancelar();
        } else {
            Mensajes.error(msg);
        }
        return msg;
    }

    public static void listarGastos(JTable tabla, String fecha) {
        List lista;
        lista = GestionarGasto.listarGastos(fecha);
        for (int i = 1; i < lista.size(); i++) {
            DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
            Object[] fila = (Object[]) lista.get(i);
            fila[0] = fila[0].toString();
            fila[1] = fila[1].toString();
            fila[2] = Fecha.formatoFechaDinver(fila[2].toString())+" "+Fecha.FormatoHoraSinSSString(fila[3].toString());
            switch (fila[4].toString()) {
                case "L":
                    fila[3] = "SALÓN DE VENTA";
                    break;
                case "A":
                    fila[3] = "ADMINISTRATIVO";
                    break;
                default:
                    break;
            }
            switch (Integer.parseInt(fila[5].toString())) {
                case 1:
                    fila[4] = "GASTOS VARIOS";
                    break;
                case 2:
                    fila[4] = "ADELANTO DE SUELDO";
                    break;
                case 3:
                    fila[4] = "PAGO DE SUELDO";
                    break;
                default:
                    break;
            }
            fila[5]= fila[6].toString();
            fila[6] = fila[7].toString();
            fila[7]= fila[8].toString();
            fila[8]= fila[9].toString();
            tb.addRow(fila);
        }
    }

}
