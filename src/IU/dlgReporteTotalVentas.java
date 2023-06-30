package IU;

import Componentes.ConexionBD;
import Componentes.Fecha;
import Componentes.Mensajes;
import Componentes.Reporte;
import Componentes.clsExportarExcel1;
import Controladores.CabecerasTablas;
import Controladores.controlFactura;
import static IU.dlgEmpresa.conM;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.io.IOException;
import org.mariadb.jdbc.MariaDbConnection;
import org.mariadb.jdbc.MariaDbStatement;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.swing.JRViewer;

public class dlgReporteTotalVentas extends javax.swing.JDialog {

    public Reporte jasper;
    public static ResultSet rs;
    public static MariaDbStatement sentencia;
    public static MariaDbConnection con;
    public static MariaDbStatement sentenciaM;
    public static MariaDbConnection conM;
    static String Fdesde;
    static String Fhasta;
    CabecerasTablas cabe = new CabecerasTablas();

    public dlgReporteTotalVentas(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        jasper = new Reporte();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Iconos/logo1.png")));
        CargarFecha();
        invisible();
        cabe.VentasContaduria(tbContable);
        CabecerasTablas.limpiarTablas(tbContable);
    }

    private void CargarFecha() {
        lbFechaActual.setText(Fecha.fechaFormulario());
        lbFechaActualR.setText(Fecha.fechaCorrecta());
    }

    private void invisible() {
        txtFDesdeR.setVisible(false);
        txtFHastaR.setVisible(false);
        lbFechaActualR.setVisible(false);
    }

    public static void prepararBD() {
        {
            try {
                con = (MariaDbConnection) new ConexionBD().getConexion();
                if (con == null) {
                    System.out.println("No hay Conexion con la Base de Datos");
                } else {
                    sentencia = (MariaDbStatement) con.createStatement();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

            try {
                conM = (MariaDbConnection) new ConexionBD().getConexionMovil();
                if (con == null) {
                    System.out.println("No hay Conexion con la Base de Datos Móvil");
                } else {
                    sentenciaM = (MariaDbStatement) conM.createStatement();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void LevantarReporte(String Dir, String Nombre1, Date Valor1, String Nombre2, Date Valor2) {
        VisorReportes vr = new VisorReportes(null, true);
        try {
            //prepararBD();
            //archivo jasper
            //URL  jasperUrl = this.getClass().getResource("\\Reports\\repartos\\movimiento_reparto_E.jasper");
            String jasperUrl = System.getProperty("user.dir").concat(Dir);
            JasperReport report = (JasperReport) JRLoader.loadObjectFromFile(jasperUrl);
            //para los parametro
            Map parametros = new HashMap();
            parametros.clear();
            //Nuestro parametro se llama "pLastName"
            parametros.put(Nombre1, Valor1);
            parametros.put(Nombre2, Valor2);

            //agregamos los parametros y la conexion a la base de datos
            JasperPrint jasperPrint = JasperFillManager.fillReport(report, parametros, con);
            //se crea el visor con el reporte
            JRViewer jRViewer = new JRViewer(jasperPrint);
            //se elimina elementos del contenedor JPanel
            VisorReportes.jpContainer.removeAll();
            //para el tamaño del reporte se agrega un BorderLayout
            VisorReportes.jpContainer.setLayout(new BorderLayout());
            VisorReportes.jpContainer.add(jRViewer, BorderLayout.CENTER);
            jRViewer.setZoomRatio((float) 1.00);
            jRViewer.setVisible(true);
            VisorReportes.jpContainer.repaint();
            VisorReportes.jpContainer.revalidate();
        } catch (JRException ex) {
            System.err.println(ex.getMessage());
        }
        //vr.setSize(250, 50);
        vr.setResizable(false);
        vr.setLocationRelativeTo(this);
        vr.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        GrupoReporte = new javax.swing.ButtonGroup();
        buttonGroup1 = new javax.swing.ButtonGroup();
        DialogExport = new javax.swing.JDialog();
        jPanel3 = new javax.swing.JPanel();
        rSButtonIconUno2 = new RSMaterialComponent.RSButtonIconUno();
        rSPanelImage1 = new rojeru_san.rspanel.RSPanelImage();
        rSButtonIconUno1 = new RSMaterialComponent.RSButtonIconUno();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbContable = new javax.swing.JTable();
        Blanco = new org.edisoncor.gui.panel.PanelImage();
        Oscuro = new org.edisoncor.gui.panel.PanelImage();
        jPanel2 = new javax.swing.JPanel();
        btnGenerar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        lbFechaActualR = new javax.swing.JLabel();
        txtFHastaR = new javax.swing.JTextField();
        txtFDesdeR = new javax.swing.JTextField();
        cbCompleto = new javax.swing.JCheckBox();
        cbResumido = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        rbRankingA = new javax.swing.JRadioButton();
        rbRankingF = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        lbFechaActual = new javax.swing.JLabel();
        dcFDesde = new datechooser.beans.DateChooserCombo();
        dcFHasta = new datechooser.beans.DateChooserCombo();
        txtFDesde = new javax.swing.JTextField();
        txtFHasta = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        cbContable = new javax.swing.JCheckBox();
        cbContable1 = new javax.swing.JCheckBox();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        itemNuevoGenerar = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        itemSalir = new javax.swing.JMenuItem();

        DialogExport.setUndecorated(true);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rSButtonIconUno2.setBackground(new java.awt.Color(255, 255, 255));
        rSButtonIconUno2.setBackgroundHover(new java.awt.Color(255, 0, 0));
        rSButtonIconUno2.setForegroundText(new java.awt.Color(255, 0, 0));
        rSButtonIconUno2.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.CLOSE);
        rSButtonIconUno2.setTypeBorder(RSMaterialComponent.RSButtonIconUno.TYPEBORDER.CIRCLE);
        rSButtonIconUno2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonIconUno2ActionPerformed(evt);
            }
        });
        jPanel3.add(rSButtonIconUno2, new org.netbeans.lib.awtextra.AbsoluteConstraints(154, 3, 25, 25));

        rSPanelImage1.setImagen(new javax.swing.ImageIcon(getClass().getResource("/Recursos/CONTENEDOR2.png"))); // NOI18N
        rSPanelImage1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rSButtonIconUno1.setBackground(new java.awt.Color(255, 255, 255));
        rSButtonIconUno1.setBackgroundHover(new java.awt.Color(0, 102, 51));
        rSButtonIconUno1.setForegroundText(new java.awt.Color(0, 102, 51));
        rSButtonIconUno1.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.EXPLICIT);
        rSButtonIconUno1.setTypeBorder(RSMaterialComponent.RSButtonIconUno.TYPEBORDER.CIRCLE);
        rSButtonIconUno1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonIconUno1ActionPerformed(evt);
            }
        });
        rSPanelImage1.add(rSButtonIconUno1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 70, 70));

        jSeparator2.setForeground(new java.awt.Color(204, 204, 204));
        rSPanelImage1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 105, 120, 3));

        jLabel3.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(17, 35, 46));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Descargar Documento");
        rSPanelImage1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 110, 120, -1));

        jPanel3.add(rSPanelImage1, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 90, 150, 140));

        tbContable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "", "", "", "", "", "", "", "", "", ""
            }
        ));
        jScrollPane1.setViewportView(tbContable);

        javax.swing.GroupLayout DialogExportLayout = new javax.swing.GroupLayout(DialogExport.getContentPane());
        DialogExport.getContentPane().setLayout(DialogExportLayout);
        DialogExportLayout.setHorizontalGroup(
            DialogExportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DialogExportLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 911, Short.MAX_VALUE)
                .addContainerGap())
        );
        DialogExportLayout.setVerticalGroup(
            DialogExportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DialogExportLayout.createSequentialGroup()
                .addGroup(DialogExportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Generador de Reportes");
        setResizable(false);

        Blanco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/fondoBlanco.jpg"))); // NOI18N
        Blanco.setPreferredSize(new java.awt.Dimension(690, 418));

        Oscuro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/0-102-102.png"))); // NOI18N
        Oscuro.setPreferredSize(new java.awt.Dimension(690, 418));

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)));
        jPanel2.setLayout(new java.awt.GridLayout(1, 6));

        btnGenerar.setFont(new java.awt.Font("Roboto", 1, 9)); // NOI18N
        btnGenerar.setForeground(new java.awt.Color(0, 102, 102));
        btnGenerar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/reporte x 40.png"))); // NOI18N
        btnGenerar.setText("GENERAR REPORTE");
        btnGenerar.setToolTipText("Registrar Nuevo Artículo");
        btnGenerar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnGenerar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnGenerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarActionPerformed(evt);
            }
        });
        jPanel2.add(btnGenerar);

        btnSalir.setFont(new java.awt.Font("Roboto", 1, 9)); // NOI18N
        btnSalir.setForeground(new java.awt.Color(0, 102, 102));
        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/back40.png"))); // NOI18N
        btnSalir.setText("SALIR");
        btnSalir.setToolTipText("Salir");
        btnSalir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSalir.setPreferredSize(new java.awt.Dimension(53, 47));
        btnSalir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        jPanel2.add(btnSalir);

        lbFechaActualR.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbFechaActualR.setText("jLabel2");

        txtFHastaR.setEditable(false);
        txtFHastaR.setBackground(new java.awt.Color(255, 255, 204));
        txtFHastaR.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 11)); // NOI18N
        txtFHastaR.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtFDesdeR.setEditable(false);
        txtFDesdeR.setBackground(new java.awt.Color(255, 255, 204));
        txtFDesdeR.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 11)); // NOI18N
        txtFDesdeR.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout OscuroLayout = new javax.swing.GroupLayout(Oscuro);
        Oscuro.setLayout(OscuroLayout);
        OscuroLayout.setHorizontalGroup(
            OscuroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OscuroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(OscuroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(OscuroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtFHastaR, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtFDesdeR, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbFechaActualR, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        OscuroLayout.setVerticalGroup(
            OscuroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OscuroLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(OscuroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(OscuroLayout.createSequentialGroup()
                        .addComponent(txtFDesdeR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFHastaR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(lbFechaActualR))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5))
        );

        buttonGroup1.add(cbCompleto);
        cbCompleto.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        cbCompleto.setSelected(true);
        cbCompleto.setText("Reporte Completo");
        cbCompleto.setOpaque(false);

        buttonGroup1.add(cbResumido);
        cbResumido.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        cbResumido.setText("Reporte Resumido");
        cbResumido.setOpaque(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel1.setOpaque(false);

        GrupoReporte.add(rbRankingA);
        rbRankingA.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        rbRankingA.setSelected(true);
        rbRankingA.setText("Venta total del día (fecha actual):");
        rbRankingA.setOpaque(false);
        rbRankingA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbRankingAActionPerformed(evt);
            }
        });

        GrupoReporte.add(rbRankingF);
        rbRankingF.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        rbRankingF.setText("Venta total entre fechas:");
        rbRankingF.setOpaque(false);
        rbRankingF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbRankingFActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        jLabel1.setText("Desde");

        lbFechaActual.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        lbFechaActual.setText("jLabel2");

        dcFDesde.setEnabled(false);
        dcFDesde.addCommitListener(new datechooser.events.CommitListener() {
            public void onCommit(datechooser.events.CommitEvent evt) {
                dcFDesdeOnCommit(evt);
            }
        });

        dcFHasta.setEnabled(false);
        dcFHasta.addCommitListener(new datechooser.events.CommitListener() {
            public void onCommit(datechooser.events.CommitEvent evt) {
                dcFHastaOnCommit(evt);
            }
        });

        txtFDesde.setEditable(false);
        txtFDesde.setBackground(new java.awt.Color(255, 255, 255));
        txtFDesde.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        txtFDesde.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFDesde.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        txtFDesde.setEnabled(false);
        txtFDesde.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFDesdeActionPerformed(evt);
            }
        });

        txtFHasta.setEditable(false);
        txtFHasta.setBackground(new java.awt.Color(255, 255, 255));
        txtFHasta.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        txtFHasta.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFHasta.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        txtFHasta.setEnabled(false);
        txtFHasta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFHastaActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        jLabel2.setText("Hasta");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator1)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(rbRankingA)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbFechaActual, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(rbRankingF, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGap(149, 149, 149)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtFDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(dcFDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtFHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(dcFHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbRankingA)
                    .addComponent(lbFechaActual, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbRankingF)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtFDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(dcFDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dcFHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtFHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(5, 5, 5))
        );

        buttonGroup1.add(cbContable);
        cbContable.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        cbContable.setText("Reporte orientado a Contabilidad");
        cbContable.setOpaque(false);

        buttonGroup1.add(cbContable1);
        cbContable1.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        cbContable1.setText("Datos para Contabilidad (Excel)");
        cbContable1.setOpaque(false);
        cbContable1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbContable1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout BlancoLayout = new javax.swing.GroupLayout(Blanco);
        Blanco.setLayout(BlancoLayout);
        BlancoLayout.setHorizontalGroup(
            BlancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Oscuro, javax.swing.GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE)
            .addGroup(BlancoLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(BlancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(BlancoLayout.createSequentialGroup()
                        .addGroup(BlancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbResumido)
                            .addComponent(cbCompleto))
                        .addGap(18, 18, 18)
                        .addGroup(BlancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbContable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(BlancoLayout.createSequentialGroup()
                                .addComponent(cbContable1, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 7, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        BlancoLayout.setVerticalGroup(
            BlancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BlancoLayout.createSequentialGroup()
                .addComponent(Oscuro, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addGroup(BlancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbCompleto)
                    .addComponent(cbContable))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(BlancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbResumido)
                    .addComponent(cbContable1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jMenu1.setText("Opciones");
        jMenu1.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        jMenu1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        itemNuevoGenerar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F6, 0));
        itemNuevoGenerar.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        itemNuevoGenerar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/reports.png"))); // NOI18N
        itemNuevoGenerar.setText("GERERAR REPORTE");
        itemNuevoGenerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemNuevoGenerarActionPerformed(evt);
            }
        });
        jMenu1.add(itemNuevoGenerar);
        jMenu1.add(jSeparator3);

        itemSalir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_DOWN_MASK));
        itemSalir.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        itemSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/back15.png"))); // NOI18N
        itemSalir.setText("SALIR");
        itemSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemSalirActionPerformed(evt);
            }
        });
        jMenu1.add(itemSalir);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Blanco, javax.swing.GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Blanco, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void itemNuevoGenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemNuevoGenerarActionPerformed
        // TODO add your handling code here:
        btnGenerar.doClick();
    }//GEN-LAST:event_itemNuevoGenerarActionPerformed

    private void itemSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemSalirActionPerformed
        // TODO add your handling code here:
        btnSalirActionPerformed(null);
    }//GEN-LAST:event_itemSalirActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        int rpta = Mensajes.confirmar("¿Seguro que desea salir del formulario?");
        if (rpta == 0) {
            this.dispose();
        }
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnGenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarActionPerformed
        // TODO add your handling code here:
        try {
            prepararBD();
            if (cbCompleto.isSelected()) {
                if (rbRankingA.isSelected()) {
                    LevantarReporte("\\Reports\\ventas\\DetalleVentasFG.jasper", "desde", Date.valueOf(lbFechaActualR.getText().trim()), "hasta", Date.valueOf(lbFechaActualR.getText().trim()));
                } else if (rbRankingF.isSelected()) {
                    if (txtFDesde.getText().trim().isEmpty()) {
                        Mensajes.informacion("Fije la fecha desde");
                    } else if (txtFHasta.getText().trim().isEmpty()) {
                        Mensajes.informacion("Fije la fecha hasta");
                    } else if (Date.valueOf(txtFDesdeR.getText().trim()).after(Date.valueOf(txtFHastaR.getText().trim()))) {
                        Mensajes.error("Error en los parametros fijados.\nFavor verifique las fechas Desde y Hasta.");
                    } else {
                        LevantarReporte("\\Reports\\ventas\\DetalleVentasFG.jasper", "desde", Date.valueOf(txtFDesdeR.getText().trim()), "hasta", Date.valueOf(txtFHastaR.getText().trim()));
                    }
                }
            } else if (cbResumido.isSelected()) {
                if (rbRankingA.isSelected()) {
                    LevantarReporte("\\Reports\\ventas\\DetalleVentasFR.jasper", "desde", Date.valueOf(lbFechaActualR.getText().trim()), "hasta", Date.valueOf(lbFechaActualR.getText().trim()));
                } else if (rbRankingF.isSelected()) {
                    if (txtFDesde.getText().trim().isEmpty()) {
                        Mensajes.informacion("Fije la fecha desde");
                    } else if (txtFHasta.getText().trim().isEmpty()) {
                        Mensajes.informacion("Fije la fecha hasta");
                    } else if (Date.valueOf(txtFDesdeR.getText().trim()).after(Date.valueOf(txtFHastaR.getText().trim()))) {
                        Mensajes.error("Error en los parametros fijados.\nFavor verifique las fechas Desde y Hasta.");
                    } else {
                        LevantarReporte("\\Reports\\ventas\\DetalleVentasFR.jasper", "desde", Date.valueOf(txtFDesdeR.getText().trim()), "hasta", Date.valueOf(txtFHastaR.getText().trim()));
                    }
                }

            } else if (cbContable.isSelected()) {
                if (rbRankingA.isSelected()) {
                    LevantarReporte("\\Reports\\ventas\\DetalleVentasFG_Conta.jasper", "desde", Date.valueOf(lbFechaActualR.getText().trim()), "hasta", Date.valueOf(lbFechaActualR.getText().trim()));
                } else if (rbRankingF.isSelected()) {
                    if (txtFDesde.getText().trim().isEmpty()) {
                        Mensajes.informacion("Fije la fecha desde");
                    } else if (txtFHasta.getText().trim().isEmpty()) {
                        Mensajes.informacion("Fije la fecha hasta");
                    } else if (Date.valueOf(txtFDesdeR.getText().trim()).after(Date.valueOf(txtFHastaR.getText().trim()))) {
                        Mensajes.error("Error en los parametros fijados.\nFavor verifique las fechas Desde y Hasta.");
                    } else {
                        LevantarReporte("\\Reports\\ventas\\DetalleVentasFG_Conta.jasper", "desde", Date.valueOf(txtFDesdeR.getText().trim()), "hasta", Date.valueOf(txtFHastaR.getText().trim()));
                    }
                }

            } else if (cbContable1.isSelected()) {
                if (rbRankingA.isSelected()) {
                    //LevantarReporte("\\Reports\\ventas\\DetalleVentasFG_Conta.jasper", "desde", Date.valueOf(lbFechaActualR.getText().trim()), "hasta", Date.valueOf(lbFechaActualR.getText().trim()));
                    try {
                        //DialogExport.setSize(182, 240);
                        CabecerasTablas.limpiarTablas(tbContable);
                        controlFactura.listVentasContaduria(tbContable, lbFechaActualR.getText().trim(), lbFechaActualR.getText().trim());

                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    //DialogExport.setSize(1200, 450);
                    DialogExport.setSize(183, 241);
                    DialogExport.setLocationRelativeTo(this);
                    DialogExport.setModal(true);
                    DialogExport.setVisible(true);
                } else if (rbRankingF.isSelected()) {
                    if (txtFDesde.getText().trim().isEmpty()) {
                        Mensajes.informacion("Fije la fecha desde");
                    } else if (txtFHasta.getText().trim().isEmpty()) {
                        Mensajes.informacion("Fije la fecha hasta");
                    } else if (Date.valueOf(txtFDesdeR.getText().trim()).after(Date.valueOf(txtFHastaR.getText().trim()))) {
                        Mensajes.error("Error en los parametros fijados.\nFavor verifique las fechas Desde y Hasta.");
                    } else {
                        //LevantarReporte("\\Reports\\ventas\\DetalleVentasFG_Conta.jasper", "desde", Date.valueOf(txtFDesdeR.getText().trim()), "hasta", Date.valueOf(txtFHastaR.getText().trim()));
                        try {
                            //DialogExport.setSize(182, 240);
                            CabecerasTablas.limpiarTablas(tbContable);
                            controlFactura.listVentasContaduria(tbContable, txtFDesdeR.getText().trim(), txtFHastaR.getText().trim());

                        } catch (Exception e) {
                            System.out.println(e);
                        }
                        DialogExport.setSize(183, 241);
                        DialogExport.setLocationRelativeTo(this);
                        DialogExport.setModal(true);
                        DialogExport.setVisible(true);
                    }
                }

            }
            con.close();
            conM.close();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnGenerarActionPerformed

    private void rbRankingAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbRankingAActionPerformed
        // TODO add your handling code here:
        txtFDesde.setEnabled(false);
        txtFDesde.setText("");
        txtFDesdeR.setText("");
        dcFDesde.setEnabled(false);
        txtFHasta.setEnabled(false);
        txtFHasta.setText("");
        txtFHastaR.setText("");
        dcFHasta.setEnabled(false);
    }//GEN-LAST:event_rbRankingAActionPerformed

    private void rbRankingFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbRankingFActionPerformed
        // TODO add your handling code here:
        txtFDesde.setEnabled(true);
        dcFDesde.setEnabled(true);
        txtFHasta.setEnabled(true);
        dcFHasta.setEnabled(true);
    }//GEN-LAST:event_rbRankingFActionPerformed

    private void dcFDesdeOnCommit(datechooser.events.CommitEvent evt) {//GEN-FIRST:event_dcFDesdeOnCommit
        // TODO add your handling code here:
        txtFDesde.setText(Fecha.fechaCFormulario(dcFDesde.getText()));
        txtFDesdeR.setText(Fecha.formatoFecha(dcFDesde.getText()));
    }//GEN-LAST:event_dcFDesdeOnCommit

    private void dcFHastaOnCommit(datechooser.events.CommitEvent evt) {//GEN-FIRST:event_dcFHastaOnCommit
        // TODO add your handling code here:
        txtFHasta.setText(Fecha.fechaCFormulario(dcFHasta.getText()));
        txtFHastaR.setText(Fecha.formatoFecha(dcFHasta.getText()));
    }//GEN-LAST:event_dcFHastaOnCommit

    private void txtFHastaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFHastaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFHastaActionPerformed

    private void txtFDesdeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFDesdeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFDesdeActionPerformed

    private void cbContable1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbContable1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbContable1ActionPerformed

    private void rSButtonIconUno2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIconUno2ActionPerformed
        // TODO add your handling code here:
        DialogExport.dispose();
    }//GEN-LAST:event_rSButtonIconUno2ActionPerformed

    private void rSButtonIconUno1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIconUno1ActionPerformed
        // TODO add your handling code here:
        if (tbContable.getRowCount() <= 0) {
            Mensajes.informacion("La tabla de registros se encuentra vacía.\nVerifique si las fechas (Desde/Hasta) son correctos.");
        } else {
            try {
                clsExportarExcel1 Export = new clsExportarExcel1();
                Export.exportarExcelVentaCabecera(tbContable, this);
            } catch (IOException ex) {
                //Logger.getLogger(dlgClientes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_rSButtonIconUno1ActionPerformed

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
            java.util.logging.Logger.getLogger(dlgReporteTotalVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(dlgReporteTotalVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(dlgReporteTotalVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(dlgReporteTotalVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                dlgReporteTotalVentas dialog = new dlgReporteTotalVentas(new javax.swing.JFrame(), true);
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
    private javax.swing.JDialog DialogExport;
    private javax.swing.ButtonGroup GrupoReporte;
    private org.edisoncor.gui.panel.PanelImage Oscuro;
    private javax.swing.JButton btnGenerar;
    private javax.swing.JButton btnSalir;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox cbCompleto;
    private javax.swing.JCheckBox cbContable;
    private javax.swing.JCheckBox cbContable1;
    private javax.swing.JCheckBox cbResumido;
    public static datechooser.beans.DateChooserCombo dcFDesde;
    public static datechooser.beans.DateChooserCombo dcFHasta;
    private javax.swing.JMenuItem itemNuevoGenerar;
    private javax.swing.JMenuItem itemSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JLabel lbFechaActual;
    private javax.swing.JLabel lbFechaActualR;
    private RSMaterialComponent.RSButtonIconUno rSButtonIconUno1;
    private RSMaterialComponent.RSButtonIconUno rSButtonIconUno2;
    private rojeru_san.rspanel.RSPanelImage rSPanelImage1;
    private javax.swing.JRadioButton rbRankingA;
    private javax.swing.JRadioButton rbRankingF;
    private javax.swing.JTable tbContable;
    public static javax.swing.JTextField txtFDesde;
    public static javax.swing.JTextField txtFDesdeR;
    public static javax.swing.JTextField txtFHasta;
    public static javax.swing.JTextField txtFHastaR;
    // End of variables declaration//GEN-END:variables
}
