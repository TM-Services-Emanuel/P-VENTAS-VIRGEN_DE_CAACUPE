package Controladores;

import Componentes.Fecha;
import Componentes.Login;
import Componentes.Notif;
import Datos.GestionarCaja;
import IU.dlgArreglo;
import IU.dlgCaja;
import IU.dlgCajaDia;
import IU.dlgCajaDia1;
import IU.dlgCajaDia2;
import IU.dlgRegistroValores;
import Modelo.Caja;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ControlCaja {

    public static void aModifcar() {
        try {
            int x = dlgRegistroValores.tablaDetalles.getSelectedRow();
            String IDArreglo = dlgRegistroValores.tablaDetalles.getValueAt(x, 0).toString();
            String IDCaja = dlgRegistroValores.tablaDetalles.getValueAt(x, 1).toString();
            String IDBoca = dlgRegistroValores.tablaDetalles.getValueAt(x, 2).toString();
            String Concepto = dlgRegistroValores.tablaDetalles.getValueAt(x, 6).toString();
            String n50 = dlgRegistroValores.tablaDetalles.getValueAt(x, 7).toString();
            String n100 = dlgRegistroValores.tablaDetalles.getValueAt(x, 8).toString();
            String n500 = dlgRegistroValores.tablaDetalles.getValueAt(x, 9).toString();
            String n1000 = dlgRegistroValores.tablaDetalles.getValueAt(x, 10).toString();
            String n2000 = dlgRegistroValores.tablaDetalles.getValueAt(x, 11).toString();
            String n5000 = dlgRegistroValores.tablaDetalles.getValueAt(x, 12).toString();
            String n10000 = dlgRegistroValores.tablaDetalles.getValueAt(x, 13).toString();
            String n20000 = dlgRegistroValores.tablaDetalles.getValueAt(x, 14).toString();
            String n50000 = dlgRegistroValores.tablaDetalles.getValueAt(x, 15).toString();
            String n100000 = dlgRegistroValores.tablaDetalles.getValueAt(x, 16).toString();

            dlgArreglo.txtIDArreglo.setText(IDArreglo);
            dlgArreglo.txtIDCaj.setText(IDCaja);
            dlgArreglo.txtIDBoca.setText(IDBoca);
            switch (Concepto) {
                case "GASTO" ->
                    dlgArreglo.cboConcepto.setSelectedIndex(3);
                case "RETIRO" ->
                    dlgArreglo.cboConcepto.setSelectedIndex(1);
                case "CIERRE" ->
                    dlgArreglo.cboConcepto.setSelectedIndex(2);
                default -> {
                    dlgArreglo.cboConcepto.setSelectedIndex(0);
                }
            }
            dlgArreglo.txt50.setText(n50);
            dlgArreglo.txt100.setText(n100);
            dlgArreglo.txt500.setText(n500);
            dlgArreglo.txt1000.setText(n1000);
            dlgArreglo.txt2000.setText(n2000);
            dlgArreglo.txt5000.setText(n5000);
            dlgArreglo.txt10000.setText(n10000);
            dlgArreglo.txt20000.setText(n20000);
            dlgArreglo.txt50000.setText(n50000);
            dlgArreglo.txt100000.setText(n100000);
            
            

        } catch (NumberFormatException ee) {
            System.out.println("Error cargando datos Arreglo: " + ee.getMessage());
        }

    }

    public static String addCaja() {
        String msg;
        String caFechaI = Fecha.formatoFecha(dlgCaja.lbFecha.getText());
        System.out.println(caFechaI);
        String caHoraI = dlgCaja.lbHora.getText();
        System.out.println(caHoraI);
        String caUsuI = dlgCaja.lbUsuario.getText();
        String caUsuF = " ";
        int caInicial = Integer.parseInt(dlgCaja.txtCaInicial.getText().replace(",", "").replace(".", ""));

        Caja caja = new Caja(caFechaI, caHoraI, caInicial, 0, 0, 0, 0, 0, caUsuI, caUsuF);
        msg = GestionarCaja.addCaja(caja);
        if (msg == null) {
            Notif.NotifySuccess("Notificación del sistema", "La caja inicial del día ha sido establecido satisfactoriamente!");
            //Mensajes.informacion("Caja inicial del día establecida");
        } else {
            Notif.NotifyError("Notificación del sistema", "Error iniciando caja diaria:\r\n" + msg);
            //Mensajes.error(msg);
        }
        return msg;
    }

    public static String CerrarCaja() {
        String msg;
        int caId = Integer.parseInt(dlgCajaDia.lbNCaja.getText());
        String caFechaF = Fecha.fechaCorrecta();
        String caHoraF = Fecha.darHora();
        int caFinal = Integer.parseInt(dlgCajaDia.txtEntregar.getText().trim().replace(",", "").replace(".", ""));
        int caEntre = Integer.parseInt(dlgCajaDia.txtEntregado.getText().trim().replace(",", "").replace(".", ""));
        int caGastos = Integer.parseInt(dlgCajaDia.txtGastos.getText().trim().replace(",", "").replace(".", ""));
        int caDif = Integer.parseInt(dlgCajaDia.txtDiferencia.getText().trim().replace(",", "").replace(".", ""));
        int caMontoSalida = Integer.parseInt(dlgCajaDia.txtTotalSalida.getText().trim().replace(",", "").replace(".", ""));
        String caUsuF = Login.getUsuarioLogueado();

        Caja caja = new Caja(caId, caFechaF, caHoraF, caFinal, caEntre, caGastos, caDif, caMontoSalida, caUsuF);
        msg = GestionarCaja.CerrarCaja(caja);
        if (msg == null) {
            Notif.NotifySuccess("Notificación del sistema", "CAJA DEL DÍA CERRADA!");
            //Mensajes.informacion("CAJA DEL DÍA CERRADA!");
        } else {
            //Mensajes.informacion("Caja del día actualizada");
            Notif.NotifyError("Notificación del sistema", "Error cerrando caja diaria:\r\n" + msg);
            //Mensajes.error(msg);
        }
        return msg;
    }

    public static String CerrarCaja1() {
        String msg;
        int caId = Integer.parseInt(dlgCajaDia2.lbNCaja.getText());
        String caFechaF = Fecha.fechaCorrecta();
        String caHoraF = Fecha.darHora();
        int caFinal = Integer.parseInt(dlgCajaDia2.txtEntregar.getText().trim().replace(",", "").replace(".", ""));
        int caEntre = Integer.parseInt(dlgCajaDia2.txtEntregado.getText().trim().replace(",", "").replace(".", ""));
        int caGastos = Integer.parseInt(dlgCajaDia2.txtGastos.getText().trim().replace(",", "").replace(".", ""));
        int caDif = Integer.parseInt(dlgCajaDia2.txtDiferencia.getText().trim().replace(",", "").replace(".", ""));
        int caMontoSalida = Integer.parseInt(dlgCajaDia2.txtTotalSalida.getText().trim().replace(",", "").replace(".", ""));
        String caUsuF = Login.getUsuarioLogueado();

        Caja caja = new Caja(caId, caFechaF, caHoraF, caFinal, caEntre, caGastos, caDif, caMontoSalida, caUsuF);
        msg = GestionarCaja.CerrarCaja(caja);
        if (msg == null) {
            Notif.NotifySuccess("Notificación del sistema", "CAJA DEL DÍA MODIFICADA Y/O CERRADA!");
            //Mensajes.informacion("CAJA DEL DÍA MODIFICADA Y/O CERRADA!");
        } else {
            //Mensajes.informacion("Caja del día actualizada");
            //Mensajes.error(msg);
            Notif.NotifyError("Notificación del sistema", "Error cerrando caja diaria:\r\n" + msg);
        }
        return msg;
    }

    public static String actCaja() {
        String msg;
        int caId = Integer.parseInt(dlgCajaDia.lbNCaja.getText());
        int caFinal = Integer.parseInt(dlgCajaDia.txtEntregar.getText().trim().replace(",", "").replace(".", ""));
        int caEntre = Integer.parseInt(dlgCajaDia.txtEntregado.getText().trim().replace(",", "").replace(".", ""));
        int caGastos = Integer.parseInt(dlgCajaDia.txtGastos.getText().trim().replace(",", "").replace(".", ""));
        int caDif = Integer.parseInt(dlgCajaDia.txtDiferencia.getText().trim().replace(",", "").replace(".", ""));
        int caMontoSalida = Integer.parseInt(dlgCajaDia.txtTotalSalida.getText().trim().replace(",", "").replace(".", ""));

        Caja caja = new Caja(caId, caFinal, caEntre, caGastos, caDif, caMontoSalida);
        msg = GestionarCaja.actCaja(caja);
        if (msg == null) {
            Notif.NotifySuccess("Notificación del sistema", "CAJA DEL DÍA ACTUALIZADA!");
            //Mensajes.informacion("CAJA DEL DÍA ACTUALIZADA!");
        } else {
            //Mensajes.informacion("Caja del día actualizada");
            //Mensajes.error(msg);
            Notif.NotifyError("Notificación del sistema", "Error actualizando caja:\r\n" + msg);
        }
        return msg;
    }

    public static String addBoca1() {
        String msg;
        int idCaja = Integer.parseInt(dlgCajaDia.lbNCaja.getText());
        int idBoca = 1;
        int Entregar = Integer.parseInt(dlgCajaDia.txtB1.getText().trim().replace(",", "").replace(".", ""));
        int Entregado = Integer.parseInt(dlgCajaDia.txtEntregado_boca_1.getText().trim().replace(",", "").replace(".", ""));
        int Gastos = Integer.parseInt(dlgCajaDia.txtGastos_boca_1.getText().trim().replace(",", "").replace(".", ""));
        int Dif = Integer.parseInt(dlgCajaDia.txtDiferencia_boca_1.getText().trim().replace(",", "").replace(".", ""));

        //Caja caja = new Caja(caId, caFinal, caEntre, caGastos, caDif, caMontoSalida);
        msg = GestionarCaja.addBoca(idBoca, idCaja, Entregar, Entregado, Gastos, Dif);
        if (msg == null) {
            Notif.NotifySuccess("Notificación del sistema", "BOCA DE COBRANZA 1 REGISTRADO!");
            //Mensajes.informacion("BOCA DE COBRANZA 1 REGISTRADO!");
        } else {
            //Mensajes.informacion("Caja del día actualizada");
            Notif.NotifyError("Notificación del sistema", "Error registrando BOCA 1:\r\n" + msg);
            //Mensajes.error(msg);
        }
        return msg;
    }

    public static String addBoca1_1() {
        String msg;
        int idCaja = Integer.parseInt(dlgCajaDia1.lbNCaja.getText());
        int idBoca = 1;
        int Entregar = Integer.parseInt(dlgCajaDia1.txtB1.getText().trim().replace(",", "").replace(".", ""));
        int Entregado = Integer.parseInt(dlgCajaDia1.txtEntregado_boca_1.getText().trim().replace(",", "").replace(".", ""));
        int Gastos = Integer.parseInt(dlgCajaDia1.txtGastos_boca_1.getText().trim().replace(",", "").replace(".", ""));
        int Dif = Integer.parseInt(dlgCajaDia1.txtDiferencia_boca_1.getText().trim().replace(",", "").replace(".", ""));

        //Caja caja = new Caja(caId, caFinal, caEntre, caGastos, caDif, caMontoSalida);
        msg = GestionarCaja.addBoca(idBoca, idCaja, Entregar, Entregado, Gastos, Dif);
        if (msg == null) {
            Notif.NotifySuccess("Notificación del sistema", "BOCA DE COBRANZA 1 REGISTRADO!");
            //Mensajes.informacion("BOCA DE COBRANZA 1 REGISTRADO!");
        } else {
            //Mensajes.informacion("Caja del día actualizada");
            Notif.NotifyError("Notificación del sistema", "Error registrando BOCA 1:\r\n" + msg);
            //Mensajes.error(msg);
        }
        return msg;
    }

    public static String addBoca2() {
        String msg;
        int idCaja = Integer.parseInt(dlgCajaDia.lbNCaja.getText());
        int idBoca = 2;
        int Entregar = Integer.parseInt(dlgCajaDia.txtB2.getText().trim().replace(",", "").replace(".", ""));
        int Entregado = Integer.parseInt(dlgCajaDia.txtEntregado_boca_2.getText().trim().replace(",", "").replace(".", ""));
        int Gastos = Integer.parseInt(dlgCajaDia.txtGastos_boca_2.getText().trim().replace(",", "").replace(".", ""));
        int Dif = Integer.parseInt(dlgCajaDia.txtDiferencia_boca_2.getText().trim().replace(",", "").replace(".", ""));

        //Caja caja = new Caja(caId, caFinal, caEntre, caGastos, caDif, caMontoSalida);
        msg = GestionarCaja.addBoca(idBoca, idCaja, Entregar, Entregado, Gastos, Dif);
        if (msg == null) {
            Notif.NotifySuccess("Notificación del sistema", "BOCA DE COBRANZA 2 REGISTRADO!");
            //Mensajes.informacion("BOCA DE COBRANZA 2 REGISTRADO!");
        } else {
            //Mensajes.informacion("Caja del día actualizada");
            Notif.NotifyError("Notificación del sistema", "Error registrando BOCA 2:\r\n" + msg);
            //Mensajes.error(msg);
        }
        return msg;
    }

    public static String addBoca2_1() {
        String msg;
        int idCaja = Integer.parseInt(dlgCajaDia1.lbNCaja.getText());
        int idBoca = 2;
        int Entregar = Integer.parseInt(dlgCajaDia1.txtB2.getText().trim().replace(",", "").replace(".", ""));
        int Entregado = Integer.parseInt(dlgCajaDia1.txtEntregado_boca_2.getText().trim().replace(",", "").replace(".", ""));
        int Gastos = Integer.parseInt(dlgCajaDia1.txtGastos_boca_2.getText().trim().replace(",", "").replace(".", ""));
        int Dif = Integer.parseInt(dlgCajaDia1.txtDiferencia_boca_2.getText().trim().replace(",", "").replace(".", ""));

        //Caja caja = new Caja(caId, caFinal, caEntre, caGastos, caDif, caMontoSalida);
        msg = GestionarCaja.addBoca(idBoca, idCaja, Entregar, Entregado, Gastos, Dif);
        if (msg == null) {
            Notif.NotifySuccess("Notificación del sistema", "BOCA DE COBRANZA 2 REGISTRADO!");
            //Mensajes.informacion("BOCA DE COBRANZA 2 REGISTRADO!");
        } else {
            //Mensajes.informacion("Caja del día actualizada");
            //Mensajes.error(msg);
            Notif.NotifyError("Notificación del sistema", "Error registrando BOCA 2:\r\n" + msg);
        }
        return msg;
    }

    public static String actBoca1() {
        String msg;
        int idCaja = Integer.parseInt(dlgCajaDia.lbNCaja.getText());
        int idBoca = 1;
        int Entregar = Integer.parseInt(dlgCajaDia.txtB1.getText().trim().replace(",", "").replace(".", ""));
        int Entregado = Integer.parseInt(dlgCajaDia.txtEntregado_boca_1.getText().trim().replace(",", "").replace(".", ""));
        int Gastos = Integer.parseInt(dlgCajaDia.txtGastos_boca_1.getText().trim().replace(",", "").replace(".", ""));
        int Dif = Integer.parseInt(dlgCajaDia.txtDiferencia_boca_1.getText().trim().replace(",", "").replace(".", ""));

        //Caja caja = new Caja(caId, caFinal, caEntre, caGastos, caDif, caMontoSalida);
        msg = GestionarCaja.actBoca(idBoca, idCaja, Entregar, Entregado, Gastos, Dif);
        if (msg == null) {
            Notif.NotifySuccess("Notificación del sistema", "BOCA DE COBRANZA 1 ACTUALIZADO!");
            //Mensajes.informacion("BOCA DE COBRANZA 1 ACTUALIZADO!");
        } else {
            //Mensajes.informacion("Caja del día actualizada");
            Notif.NotifyError("Notificación del sistema", "Error actualizando BOCA 1:\r\n" + msg);
            // Mensajes.error(msg);
        }
        return msg;
    }

    public static String actBoca1_1() {
        String msg;
        int idCaja = Integer.parseInt(dlgCajaDia1.lbNCaja.getText());
        int idBoca = 1;
        int Entregar = Integer.parseInt(dlgCajaDia1.txtB1.getText().trim().replace(",", "").replace(".", ""));
        int Entregado = Integer.parseInt(dlgCajaDia1.txtEntregado_boca_1.getText().trim().replace(",", "").replace(".", ""));
        int Gastos = Integer.parseInt(dlgCajaDia1.txtGastos_boca_1.getText().trim().replace(",", "").replace(".", ""));
        int Dif = Integer.parseInt(dlgCajaDia1.txtDiferencia_boca_1.getText().trim().replace(",", "").replace(".", ""));

        //Caja caja = new Caja(caId, caFinal, caEntre, caGastos, caDif, caMontoSalida);
        msg = GestionarCaja.actBoca(idBoca, idCaja, Entregar, Entregado, Gastos, Dif);
        if (msg == null) {
            Notif.NotifySuccess("Notificación del sistema", "BOCA DE COBRANZA 1 ACTUALIZADO!");
            // Mensajes.informacion("BOCA DE COBRANZA 1 ACTUALIZADO!");
        } else {
            //Mensajes.informacion("Caja del día actualizada");
            Notif.NotifyError("Notificación del sistema", "Error actualizando BOCA 1:\r\n" + msg);
            //Mensajes.error(msg);
        }
        return msg;
    }

    public static String actBoca2() {
        String msg;
        int idCaja = Integer.parseInt(dlgCajaDia.lbNCaja.getText());
        int idBoca = 2;
        int Entregar = Integer.parseInt(dlgCajaDia.txtB2.getText().trim().replace(",", "").replace(".", ""));
        int Entregado = Integer.parseInt(dlgCajaDia.txtEntregado_boca_2.getText().trim().replace(",", "").replace(".", ""));
        int Gastos = Integer.parseInt(dlgCajaDia.txtGastos_boca_2.getText().trim().replace(",", "").replace(".", ""));
        int Dif = Integer.parseInt(dlgCajaDia.txtDiferencia_boca_2.getText().trim().replace(",", "").replace(".", ""));

        //Caja caja = new Caja(caId, caFinal, caEntre, caGastos, caDif, caMontoSalida);
        msg = GestionarCaja.actBoca(idBoca, idCaja, Entregar, Entregado, Gastos, Dif);
        if (msg == null) {
            Notif.NotifySuccess("Notificación del sistema", "BOCA DE COBRANZA 2 ACTUALIZADO!");
            // Mensajes.informacion("BOCA DE COBRANZA 2 ACTUALIZADO!");
        } else {
            //Mensajes.informacion("Caja del día actualizada");
            Notif.NotifyError("Notificación del sistema", "Error actualizando BOCA 2:\r\n" + msg);
            //Mensajes.error(msg);
        }
        return msg;
    }

    public static String actBoca2_1() {
        String msg;
        int idCaja = Integer.parseInt(dlgCajaDia1.lbNCaja.getText());
        int idBoca = 2;
        int Entregar = Integer.parseInt(dlgCajaDia1.txtB2.getText().trim().replace(",", "").replace(".", ""));
        int Entregado = Integer.parseInt(dlgCajaDia1.txtEntregado_boca_2.getText().trim().replace(",", "").replace(".", ""));
        int Gastos = Integer.parseInt(dlgCajaDia1.txtGastos_boca_2.getText().trim().replace(",", "").replace(".", ""));
        int Dif = Integer.parseInt(dlgCajaDia1.txtDiferencia_boca_2.getText().trim().replace(",", "").replace(".", ""));

        //Caja caja = new Caja(caId, caFinal, caEntre, caGastos, caDif, caMontoSalida);
        msg = GestionarCaja.actBoca(idBoca, idCaja, Entregar, Entregado, Gastos, Dif);
        if (msg == null) {
            Notif.NotifySuccess("Notificación del sistema", "BOCA DE COBRANZA 2 ACTUALIZADO!");
            // Mensajes.informacion("BOCA DE COBRANZA 2 ACTUALIZADO!");
        } else {
            //Mensajes.informacion("Caja del día actualizada");
            Notif.NotifyError("Notificación del sistema", "Error actualizando BOCA 2:\r\n" + msg);
            //Mensajes.error(msg);
        }
        return msg;
    }

    public static String addArreglo() {
        String msg;
        int idC = Integer.parseInt(dlgArreglo.txtIDCaj.getText());
        int idB = Integer.parseInt(dlgArreglo.txtIDBoca.getText());
        String Concepto = null;
        String DetalleConcep = null;
        switch (dlgArreglo.cboConcepto.getSelectedIndex()) {
            case 1 -> {
                Concepto = "R";
                DetalleConcep = "RETIRO";
            }
            case 2 -> {
                Concepto = "C";
                DetalleConcep = "CIERRE";
            }
            case 3 -> {
                Concepto = "G";
                DetalleConcep = "GASTO";
            }
            default -> {
            }
        }
        int cincuenta = Integer.parseInt(dlgArreglo.txt50.getText().replace(".", "").replace(",", ""));
        int cien = Integer.parseInt(dlgArreglo.txt100.getText().replace(".", "").replace(",", ""));
        int quinientos = Integer.parseInt(dlgArreglo.txt500.getText().replace(".", "").replace(",", ""));
        int mil = Integer.parseInt(dlgArreglo.txt1000.getText().replace(".", "").replace(",", ""));
        int dos_mil = Integer.parseInt(dlgArreglo.txt2000.getText().replace(".", "").replace(",", ""));
        int cinco_mil = Integer.parseInt(dlgArreglo.txt5000.getText().replace(".", "").replace(",", ""));
        int diez_mil = Integer.parseInt(dlgArreglo.txt10000.getText().replace(".", "").replace(",", ""));
        int veinte_mil = Integer.parseInt(dlgArreglo.txt20000.getText().replace(".", "").replace(",", ""));
        int cincuenta_mil = Integer.parseInt(dlgArreglo.txt50000.getText().replace(".", "").replace(",", ""));
        int cien_mil = Integer.parseInt(dlgArreglo.txt100000.getText().replace(".", "").replace(",", ""));
        int total = Integer.parseInt(dlgArreglo.txtTotal.getText().replace(".", "").replace(",", ""));

        msg = GestionarCaja.addArreglo(idC, idB, Concepto, cincuenta, cien, quinientos, mil, dos_mil, cinco_mil, diez_mil, veinte_mil, cincuenta_mil, cien_mil, total);
        if (msg == null) {
            Notif.NotifySuccess("Notificación del sistema", DetalleConcep + " REGISTRADO!");
            dlgRegistroValores.CargarTabla();
        } else {
            Notif.NotifyError("Notificación del sistema", "Error registrando " + DetalleConcep + ":\r\n" + msg);
        }
        return msg;
    }
    
    public static String actArreglo() {
        String msg;
        int idA = Integer.parseInt(dlgArreglo.txtIDArreglo.getText());
        //int idC = Integer.parseInt(dlgArreglo.txtIDCaj.getText());
        //int idB = Integer.parseInt(dlgArreglo.txtIDBoca.getText());
        String Concepto = null;
        String DetalleConcep = null;
        switch (dlgArreglo.cboConcepto.getSelectedIndex()) {
            case 1 -> {
                Concepto = "R";
                DetalleConcep = "RETIRO";
            }
            case 2 -> {
                Concepto = "C";
                DetalleConcep = "CIERRE";
            }
            case 3 -> {
                Concepto = "G";
                DetalleConcep = "GASTO";
            }
            default -> {
            }
        }
        int cincuenta = Integer.parseInt(dlgArreglo.txt50.getText().replace(".", "").replace(",", ""));
        int cien = Integer.parseInt(dlgArreglo.txt100.getText().replace(".", "").replace(",", ""));
        int quinientos = Integer.parseInt(dlgArreglo.txt500.getText().replace(".", "").replace(",", ""));
        int mil = Integer.parseInt(dlgArreglo.txt1000.getText().replace(".", "").replace(",", ""));
        int dos_mil = Integer.parseInt(dlgArreglo.txt2000.getText().replace(".", "").replace(",", ""));
        int cinco_mil = Integer.parseInt(dlgArreglo.txt5000.getText().replace(".", "").replace(",", ""));
        int diez_mil = Integer.parseInt(dlgArreglo.txt10000.getText().replace(".", "").replace(",", ""));
        int veinte_mil = Integer.parseInt(dlgArreglo.txt20000.getText().replace(".", "").replace(",", ""));
        int cincuenta_mil = Integer.parseInt(dlgArreglo.txt50000.getText().replace(".", "").replace(",", ""));
        int cien_mil = Integer.parseInt(dlgArreglo.txt100000.getText().replace(".", "").replace(",", ""));
        int total = Integer.parseInt(dlgArreglo.txtTotal.getText().replace(".", "").replace(",", ""));

        msg = GestionarCaja.actArreglo(Concepto, cincuenta, cien, quinientos, mil, dos_mil, cinco_mil, diez_mil, veinte_mil, cincuenta_mil, cien_mil, total, idA);
        if (msg == null) {
            Notif.NotifySuccess("Notificación del sistema", DetalleConcep + " MODIFICADO!");
            dlgRegistroValores.CargarTabla();
        } else {
            Notif.NotifyError("Notificación del sistema", "Error modificando " + DetalleConcep + ":\r\n" + msg);
        }
        return msg;
    }

    public static void listArreglos(JTable tabla, String Ncaja) {
        List lista;
        lista = GestionarCaja.listArreglos(Ncaja);
        for (int i = 1; i < lista.size(); i++) {
            DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
            Object[] fila = (Object[]) lista.get(i);
            fila[0].toString();
            fila[1].toString();
            fila[2].toString();
            fila[3].toString();
            fila[4].toString();
            fila[5].toString();
            switch (fila[6].toString()) {
                case "G":
                    fila[6] = "GASTO";
                    break;
                case "R":
                    fila[6] = "RETIRO";
                    break;
                case "C":
                    fila[6] = "CIERRE";
                    break;
                default:
                    break;
            }
            fila[7].toString();
            fila[8].toString();
            fila[9].toString();
            fila[10].toString();
            fila[11].toString();
            fila[12].toString();
            fila[13].toString();
            fila[14].toString();
            fila[15].toString();
            fila[16].toString();
            fila[17].toString();
            tb.addRow(fila);
        }
    }
    
    public static void listArreglos2(JTable tabla, String Ncaja, String idBoca) {
        List lista;
        lista = GestionarCaja.listArreglos2(Ncaja, idBoca);
        for (int i = 1; i < lista.size(); i++) {
            DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
            Object[] fila = (Object[]) lista.get(i);
            fila[0].toString();
            fila[1].toString();
            fila[2].toString();
            fila[3].toString();
            fila[4].toString();
            fila[5].toString();
            switch (fila[6].toString()) {
                case "G":
                    fila[6] = "GASTO";
                    break;
                case "R":
                    fila[6] = "RETIRO";
                    break;
                case "C":
                    fila[6] = "CIERRE";
                    break;
                default:
                    break;
            }
            fila[7].toString();
            fila[8].toString();
            fila[9].toString();
            fila[10].toString();
            fila[11].toString();
            fila[12].toString();
            fila[13].toString();
            fila[14].toString();
            fila[15].toString();
            fila[16].toString();
            fila[17].toString();
            tb.addRow(fila);
        }
    }
    
    public static int getGastos() {
        int total = 0;
        DefaultTableModel tb = (DefaultTableModel) dlgRegistroValores.tablaDetalles.getModel();
        int fila = tb.getRowCount();
        for (int i = 0; i < fila; i++) {
            if(dlgRegistroValores.tablaDetalles.getModel().getValueAt(i, 6).equals("GASTO")){
                total += Integer.valueOf(String.valueOf(dlgRegistroValores.tablaDetalles.getModel().getValueAt(i, 17)).replace(".", "").replace(",", ""));
            }
        }
        return (total);
    }
    
    public static int getRetiros() {
        int total = 0;
        DefaultTableModel tb = (DefaultTableModel) dlgRegistroValores.tablaDetalles.getModel();
        int fila = tb.getRowCount();
        for (int i = 0; i < fila; i++) {
            if(dlgRegistroValores.tablaDetalles.getModel().getValueAt(i, 6).equals("RETIRO")){
                total += Integer.valueOf(String.valueOf(dlgRegistroValores.tablaDetalles.getModel().getValueAt(i, 17)).replace(".", "").replace(",", ""));
            }
        }
        return (total);
    }
    
    public static int getCierre() {
        int total = 0;
        DefaultTableModel tb = (DefaultTableModel) dlgRegistroValores.tablaDetalles.getModel();
        int fila = tb.getRowCount();
        for (int i = 0; i < fila; i++) {
            if(dlgRegistroValores.tablaDetalles.getModel().getValueAt(i, 6).equals("CIERRE")){
                total += Integer.valueOf(String.valueOf(dlgRegistroValores.tablaDetalles.getModel().getValueAt(i, 17)).replace(".", "").replace(",", ""));
            }
        }
        return (total);
    }
    
    public static int getn50() {
        int total = 0;
        DefaultTableModel tb = (DefaultTableModel) dlgRegistroValores.tablaDetalles.getModel();
        int fila = tb.getRowCount();
        for (int i = 0; i < fila; i++) {
            //if(dlgRegistroValores.tablaDetalles.getModel().getValueAt(i, 6).equals("CIERRE")){
                total += Integer.valueOf(String.valueOf(dlgRegistroValores.tablaDetalles.getModel().getValueAt(i, 7)).replace(".", "").replace(",", ""));
            //}
        }
        return (total);
    }
    public static int getn100() {
        int total = 0;
        DefaultTableModel tb = (DefaultTableModel) dlgRegistroValores.tablaDetalles.getModel();
        int fila = tb.getRowCount();
        for (int i = 0; i < fila; i++) {
            //if(dlgRegistroValores.tablaDetalles.getModel().getValueAt(i, 6).equals("CIERRE")){
                total += Integer.valueOf(String.valueOf(dlgRegistroValores.tablaDetalles.getModel().getValueAt(i, 8)).replace(".", "").replace(",", ""));
            //}
        }
        return (total);
    }
    public static int getn500() {
        int total = 0;
        DefaultTableModel tb = (DefaultTableModel) dlgRegistroValores.tablaDetalles.getModel();
        int fila = tb.getRowCount();
        for (int i = 0; i < fila; i++) {
            //if(dlgRegistroValores.tablaDetalles.getModel().getValueAt(i, 6).equals("CIERRE")){
                total += Integer.valueOf(String.valueOf(dlgRegistroValores.tablaDetalles.getModel().getValueAt(i, 9)).replace(".", "").replace(",", ""));
            //}
        }
        return (total);
    }
    public static int getn1000() {
        int total = 0;
        DefaultTableModel tb = (DefaultTableModel) dlgRegistroValores.tablaDetalles.getModel();
        int fila = tb.getRowCount();
        for (int i = 0; i < fila; i++) {
            //if(dlgRegistroValores.tablaDetalles.getModel().getValueAt(i, 6).equals("CIERRE")){
                total += Integer.valueOf(String.valueOf(dlgRegistroValores.tablaDetalles.getModel().getValueAt(i, 10)).replace(".", "").replace(",", ""));
            //}
        }
        return (total);
    }
    public static int getn2000() {
        int total = 0;
        DefaultTableModel tb = (DefaultTableModel) dlgRegistroValores.tablaDetalles.getModel();
        int fila = tb.getRowCount();
        for (int i = 0; i < fila; i++) {
            //if(dlgRegistroValores.tablaDetalles.getModel().getValueAt(i, 6).equals("CIERRE")){
                total += Integer.valueOf(String.valueOf(dlgRegistroValores.tablaDetalles.getModel().getValueAt(i, 11)).replace(".", "").replace(",", ""));
            //}
        }
        return (total);
    }
    public static int getn5000() {
        int total = 0;
        DefaultTableModel tb = (DefaultTableModel) dlgRegistroValores.tablaDetalles.getModel();
        int fila = tb.getRowCount();
        for (int i = 0; i < fila; i++) {
            //if(dlgRegistroValores.tablaDetalles.getModel().getValueAt(i, 6).equals("CIERRE")){
                total += Integer.valueOf(String.valueOf(dlgRegistroValores.tablaDetalles.getModel().getValueAt(i, 12)).replace(".", "").replace(",", ""));
            //}
        }
        return (total);
    }
    public static int getn10000() {
        int total = 0;
        DefaultTableModel tb = (DefaultTableModel) dlgRegistroValores.tablaDetalles.getModel();
        int fila = tb.getRowCount();
        for (int i = 0; i < fila; i++) {
            //if(dlgRegistroValores.tablaDetalles.getModel().getValueAt(i, 6).equals("CIERRE")){
                total += Integer.valueOf(String.valueOf(dlgRegistroValores.tablaDetalles.getModel().getValueAt(i, 13)).replace(".", "").replace(",", ""));
            //}
        }
        return (total);
    }
    public static int getn20000() {
        int total = 0;
        DefaultTableModel tb = (DefaultTableModel) dlgRegistroValores.tablaDetalles.getModel();
        int fila = tb.getRowCount();
        for (int i = 0; i < fila; i++) {
            //if(dlgRegistroValores.tablaDetalles.getModel().getValueAt(i, 6).equals("CIERRE")){
                total += Integer.valueOf(String.valueOf(dlgRegistroValores.tablaDetalles.getModel().getValueAt(i, 14)).replace(".", "").replace(",", ""));
            //}
        }
        return (total);
    }
    public static int getn50000() {
        int total = 0;
        DefaultTableModel tb = (DefaultTableModel) dlgRegistroValores.tablaDetalles.getModel();
        int fila = tb.getRowCount();
        for (int i = 0; i < fila; i++) {
            //if(dlgRegistroValores.tablaDetalles.getModel().getValueAt(i, 6).equals("CIERRE")){
                total += Integer.valueOf(String.valueOf(dlgRegistroValores.tablaDetalles.getModel().getValueAt(i, 15)).replace(".", "").replace(",", ""));
            //}
        }
        return (total);
    }
    public static int getn100000() {
        int total = 0;
        DefaultTableModel tb = (DefaultTableModel) dlgRegistroValores.tablaDetalles.getModel();
        int fila = tb.getRowCount();
        for (int i = 0; i < fila; i++) {
            //if(dlgRegistroValores.tablaDetalles.getModel().getValueAt(i, 6).equals("CIERRE")){
                total += Integer.valueOf(String.valueOf(dlgRegistroValores.tablaDetalles.getModel().getValueAt(i, 16)).replace(".", "").replace(",", ""));
            //}
        }
        return (total);
    }

}
