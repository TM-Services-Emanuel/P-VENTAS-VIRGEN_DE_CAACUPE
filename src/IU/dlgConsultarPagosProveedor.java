package IU;

import Componentes.Mensajes;
import Componentes.RenderDecimal2;
import Componentes.RenderDecimal1;
import Componentes.Software;
import Componentes.cargarComboBox;
import Controladores.CabecerasTablas;
import Controladores.controlCompra;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;

public class dlgConsultarPagosProveedor extends javax.swing.JDialog {

    CabecerasTablas cabe = new CabecerasTablas();

    public dlgConsultarPagosProveedor(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        titulo();
        cabe.consPagosProveedor(tbPagosProveedor);
        cabe.consDetallePagosProveedor(tbDetallePagosProveedor);
        controlCompra.listarPagosCompras(tbPagosProveedor);
        cargarComboBox.cargar(cbProveedores, "SELECT pro_codigo, pro_razonsocial FROM proveedor WHERE pro_indicador='S'");
        Renders();
        txtCodProveedor.setVisible(false);

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

    final void titulo() {
        if (Software.getSoftware().equals("null")) {
            this.setTitle("Gestión de pagos realizados");
        } else {
            this.setTitle(Software.getSoftware() + " - Gestión de pagos realizados");
        }
    }

    public static void Renders() {
        dlgConsultarPagosProveedor.tbPagosProveedor.getColumnModel().getColumn(6).setCellRenderer(new RenderDecimal2());
        //dlgConsultarPagosProveedor.tbPagosProveedor.getColumnModel().getColumn(13).setCellRenderer(new RenderPagos());
        //dlgConsultarPagosProveedor.tbPagosProveedor.getColumnModel().getColumn(14).setCellRenderer(new RenderDecimal2());
    }

    public static void RendersD() {
        dlgConsultarPagosProveedor.tbDetallePagosProveedor.getColumnModel().getColumn(6).setCellRenderer(new RenderDecimal1());
        dlgConsultarPagosProveedor.tbDetallePagosProveedor.getColumnModel().getColumn(7).setCellRenderer(new RenderDecimal2());
        dlgConsultarPagosProveedor.tbDetallePagosProveedor.getColumnModel().getColumn(9).setCellRenderer(new RenderDecimal1());
        //dlgConsultarPagosProveedor.tbDetallePagosProveedor.getColumnModel().getColumn(8).setCellRenderer(new RenderDecimalconPuntos());

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Blanco = new org.edisoncor.gui.panel.PanelImage();
        Oscuro = new org.edisoncor.gui.panel.PanelImage();
        btnSalir = new RSMaterialComponent.RSButtonIconOne();
        txtCodProveedor = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        PanelContenedor1 = new rojeru_san.rspanel.RSPanelImage();
        btnActualizar = new RSMaterialComponent.RSButtonIconUno();
        Separador1 = new javax.swing.JSeparator();
        LabelTitulo1 = new javax.swing.JLabel();
        PanelContenedor = new rojeru_san.rspanel.RSPanelImage();
        btnAnular = new RSMaterialComponent.RSButtonIconUno();
        Separador = new javax.swing.JSeparator();
        LabelTitulo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbPagosProveedor = new javax.swing.JTable()
        {
            public boolean isCellEditable(int rowInddex, int celIndex)
            {
                return false;
            }
        };
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbDetallePagosProveedor = new javax.swing.JTable()
        {
            public boolean isCellEditable(int rowInddex, int celIndex)
            {
                return false;
            }
        };
        btnFiltrar = new javax.swing.JButton();
        ckHabilitar = new rojerusan.RSCheckBox();
        cbProveedores = new RSMaterialComponent.RSComboBox();

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

        Oscuro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/0-102-102.png"))); // NOI18N
        Oscuro.setPreferredSize(new java.awt.Dimension(690, 418));
        Oscuro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                OscuroKeyPressed(evt);
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

        jPanel4.setOpaque(false);
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PanelContenedor1.setImagen(new javax.swing.ImageIcon(getClass().getResource("/Recursos/CONTENEDOR2.png"))); // NOI18N
        PanelContenedor1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PanelContenedor1KeyPressed(evt);
            }
        });

        btnActualizar.setBackground(new java.awt.Color(255, 255, 255));
        btnActualizar.setBackgroundHover(new java.awt.Color(0, 153, 153));
        btnActualizar.setForegroundText(new java.awt.Color(0, 153, 153));
        btnActualizar.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.UPDATE);
        btnActualizar.setRippleColor(java.awt.Color.white);
        btnActualizar.setTypeBorder(RSMaterialComponent.RSButtonIconUno.TYPEBORDER.CIRCLE);
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });
        btnActualizar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnActualizarKeyPressed(evt);
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

        PanelContenedor.setImagen(new javax.swing.ImageIcon(getClass().getResource("/Recursos/CONTENEDOR2.png"))); // NOI18N
        PanelContenedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PanelContenedorKeyPressed(evt);
            }
        });

        btnAnular.setBackground(new java.awt.Color(255, 255, 255));
        btnAnular.setBackgroundHover(new java.awt.Color(51, 51, 51));
        btnAnular.setEnabled(false);
        btnAnular.setForegroundText(new java.awt.Color(51, 51, 51));
        btnAnular.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.DELETE);
        btnAnular.setRippleColor(java.awt.Color.white);
        btnAnular.setTypeBorder(RSMaterialComponent.RSButtonIconUno.TYPEBORDER.CIRCLE);
        btnAnular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnularActionPerformed(evt);
            }
        });
        btnAnular.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnAnularKeyPressed(evt);
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

        jPanel4.add(PanelContenedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(102, 3, 100, -1));

        javax.swing.GroupLayout OscuroLayout = new javax.swing.GroupLayout(Oscuro);
        Oscuro.setLayout(OscuroLayout);
        OscuroLayout.setHorizontalGroup(
            OscuroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OscuroLayout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62)
                .addComponent(txtCodProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 655, Short.MAX_VALUE)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        OscuroLayout.setVerticalGroup(
            OscuroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OscuroLayout.createSequentialGroup()
                .addGroup(OscuroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(OscuroLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(OscuroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCodProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        Blanco.add(Oscuro, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 1, 989, 99));

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jScrollPane1.setOpaque(false);

        tbPagosProveedor.setFont(new java.awt.Font("Roboto", 0, 10)); // NOI18N
        tbPagosProveedor.setModel(new javax.swing.table.DefaultTableModel(
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
        tbPagosProveedor.setGridColor(new java.awt.Color(204, 204, 204));
        tbPagosProveedor.setRowHeight(20);
        tbPagosProveedor.setShowGrid(true);
        tbPagosProveedor.setShowVerticalLines(false);
        tbPagosProveedor.getTableHeader().setResizingAllowed(false);
        tbPagosProveedor.getTableHeader().setReorderingAllowed(false);
        tbPagosProveedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPagosProveedorMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbPagosProveedorMousePressed(evt);
            }
        });
        tbPagosProveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbPagosProveedorKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tbPagosProveedor);

        Blanco.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 134, 989, 210));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), "Detalle de facturas vinculadas al pago seleccionado", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Roboto", 1, 11), new java.awt.Color(0, 102, 102))); // NOI18N
        jPanel1.setOpaque(false);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jScrollPane2.setOpaque(false);

        tbDetallePagosProveedor.setFont(new java.awt.Font("Roboto", 0, 10)); // NOI18N
        tbDetallePagosProveedor.setModel(new javax.swing.table.DefaultTableModel(
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
        tbDetallePagosProveedor.setEnabled(false);
        tbDetallePagosProveedor.setGridColor(new java.awt.Color(204, 204, 204));
        tbDetallePagosProveedor.setRowHeight(20);
        tbDetallePagosProveedor.setShowGrid(true);
        tbDetallePagosProveedor.setShowVerticalLines(false);
        tbDetallePagosProveedor.getTableHeader().setResizingAllowed(false);
        tbDetallePagosProveedor.getTableHeader().setReorderingAllowed(false);
        tbDetallePagosProveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbDetallePagosProveedorKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(tbDetallePagosProveedor);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 18, 972, 245));

        Blanco.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 355, 982, 270));

        btnFiltrar.setText("FITRAR FACTURAS");
        btnFiltrar.setEnabled(false);
        btnFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrarActionPerformed(evt);
            }
        });
        btnFiltrar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnFiltrarKeyPressed(evt);
            }
        });
        Blanco.add(btnFiltrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(511, 105, -1, -1));

        ckHabilitar.setForeground(new java.awt.Color(0, 0, 0));
        ckHabilitar.setText("Habilitar filtro por proveedor");
        ckHabilitar.setColorCheck(new java.awt.Color(0, 102, 102));
        ckHabilitar.setColorUnCheck(new java.awt.Color(51, 51, 51));
        ckHabilitar.setFocusPainted(false);
        ckHabilitar.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        ckHabilitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckHabilitarActionPerformed(evt);
            }
        });
        ckHabilitar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ckHabilitarKeyPressed(evt);
            }
        });
        Blanco.add(ckHabilitar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 105, -1, 23));

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
        Blanco.add(cbProveedores, new org.netbeans.lib.awtextra.AbsoluteConstraints(192, 105, 309, 23));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Blanco, javax.swing.GroupLayout.DEFAULT_SIZE, 991, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Blanco, javax.swing.GroupLayout.PREFERRED_SIZE, 630, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbPagosProveedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPagosProveedorMouseClicked
        // TODO add your handling code here:
        /*try {
            CabecerasTablas.limpiarTablas(tbDetalleCompra);
            controlCompra.listarDetalleCompras(tbDetalleCompra);
            RendersD();
        } catch (Exception e) {
            Mensajes.error(e.toString());
        }*/
    }//GEN-LAST:event_tbPagosProveedorMouseClicked

    private void tbPagosProveedorMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPagosProveedorMousePressed
        // TODO add your handling code here:
        try {
            CabecerasTablas.limpiarTablas(tbDetallePagosProveedor);
            controlCompra.listarDetallePagosCompras(tbDetallePagosProveedor);
            RendersD();
        } catch (Exception e) {
            Mensajes.error(e.toString());
        }
    }//GEN-LAST:event_tbPagosProveedorMousePressed

    private void btnFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarActionPerformed
        // TODO add your handling code here:
        if (txtCodProveedor.getText().trim().isEmpty()) {
            Mensajes.Sistema("No es posible cargar la tabla.\nSeleccione el proveedor para inicial el filtrado de las facturas.");
        } else {
            CabecerasTablas.limpiarTablas(tbPagosProveedor);
            CabecerasTablas.limpiarTablas(tbDetallePagosProveedor);
            cabe.consPagosProveedor(tbPagosProveedor);
            cabe.consDetallePagosProveedor(tbDetallePagosProveedor);
            controlCompra.listarPaposComprasFiltro(tbPagosProveedor, Integer.parseInt(txtCodProveedor.getText().trim()));
            Renders();
        }

    }//GEN-LAST:event_btnFiltrarActionPerformed

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

    private void ckHabilitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckHabilitarActionPerformed
        // TODO add your handling code here:
        if (ckHabilitar.isSelected()) {
            cbProveedores.setEnabled(true);
            btnFiltrar.setEnabled(true);
            btnActualizar.setEnabled(false);
        } else {
            btnActualizar.setEnabled(true);
            cbProveedores.setEnabled(false);
            btnFiltrar.setEnabled(false);
            btnActualizar.doClick();
        }
    }//GEN-LAST:event_ckHabilitarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        // TODO add your handling code here:
        CabecerasTablas.limpiarTablas(tbPagosProveedor);
        CabecerasTablas.limpiarTablas(tbDetallePagosProveedor);
        cabe.consPagosProveedor(tbPagosProveedor);
        cabe.consDetallePagosProveedor(tbDetallePagosProveedor);
        controlCompra.listarPagosCompras(tbPagosProveedor);
        cargarComboBox.cargar(cbProveedores, "SELECT pro_codigo, pro_razonsocial FROM proveedor WHERE pro_indicador='S'");
        Renders();
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnAnularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnularActionPerformed
        // TODO add your handling code here:
        if (dlgConsultarPagosProveedor.tbPagosProveedor.getSelectedRow() < 0) {
            Mensajes.error("Seleccione una fila de la tabla");
        } else {
            int x = dlgConsultarPagosProveedor.tbPagosProveedor.getSelectedRow();
            String estado = dlgConsultarPagosProveedor.tbPagosProveedor.getValueAt(x, 7).toString();
            if (estado.equals("ANULADO")) {
                Mensajes.informacion("Este Pago ya fue anulado");
            } else {
                    try {
                        String msg;
                        int rpta = Mensajes.confirmar("Seguro que desea Anular este Pago?");
                        if (rpta == 0) {
                            msg = controlCompra.anularCompra();
                            if (msg == null) {
                                if (ckHabilitar.isSelected()) {
                                    if (txtCodProveedor.getText().trim().isEmpty()) {
                                        Mensajes.Sistema("No es posible cargar la tabla.\nSeleccione el proveedor para inicial el filtrado de las facturas.");
                                    } else {
                                        CabecerasTablas.limpiarTablas(tbPagosProveedor);
                                        CabecerasTablas.limpiarTablasconsDetalleCompras(tbDetallePagosProveedor);
                                        cabe.consCompras(tbPagosProveedor);
                                        CabecerasTablas.consDetalleCompras(tbDetallePagosProveedor);
                                        controlCompra.listarComprasFiltro(tbPagosProveedor, Integer.parseInt(txtCodProveedor.getText().trim()));
                                        //cargarComboBox.cargar(cbProveedores, "SELECT pro_codigo, pro_razonsocial FROM proveedor WHERE pro_indicador='S'");
                                        Renders();
                                    }

                                } else {
                                    CabecerasTablas.limpiarTablas(tbPagosProveedor);
                                    CabecerasTablas.limpiarTablasconsDetalleCompras(tbDetallePagosProveedor);
                                    cabe.consCompras(tbPagosProveedor);
                                    CabecerasTablas.consDetalleCompras(tbDetallePagosProveedor);
                                    controlCompra.listarCompras(tbPagosProveedor);
                                    //cargarComboBox.cargar(cbProveedores, "SELECT pro_codigo, pro_razonsocial FROM proveedor WHERE pro_indicador='S'");
                                    Renders();
                                }

                            }
                        }
                    } catch (Exception e) {
                        Mensajes.informacion("Seleccione la fila a eliminar:" + e.getMessage());
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

    private void btnActualizarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnActualizarKeyPressed
        // TODO add your handling code here:
         AccesoRapido(evt.getKeyCode());
    }//GEN-LAST:event_btnActualizarKeyPressed

    private void PanelContenedor1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PanelContenedor1KeyPressed
        // TODO add your handling code here:
         AccesoRapido(evt.getKeyCode());
    }//GEN-LAST:event_PanelContenedor1KeyPressed

    private void btnAnularKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnAnularKeyPressed
        // TODO add your handling code here:
         AccesoRapido(evt.getKeyCode());
    }//GEN-LAST:event_btnAnularKeyPressed

    private void PanelContenedorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PanelContenedorKeyPressed
        // TODO add your handling code here:
         AccesoRapido(evt.getKeyCode());
    }//GEN-LAST:event_PanelContenedorKeyPressed

    private void OscuroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_OscuroKeyPressed
        // TODO add your handling code here:
         AccesoRapido(evt.getKeyCode());
    }//GEN-LAST:event_OscuroKeyPressed

    private void ckHabilitarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ckHabilitarKeyPressed
        // TODO add your handling code here:
         AccesoRapido(evt.getKeyCode());
    }//GEN-LAST:event_ckHabilitarKeyPressed

    private void btnFiltrarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnFiltrarKeyPressed
        // TODO add your handling code here:
         AccesoRapido(evt.getKeyCode());
    }//GEN-LAST:event_btnFiltrarKeyPressed

    private void tbPagosProveedorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPagosProveedorKeyPressed
        // TODO add your handling code here:
         AccesoRapido(evt.getKeyCode());
    }//GEN-LAST:event_tbPagosProveedorKeyPressed

    private void tbDetallePagosProveedorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbDetallePagosProveedorKeyPressed
        // TODO add your handling code here:
         AccesoRapido(evt.getKeyCode());
    }//GEN-LAST:event_tbDetallePagosProveedorKeyPressed

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
            java.util.logging.Logger.getLogger(dlgConsultarPagosProveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(dlgConsultarPagosProveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(dlgConsultarPagosProveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(dlgConsultarPagosProveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                dlgConsultarPagosProveedor dialog = new dlgConsultarPagosProveedor(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel LabelTitulo;
    private javax.swing.JLabel LabelTitulo1;
    private org.edisoncor.gui.panel.PanelImage Oscuro;
    private rojeru_san.rspanel.RSPanelImage PanelContenedor;
    private rojeru_san.rspanel.RSPanelImage PanelContenedor1;
    private javax.swing.JSeparator Separador;
    private javax.swing.JSeparator Separador1;
    private RSMaterialComponent.RSButtonIconUno btnActualizar;
    private RSMaterialComponent.RSButtonIconUno btnAnular;
    private javax.swing.JButton btnFiltrar;
    private RSMaterialComponent.RSButtonIconOne btnSalir;
    public static RSMaterialComponent.RSComboBox cbProveedores;
    private rojerusan.RSCheckBox ckHabilitar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JTable tbDetallePagosProveedor;
    public static javax.swing.JTable tbPagosProveedor;
    public static javax.swing.JTextField txtCodProveedor;
    // End of variables declaration//GEN-END:variables
}
