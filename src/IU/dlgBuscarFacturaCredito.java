/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IU;

import Componentes.Mensajes;
import Componentes.RenderDecimal2;
import Componentes.RenderPagos;
import Componentes.Software;
import Controladores.CabecerasTablas;
import Controladores.controlCompra;
import static Controladores.controlCompra.arrayPago;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;

/**
 *
 * @author ADMIN
 */
public class dlgBuscarFacturaCredito extends javax.swing.JDialog {

    CabecerasTablas cabe = new CabecerasTablas();

    public dlgBuscarFacturaCredito(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        titulo();
        cabe.consComprasCreditos(tbCompraCredito);
        CabecerasTablas.limpiarTablas(tbCompraCredito);
        controlCompra.listarComprasPendientes(tbCompraCredito, Integer.parseInt(dlgRegistrarPagosProveedor.txtCodProveedor.getText()), dlgRegistrarPagosProveedor.txtDesde.getText(), dlgRegistrarPagosProveedor.txtHasta.getText());
        Renders();
        cbSeleccionarActionPerformed(null);
    }

    final void titulo() {
        if (Software.getSoftware().equals("null")) {
            this.setTitle("Gestión de compras realizadas");
        } else {
            this.setTitle(Software.getSoftware() + " - Gestión de compras realizadas");
        }
    }
    
    private void AccesoRapido(int n) {
        switch (n) {
            case KeyEvent.VK_ALT | KeyEvent.VK_F4 ->
                btnSalir.doClick();
            case KeyEvent.VK_ESCAPE ->
                btnSalir.doClick();
            case KeyEvent.VK_F6 ->
                btnProcesar.doClick();
            default -> {
            }
        }
        System.out.println(n);
    }

    public static void Renders() {
        tbCompraCredito.getColumnModel().getColumn(5).setCellRenderer(new RenderDecimal2());
        tbCompraCredito.getColumnModel().getColumn(7).setCellRenderer(new RenderDecimal2());
        tbCompraCredito.getColumnModel().getColumn(6).setCellRenderer(new RenderPagos());
    }

    public static boolean Seleccionados(int pos) {
        int contador = 0;
        boolean bandera = true;
        for (int i = 0; i < tbCompraCredito.getRowCount(); i++) {
            boolean seleccion = (boolean) tbCompraCredito.getValueAt(i, pos);
            if (seleccion) {
                contador++;

            }
        }
        if (contador == 0) {
            bandera = false;
        }
        txtCant.setText(String.valueOf(contador));
        return bandera;
    }

    public static void ActualizarTabla() {
        CabecerasTablas.limpiarTablas(tbCompraCredito);
        controlCompra.listarComprasPendientes(tbCompraCredito, Integer.parseInt(dlgRegistrarPagosProveedor.txtCodProveedor.getText()), dlgRegistrarPagosProveedor.txtDesde.getText(), dlgRegistrarPagosProveedor.txtHasta.getText());
        Renders();
        if (cbSeleccionar.isSelected()) {
            cbSeleccionar.setText("Deseleccionar Todo");
            for (int i = 0; i < tbCompraCredito.getRowCount(); i++) {
                tbCompraCredito.setValueAt(true, i, 8);
            }
        } else {
            cbSeleccionar.setText("Seleccionar Todo");
            for (int i = 0; i < tbCompraCredito.getRowCount(); i++) {
                tbCompraCredito.setValueAt(false, i, 8);
            }
        }
        /*DecimalFormat df = new DecimalFormat("#,###");
        int TotalF = controlNCProveedor.getTotalTF();
        txtTMonto.setText(df.format(TotalF));
        int TotalNC = controlNCProveedor.getTotalTNC();
        txtTnc.setText(df.format(TotalNC));
        txtDif.setText(df.format(TotalF - TotalNC));*/
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Blanco = new org.edisoncor.gui.panel.PanelImage();
        Oscuro = new org.edisoncor.gui.panel.PanelImage();
        btnSalir = new newscomponents.RSButtonBigIcon_new();
        cbSeleccionar = new rojerusan.RSCheckBox();
        jPanel1 = new javax.swing.JPanel();
        PanelContenedor1 = new rojeru_san.rspanel.RSPanelImage();
        btnProcesar = new RSMaterialComponent.RSButtonIconUno();
        Separador1 = new javax.swing.JSeparator();
        LabelTitulo1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbCompraCredito = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtCant = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtTMonto = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        Blanco.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        Blanco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/fondoBlanco.jpg"))); // NOI18N
        Blanco.setPreferredSize(new java.awt.Dimension(690, 418));
        Blanco.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BlancoKeyPressed(evt);
            }
        });
        Blanco.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Oscuro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/0-102-102.png"))); // NOI18N
        Oscuro.setPreferredSize(new java.awt.Dimension(690, 418));
        Oscuro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                OscuroKeyPressed(evt);
            }
        });
        Oscuro.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnSalir.setBackground(new java.awt.Color(0, 102, 102));
        btnSalir.setBorder(null);
        btnSalir.setBgHover(new java.awt.Color(255, 0, 0));
        btnSalir.setFocusPainted(false);
        btnSalir.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        btnSalir.setIconTextGap(0);
        btnSalir.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.CLOSE);
        btnSalir.setPixels(0);
        btnSalir.setSizeIcon(15.0F);
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
        Oscuro.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(845, 0, 15, 15));

        cbSeleccionar.setForeground(new java.awt.Color(255, 255, 255));
        cbSeleccionar.setText("Seleccionar Todo");
        cbSeleccionar.setColorCheck(new java.awt.Color(255, 255, 255));
        cbSeleccionar.setColorUnCheck(new java.awt.Color(255, 255, 255));
        cbSeleccionar.setFocusPainted(false);
        cbSeleccionar.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        cbSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSeleccionarActionPerformed(evt);
            }
        });
        cbSeleccionar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbSeleccionarKeyPressed(evt);
            }
        });
        Oscuro.add(cbSeleccionar, new org.netbeans.lib.awtextra.AbsoluteConstraints(695, 75, 160, 23));

        jPanel1.setOpaque(false);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PanelContenedor1.setImagen(new javax.swing.ImageIcon(getClass().getResource("/Recursos/CONTENEDOR2.png"))); // NOI18N
        PanelContenedor1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PanelContenedor1KeyPressed(evt);
            }
        });

        btnProcesar.setBackground(new java.awt.Color(255, 255, 255));
        btnProcesar.setToolTipText("F6");
        btnProcesar.setBackgroundHover(new java.awt.Color(0, 153, 153));
        btnProcesar.setForegroundText(new java.awt.Color(0, 153, 153));
        btnProcesar.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.UNDO);
        btnProcesar.setRippleColor(java.awt.Color.white);
        btnProcesar.setTypeBorder(RSMaterialComponent.RSButtonIconUno.TYPEBORDER.CIRCLE);
        btnProcesar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProcesarActionPerformed(evt);
            }
        });
        btnProcesar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnProcesarKeyPressed(evt);
            }
        });

        Separador1.setForeground(new java.awt.Color(204, 204, 204));

        LabelTitulo1.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        LabelTitulo1.setForeground(new java.awt.Color(0, 102, 102));
        LabelTitulo1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LabelTitulo1.setText("ANEXAR");

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
                .addComponent(btnProcesar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );
        PanelContenedor1Layout.setVerticalGroup(
            PanelContenedor1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelContenedor1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnProcesar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(Separador1, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(LabelTitulo1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(PanelContenedor1, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 3, 100, -1));

        Oscuro.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 120, 100));

        Blanco.add(Oscuro, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 1, 860, 100));

        jScrollPane3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        tbCompraCredito.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        tbCompraCredito.setModel(new javax.swing.table.DefaultTableModel(
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
        tbCompraCredito.setFocusable(false);
        tbCompraCredito.setGridColor(new java.awt.Color(204, 204, 204));
        tbCompraCredito.setRowHeight(25);
        tbCompraCredito.setSelectionBackground(new java.awt.Color(255, 255, 255));
        tbCompraCredito.setSelectionForeground(new java.awt.Color(0, 0, 0));
        tbCompraCredito.setShowGrid(true);
        tbCompraCredito.setShowVerticalLines(false);
        tbCompraCredito.getTableHeader().setResizingAllowed(false);
        tbCompraCredito.getTableHeader().setReorderingAllowed(false);
        tbCompraCredito.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbCompraCreditoMouseClicked(evt);
            }
        });
        tbCompraCredito.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbCompraCreditoKeyPressed(evt);
            }
        });
        jScrollPane3.setViewportView(tbCompraCredito);

        Blanco.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 105, 860, 340));

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        jLabel1.setText("Cantidad de Facturas Seleccionadas:");
        Blanco.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 450, 190, 23));

        txtCant.setEditable(false);
        txtCant.setBackground(new java.awt.Color(255, 255, 255));
        txtCant.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        txtCant.setForeground(new java.awt.Color(0, 51, 51));
        txtCant.setText("0");
        txtCant.setBorder(null);
        txtCant.setOpaque(false);
        Blanco.add(txtCant, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 450, 90, 23));

        jLabel2.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        jLabel2.setText("Monto Total de las Facturas Seleccionadas:");
        Blanco.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 450, 220, 23));

        txtTMonto.setEditable(false);
        txtTMonto.setBackground(new java.awt.Color(255, 255, 255));
        txtTMonto.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        txtTMonto.setForeground(new java.awt.Color(0, 51, 51));
        txtTMonto.setText("0");
        txtTMonto.setBorder(null);
        txtTMonto.setOpaque(false);
        Blanco.add(txtTMonto, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 450, 140, 23));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Blanco, javax.swing.GroupLayout.PREFERRED_SIZE, 862, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Blanco, javax.swing.GroupLayout.PREFERRED_SIZE, 478, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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

    private void cbSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSeleccionarActionPerformed
        // TODO add your handling code here:
        if (cbSeleccionar.isSelected()) {
            cbSeleccionar.setText("Deseleccionar Todo");
            for (int i = 0; i < tbCompraCredito.getRowCount(); i++) {
                tbCompraCredito.setValueAt(true, i, 8);
            }
        } else {
            cbSeleccionar.setText("Seleccionar Todo");
            for (int i = 0; i < tbCompraCredito.getRowCount(); i++) {
                tbCompraCredito.setValueAt(false, i, 8);
            }
        }
        tbCompraCreditoMouseClicked(null);
    }//GEN-LAST:event_cbSeleccionarActionPerformed

    private void tbCompraCreditoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbCompraCreditoMouseClicked
        DecimalFormat df = new DecimalFormat("#,###");
        int TotalF = controlCompra.getTotalTF();
        txtTMonto.setText(df.format(TotalF));
    }//GEN-LAST:event_tbCompraCreditoMouseClicked

    private void btnProcesarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProcesarActionPerformed
        // TODO add your handling code here:
        try {
            arrayPago.vaciar();
            CabecerasTablas.Pagos(dlgRegistrarPagosProveedor.tbDetalle);
            CabecerasTablas.limpiarTablas(dlgRegistrarPagosProveedor.tbDetalle);
            if (Seleccionados(8)) {
                int rpta = Mensajes.confirmar("¿Seguro que desea anexar las " + Integer.valueOf(txtCant.getText()) + " facturas seleccionadas?");
                if (rpta == 0) {
                    for (int i = 0; i < tbCompraCredito.getRowCount(); i++) {
                        boolean sel = (boolean) tbCompraCredito.getValueAt(i, 8);
                        if (sel) {
                            int codCompra = Integer.parseInt(String.valueOf(tbCompraCredito.getModel().getValueAt(i, 0)));
                            String factura = String.valueOf(tbCompraCredito.getModel().getValueAt(i, 4));
                            int monto_factura = Integer.parseInt(String.valueOf(tbCompraCredito.getModel().getValueAt(i, 5)));
                            int monto_pago = Integer.parseInt(String.valueOf(tbCompraCredito.getModel().getValueAt(i, 7)));
                            controlCompra.addTablaPagos(dlgRegistrarPagosProveedor.tbDetalle, codCompra, factura, monto_factura, monto_pago);
                            dlgRegistrarPagosProveedor.Renders();
                            dlgRegistrarPagosProveedor.cant();
                        }
                    }
                    this.dispose();
                }

            } else {
                Mensajes.Sistema("No se ha seleccionado ninguna factura.\nProceda a tildar una o varias facturas de la lista filtrada para avanzar con la operación.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_btnProcesarActionPerformed

    private void PanelContenedor1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PanelContenedor1KeyPressed
        // TODO add your handling code here:
        AccesoRapido(evt.getKeyCode());
    }//GEN-LAST:event_PanelContenedor1KeyPressed

    private void btnProcesarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnProcesarKeyPressed
        // TODO add your handling code here:
        AccesoRapido(evt.getKeyCode());
    }//GEN-LAST:event_btnProcesarKeyPressed

    private void OscuroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_OscuroKeyPressed
        // TODO add your handling code here:
        AccesoRapido(evt.getKeyCode());
    }//GEN-LAST:event_OscuroKeyPressed

    private void cbSeleccionarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbSeleccionarKeyPressed
        // TODO add your handling code here:
        AccesoRapido(evt.getKeyCode());
    }//GEN-LAST:event_cbSeleccionarKeyPressed

    private void tbCompraCreditoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbCompraCreditoKeyPressed
        // TODO add your handling code here:
        AccesoRapido(evt.getKeyCode());
    }//GEN-LAST:event_tbCompraCreditoKeyPressed

    private void BlancoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BlancoKeyPressed
        // TODO add your handling code here:
        AccesoRapido(evt.getKeyCode());
    }//GEN-LAST:event_BlancoKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(dlgBuscarFacturaCredito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(dlgBuscarFacturaCredito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(dlgBuscarFacturaCredito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(dlgBuscarFacturaCredito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                dlgBuscarFacturaCredito dialog = new dlgBuscarFacturaCredito(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel LabelTitulo1;
    private org.edisoncor.gui.panel.PanelImage Oscuro;
    private rojeru_san.rspanel.RSPanelImage PanelContenedor1;
    private javax.swing.JSeparator Separador1;
    private RSMaterialComponent.RSButtonIconUno btnProcesar;
    public static newscomponents.RSButtonBigIcon_new btnSalir;
    public static rojerusan.RSCheckBox cbSeleccionar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane3;
    public static javax.swing.JTable tbCompraCredito;
    private static javax.swing.JTextField txtCant;
    public static javax.swing.JTextField txtTMonto;
    // End of variables declaration//GEN-END:variables
}
