package IU;

import Componentes.Empresa;
import Componentes.Fecha;
import Componentes.Login;
import Componentes.Mensajes;
import Componentes.Notif;
import Componentes.PrinterService;
import Componentes.RenderDecimal1;
import Componentes.Timbrado;
import Componentes.cargarComboBox;
import Componentes.generarCodigos;
import Controladores.CabecerasTablas;
import Controladores.ControlCaja;
import Datos.GestionarCaja;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;

public class dlgRegistroValores extends javax.swing.JDialog {

    /**
     * Creates new form dlgRegistroValores
     *
     * @param parent
     * @param modal
     */
    public dlgRegistroValores(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        //CabecerasTablas.TablaCierre(tablaDetalles);
        CabecerasTablas.TablaCierreDetallado(tablaDetalles);
        Visor();
        CargarCombos();
        lbCaja.setText("");
        Invisible();
    }

    private void Invisible() {
        txtCaja.setVisible(false);
        txtIDBoca.setVisible(false);
        btnListarDatos.setVisible(false);
        txtFechaCaja.setVisible(false);
        txtEstado.setVisible(false);
    }

    public static final void CargarTabla() {

        if (cboBocaCobranza.getSelectedIndex() == 0) {
            CabecerasTablas.limpiarTablasCierre(tablaDetalles);
            ControlCaja.listArreglos(tablaDetalles, txtCaja.getText());
            lbCaja.setText("Correspondiente a la Caja N°: " + txtCaja.getText());
            Notif.NotifySuccess("Notificación del sistema", "Registro de arreglos actualizados!");
        } else {
            CabecerasTablas.limpiarTablasCierre(tablaDetalles);
            ControlCaja.listArreglos2(tablaDetalles, txtCaja.getText(), txtIDBoca.getText());
            lbCaja.setText("Correspondiente a la Caja N°: " + txtCaja.getText());
            Notif.NotifySuccess("Notificación del sistema", "Registro de arreglos actualizados!");
        }
        Renders();
    }

    public static void Renders() {
        tablaDetalles.getColumnModel().getColumn(17).setCellRenderer(new RenderDecimal1());
    }

    private void CargarCombos() {
        cargarComboBox.cargarBocaCobranza(cboBocaCobranza, "SELECT * FROM laboratorio WHERE lab_indicador='S'");
    }

    void ModificarValores() {
        int x = tablaDetalles.getSelectedRow();
        if (x < 0) {
            Notif.NotifyError("Notificación del sistema", "No es posible desplegar el formulario de valores.\r\nSeleccione un registro en la tabla.");
        } else {
            try {
                dlgArreglo a = new dlgArreglo(null, true);
                a.setLocationRelativeTo(null);
                ControlCaja.aModifcar();
                dlgArreglo.Calculadora();
                dlgArreglo.txtEvento.setText("M");
                a.setVisible(true);
                dlgArreglo.cboConcepto.requestFocus();
            } catch (Exception e) {
            }
            tablaDetalles.clearSelection();
        }
    }

    void EliminarValores() {
        int x = tablaDetalles.getSelectedRow();
        if (x < 0) {
            Notif.NotifyError("Notificación del sistema", "No es posible procesar la eliminación.\r\nSeleccione un registro de valores de la tabla.");
        } else {
            String msg;
            msg = GestionarCaja.delArreglo(Integer.parseInt(tablaDetalles.getValueAt(x, 0).toString()));
            if (msg == null) {
                Notif.NotifySuccess("Notificación del sistema", tablaDetalles.getValueAt(x, 6).toString() + " ELIMINADO!");
                CargarTabla();
            } else {
                Notif.NotifyError("Notificación del sistema", "Error eliminando " + tablaDetalles.getValueAt(x, 6).toString() + ":\r\n" + msg);
            }
        }
    }

    private void Visor() {
        switch (Login.getPerfil()) {
            case "ADMINISTRADOR" -> {
                PanelContenedor2.setVisible(true);
                PanelContenedor.setVisible(true);
            }
            case "DESARROLLADOR" -> {
                PanelContenedor2.setVisible(true);
                PanelContenedor.setVisible(true);
            }
            default -> {
                PanelContenedor2.setVisible(false);
                PanelContenedor.setVisible(false);
            }
        }
    }

    public static void CargarResumen() {
        DecimalFormat df = new DecimalFormat("#,###");
        String G = String.valueOf(ControlCaja.getGastos());
        txtGastos.setText(df.format(Integer.parseInt(G.replace(".", "").replace(",", ""))));
        String R = String.valueOf(ControlCaja.getRetiros());
        txtRetiros.setText(df.format(Integer.parseInt(R.replace(".", "").replace(",", ""))));
        String C = String.valueOf(ControlCaja.getCierre());
        txtCierres.setText(df.format(Integer.parseInt(C.replace(".", "").replace(",", ""))));
        //
        String n50 = String.valueOf(ControlCaja.getn50());
        txt50.setText(df.format(Integer.parseInt(n50.replace(".", "").replace(",", ""))));
        String n100 = String.valueOf(ControlCaja.getn100());
        txt100.setText(df.format(Integer.parseInt(n100.replace(".", "").replace(",", ""))));
        String n500 = String.valueOf(ControlCaja.getn500());
        txt500.setText(df.format(Integer.parseInt(n500.replace(".", "").replace(",", ""))));
        String n1000 = String.valueOf(ControlCaja.getn1000());
        txt1000.setText(df.format(Integer.parseInt(n1000.replace(".", "").replace(",", ""))));
        String n2000 = String.valueOf(ControlCaja.getn2000());
        txt2000.setText(df.format(Integer.parseInt(n2000.replace(".", "").replace(",", ""))));
        String n5000 = String.valueOf(ControlCaja.getn5000());
        txt5000.setText(df.format(Integer.parseInt(n5000.replace(".", "").replace(",", ""))));
        String n10000 = String.valueOf(ControlCaja.getn10000());
        txt10000.setText(df.format(Integer.parseInt(n10000.replace(".", "").replace(",", ""))));
        String n20000 = String.valueOf(ControlCaja.getn20000());
        txt20000.setText(df.format(Integer.parseInt(n20000.replace(".", "").replace(",", ""))));
        String n50000 = String.valueOf(ControlCaja.getn50000());
        txt50000.setText(df.format(Integer.parseInt(n50000.replace(".", "").replace(",", ""))));
        String n100000 = String.valueOf(ControlCaja.getn100000());
        txt100000.setText(df.format(Integer.parseInt(n100000.replace(".", "").replace(",", ""))));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        panelFondo = new javax.swing.JPanel();
        cboBocaCobranza = new RSMaterialComponent.RSComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        panelCabecera = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        PanelContenedor1 = new rojeru_san.rspanel.RSPanelImage();
        btnAdd = new RSMaterialComponent.RSButtonIconUno();
        Separador1 = new javax.swing.JSeparator();
        LabelTitulo1 = new javax.swing.JLabel();
        PanelContenedor2 = new rojeru_san.rspanel.RSPanelImage();
        btnModificar = new RSMaterialComponent.RSButtonIconUno();
        Separador2 = new javax.swing.JSeparator();
        LabelTitulo2 = new javax.swing.JLabel();
        PanelContenedor = new rojeru_san.rspanel.RSPanelImage();
        btnEliminar = new RSMaterialComponent.RSButtonIconUno();
        Separador = new javax.swing.JSeparator();
        LabelTitulo = new javax.swing.JLabel();
        btnSalir = new RSMaterialComponent.RSButtonIconOne();
        txtIDBoca = new javax.swing.JTextField();
        txtCaja = new javax.swing.JTextField();
        btnListarDatos = new RSMaterialComponent.RSButtonIconUno();
        txtFechaCaja = new javax.swing.JTextField();
        txtEstado = new javax.swing.JTextField();
        lbCaja = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        txtGastos = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtCierres = new javax.swing.JTextField();
        txtRetiros = new javax.swing.JTextField();
        btnModificar1 = new RSMaterialComponent.RSButtonIconUno();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txt2000 = new javax.swing.JTextField();
        txt1000 = new javax.swing.JTextField();
        txt500 = new javax.swing.JTextField();
        txt100 = new javax.swing.JTextField();
        txt50 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txt100000 = new javax.swing.JTextField();
        txt50000 = new javax.swing.JTextField();
        txt20000 = new javax.swing.JTextField();
        txt10000 = new javax.swing.JTextField();
        txt5000 = new javax.swing.JTextField();

        jLabel5.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        jLabel5.setText("Resumen de Gastos");

        jTextField4.setEditable(false);
        jTextField4.setBackground(new java.awt.Color(255, 255, 255));
        jTextField4.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jTextField4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        panelFondo.setBackground(new java.awt.Color(255, 255, 255));
        panelFondo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        panelFondo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                panelFondoFocusGained(evt);
            }
        });
        panelFondo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cboBocaCobranza.setForeground(new java.awt.Color(0, 0, 0));
        cboBocaCobranza.setColorArrow(new java.awt.Color(255, 255, 255));
        cboBocaCobranza.setColorBorde(new java.awt.Color(204, 204, 204));
        cboBocaCobranza.setColorBoton(new java.awt.Color(153, 153, 153));
        cboBocaCobranza.setColorFondo(new java.awt.Color(255, 255, 255));
        cboBocaCobranza.setColorSeleccion(new java.awt.Color(0, 102, 102));
        cboBocaCobranza.setConBorde(true);
        cboBocaCobranza.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        cboBocaCobranza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboBocaCobranzaActionPerformed(evt);
            }
        });
        cboBocaCobranza.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cboBocaCobranzaKeyPressed(evt);
            }
        });
        panelFondo.add(cboBocaCobranza, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 110, 310, 25));

        jScrollPane2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        tablaDetalles.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        tablaDetalles.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaDetalles.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tablaDetalles.setGridColor(new java.awt.Color(204, 204, 204));
        tablaDetalles.setRowHeight(20);
        tablaDetalles.setShowGrid(true);
        tablaDetalles.setShowVerticalLines(false);
        tablaDetalles.getTableHeader().setResizingAllowed(false);
        tablaDetalles.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tablaDetalles);

        panelFondo.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 137, 945, 270));

        panelCabecera.setBackground(new java.awt.Color(0, 102, 102));
        panelCabecera.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setOpaque(false);
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PanelContenedor1.setImagen(new javax.swing.ImageIcon(getClass().getResource("/Recursos/CONTENEDOR2.png"))); // NOI18N
        PanelContenedor1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnAdd.setBackground(new java.awt.Color(255, 255, 255));
        btnAdd.setBackgroundHover(new java.awt.Color(0, 153, 153));
        btnAdd.setForegroundText(new java.awt.Color(0, 153, 153));
        btnAdd.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ADD);
        btnAdd.setRippleColor(java.awt.Color.white);
        btnAdd.setTypeBorder(RSMaterialComponent.RSButtonIconUno.TYPEBORDER.CIRCLE);
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        PanelContenedor1.add(btnAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 11, 50, 50));

        Separador1.setForeground(new java.awt.Color(204, 204, 204));
        PanelContenedor1.add(Separador1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 66, 76, 3));

        LabelTitulo1.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        LabelTitulo1.setForeground(new java.awt.Color(0, 102, 102));
        LabelTitulo1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LabelTitulo1.setText("NUEVO");
        PanelContenedor1.add(LabelTitulo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 73, 76, -1));

        jPanel2.add(PanelContenedor1, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 3, 100, 100));

        PanelContenedor2.setImagen(new javax.swing.ImageIcon(getClass().getResource("/Recursos/CONTENEDOR2.png"))); // NOI18N
        PanelContenedor2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnModificar.setBackground(new java.awt.Color(255, 255, 255));
        btnModificar.setBackgroundHover(new java.awt.Color(204, 102, 0));
        btnModificar.setForegroundText(new java.awt.Color(204, 102, 0));
        btnModificar.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.EDIT);
        btnModificar.setRippleColor(java.awt.Color.white);
        btnModificar.setTypeBorder(RSMaterialComponent.RSButtonIconUno.TYPEBORDER.CIRCLE);
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        PanelContenedor2.add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 11, 50, 50));

        Separador2.setForeground(new java.awt.Color(204, 204, 204));
        PanelContenedor2.add(Separador2, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 66, 76, 3));

        LabelTitulo2.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        LabelTitulo2.setForeground(new java.awt.Color(0, 102, 102));
        LabelTitulo2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LabelTitulo2.setText("MODIFICAR");
        PanelContenedor2.add(LabelTitulo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 73, 76, -1));

        jPanel2.add(PanelContenedor2, new org.netbeans.lib.awtextra.AbsoluteConstraints(102, 3, 100, 100));

        PanelContenedor.setImagen(new javax.swing.ImageIcon(getClass().getResource("/Recursos/CONTENEDOR2.png"))); // NOI18N
        PanelContenedor.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnEliminar.setBackground(new java.awt.Color(255, 255, 255));
        btnEliminar.setBackgroundHover(new java.awt.Color(51, 51, 51));
        btnEliminar.setForegroundText(new java.awt.Color(51, 51, 51));
        btnEliminar.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.DELETE);
        btnEliminar.setRippleColor(java.awt.Color.white);
        btnEliminar.setTypeBorder(RSMaterialComponent.RSButtonIconUno.TYPEBORDER.CIRCLE);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        PanelContenedor.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 11, 50, 50));

        Separador.setForeground(new java.awt.Color(204, 204, 204));
        PanelContenedor.add(Separador, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 66, 76, 3));

        LabelTitulo.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        LabelTitulo.setForeground(new java.awt.Color(0, 102, 102));
        LabelTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LabelTitulo.setText("ELIMINAR");
        PanelContenedor.add(LabelTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 73, 76, -1));

        jPanel2.add(PanelContenedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(199, 3, 100, 100));

        panelCabecera.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 300, 100));

        btnSalir.setBackground(new java.awt.Color(0, 102, 102));
        btnSalir.setBackgroundHover(new java.awt.Color(255, 0, 0));
        btnSalir.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.CLOSE);
        btnSalir.setOpaque(true);
        btnSalir.setPreferredSize(new java.awt.Dimension(15, 15));
        btnSalir.setRequestFocusEnabled(false);
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        panelCabecera.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 0, -1, -1));

        txtIDBoca.setEditable(false);
        txtIDBoca.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        panelCabecera.add(txtIDBoca, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 70, 50, -1));

        txtCaja.setEditable(false);
        txtCaja.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        panelCabecera.add(txtCaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 40, 80, -1));

        btnListarDatos.setBackground(new java.awt.Color(255, 255, 255));
        btnListarDatos.setToolTipText("Listar rendición de valores");
        btnListarDatos.setBackgroundHover(new java.awt.Color(0, 102, 102));
        btnListarDatos.setForegroundText(new java.awt.Color(0, 102, 102));
        btnListarDatos.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.MONETIZATION_ON);
        btnListarDatos.setRippleColor(java.awt.Color.white);
        btnListarDatos.setTypeBorder(RSMaterialComponent.RSButtonIconUno.TYPEBORDER.CIRCLE);
        btnListarDatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListarDatosActionPerformed(evt);
            }
        });
        panelCabecera.add(btnListarDatos, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 70, 25, 25));

        txtFechaCaja.setEditable(false);
        panelCabecera.add(txtFechaCaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 40, 120, -1));

        txtEstado.setEditable(false);
        panelCabecera.add(txtEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 40, 180, -1));

        panelFondo.add(panelCabecera, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 1, 945, 105));

        lbCaja.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        lbCaja.setForeground(new java.awt.Color(204, 102, 0));
        lbCaja.setText("Correspondiente a la Caja N°:");
        lbCaja.setToolTipText("");
        panelFondo.add(lbCaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 110, 200, 25));

        jLabel2.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Mostrar Valores de");
        jLabel2.setToolTipText("");
        panelFondo.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 110, 25));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), "Resumen de valores", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Roboto", 1, 11))); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtGastos.setEditable(false);
        txtGastos.setBackground(new java.awt.Color(255, 255, 255));
        txtGastos.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        txtGastos.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtGastos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel1.add(txtGastos, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, 120, 23));

        jLabel6.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel6.setText("Resumen de Gastos");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 110, 23));

        jLabel1.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel1.setText("Resumen de Retiros");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 110, 23));

        jLabel3.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel3.setText("Resumen de Cierre");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 110, 23));

        txtCierres.setEditable(false);
        txtCierres.setBackground(new java.awt.Color(255, 255, 255));
        txtCierres.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        txtCierres.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCierres.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel1.add(txtCierres, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 80, 120, 23));

        txtRetiros.setEditable(false);
        txtRetiros.setBackground(new java.awt.Color(255, 255, 255));
        txtRetiros.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        txtRetiros.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtRetiros.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel1.add(txtRetiros, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, 120, 23));

        btnModificar1.setBackground(new java.awt.Color(255, 255, 255));
        btnModificar1.setBackgroundHover(new java.awt.Color(204, 102, 0));
        btnModificar1.setForegroundText(new java.awt.Color(204, 102, 0));
        btnModificar1.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.PRINT);
        btnModificar1.setRippleColor(java.awt.Color.white);
        btnModificar1.setTypeBorder(RSMaterialComponent.RSButtonIconUno.TYPEBORDER.CIRCLE);
        btnModificar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificar1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnModificar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 110, 30, 30));

        panelFondo.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 410, 250, 150));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), "Resumen de Monedas y Billetes", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Roboto", 1, 11))); // NOI18N
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setText("50");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, 23));

        jLabel8.setText("100");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, 23));

        jLabel9.setText("500");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, -1, 23));

        jLabel10.setText("1.000");
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, -1, 23));

        jLabel11.setText("2.000");
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 20, -1, 23));

        txt2000.setEditable(false);
        txt2000.setBackground(new java.awt.Color(255, 255, 255));
        txt2000.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        txt2000.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt2000.setText("0");
        txt2000.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        txt2000.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt2000ActionPerformed(evt);
            }
        });
        txt2000.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt2000KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt2000KeyReleased(evt);
            }
        });
        jPanel3.add(txt2000, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 20, 60, 23));

        txt1000.setEditable(false);
        txt1000.setBackground(new java.awt.Color(255, 255, 255));
        txt1000.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        txt1000.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt1000.setText("0");
        txt1000.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        txt1000.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt1000ActionPerformed(evt);
            }
        });
        txt1000.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt1000KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt1000KeyReleased(evt);
            }
        });
        jPanel3.add(txt1000, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 50, 60, 23));

        txt500.setEditable(false);
        txt500.setBackground(new java.awt.Color(255, 255, 255));
        txt500.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        txt500.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt500.setText("0");
        txt500.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        txt500.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt500ActionPerformed(evt);
            }
        });
        txt500.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt500KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt500KeyReleased(evt);
            }
        });
        jPanel3.add(txt500, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, 60, 23));

        txt100.setEditable(false);
        txt100.setBackground(new java.awt.Color(255, 255, 255));
        txt100.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        txt100.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt100.setText("0");
        txt100.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        txt100.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt100ActionPerformed(evt);
            }
        });
        txt100.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt100KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt100KeyReleased(evt);
            }
        });
        jPanel3.add(txt100, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, 60, 23));

        txt50.setEditable(false);
        txt50.setBackground(new java.awt.Color(255, 255, 255));
        txt50.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        txt50.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt50.setText("0");
        txt50.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        txt50.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt50ActionPerformed(evt);
            }
        });
        txt50.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt50KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt50KeyReleased(evt);
            }
        });
        jPanel3.add(txt50, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 60, 23));

        jLabel12.setText("5.000");
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 50, -1, 23));

        jLabel13.setText("10.000");
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 20, -1, 23));

        jLabel14.setText("20.000");
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 50, -1, 23));

        jLabel15.setText("50.000");
        jPanel3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 20, -1, 23));

        jLabel16.setText("100.000");
        jPanel3.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 50, -1, 23));

        txt100000.setEditable(false);
        txt100000.setBackground(new java.awt.Color(255, 255, 255));
        txt100000.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        txt100000.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt100000.setText("0");
        txt100000.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        txt100000.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt100000ActionPerformed(evt);
            }
        });
        txt100000.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt100000KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt100000KeyReleased(evt);
            }
        });
        jPanel3.add(txt100000, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 50, 60, 23));

        txt50000.setEditable(false);
        txt50000.setBackground(new java.awt.Color(255, 255, 255));
        txt50000.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        txt50000.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt50000.setText("0");
        txt50000.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        txt50000.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt50000ActionPerformed(evt);
            }
        });
        txt50000.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt50000KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt50000KeyReleased(evt);
            }
        });
        jPanel3.add(txt50000, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 20, 60, 23));

        txt20000.setEditable(false);
        txt20000.setBackground(new java.awt.Color(255, 255, 255));
        txt20000.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        txt20000.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt20000.setText("0");
        txt20000.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        txt20000.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt20000ActionPerformed(evt);
            }
        });
        txt20000.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt20000KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt20000KeyReleased(evt);
            }
        });
        jPanel3.add(txt20000, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 50, 60, 23));

        txt10000.setEditable(false);
        txt10000.setBackground(new java.awt.Color(255, 255, 255));
        txt10000.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        txt10000.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt10000.setText("0");
        txt10000.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        txt10000.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt10000ActionPerformed(evt);
            }
        });
        txt10000.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt10000KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt10000KeyReleased(evt);
            }
        });
        jPanel3.add(txt10000, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 20, 60, 23));

        txt5000.setEditable(false);
        txt5000.setBackground(new java.awt.Color(255, 255, 255));
        txt5000.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        txt5000.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt5000.setText("0");
        txt5000.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        txt5000.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt5000ActionPerformed(evt);
            }
        });
        txt5000.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt5000KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt5000KeyReleased(evt);
            }
        });
        jPanel3.add(txt5000, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 50, 60, 23));

        panelFondo.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 410, 580, 81));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelFondo, javax.swing.GroupLayout.PREFERRED_SIZE, 947, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelFondo, javax.swing.GroupLayout.PREFERRED_SIZE, 568, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void panelFondoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_panelFondoFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_panelFondoFocusGained

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        try {
            dlgArreglo arr = new dlgArreglo(null, true);
            arr.setLocationRelativeTo(null);
            dlgArreglo.txtEvento.setText("N");
            arr.setVisible(true);

        } catch (Exception e) {
            Mensajes.informacion("Servidor no esta activo");
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        // TODO add your handling code here:
        ModificarValores();
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        if (tablaDetalles.getSelectedRow() < 0) {
            Notif.NotifyError("Notificación del sistema", "No es posible procesar la eliminación.\r\nSeleccione un registro de valores de la tabla.");
        } else {
            int resp = Mensajes.confirmar("¿Seguro que desea eliminar esta información de valores?");
            if (resp == JOptionPane.YES_OPTION) {
                EliminarValores();
            }
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        int rpta = Mensajes.confirmar("¿Seguro que desea salir del formulario?");
        if (rpta == 0) {
            try {
                dlgCajaDia.Inicializacion();
            } catch (Exception e) {
            }
            
            try {
                dlgCajaDia2.Inicializacion();
            } catch (Exception e) {
            }
            this.dispose();
        }
    }//GEN-LAST:event_btnSalirActionPerformed

    private void cboBocaCobranzaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboBocaCobranzaActionPerformed
        // TODO add your handling code here:
        try {
            String id = cargarComboBox.getCodidgo(cboBocaCobranza);
            txtIDBoca.setText(id);
        } catch (Exception e) {
            txtIDBoca.setText("");
        }
        CargarTabla();
        CargarResumen();
    }//GEN-LAST:event_cboBocaCobranzaActionPerformed

    private void cboBocaCobranzaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cboBocaCobranzaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboBocaCobranzaKeyPressed

    private void btnListarDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListarDatosActionPerformed
        // TODO add your handling code here:
        CargarTabla();
        CargarResumen();
    }//GEN-LAST:event_btnListarDatosActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
        btnListarDatosActionPerformed(null);
    }//GEN-LAST:event_formWindowActivated

    private void txt2000ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt2000ActionPerformed
        // TODO add your handling code here:
        txt5000.requestFocus();
        txt5000.selectAll();
    }//GEN-LAST:event_txt2000ActionPerformed

    private void txt2000KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt2000KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt2000KeyPressed

    private void txt2000KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt2000KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txt2000KeyReleased

    private void txt1000ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt1000ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt1000ActionPerformed

    private void txt1000KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt1000KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt1000KeyPressed

    private void txt1000KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt1000KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txt1000KeyReleased

    private void txt500ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt500ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt500ActionPerformed

    private void txt500KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt500KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt500KeyPressed

    private void txt500KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt500KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txt500KeyReleased

    private void txt100ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt100ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt100ActionPerformed

    private void txt100KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt100KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt100KeyPressed

    private void txt100KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt100KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txt100KeyReleased

    private void txt50ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt50ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt50ActionPerformed

    private void txt50KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt50KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt50KeyPressed

    private void txt50KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt50KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txt50KeyReleased

    private void txt100000ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt100000ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt100000ActionPerformed

    private void txt100000KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt100000KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt100000KeyPressed

    private void txt100000KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt100000KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txt100000KeyReleased

    private void txt50000ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt50000ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt50000ActionPerformed

    private void txt50000KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt50000KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt50000KeyPressed

    private void txt50000KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt50000KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txt50000KeyReleased

    private void txt20000ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt20000ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt20000ActionPerformed

    private void txt20000KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt20000KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt20000KeyPressed

    private void txt20000KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt20000KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txt20000KeyReleased

    private void txt10000ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt10000ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt10000ActionPerformed

    private void txt10000KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt10000KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt10000KeyPressed

    private void txt10000KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt10000KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txt10000KeyReleased

    private void txt5000ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt5000ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt5000ActionPerformed

    private void txt5000KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt5000KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt5000KeyPressed

    private void txt5000KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt5000KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txt5000KeyReleased

    private void btnModificar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificar1ActionPerformed
        // TODO add your handling code here:
        if (cboBocaCobranza.getSelectedIndex() == 0) {
            Mensajes.Sistema("Especifique la boca de cobranza de la cual se desea imprimir el comprobante de valores");
            cboBocaCobranza.requestFocus();
            cboBocaCobranza.setPopupVisible(true);
        } else {
            String cierre = generarCodigos.ObtenerCodigo2("SELECT concepto FROM arreglo_caja_2 WHERE idcaja=" + dlgRegistroValores.txtCaja.getText() + " AND idboca=" + txtIDBoca.getText() + " AND concepto='C' AND estado='S'");
            System.out.println("Cierre: " + cierre);
            if (cierre == null) {
                Mensajes.Alerta("Imposible generar comprobante.\nEl cierre de valores aún no se ha registrado.\nProceda a registrar el cierre y luego vuelva a intentarlo");
            } else {
                int rpta = Mensajes.confirmar("¿Seguro que desea Imprimir el comprobante de valores pos-cierre?");
                if (rpta == 0) {
                    imprimirTicket();
                }

            }
        }


    }//GEN-LAST:event_btnModificar1ActionPerformed

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
            java.util.logging.Logger.getLogger(dlgRegistroValores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(dlgRegistroValores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(dlgRegistroValores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(dlgRegistroValores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                dlgRegistroValores dialog = new dlgRegistroValores(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel LabelTitulo;
    private javax.swing.JLabel LabelTitulo1;
    private javax.swing.JLabel LabelTitulo2;
    private rojeru_san.rspanel.RSPanelImage PanelContenedor;
    private rojeru_san.rspanel.RSPanelImage PanelContenedor1;
    private rojeru_san.rspanel.RSPanelImage PanelContenedor2;
    private javax.swing.JSeparator Separador;
    private javax.swing.JSeparator Separador1;
    private javax.swing.JSeparator Separador2;
    private RSMaterialComponent.RSButtonIconUno btnAdd;
    private RSMaterialComponent.RSButtonIconUno btnEliminar;
    private RSMaterialComponent.RSButtonIconUno btnListarDatos;
    private RSMaterialComponent.RSButtonIconUno btnModificar;
    private RSMaterialComponent.RSButtonIconUno btnModificar1;
    private RSMaterialComponent.RSButtonIconOne btnSalir;
    public static RSMaterialComponent.RSComboBox cboBocaCobranza;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField4;
    private static javax.swing.JLabel lbCaja;
    private javax.swing.JPanel panelCabecera;
    private javax.swing.JPanel panelFondo;
    public static final javax.swing.JTable tablaDetalles = new javax.swing.JTable();
    public static javax.swing.JTextField txt100;
    public static javax.swing.JTextField txt1000;
    public static javax.swing.JTextField txt10000;
    public static javax.swing.JTextField txt100000;
    public static javax.swing.JTextField txt2000;
    public static javax.swing.JTextField txt20000;
    public static javax.swing.JTextField txt50;
    public static javax.swing.JTextField txt500;
    public static javax.swing.JTextField txt5000;
    public static javax.swing.JTextField txt50000;
    public static javax.swing.JTextField txtCaja;
    private static javax.swing.JTextField txtCierres;
    public static javax.swing.JTextField txtEstado;
    public static javax.swing.JTextField txtFechaCaja;
    private static javax.swing.JTextField txtGastos;
    private static javax.swing.JTextField txtIDBoca;
    private static javax.swing.JTextField txtRetiros;
    // End of variables declaration//GEN-END:variables
public static void imprimirTicket() {
        //Impresora matricial tmu-22
        PrinterService printerService = new PrinterService();

        final byte[] openCD = {27, 112, 0, 60, 120};
        printerService.printBytes2(Timbrado.getImpresora(), openCD);

        System.out.println(printerService.getPrinters());
        DecimalFormat formateador = new DecimalFormat("#,###");
        String Ticket = "         " + Empresa.getEmpresa() + "\n";
        Ticket += "           VENTAS DE LACTEOS LACTOLANDA\n";
        Ticket += "                 RUC: " + Empresa.getRUC() + "\n";
        Ticket += "               CEL: " + Empresa.getCelular() + "\n";
        Ticket += Empresa.getDireccion() + "\n";
        Ticket += "     CNEL. OVIEDO - DPTO. DE CAAGUAZU - PY\n";
        Ticket += "-----------------------------------------------\n";
        Ticket += "COMPROBANTE DE VALORES POS-CIERRE\n";
        Ticket += "FECHA/HORA: " + Fecha.fechaFormulario() + " " + Fecha.darHoraSinSS() + "\n";
        Ticket += "EMISOR: " + Login.getUsuarioLogueado() + "\n";
        Ticket += "PERFIL EMISOR: " + Login.getPerfil() + "\n";
        Ticket += "===============================================\n";
        Ticket += "           DETALLE DE LOS REGISTROS\n";
        Ticket += "DESCRIPCION              CANTIDAD\n";
        Ticket += "MONEDA  DE      50:         " + txt50.getText() + "\n";
        Ticket += "MONEDA  DE     100:         " + txt100.getText() + "\n";
        Ticket += "MONEDA  DE     500:         " + txt500.getText() + "\n";
        Ticket += "MONEDA  DE   1.000:         " + txt1000.getText() + "\n";
        Ticket += "BILLETE DE   2.000:         " + txt2000.getText() + "\n";
        Ticket += "BILLETE DE   5.000:         " + txt5000.getText() + "\n";
        Ticket += "BILLETE DE  10.000:         " + txt10000.getText() + "\n";
        Ticket += "BILLETE DE  20.000:         " + txt20000.getText() + "\n";
        Ticket += "BILLETE DE  50.000:         " + txt50000.getText() + "\n";
        Ticket += "BILLETE DE 100.000:         " + txt100000.getText() + "\n\n";
        Ticket += "              RESUMEN DE VALORES\n";
        Ticket += "DESCRIPCION              MONTO\n";
        Ticket += "RESUMEN GASTOS:          " + txtGastos.getText() + "\n";
        Ticket += "RESUMEN RETIROS:         " + txtRetiros.getText() + "\n";
        Ticket += "CIERRE:                  " + txtCierres.getText() + "\n";
        int resumen = Integer.parseInt(txtRetiros.getText().replace(".", "").replace(",", "")) + Integer.parseInt(txtCierres.getText().replace(".", "").replace(",", ""));
        Ticket += "RETIROS+CIERRE:          " + formateador.format(resumen) + "\n";
        Ticket += "===============================================\n";
        Ticket += "CAJA NRO: " + txtCaja.getText().trim() + "\n";
        Ticket += "FECHA DE LA CAJA: " + txtFechaCaja.getText() + "\n";
        Ticket += "ESTADO: " + txtEstado.getText() + "\n";
        Ticket += "IDBOCA: " + txtIDBoca.getText().trim() + " - " + cboBocaCobranza.getSelectedItem().toString() + "\n";
        Ticket += "\n";
        Ticket += "\n";
        Ticket += "\n";
        Ticket += "\n";
        try {
            printerService.printString2(Timbrado.getImpresora(), Ticket);
            byte[] cutP = new byte[]{0x1d, 'V', 1};
            printerService.printBytes2(Timbrado.getImpresora(), cutP);
        } catch (Exception e) {
            Mensajes.error("No se encuentra instalada la impresora predeterminada para este punto de impresión");
        }

    }

}
