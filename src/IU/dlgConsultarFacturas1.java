package IU;

import Componentes.ConexionBD;
import Componentes.Fecha;
import Componentes.Reporte;
import Componentes.Mensajes;
import Componentes.Numero_a_Letra;
import Componentes.PrinterService;
import Componentes.Redondeo;
import Componentes.RenderDecimal1;
import Componentes.RenderDecimal2;
import Componentes.RenderDecimalconPuntos;
import Componentes.Software;
import Componentes.cargarComboBox;
import Controladores.CabecerasTablas;
import Controladores.controlFactura;
import javax.swing.JOptionPane;
import org.mariadb.jdbc.MariaDbConnection;
import org.mariadb.jdbc.MariaDbStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import javax.swing.table.DefaultTableModel;

public class dlgConsultarFacturas1 extends javax.swing.JDialog {

    CabecerasTablas cabe = new CabecerasTablas();
    public static MariaDbConnection con;
    public static MariaDbStatement st;
    //public static ResultSet rss;
    public static MariaDbConnection conM;
    public static MariaDbStatement stM;

    public static String UsuarioL = "";
    public Reporte jasper;
    static String emp;
    static String dir;
    static String cel;

    static public Numero_a_Letra d;

    public dlgConsultarFacturas1(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        txtDesde.setVisible(false);
        titulo();
        //prepararBD();
        jasper = new Reporte();
        cabe.consFacturas(tblFactura);
        cabe.detalleFactura(tblDetalle);
        txtDesde.setText(Fecha.formatoFecha(dcDesde.getText()));
        //controlFactura.listFacturas(tblFactura, txtDesde.getText().trim());
        Renders();
        d = new Numero_a_Letra();
        invisible();
        //txtE.setVisible(false);
        //txt5.setVisible(false);
        //txt10.setVisible(false);
        //txtV.setVisible(false);
        CalcularValores();

    }
    
    private void CalcularValores(){
        String facturado = String.valueOf(controlFactura.getTotalFacturado());
        String anulado = String.valueOf(controlFactura.getTotalFacturadoAnulado());
        DecimalFormat df = new DecimalFormat("#,###");
        lbTotal.setText("FACTURADO: "+df.format(Integer.valueOf(facturado.replace(".", "").replace(",", ""))));
        lbAnulados.setText("ANULADO: "+df.format(Integer.valueOf(anulado.replace(".", "").replace(",", ""))));
    }

    private void visible() {
        L1.setVisible(true);
        L2.setVisible(true);
        L3.setVisible(true);
        L4.setVisible(true);
    }

    private void invisible() {
        L1.setVisible(false);
        L2.setVisible(false);
        L3.setVisible(false);
        L4.setVisible(false);
    }

    final void titulo() {
        if (Software.getSoftware().equals("null")) {
            this.setTitle("Gestión de ventas realizadas");
        } else {
            this.setTitle(Software.getSoftware() + " - Gestión de ventas realizadas");
        }
    }

    public static void Renders() {
        dlgConsultarFacturas1.tblFactura.getColumnModel().getColumn(11).setCellRenderer(new RenderDecimal2());
    }

    public static void RendersD() {
        dlgConsultarFacturas1.tblDetalle.getColumnModel().getColumn(0).setCellRenderer(new RenderDecimalconPuntos());
        dlgConsultarFacturas1.tblDetalle.getColumnModel().getColumn(6).setCellRenderer(new RenderDecimal1());
        dlgConsultarFacturas1.tblDetalle.getColumnModel().getColumn(7).setCellRenderer(new RenderDecimal2());
    }

    public static void prepararBD() {
        try {
            con = (MariaDbConnection) new ConexionBD().getConexion();
            if (con == null) {
                System.out.println("No hay Conexion con la Base de Datos");
            } else {
                st = (MariaDbStatement) con.createStatement();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        try {
            conM = (MariaDbConnection) new ConexionBD().getConexionMovil();
            if (conM == null) {
                System.out.println("No hay Conexion con la Base de Datos");
            } else {
                stM = (MariaDbStatement) conM.createStatement();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
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

    public static void llamarReporteHoja3(int cod, String Letra) {
        Reporte gr;
        gr = new Reporte();
        //int codF = Integer.parseInt(txtCodFactura.getText());
        gr.FacturaLegal("\\Reports\\ventas\\Hoja3.jasper", "ID", cod, "LETRA", Letra);
        gr.cerrar();
    }

    public static void limpiarCampos() {
        txtCodCliente.setText("");
        txtRuc.setText("");
        txtRazonSocial.setText("");
        txtVendedor.setText("");
        txtHasta.setText("");
        txtE.setText("");
        txt5.setText("");
        txt10.setText("");
    }

    public static void imprimirTicket(int idEmision, int total, String Condicion, String Numeracion, String Fecha, String Hora, String Vendedor, String RazonSocial, String RUC) {
        //Impresora matricial tmu-220
        String ImpresoraPred = null;
        try {
            prepararBD();
            ResultSet res = stM.executeQuery("SELECT * FROM v_puntoemision4 WHERE idemision=" + idEmision);
            res.last();
            do {
                ImpresoraPred = res.getString("impresora").trim();
                System.out.println(ImpresoraPred);

            } while (res.next());
            res.close();
            st.close();
            stM.close();
            con.close();
            conM.close();

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
            String tot = formateador.format(total);
            try {
                prepararBD();
                ResultSet rs = stM.executeQuery("SELECT * FROM empresa WHERE estado='S'");
                rs.last();
                empresa = rs.getString("razon_social");
                ruc = rs.getString("ruc");
                celular = rs.getString("telefono")/* + "-" + rs.getString("em_celular")*/;
                direccion = rs.getString("direccion");
                rs.close();
                st.close();
                stM.close();
                con.close();
                conM.close();
            } catch (SQLException ex) {
                Mensajes.error("Error obteniendo datos de la empresa para la impresion de factura.");
            }
            String Ticket = "         " + empresa + "\n";
            Ticket += "           VENTAS DE LACTEOS LACTOLANDA\n";
            Ticket += "                 RUC: " + ruc + "\n";
            Ticket += "               CEL: " + celular + "\n";
            Ticket += direccion + "\n";
            Ticket += "     CNEL. OVIEDO - DPTO. DE CAAGUAZU - PY\n";
            Ticket += "-----------------------------------------------\n";
            Ticket += "TICKET " + Condicion + " NRO: " + Numeracion + "\n";
            Ticket += "FECHA/HORA: " + Fecha + " " + Hora + "\n";
            Ticket += "VENDEDOR: " + Vendedor + "\n";
            Ticket += "\n";
            Ticket += "CLIENTE: " + RazonSocial + "\n";
            Ticket += "RUC/CI: " + RUC + "\n";
            Ticket += "-----------------------------------------------\n";
            Ticket += String.format("%1$1s %2$10s %3$1s %4$12s %5$16s", "IVA", "CANT", "", "PRECIO", "   SUBTOTAL");
            Ticket += "\n";
            Ticket += "-----------------------------------------------\n";
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
            Ticket += "===============================================\n";
            Ticket += String.format("%1$5s %2$20s", "TOTAL A PAGAR Gs:", tot) + "\n";
            Ticket += "===============================================\n";
            Ticket += "\n";
            Ticket += "EFECTIVO:  0\n";
            Ticket += "VUELTO:    0\n";
            Ticket += "\n";
            Ticket += "         " + empresa + "\n";
            Ticket += "             AGRADECE SU PREFERENCIA\n";
            Ticket += "\n";
            Ticket += "\n";
            Ticket += "\n";
            Ticket += "\n";

            //printerService.printString(Ticket);
            //print some stuff
            // cut that paper!
            //byte[] cutP = new byte[]{0x1d, 'V', 1};
            //final byte[] openCD={27,112,0,60,120};
            // printerService.printBytes(openCD);
            //printerService.printBytes(cutP);
            try {
                printerService.printString2(ImpresoraPred, Ticket);
                byte[] cutP = new byte[]{0x1d, 'V', 1};
                printerService.printBytes2(ImpresoraPred, cutP);
            } catch (Exception e) {
                Mensajes.error("No se encuentra instalada la impresora predeterminada para este punto de impresión");
            }
        }

    }

    public static void imprimirFacturaOriginal(String estado, int idEmision, int Total, String Timbrado, String Desde, String Hasta, String Condicion, String NroFactura,
            String Fecha, String Hora, String Vendedor, String Cliente, String Ruc, String Exenta, String Cinco, String Diez, int iv5, int iv10) {
        //Impresora matricial tmu-220
        String ImpresoraPred = null;
        try {
            prepararBD();
            ResultSet res = stM.executeQuery("SELECT * FROM v_puntoemision4 WHERE idemision=" + idEmision);
            res.last();
            do {
                ImpresoraPred = res.getString("impresora").trim();
                System.out.println(ImpresoraPred);

            } while (res.next());
            res.close();
            st.close();
            stM.close();
            con.close();
            conM.close();

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
            try {
                prepararBD();
                ResultSet res = stM.executeQuery("SELECT * FROM empresa WHERE estado='S'");
                res.last();
                empresa = res.getString("razon_social");
                ruc = res.getString("ruc");
                celular = res.getString("telefono")/* + "-" + rs.getString("em_celular")*/;
                direccion = res.getString("direccion");
                res.close();
                st.close();
                stM.close();
                con.close();
                conM.close();
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
            Ticket += "ORIGINAL:  CLIENTE\n";
            // Ticket += "DUPLICADO: Archivo Tributario\n";
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
    
    public static void imprimirFacturaDuplicado(String estado, int idEmision, int Total, String Timbrado, String Desde, String Hasta, String Condicion, String NroFactura,
            String Fecha, String Hora, String Vendedor, String Cliente, String Ruc, String Exenta, String Cinco, String Diez, int iv5, int iv10) {
        //Impresora matricial tmu-220
        String ImpresoraPred = null;
        try {
            prepararBD();
            ResultSet res = stM.executeQuery("SELECT * FROM v_puntoemision4 WHERE idemision=" + idEmision);
            res.last();
            do {
                ImpresoraPred = res.getString("impresora").trim();
                System.out.println(ImpresoraPred);

            } while (res.next());
            res.close();
            st.close();
            stM.close();
            con.close();
            conM.close();

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
            try {
                prepararBD();
                ResultSet res = stM.executeQuery("SELECT * FROM empresa WHERE estado='S'");
                res.last();
                empresa = res.getString("razon_social");
                ruc = res.getString("ruc");
                celular = res.getString("telefono")/* + "-" + rs.getString("em_celular")*/;
                direccion = res.getString("direccion");
                res.close();
                st.close();
                stM.close();
                con.close();
                conM.close();
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
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        btnImprimir = new newscomponents.RSButtonBigIcon_new();
        btnSalir = new newscomponents.RSButtonBigIcon_new();
        txtDesde = new javax.swing.JTextField();
        txt10 = new javax.swing.JTextField();
        txtE = new javax.swing.JTextField();
        txt5 = new javax.swing.JTextField();
        txtHasta = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        lbAnulados = new javax.swing.JLabel();
        lbTotal = new javax.swing.JLabel();
        dcDesde = new datechooser.beans.DateChooserCombo();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        dcHasta = new datechooser.beans.DateChooserCombo();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);

        Blanco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/fondoBlanco.jpg"))); // NOI18N
        Blanco.setPreferredSize(new java.awt.Dimension(690, 418));

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jScrollPane1.setOpaque(false);

        tblFactura.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        tblFactura.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
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
        tblFactura.setFillsViewportHeight(true);
        tblFactura.setGridColor(new java.awt.Color(204, 204, 204));
        tblFactura.setRowHeight(20);
        tblFactura.setSelectionBackground(new java.awt.Color(0, 102, 102));
        tblFactura.setShowGrid(true);
        tblFactura.setShowVerticalLines(false);
        tblFactura.getTableHeader().setResizingAllowed(false);
        tblFactura.getTableHeader().setReorderingAllowed(false);
        tblFactura.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblFacturaMouseClicked(evt);
            }
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
        tblFactura.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblFacturaKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblFactura);

        jPanel2.setBackground(new java.awt.Color(0, 102, 102));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        L4.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        L4.setForeground(new java.awt.Color(255, 255, 255));
        L4.setText("VENDEDOR:");
        jPanel2.add(L4, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 3, -1, 23));

        txtVendedor.setEditable(false);
        txtVendedor.setBackground(new java.awt.Color(255, 255, 255));
        txtVendedor.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtVendedor.setForeground(new java.awt.Color(255, 255, 255));
        txtVendedor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)));
        txtVendedor.setOpaque(false);
        txtVendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtVendedorActionPerformed(evt);
            }
        });
        jPanel2.add(txtVendedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(697, 3, 360, 23));

        L1.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        L1.setForeground(new java.awt.Color(255, 255, 255));
        L1.setText("OP:");
        jPanel2.add(L1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 3, -1, 23));

        txtCodCliente.setEditable(false);
        txtCodCliente.setBackground(new java.awt.Color(255, 255, 255));
        txtCodCliente.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        txtCodCliente.setForeground(new java.awt.Color(255, 255, 255));
        txtCodCliente.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)));
        txtCodCliente.setOpaque(false);
        txtCodCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodClienteActionPerformed(evt);
            }
        });
        jPanel2.add(txtCodCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 3, 48, 23));

        L3.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        L3.setForeground(new java.awt.Color(255, 255, 255));
        L3.setText("RAZÓN SOCIAL:");
        jPanel2.add(L3, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 3, -1, 23));

        txtRazonSocial.setEditable(false);
        txtRazonSocial.setBackground(new java.awt.Color(255, 255, 255));
        txtRazonSocial.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        txtRazonSocial.setForeground(new java.awt.Color(255, 255, 255));
        txtRazonSocial.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)));
        txtRazonSocial.setOpaque(false);
        txtRazonSocial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRazonSocialActionPerformed(evt);
            }
        });
        jPanel2.add(txtRazonSocial, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 3, 290, 23));

        L2.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        L2.setForeground(new java.awt.Color(255, 255, 255));
        L2.setText("R.U.C.: ");
        jPanel2.add(L2, new org.netbeans.lib.awtextra.AbsoluteConstraints(82, 3, -1, 23));

        txtRuc.setEditable(false);
        txtRuc.setBackground(new java.awt.Color(255, 255, 255));
        txtRuc.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        txtRuc.setForeground(new java.awt.Color(255, 255, 255));
        txtRuc.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)));
        txtRuc.setOpaque(false);
        txtRuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRucActionPerformed(evt);
            }
        });
        jPanel2.add(txtRuc, new org.netbeans.lib.awtextra.AbsoluteConstraints(122, 3, 114, 23));

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
        tblDetalle.setRowHeight(20);
        tblDetalle.setSelectionBackground(new java.awt.Color(0, 102, 102));
        tblDetalle.setShowGrid(true);
        tblDetalle.setShowVerticalLines(false);
        tblDetalle.getTableHeader().setResizingAllowed(false);
        tblDetalle.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tblDetalle);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 29, 1067, 280));

        jPanel3.setBackground(new java.awt.Color(0, 102, 102));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setOpaque(false);
        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        btnImprimir.setBackground(new java.awt.Color(0, 102, 102));
        btnImprimir.setBorder(null);
        btnImprimir.setText("EXPORTAR");
        btnImprimir.setBgHover(new java.awt.Color(6, 125, 125));
        btnImprimir.setFocusPainted(false);
        btnImprimir.setFont(new java.awt.Font("Roboto", 1, 10)); // NOI18N
        btnImprimir.setIconTextGap(0);
        btnImprimir.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.DOCK);
        btnImprimir.setPixels(0);
        btnImprimir.setSizeIcon(45.0F);
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });
        jPanel1.add(btnImprimir);

        btnSalir.setBackground(new java.awt.Color(0, 102, 102));
        btnSalir.setBorder(null);
        btnSalir.setText("SALIR");
        btnSalir.setBgHover(new java.awt.Color(6, 125, 125));
        btnSalir.setFocusPainted(false);
        btnSalir.setFont(new java.awt.Font("Roboto", 1, 10)); // NOI18N
        btnSalir.setIconTextGap(0);
        btnSalir.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.EXIT_TO_APP);
        btnSalir.setPixels(0);
        btnSalir.setSizeIcon(45.0F);
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        jPanel1.add(btnSalir);

        jPanel3.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 170, 60));
        jPanel3.add(txtDesde, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 10, 120, -1));
        jPanel3.add(txt10, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 10, 90, -1));
        jPanel3.add(txtE, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 10, 90, -1));
        jPanel3.add(txt5, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 10, 90, -1));
        jPanel3.add(txtHasta, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 30, 120, 21));

        jLabel1.setBackground(new java.awt.Color(0, 102, 102));
        jLabel1.setForeground(new java.awt.Color(0, 102, 102));
        jLabel1.setOpaque(true);

        lbAnulados.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        lbAnulados.setForeground(new java.awt.Color(255, 0, 0));

        lbTotal.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        lbTotal.setForeground(new java.awt.Color(0, 102, 102));

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
    dcDesde.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 1));
    dcDesde.setFieldFont(new java.awt.Font("Roboto", java.awt.Font.BOLD, 15));
    dcDesde.addCommitListener(new datechooser.events.CommitListener() {
        public void onCommit(datechooser.events.CommitEvent evt) {
            dcDesdeOnCommit(evt);
        }
    });

    jLabel2.setText("Desde:");

    jLabel3.setText("Hasta:");

    dcHasta.setCurrentView(new datechooser.view.appearance.AppearancesList("Swing",
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
dcHasta.setCalendarBackground(new java.awt.Color(255, 255, 255));
dcHasta.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 1));
dcHasta.setFieldFont(new java.awt.Font("Roboto", java.awt.Font.BOLD, 15));
dcHasta.addCommitListener(new datechooser.events.CommitListener() {
    public void onCommit(datechooser.events.CommitEvent evt) {
        dcHastaOnCommit(evt);
    }
    });

    jButton1.setText("FILTRAR DOCUENTOS");

    javax.swing.GroupLayout BlancoLayout = new javax.swing.GroupLayout(Blanco);
    Blanco.setLayout(BlancoLayout);
    BlancoLayout.setHorizontalGroup(
        BlancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addComponent(jScrollPane1)
        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addGroup(BlancoLayout.createSequentialGroup()
            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jLabel2)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(dcDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jLabel3)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(dcHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(lbAnulados, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap())
    );
    BlancoLayout.setVerticalGroup(
        BlancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(BlancoLayout.createSequentialGroup()
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(BlancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(BlancoLayout.createSequentialGroup()
                    .addGap(3, 3, 3)
                    .addGroup(BlancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbAnulados, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(BlancoLayout.createSequentialGroup()
                    .addGap(9, 9, 9)
                    .addGroup(BlancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(BlancoLayout.createSequentialGroup()
                            .addGroup(BlancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(dcDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(BlancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addComponent(dcHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(0, 0, Short.MAX_VALUE)))))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
    );

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
    public void llamarReporteFactura() {
        Reporte gr;
        gr = new Reporte();
        //int codF = Integer.parseInt(txtCodFactura.getText());
        //gr.MostrarReporteConParametro(System.getProperty("user.dir")+"/Reportes/Facturas/Factura.jasper", "Factura de Venta", codF,"Facturas/Fact-"+codF+".pdf");
        //gr.cerrar();
    }
    private void tblFacturaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblFacturaMouseClicked
        // TODO add your handling code here:
        /*try {
            CabecerasTablas.limpiarTablas(tblDetalle);
            controlFactura.listDetalle(tblDetalle);
            controlFactura.ListClientes();
            controlFactura.selecVendedor();
            RendersD();
        } catch (Exception e) {
            Mensajes.error(e.getMessage());
        }*/
    }//GEN-LAST:event_tblFacturaMouseClicked

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
            txtE.setText(String.valueOf(getExcetas()));
            txt5.setText(String.valueOf(getCinco()));
            txt10.setText(String.valueOf(getDiez()));
            visible();
            RendersD();
        } catch (Exception e) {
            //Mensajes.error(e.getMessage());
        }
    }//GEN-LAST:event_tblFacturaMousePressed

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        // TODO add your handling code here:
        if (dlgConsultarFacturas1.tblFactura.getSelectedRow() < 0) {
            Mensajes.error("Seleccione la venta que desea Re-Imprimir");
        } else {
            int x = dlgConsultarFacturas1.tblFactura.getSelectedRow();
            int Cod = Integer.parseInt(dlgConsultarFacturas1.tblFactura.getValueAt(x, 0).toString());
            int idEmision = Integer.parseInt(dlgConsultarFacturas1.tblFactura.getValueAt(x, 1).toString());
            String Timbrado = (dlgConsultarFacturas1.tblFactura.getValueAt(x, 17).toString());
            String Desde = (dlgConsultarFacturas1.tblFactura.getValueAt(x, 18).toString());
            String Hasta = (dlgConsultarFacturas1.tblFactura.getValueAt(x, 19).toString());
            String Condicion = (dlgConsultarFacturas1.tblFactura.getValueAt(x, 9).toString());
            String Numeracion = (dlgConsultarFacturas1.tblFactura.getValueAt(x, 8).toString());
            String Fecha = (dlgConsultarFacturas1.tblFactura.getValueAt(x, 3).toString());
            String Hora = (dlgConsultarFacturas1.tblFactura.getValueAt(x, 4).toString());
            String Vendedor = txtVendedor.getText().trim();
            String RazonSocial = txtRazonSocial.getText().trim();
            String RUC = txtRuc.getText().trim();
            String tipo = dlgConsultarFacturas1.tblFactura.getValueAt(x, 7).toString();
            String Total = dlgConsultarFacturas1.tblFactura.getValueAt(x, 11).toString().replace(".", "").replace(",", "");
            String estado;
            
            if(dlgConsultarFacturas1.tblFactura.getValueAt(x, 13).toString().equals("ANULADO")){
                estado="  ** ANULADO **";
            }else{
                estado="";
            }
            String EXENTA = txtE.getText();
            String CINCO = txt5.getText();
            String DIEZ = txt10.getText();
            int iv5 = Integer.parseInt(dlgConsultarFacturas1.tblFactura.getValueAt(x, 15).toString());
            int iv10 = Integer.parseInt(dlgConsultarFacturas1.tblFactura.getValueAt(x, 16).toString());

            //if (estado.equals("ANULADO")) {
            //    Mensajes.informacion("No se posible Re-Imprimir esta Venta.\nMotivo: Esta venta ya fue anulada");
            //} else {
                int rpta = Mensajes.confirmar("¿Seguro que desea Re-Imprimir esta Venta?");
                if (rpta == 0) {
                    if (tipo.equals("FACTURA LEGAL")) {
                        try {
                            //prepararBD();
                            //String Letra = d.Convertir(Total, true);
                            //llamarReporteHoja3(Cod, Letra);
                            //con.close();
                            //conM.close();
                            imprimirFacturaOriginal(estado, idEmision, Integer.parseInt(Total), Timbrado, Desde, Hasta, Condicion, Numeracion, Fecha, Hora, Vendedor, RazonSocial, RUC, EXENTA, CINCO, DIEZ, iv5, iv10);
                            imprimirFacturaDuplicado(estado, idEmision, Integer.parseInt(Total), Timbrado, Desde, Hasta, Condicion, Numeracion, Fecha, Hora, Vendedor, RazonSocial, RUC, EXENTA, CINCO, DIEZ, iv5, iv10);
                        } catch (NumberFormatException e) {
                        }
                    } else if (tipo.equals("TICKET")) {
                        imprimirTicket(idEmision, Integer.parseInt(Total), Condicion, Numeracion, Fecha, Hora, Vendedor, RazonSocial, RUC);
                    }
                }
            }

       // }
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        int rpta = Mensajes.confirmar("¿Seguro que desea salir del formulario?");
        if (rpta == 0) {
            this.dispose();
        }
    }//GEN-LAST:event_btnSalirActionPerformed

    private void dcDesdeOnCommit(datechooser.events.CommitEvent evt) {//GEN-FIRST:event_dcDesdeOnCommit
        // TODO add your handling code here:
        txtDesde.setText(Fecha.formatoFecha(dcDesde.getText()));
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

    private void tblFacturaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblFacturaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tblFacturaKeyPressed

    private void dcHastaOnCommit(datechooser.events.CommitEvent evt) {//GEN-FIRST:event_dcHastaOnCommit
        // TODO add your handling code here:
        txtHasta.setText(Fecha.formatoFecha(dcHasta.getText()));
    }//GEN-LAST:event_dcHastaOnCommit

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
            java.util.logging.Logger.getLogger(dlgConsultarFacturas1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(dlgConsultarFacturas1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(dlgConsultarFacturas1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(dlgConsultarFacturas1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                dlgConsultarFacturas1 dialog = new dlgConsultarFacturas1(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.edisoncor.gui.panel.PanelImage Blanco;
    private javax.swing.JLabel L1;
    private javax.swing.JLabel L2;
    private javax.swing.JLabel L3;
    private javax.swing.JLabel L4;
    public static newscomponents.RSButtonBigIcon_new btnImprimir;
    public static newscomponents.RSButtonBigIcon_new btnSalir;
    private datechooser.beans.DateChooserCombo dcDesde;
    private datechooser.beans.DateChooserCombo dcHasta;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
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
    private static javax.swing.JTextField txtHasta;
    public static javax.swing.JTextField txtRazonSocial;
    public static javax.swing.JTextField txtRuc;
    public static javax.swing.JTextField txtVendedor;
    // End of variables declaration//GEN-END:variables
}
