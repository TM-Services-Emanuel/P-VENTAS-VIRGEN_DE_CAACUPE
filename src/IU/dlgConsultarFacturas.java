package IU;

import Componentes.DataSourceService1;
import Componentes.Empresa;
import Componentes.Fecha;
import Componentes.Login;
import Componentes.Reporte;
import Componentes.Mensajes;
import Componentes.Notif;
import Componentes.Numero_a_Letra;
import Componentes.PrinterService;
import Componentes.Redondeo;
import Componentes.RenderDecimal1;
import Componentes.RenderDecimal2;
import Componentes.RenderDecimalconPuntos;
import Componentes.Software;
import Componentes.Tickets;
import Componentes.Timbrado;
import Componentes.cargarComboBox;
import Controladores.CabecerasTablas;
import Controladores.controlFactura;
import java.awt.event.KeyEvent;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class dlgConsultarFacturas extends javax.swing.JDialog {

    CabecerasTablas cabe = new CabecerasTablas();
    public static Connection con;
    public static Connection conM;
    public Reporte jasper;
    static public Numero_a_Letra d;
    static DataSourceService1 dss1 = new DataSourceService1();

    public dlgConsultarFacturas(java.awt.Frame parent, boolean modal) throws SQLException {
        super(parent, modal);
        initComponents();
        txtDesde.setVisible(false);
        titulo();
        jasper = new Reporte();
        cabe.consFacturas(tblFactura);
        cabe.detalleFactura(tblDetalle);
        txtDesde.setText(Fecha.formatoFecha(dcDesde.getText()));
        Renders();
        d = new Numero_a_Letra();
        invisible();
        txtE.setVisible(false);
        txt5.setVisible(false);
        txt10.setVisible(false);
        txtV.setVisible(false);
        CargarDatos();
        CalcularValores();
        Visor();

    }
    
    private void AccesoRapido(int n) {
        switch (n) {
            case KeyEvent.VK_ALT | KeyEvent.VK_F4 ->
                btnSalir.doClick();
            default -> {
            }
        }
        System.out.println(n);
    }

    private void Visor() {
        switch (Login.getPerfil()) {
            case "ADMINISTRADOR" -> {
                lbTotal.setVisible(true);
                lbAnulados.setVisible(true);
            }
            case "DESARROLLADOR" -> {
                lbTotal.setVisible(true);
                lbAnulados.setVisible(true);
            }
            default -> {
                lbTotal.setVisible(false);
                lbAnulados.setVisible(false);
            }
        }
    }

    private void CargarDatos() {
        cargarComboBox.cargar2(cbVendedores, "SELECT CodVend, NombreVendedor FROM v_usuario WHERE usu_indicador='S' AND CodPerfil=2");
        if (cbVendedores.getSelectedIndex() == 0) {
            controlFactura.listFacturas(tblFactura, txtDesde.getText().trim());
        } else {
            int idV = Integer.parseInt(txtV.getText().trim());
            controlFactura.listFacturas1(tblFactura, idV, txtDesde.getText().trim());
        }
    }

    private void CalcularValores() {
        String facturado = String.valueOf(controlFactura.getTotalFacturado());
        String anulado = String.valueOf(controlFactura.getTotalFacturadoAnulado());
        DecimalFormat df = new DecimalFormat("#,###");
        lbTotal.setText("FACTURADO: " + df.format(Integer.valueOf(facturado.replace(".", "").replace(",", ""))));
        lbAnulados.setText("ANULADO: " + df.format(Integer.valueOf(anulado.replace(".", "").replace(",", ""))));
    }

    private void visible() {
        L1.setVisible(true);
        L2.setVisible(true);
        L3.setVisible(true);
        L4.setVisible(true);
        L5.setVisible(true);
        L6.setVisible(true);
        L7.setVisible(true);
        L8.setVisible(true);
        divisor.setVisible(true);
    }

    private void invisible() {
        L1.setVisible(false);
        L2.setVisible(false);
        L3.setVisible(false);
        L4.setVisible(false);
        L5.setVisible(false);
        L6.setVisible(false);
        L7.setVisible(false);
        L8.setVisible(false);
        divisor.setVisible(false);
    }

    final void titulo() {
        if (Software.getSoftware().equals("null")) {
            this.setTitle("Gestión de ventas realizadas");
        } else {
            this.setTitle(Software.getSoftware() + " - Gestión de ventas realizadas");
        }
    }

    public static void Renders() {
        dlgConsultarFacturas.tblFactura.getColumnModel().getColumn(11).setCellRenderer(new RenderDecimal2());
    }

    public static void RendersD() {
        dlgConsultarFacturas.tblDetalle.getColumnModel().getColumn(0).setCellRenderer(new RenderDecimalconPuntos());
        dlgConsultarFacturas.tblDetalle.getColumnModel().getColumn(6).setCellRenderer(new RenderDecimal1());
        dlgConsultarFacturas.tblDetalle.getColumnModel().getColumn(7).setCellRenderer(new RenderDecimal2());
    }

    public static int getExcetas() {
        int total = 0;
        DefaultTableModel tb = (DefaultTableModel) tblDetalle.getModel();
        int fila = tb.getRowCount();
        for (int i = 0; i < fila; i++) {
            total += Integer.valueOf(String.valueOf(tblDetalle.getModel().getValueAt(i, 10)).replace(".", "").replace(",", ""));
        }
        return Redondeo.redondearI(total);
    }

    public static int getCinco() {
        int total = 0;
        DefaultTableModel tb = (DefaultTableModel) tblDetalle.getModel();
        int fila = tb.getRowCount();
        for (int i = 0; i < fila; i++) {
            total += Integer.valueOf(String.valueOf(tblDetalle.getModel().getValueAt(i, 11)).replace(".", "").replace(",", ""));
        }
        return Redondeo.redondearI(total);
    }

    public static int getDiez() {
        int total = 0;
        DefaultTableModel tb = (DefaultTableModel) tblDetalle.getModel();
        int fila = tb.getRowCount();
        for (int i = 0; i < fila; i++) {
            total += Integer.valueOf(String.valueOf(tblDetalle.getModel().getValueAt(i, 12)).replace(".", "").replace(",", ""));
        }
        return Redondeo.redondearI(total);
    }

    public static void llamarReporteHoja3(int cod, String Letra) throws SQLException {
        Reporte gr;
        gr = new Reporte();
        gr.FacturaLegal("\\Reports\\ventas\\Hoja3.jasper", "ID", cod, "LETRA", Letra);
        gr.cerrar();
    }

    public static void limpiarCampos() {
        txtCodCliente.setText("");
        txtRuc.setText("");
        txtRazonSocial.setText("");
        txtVendedor.setText("");
        txtEfectivo.setText("");
        txtTransferencia.setText("");
        txtTransfBoleta.setText("");
        txtQR.setText("");
        txtQRBoleta.setText("");
        txtVuelto.setText("");
        txtV.setText("");
        txtE.setText("");
        txt5.setText("");
        txt10.setText("");
    }

    public static void imprimirTicket(int idEmision, int total, String Condicion, String Numeracion, String Fecha, String Hora, String Vendedor, String RazonSocial, String RUC, int efectivo, int tranf, int qr, int abon, int vuel) {
        //Impresora matricial tmu-220
        if (Tickets.getImpresora() == null) {
            Mensajes.Alerta("No se encuentra instalada la impresora predeterminada para este punto de impresión");
        } else {

            PrinterService printerService = new PrinterService();

            final byte[] openCD = {27, 112, 0, 60, 120};
            printerService.printBytes2(Tickets.getImpresora(), openCD);

            System.out.println(printerService.getPrinters());
            int filas = tblDetalle.getRowCount();
            DecimalFormat formateador = new DecimalFormat("#,###");
            String tot = formateador.format(total);
            String Ticket = "         " + Empresa.getEmpresa() + "\r\n";
            Ticket += "           VENTAS DE LACTEOS LACTOLANDA\r\n";
            Ticket += "                 RUC: " + Empresa.getRUC() + "\r\n";
            Ticket += "               CEL: " + Empresa.getCelular() + "\r\n";
            Ticket += Empresa.getDireccion() + "\r\n";
            Ticket += "     CNEL. OVIEDO - DPTO. DE CAAGUAZU - PY\r\n";
            Ticket += "-----------------------------------------------\r\n";
            Ticket += "TICKET " + Condicion + " NRO: " + Numeracion + "\r\n";
            Ticket += "FECHA/HORA: " + Fecha + " " + Hora + "\r\n";
            Ticket += "VENDEDOR: " + Vendedor + "\r\n";
            Ticket += "\r\n";
            Ticket += "CLIENTE: " + RazonSocial + "\r\n";
            Ticket += "RUC/CI: " + RUC + "\r\n";
            Ticket += "-----------------------------------------------\n";
            Ticket += String.format("%1$1s %2$10s %3$1s %4$12s %5$16s", "IVA", "CANT", "", "PRECIO", "   SUBTOTAL");
            Ticket += "\r\n";
            Ticket += "-----------------------------------------------\r\n";
            for (int i = 0; i < filas; i++) {
                String codB = tblDetalle.getValueAt(i, 4).toString().trim() + " " + tblDetalle.getValueAt(i, 8).toString().trim();
                String Descripcion = tblDetalle.getValueAt(i, 5).toString().trim();
                String cant = tblDetalle.getValueAt(i, 0).toString().trim();
                String Punit = tblDetalle.getValueAt(i, 6).toString().trim();
                String Mont = tblDetalle.getValueAt(i, 7).toString().trim();
                int idiva = Integer.parseInt(tblDetalle.getValueAt(i, 9).toString().trim());
                String iva = null;
                switch (idiva) {
                    case 1 ->
                        iva = "EXENTA";
                    case 2 ->
                        iva = "5%";
                    case 3 ->
                        iva = "10%";
                    default -> {
                    }
                }

                //Ticket += String.format("%1$1s", codB + "-" + Descripcion + "\n");
                Ticket += String.format("%1$1s", codB + "\r\n");
                Ticket += String.format("%1$1s", Descripcion + "\r\n");
                Ticket += String.format("%1$-9s %2$-12s %3$-14s %4$-10s", iva, cant, formateador.format(Integer.parseInt(Punit.replace(".", "").replace(",", ""))), formateador.format(Integer.parseInt(Mont.replace(".", "").replace(",", ""))));
            }
            Ticket += "\r\n";
            Ticket += "===============================================\r\n";
            Ticket += String.format("%1$5s %2$20s", "TOTAL A PAGAR Gs:", tot) + "\r\n";
            Ticket += "===============================================\r\n";
            Ticket += "\r\n";
            Ticket += "DETALLE DE PAGO\r\n";
            Ticket += "EFECTIVO:         " + formateador.format(efectivo) + "\r\n";
            Ticket += "TRANSF. BANCARIA: " + formateador.format(tranf) + "\r\n";
            Ticket += "QR:               " + formateador.format(qr) + "\r\n";
            Ticket += "\r\n";
            Ticket += "TOTAL ABONADO:    " + formateador.format(abon) + "\r\n";
            Ticket += "VUELTO:           " + formateador.format(vuel) + "\r\n";
            Ticket += "\r\n";
            Ticket += "         " + Empresa.getEmpresa() + "\r\n";
            Ticket += "             AGRADECE SU PREFERENCIA\r\n";
            Ticket += "\r\n";
            Ticket += "\r\n";
            Ticket += "\r\n";
            Ticket += "\r\n";

            //printerService.printString(Ticket);
            //print some stuff
            // cut that paper!
            //byte[] cutP = new byte[]{0x1d, 'V', 1};
            //final byte[] openCD={27,112,0,60,120};
            // printerService.printBytes(openCD);
            //printerService.printBytes(cutP);
            try {
                printerService.printString2(Tickets.getImpresora(), Ticket);
                byte[] cutP = new byte[]{0x1d, 'V', 1};
                printerService.printBytes2(Tickets.getImpresora(), cutP);
            } catch (Exception e) {
                Mensajes.error("No se encuentra instalada la impresora predeterminada para este punto de impresión");
            }
        }

    }

    public static void imprimirFacturaOriginal(String estado, int idEmision, int Total, String Timbrad, String Desde, String Hasta, String Condicion, String NroFactura,
            String Fecha, String Hora, String Vendedor, String Cliente, String Ruc, String Exenta, String Cinco, String Diez, int iv5, int iv10, int efectivo, int tranf, int qr, int abonado, int vuelto) {
        //Impresora matricial tmu-220
        if (Timbrado.getImpresora() == null) {
            Mensajes.Alerta("No se encuentra instalada la impresora predeterminada para este punto de impresión");
        } else {
            PrinterService printerService = new PrinterService();

            final byte[] openCD = {27, 112, 0, 60, 120};
            printerService.printBytes2(Timbrado.getImpresora(), openCD);

            System.out.println(printerService.getPrinters());
            int filas = tblDetalle.getRowCount();
            DecimalFormat formateador = new DecimalFormat("#,###");
            String tot = formateador.format(Total);
            String Ticket = "                " + estado + "\r\n";
            Ticket += "         " + Empresa.getEmpresa() + "\r\n";
            Ticket += "           VENTAS DE LACTEOS LACTOLANDA\r\n";
            Ticket += "                 RUC: " + Empresa.getRUC() + "\r\n";
            Ticket += "               CEL: " + Empresa.getCelular() + "\r\n";
            Ticket += Empresa.getDireccion() + "\r\n";
            Ticket += "     CNEL. OVIEDO - DPTO. DE CAAGUAZU - PY\r\n";
            Ticket += "-----------------------------------------------\r\n";
            Ticket += "              TIMBRADO: " + Timbrad + "\r\n";
            Ticket += "  VALIDO DESDE: " + Desde + " HASTA: " + Hasta + "\r\n";
            Ticket += "               I.V.A. INCLUIDO\r\n";
            Ticket += "----------------------------------------------\r\n";
            Ticket += "FACTURA " + Condicion + " NRO: " + NroFactura + "\r\n";
            Ticket += "FECHA/HORA: " + Fecha + " " + Hora + "\r\n";
            Ticket += "VENDEDOR: " + Vendedor + "\r\n";
            Ticket += "\r\n";
            Ticket += "CLIENTE: " + Cliente + "\r\n";
            Ticket += "RUC/CI: " + Ruc + "\r\n";
            Ticket += "----------------------------------------------\n";
            Ticket += String.format("%1$1s %2$10s %3$1s %4$12s %5$16s", "IVA", "CANT", "", "PRECIO", "   SUBTOTAL");
            Ticket += "\r\n";
            Ticket += "----------------------------------------------\r\n";
            for (int i = 0; i < filas; i++) {
                String codB = tblDetalle.getValueAt(i, 4).toString().trim() + " " + tblDetalle.getValueAt(i, 8).toString().trim();
                String Descripcion = tblDetalle.getValueAt(i, 5).toString().trim();
                String cant = tblDetalle.getValueAt(i, 0).toString().trim();
                String Punit = tblDetalle.getValueAt(i, 6).toString().trim();
                String Mont = tblDetalle.getValueAt(i, 7).toString().trim();
                int idiva = Integer.parseInt(tblDetalle.getValueAt(i, 9).toString().trim());
                String iva = null;
                switch (idiva) {
                    case 1 ->
                        iva = "EXENTA";
                    case 2 ->
                        iva = "5%";
                    case 3 ->
                        iva = "10%";
                    default -> {
                    }
                }

                //Ticket += String.format("%1$1s", codB + "-" + Descripcion + "\n");
                Ticket += String.format("%1$1s", codB + "\r\n");
                Ticket += String.format("%1$1s", Descripcion + "\r\n");
                Ticket += String.format("%1$-9s %2$-12s %3$-14s %4$-10s", iva, cant, formateador.format(Integer.parseInt(Punit.replace(".", "").replace(",", ""))), formateador.format(Integer.parseInt(Mont.replace(".", "").replace(",", ""))));
            }
            Ticket += "\r\n";
            Ticket += "==============================================\r\n";
            Ticket += String.format("%1$5s %2$20s", "TOTAL A PAGAR Gs:", tot) + "\r\n";
            //Ticket += "           TOTAL Gs.:"+tot+"\n";
            Ticket += "==============================================\r\n";
            String letras = d.Convertir(tot.replace(".", "").replace(",", ""), true);
            Ticket += String.format("%1$1s", letras + "\r\n");
            //Ticket += "\n";
            Ticket += "==============================================\r\n";
            Ticket += "\r\n";
            Ticket += "-------------- TOTALES GRAVADA ---------------\r\n";
            Ticket += "EXENTAS     ------>              " + formateador.format(Integer.parseInt(Exenta.replace(".", "").replace(",", ""))) + "\r\n";
            Ticket += "GRAVADA 5%  ------>              " + formateador.format(Integer.parseInt(Cinco.replace(".", "").replace(",", ""))) + "\r\n";
            Ticket += "GRAVADA 10% ------>              " + formateador.format(Integer.parseInt(Diez.replace(".", "").replace(",", ""))) + "\r\n";
            Ticket += "----------- LIQUIDACION DEL I.V.A. -----------\r\n";
            //int iv5 = Redondeo.redondearI(Integer.parseInt(Cinco.replace(".", "").replace(",", "")) / 21);
            Ticket += "I.V.A. 5%   ------>              " + formateador.format(iv5) + "\r\n";
            //int iv10 = Redondeo.redondearI(Integer.parseInt(Diez.replace(".", "").replace(",", "")) / 11);
            Ticket += "I.V.A. 10%  ------>              " + formateador.format(iv10) + "\r\n";
            Ticket += "----------------------------------------------\r\n";
            String totaliva = String.valueOf(iv5 + iv10);
            //Ticket += String.format("%1$5s %2$23s", "TOTAL I.V.A.", formateador.format(Integer.parseInt(totaliva.replace(".", "").replace(",", "")))) + "\n";
            Ticket += "TOTAL I.V.A.: " + formateador.format(Integer.parseInt(totaliva.replace(".", "").replace(",", ""))) + "\r\n";
            Ticket += "----------------------------------------------\r\n";
            Ticket += "\r\n";
            Ticket += "DETALLE DE PAGO\r\n";
            Ticket += "EFECTIVO:         " + formateador.format(efectivo) + "\r\n";
            Ticket += "TRANSF. BANCARIA: " + formateador.format(tranf) + "\r\n";
            Ticket += "QR:               " + formateador.format(qr) + "\r\n";
            Ticket += "\r\n";
            Ticket += "TOTAL ABONADO:    " + formateador.format(abonado) + "\r\n";
            Ticket += "VUELTO:           " + formateador.format(vuelto) + "\r\n";
            Ticket += "\r\n";
            Ticket += "ORIGINAL:  CLIENTE\r\n";
            // Ticket += "DUPLICADO: Archivo Tributario\n";
            Ticket += "\r\n";
            Ticket += "         " + Empresa.getEmpresa() + "\r\n";
            Ticket += "             AGRADECE SU PREFERENCIA\r\n";
            Ticket += "\r\n";
            Ticket += "\r\n";
            Ticket += "\r\n";
            Ticket += "\r\n";

            try {
                printerService.printString2(Timbrado.getImpresora(), Ticket);
                byte[] cutP = new byte[]{0x1d, 'V', 1};
                printerService.printBytes2(Timbrado.getImpresora(), cutP);
            } catch (Exception e) {
                Mensajes.error("No se encuentra instalada la impresora predeterminada para este punto de impresión");
            }
        }
    }

    public static void imprimirFacturaDuplicado(String estado, int idEmision, int Total, String Timbrado, String Desde, String Hasta, String Condicion, String NroFactura,
            String Fecha, String Hora, String Vendedor, String Cliente, String Ruc, String Exenta, String Cinco, String Diez, int iv5, int iv10) {
        //Impresora matricial tmu-220
        String ImpresoraPred = null;
        String sql = "SELECT * FROM v_puntoemision4 WHERE idemision=" + idEmision;
        try (Connection cn = dss1.getDataSource().getConnection(); Statement st = cn.createStatement(); ResultSet res = st.executeQuery(sql)) {
            res.last();
            do {
                ImpresoraPred = res.getString("impresora").trim();
                System.out.println(ImpresoraPred);

            } while (res.next());
            res.close();
            st.close();
            cn.close();
        } catch (Exception e) {
        }
        if (ImpresoraPred == null) {
            Mensajes.Alerta("No se encuentra instalada la impresora predeterminada para este punto de impresión");
        } else {
            String empresa = null;
            String ruc = null;
            String celular = null;
            String direccion = null;

            PrinterService printerService = new PrinterService();

            final byte[] openCD = {27, 112, 0, 60, 120};
            printerService.printBytes2(ImpresoraPred, openCD);

            System.out.println(printerService.getPrinters());
            int filas = tblDetalle.getRowCount();
            DecimalFormat formateador = new DecimalFormat("#,###");
            String tot = formateador.format(Total);
            String sql1 = "SELECT * FROM empresa WHERE estado='S'";
            try (Connection cn = dss1.getDataSource().getConnection(); Statement st = cn.createStatement(); ResultSet rs = st.executeQuery(sql1)) {
                rs.last();
                empresa = rs.getString("razon_social");
                ruc = rs.getString("ruc");
                celular = rs.getString("telefono")/* + "-" + rs.getString("em_celular")*/;
                direccion = rs.getString("direccion");
                rs.close();
                st.close();
                cn.close();
            } catch (SQLException ex) {
                Mensajes.error("Error obteniendo datos de la empresa para la impresion de factura.");
            }
            String Ticket = "                " + estado + "\n";
            Ticket += "         " + empresa + "\n";
            Ticket += "           VENTAS DE LACTEOS LACTOLANDA\n";
            Ticket += "                 RUC: " + ruc + "\n";
            Ticket += "               CEL: " + celular + "\n";
            Ticket += direccion + "\n";
            Ticket += "     CNEL. OVIEDO - DPTO. DE CAAGUAZU - PY\n";
            Ticket += "-----------------------------------------------\n";
            Ticket += "              TIMBRADO: " + Timbrado + "\n";
            Ticket += "  VALIDO DESDE: " + Desde + " HASTA: " + Hasta + "\n";
            Ticket += "               I.V.A. INCLUIDO\n";
            Ticket += "----------------------------------------------\n";
            Ticket += "FACTURA " + Condicion + " NRO: " + NroFactura + "\n";
            Ticket += "FECHA/HORA: " + Fecha + " " + Hora + "\n";
            Ticket += "VENDEDOR: " + Vendedor + "\n";
            Ticket += "\n";
            Ticket += "CLIENTE: " + Cliente + "\n";
            Ticket += "RUC/CI: " + Ruc + "\n";
            Ticket += "----------------------------------------------\n";
            Ticket += String.format("%1$1s %2$10s %3$1s %4$12s %5$16s", "IVA", "CANT", "", "PRECIO", "   SUBTOTAL");
            Ticket += "\n";
            Ticket += "----------------------------------------------\n";
            for (int i = 0; i < filas; i++) {
                String codB = tblDetalle.getValueAt(i, 4).toString().trim() + " " + tblDetalle.getValueAt(i, 8).toString().trim();
                String Descripcion = tblDetalle.getValueAt(i, 5).toString().trim();
                String cant = tblDetalle.getValueAt(i, 0).toString().trim();
                String Punit = tblDetalle.getValueAt(i, 6).toString().trim();
                String Mont = tblDetalle.getValueAt(i, 7).toString().trim();
                int idiva = Integer.parseInt(tblDetalle.getValueAt(i, 9).toString().trim());
                String iva = null;
                switch (idiva) {
                    case 1 ->
                        iva = "EXENTA";
                    case 2 ->
                        iva = "5%";
                    case 3 ->
                        iva = "10%";
                    default -> {
                    }
                }

                //Ticket += String.format("%1$1s", codB + "-" + Descripcion + "\n");
                Ticket += String.format("%1$1s", codB + "\n");
                Ticket += String.format("%1$1s", Descripcion + "\n");
                Ticket += String.format("%1$-9s %2$-12s %3$-14s %4$-10s", iva, cant, formateador.format(Integer.parseInt(Punit.replace(".", "").replace(",", ""))), formateador.format(Integer.parseInt(Mont.replace(".", "").replace(",", ""))));
            }
            Ticket += "\n";
            Ticket += "==============================================\n";
            Ticket += String.format("%1$5s %2$20s", "TOTAL A PAGAR Gs:", tot) + "\n";
            //Ticket += "           TOTAL Gs.:"+tot+"\n";
            Ticket += "==============================================\n";
            String letras = d.Convertir(tot.replace(".", "").replace(",", ""), true);
            Ticket += String.format("%1$1s", letras + "\n");
            //Ticket += "\n";
            Ticket += "==============================================\n";
            Ticket += "\n";
            Ticket += "-------------- TOTALES GRAVADA ---------------\n";
            Ticket += "EXENTAS     ------>              " + formateador.format(Integer.parseInt(Exenta.replace(".", "").replace(",", ""))) + "\n";
            Ticket += "GRAVADA 5%  ------>              " + formateador.format(Integer.parseInt(Cinco.replace(".", "").replace(",", ""))) + "\n";
            Ticket += "GRAVADA 10% ------>              " + formateador.format(Integer.parseInt(Diez.replace(".", "").replace(",", ""))) + "\n";
            Ticket += "----------- LIQUIDACION DEL I.V.A. -----------\n";
            //int iv5 = Redondeo.redondearI(Integer.parseInt(Cinco.replace(".", "").replace(",", "")) / 21);
            Ticket += "I.V.A. 5%   ------>              " + formateador.format(iv5) + "\n";
            //int iv10 = Redondeo.redondearI(Integer.parseInt(Diez.replace(".", "").replace(",", "")) / 11);
            Ticket += "I.V.A. 10%  ------>              " + formateador.format(iv10) + "\n";
            Ticket += "----------------------------------------------\n";
            String totaliva = String.valueOf(iv5 + iv10);
            //Ticket += String.format("%1$5s %2$23s", "TOTAL I.V.A.", formateador.format(Integer.parseInt(totaliva.replace(".", "").replace(",", "")))) + "\n";
            Ticket += "TOTAL I.V.A.: " + formateador.format(Integer.parseInt(totaliva.replace(".", "").replace(",", ""))) + "\n";
            Ticket += "----------------------------------------------\n";
            Ticket += "\n";
            Ticket += "EFECTIVO:  0\n";
            Ticket += "VUELTO:    0\n";
            Ticket += "\n";
            //Ticket += "ORIGINAL:  CLIENTE\n";
            Ticket += "DUPLICADO: Archivo Tributario\n";
            Ticket += "\n";
            Ticket += "         " + empresa + "\n";
            Ticket += "             AGRADECE SU PREFERENCIA\n";
            Ticket += "\n";
            Ticket += "\n";
            Ticket += "\n";
            Ticket += "\n";

            try {
                printerService.printString2(ImpresoraPred, Ticket);
                byte[] cutP = new byte[]{0x1d, 'V', 1};
                printerService.printBytes2(ImpresoraPred, cutP);
            } catch (Exception e) {
                Mensajes.error("No se encuentra instalada la impresora predeterminada para este punto de impresión");
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Blanco = new org.edisoncor.gui.panel.PanelImage();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblFactura = new javax.swing.JTable()
        {
            public boolean isCellEditable(int rowInddex, int celIndex)
            {
                return false;
            }
        };
        jPanel2 = new javax.swing.JPanel();
        L4 = new javax.swing.JLabel();
        txtVendedor = new javax.swing.JTextField();
        L1 = new javax.swing.JLabel();
        txtCodCliente = new javax.swing.JTextField();
        L3 = new javax.swing.JLabel();
        txtRazonSocial = new javax.swing.JTextField();
        L2 = new javax.swing.JLabel();
        txtRuc = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDetalle = new javax.swing.JTable()
        {
            public boolean isCellEditable(int rowInddex, int celIndex)
            {
                return false;
            }
        };
        L5 = new javax.swing.JLabel();
        txtEfectivo = new javax.swing.JTextField();
        txtTransferencia = new javax.swing.JTextField();
        txtTransfBoleta = new javax.swing.JTextField();
        L8 = new javax.swing.JLabel();
        txtVuelto = new javax.swing.JTextField();
        divisor = new javax.swing.JSeparator();
        L6 = new javax.swing.JLabel();
        L7 = new javax.swing.JLabel();
        txtQR = new javax.swing.JTextField();
        txtQRBoleta = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        PanelContenedor1 = new rojeru_san.rspanel.RSPanelImage();
        btnActualizar = new RSMaterialComponent.RSButtonIconUno();
        Separador1 = new javax.swing.JSeparator();
        LabelTitulo1 = new javax.swing.JLabel();
        PanelContenedor2 = new rojeru_san.rspanel.RSPanelImage();
        btnImprimir = new RSMaterialComponent.RSButtonIconUno();
        Separador2 = new javax.swing.JSeparator();
        LabelTitulo2 = new javax.swing.JLabel();
        PanelContenedor = new rojeru_san.rspanel.RSPanelImage();
        btnAnular = new RSMaterialComponent.RSButtonIconUno();
        Separador = new javax.swing.JSeparator();
        LabelTitulo = new javax.swing.JLabel();
        dcDesde = new datechooser.beans.DateChooserCombo();
        txtDesde = new javax.swing.JTextField();
        txt10 = new javax.swing.JTextField();
        txtE = new javax.swing.JTextField();
        txt5 = new javax.swing.JTextField();
        txtV = new javax.swing.JTextField();
        lbAnulados = new javax.swing.JLabel();
        lbTotal = new javax.swing.JLabel();
        btnSalir = new RSMaterialComponent.RSButtonIconOne();
        cbVendedores = new RSMaterialComponent.RSComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        Blanco.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        Blanco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/fondoBlanco.jpg"))); // NOI18N
        Blanco.setPreferredSize(new java.awt.Dimension(690, 418));
        Blanco.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jScrollPane1.setOpaque(false);

        tblFactura.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        tblFactura.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        tblFactura.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblFactura.setGridColor(new java.awt.Color(204, 204, 204));
        tblFactura.setSelectionBackground(new java.awt.Color(0, 102, 102));
        tblFactura.setShowGrid(true);
        tblFactura.setShowVerticalLines(false);
        tblFactura.getTableHeader().setResizingAllowed(false);
        tblFactura.getTableHeader().setReorderingAllowed(false);
        tblFactura.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblFacturaMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblFacturaMouseReleased(evt);
            }
        });
        tblFactura.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tblFacturaPropertyChange(evt);
            }
        });
        jScrollPane1.setViewportView(tblFactura);

        Blanco.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 106, 1066, 250));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        L4.setFont(new java.awt.Font("Roboto", 1, 10)); // NOI18N
        L4.setText("VENDEDOR:");
        jPanel2.add(L4, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 3, -1, 16));

        txtVendedor.setEditable(false);
        txtVendedor.setBackground(new java.awt.Color(255, 255, 255));
        txtVendedor.setFont(new java.awt.Font("Roboto", 0, 10)); // NOI18N
        txtVendedor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        txtVendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtVendedorActionPerformed(evt);
            }
        });
        jPanel2.add(txtVendedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(697, 3, 360, 18));

        L1.setFont(new java.awt.Font("Roboto", 1, 10)); // NOI18N
        L1.setText("OP:");
        jPanel2.add(L1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 3, -1, 16));

        txtCodCliente.setEditable(false);
        txtCodCliente.setBackground(new java.awt.Color(255, 255, 255));
        txtCodCliente.setFont(new java.awt.Font("Roboto", 0, 10)); // NOI18N
        txtCodCliente.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        txtCodCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodClienteActionPerformed(evt);
            }
        });
        jPanel2.add(txtCodCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 3, 48, 18));

        L3.setFont(new java.awt.Font("Roboto", 1, 10)); // NOI18N
        L3.setText("RAZÓN SOCIAL:");
        jPanel2.add(L3, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 3, -1, 16));

        txtRazonSocial.setEditable(false);
        txtRazonSocial.setBackground(new java.awt.Color(255, 255, 255));
        txtRazonSocial.setFont(new java.awt.Font("Roboto", 0, 10)); // NOI18N
        txtRazonSocial.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        txtRazonSocial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRazonSocialActionPerformed(evt);
            }
        });
        jPanel2.add(txtRazonSocial, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 3, 290, 18));

        L2.setFont(new java.awt.Font("Roboto", 1, 10)); // NOI18N
        L2.setText("R.U.C.: ");
        jPanel2.add(L2, new org.netbeans.lib.awtextra.AbsoluteConstraints(82, 3, -1, 16));

        txtRuc.setEditable(false);
        txtRuc.setBackground(new java.awt.Color(255, 255, 255));
        txtRuc.setFont(new java.awt.Font("Roboto", 0, 10)); // NOI18N
        txtRuc.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        txtRuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRucActionPerformed(evt);
            }
        });
        jPanel2.add(txtRuc, new org.netbeans.lib.awtextra.AbsoluteConstraints(122, 3, 114, 18));

        jScrollPane2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        tblDetalle.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        tblDetalle.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        tblDetalle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblDetalle.setGridColor(new java.awt.Color(204, 204, 204));
        tblDetalle.setSelectionBackground(new java.awt.Color(0, 102, 102));
        tblDetalle.setShowGrid(true);
        tblDetalle.setShowVerticalLines(false);
        tblDetalle.getTableHeader().setResizingAllowed(false);
        tblDetalle.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tblDetalle);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 67, 1067, 244));

        L5.setFont(new java.awt.Font("Roboto", 1, 10)); // NOI18N
        L5.setText("EFECTIVO:");
        jPanel2.add(L5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 23, -1, 17));

        txtEfectivo.setEditable(false);
        txtEfectivo.setBackground(new java.awt.Color(255, 255, 255));
        txtEfectivo.setFont(new java.awt.Font("Roboto", 0, 10)); // NOI18N
        txtEfectivo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtEfectivo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        txtEfectivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEfectivoActionPerformed(evt);
            }
        });
        jPanel2.add(txtEfectivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 23, 120, 18));

        txtTransferencia.setEditable(false);
        txtTransferencia.setBackground(new java.awt.Color(255, 255, 255));
        txtTransferencia.setFont(new java.awt.Font("Roboto", 0, 10)); // NOI18N
        txtTransferencia.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTransferencia.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        txtTransferencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTransferenciaActionPerformed(evt);
            }
        });
        jPanel2.add(txtTransferencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 23, 120, 18));

        txtTransfBoleta.setEditable(false);
        txtTransfBoleta.setBackground(new java.awt.Color(255, 255, 255));
        txtTransfBoleta.setFont(new java.awt.Font("Roboto", 0, 10)); // NOI18N
        txtTransfBoleta.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTransfBoleta.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        txtTransfBoleta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTransfBoletaActionPerformed(evt);
            }
        });
        jPanel2.add(txtTransfBoleta, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 45, 120, 18));

        L8.setFont(new java.awt.Font("Roboto", 1, 10)); // NOI18N
        L8.setText("VUELTO:");
        jPanel2.add(L8, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 23, -1, 17));

        txtVuelto.setEditable(false);
        txtVuelto.setBackground(new java.awt.Color(255, 255, 255));
        txtVuelto.setFont(new java.awt.Font("Roboto", 0, 10)); // NOI18N
        txtVuelto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtVuelto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        txtVuelto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtVueltoActionPerformed(evt);
            }
        });
        jPanel2.add(txtVuelto, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 23, 120, 18));

        divisor.setForeground(new java.awt.Color(204, 204, 204));
        jPanel2.add(divisor, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 22, 1056, 1));

        L6.setFont(new java.awt.Font("Roboto", 1, 10)); // NOI18N
        L6.setText("TRANSF. BANC:");
        jPanel2.add(L6, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 23, -1, 17));

        L7.setFont(new java.awt.Font("Roboto", 1, 10)); // NOI18N
        L7.setText("QR");
        jPanel2.add(L7, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 23, -1, 18));

        txtQR.setEditable(false);
        txtQR.setBackground(new java.awt.Color(255, 255, 255));
        txtQR.setFont(new java.awt.Font("Roboto", 0, 10)); // NOI18N
        txtQR.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtQR.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        txtQR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQRActionPerformed(evt);
            }
        });
        jPanel2.add(txtQR, new org.netbeans.lib.awtextra.AbsoluteConstraints(435, 23, 120, 18));

        txtQRBoleta.setEditable(false);
        txtQRBoleta.setBackground(new java.awt.Color(255, 255, 255));
        txtQRBoleta.setFont(new java.awt.Font("Roboto", 0, 10)); // NOI18N
        txtQRBoleta.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtQRBoleta.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        txtQRBoleta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQRBoletaActionPerformed(evt);
            }
        });
        jPanel2.add(txtQRBoleta, new org.netbeans.lib.awtextra.AbsoluteConstraints(435, 45, 120, 18));

        Blanco.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 359, 1066, 311));

        jPanel3.setBackground(new java.awt.Color(0, 102, 102));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setOpaque(false);
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PanelContenedor1.setImagen(new javax.swing.ImageIcon(getClass().getResource("/Recursos/CONTENEDOR2.png"))); // NOI18N

        btnActualizar.setBackground(new java.awt.Color(255, 255, 255));
        btnActualizar.setBackgroundHover(new java.awt.Color(0, 153, 153));
        btnActualizar.setForegroundText(new java.awt.Color(0, 153, 153));
        btnActualizar.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.REFRESH);
        btnActualizar.setRippleColor(java.awt.Color.white);
        btnActualizar.setTypeBorder(RSMaterialComponent.RSButtonIconUno.TYPEBORDER.CIRCLE);
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        Separador1.setForeground(new java.awt.Color(204, 204, 204));

        LabelTitulo1.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        LabelTitulo1.setForeground(new java.awt.Color(0, 102, 102));
        LabelTitulo1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LabelTitulo1.setText("ACTUALIZAR");

        javax.swing.GroupLayout PanelContenedor1Layout = new javax.swing.GroupLayout(PanelContenedor1);
        PanelContenedor1.setLayout(PanelContenedor1Layout);
        PanelContenedor1Layout.setHorizontalGroup(
            PanelContenedor1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelContenedor1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelContenedor1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Separador1)
                    .addComponent(LabelTitulo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(PanelContenedor1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );
        PanelContenedor1Layout.setVerticalGroup(
            PanelContenedor1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelContenedor1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(Separador1, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(LabelTitulo1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.add(PanelContenedor1, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 3, 100, -1));

        PanelContenedor2.setImagen(new javax.swing.ImageIcon(getClass().getResource("/Recursos/CONTENEDOR2.png"))); // NOI18N

        btnImprimir.setBackground(new java.awt.Color(255, 255, 255));
        btnImprimir.setBackgroundHover(new java.awt.Color(204, 102, 0));
        btnImprimir.setForegroundText(new java.awt.Color(204, 102, 0));
        btnImprimir.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.PRINT);
        btnImprimir.setRippleColor(java.awt.Color.white);
        btnImprimir.setTypeBorder(RSMaterialComponent.RSButtonIconUno.TYPEBORDER.CIRCLE);
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        Separador2.setForeground(new java.awt.Color(204, 204, 204));

        LabelTitulo2.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        LabelTitulo2.setForeground(new java.awt.Color(0, 102, 102));
        LabelTitulo2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LabelTitulo2.setText("RE-IMPRIMIR");

        javax.swing.GroupLayout PanelContenedor2Layout = new javax.swing.GroupLayout(PanelContenedor2);
        PanelContenedor2.setLayout(PanelContenedor2Layout);
        PanelContenedor2Layout.setHorizontalGroup(
            PanelContenedor2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelContenedor2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelContenedor2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Separador2)
                    .addComponent(LabelTitulo2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelContenedor2Layout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );
        PanelContenedor2Layout.setVerticalGroup(
            PanelContenedor2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelContenedor2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(Separador2, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(LabelTitulo2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.add(PanelContenedor2, new org.netbeans.lib.awtextra.AbsoluteConstraints(102, 3, 100, -1));

        PanelContenedor.setImagen(new javax.swing.ImageIcon(getClass().getResource("/Recursos/CONTENEDOR2.png"))); // NOI18N

        btnAnular.setBackground(new java.awt.Color(255, 255, 255));
        btnAnular.setBackgroundHover(new java.awt.Color(51, 51, 51));
        btnAnular.setForegroundText(new java.awt.Color(51, 51, 51));
        btnAnular.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.DELETE);
        btnAnular.setRippleColor(java.awt.Color.white);
        btnAnular.setTypeBorder(RSMaterialComponent.RSButtonIconUno.TYPEBORDER.CIRCLE);
        btnAnular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnularActionPerformed(evt);
            }
        });

        Separador.setForeground(new java.awt.Color(204, 204, 204));

        LabelTitulo.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        LabelTitulo.setForeground(new java.awt.Color(0, 102, 102));
        LabelTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LabelTitulo.setText("ANULAR");

        javax.swing.GroupLayout PanelContenedorLayout = new javax.swing.GroupLayout(PanelContenedor);
        PanelContenedor.setLayout(PanelContenedorLayout);
        PanelContenedorLayout.setHorizontalGroup(
            PanelContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelContenedorLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(btnAnular, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
            .addGroup(PanelContenedorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LabelTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Separador))
                .addContainerGap())
        );
        PanelContenedorLayout.setVerticalGroup(
            PanelContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelContenedorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAnular, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(Separador, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(LabelTitulo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.add(PanelContenedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(199, 3, 100, -1));

        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 310, 100));

        dcDesde.setCurrentView(new datechooser.view.appearance.AppearancesList("Swing",
            new datechooser.view.appearance.ViewAppearance("custom",
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(0, 0, 255),
                    true,
                    true,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 255),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(128, 128, 128),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.LabelPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.LabelPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(255, 0, 0),
                    false,
                    false,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                (datechooser.view.BackRenderer)null,
                false,
                true)));
    dcDesde.setCalendarBackground(new java.awt.Color(255, 255, 255));
    dcDesde.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102), 1));
    dcDesde.setFieldFont(new java.awt.Font("Roboto", java.awt.Font.BOLD, 15));
    dcDesde.addCommitListener(new datechooser.events.CommitListener() {
        public void onCommit(datechooser.events.CommitEvent evt) {
            dcDesdeOnCommit(evt);
        }
    });
    jPanel3.add(dcDesde, new org.netbeans.lib.awtextra.AbsoluteConstraints(888, 50, 162, 27));
    jPanel3.add(txtDesde, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 10, 120, -1));
    jPanel3.add(txt10, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 10, 90, -1));
    jPanel3.add(txtE, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 10, 90, -1));
    jPanel3.add(txt5, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 10, 90, -1));
    jPanel3.add(txtV, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 10, 82, 21));

    lbAnulados.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
    lbAnulados.setForeground(new java.awt.Color(255, 255, 255));
    lbAnulados.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jPanel3.add(lbAnulados, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 80, 160, 23));

    lbTotal.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
    lbTotal.setForeground(new java.awt.Color(255, 255, 255));
    lbTotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jPanel3.add(lbTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(715, 80, 170, 23));

    btnSalir.setBackground(new java.awt.Color(0, 102, 102));
    btnSalir.setToolTipText("ALT+F4");
    btnSalir.setBackgroundHover(new java.awt.Color(255, 0, 0));
    btnSalir.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.CLOSE);
    btnSalir.setOpaque(true);
    btnSalir.setPreferredSize(new java.awt.Dimension(20, 20));
    btnSalir.setRequestFocusEnabled(false);
    btnSalir.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnSalirActionPerformed(evt);
        }
    });
    btnSalir.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyPressed(java.awt.event.KeyEvent evt) {
            btnSalirKeyPressed(evt);
        }
    });
    jPanel3.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(1046, 0, -1, -1));

    cbVendedores.setForeground(new java.awt.Color(0, 0, 0));
    cbVendedores.setColorArrow(new java.awt.Color(255, 255, 255));
    cbVendedores.setColorBorde(new java.awt.Color(204, 204, 204));
    cbVendedores.setColorBoton(new java.awt.Color(153, 153, 153));
    cbVendedores.setColorFondo(new java.awt.Color(255, 255, 255));
    cbVendedores.setColorSeleccion(new java.awt.Color(0, 102, 102));
    cbVendedores.setConBorde(true);
    cbVendedores.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
    cbVendedores.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            cbVendedoresActionPerformed(evt);
        }
    });
    cbVendedores.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyPressed(java.awt.event.KeyEvent evt) {
            cbVendedoresKeyPressed(evt);
        }
    });
    jPanel3.add(cbVendedores, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 50, 267, 25));

    Blanco.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 1, 1066, 103));

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(Blanco, javax.swing.GroupLayout.DEFAULT_SIZE, 1067, Short.MAX_VALUE)
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(Blanco, javax.swing.GroupLayout.PREFERRED_SIZE, 672, javax.swing.GroupLayout.PREFERRED_SIZE)
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents
    public void llamarReporteFactura() throws SQLException {
        Reporte gr;
        gr = new Reporte();
    }
    private void tblFacturaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tblFacturaPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_tblFacturaPropertyChange

    private void tblFacturaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblFacturaMousePressed
        // TODO add your handling code here:
        try {
            CabecerasTablas.limpiarTablas(tblDetalle);
            controlFactura.listDetalle(tblDetalle);
            controlFactura.ListClientes();
            controlFactura.selecVendedor();
            controlFactura.selecDatosFactura();
            txtE.setText(String.valueOf(getExcetas()));
            txt5.setText(String.valueOf(getCinco()));
            txt10.setText(String.valueOf(getDiez()));
            visible();
            RendersD();
        } catch (Exception e) {
            //Mensajes.error(e.getMessage());
        }
    }//GEN-LAST:event_tblFacturaMousePressed

    private void dcDesdeOnCommit(datechooser.events.CommitEvent evt) {//GEN-FIRST:event_dcDesdeOnCommit
        // TODO add your handling code here:
        txtDesde.setText(Fecha.formatoFecha(dcDesde.getText()));
        btnActualizarActionPerformed(null);
    }//GEN-LAST:event_dcDesdeOnCommit

    private void txtVendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtVendedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVendedorActionPerformed

    private void txtCodClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodClienteActionPerformed

    private void txtRazonSocialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRazonSocialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRazonSocialActionPerformed

    private void txtRucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRucActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRucActionPerformed

    private void tblFacturaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblFacturaMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tblFacturaMouseReleased

    private void txtEfectivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEfectivoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEfectivoActionPerformed

    private void txtTransferenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTransferenciaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTransferenciaActionPerformed

    private void txtTransfBoletaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTransfBoletaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTransfBoletaActionPerformed

    private void txtVueltoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtVueltoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVueltoActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        // TODO add your handling code here:
        try {
            CabecerasTablas.limpiarTablas(tblFactura);
            CabecerasTablas.limpiarTablas(tblDetalle);
            cabe.consFacturas(tblFactura);
            cabe.detalleFactura(tblDetalle);
            CargarDatos();
            Renders();
            limpiarCampos();
            invisible();
            CalcularValores();
            Notif.NotifyInformation("Notificación del sistema", "Tabla de ventas actualizada!");
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        // TODO add your handling code here:
        if (dlgConsultarFacturas.tblFactura.getSelectedRow() < 0) {
            Notif.NotifyFail("Notificación del sistema", "No es posible procesar la petición.\r\nSeleccione en la tabla la venta que desea re-imprimir");
        } else {
            int x = dlgConsultarFacturas.tblFactura.getSelectedRow();
            int Cod = Integer.parseInt(dlgConsultarFacturas.tblFactura.getValueAt(x, 0).toString());
            int idEmision = Integer.parseInt(dlgConsultarFacturas.tblFactura.getValueAt(x, 1).toString());
            String Timbrado = (dlgConsultarFacturas.tblFactura.getValueAt(x, 17).toString());
            String Desde = (dlgConsultarFacturas.tblFactura.getValueAt(x, 18).toString());
            String Hasta = (dlgConsultarFacturas.tblFactura.getValueAt(x, 19).toString());
            String Condicion = (dlgConsultarFacturas.tblFactura.getValueAt(x, 9).toString());
            String Numeracion = (dlgConsultarFacturas.tblFactura.getValueAt(x, 8).toString());
            String Fecha = (dlgConsultarFacturas.tblFactura.getValueAt(x, 3).toString());
            String Hora = (dlgConsultarFacturas.tblFactura.getValueAt(x, 4).toString());
            String Vendedor = txtVendedor.getText().trim();
            String RazonSocial = txtRazonSocial.getText().trim();
            String RUC = txtRuc.getText().trim();
            String tipo = dlgConsultarFacturas.tblFactura.getValueAt(x, 7).toString();
            String Total = dlgConsultarFacturas.tblFactura.getValueAt(x, 11).toString().replace(".", "").replace(",", "");
            String estado;

            if (dlgConsultarFacturas.tblFactura.getValueAt(x, 13).toString().equals("ANULADO")) {
                estado = "  ** ANULADO **";
            } else {
                estado = "";
            }
            String EXENTA = txtE.getText();
            String CINCO = txt5.getText();
            String DIEZ = txt10.getText();
            int iv5 = Integer.parseInt(dlgConsultarFacturas.tblFactura.getValueAt(x, 15).toString());
            int iv10 = Integer.parseInt(dlgConsultarFacturas.tblFactura.getValueAt(x, 16).toString());
            int efectivo = Integer.parseInt(dlgConsultarFacturas.tblFactura.getValueAt(x, 20).toString());
            int tranf = Integer.parseInt(dlgConsultarFacturas.tblFactura.getValueAt(x, 21).toString());
            int qr = Integer.parseInt(dlgConsultarFacturas.tblFactura.getValueAt(x, 23).toString());
            int abonado = efectivo+tranf+qr;
            int vuelto = Integer.parseInt(dlgConsultarFacturas.tblFactura.getValueAt(x, 25).toString());

            int rpta = Mensajes.confirmar("¿Seguro que desea Re-Imprimir esta Venta?");
            if (rpta == 0) {
                if (tipo.equals("FACTURA LEGAL")) {
                    try {
                        imprimirFacturaOriginal(estado, idEmision, Integer.parseInt(Total), Timbrado, Desde, Hasta, Condicion, Numeracion, Fecha, Hora, Vendedor, RazonSocial, RUC, EXENTA, CINCO, DIEZ, iv5, iv10, efectivo, tranf, qr, abonado, vuelto);
                        // imprimirFacturaDuplicado(estado, idEmision, Integer.parseInt(Total), Timbrado, Desde, Hasta, Condicion, Numeracion, Fecha, Hora, Vendedor, RazonSocial, RUC, EXENTA, CINCO, DIEZ, iv5, iv10);
                    } catch (NumberFormatException e) {
                    }
                } else if (tipo.equals("TICKET")) {
                    imprimirTicket(idEmision, Integer.parseInt(Total), Condicion, Numeracion, Fecha, Hora, Vendedor, RazonSocial, RUC, efectivo, tranf, qr, abonado, vuelto);
                }
            }
        }
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void btnAnularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnularActionPerformed
        // TODO add your handling code here:
        if (dlgConsultarFacturas.tblFactura.getSelectedRow() < 0) {
            //Mensajes.error("Seleccione una fila de la tabla");
            Notif.NotifyFail("Notificación del sistema", "No es posible procesar la petición.\r\nSeleccione en la tabla la venta que desea anular.");
        } else {
            int x = dlgConsultarFacturas.tblFactura.getSelectedRow();
            String estado = dlgConsultarFacturas.tblFactura.getValueAt(x, 13).toString();
            if (estado.equals("ANULADO")) {
                Notif.NotifyFail("Notificación del sistema", "No se posible Anular esta Venta.\nMotivo: Esta venta ya fue anulada");
                //Mensajes.informacion("No se posible Anular esta Venta.\nMotivo: Esta venta ya fue anulada");
            } else {
                String msg;
                int rpta = Mensajes.confirmar("¿Seguro que desea Anular esta Venta?");
                if (rpta == 0) {
                    try {
                        msg = controlFactura.anularFactura();
                        if (msg == null) {
                            btnActualizar.doClick();
                        }
                    } catch (Exception e) {
                    }
                }
            }
        }
    }//GEN-LAST:event_btnAnularActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        int rpta = Mensajes.confirmar("¿Seguro que desea salir del formulario?");
        if (rpta == 0) {
            this.dispose();
        }
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnSalirKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnSalirKeyPressed
        // TODO add your handling code here:
        AccesoRapido(evt.getKeyCode());
    }//GEN-LAST:event_btnSalirKeyPressed

    private void cbVendedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbVendedoresActionPerformed
        // TODO add your handling code here:
        if (cbVendedores.getSelectedIndex() == 0) {
            txtV.setText("");
            CabecerasTablas.limpiarTablas(tblFactura);
            CabecerasTablas.limpiarTablas(tblDetalle);
            cabe.consFacturas(tblFactura);
            cabe.detalleFactura(tblDetalle);
            controlFactura.listFacturas(tblFactura, txtDesde.getText().trim());
            Renders();
            limpiarCampos();
            invisible();
            CalcularValores();
        } else {
            cbVendedores.setPopupVisible(true);
            String id = cargarComboBox.getCodidgo(cbVendedores);
            txtV.setText(String.valueOf(id));
            CabecerasTablas.limpiarTablas(tblFactura);
            CabecerasTablas.limpiarTablas(tblDetalle);
            cabe.consFacturas(tblFactura);
            cabe.detalleFactura(tblDetalle);
            int idV = Integer.parseInt(txtV.getText().trim());
            controlFactura.listFacturas1(tblFactura, idV, txtDesde.getText().trim());
            Renders();
            limpiarCampos();
            invisible();
            CalcularValores();
        }
    }//GEN-LAST:event_cbVendedoresActionPerformed

    private void cbVendedoresKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbVendedoresKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbVendedoresKeyPressed

    private void txtQRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQRActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQRActionPerformed

    private void txtQRBoletaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQRBoletaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQRBoletaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(dlgConsultarFacturas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(dlgConsultarFacturas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(dlgConsultarFacturas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(dlgConsultarFacturas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(() -> {
            try {
                dlgConsultarFacturas dialog = new dlgConsultarFacturas(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(dlgConsultarFacturas.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.edisoncor.gui.panel.PanelImage Blanco;
    private javax.swing.JLabel L1;
    private javax.swing.JLabel L2;
    private javax.swing.JLabel L3;
    private javax.swing.JLabel L4;
    private javax.swing.JLabel L5;
    private javax.swing.JLabel L6;
    private javax.swing.JLabel L7;
    private javax.swing.JLabel L8;
    private javax.swing.JLabel LabelTitulo;
    private javax.swing.JLabel LabelTitulo1;
    private javax.swing.JLabel LabelTitulo2;
    private rojeru_san.rspanel.RSPanelImage PanelContenedor;
    private rojeru_san.rspanel.RSPanelImage PanelContenedor1;
    private rojeru_san.rspanel.RSPanelImage PanelContenedor2;
    private javax.swing.JSeparator Separador;
    private javax.swing.JSeparator Separador1;
    private javax.swing.JSeparator Separador2;
    private RSMaterialComponent.RSButtonIconUno btnActualizar;
    private RSMaterialComponent.RSButtonIconUno btnAnular;
    private RSMaterialComponent.RSButtonIconUno btnImprimir;
    private RSMaterialComponent.RSButtonIconOne btnSalir;
    public static RSMaterialComponent.RSComboBox cbVendedores;
    private datechooser.beans.DateChooserCombo dcDesde;
    private javax.swing.JSeparator divisor;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbAnulados;
    private javax.swing.JLabel lbTotal;
    public static javax.swing.JTable tblDetalle;
    public static javax.swing.JTable tblFactura;
    private static javax.swing.JTextField txt10;
    private static javax.swing.JTextField txt5;
    public static javax.swing.JTextField txtCodCliente;
    private javax.swing.JTextField txtDesde;
    public static javax.swing.JTextField txtE;
    public static javax.swing.JTextField txtEfectivo;
    public static javax.swing.JTextField txtQR;
    public static javax.swing.JTextField txtQRBoleta;
    public static javax.swing.JTextField txtRazonSocial;
    public static javax.swing.JTextField txtRuc;
    public static javax.swing.JTextField txtTransfBoleta;
    public static javax.swing.JTextField txtTransferencia;
    private static javax.swing.JTextField txtV;
    public static javax.swing.JTextField txtVendedor;
    public static javax.swing.JTextField txtVuelto;
    // End of variables declaration//GEN-END:variables
}
