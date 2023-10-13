package IU;

import Componentes.DataSourceService;
import Componentes.Fecha;
import Componentes.Mensajes;
import Componentes.Software;
import Componentes.cargarComboBox;
import Componentes.generarCodigos;
import Componentes.validarCampos;
import Controladores.CabecerasTablas;
import Controladores.ControlGasto;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;

public class dlgGastos extends javax.swing.JDialog {

    public dlgGastos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        java.awt.Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Iconos/logo1.png"));
        setIconImage(icon);
        titulo();
        noVisible();
        cbDetalleGastoActionPerformed(null);
        btnCancelarActionPerformed(null);

    }

    private void noVisible() {
        txtImporteL.setVisible(false);
        txtFuncionario.setVisible(false);
        txtMotivo.setVisible(false);
        txtOrigen.setVisible(false);
    }

    final void titulo() {
        if (Software.getSoftware().equals("null")) {
            this.setTitle("Registrar egreso del día");
        } else {
            this.setTitle(Software.getSoftware() + " - Registrar egreso del día");
        }
    }

    void limpiarCampos() {
        txtCaja.setText("");
        txtImporte.setText("0");
        txtImporteL.setText("0");
        txtObservacion.setText("");
        txtFuncionario.setText("");
        txtMotivo.setText("");
        txtOrigen.setText("");
        cbDetalleGasto.setSelectedIndex(0);
        cbGenerado.setSelectedIndex(0);
        cboOtorgado.setSelectedIndex(0);
    }

    /*public void nuevo() {
        try {
            String FechaI = String.valueOf(Fecha.fechaCorrecta());
            txtCaja.setText(generarCodigos.ObtenerCodigo("SELECT ca_id from caja WHERE ca_fechainicio='" + FechaI + "' and ca_indicador='S'"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        cbDetalleGasto.setEnabled(true);
        txtImporte.setEnabled(true);
        txtObservacion.setEnabled(true);
        cboOtorgado.setEnabled(true);
        dcFecha.setEnabled(true);
        cbGenerado.setEnabled(true);

        btnNuevo.setEnabled(false);
        btnGuardar.setEnabled(true);
        btnCancelar.setEnabled(true);

        cbDetalleGasto.requestFocus();
        cbDetalleGasto.setPopupVisible(true);
    }*/
    public void Volver() {
        dlgGestGastos.txtGL.setText("0");
        dlgGestGastos.txtGA.setText("0");
        CabecerasTablas.gestGastos(dlgGestGastos.tbGastos);
        CabecerasTablas.limpiarTablasGastos(dlgGestGastos.tbGastos);
        ControlGasto.listarGastos(dlgGestGastos.tbGastos, dlgGestGastos.txtFechaFinal.getText().trim());
        dlgGestGastos.Renders();
        dlgGestGastos.calcularValores();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Blanco = new org.edisoncor.gui.panel.PanelImage();
        Oscuro = new org.edisoncor.gui.panel.PanelImage();
        btnSalir = new RSMaterialComponent.RSButtonIconOne();
        jPanel2 = new javax.swing.JPanel();
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
        txtMotivo = new javax.swing.JTextField();
        txtOrigen = new javax.swing.JTextField();
        txtFuncionario = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        btnCalcular = new RSMaterialComponent.RSButtonIconOne();
        txtCaja = new javax.swing.JTextField();
        dcFecha = new datechooser.beans.DateChooserCombo();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lbOtorgado = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtImporte = new javax.swing.JTextField();
        txtImporteL = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtObservacion = new javax.swing.JTextField();
        cbDetalleGasto = new RSMaterialComponent.RSComboBox();
        cboOtorgado = new RSMaterialComponent.RSComboBox();
        cbGenerado = new RSMaterialComponent.RSComboBox();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        Blanco.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        Blanco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/fondoBlanco.jpg"))); // NOI18N
        Blanco.setPreferredSize(new java.awt.Dimension(690, 418));
        Blanco.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Oscuro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/0-102-102.png"))); // NOI18N
        Oscuro.setPreferredSize(new java.awt.Dimension(690, 418));

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

        jPanel2.setOpaque(false);
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PanelContenedor1.setImagen(new javax.swing.ImageIcon(getClass().getResource("/Recursos/CONTENEDOR2.png"))); // NOI18N

        btnNuevo.setBackground(new java.awt.Color(255, 255, 255));
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

        jPanel2.add(PanelContenedor1, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 3, 100, -1));

        PanelContenedor3.setImagen(new javax.swing.ImageIcon(getClass().getResource("/Recursos/CONTENEDOR2.png"))); // NOI18N

        btnGuardar.setBackground(new java.awt.Color(255, 255, 255));
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

        jPanel2.add(PanelContenedor3, new org.netbeans.lib.awtextra.AbsoluteConstraints(102, 3, 100, -1));

        PanelContenedor4.setImagen(new javax.swing.ImageIcon(getClass().getResource("/Recursos/CONTENEDOR2.png"))); // NOI18N

        btnCancelar.setBackground(new java.awt.Color(255, 255, 255));
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
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
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

        jPanel2.add(PanelContenedor4, new org.netbeans.lib.awtextra.AbsoluteConstraints(199, 3, 100, -1));

        javax.swing.GroupLayout OscuroLayout = new javax.swing.GroupLayout(Oscuro);
        Oscuro.setLayout(OscuroLayout);
        OscuroLayout.setHorizontalGroup(
            OscuroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OscuroLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(OscuroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(OscuroLayout.createSequentialGroup()
                        .addComponent(txtMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 253, Short.MAX_VALUE)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(OscuroLayout.createSequentialGroup()
                        .addComponent(txtOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        OscuroLayout.setVerticalGroup(
            OscuroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OscuroLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(OscuroLayout.createSequentialGroup()
                .addGroup(OscuroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(OscuroLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(OscuroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Blanco.add(Oscuro, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 1, 701, 101));

        jPanel1.setOpaque(false);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        jLabel6.setText("Mov. N°");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 13, 82, 23));

        btnCalcular.setBackground(new java.awt.Color(255, 255, 255));
        btnCalcular.setForeground(new java.awt.Color(51, 51, 51));
        btnCalcular.setToolTipText("ALT+F4");
        btnCalcular.setBackgroundHover(new java.awt.Color(204, 102, 0));
        btnCalcular.setForegroundText(new java.awt.Color(204, 102, 0));
        btnCalcular.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.EXPOSURE);
        btnCalcular.setOpaque(true);
        btnCalcular.setPreferredSize(new java.awt.Dimension(20, 20));
        btnCalcular.setRequestFocusEnabled(false);
        btnCalcular.setTypeBorder(RSMaterialComponent.RSButtonIconOne.TYPEBORDER.CIRCLE);
        btnCalcular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalcularActionPerformed(evt);
            }
        });
        btnCalcular.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnCalcularKeyPressed(evt);
            }
        });
        jPanel1.add(btnCalcular, new org.netbeans.lib.awtextra.AbsoluteConstraints(667, 43, 23, 23));

        txtCaja.setEditable(false);
        txtCaja.setBackground(new java.awt.Color(255, 255, 255));
        txtCaja.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        txtCaja.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel1.add(txtCaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(102, 13, 110, 23));

        dcFecha.setCurrentView(new datechooser.view.appearance.AppearancesList("Swing",
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
    dcFecha.setCalendarBackground(new java.awt.Color(255, 255, 204));
    dcFecha.setWeekStyle(datechooser.view.WeekDaysStyle.SHORT);
    dcFecha.setEnabled(false);
    dcFecha.setFieldFont(new java.awt.Font("Roboto", java.awt.Font.PLAIN, 11));
    jPanel1.add(dcFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(596, 13, 94, 23));

    jLabel1.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
    jLabel1.setText("Fecha de la deuda");
    jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 13, 90, 23));

    jLabel2.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
    jLabel2.setText("Generado en");
    jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 73, -1, 23));

    lbOtorgado.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
    lbOtorgado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    lbOtorgado.setText("Otorgado a");
    jPanel1.add(lbOtorgado, new org.netbeans.lib.awtextra.AbsoluteConstraints(365, 43, 60, 23));

    jLabel4.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
    jLabel4.setText("Monto de gasto");
    jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 103, 83, 23));

    txtImporte.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
    txtImporte.setForeground(new java.awt.Color(0, 153, 0));
    txtImporte.setHorizontalAlignment(javax.swing.JTextField.CENTER);
    txtImporte.setText("0");
    txtImporte.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
    txtImporte.setEnabled(false);
    txtImporte.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            txtImporteActionPerformed(evt);
        }
    });
    txtImporte.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyPressed(java.awt.event.KeyEvent evt) {
            txtImporteKeyPressed(evt);
        }
        public void keyReleased(java.awt.event.KeyEvent evt) {
            txtImporteKeyReleased(evt);
        }
    });
    jPanel1.add(txtImporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(102, 103, 144, 23));

    txtImporteL.setEditable(false);
    txtImporteL.setBackground(new java.awt.Color(255, 255, 255));
    txtImporteL.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 11)); // NOI18N
    txtImporteL.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
    txtImporteL.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
    jPanel1.add(txtImporteL, new org.netbeans.lib.awtextra.AbsoluteConstraints(251, 103, 110, 23));

    jLabel5.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
    jLabel5.setText("Observación");
    jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 134, 83, 23));

    txtObservacion.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
    txtObservacion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
    txtObservacion.setEnabled(false);
    txtObservacion.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            txtObservacionActionPerformed(evt);
        }
    });
    txtObservacion.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyTyped(java.awt.event.KeyEvent evt) {
            txtObservacionKeyTyped(evt);
        }
    });
    jPanel1.add(txtObservacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(102, 134, 590, 23));

    cbDetalleGasto.setForeground(new java.awt.Color(0, 0, 0));
    cbDetalleGasto.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECCIONE EL MOTIVO:", "1- GASTOS VARIOS", "2- ADELANTO DE SUELDO", "3- PAGO DE SUELDO" }));
    cbDetalleGasto.setColorArrow(new java.awt.Color(255, 255, 255));
    cbDetalleGasto.setColorBorde(new java.awt.Color(204, 204, 204));
    cbDetalleGasto.setColorBoton(new java.awt.Color(153, 153, 153));
    cbDetalleGasto.setColorFondo(new java.awt.Color(255, 255, 255));
    cbDetalleGasto.setColorSeleccion(new java.awt.Color(0, 102, 102));
    cbDetalleGasto.setConBorde(true);
    cbDetalleGasto.setEnabled(false);
    cbDetalleGasto.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
    cbDetalleGasto.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            cbDetalleGastoActionPerformed(evt);
        }
    });
    cbDetalleGasto.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyPressed(java.awt.event.KeyEvent evt) {
            cbDetalleGastoKeyPressed(evt);
        }
    });
    jPanel1.add(cbDetalleGasto, new org.netbeans.lib.awtextra.AbsoluteConstraints(102, 43, 260, 23));

    cboOtorgado.setForeground(new java.awt.Color(0, 0, 0));
    cboOtorgado.setColorArrow(new java.awt.Color(255, 255, 255));
    cboOtorgado.setColorBorde(new java.awt.Color(204, 204, 204));
    cboOtorgado.setColorBoton(new java.awt.Color(153, 153, 153));
    cboOtorgado.setColorFondo(new java.awt.Color(255, 255, 255));
    cboOtorgado.setColorSeleccion(new java.awt.Color(0, 102, 102));
    cboOtorgado.setConBorde(true);
    cboOtorgado.setEnabled(false);
    cboOtorgado.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
    cboOtorgado.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            cboOtorgadoActionPerformed(evt);
        }
    });
    cboOtorgado.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyPressed(java.awt.event.KeyEvent evt) {
            cboOtorgadoKeyPressed(evt);
        }
    });
    jPanel1.add(cboOtorgado, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 43, 235, 23));

    cbGenerado.setForeground(new java.awt.Color(0, 0, 0));
    cbGenerado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ORIGEN DEL GASTO:", "1- SALÓN DE VENTA", "2- ADMINISTRACIÓN" }));
    cbGenerado.setColorArrow(new java.awt.Color(255, 255, 255));
    cbGenerado.setColorBorde(new java.awt.Color(204, 204, 204));
    cbGenerado.setColorBoton(new java.awt.Color(153, 153, 153));
    cbGenerado.setColorFondo(new java.awt.Color(255, 255, 255));
    cbGenerado.setColorSeleccion(new java.awt.Color(0, 102, 102));
    cbGenerado.setConBorde(true);
    cbGenerado.setEnabled(false);
    cbGenerado.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
    cbGenerado.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            cbGeneradoActionPerformed(evt);
        }
    });
    cbGenerado.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyPressed(java.awt.event.KeyEvent evt) {
            cbGeneradoKeyPressed(evt);
        }
    });
    jPanel1.add(cbGenerado, new org.netbeans.lib.awtextra.AbsoluteConstraints(102, 73, 260, 23));

    jLabel3.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
    jLabel3.setText("Motivo del gasto");
    jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 43, -1, 23));

    Blanco.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 700, 160));

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(Blanco, javax.swing.GroupLayout.PREFERRED_SIZE, 703, javax.swing.GroupLayout.PREFERRED_SIZE)
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(Blanco, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtImporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtImporteActionPerformed
        // TODO add your handling code here:
        if (txtImporte.getText().isEmpty()) {
            txtImporte.requestFocus();
            txtImporte.selectAll();
        } else {
            txtObservacion.requestFocus();
        }
    }//GEN-LAST:event_txtImporteActionPerformed

    private void txtImporteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtImporteKeyPressed
        // TODO add your handling code here:
        validarCampos.soloNumeros(txtImporte);
    }//GEN-LAST:event_txtImporteKeyPressed

    private void txtImporteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtImporteKeyReleased
        // TODO add your handling code here:
        try {
            if (txtImporte.getText().trim().length() >= 1) {
                DecimalFormat df = new DecimalFormat("#,###");
                txtImporte.setText(df.format(Integer.valueOf(txtImporte.getText().trim().replace(".", "").replace(",", ""))));
            } else {
                txtImporte.setText("0");
                txtImporte.selectAll();
            }
        } catch (NumberFormatException e) {
            System.out.println("c: " + e.getMessage());
        }
        if (txtImporte.getText().equals("")) {
            txtImporteL.setText("0");
        } else {
            DecimalFormat dff = new DecimalFormat("#0");
            txtImporteL.setText(dff.format(Integer.valueOf(txtImporte.getText().trim().replace(".", "").replace(",", ""))));
        }
    }//GEN-LAST:event_txtImporteKeyReleased

    private void txtObservacionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtObservacionKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (Character.isLowerCase(c)) {
            String cad = ("" + c).toUpperCase();
            c = cad.charAt(0);
            evt.setKeyChar(c);
        }
    }//GEN-LAST:event_txtObservacionKeyTyped

    private void txtObservacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtObservacionActionPerformed
        // TODO add your handling code here:
        if (txtObservacion.getText().isEmpty()) {
            txtObservacion.requestFocus();
            txtObservacion.selectAll();
        } else {
            btnGuardar.doClick();
        }
    }//GEN-LAST:event_txtObservacionActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        int rpta = Mensajes.confirmar("¿Seguro que desea salir del formulario?");
        if (rpta == 0) {
            this.dispose();
            Volver();
        }
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnSalirKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnSalirKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSalirKeyPressed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here:
        try {
            String FechaI = String.valueOf(Fecha.fechaCorrecta());
            txtCaja.setText(generarCodigos.ObtenerCodigo("SELECT ca_id from caja WHERE ca_fechainicio='" + FechaI + "' and ca_indicador='S'"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        //cbDetalleGastoActionPerformed(null);
        cbDetalleGasto.setEnabled(true);
        txtImporte.setEnabled(true);
        txtObservacion.setEnabled(true);
        cboOtorgado.setEnabled(true);
        //dcFecha.setEnabled(true);
        cbGenerado.setEnabled(true);

        btnNuevo.setEnabled(false);
        btnGuardar.setEnabled(true);
        btnCancelar.setEnabled(true);
        btnSalir.setEnabled(false);

        cbDetalleGasto.requestFocus();
        cbDetalleGasto.setPopupVisible(true);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        if (cbDetalleGasto.getSelectedIndex() == 0) {
            Mensajes.Sistema("Seleccione el motivo por el cual se registrara el gasto al sistema.");
            cbDetalleGasto.requestFocus();
            cbDetalleGasto.setPopupVisible(true);
        } else if (cbDetalleGasto.getSelectedIndex() != 1 && cboOtorgado.getSelectedIndex() == 0) {
            Mensajes.Sistema("Seleccione a quien se otorga este gasto.");
            cboOtorgado.requestFocus();
            cboOtorgado.setPopupVisible(true);
        } else if (cbGenerado.getSelectedIndex() == 0) {
            Mensajes.Sistema("Seleccione el origen del gasto.");
            cbGenerado.requestFocus();
            cbGenerado.setPopupVisible(true);
        } else if (txtImporte.getText().isEmpty()) {
            Mensajes.Sistema("Ingrese en importe a registrar");
            txtImporte.requestFocus();
            txtImporte.selectAll();
        } else if (txtImporte.getText().equals("0")) {
            Mensajes.Sistema("Ingrese un importe mayor a 0");
            txtImporte.requestFocus();
            txtImporte.selectAll();
        } else if (txtObservacion.getText().isEmpty()) {
            Mensajes.Sistema("Ingrese una Observacion con relacion a este gasto.");
            txtObservacion.requestFocus();
        } else {
            try {
                int resp = JOptionPane.showConfirmDialog(this, "¿Seguro que desea insertar el registro?", "Insertar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (resp == JOptionPane.YES_OPTION) {
                    ControlGasto.addGasto();
                    btnCancelarActionPerformed(null);
                }
            } catch (HeadlessException ee) {
                System.out.println(ee.getMessage());
            }

        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        limpiarCampos();
        cbDetalleGasto.setEnabled(false);
        txtImporte.setEnabled(false);
        txtObservacion.setEnabled(false);
        cboOtorgado.setEnabled(false);
        dcFecha.setEnabled(false);
        cbGenerado.setEnabled(false);
        btnNuevo.setEnabled(true);
        btnGuardar.setEnabled(false);
        btnCancelar.setEnabled(false);
        btnSalir.setEnabled(true);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void cbDetalleGastoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbDetalleGastoActionPerformed
        // TODO add your handling code here:
        switch (cbDetalleGasto.getSelectedIndex()) {
            case 0 -> {
                lbOtorgado.setVisible(false);
                cboOtorgado.setVisible(false);
                txtMotivo.setText("");
                btnCalcular.setVisible(false);
            }
            case 1 -> {
                lbOtorgado.setVisible(false);
                cboOtorgado.setVisible(false);
                txtMotivo.setText("1");
                btnCalcular.setVisible(false);
            }
            case 2 -> {
                lbOtorgado.setVisible(true);
                cboOtorgado.setVisible(true);
                txtMotivo.setText("2");
                btnCalcular.setVisible(true);
            }
            case 3 -> {
                lbOtorgado.setVisible(true);
                cboOtorgado.setVisible(true);
                txtMotivo.setText("3");
                btnCalcular.setVisible(true);
            }
            default -> {
            }
        }
        cargarComboBox.cargarResponsable(cboOtorgado, "SELECT * FROM v_vendedores WHERE idfuncion>=3 AND idfuncion<=4 AND ven_indicador='S'");
        cboOtorgadoActionPerformed(null);
    }//GEN-LAST:event_cbDetalleGastoActionPerformed

    private void cbDetalleGastoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbDetalleGastoKeyPressed
        // TODO add your handling code here:
        // AccesoRapido(evt.getKeyCode());
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cboOtorgado.requestFocus();
            cboOtorgado.setPopupVisible(true);
        }
    }//GEN-LAST:event_cbDetalleGastoKeyPressed

    private void cboOtorgadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboOtorgadoActionPerformed
        // TODO add your handling code here:
        if (cboOtorgado.getSelectedIndex() == 0) {
            txtFuncionario.setText("0");
            txtImporte.setText("0");
            txtImporteL.setText("0");
        } else {
            txtFuncionario.setText(cargarComboBox.getCodidgo(cboOtorgado));
            txtImporte.setText("0");
            txtImporteL.setText("0");
        }
    }//GEN-LAST:event_cboOtorgadoActionPerformed

    private void cboOtorgadoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cboOtorgadoKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtImporte.requestFocus();
        }
    }//GEN-LAST:event_cboOtorgadoKeyPressed

    private void cbGeneradoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbGeneradoActionPerformed
        // TODO add your handling code here:
        switch (cbGenerado.getSelectedIndex()) {
            case 0 ->
                txtOrigen.setText("");
            case 1 ->
                txtOrigen.setText("L");
            case 2 ->
                txtOrigen.setText("A");
            default -> {
            }
        }
    }//GEN-LAST:event_cbGeneradoActionPerformed

    private void cbGeneradoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbGeneradoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbGeneradoKeyPressed

    private void btnCalcularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalcularActionPerformed
        // TODO add your handling code here:
        if(cboOtorgado.getSelectedIndex() == 0){
            Mensajes.Sistema("No es posible abrir el gestor de consulta.\nIdentifique el funcionario y vuelva a intentarlo.");
        }else{
            try {

                dlgReporteGastos gastos = new dlgReporteGastos(null, true);
                String sueldo = generarCodigos.getFecha("SELECT ven_sueldo FROM vendedor WHERE ven_codigo=" + txtFuncionario.getText());
                dlgReporteGastos.txtCod.setText(txtFuncionario.getText());
                dlgReporteGastos.txtNombre.setText(cboOtorgado.getSelectedItem().toString());
                DecimalFormat df = new DecimalFormat("#,###");
                dlgReporteGastos.lbSueldo.setText(df.format(Integer.valueOf(sueldo)));
                gastos.setLocationRelativeTo(this);
                gastos.setVisible(true);
            } catch (NumberFormatException | SQLException e) {
                Mensajes.informacion("Servidor no esta activo: " + e.getMessage());
            } 
        }
        

    }//GEN-LAST:event_btnCalcularActionPerformed

    private void btnCalcularKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnCalcularKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_btnCalcularKeyPressed

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
            java.util.logging.Logger.getLogger(dlgGastos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(() -> {
            dlgGastos dialog = new dlgGastos(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel LabelTitulo1;
    private javax.swing.JLabel LabelTitulo3;
    private javax.swing.JLabel LabelTitulo4;
    private org.edisoncor.gui.panel.PanelImage Oscuro;
    private rojeru_san.rspanel.RSPanelImage PanelContenedor1;
    private rojeru_san.rspanel.RSPanelImage PanelContenedor3;
    private rojeru_san.rspanel.RSPanelImage PanelContenedor4;
    private javax.swing.JSeparator Separador1;
    private javax.swing.JSeparator Separador3;
    private javax.swing.JSeparator Separador4;
    private RSMaterialComponent.RSButtonIconOne btnCalcular;
    private RSMaterialComponent.RSButtonIconUno btnCancelar;
    private RSMaterialComponent.RSButtonIconUno btnGuardar;
    private RSMaterialComponent.RSButtonIconUno btnNuevo;
    private RSMaterialComponent.RSButtonIconOne btnSalir;
    public static RSMaterialComponent.RSComboBox cbDetalleGasto;
    public static RSMaterialComponent.RSComboBox cbGenerado;
    public static RSMaterialComponent.RSComboBox cboOtorgado;
    public static datechooser.beans.DateChooserCombo dcFecha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lbOtorgado;
    public static javax.swing.JTextField txtCaja;
    public static javax.swing.JTextField txtFuncionario;
    public static javax.swing.JTextField txtImporte;
    public static javax.swing.JTextField txtImporteL;
    public static javax.swing.JTextField txtMotivo;
    public static javax.swing.JTextField txtObservacion;
    public static javax.swing.JTextField txtOrigen;
    // End of variables declaration//GEN-END:variables
}
