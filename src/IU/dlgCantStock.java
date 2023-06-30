package IU;

import Componentes.Fecha;
import Componentes.Mensajes;
import Componentes.cargarComboBox;
import Componentes.validarCampos;
import Controladores.CabecerasTablas;
import Controladores.controlArticuloMovil;
import Controladores.controlGestStock;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;

public class dlgCantStock extends javax.swing.JDialog {

    public dlgCantStock(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Iconos/logo1.png")));
        cargarComboBox.cargar(cbMotivo, "SELECT * FROM motivo WHERE mot_indicador='S'");
        lbfechahora.setText(Fecha.fechaCorrectaFachada());
        lblCodA.setVisible(false);
        txtCodMov.setVisible(false);
        txtStock.requestFocus();
    }

    public void actualizar() {

        CabecerasTablas.ajusteStock(dlgAjusteStock.tbDetalle);
        CabecerasTablas.limpiarTablasAJSK(dlgAjusteStock.tbDetalle);
        controlArticuloMovil.listArticulo(dlgAjusteStock.tbDetalle, "idproducto");
        dlgAjusteStock.Renders();
        dlgAjusteStock.txtBuscar.requestFocus();
        dlgAjusteStock.txtBuscar.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        lbfechahora = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lblCodA = new javax.swing.JLabel();
        lblDesc = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblStA = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtStock = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cbMotivo = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtObs = new javax.swing.JTextArea();
        btnMotivo = new javax.swing.JButton();
        txtCodMov = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        itemGuardar = new javax.swing.JMenuItem();
        itemCancelar = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Modificación de Stock");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(0, 102, 102));

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)));
        jPanel4.setLayout(new java.awt.GridLayout(1, 0));

        btnGuardar.setFont(new java.awt.Font("Roboto", 1, 9)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(0, 102, 102));
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/save30.png"))); // NOI18N
        btnGuardar.setText("GUARDAR");
        btnGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnGuardar.setMaximumSize(new java.awt.Dimension(71, 57));
        btnGuardar.setMinimumSize(new java.awt.Dimension(71, 57));
        btnGuardar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel4.add(btnGuardar);

        btnCancelar.setFont(new java.awt.Font("Roboto", 1, 9)); // NOI18N
        btnCancelar.setForeground(new java.awt.Color(0, 102, 102));
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/cancel30.png"))); // NOI18N
        btnCancelar.setText("CANCEL");
        btnCancelar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCancelar.setMaximumSize(new java.awt.Dimension(75, 57));
        btnCancelar.setMinimumSize(new java.awt.Dimension(75, 57));
        btnCancelar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel4.add(btnCancelar);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        lbfechahora.setFont(new java.awt.Font("Roboto", 1, 10)); // NOI18N
        lbfechahora.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbfechahora.setText("jLabel6");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        lblCodA.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lblCodA.setText("c");

        lblDesc.setFont(new java.awt.Font("Roboto", 1, 19)); // NOI18N
        lblDesc.setForeground(new java.awt.Color(0, 102, 102));
        lblDesc.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDesc.setText("jLabel2");

        jLabel3.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        jLabel3.setText("STOCK ACTUAL:");

        lblStA.setBackground(new java.awt.Color(255, 255, 255));
        lblStA.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        lblStA.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblStA.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        lblStA.setOpaque(true);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDesc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblStA, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addComponent(lblCodA, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCodA, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblStA, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(5, 5, 5))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel5.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        jLabel5.setText("Nuevo Stock");

        txtStock.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N
        txtStock.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtStock.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        txtStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtStockActionPerformed(evt);
            }
        });
        txtStock.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtStockKeyPressed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        jLabel2.setText("Motivo de Ajuste");

        cbMotivo.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        cbMotivo.setForeground(new java.awt.Color(0, 102, 102));
        cbMotivo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        cbMotivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbMotivoActionPerformed(evt);
            }
        });
        cbMotivo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbMotivoKeyPressed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        jLabel4.setText("Observación");

        txtObs.setColumns(20);
        txtObs.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N
        txtObs.setRows(3);
        txtObs.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtObsKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtObsKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(txtObs);

        btnMotivo.setBackground(new java.awt.Color(255, 255, 255));
        btnMotivo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/document15.png"))); // NOI18N
        btnMotivo.setToolTipText("Gestionar Ciudad");
        btnMotivo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        btnMotivo.setBorderPainted(false);
        btnMotivo.setContentAreaFilled(false);
        btnMotivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMotivoActionPerformed(evt);
            }
        });

        txtCodMov.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbMotivo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 160, Short.MAX_VALUE)
                                .addComponent(txtCodMov, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 496, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtCodMov, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lbfechahora, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(lbfechahora)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jMenuBar1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jMenu1.setText("Opciones");
        jMenu1.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        jMenu1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        itemGuardar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F6, 0));
        itemGuardar.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        itemGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/save15.png"))); // NOI18N
        itemGuardar.setText("Guardar Nuevo");
        itemGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemGuardarActionPerformed(evt);
            }
        });
        jMenu1.add(itemGuardar);

        itemCancelar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0));
        itemCancelar.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        itemCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/cancel15.png"))); // NOI18N
        itemCancelar.setText("Cancelar");
        itemCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemCancelarActionPerformed(evt);
            }
        });
        jMenu1.add(itemCancelar);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtStockKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStockKeyPressed
        // TODO add your handling code here:
        validarCampos.soloDecimales(txtStock);
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cbMotivo.requestFocus();
            cbMotivo.setPopupVisible(true);
        }
    }//GEN-LAST:event_txtStockKeyPressed

    private void cbMotivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbMotivoActionPerformed
        // TODO add your handling code here:  
        if (cbMotivo.getSelectedIndex() == 0) {
            txtCodMov.setText("");
        } else {
            txtCodMov.setText(String.valueOf(cbMotivo.getSelectedIndex()));
        }
    }//GEN-LAST:event_cbMotivoActionPerformed

    private void btnMotivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMotivoActionPerformed
        // TODO add your handling code here:
        dlgMotivo mo = new dlgMotivo(null, true);
        mo.setLocationRelativeTo(null);
        mo.setVisible(true);
    }//GEN-LAST:event_btnMotivoActionPerformed

    private void txtObsKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtObsKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (Character.isLowerCase(c)) {
            String cad = ("" + c).toUpperCase();
            c = cad.charAt(0);
            evt.setKeyChar(c);
        }
    }//GEN-LAST:event_txtObsKeyTyped

    private void txtStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtStockActionPerformed
        // TODO add your handling code here:
        if (txtStock.getText().isEmpty()) {
            txtStock.setText("0");
        }
    }//GEN-LAST:event_txtStockActionPerformed

    private void cbMotivoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbMotivoKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtObs.requestFocus();
        }
    }//GEN-LAST:event_cbMotivoKeyPressed

    private void txtObsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtObsKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnGuardar.doClick();
        }
    }//GEN-LAST:event_txtObsKeyPressed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_formKeyPressed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowActivated

    private void itemGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemGuardarActionPerformed
        // TODO add your handling code here:
        btnGuardarActionPerformed(null);
    }//GEN-LAST:event_itemGuardarActionPerformed

    private void itemCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemCancelarActionPerformed
        // TODO add your handling code here:
        btnCancelarActionPerformed(null);
    }//GEN-LAST:event_itemCancelarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        if (validarCampos.estaVacio(txtStock) && validarCampos.estaVacio(txtCodMov)) {
            int resp = JOptionPane.showConfirmDialog(this, "¿Seguro que desea Actualizar el Stock de este Articulo?", "Ajustar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (resp == JOptionPane.YES_OPTION) {
                try {
                    controlGestStock.actStock();
                    controlGestStock.addDetalleStock();
                    actualizar();
                    this.dispose();
                } catch (Exception e) {
                }
            }
        } else {
            if (txtStock.getText().isEmpty()) {
                Mensajes.error("Ingrese una cantidad");
                txtStock.requestFocus();
            } else if (txtCodMov.getText().isEmpty()) {
                Mensajes.error("Seleccione Obligatoriamente un Motivo");
                cbMotivo.requestFocus();
            }
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        int rpta = Mensajes.confirmar("¿Seguro que desea Cancelar el Ajuste?");
        if (rpta == 0) {
            actualizar();
            this.dispose();
        }
    }//GEN-LAST:event_btnCancelarActionPerformed

    /*    public JButton[] getButtons()
    {
        return new JButton[]{btnGuardar};
    }
    
    public int getOpcion()
    {
        return opcion;
    }
    
    private void getOptionPane()
    {
        if(op!=null)
        {
            return;
        }
        Component pdr = this.getParent();
        while(pdr.getParent() != null)
        {
            if(pdr instanceof JOptionPane)
            {
                op = (JOptionPane)pdr;
                break;
            }
            pdr = pdr.getParent();
        }
    }
    
    public DetalleSalida getDetalle()
    {
        return detalle;
    }*/
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
            java.util.logging.Logger.getLogger(dlgCantStock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>
        java.awt.EventQueue.invokeLater(() -> {
            dlgCantStock dialog = new dlgCantStock(new javax.swing.JFrame(), true);
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }
            });
            dialog.setVisible(true);
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton btnCancelar;
    public static javax.swing.JButton btnGuardar;
    public static javax.swing.JButton btnMotivo;
    public static javax.swing.JComboBox<String> cbMotivo;
    private javax.swing.JMenuItem itemCancelar;
    private javax.swing.JMenuItem itemGuardar;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbfechahora;
    public static javax.swing.JLabel lblCodA;
    public static javax.swing.JLabel lblDesc;
    public static javax.swing.JLabel lblStA;
    public static javax.swing.JTextField txtCodMov;
    public static javax.swing.JTextArea txtObs;
    public static javax.swing.JTextField txtStock;
    // End of variables declaration//GEN-END:variables
}
