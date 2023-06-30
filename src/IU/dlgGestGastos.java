/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IU;

import Componentes.Fecha;
import Componentes.Mensajes;
import Componentes.RenderDecimal1;
import Componentes.Software;
import Componentes.generarCodigos;
import Controladores.CabecerasTablas;
import Controladores.ControlGasto;
import java.text.DecimalFormat;

/**
 *
 * @author ADMIN
 */
public class dlgGestGastos extends javax.swing.JDialog {

    /**
     * Creates new form dlgGestGastos
     * @param parent
     * @param modal
     */
    public dlgGestGastos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        titulo();
        txtFechaFinal.setText(Fecha.formatoFecha(dcFecha.getText()));
        btnActualizarActionPerformed(null);
        //Renders();
        txtFechaFinal.setVisible(false);
    }
    
    final void titulo() {
        if (Software.getSoftware().equals("null")) {
            this.setTitle("Gestionar todos los Gastos Diarios");
        } else {
            this.setTitle(Software.getSoftware() + " - Gestionar todos los Gastos Diarios");
        }
    }
    
    public void actualizar(){
        txtGL.setText("0");
        txtGR.setText("0");
        txtGA.setText("0");
        CabecerasTablas.gestGastos(tbGastos);
        CabecerasTablas.limpiarTablasGastos(tbGastos);
        ControlGasto.listarGastos(tbGastos, txtFechaFinal.getText().trim());
        Renders();
        calcularValores();
    }
    
    private void delArticulo() {

        int x = tbGastos.getSelectedRow();
        if (x < 0) {
            Mensajes.informacion("Seleccione una fila de la tabla");
        } else {

            try {
                int ID = Integer.parseInt(tbGastos.getValueAt(x, 0).toString());
                String DEs = (tbGastos.getValueAt(x, 4).toString());
                int rpta = Mensajes.confirmar("Desea realmente anular el Gasto de ORIGEN "+DEs);
                if (rpta == 0) {
                    ControlGasto.anularGasto(ID);
                    /*CabecerasTablas.gestGastos(tbGastos);
                    CabecerasTablas.limpiarTablasGastos(tbGastos);
                    ControlGasto.listarGastos(tbGastos, txtFechaFinal.getText().trim());
                    Renders();
                    calcularValores();*/
                    btnActualizarActionPerformed(null);
                }
            } catch (NumberFormatException e) {
            }

        }

    }
    
    public static void Renders() {
        tbGastos.getColumnModel().getColumn(6).setCellRenderer(new RenderDecimal1());
        //tbGastos.getColumnModel().getColumn(6).setCellRenderer(new RenderDecimal2());
    }
    
    public static void calcularValores() {
        DecimalFormat df = new DecimalFormat("#,###");
        
        int filas = tbGastos.getRowCount();
        if (filas == 0) {
            txtGL.setText("0");
            txtGR.setText("0");
            txtGA.setText("0");
        } else {
            int GL = 0;
            int GR = 0;
            int GA = 0;
            for (int i = 0; i < filas; i++) {
                if (tbGastos.getModel().getValueAt(i, 4).equals("LOCAL")) {
                    GL += Integer.parseInt(String.valueOf(dlgGestGastos.tbGastos.getModel().getValueAt(i, 6)).replace(".", "").replace(",", ""));
                    txtGL.setText(df.format(GL));
                } else if (tbGastos.getModel().getValueAt(i, 4).equals("REPARTO")) {
                    GR += Integer.parseInt(String.valueOf(dlgGestGastos.tbGastos.getModel().getValueAt(i, 6)).replace(".", "").replace(",", ""));
                    txtGR.setText(df.format(GR));
                } else if (tbGastos.getModel().getValueAt(i, 4).equals("ADMINISTRATIVO")) {
                    GA += (int) Integer.parseInt(String.valueOf(dlgGestGastos.tbGastos.getModel().getValueAt(i, 6)).replace(".", "").replace(",", ""));
                    txtGA.setText(df.format(GA));
                }
            }
        }
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
        jPanel2 = new javax.swing.JPanel();
        btnNuevo = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnAnular = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        txtFechaFinal = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbGastos = new javax.swing.JTable()
        {
            public boolean isCellEditable(int rowInddex, int celIndex)
            {
                return false;
            }
        };
        jLabel1 = new javax.swing.JLabel();
        dcFecha = new datechooser.beans.DateChooserCombo();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtGL = new javax.swing.JTextField();
        txtGR = new javax.swing.JTextField();
        txtGA = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);

        Blanco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/fondoBlanco.jpg"))); // NOI18N
        Blanco.setPreferredSize(new java.awt.Dimension(690, 418));

        Oscuro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/0-102-102.png"))); // NOI18N
        Oscuro.setPreferredSize(new java.awt.Dimension(690, 418));

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)));
        jPanel2.setLayout(new java.awt.GridLayout(1, 0));

        btnNuevo.setFont(new java.awt.Font("Roboto", 1, 9)); // NOI18N
        btnNuevo.setForeground(new java.awt.Color(0, 102, 102));
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/AgregarGastos - copia.png"))); // NOI18N
        btnNuevo.setText("AGREGAR");
        btnNuevo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnNuevo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        jPanel2.add(btnNuevo);

        btnActualizar.setFont(new java.awt.Font("Roboto", 1, 9)); // NOI18N
        btnActualizar.setForeground(new java.awt.Color(0, 102, 102));
        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/ActualizarGastos - copia.png"))); // NOI18N
        btnActualizar.setText("ACTUALIZAR");
        btnActualizar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnActualizar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });
        jPanel2.add(btnActualizar);

        btnAnular.setFont(new java.awt.Font("Roboto", 1, 9)); // NOI18N
        btnAnular.setForeground(new java.awt.Color(0, 102, 102));
        btnAnular.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/AnularGastos - copia.png"))); // NOI18N
        btnAnular.setText("ANULAR");
        btnAnular.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAnular.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAnular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnularActionPerformed(evt);
            }
        });
        jPanel2.add(btnAnular);

        jButton3.setFont(new java.awt.Font("Roboto", 1, 9)); // NOI18N
        jButton3.setForeground(new java.awt.Color(0, 102, 102));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/back30.png"))); // NOI18N
        jButton3.setText("SALIR");
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton3);

        javax.swing.GroupLayout OscuroLayout = new javax.swing.GroupLayout(Oscuro);
        Oscuro.setLayout(OscuroLayout);
        OscuroLayout.setHorizontalGroup(
            OscuroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OscuroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtFechaFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        OscuroLayout.setVerticalGroup(
            OscuroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OscuroLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(OscuroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtFechaFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jScrollPane1.setOpaque(false);

        tbGastos.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        tbGastos.setModel(new javax.swing.table.DefaultTableModel(
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
        tbGastos.setGridColor(new java.awt.Color(204, 204, 204));
        tbGastos.setRowHeight(20);
        tbGastos.setShowGrid(true);
        tbGastos.setShowVerticalLines(false);
        tbGastos.getTableHeader().setResizingAllowed(false);
        tbGastos.getTableHeader().setReorderingAllowed(false);
        tbGastos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbGastosMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbGastosMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tbGastos);

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 15)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("GASTOS DE LA FECHA");

        dcFecha.setCurrentView(new datechooser.view.appearance.AppearancesList("Grey",
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
    dcFecha.setCalendarBackground(new java.awt.Color(255, 255, 255));
    dcFecha.setCalendarPreferredSize(new java.awt.Dimension(300, 180));
    dcFecha.setFieldFont(new java.awt.Font("Roboto", java.awt.Font.BOLD, 15));
    dcFecha.setNavigateFont(new java.awt.Font("Times New Roman", java.awt.Font.PLAIN, 14));
    dcFecha.addCommitListener(new datechooser.events.CommitListener() {
        public void onCommit(datechooser.events.CommitEvent evt) {
            dcFechaOnCommit(evt);
        }
    });

    jPanel1.setBackground(new java.awt.Color(255, 255, 255));
    jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)));
    jPanel1.setOpaque(false);

    jLabel2.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
    jLabel2.setText("VALOR TOTAL DE GASTOS EN  SALÓN:");

    jLabel3.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
    jLabel3.setText("VALOR TOTAL DE GASTOS EN REPARTOS:");

    jLabel4.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
    jLabel4.setText("VALOR TOTAL DE GASTOS ADMINISTRATIVOS:");

    txtGL.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
    txtGL.setHorizontalAlignment(javax.swing.JTextField.CENTER);
    txtGL.setBorder(null);
    txtGL.setOpaque(false);

    txtGR.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
    txtGR.setHorizontalAlignment(javax.swing.JTextField.CENTER);
    txtGR.setBorder(null);
    txtGR.setOpaque(false);

    txtGA.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
    txtGA.setHorizontalAlignment(javax.swing.JTextField.CENTER);
    txtGA.setBorder(null);
    txtGA.setOpaque(false);

    jLabel5.setBackground(new java.awt.Color(0, 102, 102));
    jLabel5.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
    jLabel5.setForeground(new java.awt.Color(255, 255, 255));
    jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel5.setText("RESUMEN DE GASTOS TOTALES POR ORIGEN");
    jLabel5.setOpaque(true);

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(txtGL, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtGR, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtGA, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );
    jPanel1Layout.setVerticalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtGL, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtGR, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtGA, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap())
    );

    javax.swing.GroupLayout BlancoLayout = new javax.swing.GroupLayout(Blanco);
    Blanco.setLayout(BlancoLayout);
    BlancoLayout.setHorizontalGroup(
        BlancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(Oscuro, javax.swing.GroupLayout.DEFAULT_SIZE, 789, Short.MAX_VALUE)
        .addGroup(BlancoLayout.createSequentialGroup()
            .addGroup(BlancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(BlancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 779, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(BlancoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dcFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)))
                .addGroup(BlancoLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    BlancoLayout.setVerticalGroup(
        BlancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(BlancoLayout.createSequentialGroup()
            .addComponent(Oscuro, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(BlancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(dcFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(Blanco, javax.swing.GroupLayout.PREFERRED_SIZE, 779, javax.swing.GroupLayout.PREFERRED_SIZE)
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addComponent(Blanco, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 0, Short.MAX_VALUE))
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        // TODO add your handling code here:
        actualizar();
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnAnularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnularActionPerformed
        // TODO add your handling code here:
        delArticulo();
    }//GEN-LAST:event_btnAnularActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        int rpta = Mensajes.confirmar("¿Seguro que desea salir del formulario?");
        if (rpta == 0) {
            this.dispose();
        }

    }//GEN-LAST:event_jButton3ActionPerformed

    private void tbGastosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbGastosMouseClicked
        // TODO add your handling code here:
        /*try {
            CabecerasTablas.limpiarTablas(tbDetalleCompra);
            controlCompra.listarDetalleCompras(tbDetalleCompra);
            RendersD();
        } catch (Exception e) {
            Mensajes.error(e.toString());
        }*/
    }//GEN-LAST:event_tbGastosMouseClicked

    private void tbGastosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbGastosMousePressed
        // TODO add your handling code here:
 /*       try {
            CabecerasTablas.limpiarTablasconsDetalleCompras(tbDetalleCompra);
            controlCompra.listarDetalleCompras(tbDetalleCompra);
            RendersD();
        } catch (Exception e) {
            Mensajes.error(e.toString());
        }*/
    }//GEN-LAST:event_tbGastosMousePressed

    private void dcFechaOnCommit(datechooser.events.CommitEvent evt) {//GEN-FIRST:event_dcFechaOnCommit
        // TODO add your handling code here:
        txtFechaFinal.setText(Fecha.formatoFecha(dcFecha.getText()));
        btnActualizarActionPerformed(null);
        //CabecerasTablas.limpiarTablasGastos(tbGastos);
        //ControlGasto.listarGastos(tbGastos, txtFechaFinal.getText().trim());
        //Renders();
        //calcularValores();
        
    }//GEN-LAST:event_dcFechaOnCommit

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here:
        try {
            String fe = generarCodigos.getFecha("SELECT ca_fechainicio FROM caja where ca_indicador='S' ORDER BY ca_id DESC LIMIT 1");
            if (!fe.equals(Fecha.fechaCorrecta())) {
                Mensajes.informacion("La caja del día aún no ha sido inicializada.\n\nPara registrar pagos de servicios u otros egresos sera necesario hacerlo.\nLa apertura puede realizarse con los perfiles ADMINISTRADOR y VENTAS.");
            } else {
                dlgGastos gastos = new dlgGastos(null, true);
                gastos.setLocationRelativeTo(this);
                gastos.setVisible(true);   
                
                
            }
        } catch (Exception e) {
            Mensajes.informacion("Servidor no esta activo: "+e.getMessage());
        }
    }//GEN-LAST:event_btnNuevoActionPerformed

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
            java.util.logging.Logger.getLogger(dlgGestGastos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(dlgGestGastos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(dlgGestGastos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(dlgGestGastos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                dlgGestGastos dialog = new dlgGestGastos(new javax.swing.JFrame(), true);
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
    private org.edisoncor.gui.panel.PanelImage Oscuro;
    public static javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAnular;
    private javax.swing.JButton btnNuevo;
    private datechooser.beans.DateChooserCombo dcFecha;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable tbGastos;
    public static javax.swing.JTextField txtFechaFinal;
    public static javax.swing.JTextField txtGA;
    public static javax.swing.JTextField txtGL;
    public static javax.swing.JTextField txtGR;
    // End of variables declaration//GEN-END:variables
}