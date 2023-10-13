package IU;

import Componentes.DataSourceService;
import Componentes.DataSourceService1;
import Componentes.Mensajes;
import Componentes.Software;
import Componentes.cargarComboBox;
import Componentes.cargarComboBoxMovil;
import Componentes.validarCampos;
import Controladores.CabecerasTablas;
import Controladores.ControlLogeo;
import Controladores.controlPuntoEmisionMovil;
import Datos.GestionarPuntoEmisionMovil;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.sql.*;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

public class dlgPuntoEmisionMovil extends javax.swing.JDialog {

    CabecerasTablas cabe = new CabecerasTablas();
    static DataSourceService1 dss1 = new DataSourceService1();
    static DataSourceService dss = new DataSourceService();

    public dlgPuntoEmisionMovil(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        cargarComboBoxMovil.cargar(cboTimbrado, "SELECT * FROM timbrado WHERE estado='Activo'");
        cargarComboBox.cargar(cbBoca_de_cobranza, "SELECT * FROM laboratorio WHERE lab_indicador='S'");
        titulo();
        cabe.PuntoEmision(tbPuntoEmision);
        controlPuntoEmisionMovil.listPuntoEmision(tbPuntoEmision);
        Estados();
    }

    final void titulo() {
        if (Software.getSoftware().equals("null")) {
            this.setTitle("Gestionar Punto de Emisión");
        } else if (Software.getSoftware().isEmpty()) {
            this.setTitle("Gestionar Punto de Emisión");
        } else {
            this.setTitle(Software.getSoftware() + " - Gestionar Punto de Emisión");
        }
    }

    private void Estados() {
        if (rbActivo.isSelected()) {
            etiEstado.setText("ACTIVO");
        } else {
            etiEstado.setText("INACTIVO");
        }
    }

    public void Cancelar() {
        btnNuevo.setEnabled(true);
        btnModificar.setEnabled(false);
        btnGuardar.setEnabled(false);
        btnCancelar.setEnabled(false);
        btnEliminar.setEnabled(false);
        tbPuntoEmision.setVisible(true);
        //
        cboTimbrado.setEnabled(false);
        cbBoca_de_cobranza.setEnabled(false);
        btnBuscarImpresora.setEnabled(false);
        txtEstablecimiento.setEnabled(false);
        txtEmision.setEnabled(false);
        txtDireccion.setEnabled(false);
        txtFInicio.setEnabled(false);
        txtFFin.setEnabled(false);
        txtFActual.setEnabled(false);
        txtNVenta.setEnabled(false);
        cbAMovil.setEnabled(false);
        rbActivo.setEnabled(false);
        rbInactivo.setEnabled(false);
        cboTipo2.setEnabled(false);
        limpiarCampos();
        btnNuevo.requestFocus();
    }

    public void modcboBocaCobranza(int idBoca) {
        DefaultComboBoxModel ml = new DefaultComboBoxModel();
        String sql = "SELECT * FROM laboratorio WHERE lab_indicador='S'";
        String sql2 = "SELECT * FROM laboratorio WHERE lab_codigo=" + idBoca;
        try (Connection cn = dss.getDataSource().getConnection(); Statement st = cn.createStatement(); ResultSet rs = st.executeQuery(sql); ResultSet rss = st.executeQuery(sql2)) {
            ml.addElement("SELEC. UNA OPCIÓN");
            while (rs.next()) {
                ml.addElement(rs.getString("lab_nombre"));
            }
            rss.first();
            Object descripcion = (Object) rss.getString("lab_nombre");
            cbBoca_de_cobranza.setModel(ml);
            cbBoca_de_cobranza.setSelectedItem(descripcion);
            rs.close();
            rss.close();
            st.close();
            cn.close();
        } catch (SQLException ew) {
            //Mensajes.error("TIENES UN ERROR AL CARGAR LOS LABORATORIOS: "+ew.getMessage().toUpperCase());
        }
    }

    public void modcboTimbrado(String idTimbrado) {
        DefaultComboBoxModel ml = new DefaultComboBoxModel();
        String sql = "SELECT * FROM timbrado WHERE estado='Activo'";
        String sql2 = "SELECT * FROM timbrado WHERE idtimbrado=" + idTimbrado;
        try (Connection cn = dss1.getDataSource().getConnection(); Statement st = cn.createStatement(); ResultSet rs = st.executeQuery(sql); ResultSet rss = st.executeQuery(sql2)) {
            ml.addElement("SELEC. UNA OPCIÓN");
            while (rs.next()) {
                ml.addElement(rs.getString("descripcion"));
            }
            rss.first();
            Object descripcion = (Object) rss.getString("descripcion");
            try {
                if (rss.getRow() != 0) {
                    lbFechaTimbrado.setText("VALIDEZ: " + rss.getString(3) + " - " + rss.getString(4));
                    txtEstablecimiento.requestFocus();
                } else {
                    System.out.println("No se puede cargar Fecha timbrado.");
                }
            } catch (SQLException ee) {
                System.out.println(ee.getMessage());
            }
            cboTimbrado.setModel(ml);
            cboTimbrado.setSelectedItem(descripcion);
            rs.close();
            rss.close();
            st.close();
            cn.close();
        } catch (SQLException ew) {
            //Mensajes.error("TIENES UN ERROR AL CARGAR LOS LABORATORIOS: "+ew.getMessage().toUpperCase());
        }

        /*try {
            try {
                prepararBD();
                rs = sentencia.executeQuery("select * from timbrado where idtimbrado=" + idTimbrado);
                rs.first();
                try {
                    if (rs.getRow() != 0) {
                        lbFechaTimbrado.setText("VALIDEZ: " + rs.getString(3) + " - " + rs.getString(4));
                        txtEstablecimiento.requestFocus();
                    } else {
                        System.out.println("No se puede cargar Fecha timbrado.");
                    }
                } catch (SQLException ee) {
                    System.out.println(ee.getMessage());
                }
                rs.close();
                con.close();
                conG.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

        } catch (Exception e) {
        }*/
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupActivo = new javax.swing.ButtonGroup();
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
        cboTimbrado = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        txtEstablecimiento = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtEmision = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtFInicio = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtFFin = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtFActual = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtNVenta = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        txtIP = new javax.swing.JTextField();
        rbActivo = new javax.swing.JRadioButton();
        rbInactivo = new javax.swing.JRadioButton();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel13 = new javax.swing.JLabel();
        etiEstado = new javax.swing.JLabel();
        cboTipo2 = new javax.swing.JComboBox<>();
        cbAMovil = new rojerusan.RSCheckBox();
        lbFechaTimbrado = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtImpresora = new javax.swing.JTextField();
        btnBuscarImpresora = new javax.swing.JButton();
        cbBoca_de_cobranza = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbPuntoEmision = new javax.swing.JTable()
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

        Oscuro.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)));
        Oscuro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/0-102-102.png"))); // NOI18N
        Oscuro.setPreferredSize(new java.awt.Dimension(690, 418));

        jPanel1.setLayout(new java.awt.GridLayout(1, 9));

        btnNuevo.setFont(new java.awt.Font("Roboto", 1, 9)); // NOI18N
        btnNuevo.setForeground(new java.awt.Color(0, 102, 102));
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/document30.png"))); // NOI18N
        btnNuevo.setText("NUEVO");
        btnNuevo.setToolTipText("Nuevo - F3");
        btnNuevo.setFocusPainted(false);
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
        btnModificar.setFocusPainted(false);
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
        btnGuardar.setFocusPainted(false);
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
        btnCancelar.setFocusPainted(false);
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
        btnEliminar.setFocusPainted(false);
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rSButtonIconOne1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(OscuroLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        OscuroLayout.setVerticalGroup(
            OscuroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OscuroLayout.createSequentialGroup()
                .addComponent(rSButtonIconOne1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel2.setOpaque(false);

        jLabel3.setText("ID");

        txtCod.setEditable(false);
        txtCod.setBackground(new java.awt.Color(255, 255, 255));
        txtCod.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        txtCod.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCod.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel2.setText("Timbrado");

        cboTimbrado.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        cboTimbrado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        cboTimbrado.setEnabled(false);
        cboTimbrado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTimbradoActionPerformed(evt);
            }
        });
        cboTimbrado.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                cboTimbradoPropertyChange(evt);
            }
        });
        cboTimbrado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cboTimbradoKeyPressed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        jLabel6.setText("Establecimiento");

        txtEstablecimiento.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtEstablecimiento.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtEstablecimiento.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        txtEstablecimiento.setEnabled(false);
        txtEstablecimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEstablecimientoActionPerformed(evt);
            }
        });
        txtEstablecimiento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtEstablecimientoKeyPressed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        jLabel7.setText("P. Emisión");

        txtEmision.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtEmision.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtEmision.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        txtEmision.setEnabled(false);
        txtEmision.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmisionActionPerformed(evt);
            }
        });
        txtEmision.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtEmisionKeyPressed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        jLabel8.setText("Dirección");

        txtDireccion.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtDireccion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        txtDireccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDireccionActionPerformed(evt);
            }
        });
        txtDireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDireccionKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDireccionKeyTyped(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        jLabel9.setText("Factura Inicio");

        txtFInicio.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtFInicio.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFInicio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        txtFInicio.setEnabled(false);
        txtFInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFInicioActionPerformed(evt);
            }
        });
        txtFInicio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtFInicioKeyPressed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        jLabel10.setText("Factura Fin");

        txtFFin.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtFFin.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFFin.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        txtFFin.setEnabled(false);
        txtFFin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFFinActionPerformed(evt);
            }
        });
        txtFFin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtFFinKeyPressed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        jLabel11.setText("Factura Actual");

        txtFActual.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtFActual.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFActual.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        txtFActual.setEnabled(false);
        txtFActual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFActualActionPerformed(evt);
            }
        });
        txtFActual.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtFActualKeyPressed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        jLabel12.setText("N° Venta");

        txtNVenta.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtNVenta.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNVenta.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        txtNVenta.setEnabled(false);
        txtNVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNVentaActionPerformed(evt);
            }
        });
        txtNVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNVentaKeyPressed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)));
        jPanel3.setOpaque(false);

        txtIP.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N
        txtIP.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtIP.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        txtIP.setEnabled(false);
        txtIP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtIPKeyPressed(evt);
            }
        });

        grupActivo.add(rbActivo);
        rbActivo.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        rbActivo.setText("P. Emisión Activo");
        rbActivo.setEnabled(false);
        rbActivo.setIconTextGap(6);
        rbActivo.setOpaque(false);
        rbActivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbActivoActionPerformed(evt);
            }
        });

        grupActivo.add(rbInactivo);
        rbInactivo.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        rbInactivo.setSelected(true);
        rbInactivo.setText("P. Emisión Inactivo");
        rbInactivo.setEnabled(false);
        rbInactivo.setIconTextGap(6);
        rbInactivo.setOpaque(false);
        rbInactivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbInactivoActionPerformed(evt);
            }
        });

        jSeparator2.setForeground(new java.awt.Color(204, 204, 204));

        jLabel13.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("IP");

        etiEstado.setBackground(new java.awt.Color(0, 102, 102));
        etiEstado.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        etiEstado.setForeground(new java.awt.Color(255, 255, 255));
        etiEstado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiEstado.setOpaque(true);

        cboTipo2.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        cboTipo2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TICKET", "FACTURA LEGAL" }));
        cboTipo2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        cboTipo2.setEnabled(false);

        cbAMovil.setForeground(new java.awt.Color(0, 0, 0));
        cbAMovil.setSelected(true);
        cbAMovil.setText("APLICACIÓN MÓVIL");
        cbAMovil.setColorCheck(new java.awt.Color(0, 102, 102));
        cbAMovil.setColorUnCheck(new java.awt.Color(153, 153, 153));
        cbAMovil.setFocusPainted(false);
        cbAMovil.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        cbAMovil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbAMovilActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(rbActivo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rbInactivo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(6, 6, 6))
            .addComponent(etiEstado, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cboTipo2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIP)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator2)
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbAMovil, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(73, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cboTipo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(cbAMovil, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIP, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(rbActivo, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbInactivo, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(etiEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        lbFechaTimbrado.setBackground(new java.awt.Color(0, 102, 102));
        lbFechaTimbrado.setForeground(new java.awt.Color(255, 255, 255));
        lbFechaTimbrado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbFechaTimbrado.setOpaque(true);

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        jLabel1.setText("Impresora Predet.");

        txtImpresora.setEditable(false);
        txtImpresora.setBackground(new java.awt.Color(255, 255, 255));
        txtImpresora.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtImpresora.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        btnBuscarImpresora.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Find.png"))); // NOI18N
        btnBuscarImpresora.setEnabled(false);
        btnBuscarImpresora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarImpresoraActionPerformed(evt);
            }
        });

        cbBoca_de_cobranza.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        cbBoca_de_cobranza.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        cbBoca_de_cobranza.setEnabled(false);
        cbBoca_de_cobranza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbBoca_de_cobranzaActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        jLabel4.setText("Boca de Cobranza");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbFechaTimbrado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCod, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtEstablecimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtEmision, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cboTimbrado, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtFActual, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                                    .addComponent(txtFInicio))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtNVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(12, 12, 12)
                                        .addComponent(txtFFin))))
                            .addComponent(txtDireccion, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtImpresora, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscarImpresora, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(cbBoca_de_cobranza, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbBoca_de_cobranza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtImpresora, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBuscarImpresora, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCod, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(cboTimbrado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbFechaTimbrado, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtEmision, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEstablecimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtFFin, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFActual, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        tbPuntoEmision.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        tbPuntoEmision.setModel(new javax.swing.table.DefaultTableModel(
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
        tbPuntoEmision.setGridColor(new java.awt.Color(204, 204, 204));
        tbPuntoEmision.setRowHeight(20);
        tbPuntoEmision.setSelectionBackground(new java.awt.Color(0, 102, 102));
        tbPuntoEmision.setShowVerticalLines(false);
        tbPuntoEmision.getTableHeader().setResizingAllowed(false);
        tbPuntoEmision.getTableHeader().setReorderingAllowed(false);
        tbPuntoEmision.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPuntoEmisionMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tbPuntoEmisionMouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(tbPuntoEmision);

        javax.swing.GroupLayout BlancoLayout = new javax.swing.GroupLayout(Blanco);
        Blanco.setLayout(BlancoLayout);
        BlancoLayout.setHorizontalGroup(
            BlancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BlancoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(Oscuro, javax.swing.GroupLayout.DEFAULT_SIZE, 1042, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
        );
        BlancoLayout.setVerticalGroup(
            BlancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BlancoLayout.createSequentialGroup()
                .addComponent(Oscuro, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Blanco, javax.swing.GroupLayout.PREFERRED_SIZE, 1044, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Blanco, javax.swing.GroupLayout.DEFAULT_SIZE, 546, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here:
        String cod = GestionarPuntoEmisionMovil.getCodigo();
        cargarComboBoxMovil.cargar(cboTimbrado, "SELECT * FROM timbrado WHERE estado='Activo'");
        cargarComboBox.cargar(cbBoca_de_cobranza, "SELECT * FROM laboratorio WHERE lab_indicador='S'");
        txtCod.setText(cod);
        btnNuevo.setEnabled(false);
        btnModificar.setEnabled(false);
        btnGuardar.setEnabled(true);
        btnCancelar.setEnabled(true);
        btnEliminar.setEnabled(false);
        tbPuntoEmision.setVisible(false);
        //
        cboTimbrado.setEnabled(true);
        cbBoca_de_cobranza.setEnabled(true);
        btnBuscarImpresora.setEnabled(true);
        txtEstablecimiento.setEnabled(true);
        txtEmision.setEnabled(true);
        txtDireccion.setEnabled(true);
        txtFInicio.setEnabled(true);
        txtFFin.setEnabled(true);
        txtFActual.setEnabled(true);
        txtNVenta.setEnabled(true);
        cbAMovil.setEnabled(true);
        rbActivo.setEnabled(true);
        rbInactivo.setEnabled(true);
        cboTipo2.setEnabled(true);
        //
        CabecerasTablas.limpiarTablas(tbPuntoEmision);
        controlPuntoEmisionMovil.listPuntoEmision(tbPuntoEmision);
        cboTimbrado.requestFocus();
        cboTimbrado.setPopupVisible(true);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        try {
            int resp = JOptionPane.showConfirmDialog(this, "¿Seguro que desea Eliminar el registro?", "Eliminar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (resp == JOptionPane.YES_OPTION) {
                controlPuntoEmisionMovil.delPuntoEmision();
                CabecerasTablas.limpiarTablas(tbPuntoEmision);
                controlPuntoEmisionMovil.listPuntoEmision(tbPuntoEmision);
                Cancelar();
            }
        } catch (HeadlessException ee) {
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        // TODO add your handling code here:
        if (cboTimbrado.getSelectedIndex() == 0) {
            Mensajes.informacion("Seleccione un Timbrado");
            cboTimbrado.requestFocus();
            cboTimbrado.setPopupVisible(true);
        } else if (txtEstablecimiento.getText().trim().isEmpty()) {
            Mensajes.informacion("Ingrese el Establecimiento");
            txtEstablecimiento.requestFocus();
        } else if (txtEmision.getText().trim().isEmpty()) {
            Mensajes.informacion("Ingrese el Punto de Emisión");
            txtEmision.requestFocus();
        } else if (txtDireccion.getText().trim().isEmpty()) {
            Mensajes.informacion("Ingrese la Dirección");
            txtDireccion.requestFocus();
        } else if (txtFInicio.getText().trim().isEmpty()) {
            Mensajes.informacion("Ingrese número para el inicio de las facturaciones");
            txtFInicio.requestFocus();
        } else if (txtFFin.getText().trim().isEmpty()) {
            Mensajes.informacion("Ingrese número para la finalización de las facturaciones");
            txtFFin.requestFocus();
        } else if (txtFActual.getText().trim().isEmpty()) {
            Mensajes.informacion("Ingrese número actual de la facturación");
            txtFActual.requestFocus();
        } else if (txtNVenta.getText().trim().isEmpty()) {
            Mensajes.informacion("Ingrese número de venta actual - Infomación requerido para la Aplicación Fact-Express");
            txtNVenta.requestFocus();
        } else if (cbBoca_de_cobranza.getSelectedIndex() == 0) {
            Mensajes.informacion("Seleccione la boca de cobranza asociada a este punto de emisión");
            cbBoca_de_cobranza.requestFocus();
        } else if (txtImpresora.getText().isEmpty()) {
            Mensajes.informacion("Debe seleccionar la impresora predeterminada");
            btnBuscarImpresora.requestFocus();
        } else if (!cbAMovil.isSelected()) {
            if (txtIP.getText().trim().isEmpty()) {
                Mensajes.informacion("Ingrese la dirección IP de la terminar que utilizara este Punto de Emisión");
                txtIP.requestFocus();
            } else {
                try {
                    int resp = JOptionPane.showConfirmDialog(this, "¿Seguro que desea Modificar el registro?", "Modificar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (resp == JOptionPane.YES_OPTION) {
                        controlPuntoEmisionMovil.actPuntoEmision();
                        controlPuntoEmisionMovil.actRef();
                        CabecerasTablas.limpiarTablas(tbPuntoEmision);
                        controlPuntoEmisionMovil.listPuntoEmision(tbPuntoEmision);
                        Cancelar();
                    }
                } catch (HeadlessException ee) {
                }
            }
        } else {
            try {
                int resp = JOptionPane.showConfirmDialog(this, "¿Seguro que desea Modificar el registro?", "Modificar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (resp == JOptionPane.YES_OPTION) {
                    controlPuntoEmisionMovil.actPuntoEmision();
                    controlPuntoEmisionMovil.actRef();
                    CabecerasTablas.limpiarTablas(tbPuntoEmision);
                    controlPuntoEmisionMovil.listPuntoEmision(tbPuntoEmision);
                    Cancelar();
                }
            } catch (HeadlessException ee) {
            }
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        if (cboTimbrado.getSelectedIndex() == 0) {
            Mensajes.informacion("Seleccione un Timbrado");
            cboTimbrado.requestFocus();
            cboTimbrado.setPopupVisible(true);
        } else if (txtEstablecimiento.getText().trim().isEmpty()) {
            Mensajes.informacion("Ingrese el Establecimiento");
            txtEstablecimiento.requestFocus();
        } else if (txtEmision.getText().trim().isEmpty()) {
            Mensajes.informacion("Ingrese el Punto de Emisión");
            txtEmision.requestFocus();
        } else if (txtDireccion.getText().trim().isEmpty()) {
            Mensajes.informacion("Ingrese la Dirección");
            txtDireccion.requestFocus();
        } else if (txtFInicio.getText().trim().isEmpty()) {
            Mensajes.informacion("Ingrese número para el inicio de las facturaciones");
            txtFInicio.requestFocus();
        } else if (txtFFin.getText().trim().isEmpty()) {
            Mensajes.informacion("Ingrese número para la finalización de las facturaciones");
            txtFFin.requestFocus();
        } else if (txtFActual.getText().trim().isEmpty()) {
            Mensajes.informacion("Ingrese número actual de la facturación");
            txtFActual.requestFocus();
        } else if (txtNVenta.getText().trim().isEmpty()) {
            Mensajes.informacion("Ingrese número de venta actual - Infomación requerido para la Aplicación Fact-Express");
            txtNVenta.requestFocus();
        } else if (cbBoca_de_cobranza.getSelectedIndex() == 0) {
            Mensajes.informacion("Seleccione la boca de cobranza asociada a este punto de emisión");
            cbBoca_de_cobranza.requestFocus();
        } else if (txtImpresora.getText().isEmpty()) {
            Mensajes.informacion("Debe seleccionar la impresora predeterminada");
            btnBuscarImpresora.requestFocus();
        } else if (!cbAMovil.isSelected()) {
            if (txtIP.getText().trim().isEmpty()) {
                Mensajes.informacion("Ingrese la dirección IP de la terminar que utilizara este Punto de Emisión");
                txtIP.requestFocus();
            } else {
                try {
                    int resp = JOptionPane.showConfirmDialog(this, "¿Seguro que desea insertar el registro?", "Insertar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (resp == JOptionPane.YES_OPTION) {
                        String cod = GestionarPuntoEmisionMovil.getCodigo();
                        txtCod.setText(cod);
                        controlPuntoEmisionMovil.addPuntoEmision();
                        controlPuntoEmisionMovil.addRef();
                        CabecerasTablas.limpiarTablas(tbPuntoEmision);
                        controlPuntoEmisionMovil.listPuntoEmision(tbPuntoEmision);
                        Cancelar();
                    }
                } catch (HeadlessException ee) {
                }
            }
        } else {
            try {
                int resp = JOptionPane.showConfirmDialog(this, "¿Seguro que desea insertar el registro?", "Insertar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (resp == JOptionPane.YES_OPTION) {
                    String cod = GestionarPuntoEmisionMovil.getCodigo();
                    txtCod.setText(cod);
                    controlPuntoEmisionMovil.addPuntoEmision();
                    controlPuntoEmisionMovil.addRef();
                    CabecerasTablas.limpiarTablas(tbPuntoEmision);
                    controlPuntoEmisionMovil.listPuntoEmision(tbPuntoEmision);
                    Cancelar();
                }
            } catch (HeadlessException ee) {
            }
        }


    }//GEN-LAST:event_btnGuardarActionPerformed

    private void tbPuntoEmisionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPuntoEmisionMouseClicked
        // TODO add your handling code here:
        try {
            btnNuevo.setEnabled(false);
            btnModificar.setEnabled(true);
            btnGuardar.setEnabled(false);
            btnCancelar.setEnabled(true);
            btnEliminar.setEnabled(true);
            tbPuntoEmision.setVisible(true);
            //
            cboTimbrado.setEnabled(false);
            cbBoca_de_cobranza.setEnabled(true);
            btnBuscarImpresora.setEnabled(true);
            txtEstablecimiento.setEnabled(true);
            txtEmision.setEnabled(true);
            txtDireccion.setEnabled(true);
            txtFInicio.setEnabled(true);
            txtFFin.setEnabled(true);
            txtFActual.setEnabled(true);
            txtNVenta.setEnabled(true);
            cbAMovil.setEnabled(true);
            rbActivo.setEnabled(true);
            rbInactivo.setEnabled(true);
            cboTipo2.setEnabled(true);

            int fila = tbPuntoEmision.getSelectedRow();
            String cod = tbPuntoEmision.getValueAt(fila, 0).toString();
            System.out.println(cod);
            String itTimbrado = tbPuntoEmision.getValueAt(fila, 1).toString();
            String establecimiento = tbPuntoEmision.getValueAt(fila, 3).toString();
            String puntoEmision = tbPuntoEmision.getValueAt(fila, 4).toString();
            String direccion = tbPuntoEmision.getValueAt(fila, 5).toString();
            String fi = tbPuntoEmision.getValueAt(fila, 6).toString();
            String ff = tbPuntoEmision.getValueAt(fila, 7).toString();
            String fa = tbPuntoEmision.getValueAt(fila, 8).toString();
            String nv = tbPuntoEmision.getValueAt(fila, 9).toString();
            String tipo = tbPuntoEmision.getValueAt(fila, 10).toString();
            String tipo2 = tbPuntoEmision.getValueAt(fila, 11).toString();
            String ip = tbPuntoEmision.getValueAt(fila, 12).toString();
            String estado = tbPuntoEmision.getValueAt(fila, 13).toString();
            int idboca = Integer.parseInt(tbPuntoEmision.getValueAt(fila, 14).toString());
            String boca = tbPuntoEmision.getValueAt(fila, 15).toString();
            String impresora = tbPuntoEmision.getValueAt(fila, 16).toString();

            txtCod.setText(cod);
            modcboTimbrado(itTimbrado);
            txtEstablecimiento.setText(establecimiento);
            txtEmision.setText(puntoEmision);
            txtDireccion.setText(direccion);
            txtFInicio.setText(fi);
            txtFFin.setText(ff);
            txtFActual.setText(fa);
            txtNVenta.setText(nv);
            if (tipo.equals("M")) {
                cbAMovil.setSelected(true);
                cbAMovilActionPerformed(null);
            } else {
                cbAMovil.setSelected(false);
                txtIP.setEnabled(true);
            }
            txtIP.setText(ip);
            if (tipo2.equals("T")) {
                cboTipo2.setSelectedIndex(0);
            } else if (tipo2.equals("F")) {
                cboTipo2.setSelectedIndex(1);
            }
            if (estado.equals("Inactivo")) {
                rbInactivo.setSelected(true);
            } else {
                rbActivo.setSelected(true);
            }

            modcboBocaCobranza(idboca);
            txtImpresora.setText(impresora);
            txtEstablecimiento.requestFocus();
        } catch (NumberFormatException e) {
            System.out.println("Error Punto emision Mouse clicked: " + e.getMessage());
        }

    }//GEN-LAST:event_tbPuntoEmisionMouseClicked

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        int rpta = Mensajes.confirmar("¿Seguro que desea Cancelar?");
        if (rpta == 0) {
            btnNuevo.setEnabled(true);
            btnModificar.setEnabled(false);
            btnGuardar.setEnabled(false);
            btnCancelar.setEnabled(false);
            btnEliminar.setEnabled(false);
            tbPuntoEmision.setVisible(true);
            //
            cboTimbrado.setEnabled(false);
            cbBoca_de_cobranza.setEnabled(false);
            btnBuscarImpresora.setEnabled(false);
            txtEstablecimiento.setEnabled(false);
            txtEmision.setEnabled(false);
            txtDireccion.setEnabled(false);
            txtFInicio.setEnabled(false);
            txtFFin.setEnabled(false);
            txtFActual.setEnabled(false);
            txtNVenta.setEnabled(false);
            cbAMovil.setEnabled(false);
            rbActivo.setEnabled(false);
            rbInactivo.setEnabled(false);
            cboTipo2.setEnabled(false);
            limpiarCampos();
            btnNuevo.requestFocus();
        }

    }//GEN-LAST:event_btnCancelarActionPerformed

    private void txtFInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFInicioActionPerformed
        // TODO add your handling code here:
        txtFFin.requestFocus();
    }//GEN-LAST:event_txtFInicioActionPerformed

    private void txtEmisionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmisionKeyPressed
        // TODO add your handling code here:
        validarCampos.soloNumeros(txtEmision);
        validarCampos.cantCaracteres(txtEmision, 3);
    }//GEN-LAST:event_txtEmisionKeyPressed

    private void txtEmisionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmisionActionPerformed
        // TODO add your handling code here:
        txtDireccion.requestFocus();
    }//GEN-LAST:event_txtEmisionActionPerformed

    private void txtEstablecimientoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEstablecimientoKeyPressed
        // TODO add your handling code here:
        validarCampos.soloNumeros(txtEstablecimiento);
        validarCampos.cantCaracteres(txtEstablecimiento, 3);
    }//GEN-LAST:event_txtEstablecimientoKeyPressed

    private void txtEstablecimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEstablecimientoActionPerformed
        // TODO add your handling code here:
        txtEmision.requestFocus();
    }//GEN-LAST:event_txtEstablecimientoActionPerformed

    private void cboTimbradoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cboTimbradoKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (cboTimbrado.getSelectedIndex() == 0) {
                lbFechaTimbrado.setText("");
            } else {
                int idT = Integer.parseInt(cargarComboBoxMovil.getCodidgo(cboTimbrado));
                String sql = "select * from timbrado where idtimbrado=" + idT;
                try (Connection cn = dss1.getDataSource().getConnection(); Statement st = cn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
                    rs.first();
                    try {
                        if (rs.getRow() != 0) {
                            lbFechaTimbrado.setText("VALIDEZ: " + rs.getString(3) + " - " + rs.getString(4));
                            txtEstablecimiento.requestFocus();
                        } else {
                            System.out.println("No se puede cargar Fecha timbrado.");
                        }
                    } catch (SQLException ee) {
                        System.out.println(ee.getMessage());
                    }
                    rs.close();
                    st.close();
                    cn.close();
                } catch (SQLException ex) {
                    System.out.println("Error cboTimbradoKeyPressed" + ex.getMessage());
                }
                txtEstablecimiento.requestFocus();
            }

        }
    }//GEN-LAST:event_cboTimbradoKeyPressed

    private void cboTimbradoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTimbradoActionPerformed
        // TODO add your handling code here:
        if (cboTimbrado.getSelectedIndex() == 0) {
            lbFechaTimbrado.setText("");
        } else {
            int idT = 0;
            String sqlx = "Select * from timbrado where descripcion=" + cboTimbrado.getSelectedItem()+" and estado='Activo'";
            try (Connection cnx = dss1.getDataSource().getConnection(); Statement st = cnx.createStatement(); ResultSet rsx = st.executeQuery(sqlx)) {
                rsx.last();
                if (rsx.getRow() != 0) {
                    idT = rsx.getInt("idtimbrado");
                } else {
                    System.out.println("Busqueda sin resultados");
                }
            } catch (Exception e) {
                System.out.println("Error obteniendo ID de timbrado");
            }
            String sql = "select * from timbrado where idtimbrado=" + idT;
            try (Connection cn = dss1.getDataSource().getConnection(); Statement st = cn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
                rs.first();
                try {
                    if (rs.getRow() != 0) {
                        lbFechaTimbrado.setText("VALIDEZ: " + rs.getString(3) + " - " + rs.getString(4));
                        txtEstablecimiento.requestFocus();
                    } else {
                        System.out.println("No se puede cargar Fecha timbrado.");
                    }
                } catch (SQLException ee) {
                    System.out.println(ee.getMessage());
                }
                rs.close();
                st.close();
                cn.close();
            } catch (SQLException ex) {
                System.out.println("Error cboTimbradoActionPerformed: " + ex.getMessage());
            }
        }
    }//GEN-LAST:event_cboTimbradoActionPerformed

    private void rbInactivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbInactivoActionPerformed
        // TODO add your handling code here:
        Estados();
    }//GEN-LAST:event_rbInactivoActionPerformed

    private void rbActivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbActivoActionPerformed
        // TODO add your handling code here:
        Estados();
    }//GEN-LAST:event_rbActivoActionPerformed

    private void txtIPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIPKeyPressed
        // TODO add your handling code here:
        validarCampos.soloDecimales(txtIP);
        validarCampos.cantCaracteres(txtIP, 15);
    }//GEN-LAST:event_txtIPKeyPressed

    private void txtDireccionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDireccionKeyPressed
        // TODO add your handling code here:
        validarCampos.cantCaracteres(txtDireccion, 50);
    }//GEN-LAST:event_txtDireccionKeyPressed

    private void txtDireccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDireccionActionPerformed
        // TODO add your handling code here:
        txtFInicio.requestFocus();
    }//GEN-LAST:event_txtDireccionActionPerformed

    private void txtFInicioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFInicioKeyPressed
        // TODO add your handling code here:
        validarCampos.soloNumeros(txtFInicio);
        validarCampos.cantCaracteres(txtFInicio, 3);
    }//GEN-LAST:event_txtFInicioKeyPressed

    private void txtFFinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFFinActionPerformed
        // TODO add your handling code here:
        txtFActual.requestFocus();
    }//GEN-LAST:event_txtFFinActionPerformed

    private void txtFFinKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFFinKeyPressed
        // TODO add your handling code here:
        validarCampos.soloNumeros(txtFFin);
        validarCampos.cantCaracteres(txtFFin, 10);
    }//GEN-LAST:event_txtFFinKeyPressed

    private void txtFActualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFActualActionPerformed
        // TODO add your handling code here:
        txtNVenta.requestFocus();
    }//GEN-LAST:event_txtFActualActionPerformed

    private void txtFActualKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFActualKeyPressed
        // TODO add your handling code here:
        validarCampos.soloNumeros(txtFActual);
        validarCampos.cantCaracteres(txtFActual, 6);
    }//GEN-LAST:event_txtFActualKeyPressed

    private void txtNVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNVentaActionPerformed
        // TODO add your handling code here:
        cbAMovil.requestFocus();
    }//GEN-LAST:event_txtNVentaActionPerformed

    private void txtNVentaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNVentaKeyPressed
        // TODO add your handling code here:
        validarCampos.soloNumeros(txtNVenta);
        validarCampos.cantCaracteres(txtNVenta, 6);
    }//GEN-LAST:event_txtNVentaKeyPressed

    private void txtDireccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDireccionKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (Character.isLowerCase(c)) {
            String cad = ("" + c).toUpperCase();
            c = cad.charAt(0);
            evt.setKeyChar(c);
        }
    }//GEN-LAST:event_txtDireccionKeyTyped

    private void tbPuntoEmisionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPuntoEmisionMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tbPuntoEmisionMouseEntered

    private void rSButtonIconOne1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIconOne1ActionPerformed
        // TODO add your handling code here:
        int rpta = Mensajes.confirmar("¿Seguro que desea salir del formulario?");
        if (rpta == 0) {
            ControlLogeo.Timbrado_Ticket();
            this.dispose();
        }
    }//GEN-LAST:event_rSButtonIconOne1ActionPerformed

    private void cbAMovilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbAMovilActionPerformed
        // TODO add your handling code here:
        if (cbAMovil.isSelected()) {
            txtIP.setEnabled(false);
        } else {
            txtIP.setEnabled(true);
            txtIP.requestFocus();
            rbActivo.doClick();
        }
    }//GEN-LAST:event_cbAMovilActionPerformed

    private void btnBuscarImpresoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarImpresoraActionPerformed
        // TODO add your handling code here:
        try {
            dlgImpresorasPE imp = new dlgImpresorasPE(null, true);
            imp.setLocationRelativeTo(null);
            imp.setVisible(true);
        } catch (Exception e) {
            Mensajes.informacion("No hay conexión con el servidor");
        }
    }//GEN-LAST:event_btnBuscarImpresoraActionPerformed

    private void cboTimbradoPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_cboTimbradoPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_cboTimbradoPropertyChange

    private void cbBoca_de_cobranzaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbBoca_de_cobranzaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbBoca_de_cobranzaActionPerformed

    void limpiarCampos() {
        txtCod.setText("");
        cboTimbrado.setSelectedIndex(0);
        lbFechaTimbrado.setText("");
        txtEstablecimiento.setText("");
        txtEmision.setText("");
        txtDireccion.setText("");
        txtFInicio.setText("");
        txtFFin.setText("");
        txtFActual.setText("");
        txtNVenta.setText("");
        txtIP.setText("");
        cbAMovil.setSelected(true);
        rbInactivo.setSelected(true);
        tbPuntoEmision.clearSelection();
        cboTipo2.setSelectedIndex(0);
        cbBoca_de_cobranza.setSelectedIndex(0);
        txtImpresora.setText("");
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
            java.util.logging.Logger.getLogger(dlgPuntoEmisionMovil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        //</editor-fold>
        java.awt.EventQueue.invokeLater(() -> {
            dlgPuntoEmisionMovil dialog = new dlgPuntoEmisionMovil(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnBuscarImpresora;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    public static rojerusan.RSCheckBox cbAMovil;
    public static javax.swing.JComboBox<String> cbBoca_de_cobranza;
    public static javax.swing.JComboBox<String> cboTimbrado;
    public static javax.swing.JComboBox<String> cboTipo2;
    private javax.swing.JLabel etiEstado;
    private javax.swing.ButtonGroup grupActivo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lbFechaTimbrado;
    private RSMaterialComponent.RSButtonIconOne rSButtonIconOne1;
    public static javax.swing.JRadioButton rbActivo;
    public static javax.swing.JRadioButton rbInactivo;
    private javax.swing.JTable tbPuntoEmision;
    public static javax.swing.JTextField txtCod;
    public static javax.swing.JTextField txtDireccion;
    public static javax.swing.JTextField txtEmision;
    public static javax.swing.JTextField txtEstablecimiento;
    public static javax.swing.JTextField txtFActual;
    public static javax.swing.JTextField txtFFin;
    public static javax.swing.JTextField txtFInicio;
    public static javax.swing.JTextField txtIP;
    public static javax.swing.JTextField txtImpresora;
    public static javax.swing.JTextField txtNVenta;
    // End of variables declaration//GEN-END:variables
}
