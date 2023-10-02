package IU;

import Componentes.DataSourceService;
import Componentes.Fecha;
import Componentes.Login;
import Componentes.Mensajes;
import Componentes.RenderDecimal1;
import Componentes.RenderDecimal2;
import Componentes.RenderPagos;
import Componentes.Reporte;
import Componentes.Software;
import Componentes.cargarComboBox;
import Componentes.generarCodigos;
import Componentes.validarCampos;
import Controladores.CabecerasTablas;
import Controladores.controlArticuloMovil;
import Controladores.controlCompra;
//import Controladores.controlCompra;
import Datos.GestionarCompra;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public final class dlgRegistrarPagosProveedor extends javax.swing.JDialog {

    public Reporte jasper;
    static DataSourceService dss = new DataSourceService();

    public dlgRegistrarPagosProveedor(java.awt.Frame parent, boolean modal) throws SQLException {
        super(parent, modal);
        initComponents();
        titulo();
        //cargarComboBox.cargar(cbProveedores, "SELECT pro_codigo, pro_razonsocial FROM proveedor WHERE pro_indicador='S'");
        jasper = new Reporte();
        CabecerasTablas.Pagos(tbDetalle);
        Cancelar();
        Invisible();

    }

    public static void CalcularDifDeposito() {
        try {
            if (!txtTotal.getText().isEmpty() && !txtMontoDepositado.getText().isEmpty()) {
                long resultado = Long.parseLong(txtMontoDepositado.getText().replace(".", "").replace(",", "")) - Long.parseLong(txtTotal.getText().replace("Gs.", "").replace(".", "").replace(",", ""));
                txtDifDeposito.setText(String.valueOf(resultado));
            }
        } catch (Exception e) {
            System.out.println("Error calculando diferencia: " + e.getMessage());
        }

    }

    final void titulo() {
        if (Software.getSoftware().equals("null")) {
            this.setTitle("Registrar pagos a proveedores");
        } else {
            this.setTitle(Software.getSoftware() + " - Registrar pagos a proveedores");
        }
    }

    private void AccesoRapido(int n) {
        switch (n) {
            case KeyEvent.VK_F1 ->
                btnNuevo.doClick();
            case KeyEvent.VK_F6 ->
                btnGuardar.doClick();
            case KeyEvent.VK_ALT | KeyEvent.VK_F4 ->
                btnSalir.doClick();
            case KeyEvent.VK_ESCAPE ->
                btnCancelar.doClick();
            case KeyEvent.VK_F3 ->
                btnBuscarFacturas.doClick();
            case KeyEvent.VK_DELETE ->
                btnRestar.doClick();
            default -> {
            }
        }
        System.out.println(n);
    }

    public void Cancelar() {
        limpiarCampos();
        btnBuscarFacturas.setEnabled(false);
        dcFDesde.setEnabled(false);
        dcFHasta.setEnabled(false);
        btnNuevo.setEnabled(true);
        btnGuardar.setEnabled(false);
        btnCancelar.setEnabled(false);
        cbProveedores.setEnabled(false);
        btnSalir.setEnabled(true);
        cant();
    }

    public static void Renders() {
        dlgRegistrarPagosProveedor.tbDetalle.getColumnModel().getColumn(2).setCellRenderer(new RenderDecimal2());
        dlgRegistrarPagosProveedor.tbDetalle.getColumnModel().getColumn(3).setCellRenderer(new RenderDecimal1());
        dlgRegistrarPagosProveedor.tbDetalle.getColumnModel().getColumn(4).setCellRenderer(new RenderDecimal1());
        dlgRegistrarPagosProveedor.tbDetalle.getColumnModel().getColumn(5).setCellRenderer(new RenderDecimal1());
        dlgRegistrarPagosProveedor.tbDetalle.getColumnModel().getColumn(6).setCellRenderer(new RenderPagos());
        dlgRegistrarPagosProveedor.tbDetalle.getColumnModel().getColumn(9).setCellRenderer(new RenderDecimal1());
    }

    public void Invisible() {
        btnAdd.setVisible(false);
        btnRestar.setVisible(false);
        txtCodProveedor.setVisible(false);
        txtDesde.setVisible(false);
        txtHasta.setVisible(false);
        lbmonto.setVisible(false);
        lbmonto1.setVisible(false);
        txtDifDeposito.setVisible(false);
    }

    public static void cant() {
        int total = tbDetalle.getRowCount();
        etiCant.setText("Cantidad de Facturas anexadas para registro de pagos: " + String.valueOf(total));
    }

    private void limpiarCampos() {
        txtCod.setText("");
        txtHasta.setText("");
        txtCaja.setText("");
        txtCodProveedor.setText("");
        txtFechaFachada.setText("");
        txtTotal.setText("");
        txtDifDeposito.setText("");
        txtMontoDepositado.setText("");
        txtReciboD.setText("");
        controlCompra.arrayPago.vaciar();
        CabecerasTablas.Pagos(tbDetalle);
        CabecerasTablas.limpiarTablas(tbDetalle);
        cargarComboBox.cargar(cbProveedores, "SELECT pro_codigo, pro_razonsocial FROM proveedor WHERE pro_indicador='S'");

    }

    private void CambiarMontoAbonar() {
        if (tbDetalle.getSelectedRowCount() != 0) {
            try {
                int fila = dlgRegistrarPagosProveedor.tbDetalle.getSelectedRow();
                String factura = (dlgRegistrarPagosProveedor.tbDetalle.getValueAt(fila, 1).toString());
                int monto_factura = Integer.parseInt(dlgRegistrarPagosProveedor.tbDetalle.getValueAt(fila, 2).toString().replace(".", "").replace(",", ""));
                int monto_saldo = Integer.parseInt(dlgRegistrarPagosProveedor.tbDetalle.getValueAt(fila, 3).toString().replace(".", "").replace(",", ""));
                int monto_abonar = Integer.parseInt(dlgRegistrarPagosProveedor.tbDetalle.getValueAt(fila, 4).toString().replace(".", "").replace(",", ""));
                int saldo = monto_saldo - monto_abonar;
                int DIFDEP;
                if (Integer.parseInt(txtDifDeposito.getText()) < 0) {
                    DIFDEP = Integer.parseInt(txtDifDeposito.getText()) * -1;
                } else {
                    DIFDEP = Integer.parseInt(txtDifDeposito.getText());
                }
                DecimalFormat df = new DecimalFormat("#,###");
                if (DIFDEP >= monto_abonar) {
                    lbSaldoDepositado.setForeground(new Color(0, 102, 0));
                    lbSaldoDepositado.setText("Monto cubierto sin inconvenientes");
                } else if (Integer.parseInt(txtMontoDepositado.getText().replace(",", "").replace(".", "")) >= Integer.parseInt(txtTotal.getText().replace("Gs.", "").replace(".", "").replace(",", ""))) {
                    lbSaldoDepositado.setForeground(new Color(0, 102, 0));
                    lbSaldoDepositado.setText("Monto cubierto sin inconvenientes");

                } else {
                    int Depositado = monto_abonar - DIFDEP;
                    lbSaldoDepositado.setForeground(new Color(255, 0, 0));
                    lbSaldoDepositado.setText("Monto habilitado del depósito: " + df.format(Depositado));
                }
                lbFactura.setText("Factura Nro: " + factura);
                lbMonto_Factura.setText("Monto de la compra: " + df.format(monto_factura));
                lbMonto_abonar.setText("Monto del saldo a abonar: " + df.format(monto_saldo));

                lbSaldo_final.setText("Saldo pendiente: " + df.format(saldo));
                lbmonto.setText(String.valueOf(monto_saldo));
                txtAbono.setText("0");
                DialogCambioPago.setSize(299, 222);
                DialogCambioPago.setLocationRelativeTo(this);
                DialogCambioPago.setModal(true);
                //DialogCambioPago.setTStitle("OPCIONES");
                DialogCambioPago.setVisible(true);
                txtAbono.requestFocus();
            } catch (Exception e) {
            }
        }
    }

    private void AnexarNC() {
        if (tbDetalle.getSelectedRowCount() != 0) {
            try {
                //controlCompra.actMontoPago(tbDetalle);
                int fila = dlgRegistrarPagosProveedor.tbDetalle.getSelectedRow();
                String factura = (dlgRegistrarPagosProveedor.tbDetalle.getValueAt(fila, 1).toString());
                int monto_factura = Integer.parseInt(dlgRegistrarPagosProveedor.tbDetalle.getValueAt(fila, 2).toString().replace(".", "").replace(",", ""));
                int monto_saldo = Integer.parseInt(dlgRegistrarPagosProveedor.tbDetalle.getValueAt(fila, 7).toString().replace(".", "").replace(",", ""));
                int monto_abonar = Integer.parseInt(dlgRegistrarPagosProveedor.tbDetalle.getValueAt(fila, 4).toString().replace(".", "").replace(",", ""));
                int saldo = monto_saldo - monto_abonar;
                DecimalFormat df = new DecimalFormat("#,###");
                lbFactura1.setText("Factura Nro: " + factura);
                lbMonto_Factura1.setText("Monto de la compra: " + df.format(monto_factura));
                lbmonto1.setText(String.valueOf(monto_saldo));
                txtNC.setText("-");
                txtAbono1.setText("0");
                DialogAgregarNC.setSize(318, 190);
                DialogAgregarNC.setLocationRelativeTo(this);
                DialogAgregarNC.setModal(true);
                //DialogCambioPago.setTStitle("OPCIONES");
                DialogAgregarNC.setVisible(true);
                txtNC.requestFocus();

            } catch (Exception e) {
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuEmergente = new javax.swing.JPopupMenu();
        itemAnexarNC = new javax.swing.JMenuItem();
        itemMonto_a_abonar = new javax.swing.JMenuItem();
        DialogCambioPago = new javax.swing.JDialog();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtAbono = new javax.swing.JTextField();
        btnAceptar = new javax.swing.JButton();
        btnCancelarModificacionPago = new javax.swing.JButton();
        lbFactura = new javax.swing.JLabel();
        lbMonto_abonar = new javax.swing.JLabel();
        lbMonto_Factura = new javax.swing.JLabel();
        lbSaldo_final = new javax.swing.JLabel();
        lbmonto = new javax.swing.JLabel();
        lbSaldoDepositado = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        DialogAgregarNC = new javax.swing.JDialog();
        jPanel6 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtAbono1 = new javax.swing.JTextField();
        btnAceptar1 = new javax.swing.JButton();
        btnCancelarModificacionPago1 = new javax.swing.JButton();
        lbFactura1 = new javax.swing.JLabel();
        lbMonto_Factura1 = new javax.swing.JLabel();
        lbmonto1 = new javax.swing.JLabel();
        txtNC = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        Blanco = new org.edisoncor.gui.panel.PanelImage();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtCod = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtCaja = new javax.swing.JTextField();
        txtFechaFachada = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtReciboD = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtMontoDepositado = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbDetalle = new javax.swing.JTable()
        {
            public boolean isCellEditable(int rowInddex, int celIndex)
            {
                return false;
            }
        };
        etiCant = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        txtTotal = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        txtHasta = new javax.swing.JTextField();
        btnSalir = new RSMaterialComponent.RSButtonIconOne();
        txtCodProveedor = new javax.swing.JTextField();
        btnRestar = new javax.swing.JButton();
        txtDesde = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        PanelContenedor1 = new rojeru_san.rspanel.RSPanelImage();
        btnNuevo = new RSMaterialComponent.RSButtonIconUno();
        Separador1 = new javax.swing.JSeparator();
        LabelTitulo1 = new javax.swing.JLabel();
        PanelContenedor3 = new rojeru_san.rspanel.RSPanelImage();
        btnGuardar = new RSMaterialComponent.RSButtonIconUno();
        Separador3 = new javax.swing.JSeparator();
        LabelTitulo3 = new javax.swing.JLabel();
        PanelContenedor4 = new rojeru_san.rspanel.RSPanelImage();
        btnCancelar = new RSMaterialComponent.RSButtonIconUno();
        Separador4 = new javax.swing.JSeparator();
        LabelTitulo4 = new javax.swing.JLabel();
        txtDifDeposito = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        dcFDesde = new datechooser.beans.DateChooserCombo();
        dcFHasta = new datechooser.beans.DateChooserCombo();
        btnBuscarFacturas = new newscomponents.RSButtonGradientIcon_new();
        cbProveedores = new RSMaterialComponent.RSComboBox();

        menuEmergente.setBackground(new java.awt.Color(255, 255, 255));
        menuEmergente.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N

        itemAnexarNC.setBackground(new java.awt.Color(255, 255, 255));
        itemAnexarNC.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        itemAnexarNC.setForeground(new java.awt.Color(255, 0, 0));
        itemAnexarNC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/baseline_edit_black_20.png"))); // NOI18N
        itemAnexarNC.setText("1- ANEXAR NOTA DE CRÉDITO A ESTA FACTURA");
        itemAnexarNC.setOpaque(true);
        itemAnexarNC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemAnexarNCActionPerformed(evt);
            }
        });
        menuEmergente.add(itemAnexarNC);

        itemMonto_a_abonar.setBackground(new java.awt.Color(255, 255, 255));
        itemMonto_a_abonar.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        itemMonto_a_abonar.setForeground(new java.awt.Color(0, 102, 51));
        itemMonto_a_abonar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/baseline_edit_black_20.png"))); // NOI18N
        itemMonto_a_abonar.setText("2- MODIFICAR EL MONTO A ABONAR DE ESTA FACTURA");
        itemMonto_a_abonar.setOpaque(true);
        itemMonto_a_abonar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemMonto_a_abonarActionPerformed(evt);
            }
        });
        menuEmergente.add(itemMonto_a_abonar);

        DialogCambioPago.setUndecorated(true);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Roboto", 1, 10)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 102, 102));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("CAMBIO DEL MONTO A ABONAR");
        jPanel5.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 5, 297, 23));

        txtAbono.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        txtAbono.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAbono.setText("0");
        txtAbono.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        txtAbono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAbonoActionPerformed(evt);
            }
        });
        txtAbono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtAbonoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtAbonoKeyReleased(evt);
            }
        });
        jPanel5.add(txtAbono, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 145, 273, 30));

        btnAceptar.setText("ACEPTAR CAMBIO");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });
        jPanel5.add(btnAceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(56, 185, -1, -1));

        btnCancelarModificacionPago.setText("CANCELAR");
        btnCancelarModificacionPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarModificacionPagoActionPerformed(evt);
            }
        });
        jPanel5.add(btnCancelarModificacionPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(193, 185, -1, -1));

        lbFactura.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        lbFactura.setText("Factura Nro:");
        jPanel5.add(lbFactura, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 35, 285, -1));

        lbMonto_abonar.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        lbMonto_abonar.setForeground(new java.awt.Color(204, 102, 0));
        lbMonto_abonar.setText("Monto actual a abonar:");
        jPanel5.add(lbMonto_abonar, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 77, 285, -1));

        lbMonto_Factura.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        lbMonto_Factura.setText("Monto de la Factura:");
        jPanel5.add(lbMonto_Factura, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 56, 285, -1));

        lbSaldo_final.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        lbSaldo_final.setText("Saldo final:");
        jPanel5.add(lbSaldo_final, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 125, 285, -1));

        lbmonto.setText("0");
        jPanel5.add(lbmonto, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 38, -1));

        lbSaldoDepositado.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        lbSaldoDepositado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbSaldoDepositado.setText("Monto habilitado del depósito:");
        jPanel5.add(lbSaldoDepositado, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 100, 285, -1));

        jSeparator1.setForeground(new java.awt.Color(204, 204, 204));
        jPanel5.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 30, 297, 10));

        jSeparator3.setForeground(new java.awt.Color(204, 204, 204));
        jPanel5.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 120, 297, 10));

        jSeparator2.setForeground(new java.awt.Color(204, 204, 204));
        jPanel5.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 95, 297, 10));

        javax.swing.GroupLayout DialogCambioPagoLayout = new javax.swing.GroupLayout(DialogCambioPago.getContentPane());
        DialogCambioPago.getContentPane().setLayout(DialogCambioPagoLayout);
        DialogCambioPagoLayout.setHorizontalGroup(
            DialogCambioPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        DialogCambioPagoLayout.setVerticalGroup(
            DialogCambioPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        DialogAgregarNC.setUndecorated(true);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Roboto", 1, 10)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 102, 102));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("ANEXAR NOTA DE CRÉDITO A LA FACTURA");
        jPanel6.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 1, 316, 27));

        txtAbono1.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        txtAbono1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAbono1.setText("0");
        txtAbono1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        txtAbono1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAbono1ActionPerformed(evt);
            }
        });
        txtAbono1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtAbono1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtAbono1KeyReleased(evt);
            }
        });
        jPanel6.add(txtAbono1, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 114, 292, 30));

        btnAceptar1.setText("ACEPTAR ANEXIÓN");
        btnAceptar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptar1ActionPerformed(evt);
            }
        });
        jPanel6.add(btnAceptar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(69, 151, -1, -1));

        btnCancelarModificacionPago1.setText("CANCELAR");
        btnCancelarModificacionPago1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarModificacionPago1ActionPerformed(evt);
            }
        });
        jPanel6.add(btnCancelarModificacionPago1, new org.netbeans.lib.awtextra.AbsoluteConstraints(212, 151, -1, -1));

        lbFactura1.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        lbFactura1.setText("Factura Nro:");
        jPanel6.add(lbFactura1, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 35, 292, -1));

        lbMonto_Factura1.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        lbMonto_Factura1.setText("Monto de la Factura:");
        jPanel6.add(lbMonto_Factura1, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 56, 292, -1));

        lbmonto1.setText("0");
        jPanel6.add(lbmonto1, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 155, 51, -1));

        txtNC.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        txtNC.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNC.setText("-");
        txtNC.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        txtNC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNCActionPerformed(evt);
            }
        });
        txtNC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNCKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNCKeyReleased(evt);
            }
        });
        jPanel6.add(txtNC, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 77, 292, 30));

        jSeparator4.setForeground(new java.awt.Color(204, 204, 204));
        jPanel6.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 25, 316, 10));

        javax.swing.GroupLayout DialogAgregarNCLayout = new javax.swing.GroupLayout(DialogAgregarNC.getContentPane());
        DialogAgregarNC.getContentPane().setLayout(DialogAgregarNCLayout);
        DialogAgregarNCLayout.setHorizontalGroup(
            DialogAgregarNCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        DialogAgregarNCLayout.setVerticalGroup(
            DialogAgregarNCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        Blanco.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        Blanco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/fondoBlanco.jpg"))); // NOI18N
        Blanco.setPreferredSize(new java.awt.Dimension(690, 418));
        Blanco.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BlancoKeyPressed(evt);
            }
        });
        Blanco.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel1.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        jLabel1.setText("Operación N°");

        txtCod.setEditable(false);
        txtCod.setBackground(new java.awt.Color(255, 255, 255));
        txtCod.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        txtCod.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCod.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        txtCod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodActionPerformed(evt);
            }
        });
        txtCod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodKeyPressed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        jLabel4.setText("Fecha");

        jLabel12.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        jLabel12.setText("Mov.N°");

        txtCaja.setEditable(false);
        txtCaja.setBackground(new java.awt.Color(255, 255, 255));
        txtCaja.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        txtCaja.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCaja.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        txtCaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCajaActionPerformed(evt);
            }
        });
        txtCaja.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCajaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCajaKeyTyped(evt);
            }
        });

        txtFechaFachada.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        txtFechaFachada.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFechaFachada.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        txtFechaFachada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFechaFachadaActionPerformed(evt);
            }
        });
        txtFechaFachada.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtFechaFachadaKeyPressed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        jLabel14.setText("Recibo de Dinero Nro");

        txtReciboD.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        txtReciboD.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtReciboD.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        txtReciboD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtReciboDKeyPressed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        jLabel15.setText("Monto Depositado");

        txtMontoDepositado.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        txtMontoDepositado.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtMontoDepositado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        txtMontoDepositado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMontoDepositadoActionPerformed(evt);
            }
        });
        txtMontoDepositado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMontoDepositadoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMontoDepositadoKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCod, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCaja, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFechaFachada, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtReciboD, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMontoDepositado, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtFechaFachada, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtReciboD, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtMontoDepositado, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtCod, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtCaja, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Blanco.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 104, 1090, -1));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), "Tabla de Facturas a liquidar", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Roboto", 1, 11), new java.awt.Color(0, 102, 102))); // NOI18N
        jPanel2.setOpaque(false);

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jScrollPane1.setRowHeaderView(null);

        tbDetalle.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        tbDetalle.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        tbDetalle.setModel(new javax.swing.table.DefaultTableModel(
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
        tbDetalle.setGridColor(new java.awt.Color(204, 204, 204));
        tbDetalle.setRowHeight(20);
        tbDetalle.setRowMargin(0);
        tbDetalle.setSelectionBackground(new java.awt.Color(0, 102, 102));
        tbDetalle.setShowGrid(true);
        tbDetalle.setShowVerticalLines(false);
        tbDetalle.getTableHeader().setResizingAllowed(false);
        tbDetalle.getTableHeader().setReorderingAllowed(false);
        tbDetalle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDetalleMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbDetalleMousePressed(evt);
            }
        });
        tbDetalle.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbDetalleKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tbDetalle);

        etiCant.setFont(new java.awt.Font("Roboto", 1, 10)); // NOI18N
        etiCant.setText("Cantidad de facturas anexadas:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(etiCant, javax.swing.GroupLayout.DEFAULT_SIZE, 616, Short.MAX_VALUE)
                .addGap(466, 466, 466))
            .addComponent(jScrollPane1)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(etiCant)
                .addGap(5, 5, 5))
        );

        Blanco.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 222, 1095, -1));

        jPanel9.setBackground(new java.awt.Color(0, 102, 102));
        jPanel9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPanel9KeyPressed(evt);
            }
        });

        txtTotal.setEditable(false);
        txtTotal.setBackground(new java.awt.Color(0, 102, 102));
        txtTotal.setFont(new java.awt.Font("Roboto", 1, 30)); // NOI18N
        txtTotal.setForeground(new java.awt.Color(255, 255, 255));
        txtTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTotal.setBorder(null);
        txtTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalActionPerformed(evt);
            }
        });

        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Create.png"))); // NOI18N
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        txtHasta.setEditable(false);
        txtHasta.setBackground(new java.awt.Color(255, 255, 255));
        txtHasta.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 11)); // NOI18N
        txtHasta.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtHasta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHastaActionPerformed(evt);
            }
        });

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

        txtCodProveedor.setEditable(false);
        txtCodProveedor.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 12)); // NOI18N
        txtCodProveedor.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        btnRestar.setText("R");
        btnRestar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRestarActionPerformed(evt);
            }
        });

        txtDesde.setEditable(false);
        txtDesde.setBackground(new java.awt.Color(255, 255, 255));
        txtDesde.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 11)); // NOI18N
        txtDesde.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDesde.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDesdeActionPerformed(evt);
            }
        });

        jPanel7.setOpaque(false);
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PanelContenedor1.setImagen(new javax.swing.ImageIcon(getClass().getResource("/Recursos/CONTENEDOR2.png"))); // NOI18N
        PanelContenedor1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PanelContenedor1KeyPressed(evt);
            }
        });

        btnNuevo.setBackground(new java.awt.Color(255, 255, 255));
        btnNuevo.setToolTipText("F1");
        btnNuevo.setBackgroundHover(new java.awt.Color(0, 153, 153));
        btnNuevo.setForegroundText(new java.awt.Color(0, 153, 153));
        btnNuevo.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ADD);
        btnNuevo.setRippleColor(java.awt.Color.white);
        btnNuevo.setTypeBorder(RSMaterialComponent.RSButtonIconUno.TYPEBORDER.CIRCLE);
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        btnNuevo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnNuevoKeyPressed(evt);
            }
        });

        Separador1.setForeground(new java.awt.Color(204, 204, 204));

        LabelTitulo1.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        LabelTitulo1.setForeground(new java.awt.Color(0, 102, 102));
        LabelTitulo1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LabelTitulo1.setText("NUEVO");

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
                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );
        PanelContenedor1Layout.setVerticalGroup(
            PanelContenedor1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelContenedor1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(Separador1, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(LabelTitulo1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.add(PanelContenedor1, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 3, 100, -1));

        PanelContenedor3.setImagen(new javax.swing.ImageIcon(getClass().getResource("/Recursos/CONTENEDOR2.png"))); // NOI18N
        PanelContenedor3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PanelContenedor3KeyPressed(evt);
            }
        });

        btnGuardar.setBackground(new java.awt.Color(255, 255, 255));
        btnGuardar.setToolTipText("F6");
        btnGuardar.setBackgroundHover(new java.awt.Color(0, 102, 0));
        btnGuardar.setForegroundText(new java.awt.Color(0, 102, 0));
        btnGuardar.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.SAVE);
        btnGuardar.setRippleColor(java.awt.Color.white);
        btnGuardar.setTypeBorder(RSMaterialComponent.RSButtonIconUno.TYPEBORDER.CIRCLE);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        btnGuardar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnGuardarKeyPressed(evt);
            }
        });

        Separador3.setForeground(new java.awt.Color(204, 204, 204));

        LabelTitulo3.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        LabelTitulo3.setForeground(new java.awt.Color(0, 102, 102));
        LabelTitulo3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LabelTitulo3.setText("REGISTRAR");

        javax.swing.GroupLayout PanelContenedor3Layout = new javax.swing.GroupLayout(PanelContenedor3);
        PanelContenedor3.setLayout(PanelContenedor3Layout);
        PanelContenedor3Layout.setHorizontalGroup(
            PanelContenedor3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelContenedor3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelContenedor3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Separador3)
                    .addComponent(LabelTitulo3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(PanelContenedor3Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );
        PanelContenedor3Layout.setVerticalGroup(
            PanelContenedor3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelContenedor3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(Separador3, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(LabelTitulo3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.add(PanelContenedor3, new org.netbeans.lib.awtextra.AbsoluteConstraints(102, 3, 100, -1));

        PanelContenedor4.setImagen(new javax.swing.ImageIcon(getClass().getResource("/Recursos/CONTENEDOR2.png"))); // NOI18N
        PanelContenedor4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PanelContenedor4KeyPressed(evt);
            }
        });

        btnCancelar.setBackground(new java.awt.Color(255, 255, 255));
        btnCancelar.setToolTipText("ESCAPE");
        btnCancelar.setBackgroundHover(new java.awt.Color(255, 0, 0));
        btnCancelar.setForegroundText(new java.awt.Color(255, 0, 0));
        btnCancelar.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.CANCEL);
        btnCancelar.setRippleColor(java.awt.Color.white);
        btnCancelar.setTypeBorder(RSMaterialComponent.RSButtonIconUno.TYPEBORDER.CIRCLE);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        btnCancelar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnCancelarKeyPressed(evt);
            }
        });

        Separador4.setForeground(new java.awt.Color(204, 204, 204));

        LabelTitulo4.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        LabelTitulo4.setForeground(new java.awt.Color(0, 102, 102));
        LabelTitulo4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LabelTitulo4.setText("CANCELAR");

        javax.swing.GroupLayout PanelContenedor4Layout = new javax.swing.GroupLayout(PanelContenedor4);
        PanelContenedor4.setLayout(PanelContenedor4Layout);
        PanelContenedor4Layout.setHorizontalGroup(
            PanelContenedor4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelContenedor4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelContenedor4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Separador4)
                    .addComponent(LabelTitulo4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelContenedor4Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );
        PanelContenedor4Layout.setVerticalGroup(
            PanelContenedor4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelContenedor4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(Separador4, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(LabelTitulo4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.add(PanelContenedor4, new org.netbeans.lib.awtextra.AbsoluteConstraints(199, 3, 100, -1));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(btnRestar)
                        .addGap(1, 1, 1)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAdd)
                            .addComponent(txtCodProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(txtHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(txtDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(txtDifDeposito, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 324, Short.MAX_VALUE)
                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRestar, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtCodProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addComponent(txtHasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtDifDeposito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        Blanco.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 1, 1103, 99));

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel4.setOpaque(false);

        jLabel2.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        jLabel2.setText("Proveedor");

        jLabel5.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        jLabel5.setText("Hasta");

        jLabel13.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        jLabel13.setText("Desde");

        dcFDesde.setCurrentView(new datechooser.view.appearance.AppearancesList("Grey",
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
    dcFDesde.setCalendarBackground(new java.awt.Color(255, 255, 255));
    dcFDesde.setCalendarPreferredSize(new java.awt.Dimension(300, 180));
    dcFDesde.setFieldFont(new java.awt.Font("Roboto", java.awt.Font.BOLD, 12));
    dcFDesde.setNavigateFont(new java.awt.Font("Roboto", java.awt.Font.PLAIN, 14));
    dcFDesde.addCommitListener(new datechooser.events.CommitListener() {
        public void onCommit(datechooser.events.CommitEvent evt) {
            dcFDesdeOnCommit(evt);
        }
    });

    dcFHasta.setCurrentView(new datechooser.view.appearance.AppearancesList("Grey",
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
dcFHasta.setCalendarBackground(new java.awt.Color(255, 255, 255));
dcFHasta.setCalendarPreferredSize(new java.awt.Dimension(300, 180));
dcFHasta.setFieldFont(new java.awt.Font("Roboto", java.awt.Font.BOLD, 12));
dcFHasta.setNavigateFont(new java.awt.Font("Roboto", java.awt.Font.PLAIN, 14));
dcFHasta.addCommitListener(new datechooser.events.CommitListener() {
    public void onCommit(datechooser.events.CommitEvent evt) {
        dcFHastaOnCommit(evt);
    }
    });

    btnBuscarFacturas.setText("CARGAR LISTA DE FACTURAS");
    btnBuscarFacturas.setToolTipText("F3");
    btnBuscarFacturas.setColorPrimario(new java.awt.Color(0, 102, 102));
    btnBuscarFacturas.setColorPrimarioHover(new java.awt.Color(0, 102, 102));
    btnBuscarFacturas.setColorSecundario(new java.awt.Color(0, 102, 102));
    btnBuscarFacturas.setColorSecundarioHover(new java.awt.Color(0, 102, 102));
    btnBuscarFacturas.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
    btnBuscarFacturas.setForegroundHover(new java.awt.Color(205, 0, 0));
    btnBuscarFacturas.setForegroundIconHover(new java.awt.Color(205, 0, 0));
    btnBuscarFacturas.setIconTextGap(10);
    btnBuscarFacturas.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.LIST);
    btnBuscarFacturas.setSizeIcon(21.0F);
    btnBuscarFacturas.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnBuscarFacturasActionPerformed(evt);
        }
    });
    btnBuscarFacturas.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyPressed(java.awt.event.KeyEvent evt) {
            btnBuscarFacturasKeyPressed(evt);
        }
    });

    cbProveedores.setForeground(new java.awt.Color(0, 0, 0));
    cbProveedores.setColorArrow(new java.awt.Color(255, 255, 255));
    cbProveedores.setColorBorde(new java.awt.Color(204, 204, 204));
    cbProveedores.setColorBoton(new java.awt.Color(153, 153, 153));
    cbProveedores.setColorFondo(new java.awt.Color(255, 255, 255));
    cbProveedores.setColorSeleccion(new java.awt.Color(0, 102, 102));
    cbProveedores.setConBorde(true);
    cbProveedores.setEnabled(false);
    cbProveedores.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
    cbProveedores.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            cbProveedoresActionPerformed(evt);
        }
    });
    cbProveedores.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyPressed(java.awt.event.KeyEvent evt) {
            cbProveedoresKeyPressed(evt);
        }
    });

    javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
    jPanel4.setLayout(jPanel4Layout);
    jPanel4Layout.setHorizontalGroup(
        jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel4Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jLabel2)
            .addGap(10, 10, 10)
            .addComponent(cbProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(dcFDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jLabel5)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(dcFHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(btnBuscarFacturas, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    jPanel4Layout.setVerticalGroup(
        jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel4Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(btnBuscarFacturas, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(dcFHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(dcFDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    Blanco.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 163, -1, -1));

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addComponent(Blanco, javax.swing.GroupLayout.PREFERRED_SIZE, 1105, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 0, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(Blanco, javax.swing.GroupLayout.PREFERRED_SIZE, 569, javax.swing.GroupLayout.PREFERRED_SIZE)
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        /*controlCompra.addTablaPagos(tbDetalle);
        Renders();
        cant();
        tbDetalle.changeSelection(tbDetalle.getRowCount() - 1, 0, false, false);
        btnBuscarArticuloActionPerformed(null);*/


    }//GEN-LAST:event_btnAddActionPerformed

    private void btnRestarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestarActionPerformed
        // TODO add your handling code here:
        try {
            controlCompra.delRenglonPagos(tbDetalle);
            Renders();
            cant();
        } catch (Exception e) {
            Mensajes.error("Seleccione una fila de la tabla");
        }

    }//GEN-LAST:event_btnRestarActionPerformed

    private void txtTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalActionPerformed

    private void txtCodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodActionPerformed

    private void txtCajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCajaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCajaActionPerformed

    private void txtCajaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCajaKeyPressed
        // TODO add your handling code here:
        AccesoRapido(evt.getKeyCode());
    }//GEN-LAST:event_txtCajaKeyPressed

    private void txtCajaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCajaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCajaKeyTyped

    private void tbDetalleMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDetalleMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbDetalleMousePressed

    private void tbDetalleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDetalleMouseClicked
        // TODO add your handling code here:
        if (evt.getButton() == 1) {
            if (evt.getClickCount() == 2) {
                menuEmergente.show(tbDetalle, evt.getX(), evt.getY());
            }
        }
    }//GEN-LAST:event_tbDetalleMouseClicked

    private void txtHastaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHastaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHastaActionPerformed

    private void btnBuscarFacturasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarFacturasActionPerformed
        // TODO add your handling code here:
        if (txtCodProveedor.getText().isEmpty()) {
            Mensajes.Alerta("Seleccione un Proveedor para filtrar las facturas.");
        } else if (txtDesde.getText().isEmpty()) {
            Mensajes.Alerta("Es necesario proporcionar una fecha de inicio para iniciar el filtrado.");
        } else if (txtHasta.getText().isEmpty()) {
            Mensajes.Alerta("Es necesario proporcionar una fecha final para iniciar el filtrado.");
        } else if (txtMontoDepositado.getText().isEmpty()) {
            Mensajes.Alerta("MONTO DEPOSITADO: Vacio.\nEs necesario proporcionar el monto depositado para que el sistema realice los calculos pertinentes.");
            txtMontoDepositado.requestFocus();
        } else if (txtMontoDepositado.getText().equals("0")) {
            Mensajes.Alerta("MONTO DEPOSITADO: 0.\nEs necesario proporcionar el monto depositado para que el sistema realice los calculos pertinentes.");
            txtMontoDepositado.requestFocus();
        } else {
            try {
                dlgBuscarFacturaCredito bac = new dlgBuscarFacturaCredito(null, true);
                bac.setLocationRelativeTo(null);
                // bac.setLocation(200, 250);
                bac.setVisible(true);
            } catch (Exception e) {
                Mensajes.informacion("No hay conexión con el servidor");
                System.out.println(e.getMessage());
            }
        }
    }//GEN-LAST:event_btnBuscarFacturasActionPerformed

    private void dcFDesdeOnCommit(datechooser.events.CommitEvent evt) {//GEN-FIRST:event_dcFDesdeOnCommit
        // TODO add your handling code here:
        txtDesde.setText(Fecha.formatoFecha(dcFDesde.getText()));
    }//GEN-LAST:event_dcFDesdeOnCommit

    private void dcFHastaOnCommit(datechooser.events.CommitEvent evt) {//GEN-FIRST:event_dcFHastaOnCommit
        // TODO add your handling code here:
        txtHasta.setText(Fecha.formatoFecha(dcFHasta.getText()));
    }//GEN-LAST:event_dcFHastaOnCommit

    private void txtDesdeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDesdeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDesdeActionPerformed

    private void itemMonto_a_abonarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemMonto_a_abonarActionPerformed
        // TODO add your handling code here:
        CambiarMontoAbonar();
    }//GEN-LAST:event_itemMonto_a_abonarActionPerformed

    private void btnCancelarModificacionPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarModificacionPagoActionPerformed
        // TODO add your handling code here:
        DialogCambioPago.dispose();
        tbDetalle.clearSelection();
        CalcularDifDeposito();
    }//GEN-LAST:event_btnCancelarModificacionPagoActionPerformed

    private void txtAbonoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAbonoKeyReleased
        // TODO add your handling code here:
        try {
            if (txtAbono.getText().trim().length() >= 1) {
                DecimalFormat df = new DecimalFormat("#,###");
                txtAbono.setText(df.format(Integer.valueOf(txtAbono.getText().trim().replace(".", "").replace(",", ""))));

            } else {
                txtAbono.setText("0");
                txtAbono.selectAll();

            }
        } catch (NumberFormatException e) {
            System.out.println("c: " + e.getMessage());
        }
        try {
            DecimalFormat df = new DecimalFormat("#,###");
            int abono = Integer.parseInt(txtAbono.getText().trim().replace(".", "").replace(",", ""));
            int monto = Integer.parseInt(lbmonto.getText().trim());
            if (abono > monto) {
                Mensajes.Sistema("El monto ingresado: " + df.format(abono) + " supera el valor completo o parcial de esta factura, la cual es de: " + df.format(monto));
                txtAbono.setText(df.format(monto));
                txtAbono.requestFocus();
                abono = Integer.parseInt(txtAbono.getText().trim().replace(".", "").replace(",", ""));
                int saldo = monto - abono;
                lbSaldo_final.setText("Saldo pendiente: " + df.format(saldo));
            } else {
                int saldo = monto - abono;
                lbSaldo_final.setText("Saldo pendiente: " + df.format(saldo));
            }

        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtAbonoKeyReleased

    private void txtAbonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAbonoActionPerformed
        // TODO add your handling code here:
        btnAceptarActionPerformed(null);
    }//GEN-LAST:event_txtAbonoActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        // TODO add your handling code here:
        controlCompra.actMontoPago(tbDetalle);
        DialogCambioPago.dispose();
        tbDetalle.clearSelection();
        CalcularDifDeposito();
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void txtAbonoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAbonoKeyPressed
        // TODO add your handling code here:
        validarCampos.soloNumeros(txtAbono);
    }//GEN-LAST:event_txtAbonoKeyPressed

    private void txtFechaFachadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaFachadaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaFachadaActionPerformed

    private void txtReciboDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtReciboDKeyPressed
        // TODO add your handling code here:
        validarCampos.soloDecimales(txtReciboD);
        validarCampos.cantCaracteres(txtReciboD, 15);
        AccesoRapido(evt.getKeyCode());
    }//GEN-LAST:event_txtReciboDKeyPressed

    private void txtAbono1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAbono1ActionPerformed
        // TODO add your handling code here:
        btnAceptar1.doClick();
    }//GEN-LAST:event_txtAbono1ActionPerformed

    private void txtAbono1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAbono1KeyPressed
        // TODO add your handling code here:
        validarCampos.soloNumeros(txtAbono1);
    }//GEN-LAST:event_txtAbono1KeyPressed

    private void txtAbono1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAbono1KeyReleased
        // TODO add your handling code here:
        try {
            if (txtAbono1.getText().trim().length() >= 1) {
                DecimalFormat df = new DecimalFormat("#,###");
                txtAbono1.setText(df.format(Integer.valueOf(txtAbono1.getText().trim().replace(".", "").replace(",", ""))));

            } else {
                txtAbono1.setText("0");
                txtAbono1.selectAll();

            }
        } catch (NumberFormatException e) {
            System.out.println("c: " + e.getMessage());
        }
        try {
            DecimalFormat df = new DecimalFormat("#,###");
            int abono = Integer.parseInt(txtAbono1.getText().trim().replace(".", "").replace(",", ""));
            int monto = Integer.parseInt(lbmonto1.getText().trim());
            if (abono > monto) {
                Mensajes.Sistema("El monto de la Nota de Crédito ingresado: " + df.format(abono) + " supera el valor del Saldo actual esta factura, la cual es de: " + df.format(monto));
                txtAbono1.setText(df.format(monto));
                txtAbono1.requestFocus();
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtAbono1KeyReleased

    private void btnAceptar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptar1ActionPerformed
        // TODO add your handling code here:
        if (txtNC.getText().trim().equals("-") && txtAbono1.getText().trim().equals("0")) {
            controlCompra.addNCPagoProveedor(tbDetalle);
            DialogAgregarNC.dispose();
            tbDetalle.clearSelection();
        } else {
            if (txtNC.getText().trim().equals("-")) {
                Mensajes.Sistema("Ingrese el Número de Nota de Crédito correspondiente a esta factura.");
                txtNC.requestFocus();
                txtNC.selectAll();
            } else if (txtAbono1.getText().trim().equals("0")) {
                Mensajes.Sistema("No se puede registrar una Nota de Crédito con monto 0.");
                txtAbono1.requestFocus();
                txtAbono1.selectAll();
            } else {
                controlCompra.addNCPagoProveedor(tbDetalle);
                DialogAgregarNC.dispose();
                tbDetalle.clearSelection();
            }
        }
        CalcularDifDeposito();

    }//GEN-LAST:event_btnAceptar1ActionPerformed

    private void btnCancelarModificacionPago1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarModificacionPago1ActionPerformed
        // TODO add your handling code here:
        DialogAgregarNC.dispose();
        CalcularDifDeposito();
    }//GEN-LAST:event_btnCancelarModificacionPago1ActionPerformed

    private void itemAnexarNCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemAnexarNCActionPerformed
        // TODO add your handling code here:
        AnexarNC();
    }//GEN-LAST:event_itemAnexarNCActionPerformed

    private void txtNCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNCActionPerformed
        // TODO add your handling code here:}
        txtAbono1.requestFocus();
    }//GEN-LAST:event_txtNCActionPerformed

    private void txtNCKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNCKeyPressed
        // TODO add your handling code here:
        validarCampos.soloDecimales(txtNC);
        validarCampos.cantCaracteres(txtNC, 15);
    }//GEN-LAST:event_txtNCKeyPressed

    private void txtNCKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNCKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNCKeyReleased

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        int rpta = Mensajes.confirmar("¿Seguro que desea salir del formulario?");
        if (rpta == 0) {
            controlCompra.arrayPago.vaciar();
            try {
                CabecerasTablas.ArticulosMovil(dlgArticulosMovil.tbProductos);
                CabecerasTablas.limpiarTablasArticuloM(tbDetalle);
                controlArticuloMovil.listArticulo(dlgArticulosMovil.tbProductos, "cod");
            } catch (Exception e) {
            }
            this.dispose();
        }
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnSalirKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnSalirKeyPressed
        // TODO add your handling code here:
        AccesoRapido(evt.getKeyCode());
    }//GEN-LAST:event_btnSalirKeyPressed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here:
        String cod = GestionarCompra.getCodigoPago();
        txtCod.setText(cod);
        try {
            String FechaI = String.valueOf(Fecha.fechaCorrecta());
            txtCaja.setText(generarCodigos.ObtenerCodigo("SELECT ca_id from caja WHERE ca_fechainicio='" + FechaI + "' and ca_indicador='S'"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        txtFechaFachada.setText(Fecha.fechaCorrectaFachada());
        dcFDesde.setEnabled(true);
        dcFHasta.setEnabled(true);
        dcFDesdeOnCommit(null);
        dcFHastaOnCommit(null);
        btnBuscarFacturas.setEnabled(true);
        btnNuevo.setEnabled(false);
        btnGuardar.setEnabled(true);
        btnCancelar.setEnabled(true);
        btnSalir.setEnabled(false);
        cbProveedores.setEnabled(true);
        CalcularDifDeposito();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        String cod = GestionarCompra.getCodigoPago();
        txtCod.setText(cod);
        if (txtReciboD.getText().isEmpty()) {
            Mensajes.informacion("Ingrese el número de Recido de Dinero emitido por el proveedor");
            txtReciboD.requestFocus();
        } else if (tbDetalle.getRowCount() <= 0) {
            Mensajes.informacion("Tabla vacía.\nPara registrar un pago es necesario identificar las Facturas de Compra.");
            btnBuscarFacturas.doClick();
        } else if (Integer.parseInt(txtMontoDepositado.getText().replace(",", "").replace(".", "")) < Integer.parseInt(txtTotal.getText().replace("Gs.", "").replace(".", "").replace(",", ""))) {
            Mensajes.informacion("El monto depositado no cubre la totalidad de las facturas anexadas.");
        } else {
            try (Connection cn = dss.getDataSource().getConnection(); Statement st = cn.createStatement()) {
                int resp = JOptionPane.showConfirmDialog(this, "¿Seguro que deseas registrar el pago al sistema?", "CONFIRMACIÓN DE PAGO", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (resp == JOptionPane.YES_OPTION) {
                    try {
                        String usuario = Login.getUsuarioLogueado();
                        cn.setAutoCommit(false);
                        String sql = "insert into pagos_proveedor values(" + txtCod.getText() + "," + txtCaja.getText() + "," + txtCodProveedor.getText() + ",'" + txtReciboD.getText()
                                + "','" + Fecha.formatoFecha(txtFechaFachada.getText().trim()) + "','" + Fecha.darHora() + "'," + txtTotal.getText().replace("Gs.", "").replace(".", "").replace(",", "") +","+ txtMontoDepositado.getText().replace(".", "").replace(",", "") + ",'S','" + usuario + "')";
                        st.executeUpdate(sql);
                        int fila = tbDetalle.getRowCount();
                        for (int j = 0; j < fila; j++) {
                            String filas[] = {
                                tbDetalle.getValueAt(j, 0).toString(),//idcompra
                                tbDetalle.getValueAt(j, 4).toString(),//monto_pago
                                tbDetalle.getValueAt(j, 5).toString(),//saldo pendiente
                                tbDetalle.getValueAt(j, 6).toString(),//estado actual
                                tbDetalle.getValueAt(j, 8).toString(),//nc_nro
                                tbDetalle.getValueAt(j, 9).toString(),//nc_monto
                            };
                            sql = "insert into detalle_pagos_proveedor values(" + txtCod.getText() + "," + filas[0] + "," + filas[1] + "," + (j + 1) + ",'" + filas[4] + "'," + filas[5] + ")";
                            String estado = null;
                            switch (filas[3]) {
                                case "PAGO PENDIENTE" ->
                                    estado = "PE";
                                case "PAGO PARCIAL" ->
                                    estado = "PA";
                                case "PAGO ABONADO" ->
                                    estado = "AB";
                                default -> {
                                }
                            }
                            String sql2 = "UPDATE compra SET estado='" + estado + "', saldo=" + filas[2] + " WHERE  com_codigo=" + filas[0];
                            st.executeUpdate(sql);
                            st.executeUpdate(sql2);
                        }
                        cn.commit();
                        Mensajes.informacion("El Pago con Recibo de Dinero N°:" + txtReciboD.getText() + " ha sido regitrado exitosamente");
                        st.close();
                        cn.close();

                    } catch (SQLException e) {
                        try {
                            cn.rollback();
                            Mensajes.error("TRANSACCION FALLIDA. LOS DATOS NO FUERON GUARDADOS EN LA BD." + e.getMessage().toUpperCase());
                            st.close();
                            cn.close();
                        } catch (SQLException se) {
                            Mensajes.error(se.getMessage());
                        }
                    }
                    Cancelar();
                    cant();
                }
            } catch (Exception ee) {
                System.out.println(ee.getMessage());
            }
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        int rpta = Mensajes.confirmar("¿Seguro que desea Cancelar la Compra?");
        if (rpta == 0) {
            limpiarCampos();
            btnBuscarFacturas.setEnabled(false);
            dcFDesde.setEnabled(false);
            dcFHasta.setEnabled(false);
            btnNuevo.setEnabled(true);
            btnGuardar.setEnabled(false);
            btnCancelar.setEnabled(false);
            cbProveedores.setEnabled(false);
            btnSalir.setEnabled(true);
            cant();
        }
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void cbProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbProveedoresActionPerformed
        // TODO add your handling code here:
        try {
            String id = cargarComboBox.getCodidgo(cbProveedores);
            txtCodProveedor.setText(id);
        } catch (Exception e) {
            txtCodProveedor.setText("");
        }
    }//GEN-LAST:event_cbProveedoresActionPerformed

    private void cbProveedoresKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbProveedoresKeyPressed
        // TODO add your handling code here:
        AccesoRapido(evt.getKeyCode());
    }//GEN-LAST:event_cbProveedoresKeyPressed

    private void btnNuevoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnNuevoKeyPressed
        // TODO add your handling code here:
        AccesoRapido(evt.getKeyCode());
    }//GEN-LAST:event_btnNuevoKeyPressed

    private void btnGuardarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnGuardarKeyPressed
        // TODO add your handling code here:
        AccesoRapido(evt.getKeyCode());
    }//GEN-LAST:event_btnGuardarKeyPressed

    private void btnCancelarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnCancelarKeyPressed
        // TODO add your handling code here:
        AccesoRapido(evt.getKeyCode());
    }//GEN-LAST:event_btnCancelarKeyPressed

    private void PanelContenedor4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PanelContenedor4KeyPressed
        // TODO add your handling code here:
        AccesoRapido(evt.getKeyCode());
    }//GEN-LAST:event_PanelContenedor4KeyPressed

    private void PanelContenedor3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PanelContenedor3KeyPressed
        // TODO add your handling code here:
        AccesoRapido(evt.getKeyCode());
    }//GEN-LAST:event_PanelContenedor3KeyPressed

    private void PanelContenedor1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PanelContenedor1KeyPressed
        // TODO add your handling code here:
        AccesoRapido(evt.getKeyCode());
    }//GEN-LAST:event_PanelContenedor1KeyPressed

    private void txtCodKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodKeyPressed
        // TODO add your handling code here:
        AccesoRapido(evt.getKeyCode());
    }//GEN-LAST:event_txtCodKeyPressed

    private void txtFechaFachadaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFechaFachadaKeyPressed
        // TODO add your handling code here:
        AccesoRapido(evt.getKeyCode());
    }//GEN-LAST:event_txtFechaFachadaKeyPressed

    private void btnBuscarFacturasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnBuscarFacturasKeyPressed
        // TODO add your handling code here:
        AccesoRapido(evt.getKeyCode());
    }//GEN-LAST:event_btnBuscarFacturasKeyPressed

    private void tbDetalleKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbDetalleKeyPressed
        // TODO add your handling code here:
        AccesoRapido(evt.getKeyCode());
    }//GEN-LAST:event_tbDetalleKeyPressed

    private void jPanel9KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel9KeyPressed
        // TODO add your handling code here:
        AccesoRapido(evt.getKeyCode());
    }//GEN-LAST:event_jPanel9KeyPressed

    private void BlancoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BlancoKeyPressed
        // TODO add your handling code here:
        AccesoRapido(evt.getKeyCode());
    }//GEN-LAST:event_BlancoKeyPressed

    private void txtMontoDepositadoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMontoDepositadoKeyPressed
        // TODO add your handling code here:
        validarCampos.soloNumeros(txtMontoDepositado);
    }//GEN-LAST:event_txtMontoDepositadoKeyPressed

    private void txtMontoDepositadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMontoDepositadoKeyReleased
        // TODO add your handling code here:
        try {
            if (txtMontoDepositado.getText().trim().length() >= 1) {
                DecimalFormat df = new DecimalFormat("#,###");
                txtMontoDepositado.setText(df.format(Integer.valueOf(txtMontoDepositado.getText().trim().replace(".", "").replace(",", ""))));
            } else {
                txtMontoDepositado.setText("0");
                txtMontoDepositado.selectAll();
            }
        } catch (NumberFormatException e) {
            System.out.println("c: " + e.getMessage());
        }
        CalcularDifDeposito();
    }//GEN-LAST:event_txtMontoDepositadoKeyReleased

    private void txtMontoDepositadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMontoDepositadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMontoDepositadoActionPerformed

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(dlgRegistrarPagosProveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        //</editor-fold>
        java.awt.EventQueue.invokeLater(() -> {
            try {
                dlgRegistrarPagosProveedor dialog = new dlgRegistrarPagosProveedor(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(dlgRegistrarPagosProveedor.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.edisoncor.gui.panel.PanelImage Blanco;
    private javax.swing.JDialog DialogAgregarNC;
    private javax.swing.JDialog DialogCambioPago;
    private javax.swing.JLabel LabelTitulo1;
    private javax.swing.JLabel LabelTitulo3;
    private javax.swing.JLabel LabelTitulo4;
    private rojeru_san.rspanel.RSPanelImage PanelContenedor1;
    private rojeru_san.rspanel.RSPanelImage PanelContenedor3;
    private rojeru_san.rspanel.RSPanelImage PanelContenedor4;
    private javax.swing.JSeparator Separador1;
    private javax.swing.JSeparator Separador3;
    private javax.swing.JSeparator Separador4;
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnAceptar1;
    private javax.swing.JButton btnAdd;
    private newscomponents.RSButtonGradientIcon_new btnBuscarFacturas;
    private RSMaterialComponent.RSButtonIconUno btnCancelar;
    private javax.swing.JButton btnCancelarModificacionPago;
    private javax.swing.JButton btnCancelarModificacionPago1;
    private RSMaterialComponent.RSButtonIconUno btnGuardar;
    private RSMaterialComponent.RSButtonIconUno btnNuevo;
    private javax.swing.JButton btnRestar;
    private RSMaterialComponent.RSButtonIconOne btnSalir;
    public static RSMaterialComponent.RSComboBox cbProveedores;
    private datechooser.beans.DateChooserCombo dcFDesde;
    private datechooser.beans.DateChooserCombo dcFHasta;
    public static javax.swing.JLabel etiCant;
    private javax.swing.JMenuItem itemAnexarNC;
    private javax.swing.JMenuItem itemMonto_a_abonar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JLabel lbFactura;
    private javax.swing.JLabel lbFactura1;
    private javax.swing.JLabel lbMonto_Factura;
    private javax.swing.JLabel lbMonto_Factura1;
    private javax.swing.JLabel lbMonto_abonar;
    private javax.swing.JLabel lbSaldoDepositado;
    private javax.swing.JLabel lbSaldo_final;
    private javax.swing.JLabel lbmonto;
    private javax.swing.JLabel lbmonto1;
    private javax.swing.JPopupMenu menuEmergente;
    public static javax.swing.JTable tbDetalle;
    public static javax.swing.JTextField txtAbono;
    public static javax.swing.JTextField txtAbono1;
    public static javax.swing.JTextField txtCaja;
    public static javax.swing.JTextField txtCod;
    public static javax.swing.JTextField txtCodProveedor;
    public static javax.swing.JTextField txtDesde;
    public static javax.swing.JTextField txtDifDeposito;
    private javax.swing.JTextField txtFechaFachada;
    public static javax.swing.JTextField txtHasta;
    public static javax.swing.JTextField txtMontoDepositado;
    public static javax.swing.JTextField txtNC;
    private javax.swing.JTextField txtReciboD;
    public static javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
