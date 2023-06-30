package Controladores;

import Componentes.Fecha;
import Componentes.Login;
import Componentes.Mensajes;
import Datos.GestionarCaja;
import IU.dlgCaja;
import IU.dlgCajaDia;
import IU.dlgCajaDia1;
import Modelo.Caja;

public class ControlCaja {
static String UsuarioL="";
    public static String addCaja() {
        String msg;
        String caFechaI = Fecha.formatoFecha(dlgCaja.lbFecha.getText());
        System.out.println(caFechaI);
        String caHoraI= dlgCaja.lbHora.getText();
        System.out.println(caHoraI);
        String caUsuI= dlgCaja.lbUsuario.getText();
        String caUsuF=" ";
        int caInicial = Integer.parseInt(dlgCaja.txtCaInicial.getText().replace(",", "").replace(".", ""));

        Caja caja = new Caja(caFechaI,caHoraI,caInicial, 0, 0, 0, 0, 0,caUsuI,caUsuF);
        msg = GestionarCaja.addCaja(caja);
        if (msg == null) {
            Mensajes.informacion("Caja inicial del día establecida");
        } else {
            Mensajes.error(msg);
        }
        return msg;
    }
    
    public static String CerrarCaja() {
        String msg;
        int caId=Integer.parseInt(dlgCajaDia.lbNCaja.getText());
        String caFechaF = Fecha.fechaCorrecta();
        String caHoraF = Fecha.darHora();
        int caFinal = Integer.parseInt(dlgCajaDia.txtEntregar.getText().trim().replace(",", "").replace(".", ""));
        int caEntre = Integer.parseInt(dlgCajaDia.txtEntregado.getText().trim().replace(",", "").replace(".", ""));
        int caGastos = Integer.parseInt(dlgCajaDia.txtGastos.getText().trim().replace(",", "").replace(".", ""));
        int caDif = Integer.parseInt(dlgCajaDia.txtDiferencia.getText().trim().replace(",", "").replace(".", ""));
        int caMontoSalida = Integer.parseInt(dlgCajaDia.txtTotalSalida.getText().trim().replace(",", "").replace(".", ""));
        String caUsuF=UsuarioL=Login.getUsuarioLogueado();

        Caja caja = new Caja(caId, caFechaF, caHoraF, caFinal, caEntre, caGastos, caDif, caMontoSalida, caUsuF);
        msg = GestionarCaja.CerrarCaja(caja);
        if (msg == null) {
            Mensajes.informacion("CAJA DEL DÍA CERRADA!");
        } else {
            //Mensajes.informacion("Caja del día actualizada");
            Mensajes.error(msg);
        }
        return msg;
    }
    
     public static String CerrarCaja1() {
        String msg;
        int caId=Integer.parseInt(dlgCajaDia1.lbNCaja.getText());
        String caFechaF = Fecha.fechaCorrecta();
        String caHoraF = Fecha.darHora();
        int caFinal = Integer.parseInt(dlgCajaDia1.txtEntregar.getText().trim().replace(",", "").replace(".", ""));
        int caEntre = Integer.parseInt(dlgCajaDia1.txtEntregado.getText().trim().replace(",", "").replace(".", ""));
        int caGastos = Integer.parseInt(dlgCajaDia1.txtGastos.getText().trim().replace(",", "").replace(".", ""));
        int caDif = Integer.parseInt(dlgCajaDia1.txtDiferencia.getText().trim().replace(",", "").replace(".", ""));
        int caMontoSalida = Integer.parseInt(dlgCajaDia1.txtTotalSalida.getText().trim().replace(",", "").replace(".", ""));
        String caUsuF=Login.getUsuarioLogueado();

        Caja caja = new Caja(caId, caFechaF, caHoraF, caFinal, caEntre, caGastos, caDif, caMontoSalida, caUsuF);
        msg = GestionarCaja.CerrarCaja(caja);
        if (msg == null) {
            Mensajes.informacion("CAJA DEL MODIFICADA Y/O CERRADA!");
        } else {
            //Mensajes.informacion("Caja del día actualizada");
            Mensajes.error(msg);
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
            Mensajes.informacion("CAJA DEL DÍA ACTUALIZADA!");
        } else {
            //Mensajes.informacion("Caja del día actualizada");
            Mensajes.error(msg);
        }
        return msg;
    }
    
    public static String addBoca1() {
        String msg;
        int idCaja = Integer.parseInt(dlgCajaDia.lbNCaja.getText());
        int idBoca=1;
        int Entregar = Integer.parseInt(dlgCajaDia.txtB1.getText().trim().replace(",", "").replace(".", ""));
        int Entregado = Integer.parseInt(dlgCajaDia.txtEntregado_boca_1.getText().trim().replace(",", "").replace(".", ""));
        int Gastos = Integer.parseInt(dlgCajaDia.txtGastos_boca_1.getText().trim().replace(",", "").replace(".", ""));
        int Dif = Integer.parseInt(dlgCajaDia.txtDiferencia_boca_1.getText().trim().replace(",", "").replace(".", ""));

        //Caja caja = new Caja(caId, caFinal, caEntre, caGastos, caDif, caMontoSalida);
        msg = GestionarCaja.addBoca(idBoca, idCaja, Entregar, Entregado, Gastos, Dif);
        if (msg == null) {
            Mensajes.informacion("BOCA DE COBRANZA 1 REGISTRADO!");
        } else {
            //Mensajes.informacion("Caja del día actualizada");
            Mensajes.error(msg);
        }
        return msg;
    }
    
    public static String addBoca1_1() {
        String msg;
        int idCaja = Integer.parseInt(dlgCajaDia1.lbNCaja.getText());
        int idBoca=1;
        int Entregar = Integer.parseInt(dlgCajaDia1.txtB1.getText().trim().replace(",", "").replace(".", ""));
        int Entregado = Integer.parseInt(dlgCajaDia1.txtEntregado_boca_1.getText().trim().replace(",", "").replace(".", ""));
        int Gastos = Integer.parseInt(dlgCajaDia1.txtGastos_boca_1.getText().trim().replace(",", "").replace(".", ""));
        int Dif = Integer.parseInt(dlgCajaDia1.txtDiferencia_boca_1.getText().trim().replace(",", "").replace(".", ""));

        //Caja caja = new Caja(caId, caFinal, caEntre, caGastos, caDif, caMontoSalida);
        msg = GestionarCaja.addBoca(idBoca, idCaja, Entregar, Entregado, Gastos, Dif);
        if (msg == null) {
            Mensajes.informacion("BOCA DE COBRANZA 1 REGISTRADO!");
        } else {
            //Mensajes.informacion("Caja del día actualizada");
            Mensajes.error(msg);
        }
        return msg;
    }
    
    public static String addBoca2() {
        String msg;
        int idCaja = Integer.parseInt(dlgCajaDia.lbNCaja.getText());
        int idBoca=2;
        int Entregar = Integer.parseInt(dlgCajaDia.txtB2.getText().trim().replace(",", "").replace(".", ""));
        int Entregado = Integer.parseInt(dlgCajaDia.txtEntregado_boca_2.getText().trim().replace(",", "").replace(".", ""));
        int Gastos = Integer.parseInt(dlgCajaDia.txtGastos_boca_2.getText().trim().replace(",", "").replace(".", ""));
        int Dif = Integer.parseInt(dlgCajaDia.txtDiferencia_boca_2.getText().trim().replace(",", "").replace(".", ""));

        //Caja caja = new Caja(caId, caFinal, caEntre, caGastos, caDif, caMontoSalida);
        msg = GestionarCaja.addBoca(idBoca, idCaja, Entregar, Entregado, Gastos, Dif);
        if (msg == null) {
            Mensajes.informacion("BOCA DE COBRANZA 2 REGISTRADO!");
        } else {
            //Mensajes.informacion("Caja del día actualizada");
            Mensajes.error(msg);
        }
        return msg;
    }
    
    public static String addBoca2_1() {
        String msg;
        int idCaja = Integer.parseInt(dlgCajaDia1.lbNCaja.getText());
        int idBoca=2;
        int Entregar = Integer.parseInt(dlgCajaDia1.txtB2.getText().trim().replace(",", "").replace(".", ""));
        int Entregado = Integer.parseInt(dlgCajaDia1.txtEntregado_boca_2.getText().trim().replace(",", "").replace(".", ""));
        int Gastos = Integer.parseInt(dlgCajaDia1.txtGastos_boca_2.getText().trim().replace(",", "").replace(".", ""));
        int Dif = Integer.parseInt(dlgCajaDia1.txtDiferencia_boca_2.getText().trim().replace(",", "").replace(".", ""));

        //Caja caja = new Caja(caId, caFinal, caEntre, caGastos, caDif, caMontoSalida);
        msg = GestionarCaja.addBoca(idBoca, idCaja, Entregar, Entregado, Gastos, Dif);
        if (msg == null) {
            Mensajes.informacion("BOCA DE COBRANZA 2 REGISTRADO!");
        } else {
            //Mensajes.informacion("Caja del día actualizada");
            Mensajes.error(msg);
        }
        return msg;
    }
    
    public static String actBoca1() {
        String msg;
        int idCaja = Integer.parseInt(dlgCajaDia.lbNCaja.getText());
        int idBoca=1;
        int Entregar = Integer.parseInt(dlgCajaDia.txtB1.getText().trim().replace(",", "").replace(".", ""));
        int Entregado = Integer.parseInt(dlgCajaDia.txtEntregado_boca_1.getText().trim().replace(",", "").replace(".", ""));
        int Gastos = Integer.parseInt(dlgCajaDia.txtGastos_boca_1.getText().trim().replace(",", "").replace(".", ""));
        int Dif = Integer.parseInt(dlgCajaDia.txtDiferencia_boca_1.getText().trim().replace(",", "").replace(".", ""));

        //Caja caja = new Caja(caId, caFinal, caEntre, caGastos, caDif, caMontoSalida);
        msg = GestionarCaja.actBoca(idBoca, idCaja, Entregar, Entregado, Gastos, Dif);
        if (msg == null) {
            Mensajes.informacion("BOCA DE COBRANZA 1 ACTUALIZADO!");
        } else {
            //Mensajes.informacion("Caja del día actualizada");
            Mensajes.error(msg);
        }
        return msg;
    }
    
    public static String actBoca1_1() {
        String msg;
        int idCaja = Integer.parseInt(dlgCajaDia1.lbNCaja.getText());
        int idBoca=1;
        int Entregar = Integer.parseInt(dlgCajaDia1.txtB1.getText().trim().replace(",", "").replace(".", ""));
        int Entregado = Integer.parseInt(dlgCajaDia1.txtEntregado_boca_1.getText().trim().replace(",", "").replace(".", ""));
        int Gastos = Integer.parseInt(dlgCajaDia1.txtGastos_boca_1.getText().trim().replace(",", "").replace(".", ""));
        int Dif = Integer.parseInt(dlgCajaDia1.txtDiferencia_boca_1.getText().trim().replace(",", "").replace(".", ""));

        //Caja caja = new Caja(caId, caFinal, caEntre, caGastos, caDif, caMontoSalida);
        msg = GestionarCaja.actBoca(idBoca, idCaja, Entregar, Entregado, Gastos, Dif);
        if (msg == null) {
            Mensajes.informacion("BOCA DE COBRANZA 1 ACTUALIZADO!");
        } else {
            //Mensajes.informacion("Caja del día actualizada");
            Mensajes.error(msg);
        }
        return msg;
    }
    
    public static String actBoca2() {
        String msg;
        int idCaja = Integer.parseInt(dlgCajaDia.lbNCaja.getText());
        int idBoca=2;
        int Entregar = Integer.parseInt(dlgCajaDia.txtB2.getText().trim().replace(",", "").replace(".", ""));
        int Entregado = Integer.parseInt(dlgCajaDia.txtEntregado_boca_2.getText().trim().replace(",", "").replace(".", ""));
        int Gastos = Integer.parseInt(dlgCajaDia.txtGastos_boca_2.getText().trim().replace(",", "").replace(".", ""));
        int Dif = Integer.parseInt(dlgCajaDia.txtDiferencia_boca_2.getText().trim().replace(",", "").replace(".", ""));

        //Caja caja = new Caja(caId, caFinal, caEntre, caGastos, caDif, caMontoSalida);
        msg = GestionarCaja.actBoca(idBoca, idCaja, Entregar, Entregado, Gastos, Dif);
        if (msg == null) {
            Mensajes.informacion("BOCA DE COBRANZA 2 ACTUALIZADO!");
        } else {
            //Mensajes.informacion("Caja del día actualizada");
            Mensajes.error(msg);
        }
        return msg;
    }
    
    public static String actBoca2_1() {
        String msg;
        int idCaja = Integer.parseInt(dlgCajaDia1.lbNCaja.getText());
        int idBoca=2;
        int Entregar = Integer.parseInt(dlgCajaDia1.txtB2.getText().trim().replace(",", "").replace(".", ""));
        int Entregado = Integer.parseInt(dlgCajaDia1.txtEntregado_boca_2.getText().trim().replace(",", "").replace(".", ""));
        int Gastos = Integer.parseInt(dlgCajaDia1.txtGastos_boca_2.getText().trim().replace(",", "").replace(".", ""));
        int Dif = Integer.parseInt(dlgCajaDia1.txtDiferencia_boca_2.getText().trim().replace(",", "").replace(".", ""));

        //Caja caja = new Caja(caId, caFinal, caEntre, caGastos, caDif, caMontoSalida);
        msg = GestionarCaja.actBoca(idBoca, idCaja, Entregar, Entregado, Gastos, Dif);
        if (msg == null) {
            Mensajes.informacion("BOCA DE COBRANZA 2 ACTUALIZADO!");
        } else {
            //Mensajes.informacion("Caja del día actualizada");
            Mensajes.error(msg);
        }
        return msg;
    }

}
