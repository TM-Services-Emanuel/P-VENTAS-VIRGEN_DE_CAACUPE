package IU;

import Componentes.Mensajes;
import Componentes.Notif;
import Componentes.RenderDecimal2;
import Componentes.RenderDecimal1;
import Componentes.RenderDecimalconPuntos;
import Componentes.RenderPagos;
import Componentes.Software;
import Componentes.cargarComboBox;
import Controladores.CabecerasTablas;
import Controladores.controlCompra;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;

public class dlgConsultarCompras11 extends javax.swing.JDialog {

    CabecerasTablas cabe = new CabecerasTablas();

    public dlgConsultarCompras11(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        titulo();
        cabe.consCompras(tbCompra);
        CabecerasTablas.consDetalleCompras(tbDetalleCompra);
        controlCompra.listarCompras(tbCompra);
        cant();
        DecimalFormat df = new DecimalFormat("#,###");
        //lbSaldo.setText(String.valueOf("DEUDA TOTAL: Gs " + df.format(controlCompra.getSaldoCompras())));
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
            this.setTitle("Gestión de compras realizadas");
        } else {
            this.setTitle(Software.getSoftware() + " - Gestión de compras realizadas");
        }
    }

    public static void Renders() {
        dlgConsultarCompras11.tbCompra.getColumnModel().getColumn(10).setCellRenderer(new RenderDecimal1());
        dlgConsultarCompras11.tbCompra.getColumnModel().getColumn(13).setCellRenderer(new RenderPagos());
        dlgConsultarCompras11.tbCompra.getColumnModel().getColumn(14).setCellRenderer(new RenderDecimal2());
    }

    public static void RendersD() {
        dlgConsultarCompras11.tbDetalleCompra.getColumnModel().getColumn(4).setCellRenderer(new RenderDecimalconPuntos());
        dlgConsultarCompras11.tbDetalleCompra.getColumnModel().getColumn(5).setCellRenderer(new RenderDecimal1());
        dlgConsultarCompras11.tbDetalleCompra.getColumnModel().getColumn(6).setCellRenderer(new RenderDecimal2());
        dlgConsultarCompras11.tbDetalleCompra.getColumnModel().getColumn(8).setCellRenderer(new RenderDecimalconPuntos());

    }

    public static void cant() {
        int total = tbCompra.getRowCount();
        lbCantidad.setText("Se contabilizan: " + String.valueOf(total) + " facturas en la tabla.");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Blanco = new org.edisoncor.gui.panel.PanelImage();
        Oscuro = new org.edisoncor.gui.panel.PanelImage();
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
        btnSalir = new RSMaterialComponent.RSButtonIconOne();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbCompra = new javax.swing.JTable()
        {
            public boolean isCellEditable(int rowInddex, int celIndex)
            {
                return false;
            }
        };
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtCodCompra = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtFechaCompra = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtProveedor = new javax.swing.JTextField();
        lbInformacion = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtmov = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbDetalleCompra = new javax.swing.JTable()
        {
            public boolean isCellEditable(int rowInddex, int celIndex)
            {
                return false;
            }
        };
        lbCantidad = new javax.swing.JLabel();
        btnFiltrar = new javax.swing.JButton();
        cbProveedores = new RSMaterialComponent.RSComboBox();
        ckHabilitar = new rojerusan.RSCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        Blanco.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        Blanco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/fondoBlanco.jpg"))); // NOI18N
        Blanco.setPreferredSize(new java.awt.Dimension(690, 418));
        Blanco.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Oscuro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/0-102-102.png"))); // NOI18N
        Oscuro.setPreferredSize(new java.awt.Dimension(690, 418));
        Oscuro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                OscuroKeyPressed(evt);
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
                .addGap(27, 27, 27)
                .addComponent(btnAnular, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
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

        javax.swing.GroupLayout OscuroLayout = new javax.swing.GroupLayout(Oscuro);
        Oscuro.setLayout(OscuroLayout);
        OscuroLayout.setHorizontalGroup(
            OscuroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, OscuroLayout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(OscuroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(OscuroLayout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(txtCodProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(743, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, OscuroLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        OscuroLayout.setVerticalGroup(
            OscuroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OscuroLayout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(OscuroLayout.createSequentialGroup()
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCodProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Blanco.add(Oscuro, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 1, 1064, 99));

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jScrollPane1.setOpaque(false);

        tbCompra.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        tbCompra.setModel(new javax.swing.table.DefaultTableModel(
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
        tbCompra.setGridColor(new java.awt.Color(204, 204, 204));
        tbCompra.setRowHeight(20);
        tbCompra.setShowGrid(true);
        tbCompra.setShowVerticalLines(false);
        tbCompra.getTableHeader().setResizingAllowed(false);
        tbCompra.getTableHeader().setReorderingAllowed(false);
        tbCompra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbCompraMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbCompraMousePressed(evt);
            }
        });
        tbCompra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbCompraKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tbCompra);

        Blanco.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 134, 1064, 206));

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)));
        jPanel3.setOpaque(false);
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setBackground(new java.awt.Color(0, 102, 102));
        jLabel4.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("INFORMACIÓN");
        jLabel4.setOpaque(true);
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 1, 391, 39));

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 10)); // NOI18N
        jLabel1.setText("COMPRA N°");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(9, 50, -1, -1));

        txtCodCompra.setEditable(false);
        txtCodCompra.setBackground(new java.awt.Color(255, 255, 255));
        txtCodCompra.setFont(new java.awt.Font("Roboto", 0, 10)); // NOI18N
        txtCodCompra.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCodCompra.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        txtCodCompra.setOpaque(false);
        txtCodCompra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodCompraKeyPressed(evt);
            }
        });
        jPanel3.add(txtCodCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 47, 290, 23));

        jLabel2.setFont(new java.awt.Font("Roboto", 1, 10)); // NOI18N
        jLabel2.setText("FECHA/HORA");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(9, 106, -1, -1));

        txtFechaCompra.setEditable(false);
        txtFechaCompra.setBackground(new java.awt.Color(255, 255, 255));
        txtFechaCompra.setFont(new java.awt.Font("Roboto", 0, 10)); // NOI18N
        txtFechaCompra.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFechaCompra.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        txtFechaCompra.setOpaque(false);
        txtFechaCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFechaCompraActionPerformed(evt);
            }
        });
        txtFechaCompra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtFechaCompraKeyPressed(evt);
            }
        });
        jPanel3.add(txtFechaCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 103, 290, 23));

        jLabel3.setFont(new java.awt.Font("Roboto", 1, 10)); // NOI18N
        jLabel3.setText("PROVEEDOR");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(9, 134, -1, -1));

        txtProveedor.setEditable(false);
        txtProveedor.setBackground(new java.awt.Color(255, 255, 255));
        txtProveedor.setFont(new java.awt.Font("Roboto", 0, 10)); // NOI18N
        txtProveedor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtProveedor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        txtProveedor.setOpaque(false);
        txtProveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtProveedorKeyPressed(evt);
            }
        });
        jPanel3.add(txtProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 131, 290, 23));

        lbInformacion.setBackground(new java.awt.Color(0, 102, 102));
        lbInformacion.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        lbInformacion.setForeground(new java.awt.Color(255, 255, 255));
        lbInformacion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbInformacion.setOpaque(true);
        jPanel3.add(lbInformacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 195, 391, 36));

        jLabel5.setFont(new java.awt.Font("Roboto", 1, 10)); // NOI18N
        jLabel5.setText("MOV. DIARIO N°");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(9, 78, -1, -1));

        txtmov.setEditable(false);
        txtmov.setBackground(new java.awt.Color(255, 255, 255));
        txtmov.setFont(new java.awt.Font("Roboto", 0, 10)); // NOI18N
        txtmov.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtmov.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        txtmov.setOpaque(false);
        txtmov.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtmovKeyPressed(evt);
            }
        });
        jPanel3.add(txtmov, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 75, 290, 23));

        jLabel6.setFont(new java.awt.Font("Roboto", 1, 10)); // NOI18N
        jLabel6.setText("TOTAL COMPRA");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(9, 163, -1, -1));

        txtTotal.setEditable(false);
        txtTotal.setBackground(new java.awt.Color(255, 255, 255));
        txtTotal.setFont(new java.awt.Font("Roboto", 0, 10)); // NOI18N
        txtTotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTotal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        txtTotal.setOpaque(false);
        txtTotal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTotalKeyPressed(evt);
            }
        });
        jPanel3.add(txtTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 159, 290, 23));

        Blanco.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 370, -1, -1));

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setOpaque(false);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane2.setOpaque(false);

        tbDetalleCompra.setFont(new java.awt.Font("Roboto", 0, 10)); // NOI18N
        tbDetalleCompra.setModel(new javax.swing.table.DefaultTableModel(
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
        tbDetalleCompra.setEnabled(false);
        tbDetalleCompra.setGridColor(new java.awt.Color(204, 204, 204));
        tbDetalleCompra.setRowHeight(20);
        tbDetalleCompra.setShowGrid(true);
        tbDetalleCompra.setShowVerticalLines(false);
        tbDetalleCompra.getTableHeader().setResizingAllowed(false);
        tbDetalleCompra.getTableHeader().setReorderingAllowed(false);
        tbDetalleCompra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbDetalleCompraKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(tbDetalleCompra);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 5, 647, 244));

        Blanco.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(403, 347, 658, 255));

        lbCantidad.setFont(new java.awt.Font("Roboto", 1, 10)); // NOI18N
        lbCantidad.setText("Se contabilizan x facturas");
        Blanco.add(lbCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 347, 386, -1));

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Blanco, javax.swing.GroupLayout.PREFERRED_SIZE, 1066, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Blanco, javax.swing.GroupLayout.PREFERRED_SIZE, 607, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbCompraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbCompraMouseClicked
        // TODO add your handling code here:
        /*try {
            CabecerasTablas.limpiarTablas(tbDetalleCompra);
            controlCompra.listarDetalleCompras(tbDetalleCompra);
            RendersD();
        } catch (Exception e) {
            Mensajes.error(e.toString());
        }*/
    }//GEN-LAST:event_tbCompraMouseClicked

    private void tbCompraMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbCompraMousePressed
        // TODO add your handling code here:
        try {
            CabecerasTablas.limpiarTablasconsDetalleCompras(tbDetalleCompra);
            controlCompra.listarDetalleCompras(tbDetalleCompra);
            RendersD();
        } catch (Exception e) {
            Mensajes.error(e.toString());
        }
    }//GEN-LAST:event_tbCompraMousePressed

    private void txtFechaCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaCompraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaCompraActionPerformed

    private void btnFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarActionPerformed
        // TODO add your handling code here:
        if (txtCodProveedor.getText().trim().isEmpty()) {
            //Mensajes.Sistema("No es posible cargar la tabla.\nSeleccione el proveedor para inicial el filtrado de las facturas.");
            Notif.NotifyFail("Notificación del sistema", "No es posible cargar la tabla.\nSeleccione el proveedor para inicial el filtrado de las facturas.");
        } else {
            CabecerasTablas.limpiarTablas(tbCompra);
            CabecerasTablas.limpiarTablasconsDetalleCompras(tbDetalleCompra);
            cabe.consCompras(tbCompra);
            CabecerasTablas.consDetalleCompras(tbDetalleCompra);
            controlCompra.listarComprasFiltro(tbCompra, Integer.parseInt(txtCodProveedor.getText().trim()));
            cant();
            DecimalFormat df = new DecimalFormat("#,###");
            Renders();
            txtCodCompra.setText("");
            txtmov.setText("");
            txtFechaCompra.setText("");
            txtProveedor.setText("");
            lbInformacion.setText("");
            txtTotal.setText("");
        }

    }//GEN-LAST:event_btnFiltrarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        // TODO add your handling code here:
        /*CabecerasTablas.limpiarTablas(tbCompra);
        CabecerasTablas.limpiarTablasconsDetalleCompras(tbDetalleCompra);
        cabe.consCompras(tbCompra);
        CabecerasTablas.consDetalleCompras(tbDetalleCompra);
        controlCompra.listarCompras(tbCompra);
        cant();
        DecimalFormat df = new DecimalFormat("#,###");
        cargarComboBox.cargar(cbProveedores, "SELECT pro_codigo, pro_razonsocial FROM proveedor WHERE pro_indicador='S'");
        Renders();
        txtCodCompra.setText("");
        txtmov.setText("");
        txtFechaCompra.setText("");
        txtProveedor.setText("");
        lbInformacion.setText("");
        txtTotal.setText("");*/
        if (ckHabilitar.isSelected()) {
            if (txtCodProveedor.getText().trim().isEmpty()) {
                //Mensajes.Sistema("No es posible cargar la tabla.\nSeleccione el proveedor para inicial el filtrado de las facturas.");
                Notif.NotifyFail("Notificación del sistema", "No es posible cargar la tabla.\nSeleccione el proveedor para inicial el filtrado de las facturas.");
            } else {
                CabecerasTablas.limpiarTablas(tbCompra);
                CabecerasTablas.limpiarTablasconsDetalleCompras(tbDetalleCompra);
                cabe.consCompras(tbCompra);
                CabecerasTablas.consDetalleCompras(tbDetalleCompra);
                controlCompra.listarComprasFiltro(tbCompra, Integer.parseInt(txtCodProveedor.getText().trim()));
                cant();
                DecimalFormat df = new DecimalFormat("#,###");
                Renders();
                Notif.NotifyInformation("Notificación del sistema", "Tabla de compras actualizada!");
            }

        } else {
            CabecerasTablas.limpiarTablas(tbCompra);
            CabecerasTablas.limpiarTablasconsDetalleCompras(tbDetalleCompra);
            cabe.consCompras(tbCompra);
            CabecerasTablas.consDetalleCompras(tbDetalleCompra);
            controlCompra.listarCompras(tbCompra);
            cant();
            DecimalFormat df = new DecimalFormat("#,###");
            Renders();
            Notif.NotifyInformation("Notificación del sistema", "Tabla de compras actualizada!");
        }
        
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnAnularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnularActionPerformed
        // TODO add your handling code here:
        if (dlgConsultarCompras11.tbCompra.getSelectedRow() < 0) {
            //Mensajes.error("Seleccione una fila de la tabla");
            Notif.NotifyFail("Notificación del sistema", "No es posible procesar la petición.\r\nSeleccione en la tabla la compra que desea anular");
        } else {
            int x = dlgConsultarCompras11.tbCompra.getSelectedRow();
            String estado = dlgConsultarCompras11.tbCompra.getValueAt(x, 11).toString();
            String tipo = dlgConsultarCompras11.tbCompra.getValueAt(x, 2).toString();
            if (estado.equals("ANULADO")) {
                Notif.NotifyFail("Notificación del sistema", "No es posible procesar la petición.\r\nEsta compra ya fue anulada");
                //Mensajes.informacion("Esta compra ya fue anulada");
            } else {
                if (tipo.equals("L")) {
                    try {
                        String msg;
                        int rpta = Mensajes.confirmar("Seguro que desea Anular esta Compra Local?");
                        if (rpta == 0) {
                            msg = controlCompra.anularCompra();
                            if (msg == null) {
                                btnActualizarActionPerformed(null);
                            }
                                /*if (ckHabilitar.isSelected()) {
                                    if (txtCodProveedor.getText().trim().isEmpty()) {
                                        Mensajes.Sistema("No es posible cargar la tabla.\nSeleccione el proveedor para inicial el filtrado de las facturas.");
                                    } else {
                                        CabecerasTablas.limpiarTablas(tbCompra);
                                        CabecerasTablas.limpiarTablasconsDetalleCompras(tbDetalleCompra);
                                        cabe.consCompras(tbCompra);
                                        CabecerasTablas.consDetalleCompras(tbDetalleCompra);
                                        controlCompra.listarComprasFiltro(tbCompra, Integer.parseInt(txtCodProveedor.getText().trim()));
                                        cant();
                                        DecimalFormat df = new DecimalFormat("#,###");
                                        Renders();
                                    }

                                } else {
                                    CabecerasTablas.limpiarTablas(tbCompra);
                                    CabecerasTablas.limpiarTablasconsDetalleCompras(tbDetalleCompra);
                                    cabe.consCompras(tbCompra);
                                    CabecerasTablas.consDetalleCompras(tbDetalleCompra);
                                    controlCompra.listarCompras(tbCompra);
                                    cant();
                                    DecimalFormat df = new DecimalFormat("#,###");
                                    Renders();
                                }

                            }*/
                        }
                    } catch (Exception e) {
                        // Mensajes.informacion("Seleccione la fila a eliminar:" + e.getMessage());
                    }

                }
                /*else {
                    try {
                        String msg;
                        int rpta = Mensajes.confirmar("Seguro que desea Anular esta Compra para Reparto?");
                        if (rpta == 0) {
                            msg = controlCompra.anularCompraReparto();
                            if (msg == null) {
                                if (ckHabilitar.isSelected()) {
                                    if (txtCodProveedor.getText().trim().isEmpty()) {
                                        Mensajes.Sistema("No es posible cargar la tabla.\nSeleccione el proveedor para inicial el filtrado de las facturas.");
                                    } else {
                                        CabecerasTablas.limpiarTablas(tbCompra);
                                        CabecerasTablas.limpiarTablasconsDetalleCompras(tbDetalleCompra);
                                        cabe.consCompras(tbCompra);
                                        CabecerasTablas.consDetalleCompras(tbDetalleCompra);
                                        controlCompra.listarComprasFiltro(tbCompra, Integer.parseInt(txtCodProveedor.getText().trim()));;
                                        cant();
                                        DecimalFormat df = new DecimalFormat("#,###");
                                        Renders();
                                    }
                                } else {
                                    CabecerasTablas.limpiarTablas(tbCompra);
                                    CabecerasTablas.limpiarTablasconsDetalleCompras(tbDetalleCompra);
                                    cabe.consCompras(tbCompra);
                                    CabecerasTablas.consDetalleCompras(tbDetalleCompra);
                                    controlCompra.listarCompras(tbCompra);
                                    cant();
                                    DecimalFormat df = new DecimalFormat("#,###");
                                    Renders();
                                }

                            }
                        }
                    } catch (Exception e) {
                        Mensajes.informacion("Seleccione la fila a eliminar:" + e.getMessage());
                    }
                }*/
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

    private void tbCompraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbCompraKeyPressed
        // TODO add your handling code here:
        AccesoRapido(evt.getKeyCode());
    }//GEN-LAST:event_tbCompraKeyPressed

    private void tbDetalleCompraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbDetalleCompraKeyPressed
        // TODO add your handling code here:
        AccesoRapido(evt.getKeyCode());
    }//GEN-LAST:event_tbDetalleCompraKeyPressed

    private void txtCodCompraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodCompraKeyPressed
        // TODO add your handling code here:
        AccesoRapido(evt.getKeyCode());
    }//GEN-LAST:event_txtCodCompraKeyPressed

    private void txtmovKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmovKeyPressed
        // TODO add your handling code here:
        AccesoRapido(evt.getKeyCode());
    }//GEN-LAST:event_txtmovKeyPressed

    private void txtFechaCompraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFechaCompraKeyPressed
        // TODO add your handling code here:
        AccesoRapido(evt.getKeyCode());
    }//GEN-LAST:event_txtFechaCompraKeyPressed

    private void txtProveedorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProveedorKeyPressed
        // TODO add your handling code here:
        AccesoRapido(evt.getKeyCode());
    }//GEN-LAST:event_txtProveedorKeyPressed

    private void txtTotalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotalKeyPressed
        // TODO add your handling code here:
        AccesoRapido(evt.getKeyCode());
    }//GEN-LAST:event_txtTotalKeyPressed

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
            java.util.logging.Logger.getLogger(dlgConsultarCompras11.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(dlgConsultarCompras11.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(dlgConsultarCompras11.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(dlgConsultarCompras11.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                dlgConsultarCompras11 dialog = new dlgConsultarCompras11(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JLabel lbCantidad;
    public static javax.swing.JLabel lbInformacion;
    public static javax.swing.JTable tbCompra;
    public static javax.swing.JTable tbDetalleCompra;
    public static javax.swing.JTextField txtCodCompra;
    public static javax.swing.JTextField txtCodProveedor;
    public static javax.swing.JTextField txtFechaCompra;
    public static javax.swing.JTextField txtProveedor;
    public static javax.swing.JTextField txtTotal;
    public static javax.swing.JTextField txtmov;
    // End of variables declaration//GEN-END:variables
}
