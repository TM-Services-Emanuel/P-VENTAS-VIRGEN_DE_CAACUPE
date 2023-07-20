package IU;

import Componentes.Fecha;
import Componentes.Mensajes;
import Componentes.RenderDecimal2;
import Componentes.RenderDecimalconPuntos;
import Componentes.RenderDecimalconPuntos1;
import Componentes.Reporte;
import Componentes.Software;
import Componentes.clsExportarExcel;
import Controladores.CabecerasTablas;
import Controladores.controlArticuloMovil;
import Controladores.controlFactura;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;

public class dlgArticulosMovil extends javax.swing.JDialog {

    CabecerasTablas cabe = new CabecerasTablas();
    clsExportarExcel Export;
    public Reporte jasper;

    public dlgArticulosMovil(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        titulo();
        jasper = new Reporte();
        ckStockActionPerformed(null);
        txtBuscar.requestFocus();
    }

    final void titulo() {
        if (Software.getSoftware().equals("null")) {
            this.setTitle("Gestor de Productos");
        } else {
            this.setTitle(Software.getSoftware() + " - Gestor de Productos");
        }
    }

    public static void Renders() {
        dlgArticulosMovil.tbProductos.getColumnModel().getColumn(7).setCellRenderer(new RenderDecimal2());

        dlgArticulosMovil.tbProductos.getColumnModel().getColumn(8).setCellRenderer(new RenderDecimal2());
        dlgArticulosMovil.tbProductos.getColumnModel().getColumn(9).setCellRenderer(new RenderDecimal2());
        dlgArticulosMovil.tbProductos.getColumnModel().getColumn(10).setCellRenderer(new RenderDecimalconPuntos());
        dlgArticulosMovil.tbProductos.getColumnModel().getColumn(13).setCellRenderer(new RenderDecimal2());
        dlgArticulosMovil.tbProductos.getColumnModel().getColumn(14).setCellRenderer(new RenderDecimal2());
    }
    
    public static void RenderAuditoria(){
        dlgArticulosMovil.tbAuditoriaProductos.getColumnModel().getColumn(1).setCellRenderer(new RenderDecimalconPuntos());
        dlgArticulosMovil.tbAuditoriaProductos.getColumnModel().getColumn(2).setCellRenderer(new RenderDecimalconPuntos());
        dlgArticulosMovil.tbAuditoriaProductos.getColumnModel().getColumn(3).setCellRenderer(new RenderDecimalconPuntos1());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuE = new javax.swing.JPopupMenu();
        mbtnAuditar = new javax.swing.JMenuItem();
        grupoBotones = new javax.swing.ButtonGroup();
        buttonGroup1 = new javax.swing.ButtonGroup();
        DialogAuditar = new javax.swing.JDialog();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtidPA = new javax.swing.JTextField();
        txtCodInternoPA = new javax.swing.JTextField();
        txtCodBarraPA = new javax.swing.JTextField();
        txtDescripcionPA = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtFDesde = new javax.swing.JTextField();
        dcFDesde = new datechooser.beans.DateChooserCombo();
        txtFHasta = new javax.swing.JTextField();
        dcFHasta = new datechooser.beans.DateChooserCombo();
        jLabel8 = new javax.swing.JLabel();
        rSButtonGradiente1 = new rsbuttongradiente.RSButtonGradiente();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbAuditoriaProductos = new javax.swing.JTable();
        txtFDesdeR = new javax.swing.JTextField();
        txtFHastaR = new javax.swing.JTextField();
        panelImage2 = new org.edisoncor.gui.panel.PanelImage();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbProductos = new javax.swing.JTable()
        {
            public boolean isCellEditable(int rowInddex, int celIndex)
            {
                return false;
            }
        };
        panelImage1 = new org.edisoncor.gui.panel.PanelImage();
        jPanel1 = new javax.swing.JPanel();
        btnNuevo = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        ckStock = new javax.swing.JCheckBox();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        itemNuevoE = new javax.swing.JMenuItem();
        itemModificarE = new javax.swing.JMenuItem();
        itemEliminarE = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        itemOrdenC = new javax.swing.JMenuItem();
        itemOrdenN = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        itemExportar = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        itemSalir = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        itemNuevoE1 = new javax.swing.JMenuItem();

        mbtnAuditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/baseline_move_down_black_24.png"))); // NOI18N
        mbtnAuditar.setText("Visualizar movimientos de este producto");
        mbtnAuditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mbtnAuditarActionPerformed(evt);
            }
        });
        menuE.add(mbtnAuditar);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 102));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("VISUALIZAR MOVIMIENTO DE PRODUCTO");

        jLabel2.setText("ID PRODUCTO");

        jLabel4.setText("COD INTERNO");

        jLabel5.setText("COD BARRA");

        jLabel6.setText("DESCRIPCIÓN");

        txtidPA.setEditable(false);
        txtidPA.setBackground(new java.awt.Color(255, 255, 255));
        txtidPA.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        txtidPA.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtCodInternoPA.setEditable(false);
        txtCodInternoPA.setBackground(new java.awt.Color(255, 255, 255));
        txtCodInternoPA.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtCodInternoPA.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtCodBarraPA.setEditable(false);
        txtCodBarraPA.setBackground(new java.awt.Color(255, 255, 255));
        txtCodBarraPA.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtCodBarraPA.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtDescripcionPA.setEditable(false);
        txtDescripcionPA.setBackground(new java.awt.Color(255, 255, 255));
        txtDescripcionPA.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N

        jLabel7.setText("DESDE FECHA");

        txtFDesde.setEditable(false);
        txtFDesde.setBackground(new java.awt.Color(255, 255, 255));
        txtFDesde.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        txtFDesde.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFDesde.setEnabled(false);
        txtFDesde.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFDesdeActionPerformed(evt);
            }
        });

        dcFDesde.addCommitListener(new datechooser.events.CommitListener() {
            public void onCommit(datechooser.events.CommitEvent evt) {
                dcFDesdeOnCommit(evt);
            }
        });

        txtFHasta.setEditable(false);
        txtFHasta.setBackground(new java.awt.Color(255, 255, 255));
        txtFHasta.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        txtFHasta.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFHasta.setEnabled(false);
        txtFHasta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFHastaActionPerformed(evt);
            }
        });

        dcFHasta.addCommitListener(new datechooser.events.CommitListener() {
            public void onCommit(datechooser.events.CommitEvent evt) {
                dcFHastaOnCommit(evt);
            }
        });

        jLabel8.setText("HASTA FECHA");

        rSButtonGradiente1.setText("CARGAR TABLA");
        rSButtonGradiente1.setColorPrimario(new java.awt.Color(0, 102, 102));
        rSButtonGradiente1.setColorPrimarioHover(new java.awt.Color(0, 84, 94));
        rSButtonGradiente1.setColorSecundario(new java.awt.Color(0, 84, 94));
        rSButtonGradiente1.setColorSecundarioHover(new java.awt.Color(0, 102, 102));
        rSButtonGradiente1.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        rSButtonGradiente1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonGradiente1ActionPerformed(evt);
            }
        });

        jScrollPane3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        tbAuditoriaProductos.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        tbAuditoriaProductos.setModel(new javax.swing.table.DefaultTableModel(
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
        tbAuditoriaProductos.setGridColor(new java.awt.Color(204, 204, 204));
        tbAuditoriaProductos.setRowHeight(20);
        tbAuditoriaProductos.setShowGrid(true);
        tbAuditoriaProductos.setShowVerticalLines(false);
        tbAuditoriaProductos.getTableHeader().setResizingAllowed(false);
        tbAuditoriaProductos.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(tbAuditoriaProductos);

        txtFDesdeR.setEditable(false);
        txtFDesdeR.setBackground(new java.awt.Color(255, 255, 204));
        txtFDesdeR.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 11)); // NOI18N
        txtFDesdeR.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtFHastaR.setEditable(false);
        txtFHastaR.setBackground(new java.awt.Color(255, 255, 204));
        txtFHastaR.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 11)); // NOI18N
        txtFHastaR.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtidPA, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(txtCodInternoPA, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(37, 37, 37)
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtCodBarraPA, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 105, Short.MAX_VALUE)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtFHastaR, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtFDesdeR, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtDescripcionPA)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addComponent(txtFDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, 0)
                                                .addComponent(dcFDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addComponent(txtFHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, 0)
                                                .addComponent(dcFHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(rSButtonGradiente1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(0, 0, Short.MAX_VALUE)))))))
                .addContainerGap())
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(rSButtonGradiente1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(txtidPA, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(txtCodInternoPA, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCodBarraPA, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(txtFDesdeR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtFHastaR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtDescripcionPA, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtFDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(dcFDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dcFHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(61, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout DialogAuditarLayout = new javax.swing.GroupLayout(DialogAuditar.getContentPane());
        DialogAuditar.getContentPane().setLayout(DialogAuditarLayout);
        DialogAuditarLayout.setHorizontalGroup(
            DialogAuditarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        DialogAuditarLayout.setVerticalGroup(
            DialogAuditarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DialogAuditarLayout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
                formWindowLostFocus(evt);
            }
        });
        addWindowStateListener(new java.awt.event.WindowStateListener() {
            public void windowStateChanged(java.awt.event.WindowEvent evt) {
                formWindowStateChanged(evt);
            }
        });

        panelImage2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/fondoBlanco.jpg"))); // NOI18N
        panelImage2.setPreferredSize(new java.awt.Dimension(690, 418));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel2.setOpaque(false);

        jLabel3.setFont(new java.awt.Font("Roboto", 1, 10)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/search15.png"))); // NOI18N
        jLabel3.setText("BUSCADOR DE PRODUCTO");
        jLabel3.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        txtBuscar.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarActionPerformed(evt);
            }
        });
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jScrollPane1.setOpaque(false);

        tbProductos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        tbProductos.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        tbProductos.setModel(new javax.swing.table.DefaultTableModel(
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
        tbProductos.setGridColor(new java.awt.Color(204, 204, 204));
        tbProductos.setRowHeight(20);
        tbProductos.setSelectionBackground(new java.awt.Color(0, 102, 102));
        tbProductos.setShowGrid(true);
        tbProductos.setShowVerticalLines(false);
        tbProductos.getTableHeader().setResizingAllowed(false);
        tbProductos.getTableHeader().setReorderingAllowed(false);
        tbProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbProductosMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbProductosMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tbProductosMouseReleased(evt);
            }
        });
        tbProductos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbProductosKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tbProductos);

        panelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/0-102-102.png"))); // NOI18N
        panelImage1.setPreferredSize(new java.awt.Dimension(690, 418));
        panelImage1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)));
        jPanel1.setLayout(new java.awt.GridLayout(1, 6));

        btnNuevo.setFont(new java.awt.Font("Roboto", 1, 9)); // NOI18N
        btnNuevo.setForeground(new java.awt.Color(0, 102, 102));
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/productosAgregar.png"))); // NOI18N
        btnNuevo.setText("NUEVO");
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
        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/productosModificar.png"))); // NOI18N
        btnModificar.setText("MODIFICAR");
        btnModificar.setFocusPainted(false);
        btnModificar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnModificar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        jPanel1.add(btnModificar);

        btnEliminar.setFont(new java.awt.Font("Roboto", 1, 9)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(0, 102, 102));
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/productosEliminar.png"))); // NOI18N
        btnEliminar.setText("ELIMINAR");
        btnEliminar.setFocusPainted(false);
        btnEliminar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEliminar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEliminar);

        btnActualizar.setFont(new java.awt.Font("Roboto", 1, 9)); // NOI18N
        btnActualizar.setForeground(new java.awt.Color(0, 102, 102));
        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/productosActualizar.png"))); // NOI18N
        btnActualizar.setText("ACTUALIZAR");
        btnActualizar.setFocusPainted(false);
        btnActualizar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnActualizar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });
        jPanel1.add(btnActualizar);

        btnSalir.setFont(new java.awt.Font("Roboto", 1, 9)); // NOI18N
        btnSalir.setForeground(new java.awt.Color(0, 102, 102));
        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/back40.png"))); // NOI18N
        btnSalir.setText("SALIR");
        btnSalir.setFocusPainted(false);
        btnSalir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSalir.setPreferredSize(new java.awt.Dimension(53, 47));
        btnSalir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        jPanel1.add(btnSalir);

        panelImage1.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 7, -1, -1));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel3.setOpaque(false);

        ckStock.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        ckStock.setForeground(new java.awt.Color(255, 0, 0));
        ckStock.setSelected(true);
        ckStock.setText("EXCLUIR PRODUCTOS CON STOCK 0");
        ckStock.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ckStock.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        ckStock.setIconTextGap(10);
        ckStock.setOpaque(false);
        ckStock.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        ckStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckStockActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ckStock)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ckStock, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panelImage2Layout = new javax.swing.GroupLayout(panelImage2);
        panelImage2.setLayout(panelImage2Layout);
        panelImage2Layout.setHorizontalGroup(
            panelImage2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImage1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelImage2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(panelImage2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelImage2Layout.setVerticalGroup(
            panelImage2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage2Layout.createSequentialGroup()
                .addComponent(panelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelImage2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE))
        );

        jMenu1.setText("OPCIONES");
        jMenu1.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        jMenu1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        itemNuevoE.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        itemNuevoE.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        itemNuevoE.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/productosAgregar - copia.png"))); // NOI18N
        itemNuevoE.setText("NUEVO");
        itemNuevoE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemNuevoEActionPerformed(evt);
            }
        });
        jMenu1.add(itemNuevoE);

        itemModificarE.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        itemModificarE.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        itemModificarE.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/productosModificar - copia.png"))); // NOI18N
        itemModificarE.setText("MODIFICAR ");
        itemModificarE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemModificarEActionPerformed(evt);
            }
        });
        jMenu1.add(itemModificarE);

        itemEliminarE.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_DELETE, 0));
        itemEliminarE.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        itemEliminarE.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/productosEliminar - copia.png"))); // NOI18N
        itemEliminarE.setText("ELIMINAR");
        itemEliminarE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemEliminarEActionPerformed(evt);
            }
        });
        jMenu1.add(itemEliminarE);
        jMenu1.add(jSeparator4);

        itemOrdenC.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        itemOrdenC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/orderC15.png"))); // NOI18N
        itemOrdenC.setText("ORDENAR POR ID");
        itemOrdenC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemOrdenCActionPerformed(evt);
            }
        });
        jMenu1.add(itemOrdenC);

        itemOrdenN.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        itemOrdenN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/orderN15.png"))); // NOI18N
        itemOrdenN.setText("ORDENAR DOR DESCRIPCIÓN");
        itemOrdenN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemOrdenNActionPerformed(evt);
            }
        });
        jMenu1.add(itemOrdenN);
        jMenu1.add(jSeparator2);

        itemExportar.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        itemExportar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/excel15.png"))); // NOI18N
        itemExportar.setText("EXPORTAR DATOS DE LA TABLA A EXCEL");
        itemExportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemExportarActionPerformed(evt);
            }
        });
        jMenu1.add(itemExportar);
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

        jMenu2.setText("IMPRIMIR");
        jMenu2.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        jMenu2.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        itemNuevoE1.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        itemNuevoE1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/reports.png"))); // NOI18N
        itemNuevoE1.setText("REPORTE DE PRODUCTOS CON STOCK CRÍTICIO (< STOCK MIN.)");
        itemNuevoE1.setEnabled(false);
        itemNuevoE1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemNuevoE1ActionPerformed(evt);
            }
        });
        jMenu2.add(itemNuevoE1);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImage2, javax.swing.GroupLayout.DEFAULT_SIZE, 1239, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImage2, javax.swing.GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbProductosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbProductosMousePressed
        // TODO add your handling code here:
        if (SwingUtilities.isRightMouseButton(evt)) {
            Point p = evt.getPoint();
            int number = tbProductos.rowAtPoint(p);
            ListSelectionModel modelos = tbProductos.getSelectionModel();
            modelos.setSelectionInterval(number, number);
        }

        if (evt.getButton() == 3) {
            menuE.show(tbProductos, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_tbProductosMousePressed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here:
        nuevoArticulo();
    }//GEN-LAST:event_btnNuevoActionPerformed
    void nuevoArticulo() {
        dlgGestArticulosMovil gestArticulos = new dlgGestArticulosMovil(null, true);
        gestArticulos.setLocationRelativeTo(null);
        gestArticulos.Nuevo();
        gestArticulos.setVisible(true);

    }
    private void mbtnAuditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mbtnAuditarActionPerformed
        // TODO add your handling code here:
        int f = tbProductos.getSelectedRow();
        txtidPA.setText(tbProductos.getValueAt(f, 0).toString());
        txtCodInternoPA.setText(tbProductos.getValueAt(f, 3).toString());
        txtCodBarraPA.setText(tbProductos.getValueAt(f, 4).toString());
        txtDescripcionPA.setText(tbProductos.getValueAt(f, 5).toString());
        CabecerasTablas.AuditoriaProductos(tbAuditoriaProductos);
        CabecerasTablas.limpiarTablas(tbAuditoriaProductos);
        DialogAuditar.setSize(665, 663);
        DialogAuditar.setLocationRelativeTo(this);
        DialogAuditar.setModal(true);
        DialogAuditar.setTitle("OPCIONES");
        DialogAuditar.setVisible(true);
    }//GEN-LAST:event_mbtnAuditarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        // TODO add your handling code here:
        modArticulo();
    }//GEN-LAST:event_btnModificarActionPerformed
    void modArticulo() {
        int x = tbProductos.getSelectedRow();
        if (x < 0) {
            Mensajes.informacion("Seleccione una fila de la tabla");
        } else {
            try {
                dlgGestArticulosMovil a = new dlgGestArticulosMovil(null, true);
                a.setLocationRelativeTo(null);
                controlArticuloMovil.aModifcar();
                dlgGestArticulosMovil.btnNuevo.setEnabled(false);
                dlgGestArticulosMovil.itemNuevo.setEnabled(false);
                dlgGestArticulosMovil.btnModificar.setEnabled(true);
                dlgGestArticulosMovil.itemModificar.setEnabled(true);
                dlgGestArticulosMovil.btnGuardar.setEnabled(false);
                dlgGestArticulosMovil.itemGuardar.setEnabled(false);
                dlgGestArticulosMovil.btnCancelar.setEnabled(true);
                dlgGestArticulosMovil.itemCancelar.setEnabled(true);
                dlgGestArticulosMovil.txtCodInterno.requestFocus();
                a.modcboClasificacion();
                a.modcboUM();
                a.modcboImpuesto();
                a.setVisible(true);
            } catch (Exception e) {
                //Mensajes.error("Seleccione una fila de la tabla");     
            }
            tbProductos.clearSelection();
        }

    }
    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        delArticulo();
    }//GEN-LAST:event_btnEliminarActionPerformed
    void delArticulo() {
        try {
            int x = tbProductos.getSelectedRow();
            String desc = tbProductos.getValueAt(x, 4).toString();
            int rpta = Mensajes.confirmar("Desea realmente eliminar " + desc + " de la lista");
            if (rpta == 0) {
                controlArticuloMovil.delArticulo();
                if (ckStock.isSelected()) {
                    CabecerasTablas.ArticulosMovil(tbProductos);
                    CabecerasTablas.limpiarTablasArticuloM(tbProductos);
                    controlArticuloMovil.listArticuloconStock(tbProductos, "idproducto");
                    Renders();
                } else {
                    CabecerasTablas.ArticulosMovil(tbProductos);
                    CabecerasTablas.limpiarTablasArticuloM(tbProductos);
                    controlArticuloMovil.listArticulo(tbProductos, "idproducto");
                    Renders();
                }
                txtBuscar.setText("");
                txtBuscar.requestFocus();
            }
        } catch (Exception e) {
            Mensajes.error("Seleccione una fila de la tabla");
        }
    }
    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        int rpta = Mensajes.confirmar("¿Seguro que desea salir del formulario?");
        if (rpta == 0) {
            this.dispose();
        } else {
            txtBuscar.requestFocus();
        }
    }//GEN-LAST:event_btnSalirActionPerformed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        // TODO add your handling code here:
        if (txtBuscar.getText().trim().length() == 0) {
            if (ckStock.isSelected()) {
                CabecerasTablas.ArticulosMovil(tbProductos);
                CabecerasTablas.limpiarTablasArticuloM(tbProductos);
                controlArticuloMovil.listArticuloconStock(tbProductos, "idproducto");
                Renders();
            } else {
                CabecerasTablas.ArticulosMovil(tbProductos);
                CabecerasTablas.limpiarTablasArticuloM(tbProductos);
                controlArticuloMovil.listArticulo(tbProductos, "idproducto");
                Renders();
            }
        } else {
            try {
                String cod = txtBuscar.getText();
                //CabecerasTablas.ArticulosMovil(tbProductos);
                CabecerasTablas.limpiarTablasArticuloM(tbProductos);
                controlArticuloMovil.filtrarGral(tbProductos, cod);
                Renders();
            } catch (Exception e) {
                System.out.println("Mensaje de Error: " + e.getMessage());
            }
        }

    }//GEN-LAST:event_txtBuscarKeyReleased

    private void tbProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbProductosMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            try {
                dlgGestArticulosMovil a = new dlgGestArticulosMovil(null, true);
                a.setLocationRelativeTo(null);
                controlArticuloMovil.aModifcar();
                //a.setTitle("Gestionar Productos");
                dlgGestArticulosMovil.btnNuevo.setEnabled(false);
                dlgGestArticulosMovil.itemNuevo.setEnabled(false);
                dlgGestArticulosMovil.btnModificar.setEnabled(true);
                dlgGestArticulosMovil.itemModificar.setEnabled(true);
                dlgGestArticulosMovil.btnGuardar.setEnabled(false);
                dlgGestArticulosMovil.itemGuardar.setEnabled(false);
                dlgGestArticulosMovil.btnCancelar.setEnabled(true);
                dlgGestArticulosMovil.itemCancelar.setEnabled(true);
                dlgGestArticulosMovil.txtCodInterno.requestFocus();
                a.modcboClasificacion();
                a.modcboUM();
                a.modcboImpuesto();
                a.setVisible(true);
            } catch (Exception e) {
                Mensajes.error("No se pudo cagar informacion del Producto");

            }
        }

    }//GEN-LAST:event_tbProductosMouseClicked

    private void txtBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (Character.isLowerCase(c)) {
            String cad = ("" + c).toUpperCase();
            c = cad.charAt(0);
            evt.setKeyChar(c);
        }
    }//GEN-LAST:event_txtBuscarKeyTyped

    private void itemNuevoEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemNuevoEActionPerformed
        // TODO add your handling code here:
        btnNuevoActionPerformed(null);
    }//GEN-LAST:event_itemNuevoEActionPerformed

    private void itemModificarEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemModificarEActionPerformed
        // TODO add your handling code here:
        btnModificarActionPerformed(null);
    }//GEN-LAST:event_itemModificarEActionPerformed

    private void itemEliminarEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemEliminarEActionPerformed
        // TODO add your handling code here:
        btnEliminarActionPerformed(null);
    }//GEN-LAST:event_itemEliminarEActionPerformed

    private void itemOrdenCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemOrdenCActionPerformed
        // TODO add your handling code here:
        CabecerasTablas.limpiarTablasArticuloM(tbProductos);
        controlArticuloMovil.listArticulo(tbProductos, "idproducto");
    }//GEN-LAST:event_itemOrdenCActionPerformed

    private void itemOrdenNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemOrdenNActionPerformed
        // TODO add your handling code here:
        CabecerasTablas.limpiarTablasArticuloM(tbProductos);
        controlArticuloMovil.listArticulo(tbProductos, "descripcion");
    }//GEN-LAST:event_itemOrdenNActionPerformed

    private void itemExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemExportarActionPerformed

        try {
            Export = new clsExportarExcel();
            Export.exportarExcel(tbProductos);
        } catch (IOException ex) {
            Logger.getLogger(dlgClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_itemExportarActionPerformed

    private void itemSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemSalirActionPerformed
        // TODO add your handling code here:
        btnSalirActionPerformed(null);
    }//GEN-LAST:event_itemSalirActionPerformed

    private void tbProductosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbProductosKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                dlgGestArticulosMovil a = new dlgGestArticulosMovil(null, true);
                a.setLocationRelativeTo(null);
                controlArticuloMovil.aModifcar();
                dlgGestArticulosMovil.btnNuevo.setEnabled(false);
                dlgGestArticulosMovil.itemNuevo.setEnabled(false);
                dlgGestArticulosMovil.btnModificar.setEnabled(true);
                dlgGestArticulosMovil.itemModificar.setEnabled(true);
                dlgGestArticulosMovil.btnGuardar.setEnabled(false);
                dlgGestArticulosMovil.itemGuardar.setEnabled(false);
                dlgGestArticulosMovil.btnCancelar.setEnabled(true);
                dlgGestArticulosMovil.itemCancelar.setEnabled(true);
                dlgGestArticulosMovil.txtCodInterno.requestFocus();
                a.modcboClasificacion();
                a.modcboUM();
                a.modcboImpuesto();
                a.setVisible(true);
            } catch (Exception e) {
                //Mensajes.error("No se pudo cagar informacion del Producto" + e.getMessage());
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            txtBuscar.requestFocus();
            txtBuscar.selectAll();
        }
    }//GEN-LAST:event_tbProductosKeyPressed

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarActionPerformed

    private void formWindowLostFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowLostFocus

    }//GEN-LAST:event_formWindowLostFocus

    private void formWindowStateChanged(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowStateChanged

    private void txtBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (tbProductos.getRowCount() <= 0) {
                txtBuscar.requestFocus();
                txtBuscar.selectAll();
            } else {
                //tbProductos.requestFocus();
                tbProductos.getSelectionModel().setSelectionInterval(0, 0);
                tbProductos.requestFocus();
            }
        }
    }//GEN-LAST:event_txtBuscarKeyPressed

    private void itemNuevoE1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemNuevoE1ActionPerformed
        // TODO add your handling code here:
        /* try {
            dlgReporteStockCritico rsc = new dlgReporteStockCritico(null, false);
            rsc.setLocationRelativeTo(null);
            rsc.setVisible(true);
        } catch (Exception e) {
            Mensajes.informacion("No hay conexión con el servidor");
        }*/

        Mensajes.Sistema("La función: Reporte de Stock crítico se encuentra bloqueada en estos momentos.\nPara más información comuniquese con el proveedor del sistema.");
        //
    }//GEN-LAST:event_itemNuevoE1ActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        // TODO add your handling code here:
        if (ckStock.isSelected()) {
            CabecerasTablas.ArticulosMovil(tbProductos);
            CabecerasTablas.limpiarTablasArticuloM(tbProductos);
            controlArticuloMovil.listArticuloconStock(tbProductos, "idproducto");
            Renders();
        } else {
            CabecerasTablas.ArticulosMovil(tbProductos);
            CabecerasTablas.limpiarTablasArticuloM(tbProductos);
            controlArticuloMovil.listArticulo(tbProductos, "idproducto");
            Renders();
        }
        txtBuscar.setText("");
        txtBuscar.requestFocus();
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void ckStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckStockActionPerformed
        // TODO add your handling code here:
        if (ckStock.isSelected()) {
            CabecerasTablas.ArticulosMovil(tbProductos);
            CabecerasTablas.limpiarTablasArticuloM(tbProductos);
            controlArticuloMovil.listArticuloconStock(tbProductos, "idproducto");
            Renders();
        } else {
            CabecerasTablas.ArticulosMovil(tbProductos);
            CabecerasTablas.limpiarTablasArticuloM(tbProductos);
            controlArticuloMovil.listArticulo(tbProductos, "idproducto");
            Renders();
        }
    }//GEN-LAST:event_ckStockActionPerformed

    private void txtFDesdeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFDesdeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFDesdeActionPerformed

    private void dcFDesdeOnCommit(datechooser.events.CommitEvent evt) {//GEN-FIRST:event_dcFDesdeOnCommit
        // TODO add your handling code here:
        txtFDesde.setText(Fecha.fechaCFormulario(dcFDesde.getText()));
        txtFDesdeR.setText(Fecha.formatoFecha(dcFDesde.getText()));
    }//GEN-LAST:event_dcFDesdeOnCommit

    private void txtFHastaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFHastaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFHastaActionPerformed

    private void dcFHastaOnCommit(datechooser.events.CommitEvent evt) {//GEN-FIRST:event_dcFHastaOnCommit
        // TODO add your handling code here:
        txtFHasta.setText(Fecha.fechaCFormulario(dcFHasta.getText()));
        txtFHastaR.setText(Fecha.formatoFecha(dcFHasta.getText()));
    }//GEN-LAST:event_dcFHastaOnCommit

    private void tbProductosMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbProductosMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tbProductosMouseReleased

    private void rSButtonGradiente1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonGradiente1ActionPerformed
        // TODO add your handling code here:
        CabecerasTablas.limpiarTablas(tbAuditoriaProductos);
        controlArticuloMovil.listAuditoriaProductos(tbAuditoriaProductos,txtidPA.getText().trim(), txtFDesdeR.getText().trim(), txtFHastaR.getText().trim());
        RenderAuditoria();
    }//GEN-LAST:event_rSButtonGradiente1ActionPerformed

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
            java.util.logging.Logger.getLogger(dlgArticulosMovil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        //</editor-fold>
        //</editor-fold>
        java.awt.EventQueue.invokeLater(() -> {
            dlgArticulosMovil dialog = new dlgArticulosMovil(new javax.swing.JFrame(), true);
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
    private javax.swing.JDialog DialogAuditar;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSalir;
    private javax.swing.ButtonGroup buttonGroup1;
    public static javax.swing.JCheckBox ckStock;
    public static datechooser.beans.DateChooserCombo dcFDesde;
    public static datechooser.beans.DateChooserCombo dcFHasta;
    private javax.swing.ButtonGroup grupoBotones;
    private javax.swing.JMenuItem itemEliminarE;
    private javax.swing.JMenuItem itemExportar;
    private javax.swing.JMenuItem itemModificarE;
    private javax.swing.JMenuItem itemNuevoE;
    public javax.swing.JMenuItem itemNuevoE1;
    private javax.swing.JMenuItem itemOrdenC;
    private javax.swing.JMenuItem itemOrdenN;
    private javax.swing.JMenuItem itemSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JMenuItem mbtnAuditar;
    private javax.swing.JPopupMenu menuE;
    private org.edisoncor.gui.panel.PanelImage panelImage1;
    private org.edisoncor.gui.panel.PanelImage panelImage2;
    private rsbuttongradiente.RSButtonGradiente rSButtonGradiente1;
    private static javax.swing.JTable tbAuditoriaProductos;
    public static javax.swing.JTable tbProductos;
    public static javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCodBarraPA;
    private javax.swing.JTextField txtCodInternoPA;
    private javax.swing.JTextField txtDescripcionPA;
    public static javax.swing.JTextField txtFDesde;
    public static javax.swing.JTextField txtFDesdeR;
    public static javax.swing.JTextField txtFHasta;
    public static javax.swing.JTextField txtFHastaR;
    private javax.swing.JTextField txtidPA;
    // End of variables declaration//GEN-END:variables
}
