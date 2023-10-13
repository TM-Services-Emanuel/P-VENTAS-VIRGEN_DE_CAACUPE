package IU;

public class cargaPrincipal extends javax.swing.JFrame {

    public cargaPrincipal() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Oscuro = new org.edisoncor.gui.panel.PanelImage();
        Blanco = new org.edisoncor.gui.panel.PanelImage();
        rSPasswordMaterial1 = new RSMaterialComponent.RSPasswordMaterial();
        rSButtonIconShadow1 = new RSMaterialComponent.RSButtonIconShadow();
        btnCargarTransferencias = new newscomponents.RSButtonGradientIcon_new();
        btnCargarRA = new newscomponents.RSButtonGradientIcon_new();
        btnCompraA = new newscomponents.RSButtonGradientIcon_new();
        rSComboBox1 = new rojerusan.RSComboBox();
        rSCheckBox1 = new rojerusan.RSCheckBox();
        rSRadioButton1 = new rojerusan.RSRadioButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        PanelContenedor1 = new rojeru_san.rspanel.RSPanelImage();
        btn = new RSMaterialComponent.RSButtonIconUno();
        Separador1 = new javax.swing.JSeparator();
        LabelTitulo1 = new javax.swing.JLabel();
        PanelContenedor2 = new rojeru_san.rspanel.RSPanelImage();
        btN2 = new RSMaterialComponent.RSButtonIconUno();
        Separador2 = new javax.swing.JSeparator();
        LabelTitulo2 = new javax.swing.JLabel();
        PanelContenedor3 = new rojeru_san.rspanel.RSPanelImage();
        btn3 = new RSMaterialComponent.RSButtonIconUno();
        Separador3 = new javax.swing.JSeparator();
        LabelTitulo3 = new javax.swing.JLabel();
        PanelContenedor4 = new rojeru_san.rspanel.RSPanelImage();
        btn4 = new RSMaterialComponent.RSButtonIconUno();
        Separador4 = new javax.swing.JSeparator();
        LabelTitulo4 = new javax.swing.JLabel();
        PanelContenedor = new rojeru_san.rspanel.RSPanelImage();
        btn5 = new RSMaterialComponent.RSButtonIconUno();
        Separador = new javax.swing.JSeparator();
        LabelTitulo = new javax.swing.JLabel();
        swEstado = new rojerusan.RSSwitch();
        btnSalir = new RSMaterialComponent.RSButtonIconOne();
        cboMarca = new RSMaterialComponent.RSComboBox();
        rSTextFieldShade1 = new rscomponentshade.RSTextFieldShade();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Oscuro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/fondo.png"))); // NOI18N
        Oscuro.setPreferredSize(new java.awt.Dimension(690, 418));
        Oscuro.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(Oscuro, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 40, 27, 29));

        Blanco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/fondoBlanco.jpg"))); // NOI18N
        Blanco.setPreferredSize(new java.awt.Dimension(690, 418));
        Blanco.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(Blanco, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 40, 27, 29));
        getContentPane().add(rSPasswordMaterial1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 50, -1, -1));

        rSButtonIconShadow1.setBackground(new java.awt.Color(0, 153, 0));
        rSButtonIconShadow1.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ADD_SHOPPING_CART);
        rSButtonIconShadow1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonIconShadow1ActionPerformed(evt);
            }
        });
        getContentPane().add(rSButtonIconShadow1, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 10, -1, -1));

        btnCargarTransferencias.setText("3- TRANSFERENCIAS");
        btnCargarTransferencias.setColorPrimario(new java.awt.Color(204, 0, 0));
        btnCargarTransferencias.setColorPrimarioHover(new java.awt.Color(255, 51, 0));
        btnCargarTransferencias.setColorSecundario(new java.awt.Color(255, 51, 0));
        btnCargarTransferencias.setColorSecundarioHover(new java.awt.Color(204, 0, 0));
        btnCargarTransferencias.setFont(new java.awt.Font("Roboto", 1, 10)); // NOI18N
        btnCargarTransferencias.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ZOOM_OUT_MAP);
        btnCargarTransferencias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarTransferenciasActionPerformed(evt);
            }
        });
        getContentPane().add(btnCargarTransferencias, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 150, 180, 39));

        btnCargarRA.setText("1- REPARTO ANTERIOR");
        btnCargarRA.setColorPrimario(new java.awt.Color(204, 0, 0));
        btnCargarRA.setColorPrimarioHover(new java.awt.Color(255, 51, 0));
        btnCargarRA.setColorSecundario(new java.awt.Color(255, 51, 0));
        btnCargarRA.setColorSecundarioHover(new java.awt.Color(204, 0, 0));
        btnCargarRA.setFont(new java.awt.Font("Roboto", 1, 10)); // NOI18N
        btnCargarRA.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.REPLY);
        btnCargarRA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarRAActionPerformed(evt);
            }
        });
        getContentPane().add(btnCargarRA, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 60, 180, 39));

        btnCompraA.setText("2- COMPRA DEL DÍA");
        btnCompraA.setColorPrimario(new java.awt.Color(204, 0, 0));
        btnCompraA.setColorPrimarioHover(new java.awt.Color(255, 51, 0));
        btnCompraA.setColorSecundario(new java.awt.Color(255, 51, 0));
        btnCompraA.setColorSecundarioHover(new java.awt.Color(204, 0, 0));
        btnCompraA.setFont(new java.awt.Font("Roboto", 1, 10)); // NOI18N
        btnCompraA.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ADD_SHOPPING_CART);
        btnCompraA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCompraAActionPerformed(evt);
            }
        });
        getContentPane().add(btnCompraA, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 100, 180, 39));

        rSComboBox1.setForeground(new java.awt.Color(0, 0, 0));
        rSComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "LUNES", "MARTES", "MIERCOLES", "JUEVES", "VIERNES" }));
        rSComboBox1.setSelectedIndex(-1);
        rSComboBox1.setColorArrow(new java.awt.Color(17, 35, 46));
        rSComboBox1.setColorBorde(new java.awt.Color(255, 255, 255));
        rSComboBox1.setColorBoton(new java.awt.Color(255, 255, 255));
        rSComboBox1.setColorDisabledIndex(new java.awt.Color(255, 255, 255));
        rSComboBox1.setColorFondo(new java.awt.Color(255, 255, 255));
        rSComboBox1.setColorSeleccion(new java.awt.Color(102, 102, 102));
        rSComboBox1.setColorSeleccionTXT(new java.awt.Color(0, 0, 0));
        rSComboBox1.setDisabledIdex("-1");
        rSComboBox1.setFont(new java.awt.Font("Roboto Bold", 1, 12)); // NOI18N
        getContentPane().add(rSComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 40, 90, 20));

        rSCheckBox1.setForeground(new java.awt.Color(0, 0, 0));
        rSCheckBox1.setText("rSCheckBox1");
        rSCheckBox1.setColorCheck(new java.awt.Color(0, 102, 102));
        rSCheckBox1.setColorUnCheck(new java.awt.Color(51, 51, 51));
        rSCheckBox1.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        getContentPane().add(rSCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 80, -1, -1));

        rSRadioButton1.setForeground(new java.awt.Color(0, 0, 0));
        rSRadioButton1.setText("CARGA TOTAL");
        rSRadioButton1.setColorUnCheck(new java.awt.Color(0, 153, 51));
        rSRadioButton1.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        getContentPane().add(rSRadioButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 120, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/DVC - copia - copia.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, -1, -1));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PanelContenedor1.setImagen(new javax.swing.ImageIcon(getClass().getResource("/Recursos/CONTENEDOR2.png"))); // NOI18N

        btn.setBackground(new java.awt.Color(255, 255, 255));
        btn.setBackgroundHover(new java.awt.Color(0, 153, 153));
        btn.setForegroundText(new java.awt.Color(0, 153, 153));
        btn.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ADD);
        btn.setRippleColor(java.awt.Color.white);
        btn.setTypeBorder(RSMaterialComponent.RSButtonIconUno.TYPEBORDER.CIRCLE);
        btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActionPerformed(evt);
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
                .addComponent(btn, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );
        PanelContenedor1Layout.setVerticalGroup(
            PanelContenedor1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelContenedor1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(Separador1, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(LabelTitulo1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(PanelContenedor1, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 3, 100, -1));

        PanelContenedor2.setImagen(new javax.swing.ImageIcon(getClass().getResource("/Recursos/CONTENEDOR2.png"))); // NOI18N

        btN2.setBackground(new java.awt.Color(255, 255, 255));
        btN2.setBackgroundHover(new java.awt.Color(204, 102, 0));
        btN2.setForegroundText(new java.awt.Color(204, 102, 0));
        btN2.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.EDIT);
        btN2.setRippleColor(java.awt.Color.white);
        btN2.setTypeBorder(RSMaterialComponent.RSButtonIconUno.TYPEBORDER.CIRCLE);
        btN2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btN2ActionPerformed(evt);
            }
        });

        Separador2.setForeground(new java.awt.Color(204, 204, 204));

        LabelTitulo2.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        LabelTitulo2.setForeground(new java.awt.Color(0, 102, 102));
        LabelTitulo2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LabelTitulo2.setText("MODIFICAR");

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
                .addComponent(btN2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );
        PanelContenedor2Layout.setVerticalGroup(
            PanelContenedor2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelContenedor2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btN2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(Separador2, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(LabelTitulo2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(PanelContenedor2, new org.netbeans.lib.awtextra.AbsoluteConstraints(102, 3, 100, -1));

        PanelContenedor3.setImagen(new javax.swing.ImageIcon(getClass().getResource("/Recursos/CONTENEDOR2.png"))); // NOI18N

        btn3.setBackground(new java.awt.Color(255, 255, 255));
        btn3.setBackgroundHover(new java.awt.Color(0, 102, 0));
        btn3.setForegroundText(new java.awt.Color(0, 102, 0));
        btn3.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.SAVE);
        btn3.setRippleColor(java.awt.Color.white);
        btn3.setTypeBorder(RSMaterialComponent.RSButtonIconUno.TYPEBORDER.CIRCLE);
        btn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn3ActionPerformed(evt);
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
                .addComponent(btn3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );
        PanelContenedor3Layout.setVerticalGroup(
            PanelContenedor3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelContenedor3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(Separador3, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(LabelTitulo3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(PanelContenedor3, new org.netbeans.lib.awtextra.AbsoluteConstraints(199, 3, 100, -1));

        PanelContenedor4.setImagen(new javax.swing.ImageIcon(getClass().getResource("/Recursos/CONTENEDOR2.png"))); // NOI18N

        btn4.setBackground(new java.awt.Color(255, 255, 255));
        btn4.setBackgroundHover(new java.awt.Color(255, 0, 0));
        btn4.setForegroundText(new java.awt.Color(255, 0, 0));
        btn4.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.CANCEL);
        btn4.setRippleColor(java.awt.Color.white);
        btn4.setTypeBorder(RSMaterialComponent.RSButtonIconUno.TYPEBORDER.CIRCLE);
        btn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn4ActionPerformed(evt);
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
                .addContainerGap(29, Short.MAX_VALUE)
                .addComponent(btn4, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );
        PanelContenedor4Layout.setVerticalGroup(
            PanelContenedor4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelContenedor4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn4, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(Separador4, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(LabelTitulo4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(PanelContenedor4, new org.netbeans.lib.awtextra.AbsoluteConstraints(296, 3, 100, -1));

        PanelContenedor.setImagen(new javax.swing.ImageIcon(getClass().getResource("/Recursos/CONTENEDOR2.png"))); // NOI18N

        btn5.setBackground(new java.awt.Color(255, 255, 255));
        btn5.setBackgroundHover(new java.awt.Color(51, 51, 51));
        btn5.setForegroundText(new java.awt.Color(51, 51, 51));
        btn5.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.DELETE);
        btn5.setRippleColor(java.awt.Color.white);
        btn5.setTypeBorder(RSMaterialComponent.RSButtonIconUno.TYPEBORDER.CIRCLE);
        btn5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn5ActionPerformed(evt);
            }
        });

        Separador.setForeground(new java.awt.Color(204, 204, 204));

        LabelTitulo.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        LabelTitulo.setForeground(new java.awt.Color(0, 102, 102));
        LabelTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LabelTitulo.setText("ELIMINAR");

        javax.swing.GroupLayout PanelContenedorLayout = new javax.swing.GroupLayout(PanelContenedor);
        PanelContenedor.setLayout(PanelContenedorLayout);
        PanelContenedorLayout.setHorizontalGroup(
            PanelContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelContenedorLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(btn5, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addComponent(btn5, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(Separador, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(LabelTitulo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(PanelContenedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(393, 3, 100, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 260, 500, 110));

        swEstado.setColorFondoActivado(new java.awt.Color(204, 102, 0));
        swEstado.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        swEstado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                swEstadoMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                swEstadoMousePressed(evt);
            }
        });
        swEstado.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                swEstadoPropertyChange(evt);
            }
        });
        swEstado.addVetoableChangeListener(new java.beans.VetoableChangeListener() {
            public void vetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {
                swEstadoVetoableChange(evt);
            }
        });
        getContentPane().add(swEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 430, 50, 20));

        btnSalir.setBackground(new java.awt.Color(0, 102, 102));
        btnSalir.setToolTipText("ALT+F4");
        btnSalir.setBackgroundHover(new java.awt.Color(255, 0, 0));
        btnSalir.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.CLOSE);
        btnSalir.setOpaque(true);
        btnSalir.setPreferredSize(new java.awt.Dimension(20, 20));
        btnSalir.setRequestFocusEnabled(false);
        btnSalir.setTypeBorder(RSMaterialComponent.RSButtonIconOne.TYPEBORDER.CIRCLE);
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
        getContentPane().add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 270, -1, -1));

        cboMarca.setForeground(new java.awt.Color(0, 0, 0));
        cboMarca.setColorArrow(new java.awt.Color(255, 255, 255));
        cboMarca.setColorBorde(new java.awt.Color(204, 204, 204));
        cboMarca.setColorBoton(new java.awt.Color(153, 153, 153));
        cboMarca.setColorFondo(new java.awt.Color(255, 255, 255));
        cboMarca.setColorSeleccion(new java.awt.Color(0, 102, 102));
        cboMarca.setConBorde(true);
        cboMarca.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        cboMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboMarcaActionPerformed(evt);
            }
        });
        cboMarca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cboMarcaKeyPressed(evt);
            }
        });
        getContentPane().add(cboMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 270, 267, 23));

        rSTextFieldShade1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        rSTextFieldShade1.setRound(25);
        getContentPane().add(rSTextFieldShade1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 410, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rSButtonIconShadow1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIconShadow1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rSButtonIconShadow1ActionPerformed

    private void btnCargarTransferenciasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarTransferenciasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCargarTransferenciasActionPerformed

    private void btnCargarRAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarRAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCargarRAActionPerformed

    private void btnCompraAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCompraAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCompraAActionPerformed

    private void swEstadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_swEstadoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_swEstadoMouseClicked

    private void swEstadoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_swEstadoMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_swEstadoMousePressed

    private void swEstadoPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_swEstadoPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_swEstadoPropertyChange

    private void swEstadoVetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {//GEN-FIRST:event_swEstadoVetoableChange
        // TODO add your handling code here:
    }//GEN-LAST:event_swEstadoVetoableChange

    private void btn5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn5ActionPerformed

    private void btn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn4ActionPerformed

    private void btn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn3ActionPerformed

    private void btN2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btN2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btN2ActionPerformed

    private void btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
 
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnSalirKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnSalirKeyPressed
        // TODO add your handling code here:
     
    }//GEN-LAST:event_btnSalirKeyPressed

    private void cboMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboMarcaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboMarcaActionPerformed

    private void cboMarcaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cboMarcaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboMarcaKeyPressed

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(cargaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new cargaPrincipal().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.edisoncor.gui.panel.PanelImage Blanco;
    private javax.swing.JLabel LabelTitulo;
    private javax.swing.JLabel LabelTitulo1;
    private javax.swing.JLabel LabelTitulo2;
    private javax.swing.JLabel LabelTitulo3;
    private javax.swing.JLabel LabelTitulo4;
    private org.edisoncor.gui.panel.PanelImage Oscuro;
    private rojeru_san.rspanel.RSPanelImage PanelContenedor;
    private rojeru_san.rspanel.RSPanelImage PanelContenedor1;
    private rojeru_san.rspanel.RSPanelImage PanelContenedor2;
    private rojeru_san.rspanel.RSPanelImage PanelContenedor3;
    private rojeru_san.rspanel.RSPanelImage PanelContenedor4;
    private javax.swing.JSeparator Separador;
    private javax.swing.JSeparator Separador1;
    private javax.swing.JSeparator Separador2;
    private javax.swing.JSeparator Separador3;
    private javax.swing.JSeparator Separador4;
    private RSMaterialComponent.RSButtonIconUno btN2;
    private RSMaterialComponent.RSButtonIconUno btn;
    private RSMaterialComponent.RSButtonIconUno btn3;
    private RSMaterialComponent.RSButtonIconUno btn4;
    private RSMaterialComponent.RSButtonIconUno btn5;
    public static newscomponents.RSButtonGradientIcon_new btnCargarRA;
    public static newscomponents.RSButtonGradientIcon_new btnCargarTransferencias;
    public static newscomponents.RSButtonGradientIcon_new btnCompraA;
    private RSMaterialComponent.RSButtonIconOne btnSalir;
    public static RSMaterialComponent.RSComboBox cboMarca;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private RSMaterialComponent.RSButtonIconShadow rSButtonIconShadow1;
    private rojerusan.RSCheckBox rSCheckBox1;
    private rojerusan.RSComboBox rSComboBox1;
    private RSMaterialComponent.RSPasswordMaterial rSPasswordMaterial1;
    private rojerusan.RSRadioButton rSRadioButton1;
    private rscomponentshade.RSTextFieldShade rSTextFieldShade1;
    public static rojerusan.RSSwitch swEstado;
    // End of variables declaration//GEN-END:variables
}
