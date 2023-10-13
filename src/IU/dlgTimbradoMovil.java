package IU;

import Componentes.Fecha;
import Componentes.Mensajes;
import Componentes.Software;
import Componentes.validarCampos;
import Controladores.CabecerasTablas;
import Controladores.ControlLogeo;
import Controladores.controlTimbradoMovil;
import Datos.GestionarTimbradoMovil;
import java.awt.HeadlessException;
import javax.swing.JOptionPane;

public class dlgTimbradoMovil extends javax.swing.JDialog {

    CabecerasTablas cabe = new CabecerasTablas();
    public dlgTimbradoMovil(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        titulo();
        cabe.Timbrado(tbTimbrado);
        controlTimbradoMovil.listTimbrado(tbTimbrado);
    }
    
    final void titulo(){
        if(Software.getSoftware().equals("null")){
            this.setTitle("Gestionar Timbrado");
        }else{
            this.setTitle(Software.getSoftware()+" - Gestionar Timbrado");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Blanco = new org.edisoncor.gui.panel.PanelImage();
        Oscuro = new org.edisoncor.gui.panel.PanelImage();
        jPanel1 = new javax.swing.JPanel();
        btnNuevo = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        rSButtonIconOne1 = new RSMaterialComponent.RSButtonIconOne();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtCod = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtTimbrado = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtFDesde = new javax.swing.JTextField();
        dcFDesde = new datechooser.beans.DateChooserCombo();
        txtFHasta = new javax.swing.JTextField();
        cbEstado = new javax.swing.JCheckBox();
        dcFHasta = new datechooser.beans.DateChooserCombo();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtAutorizacion = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtFAutori = new javax.swing.JTextField();
        dcFautori = new datechooser.beans.DateChooserCombo();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbTimbrado = new javax.swing.JTable()
        {
            public boolean isCellEditable(int rowInddex, int celIndex)
            {
                return false;
            }
        };

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        Blanco.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)));
        Blanco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/fondoBlanco.jpg"))); // NOI18N
        Blanco.setPreferredSize(new java.awt.Dimension(690, 418));

        Oscuro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/0-102-102.png"))); // NOI18N
        Oscuro.setPreferredSize(new java.awt.Dimension(690, 418));

        jPanel1.setLayout(new java.awt.GridLayout(1, 9));

        btnNuevo.setFont(new java.awt.Font("Roboto", 1, 9)); // NOI18N
        btnNuevo.setForeground(new java.awt.Color(0, 102, 102));
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/document30.png"))); // NOI18N
        btnNuevo.setText("NUEVO");
        btnNuevo.setToolTipText("Nuevo - F3");
        btnNuevo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnNuevo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        jPanel1.add(btnNuevo);

        btnModificar.setFont(new java.awt.Font("Roboto", 1, 9)); // NOI18N
        btnModificar.setForeground(new java.awt.Color(0, 102, 102));
        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/edit30.png"))); // NOI18N
        btnModificar.setText("MODIF.");
        btnModificar.setToolTipText("Modificar - F5");
        btnModificar.setEnabled(false);
        btnModificar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnModificar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        jPanel1.add(btnModificar);

        btnGuardar.setFont(new java.awt.Font("Roboto", 1, 9)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(0, 102, 102));
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/save30.png"))); // NOI18N
        btnGuardar.setText("GUARDAR");
        btnGuardar.setToolTipText("Guardar - F6");
        btnGuardar.setEnabled(false);
        btnGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnGuardar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel1.add(btnGuardar);

        btnCancelar.setFont(new java.awt.Font("Roboto", 1, 9)); // NOI18N
        btnCancelar.setForeground(new java.awt.Color(0, 102, 102));
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/cancel30.png"))); // NOI18N
        btnCancelar.setText("CANCELAR");
        btnCancelar.setToolTipText("Cancelar - Esc");
        btnCancelar.setEnabled(false);
        btnCancelar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCancelar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancelar);

        btnEliminar.setFont(new java.awt.Font("Roboto", 1, 9)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(0, 102, 102));
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/garbage30.png"))); // NOI18N
        btnEliminar.setText("ELIMINAR");
        btnEliminar.setToolTipText("Eliminar - Supr");
        btnEliminar.setEnabled(false);
        btnEliminar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEliminar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEliminar);

        rSButtonIconOne1.setBackground(new java.awt.Color(0, 102, 102));
        rSButtonIconOne1.setBackgroundHover(new java.awt.Color(255, 0, 0));
        rSButtonIconOne1.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.CLOSE);
        rSButtonIconOne1.setOpaque(true);
        rSButtonIconOne1.setPreferredSize(new java.awt.Dimension(20, 20));
        rSButtonIconOne1.setRequestFocusEnabled(false);
        rSButtonIconOne1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonIconOne1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout OscuroLayout = new javax.swing.GroupLayout(Oscuro);
        Oscuro.setLayout(OscuroLayout);
        OscuroLayout.setHorizontalGroup(
            OscuroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OscuroLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rSButtonIconOne1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        OscuroLayout.setVerticalGroup(
            OscuroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OscuroLayout.createSequentialGroup()
                .addComponent(rSButtonIconOne1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(OscuroLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel2.setOpaque(false);
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setText("ID");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 8, 21, 23));

        txtCod.setEditable(false);
        txtCod.setBackground(new java.awt.Color(255, 255, 255));
        txtCod.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N
        txtCod.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCod.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel2.add(txtCod, new org.netbeans.lib.awtextra.AbsoluteConstraints(47, 8, 39, 23));

        jLabel2.setText("Timbrado");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(104, 8, -1, 23));

        txtTimbrado.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N
        txtTimbrado.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTimbrado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        txtTimbrado.setEnabled(false);
        txtTimbrado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimbradoActionPerformed(evt);
            }
        });
        txtTimbrado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTimbradoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTimbradoKeyTyped(evt);
            }
        });
        jPanel2.add(txtTimbrado, new org.netbeans.lib.awtextra.AbsoluteConstraints(152, 8, 138, 23));

        jLabel4.setText("Desde");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(325, 8, -1, 23));

        txtFDesde.setEditable(false);
        txtFDesde.setBackground(new java.awt.Color(255, 255, 255));
        txtFDesde.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        txtFDesde.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFDesde.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        txtFDesde.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFDesdeActionPerformed(evt);
            }
        });
        jPanel2.add(txtFDesde, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 8, 92, 23));

        dcFDesde.setEnabled(false);
        dcFDesde.addCommitListener(new datechooser.events.CommitListener() {
            public void onCommit(datechooser.events.CommitEvent evt) {
                dcFDesdeOnCommit(evt);
            }
        });
        jPanel2.add(dcFDesde, new org.netbeans.lib.awtextra.AbsoluteConstraints(451, 8, 27, 23));

        txtFHasta.setEditable(false);
        txtFHasta.setBackground(new java.awt.Color(255, 255, 255));
        txtFHasta.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        txtFHasta.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFHasta.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        txtFHasta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFHastaActionPerformed(evt);
            }
        });
        jPanel2.add(txtFHasta, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 38, 92, 23));

        cbEstado.setBackground(new java.awt.Color(0, 102, 102));
        cbEstado.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        cbEstado.setForeground(new java.awt.Color(255, 255, 255));
        cbEstado.setText("ACTIVO");
        cbEstado.setEnabled(false);
        cbEstado.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        cbEstado.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        cbEstado.setIconTextGap(15);
        jPanel2.add(cbEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(302, 68, 178, 23));

        dcFHasta.setEnabled(false);
        dcFHasta.addCommitListener(new datechooser.events.CommitListener() {
            public void onCommit(datechooser.events.CommitEvent evt) {
                dcFHastaOnCommit(evt);
            }
        });
        jPanel2.add(dcFHasta, new org.netbeans.lib.awtextra.AbsoluteConstraints(451, 38, 27, 23));

        jLabel5.setText("Hasta");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(325, 38, -1, 23));

        jLabel6.setText("NR. Autorización");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 38, -1, 23));

        txtAutorizacion.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        txtAutorizacion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAutorizacion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        txtAutorizacion.setEnabled(false);
        jPanel2.add(txtAutorizacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(107, 38, 185, 23));

        jLabel7.setText("Fecha de Autorización");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 68, 110, 23));

        txtFAutori.setEditable(false);
        txtFAutori.setBackground(new java.awt.Color(255, 255, 255));
        txtFAutori.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        txtFAutori.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFAutori.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        txtFAutori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFAutoriActionPerformed(evt);
            }
        });
        jPanel2.add(txtFAutori, new org.netbeans.lib.awtextra.AbsoluteConstraints(174, 68, 92, 23));

        dcFautori.setEnabled(false);
        dcFautori.addCommitListener(new datechooser.events.CommitListener() {
            public void onCommit(datechooser.events.CommitEvent evt) {
                dcFautoriOnCommit(evt);
            }
        });
        jPanel2.add(dcFautori, new org.netbeans.lib.awtextra.AbsoluteConstraints(265, 68, 27, 23));

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        tbTimbrado.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        tbTimbrado.setModel(new javax.swing.table.DefaultTableModel(
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
        tbTimbrado.setGridColor(new java.awt.Color(204, 204, 204));
        tbTimbrado.setRowHeight(20);
        tbTimbrado.setSelectionBackground(new java.awt.Color(0, 102, 102));
        tbTimbrado.setShowGrid(true);
        tbTimbrado.setShowVerticalLines(false);
        tbTimbrado.getTableHeader().setResizingAllowed(false);
        tbTimbrado.getTableHeader().setReorderingAllowed(false);
        tbTimbrado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbTimbradoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbTimbrado);

        javax.swing.GroupLayout BlancoLayout = new javax.swing.GroupLayout(Blanco);
        Blanco.setLayout(BlancoLayout);
        BlancoLayout.setHorizontalGroup(
            BlancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Oscuro, javax.swing.GroupLayout.DEFAULT_SIZE, 508, Short.MAX_VALUE)
            .addGroup(BlancoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jScrollPane1)
        );
        BlancoLayout.setVerticalGroup(
            BlancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BlancoLayout.createSequentialGroup()
                .addComponent(Oscuro, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Blanco, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Blanco, javax.swing.GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here:
        String cod = GestionarTimbradoMovil.getCodigo();
        txtCod.setText(cod);
        btnNuevo.setEnabled(false);
        btnGuardar.setEnabled(true);
        btnCancelar.setEnabled(true);
        txtTimbrado.setEnabled(true);
        txtTimbrado.setText("");
        dcFDesde.setEnabled(true);
        dcFHasta.setEnabled(true);
        cbEstado.setSelected(true);
        CabecerasTablas.limpiarTablas(tbTimbrado);
        controlTimbradoMovil.listTimbrado(tbTimbrado);
        txtTimbrado.requestFocus();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        try{
            int resp = JOptionPane.showConfirmDialog(this,"¿Seguro que desea desactivar el registro?", "Eliminar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (resp == JOptionPane.YES_OPTION){
                controlTimbradoMovil.delTimbrado();
                CabecerasTablas.limpiarTablas(tbTimbrado);
                controlTimbradoMovil.listTimbrado(tbTimbrado);
                btnCancelarActionPerformed(null);
            }
        }catch(HeadlessException ee){}        
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        // TODO add your handling code here:
        try{
            int resp = JOptionPane.showConfirmDialog(this,"¿Seguro que desea modificar el registro?", "Modificar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (resp == JOptionPane.YES_OPTION){
                controlTimbradoMovil.actTimbrado();
                CabecerasTablas.limpiarTablas(tbTimbrado);
                controlTimbradoMovil.listTimbrado(tbTimbrado);
                btnCancelarActionPerformed(null);
            }
        }catch(HeadlessException ee){}        
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        if (validarCampos.estaVacio(txtTimbrado) ) {
            try{
                int resp = JOptionPane.showConfirmDialog(this,"¿Seguro que desea insertar el registro?", "Insertar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (resp == JOptionPane.YES_OPTION){
                    String cod = GestionarTimbradoMovil.getCodigo();
                    txtCod.setText(cod);
                    controlTimbradoMovil.addTimbrado();
                    CabecerasTablas.limpiarTablas(tbTimbrado);
                    controlTimbradoMovil.listTimbrado(tbTimbrado);
                    btnCancelarActionPerformed(null);
                }
            }catch(HeadlessException ee){}            
        }
        else {
            Mensajes.informacion("Debe llenar obligatoriamente los campos Timbrado, Fecha desde y hasta");
            txtTimbrado.requestFocus();
        }
        
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void tbTimbradoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTimbradoMouseClicked
        // TODO add your handling code here:
        btnNuevo.setEnabled(false);
        btnModificar.setEnabled(true);
        btnEliminar.setEnabled(true);
        txtTimbrado.setEnabled(true);
        txtAutorizacion.setEnabled(true);
        dcFDesde.setEnabled(true);
        dcFHasta.setEnabled(true);
        dcFautori.setEnabled(true);
        btnGuardar.setEnabled(false);
        btnCancelar.setEnabled(true);

        int fila = tbTimbrado.getSelectedRow();
        String cod = tbTimbrado.getValueAt(fila, 0).toString();
        String nom = tbTimbrado.getValueAt(fila, 1).toString();
        String desde = tbTimbrado.getValueAt(fila, 2).toString();
        String hasta = tbTimbrado.getValueAt(fila, 3).toString();
        String rn_autorizacion = tbTimbrado.getValueAt(fila, 4).toString();
        String fecha_autorizacion = tbTimbrado.getValueAt(fila, 5).toString();
        String estado = tbTimbrado.getValueAt(fila, 6).toString();

        txtCod.setText(cod);
        txtTimbrado.setText(nom);
        txtFDesde.setText(desde);
        txtFHasta.setText(hasta);
        txtAutorizacion.setText(rn_autorizacion);
        txtFAutori.setText(fecha_autorizacion);
        txtFHasta.setText(hasta);
        if(estado.equals("Activo")){
            cbEstado.setSelected(true);
            cbEstado.setEnabled(false);
        }else{
            cbEstado.setSelected(false);
            cbEstado.setEnabled(true);
        }
        txtTimbrado.requestFocus();
    }//GEN-LAST:event_tbTimbradoMouseClicked

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        btnNuevo.setEnabled(true);
        btnModificar.setEnabled(false);
        btnGuardar.setEnabled(false);
        btnCancelar.setEnabled(false);
        btnEliminar.setEnabled(false);
        txtTimbrado.setEnabled(false);
        dcFDesde.setEnabled(false);
        dcFHasta.setEnabled(false);
        cbEstado.setEnabled(false);
        cbEstado.setSelected(false);
        limpiarCampos();
        tbTimbrado.clearSelection();
        btnNuevo.requestFocus();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void txtTimbradoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimbradoKeyPressed
        // TODO add your handling code here:
        if (!txtTimbrado.getText().trim().isEmpty()) {
            validarCampos.soloNumeros(txtTimbrado);
        }
    }//GEN-LAST:event_txtTimbradoKeyPressed

    private void txtTimbradoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimbradoKeyTyped
        // TODO add your handling code here:
        int limite=10;
        if (txtTimbrado.getText().length() == limite) {
            evt.consume();
        }
        char c=evt.getKeyChar();
        if(Character.isLowerCase(c)){
            String cad=(""+c).toUpperCase();
            c=cad.charAt(0);
            evt.setKeyChar(c);
        }
    }//GEN-LAST:event_txtTimbradoKeyTyped

    private void txtTimbradoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimbradoActionPerformed
        // TODO add your handling code here:
        if(btnGuardar.isEnabled()){
            btnGuardar.doClick();
        }else{
            btnModificar.doClick();
        }
    }//GEN-LAST:event_txtTimbradoActionPerformed

    private void txtFDesdeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFDesdeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFDesdeActionPerformed

    private void dcFDesdeOnCommit(datechooser.events.CommitEvent evt) {//GEN-FIRST:event_dcFDesdeOnCommit
        // TODO add your handling code here:
        //txtFDesde.setText(Fecha.fechaCFormulario(dcFDesde.getText()));
        txtFDesde.setText(Fecha.formatoFechaN(dcFDesde.getText()));
    }//GEN-LAST:event_dcFDesdeOnCommit

    private void txtFHastaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFHastaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFHastaActionPerformed

    private void dcFHastaOnCommit(datechooser.events.CommitEvent evt) {//GEN-FIRST:event_dcFHastaOnCommit
        // TODO add your handling code here:
        txtFHasta.setText(Fecha.formatoFechaN(dcFHasta.getText()));
    }//GEN-LAST:event_dcFHastaOnCommit

    private void txtFAutoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFAutoriActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFAutoriActionPerformed

    private void dcFautoriOnCommit(datechooser.events.CommitEvent evt) {//GEN-FIRST:event_dcFautoriOnCommit
        // TODO add your handling code here:
        txtFAutori.setText(Fecha.formatoFechaN(dcFautori.getText()));
    }//GEN-LAST:event_dcFautoriOnCommit

    private void rSButtonIconOne1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIconOne1ActionPerformed
        // TODO add your handling code here:
        int rpta = Mensajes.confirmar("¿Seguro que desea salir del formulario?");
        if (rpta == 0) {
            ControlLogeo.Timbrado_Ticket();
            this.dispose();
        }
    }//GEN-LAST:event_rSButtonIconOne1ActionPerformed

    void limpiarCampos()
    {
        txtCod.setText("");
        txtTimbrado.setText("");
        txtFDesde.setText("");
        txtFHasta.setText("");
        txtAutorizacion.setText("");
        txtFAutori.setText("");
    }    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(dlgTimbradoMovil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
            dlgTimbradoMovil dialog = new dlgTimbradoMovil(new javax.swing.JFrame(), true);
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
    private org.edisoncor.gui.panel.PanelImage Blanco;
    private org.edisoncor.gui.panel.PanelImage Oscuro;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    public static javax.swing.JCheckBox cbEstado;
    public static datechooser.beans.DateChooserCombo dcFDesde;
    public static datechooser.beans.DateChooserCombo dcFHasta;
    public static datechooser.beans.DateChooserCombo dcFautori;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private RSMaterialComponent.RSButtonIconOne rSButtonIconOne1;
    private javax.swing.JTable tbTimbrado;
    public static javax.swing.JTextField txtAutorizacion;
    public static javax.swing.JTextField txtCod;
    public static javax.swing.JTextField txtFAutori;
    public static javax.swing.JTextField txtFDesde;
    public static javax.swing.JTextField txtFHasta;
    public static javax.swing.JTextField txtTimbrado;
    // End of variables declaration//GEN-END:variables
}
