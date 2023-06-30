package IU;

import Componentes.ConexionBD;
import Componentes.Fecha;
import Componentes.Mensajes;
import Componentes.Reporte;
import Componentes.Software;
import Componentes.generarCodigos;
import Componentes.validarCampos;
import Controladores.ControlCaja;
import Datos.GestionarCaja;
import Modelo.Caja;
import java.awt.HeadlessException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import org.mariadb.jdbc.MariaDbConnection;
import org.mariadb.jdbc.MariaDbStatement;

public final class dlgCajaDia extends javax.swing.JDialog {

    public String NCaja;
    public int ING;
    public int GAS;
    public int INI;

    public Reporte jasper;

    public static ResultSet rs;
    public static MariaDbConnection con;
    public static MariaDbStatement sentencia;
    public static MariaDbConnection conMovil;
    public static MariaDbStatement sentenciaMovil;

    public dlgCajaDia(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        jasper = new Reporte();
        titulo();
        Invisible();
        txtDesde.setText(Fecha.formatoFecha(dcDesde.getText()));
        txtHasta.setText(Fecha.formatoFecha(dcHasta.getText()));

        try {
            NCaja = (generarCodigos.ObtenerCodigo("SELECT ca_id from caja where ca_indicador='S' ORDER BY ca_id DESC LIMIT 1"));
            System.out.println("N CAja hoy: " + NCaja);
            Caja caj = GestionarCaja.busCaja(NCaja);
            DecimalFormat df = new DecimalFormat("#,###");
            lbInicial.setText(df.format(Integer.valueOf(String.valueOf(caj.getCaInicial()).trim().replace(".", "").replace(",", ""))));
            INI = ((caj.getCaInicial()));
            lbNCaja.setText(String.valueOf(caj.getCaId()));
            lbFecha.setText(Fecha.formatoFechaFF(caj.getFechaI()));
            lbHora.setText(Fecha.FormatoHoraSinSSString(caj.getHoraI()));
            lbUsuI.setText(String.valueOf(caj.getUsuarioI()));
            txtEntregado.setText(df.format(caj.getCaEntregado()));
            txtGastos.setText(df.format(caj.getCaGastos()));
            txtDiferencia.setText(df.format(caj.getCaDiferencia()));
            if (caj.getIndicador().equals("S")) {
                lbEstado.setText("ABIERTO");
            } else {
                lbEstado.setText("CERRADO");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        totalVentasCont();
        totalVentasCred();
        totalCompraCont();
        totalCompraCred();
        totalGasto();
        totalSalidas();
        totalIngreso();
        gastoTotal();
        ingresoTotal();
        totalCaja();
        calcular();
        totalVentasContBoca1();
        Opcion_1();
        Boca_1();
        Gastos_1();
        calcularDiferencia1();
        totalVentasContBoca2();
        Opcion_2();
        Boca_2();
        Gastos_2();
        calcularDiferencia2();
        EntregadoGral();
        GastosGral();
        DiferenciaGral();
    }

    final void titulo() {
        if (Software.getSoftware().equals("null")) {
            this.setTitle("Cerrar movimientos del día");
        } else {
            this.setTitle(Software.getSoftware() + " - Cerrar movimientos del día");
        }
    }

    public void calcular() {
        int entregar, gastos, entregado, dif;
        entregar = Integer.parseInt(txtEntregar.getText().trim().replace(",", "").replace(".", ""));
        entregado = Integer.parseInt(txtEntregado.getText().trim().replace(",", "").replace(".", ""));
        gastos = Integer.parseInt(txtGastos.getText().trim().replace(",", "").replace(".", ""));
        if (entregar >= 0) {
            dif = (entregado + gastos) - entregar;
            DecimalFormat df = new DecimalFormat("#,###");
            txtDiferencia.setText((df.format(dif)));
        }

    }

    public static void prepararBD() {
        try {
            con = (MariaDbConnection) new ConexionBD().getConexion();
            conMovil = (MariaDbConnection) new ConexionBD().getConexionMovil();
            if (con == null) {
                System.out.println("No hay Conexion con la Base de Datos");
            } else {
                sentencia = (MariaDbStatement) con.createStatement();
            }
            if (conMovil == null) {
                System.out.println("No hay Conexion con la Base de Datos Movil");
            } else {
                sentenciaMovil = (MariaDbStatement) conMovil.createStatement();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void CalcularDiferencia(String desde, String hasta) {
        prepararBD();
        try {
            DecimalFormat df = new DecimalFormat("#,###");
            StringBuilder sql = new StringBuilder("SELECT SUM(ca_diferencia) FROM caja WHERE ca_fechainicio>='");
            sql.append(desde);
            sql.append("' AND ca_fechainicio<='");
            sql.append(hasta).append("'");
            rs = sentencia.executeQuery(sql.toString());
            rs.first();
            txtDifAcumulada.setText(df.format(rs.getInt(1)));
            rs.close();
        } catch (SQLException ex) {
            Mensajes.error("Error obteniendo las diferencias acumuladas: " + ex.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel12 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        txtHasta = new javax.swing.JTextField();
        txtDesde = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        btnCerrar = new newscomponents.RSButtonBigIcon_new();
        btnImprimir = new newscomponents.RSButtonBigIcon_new();
        btnGuardar = new newscomponents.RSButtonBigIcon_new();
        rSButtonIconOne1 = new RSMaterialComponent.RSButtonIconOne();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel9 = new javax.swing.JPanel();
        Blanco = new org.edisoncor.gui.panel.PanelImage();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        txtEntregar = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtDiferencia = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtEntregado = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtGastos = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        lbInicial = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        txtTotalGastos = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtGastoTotal = new javax.swing.JTextField();
        txtTotalCompraC = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        txtTotalCompra = new javax.swing.JTextField();
        btnRegistrarSalidas = new newscomponents.RSButtonGradientIcon_new();
        txtTotalSalida = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        jPanel10 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        lbNCaja = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        lbFecha = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lbUsuI = new javax.swing.JLabel();
        lbEstado = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lbHora = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        txtDifAcumulada = new javax.swing.JLabel();
        dcDesde = new datechooser.beans.DateChooserCombo();
        jLabel3 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        dcHasta = new datechooser.beans.DateChooserCombo();
        jLabel2 = new javax.swing.JLabel();
        btnCalcular = new newscomponents.RSButtonGradientIcon_new();
        jPanel2 = new javax.swing.JPanel();
        txtTotalVentas = new javax.swing.JTextField();
        txtTotalIngreso = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtIngresoT = new javax.swing.JTextField();
        txtTotalVentasC = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel11 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        txtB1 = new javax.swing.JTextField();
        txtB2 = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txtEntregado_boca_1 = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        txtGastos_boca_1 = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        txtDiferencia_boca_1 = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        txtEntregado_boca_2 = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        txtGastos_boca_2 = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        txtDiferencia_boca_2 = new javax.swing.JTextField();
        btnGuardar1 = new newscomponents.RSButtonBigIcon_new();
        btnGuardar2 = new newscomponents.RSButtonBigIcon_new();
        txtOpcion2 = new javax.swing.JTextField();
        txtOpcion1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        setUndecorated(true);
        setResizable(false);

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)));

        jPanel8.setBackground(new java.awt.Color(0, 102, 102));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel8.add(txtHasta, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 50, 74, -1));
        jPanel8.add(txtDesde, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 20, 74, -1));

        jPanel5.setOpaque(false);
        jPanel5.setLayout(new java.awt.GridLayout(1, 9));

        btnCerrar.setBackground(new java.awt.Color(0, 102, 102));
        btnCerrar.setBorder(null);
        btnCerrar.setText("CERRAR CAJA");
        btnCerrar.setBgHover(new java.awt.Color(6, 125, 125));
        btnCerrar.setFocusPainted(false);
        btnCerrar.setFont(new java.awt.Font("Roboto", 1, 10)); // NOI18N
        btnCerrar.setIconTextGap(0);
        btnCerrar.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.COMPARE_ARROWS);
        btnCerrar.setPixels(0);
        btnCerrar.setSizeIcon(50.0F);
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });
        jPanel5.add(btnCerrar);

        btnImprimir.setBackground(new java.awt.Color(0, 102, 102));
        btnImprimir.setBorder(null);
        btnImprimir.setText("IMPRIMIR");
        btnImprimir.setBgHover(new java.awt.Color(6, 125, 125));
        btnImprimir.setEnabled(false);
        btnImprimir.setFocusPainted(false);
        btnImprimir.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        btnImprimir.setIconTextGap(0);
        btnImprimir.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.PRINT);
        btnImprimir.setPixels(0);
        btnImprimir.setSizeIcon(50.0F);
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });
        jPanel5.add(btnImprimir);

        btnGuardar.setBackground(new java.awt.Color(0, 102, 102));
        btnGuardar.setBorder(null);
        btnGuardar.setText("GUARDAR");
        btnGuardar.setBgHover(new java.awt.Color(6, 125, 125));
        btnGuardar.setFocusPainted(false);
        btnGuardar.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        btnGuardar.setIconTextGap(0);
        btnGuardar.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.SAVE);
        btnGuardar.setPixels(0);
        btnGuardar.setSizeIcon(50.0F);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel5.add(btnGuardar);

        jPanel8.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 270, 80));

        rSButtonIconOne1.setBackground(new java.awt.Color(0, 102, 102));
        rSButtonIconOne1.setBackgroundHover(new java.awt.Color(255, 0, 0));
        rSButtonIconOne1.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.CLOSE);
        rSButtonIconOne1.setOpaque(true);
        rSButtonIconOne1.setPreferredSize(new java.awt.Dimension(15, 15));
        rSButtonIconOne1.setRequestFocusEnabled(false);
        rSButtonIconOne1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonIconOne1ActionPerformed(evt);
            }
        });
        jPanel8.add(rSButtonIconOne1, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 0, 15, 15));

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jTabbedPane1.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        jTabbedPane1.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        Blanco.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        Blanco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/fondoBlanco.jpg"))); // NOI18N
        Blanco.setPreferredSize(new java.awt.Dimension(690, 418));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel4.setOpaque(false);

        jLabel17.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 102, 0));
        jLabel17.setText("EFECTIVO A ENTREGAR");

        txtEntregar.setBackground(new java.awt.Color(255, 255, 255));
        txtEntregar.setFont(new java.awt.Font("Roboto Black", 0, 17)); // NOI18N
        txtEntregar.setForeground(new java.awt.Color(0, 102, 0));
        txtEntregar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtEntregar.setText("0");
        txtEntregar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtEntregar.setOpaque(true);

        jLabel18.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel18.setText("EFECTIVO PRESENTADO");

        txtDiferencia.setBackground(new java.awt.Color(255, 255, 255));
        txtDiferencia.setFont(new java.awt.Font("Roboto Black", 0, 17)); // NOI18N
        txtDiferencia.setForeground(new java.awt.Color(205, 0, 0));
        txtDiferencia.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtDiferencia.setText("0");
        txtDiferencia.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtDiferencia.setOpaque(true);

        jLabel20.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(205, 0, 0));
        jLabel20.setText("DIFERENCIA DEL DÍA");

        txtEntregado.setFont(new java.awt.Font("Roboto Black", 0, 17)); // NOI18N
        txtEntregado.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtEntregado.setText("0");
        txtEntregado.setEnabled(false);
        txtEntregado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEntregadoActionPerformed(evt);
            }
        });
        txtEntregado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtEntregadoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEntregadoKeyReleased(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel21.setText("GASTOS PRESENTADOS");

        txtGastos.setFont(new java.awt.Font("Roboto Black", 0, 17)); // NOI18N
        txtGastos.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtGastos.setText("0");
        txtGastos.setEnabled(false);
        txtGastos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGastosActionPerformed(evt);
            }
        });
        txtGastos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtGastosKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtGastosKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDiferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEntregar, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEntregado, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGastos, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEntregar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEntregado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGastos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDiferencia))
                .addGap(6, 6, 6))
        );

        jLabel6.setBackground(new java.awt.Color(0, 102, 102));
        jLabel6.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel6.setText("  Efectivo base inicializado para los movimientos del día:   ");
        jLabel6.setOpaque(true);

        lbInicial.setBackground(new java.awt.Color(0, 102, 102));
        lbInicial.setFont(new java.awt.Font("Roboto Black", 1, 17)); // NOI18N
        lbInicial.setForeground(new java.awt.Color(255, 255, 255));
        lbInicial.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbInicial.setText("0");
        lbInicial.setOpaque(true);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(0, 0, 0)
                        .addComponent(lbInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbInicial, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        txtTotalGastos.setEditable(false);
        txtTotalGastos.setBackground(new java.awt.Color(255, 255, 255));
        txtTotalGastos.setFont(new java.awt.Font("Roboto Black", 0, 14)); // NOI18N
        txtTotalGastos.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTotalGastos.setText("0");

        jLabel13.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel13.setText("Suma de gastos, pagos y/o retiros:");

        jLabel15.setFont(new java.awt.Font("Roboto Black", 0, 12)); // NOI18N
        jLabel15.setText("TOTAL DE EGRESOS DEL DEL DÍA:");

        txtGastoTotal.setEditable(false);
        txtGastoTotal.setBackground(new java.awt.Color(255, 255, 255));
        txtGastoTotal.setFont(new java.awt.Font("Roboto Black", 0, 14)); // NOI18N
        txtGastoTotal.setForeground(new java.awt.Color(255, 0, 0));
        txtGastoTotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtGastoTotal.setText("0");

        txtTotalCompraC.setEditable(false);
        txtTotalCompraC.setBackground(new java.awt.Color(255, 255, 255));
        txtTotalCompraC.setFont(new java.awt.Font("Roboto Black", 0, 14)); // NOI18N
        txtTotalCompraC.setForeground(new java.awt.Color(0, 0, 102));
        txtTotalCompraC.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTotalCompraC.setText("0");

        jLabel12.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel12.setText("Compras a crédito");

        jSeparator2.setForeground(new java.awt.Color(204, 204, 204));

        jLabel9.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel9.setText("Compras a contado:");

        txtTotalCompra.setEditable(false);
        txtTotalCompra.setBackground(new java.awt.Color(255, 255, 255));
        txtTotalCompra.setFont(new java.awt.Font("Roboto Black", 0, 14)); // NOI18N
        txtTotalCompra.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTotalCompra.setText("0");

        btnRegistrarSalidas.setText("REGISTRAR VENCIDOS");
        btnRegistrarSalidas.setColorPrimario(new java.awt.Color(204, 0, 0));
        btnRegistrarSalidas.setColorPrimarioHover(new java.awt.Color(255, 51, 0));
        btnRegistrarSalidas.setColorSecundario(new java.awt.Color(255, 51, 0));
        btnRegistrarSalidas.setColorSecundarioHover(new java.awt.Color(204, 0, 0));
        btnRegistrarSalidas.setFocusPainted(false);
        btnRegistrarSalidas.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        btnRegistrarSalidas.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.CHEVRON_LEFT);
        btnRegistrarSalidas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarSalidasActionPerformed(evt);
            }
        });

        txtTotalSalida.setEditable(false);
        txtTotalSalida.setBackground(new java.awt.Color(255, 255, 255));
        txtTotalSalida.setFont(new java.awt.Font("Roboto Black", 0, 14)); // NOI18N
        txtTotalSalida.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTotalSalida.setText("0");

        jSeparator3.setForeground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jSeparator3)
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator2)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtTotalCompraC, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtGastoTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                            .addComponent(txtTotalGastos)))
                                    .addComponent(txtTotalCompra, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(8, 8, 8))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnRegistrarSalidas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTotalSalida, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTotalGastos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(8, 8, 8)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGastoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTotalCompraC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTotalCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRegistrarSalidas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTotalSalida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6))
        );

        jPanel10.setBackground(new java.awt.Color(0, 102, 102));

        jLabel16.setFont(new java.awt.Font("Roboto Black", 0, 15)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("MOVIMIENTO DIARIO N°");

        lbNCaja.setFont(new java.awt.Font("Roboto Black", 0, 15)); // NOI18N
        lbNCaja.setForeground(new java.awt.Color(255, 255, 255));
        lbNCaja.setText("NCaja");

        jPanel6.setOpaque(false);

        jLabel4.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel4.setForeground(java.awt.Color.white);
        jLabel4.setText("Fecha:");

        lbFecha.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        lbFecha.setForeground(java.awt.Color.white);
        lbFecha.setText("FECHA ");

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel1.setForeground(java.awt.Color.white);
        jLabel1.setText("Realizado por:");

        lbUsuI.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        lbUsuI.setForeground(java.awt.Color.white);
        lbUsuI.setText("USUSARIO");

        lbEstado.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        lbEstado.setForeground(java.awt.Color.white);
        lbEstado.setText("ESTADO");

        jLabel19.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel19.setForeground(java.awt.Color.white);
        jLabel19.setText("Estado actual:");

        jLabel7.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel7.setForeground(java.awt.Color.white);
        jLabel7.setText("Hora:");

        lbHora.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        lbHora.setForeground(java.awt.Color.white);
        lbHora.setText("HORA");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbHora, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbFecha, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbEstado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbUsuI, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lbFecha))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(lbHora))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lbUsuI))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(lbEstado))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel7.setOpaque(false);

        txtDifAcumulada.setBackground(new java.awt.Color(0, 102, 102));
        txtDifAcumulada.setFont(new java.awt.Font("Roboto Black", 0, 20)); // NOI18N
        txtDifAcumulada.setForeground(new java.awt.Color(255, 255, 255));
        txtDifAcumulada.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtDifAcumulada.setText("0");
        txtDifAcumulada.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtDifAcumulada.setOpaque(true);

        dcDesde.setFieldFont(new java.awt.Font("Roboto Medium", java.awt.Font.PLAIN, 14));
        dcDesde.addCommitListener(new datechooser.events.CommitListener() {
            public void onCommit(datechooser.events.CommitEvent evt) {
                dcDesdeOnCommit(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("DESDE");

        jLabel11.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("HASTA");

        dcHasta.setFieldFont(new java.awt.Font("Roboto Medium", java.awt.Font.PLAIN, 14));
        dcHasta.addCommitListener(new datechooser.events.CommitListener() {
            public void onCommit(datechooser.events.CommitEvent evt) {
                dcHastaOnCommit(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(0, 102, 102));
        jLabel2.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Diferencia acumulada");
        jLabel2.setOpaque(true);

        btnCalcular.setText("CALCULAR");
        btnCalcular.setColorPrimario(new java.awt.Color(204, 0, 0));
        btnCalcular.setColorPrimarioHover(new java.awt.Color(255, 51, 0));
        btnCalcular.setColorSecundario(new java.awt.Color(255, 51, 0));
        btnCalcular.setColorSecundarioHover(new java.awt.Color(204, 0, 0));
        btnCalcular.setFocusPainted(false);
        btnCalcular.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        btnCalcular.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.EQUALIZER);
        btnCalcular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalcularActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDifAcumulada, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel3))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(dcDesde, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(dcHasta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnCalcular, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(dcDesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(dcHasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCalcular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDifAcumulada)
                .addGap(6, 6, 6))
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbNCaja, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(3, 3, 3))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(lbNCaja))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(54, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        txtTotalVentas.setEditable(false);
        txtTotalVentas.setBackground(new java.awt.Color(255, 255, 255));
        txtTotalVentas.setFont(new java.awt.Font("Roboto Black", 0, 14)); // NOI18N
        txtTotalVentas.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTotalVentas.setText("0");

        txtTotalIngreso.setEditable(false);
        txtTotalIngreso.setBackground(new java.awt.Color(255, 255, 255));
        txtTotalIngreso.setFont(new java.awt.Font("Roboto Black", 0, 14)); // NOI18N
        txtTotalIngreso.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTotalIngreso.setText("0");

        jLabel5.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setText("Valor de la ventas a contado:");

        jLabel8.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel8.setText("Valor de los otros ingresos:");

        jLabel14.setFont(new java.awt.Font("Roboto Black", 0, 12)); // NOI18N
        jLabel14.setText("TOTAL  DE INGRESOS DEL DÍA:");

        txtIngresoT.setEditable(false);
        txtIngresoT.setBackground(new java.awt.Color(255, 255, 255));
        txtIngresoT.setFont(new java.awt.Font("Roboto Black", 0, 14)); // NOI18N
        txtIngresoT.setForeground(new java.awt.Color(0, 102, 0));
        txtIngresoT.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtIngresoT.setText("0");
        txtIngresoT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIngresoTActionPerformed(evt);
            }
        });

        txtTotalVentasC.setEditable(false);
        txtTotalVentasC.setBackground(new java.awt.Color(255, 255, 255));
        txtTotalVentasC.setFont(new java.awt.Font("Roboto Black", 0, 14)); // NOI18N
        txtTotalVentasC.setForeground(new java.awt.Color(0, 0, 102));
        txtTotalVentasC.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTotalVentasC.setText("0");

        jLabel10.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel10.setText("Valor de ventas a Crédito:");

        jSeparator1.setForeground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel14))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtIngresoT, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTotalVentasC, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTotalIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTotalVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTotalVentas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTotalIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtIngresoT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtTotalVentasC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6))
        );

        javax.swing.GroupLayout BlancoLayout = new javax.swing.GroupLayout(Blanco);
        Blanco.setLayout(BlancoLayout);
        BlancoLayout.setHorizontalGroup(
            BlancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BlancoLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addGroup(BlancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(BlancoLayout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        BlancoLayout.setVerticalGroup(
            BlancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BlancoLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addGroup(BlancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(Blanco, javax.swing.GroupLayout.PREFERRED_SIZE, 882, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(Blanco, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("GENERAL", jPanel9);

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));

        jLabel22.setBackground(new java.awt.Color(0, 102, 102));
        jLabel22.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText(" BOCA DE COBRANZA 1");
        jLabel22.setOpaque(true);

        jLabel23.setBackground(new java.awt.Color(0, 102, 102));
        jLabel23.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText(" BOCA DE COBRANZA 2");
        jLabel23.setOpaque(true);

        txtB1.setFont(new java.awt.Font("Roboto", 1, 15)); // NOI18N
        txtB1.setForeground(new java.awt.Color(0, 102, 102));
        txtB1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtB1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        txtB2.setFont(new java.awt.Font("Roboto", 1, 15)); // NOI18N
        txtB2.setForeground(new java.awt.Color(0, 102, 102));
        txtB2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtB2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel24.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("ENTREGADO");

        txtEntregado_boca_1.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        txtEntregado_boca_1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtEntregado_boca_1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        txtEntregado_boca_1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtEntregado_boca_1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEntregado_boca_1KeyReleased(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("GASTOS");

        txtGastos_boca_1.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        txtGastos_boca_1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtGastos_boca_1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        txtGastos_boca_1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtGastos_boca_1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtGastos_boca_1KeyReleased(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("DIFERENCIA");

        txtDiferencia_boca_1.setEditable(false);
        txtDiferencia_boca_1.setBackground(new java.awt.Color(255, 255, 255));
        txtDiferencia_boca_1.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        txtDiferencia_boca_1.setForeground(new java.awt.Color(250, 0, 0));
        txtDiferencia_boca_1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDiferencia_boca_1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel27.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("ENTREGADO");

        txtEntregado_boca_2.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        txtEntregado_boca_2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtEntregado_boca_2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        txtEntregado_boca_2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtEntregado_boca_2KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEntregado_boca_2KeyReleased(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("GASTOS");

        txtGastos_boca_2.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        txtGastos_boca_2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtGastos_boca_2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        txtGastos_boca_2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtGastos_boca_2KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtGastos_boca_2KeyReleased(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("DIFERENCIA");

        txtDiferencia_boca_2.setEditable(false);
        txtDiferencia_boca_2.setBackground(new java.awt.Color(255, 255, 255));
        txtDiferencia_boca_2.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        txtDiferencia_boca_2.setForeground(new java.awt.Color(250, 0, 0));
        txtDiferencia_boca_2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDiferencia_boca_2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        btnGuardar1.setBackground(new java.awt.Color(0, 102, 102));
        btnGuardar1.setBorder(null);
        btnGuardar1.setBgHover(new java.awt.Color(6, 125, 125));
        btnGuardar1.setFocusPainted(false);
        btnGuardar1.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        btnGuardar1.setIconTextGap(0);
        btnGuardar1.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.SAVE);
        btnGuardar1.setPixels(0);
        btnGuardar1.setSizeIcon(25.0F);
        btnGuardar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardar1ActionPerformed(evt);
            }
        });

        btnGuardar2.setBackground(new java.awt.Color(0, 102, 102));
        btnGuardar2.setBorder(null);
        btnGuardar2.setBgHover(new java.awt.Color(6, 125, 125));
        btnGuardar2.setFocusPainted(false);
        btnGuardar2.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        btnGuardar2.setIconTextGap(0);
        btnGuardar2.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.SAVE);
        btnGuardar2.setPixels(0);
        btnGuardar2.setSizeIcon(25.0F);
        btnGuardar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardar2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(0, 21, Short.MAX_VALUE)
                                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtEntregado_boca_1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtGastos_boca_1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDiferencia_boca_1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnGuardar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(txtOpcion1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtB1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtEntregado_boca_2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtGastos_boca_2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDiferencia_boca_2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnGuardar2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(txtOpcion2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtB2, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtB1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtDiferencia_boca_1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtGastos_boca_1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtEntregado_boca_1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(txtOpcion1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnGuardar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(43, 43, 43)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtB2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtDiferencia_boca_2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtGastos_boca_2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtEntregado_boca_2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(txtOpcion2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnGuardar2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(205, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("INFORMACIÓN ESPECIFICA", jPanel11);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 890, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    void Invisible() {
        txtDesde.setVisible(false);
        txtHasta.setVisible(false);
        btnGuardar.setVisible(false);
        btnImprimir.setVisible(false);
        txtOpcion1.setVisible(false);
        txtOpcion2.setVisible(false);
    }

    void EntregadoGral() {
        int Entregado1, Entregado2;
        Entregado1 = Integer.parseInt(txtEntregado_boca_1.getText().replace(".", "").replace(",", ""));
        Entregado2 = Integer.parseInt(txtEntregado_boca_2.getText().replace(".", "").replace(",", ""));
        DecimalFormat df = new DecimalFormat("#,###");
        int Suma = Entregado1 + Entregado2;
        txtEntregado.setText(df.format(Suma));
    }

    void GastosGral() {
        int Gasto1, Gasto2;
        Gasto1 = Integer.parseInt(txtGastos_boca_1.getText().replace(".", "").replace(",", ""));
        Gasto2 = Integer.parseInt(txtGastos_boca_2.getText().replace(".", "").replace(",", ""));
        DecimalFormat df = new DecimalFormat("#,###");
        int Suma = Gasto1 + Gasto2;
        txtGastos.setText(df.format(Suma));
    }

    void DiferenciaGral() {
        int Dif1, Dif2;
        Dif1 = Integer.parseInt(txtDiferencia_boca_1.getText().replace(".", "").replace(",", ""));
        Dif2 = Integer.parseInt(txtDiferencia_boca_2.getText().replace(".", "").replace(",", ""));
        DecimalFormat df = new DecimalFormat("#,###");
        int Suma = Dif1 + Dif2;
        txtDiferencia.setText(df.format(Suma));
    }

    void totalVentasCont() {
        try {
            String TotalVenta = (generarCodigos.getDecimales("SELECT IFNULL((select SUM(fac_totalfinal) from factura where caja_ca_id = " + NCaja + " and fac_indicador='S' and fac_tipoventa='CONTADO'),0)"));
            DecimalFormat df = new DecimalFormat("#,###");
            txtTotalVentas.setText(df.format(Integer.valueOf((TotalVenta.trim().replace(".", "").replace(",", "")))));
        } catch (Exception e) {
            txtTotalVentas.setText("0");
        }
    }

    void totalVentasCred() {
        try {
            String TotalVenta = (generarCodigos.getDecimales("SELECT IFNULL((select SUM(fac_totalfinal) from factura where caja_ca_id = " + NCaja + " and fac_indicador='S' and fac_tipoventa='CREDITO'),0)"));
            DecimalFormat df = new DecimalFormat("#,###");
            txtTotalVentasC.setText(df.format(Integer.valueOf((TotalVenta.trim().replace(".", "").replace(",", "")))));
        } catch (Exception e) {
            txtTotalVentasC.setText("0");
        }
    }

    void totalCompraCont() {
        try {
            String TotalCompra = (generarCodigos.getDecimales("SELECT IFNULL((select SUM(com_total) from compra where caja_ca_id = " + NCaja + " and com_indicador='S' and com_condpago='CONTADO' and tipo='L'),0)"));
            DecimalFormat df = new DecimalFormat("#,###");
            txtTotalCompra.setText(df.format(Integer.valueOf((TotalCompra.trim().replace(".", "").replace(",", "")))));
        } catch (Exception e) {
            txtTotalCompra.setText("0");
        }

    }

    void totalCompraCred() {
        try {
            String TotalCompra = (generarCodigos.getDecimales("SELECT IFNULL((select SUM(com_total) from compra where caja_ca_id = " + NCaja + " and com_indicador='S' and com_condpago='CREDITO' and tipo='L'),0)"));
            DecimalFormat df = new DecimalFormat("#,###");
            txtTotalCompraC.setText(df.format(Integer.valueOf((TotalCompra.trim().replace(".", "").replace(",", "")))));
        } catch (Exception e) {
            txtTotalCompraC.setText("0");
        }

    }

    void totalGasto() {
        try {
            DecimalFormat df = new DecimalFormat("#,###");
            String TotalGasto = (generarCodigos.getDecimales("SELECT IFNULL((select SUM(ga_importe) from gastos where caja_ca_id = " + NCaja + " and ga_indicador='S' AND tipo='L'),0)"));
            txtTotalGastos.setText(df.format(Integer.valueOf((TotalGasto.trim().replace(".", "").replace(",", "")))));
        } catch (Exception e) {
            txtTotalGastos.setText("0");
        }
    }

    public static void totalSalidas() {
        try {
            DecimalFormat df = new DecimalFormat("#,###");
            String TotalGasto = (generarCodigos.getDecimales("SELECT IFNULL((select SUM(sal_totalfinal) from salida where sal_fecha='" + Fecha.fechaCorrecta() + "' and sal_indicador='S'),0)"));
            txtTotalSalida.setText(df.format(Integer.valueOf((TotalGasto.trim().replace(".", "").replace(",", "")))));
        } catch (Exception e) {
            txtTotalSalida.setText("0");
        }
    }

    void totalIngreso() {
        try {
            String TotalIngreso = (generarCodigos.getDecimales("SELECT IFNULL((select SUM(ing_importe) from ingreso where caja_ca_id = " + NCaja + " and ing_indicador='S'),0)"));
            DecimalFormat df = new DecimalFormat("#,###");
            txtTotalIngreso.setText(df.format(Integer.valueOf((TotalIngreso.trim().replace(".", "").replace(",", "")))));
        } catch (Exception e) {
            txtTotalIngreso.setText("0");
        }

    }

    void gastoTotal() {
        try {
            DecimalFormat df = new DecimalFormat("#,###");
            int G = Integer.parseInt(txtTotalGastos.getText().replace(",", "").replace(".", ""));
            GAS = G;
            txtGastoTotal.setText(df.format(G));
        } catch (NumberFormatException e) {
            txtGastoTotal.setText("0");
        }
    }

    void ingresoTotal() {
        try {
            DecimalFormat df = new DecimalFormat("#,###");
            int IG = Integer.parseInt(txtTotalVentas.getText().replace(",", "").replace(".", "")) + (Integer.parseInt(txtTotalIngreso.getText().replace(",", "").replace(".", "")));
            ING = IG;
            txtIngresoT.setText(df.format(IG));
        } catch (Exception e) {
            txtIngresoT.setText("0");
        }
    }

    void totalCaja() {
        DecimalFormat df = new DecimalFormat("#,###");
        int res = ING - GAS;
        txtEntregar.setText(df.format(res));
    }

    void totalVentasContBoca1() {
        try {
            String TotalVenta = (generarCodigos.getDecimales("SELECT IFNULL((select SUM(fac_totalfinal) from factura where caja_ca_id = " + NCaja + " and fac_indicador='S' and fac_tipoventa='CONTADO' and idboca=1),0)"));
            DecimalFormat df = new DecimalFormat("#,###");
            txtB1.setText(df.format(Integer.valueOf((TotalVenta.trim().replace(".", "").replace(",", "")))));
        } catch (Exception e) {
            txtB1.setText("0");
        }
    }

    void totalVentasContBoca2() {
        try {
            String TotalVenta = (generarCodigos.getDecimales("SELECT IFNULL((select SUM(fac_totalfinal) from factura where caja_ca_id = " + NCaja + " and fac_indicador='S' and fac_tipoventa='CONTADO' and idboca=2),0)"));
            DecimalFormat df = new DecimalFormat("#,###");
            txtB2.setText(df.format(Integer.valueOf((TotalVenta.trim().replace(".", "").replace(",", "")))));
        } catch (Exception e) {
            txtB2.setText("0");
        }
    }

    void Opcion_1() {
        try {
            String opcion1 = (generarCodigos.getDecimales("SELECT IFNULL((select idboca from arreglo_caja where idcaja = " + NCaja + " and idboca= 1),0) AS boca"));
            /*DecimalFormat df = new DecimalFormat("#,###");
            txtEntregado_boca_1.setText(df.format(Integer.valueOf((boca_1.trim().replace(".", "").replace(",", "")))));  */
            txtOpcion1.setText(opcion1);
        } catch (Exception e) {
            //txtEntregado_boca_1.setText("0");
        }
    }

    void Boca_1() {
        try {
            String boca_1 = (generarCodigos.getDecimales("SELECT IFNULL((select entregado from arreglo_caja where idcaja = " + NCaja + " and idboca= 1),0) AS entregado1"));
            DecimalFormat df = new DecimalFormat("#,###");
            txtEntregado_boca_1.setText(df.format(Integer.valueOf((boca_1.trim().replace(".", "").replace(",", "")))));
        } catch (Exception e) {
            txtEntregado_boca_1.setText("0");
        }
    }

    void Gastos_1() {
        try {
            String gastos_1 = (generarCodigos.getDecimales("SELECT IFNULL((select gastos from arreglo_caja where idcaja = " + NCaja + " and idboca= 1),0) AS gastos1"));
            DecimalFormat df = new DecimalFormat("#,###");
            txtGastos_boca_1.setText(df.format(Integer.valueOf((gastos_1.trim().replace(".", "").replace(",", "")))));
        } catch (Exception e) {
            txtGastos_boca_1.setText("0");
        }
    }

    void calcularDiferencia1() {
        int entregar, gastos, entregado, dif;
        entregar = Integer.parseInt(txtB1.getText().trim().replace(",", "").replace(".", ""));
        entregado = Integer.parseInt(txtEntregado_boca_1.getText().trim().replace(",", "").replace(".", ""));
        gastos = Integer.parseInt(txtGastos_boca_1.getText().trim().replace(",", "").replace(".", ""));
        if (entregar >= 0) {
            dif = (entregado + gastos) - entregar;
            DecimalFormat df = new DecimalFormat("#,###");
            txtDiferencia_boca_1.setText((df.format(dif)));
        }

    }

    void Opcion_2() {
        try {
            String opcion2 = (generarCodigos.getDecimales("SELECT IFNULL((select idboca from arreglo_caja where idcaja = " + NCaja + " and idboca= 2),0) AS boca"));
            /*DecimalFormat df = new DecimalFormat("#,###");
            txtEntregado_boca_1.setText(df.format(Integer.valueOf((boca_1.trim().replace(".", "").replace(",", "")))));  */
            txtOpcion2.setText(opcion2);
        } catch (Exception e) {
            //txtEntregado_boca_1.setText("0");
        }
    }

    void Boca_2() {
        try {
            String boca_2 = (generarCodigos.getDecimales("SELECT IFNULL((select entregado from arreglo_caja where idcaja = " + NCaja + " and idboca= 2),0) AS entregado2"));
            DecimalFormat df = new DecimalFormat("#,###");
            txtEntregado_boca_2.setText(df.format(Integer.valueOf((boca_2.trim().replace(".", "").replace(",", "")))));
        } catch (Exception e) {
            txtEntregado_boca_2.setText("0");
        }
    }

    void Gastos_2() {
        try {
            String gastos_2 = (generarCodigos.getDecimales("SELECT IFNULL((select gastos from arreglo_caja where idcaja = " + NCaja + " and idboca= 2),0) AS gastos2"));
            DecimalFormat df = new DecimalFormat("#,###");
            txtGastos_boca_2.setText(df.format(Integer.valueOf((gastos_2.trim().replace(".", "").replace(",", "")))));
        } catch (Exception e) {
            txtGastos_boca_2.setText("0");
        }
    }

    void calcularDiferencia2() {
        int entregar, gastos, entregado, dif;
        entregar = Integer.parseInt(txtB2.getText().trim().replace(",", "").replace(".", ""));
        entregado = Integer.parseInt(txtEntregado_boca_2.getText().trim().replace(",", "").replace(".", ""));
        gastos = Integer.parseInt(txtGastos_boca_2.getText().trim().replace(",", "").replace(".", ""));
        if (entregar >= 0) {
            dif = (entregado + gastos) - entregar;
            DecimalFormat df = new DecimalFormat("#,###");
            txtDiferencia_boca_2.setText((df.format(dif)));
        }

    }

    private void txtEntregadoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEntregadoKeyPressed
        // TODO add your handling code here:
        validarCampos.soloNumeros(txtEntregado);
    }//GEN-LAST:event_txtEntregadoKeyPressed

    private void txtEntregadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEntregadoKeyReleased
        // TODO add your handling code here:
        try {
            if (txtEntregado.getText().trim().length() >= 1) {
                DecimalFormat df = new DecimalFormat("#,###");
                txtEntregado.setText(df.format(Integer.valueOf(txtEntregado.getText().trim().replace(".", "").replace(",", ""))));
            } else {
                txtEntregado.setText("0");
                txtEntregado.selectAll();
            }
        } catch (NumberFormatException e) {
            System.out.println("c: " + e.getMessage());
        }
        calcular();
    }//GEN-LAST:event_txtEntregadoKeyReleased

    private void dcDesdeOnCommit(datechooser.events.CommitEvent evt) {//GEN-FIRST:event_dcDesdeOnCommit
        // TODO add your handling code here:
        txtDesde.setText(Fecha.formatoFecha(dcDesde.getText()));
    }//GEN-LAST:event_dcDesdeOnCommit

    private void dcHastaOnCommit(datechooser.events.CommitEvent evt) {//GEN-FIRST:event_dcHastaOnCommit
        // TODO add your handling code here:
        txtHasta.setText(Fecha.formatoFecha(dcHasta.getText()));
    }//GEN-LAST:event_dcHastaOnCommit

    private void txtGastosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGastosKeyPressed
        // TODO add your handling code here:
        validarCampos.soloNumeros(txtGastos);
    }//GEN-LAST:event_txtGastosKeyPressed

    private void txtGastosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGastosKeyReleased
        // TODO add your handling code here:
        try {
            if (txtGastos.getText().trim().length() >= 1) {
                DecimalFormat df = new DecimalFormat("#,###");
                txtGastos.setText(df.format(Integer.valueOf(txtGastos.getText().trim().replace(".", "").replace(",", ""))));
            } else {
                txtGastos.setText("0");
                txtGastos.selectAll();
            }
        } catch (NumberFormatException e) {
            System.out.println("c: " + e.getMessage());
        }
        calcular();
    }//GEN-LAST:event_txtGastosKeyReleased

    private void btnCalcularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalcularActionPerformed
        // TODO add your handling code here:
        try {
            SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
            Date desde = sdformat.parse(txtDesde.getText().trim());
            Date hasta = sdformat.parse(txtHasta.getText().trim());
            if (desde.before(hasta)) {
                try {
                    CalcularDiferencia(txtDesde.getText().trim(), txtHasta.getText().trim());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else {
                Mensajes.Alerta("ATENCIÓN:\nLa fecha DESDE no puede ser mayor o igual a la fecha HASTA.\nCorrija esta observación y vuelva a intentarlo.");
            }
        } catch (ParseException ee) {
        }
    }//GEN-LAST:event_btnCalcularActionPerformed

    private void btnRegistrarSalidasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarSalidasActionPerformed
        // TODO add your handling code here:
        try {
            dlgSalidaMercaderia salida = new dlgSalidaMercaderia(null, true);
            salida.setLocationRelativeTo(null);
            salida.setVisible(true);
        } catch (Exception e) {
            Mensajes.informacion("Servidor no esta activo");
        }
    }//GEN-LAST:event_btnRegistrarSalidasActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        try {
            int resp = JOptionPane.showConfirmDialog(this, "¿Seguro que desea insertar el registro?", "Insertar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (resp == JOptionPane.YES_OPTION) {
                ControlCaja.actCaja();
            }
        } catch (HeadlessException ee) {
            System.out.println(ee.getMessage());
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        // TODO add your handling code here:
        if (Integer.parseInt(txtEntregar.getText().trim().replace(",", "").replace(".", "")) < 0) {
            Mensajes.error("El cierre final de la caja contiene saldo negativo.\nNo se puede proceder a cerrar la caja.\nConsejo: verifique si una o varias operaciones no fueron duplicados.");
        } else {
            int resp = JOptionPane.showConfirmDialog(this, "¿Seguro que desea Cerrar la Caja y Finalizar las Operaciones?", "CIERRE GENERAL DE LOS MOVIMIENTOS DEL DÍA", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (resp == JOptionPane.YES_OPTION) {
                try {
                    ControlCaja.CerrarCaja();
                    btnCerrar.setEnabled(false);
                    //btnImprimir.setEnabled(true);
                    txtEntregado.setEnabled(false);
                    dcDesde.setEnabled(false);
                    dcHasta.setEnabled(false);
                    btnCalcular.setEnabled(false);
                    btnGuardar.setEnabled(false);
                    txtEntregado_boca_1.setEnabled(false);
                    txtGastos_boca_1.setEnabled(false);
                    btnGuardar1.setEnabled(false);
                    txtEntregado_boca_2.setEnabled(false);
                    txtGastos_boca_2.setEnabled(false);
                    btnGuardar2.setEnabled(false);
                    btnRegistrarSalidas.setEnabled(false);
                    Caja caj = GestionarCaja.busCaja(NCaja);
                    if (caj.getIndicador().equals("S")) {
                        lbEstado.setText("ABIERTO");
                    } else {
                        lbEstado.setText("CERRADO");
                    }
                } catch (Exception e) {
                    Mensajes.error(e.getMessage());
                }
            }
        }
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void txtIngresoTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIngresoTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIngresoTActionPerformed

    private void txtEntregadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEntregadoActionPerformed
        // TODO add your handling code here:
        txtGastos.requestFocus();
    }//GEN-LAST:event_txtEntregadoActionPerformed

    private void txtGastosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGastosActionPerformed
        // TODO add your handling code here:
        txtEntregado.requestFocus();
    }//GEN-LAST:event_txtGastosActionPerformed

    private void rSButtonIconOne1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIconOne1ActionPerformed
        // TODO add your handling code here:
        int rpta = Mensajes.confirmar("¿Seguro que desea salir del formulario?");
        if (rpta == 0) {
            this.dispose();
        }
    }//GEN-LAST:event_rSButtonIconOne1ActionPerformed

    private void btnGuardar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardar1ActionPerformed
        // TODO add your handling code here:
        int resp = JOptionPane.showConfirmDialog(this, "¿Desea guardar información de valores?", "BOCA DE COBRANZA 1", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (resp == JOptionPane.YES_OPTION) {
            if (Integer.parseInt(txtOpcion1.getText().trim()) <= 0) {
                ControlCaja.addBoca1();
            } else {
                ControlCaja.actBoca1();
            }
            totalVentasCont();
            totalVentasCred();
            totalCompraCont();
            totalCompraCred();
            totalGasto();
            totalSalidas();
            totalIngreso();
            gastoTotal();
            ingresoTotal();
            totalCaja();
            calcular();
            totalVentasContBoca1();
            Opcion_1();
            Boca_1();
            Gastos_1();
            calcularDiferencia1();
            totalVentasContBoca2();
            Opcion_2();
            Boca_2();
            Gastos_2();
            calcularDiferencia2();
            EntregadoGral();
            GastosGral();
            DiferenciaGral();
        }

    }//GEN-LAST:event_btnGuardar1ActionPerformed

    private void btnGuardar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardar2ActionPerformed
        // TODO add your handling code here:
        int resp = JOptionPane.showConfirmDialog(this, "¿Desea guardar información de valores?", "BOCA DE COBRANZA 2", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (resp == JOptionPane.YES_OPTION) {
            if (Integer.parseInt(txtOpcion2.getText().trim()) <= 0) {
                ControlCaja.addBoca2();
            } else {
                ControlCaja.actBoca2();
            }
            totalVentasCont();
            totalVentasCred();
            totalCompraCont();
            totalCompraCred();
            totalGasto();
            totalSalidas();
            totalIngreso();
            gastoTotal();
            ingresoTotal();
            totalCaja();
            calcular();
            totalVentasContBoca1();
            Opcion_1();
            Boca_1();
            Gastos_1();
            calcularDiferencia1();
            totalVentasContBoca2();
            Opcion_2();
            Boca_2();
            Gastos_2();
            calcularDiferencia2();
            EntregadoGral();
            GastosGral();
            DiferenciaGral();
        }


    }//GEN-LAST:event_btnGuardar2ActionPerformed

    private void txtEntregado_boca_1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEntregado_boca_1KeyPressed
        // TODO add your handling code here:
        validarCampos.soloNumeros(txtEntregado_boca_1);
    }//GEN-LAST:event_txtEntregado_boca_1KeyPressed

    private void txtEntregado_boca_1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEntregado_boca_1KeyReleased
        // TODO add your handling code here:
        try {
            if (txtEntregado_boca_1.getText().trim().length() >= 1) {
                DecimalFormat df = new DecimalFormat("#,###");
                txtEntregado_boca_1.setText(df.format(Integer.valueOf(txtEntregado_boca_1.getText().trim().replace(".", "").replace(",", ""))));
            } else {
                txtEntregado_boca_1.setText("0");
                txtEntregado_boca_1.selectAll();
            }
        } catch (NumberFormatException e) {
            System.out.println("c: " + e.getMessage());
        }
        calcularDiferencia1();
    }//GEN-LAST:event_txtEntregado_boca_1KeyReleased

    private void txtGastos_boca_1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGastos_boca_1KeyPressed
        // TODO add your handling code here:
        validarCampos.soloNumeros(txtGastos_boca_1);
    }//GEN-LAST:event_txtGastos_boca_1KeyPressed

    private void txtGastos_boca_1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGastos_boca_1KeyReleased
        // TODO add your handling code here:
        try {
            if (txtGastos_boca_1.getText().trim().length() >= 1) {
                DecimalFormat df = new DecimalFormat("#,###");
                txtGastos_boca_1.setText(df.format(Integer.valueOf(txtGastos_boca_1.getText().trim().replace(".", "").replace(",", ""))));
            } else {
                txtGastos_boca_1.setText("0");
                txtGastos_boca_1.selectAll();
            }
        } catch (NumberFormatException e) {
            System.out.println("c: " + e.getMessage());
        }
        calcularDiferencia1();
    }//GEN-LAST:event_txtGastos_boca_1KeyReleased

    private void txtEntregado_boca_2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEntregado_boca_2KeyPressed
        // TODO add your handling code here:
        validarCampos.soloNumeros(txtEntregado_boca_2);
    }//GEN-LAST:event_txtEntregado_boca_2KeyPressed

    private void txtEntregado_boca_2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEntregado_boca_2KeyReleased
        // TODO add your handling code here:
        try {
            if (txtEntregado_boca_2.getText().trim().length() >= 1) {
                DecimalFormat df = new DecimalFormat("#,###");
                txtEntregado_boca_2.setText(df.format(Integer.valueOf(txtEntregado_boca_2.getText().trim().replace(".", "").replace(",", ""))));
            } else {
                txtEntregado_boca_2.setText("0");
                txtEntregado_boca_2.selectAll();
            }
        } catch (NumberFormatException e) {
            System.out.println("c: " + e.getMessage());
        }
        calcularDiferencia2();
    }//GEN-LAST:event_txtEntregado_boca_2KeyReleased

    private void txtGastos_boca_2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGastos_boca_2KeyPressed
        // TODO add your handling code here:
        validarCampos.soloNumeros(txtGastos_boca_2);
    }//GEN-LAST:event_txtGastos_boca_2KeyPressed

    private void txtGastos_boca_2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGastos_boca_2KeyReleased
        // TODO add your handling code here:
        try {
            if (txtGastos_boca_2.getText().trim().length() >= 1) {
                DecimalFormat df = new DecimalFormat("#,###");
                txtGastos_boca_2.setText(df.format(Integer.valueOf(txtGastos_boca_2.getText().trim().replace(".", "").replace(",", ""))));
            } else {
                txtGastos_boca_2.setText("0");
                txtGastos_boca_2.selectAll();
            }
        } catch (NumberFormatException e) {
            System.out.println("c: " + e.getMessage());
        }
        calcularDiferencia2();
    }//GEN-LAST:event_txtGastos_boca_2KeyReleased

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
            java.util.logging.Logger.getLogger(dlgCajaDia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(() -> {
            dlgCajaDia dialog = new dlgCajaDia(new javax.swing.JFrame(), true);
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
    private newscomponents.RSButtonGradientIcon_new btnCalcular;
    private newscomponents.RSButtonBigIcon_new btnCerrar;
    private newscomponents.RSButtonBigIcon_new btnGuardar;
    private newscomponents.RSButtonBigIcon_new btnGuardar1;
    private newscomponents.RSButtonBigIcon_new btnGuardar2;
    private newscomponents.RSButtonBigIcon_new btnImprimir;
    private newscomponents.RSButtonGradientIcon_new btnRegistrarSalidas;
    private datechooser.beans.DateChooserCombo dcDesde;
    private datechooser.beans.DateChooserCombo dcHasta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lbEstado;
    private javax.swing.JLabel lbFecha;
    private javax.swing.JLabel lbHora;
    private javax.swing.JLabel lbInicial;
    public static javax.swing.JLabel lbNCaja;
    private javax.swing.JLabel lbUsuI;
    private RSMaterialComponent.RSButtonIconOne rSButtonIconOne1;
    public static javax.swing.JTextField txtB1;
    public static javax.swing.JTextField txtB2;
    private javax.swing.JTextField txtDesde;
    public static javax.swing.JLabel txtDifAcumulada;
    public static javax.swing.JLabel txtDiferencia;
    public static javax.swing.JTextField txtDiferencia_boca_1;
    public static javax.swing.JTextField txtDiferencia_boca_2;
    public static javax.swing.JTextField txtEntregado;
    public static javax.swing.JTextField txtEntregado_boca_1;
    public static javax.swing.JTextField txtEntregado_boca_2;
    public static javax.swing.JLabel txtEntregar;
    private javax.swing.JTextField txtGastoTotal;
    public static javax.swing.JTextField txtGastos;
    public static javax.swing.JTextField txtGastos_boca_1;
    public static javax.swing.JTextField txtGastos_boca_2;
    private javax.swing.JTextField txtHasta;
    private javax.swing.JTextField txtIngresoT;
    private javax.swing.JTextField txtOpcion1;
    private javax.swing.JTextField txtOpcion2;
    private javax.swing.JTextField txtTotalCompra;
    private javax.swing.JTextField txtTotalCompraC;
    private javax.swing.JTextField txtTotalGastos;
    private javax.swing.JTextField txtTotalIngreso;
    public static javax.swing.JTextField txtTotalSalida;
    private javax.swing.JTextField txtTotalVentas;
    private javax.swing.JTextField txtTotalVentasC;
    // End of variables declaration//GEN-END:variables
}
