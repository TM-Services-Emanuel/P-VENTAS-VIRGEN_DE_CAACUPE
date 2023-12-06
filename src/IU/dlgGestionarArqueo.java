package IU;

import Componentes.Fecha;
import Componentes.Mensajes;
import Componentes.generarCodigos;
import java.awt.Color;
import java.text.DecimalFormat;
import java.sql.*;

public class dlgGestionarArqueo extends javax.swing.JDialog {

    public dlgGestionarArqueo(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        txtFDesdeR.setVisible(false);
        txtFHastaR.setVisible(false);
        rbEntreFechas.doClick();
    }

    private void ValoresPrecioVentaFechas() {
        try {
            StringBuilder sql = new StringBuilder("SELECT IFNULL((SELECT SUM(fac_totalfinal) FROM v_ventatotal_reporte");
            sql.append(" WHERE v_ventatotal_reporte.fecha >='").append(txtFDesdeR.getText()).append("'");
            sql.append(" AND v_ventatotal_reporte.fecha <='").append(txtFHastaR.getText()).append("'),0) AS valorventa");
            long TotalVenta = generarCodigos.getDecimalesLong(sql.toString());
            DecimalFormat df = new DecimalFormat("#,###");
            txtValorVenta.setText(df.format(TotalVenta));
        } catch (Exception e) {
            System.out.println("Valor precio venta: " + e.getMessage());
            txtValorVenta.setText("0");
        }
    }

    private void ValoresPrecioCostoFechas() {
        try {
            StringBuilder sql = new StringBuilder("SELECT IFNULL((SELECT SUM(fac_totalcosto) FROM v_ventatotal_reporte");
            sql.append(" WHERE v_ventatotal_reporte.fecha >='").append(txtFDesdeR.getText()).append("'");
            sql.append(" AND v_ventatotal_reporte.fecha <='").append(txtFHastaR.getText()).append("'),0) AS valorcosto");
            long TotalCosto = generarCodigos.getDecimalesLong(sql.toString());
            DecimalFormat df = new DecimalFormat("#,###");
            txtValorCosto.setText(df.format(TotalCosto));
        } catch (Exception e) {
            System.out.println("Valor precio costo: " + e.getMessage());
            txtValorCosto.setText("0");
        }
    }

    private void CaluclarValorBruto() {
        try {
            long resultado = (Long.parseLong(txtValorVenta.getText().replace(".", "").replace(",", "")) - Long.parseLong(txtValorCosto.getText().replace(".", "").replace(",", "")));
            DecimalFormat df = new DecimalFormat("#,###");
            txtDiferenciaBruta.setText(df.format(resultado));
        } catch (NumberFormatException e) {
            System.out.println("Calculo final bruto: " + e.getMessage());
            txtDiferenciaBruta.setText("0");
        }

    }

    private void GastosVarios() {
        try {
            StringBuilder sql = new StringBuilder("SELECT IFNULL((SELECT SUM(ga_importe) FROM v_gastos_gral");
            sql.append(" WHERE ga_fecha >= '").append(txtFDesdeR.getText()).append("'");
            sql.append(" AND ga_fecha <= '").append(txtFHastaR.getText()).append("' AND motivo_gasto= 1),0) AS sumagasto");
            long GastosVarios = generarCodigos.getDecimalesLong(sql.toString());
            DecimalFormat df = new DecimalFormat("#,###");
            txtGastosVarios.setText(df.format(GastosVarios));
        } catch (NumberFormatException e) {
            txtGastosVarios.setText("0");
        }
    }

    private void GastosAdelanto() {
        try {
            StringBuilder sql = new StringBuilder("SELECT IFNULL((SELECT SUM(ga_importe) FROM v_gastos_gral");
            sql.append(" WHERE ga_fecha >= '").append(txtFDesdeR.getText()).append("'");
            sql.append(" AND ga_fecha <= '").append(txtFHastaR.getText()).append("' AND motivo_gasto= 2),0) AS sumagasto");
            long Adelantos = generarCodigos.getDecimalesLong(sql.toString());
            DecimalFormat df = new DecimalFormat("#,###");
            txtAdelantos.setText(df.format(Adelantos));
        } catch (NumberFormatException e) {
            txtAdelantos.setText("0");
        }
    }

    private void GastosLiquidacion() {
        try {
            StringBuilder sql = new StringBuilder("SELECT IFNULL((SELECT SUM(ga_importe) FROM v_gastos_gral");
            sql.append(" WHERE ga_fecha >= '").append(txtFDesdeR.getText()).append("'");
            sql.append(" AND ga_fecha <= '").append(txtFHastaR.getText()).append("' AND motivo_gasto= 3),0) AS sumagasto");
            long Liquidacion = generarCodigos.getDecimalesLong(sql.toString());
            DecimalFormat df = new DecimalFormat("#,###");
            txtLiquidacion.setText(df.format(Liquidacion));
        } catch (NumberFormatException e) {
            txtLiquidacion.setText("0");
        }
    }

    private void CaluclarGastosGeneral() {
        try {
            long resultado = (Long.parseLong(txtGastosVarios.getText().replace(".", "").replace(",", "")) + Long.parseLong(txtAdelantos.getText().replace(".", "").replace(",", "")) + Long.parseLong(txtLiquidacion.getText().replace(".", "").replace(",", "")));
            DecimalFormat df = new DecimalFormat("#,###");
            txtGastosGeneral.setText(df.format(resultado));
        } catch (NumberFormatException e) {
            txtGastosGeneral.setText("0");
        }

    }

    private void CalcularNeto() {
        try {
            long resultado = (Long.parseLong(txtDiferenciaBruta.getText().replace(".", "").replace(",", "")) - Long.parseLong(txtGastosGeneral.getText().replace(".", "").replace(",", "")));
            DecimalFormat df = new DecimalFormat("#,###");
            if (resultado >= 0) {
                lbResultadoNeto.setForeground(new Color(0, 102, 0));
                lbResultadoNeto.setText("GANANCIA O PERDIDA NETA: " + df.format(resultado));
            } else if (resultado < 0) {
                lbResultadoNeto.setForeground(new Color(255, 0, 0));
                lbResultadoNeto.setText(df.format(resultado));
            }
            lbResultadoNeto.setText("GANANCIA O PERDIDA NETA: " + df.format(resultado));
        } catch (NumberFormatException e) {
            lbResultadoNeto.setText("");
        }
    }

    private void ComprasContado() {
        try {
            StringBuilder sql = new StringBuilder("SELECT IFNULL((SELECT SUM(com_total) FROM compra WHERE com_condpago='CONTADO'");
            sql.append(" AND com_fecha>='").append(txtFDesdeR.getText()).append("'");
            sql.append(" AND com_fecha <='").append(txtFHastaR.getText()).append("' AND com_indicador='S'),0) AS compraContado");
            long CompraContado = generarCodigos.getDecimalesLong(sql.toString());
            DecimalFormat df = new DecimalFormat("#,###");
            txtCompraContado.setText(df.format(CompraContado));
        } catch (NumberFormatException e) {
            txtCompraContado.setText("0");
        }
    }

    private void PagosCreditos() {
        try {
            StringBuilder sql = new StringBuilder("SELECT IFNULL((SELECT SUM(monto_pago) FROM pagos_proveedor");
            sql.append(" WHERE fecha>='").append(txtFDesdeR.getText()).append("'");
            sql.append(" AND fecha <= '").append(txtFHastaR.getText()).append("' AND indicador='S'),0) AS pagoscreditos");
            long PagosCreditos = generarCodigos.getDecimalesLong(sql.toString());
            DecimalFormat df = new DecimalFormat("#,###");
            txtPagosCreditos.setText(df.format(PagosCreditos));
        } catch (NumberFormatException e) {
            txtPagosCreditos.setText("0");
        }
    }

    private void CaluclarPagosGeneral() {
        try {
            long resultado = (Long.parseLong(txtCompraContado.getText().replace(".", "").replace(",", "")) + Long.parseLong(txtPagosCreditos.getText().replace(".", "").replace(",", "")));
            DecimalFormat df = new DecimalFormat("#,###");
            txtPagosGeneral.setText(df.format(resultado));
        } catch (NumberFormatException e) {
            txtPagosGeneral.setText("0");
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnSalir = new RSMaterialComponent.RSButtonIconOne();
        jPanel3 = new javax.swing.JPanel();
        PanelContenedor1 = new rojeru_san.rspanel.RSPanelImage();
        btn = new RSMaterialComponent.RSButtonIconUno();
        Separador1 = new javax.swing.JSeparator();
        LabelTitulo1 = new javax.swing.JLabel();
        txtFDesdeR = new javax.swing.JTextField();
        txtFHastaR = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        rbEntreFechas = new rojerusan.RSRadioButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtFHasta = new javax.swing.JTextField();
        txtFDesde = new javax.swing.JTextField();
        dcFDesde = new datechooser.beans.DateChooserCombo();
        dcFHasta = new datechooser.beans.DateChooserCombo();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtValorVenta = new javax.swing.JTextField();
        txtValorCosto = new javax.swing.JTextField();
        txtDiferenciaBruta = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtGastosVarios = new javax.swing.JTextField();
        txtAdelantos = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtLiquidacion = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtGastosGeneral = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtPagosGeneral = new javax.swing.JTextField();
        txtPagosCreditos = new javax.swing.JTextField();
        txtCompraContado = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        lbResultadoNeto = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(0, 102, 102));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        jPanel2.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(787, 0, -1, -1));

        jPanel3.setOpaque(false);
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PanelContenedor1.setImagen(new javax.swing.ImageIcon(getClass().getResource("/Recursos/CONTENEDOR2.png"))); // NOI18N

        btn.setBackground(new java.awt.Color(255, 255, 255));
        btn.setBackgroundHover(new java.awt.Color(0, 153, 153));
        btn.setForegroundText(new java.awt.Color(0, 153, 153));
        btn.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.FILTER_LIST);
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
        LabelTitulo1.setText("GENERAR");

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

        jPanel3.add(PanelContenedor1, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 3, 100, -1));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 120, 100));

        txtFDesdeR.setEditable(false);
        txtFDesdeR.setBackground(new java.awt.Color(255, 255, 204));
        txtFDesdeR.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 11)); // NOI18N
        txtFDesdeR.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel2.add(txtFDesdeR, new org.netbeans.lib.awtextra.AbsoluteConstraints(134, 18, 78, -1));

        txtFHastaR.setEditable(false);
        txtFHastaR.setBackground(new java.awt.Color(255, 255, 204));
        txtFHastaR.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 11)); // NOI18N
        txtFHastaR.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel2.add(txtFHastaR, new org.netbeans.lib.awtextra.AbsoluteConstraints(134, 45, 78, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 1, 807, -1));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), "Parametrización de busqueda", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Roboto", 1, 11), new java.awt.Color(0, 102, 102))); // NOI18N
        jPanel4.setOpaque(false);
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        buttonGroup1.add(rbEntreFechas);
        rbEntreFechas.setForeground(new java.awt.Color(0, 0, 0));
        rbEntreFechas.setSelected(true);
        rbEntreFechas.setText("Balance general entre Ingresos VS Egresos entre fechas:");
        rbEntreFechas.setColorCheck(new java.awt.Color(204, 102, 0));
        rbEntreFechas.setColorUnCheck(new java.awt.Color(51, 51, 51));
        rbEntreFechas.setFocusPainted(false);
        rbEntreFechas.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        rbEntreFechas.setOpaque(false);
        rbEntreFechas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbEntreFechasActionPerformed(evt);
            }
        });
        jPanel4.add(rbEntreFechas, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 25, 330, 23));

        jLabel1.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        jLabel1.setText("Desde");
        jPanel4.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(405, 25, 36, 23));

        jLabel2.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        jLabel2.setText("Hasta");
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(579, 25, 36, 23));

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
        jPanel4.add(txtFHasta, new org.netbeans.lib.awtextra.AbsoluteConstraints(613, 25, 92, 23));

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
        jPanel4.add(txtFDesde, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 25, 92, 23));

        dcFDesde.setEnabled(false);
        dcFDesde.addCommitListener(new datechooser.events.CommitListener() {
            public void onCommit(datechooser.events.CommitEvent evt) {
                dcFDesdeOnCommit(evt);
            }
        });
        jPanel4.add(dcFDesde, new org.netbeans.lib.awtextra.AbsoluteConstraints(531, 25, 27, 23));

        dcFHasta.setEnabled(false);
        dcFHasta.addCommitListener(new datechooser.events.CommitListener() {
            public void onCommit(datechooser.events.CommitEvent evt) {
                dcFHastaOnCommit(evt);
            }
        });
        jPanel4.add(dcFHasta, new org.netbeans.lib.awtextra.AbsoluteConstraints(704, 25, 27, 23));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 102, 800, 60));

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204))));
        jPanel5.setOpaque(false);
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), "RESUMEN DE VENTAS EN SALÓN", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Roboto", 1, 10), new java.awt.Color(0, 102, 102))); // NOI18N
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel3.setText("Total de valores en precio de venta:");
        jPanel6.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 29, 198, 23));

        jLabel4.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel4.setText("Total de valores en precio de costo:");
        jPanel6.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 59, 198, 23));

        jLabel5.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel5.setText("Ganancia bruta:");
        jPanel6.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 89, 198, 23));

        txtValorVenta.setEditable(false);
        txtValorVenta.setBackground(new java.awt.Color(255, 255, 255));
        txtValorVenta.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        txtValorVenta.setForeground(new java.awt.Color(204, 102, 0));
        txtValorVenta.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtValorVenta.setText("0");
        txtValorVenta.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel6.add(txtValorVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 29, 130, 23));

        txtValorCosto.setEditable(false);
        txtValorCosto.setBackground(new java.awt.Color(255, 255, 255));
        txtValorCosto.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        txtValorCosto.setForeground(new java.awt.Color(255, 0, 0));
        txtValorCosto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtValorCosto.setText("0");
        txtValorCosto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel6.add(txtValorCosto, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 59, 130, 23));

        txtDiferenciaBruta.setEditable(false);
        txtDiferenciaBruta.setBackground(new java.awt.Color(255, 255, 255));
        txtDiferenciaBruta.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        txtDiferenciaBruta.setForeground(new java.awt.Color(0, 102, 0));
        txtDiferenciaBruta.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDiferenciaBruta.setText("0");
        txtDiferenciaBruta.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel6.add(txtDiferenciaBruta, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 89, 130, 23));

        jPanel5.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 14, 364, 126));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), "RESUMEN DE GASTOS REGISTRADOS", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Roboto", 1, 10), new java.awt.Color(0, 102, 102))); // NOI18N
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel6.setText("Total de valores en gastos varios:");
        jPanel7.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 29, 225, 23));

        jLabel7.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel7.setText("Total de valores en adelantos de salarios:");
        jPanel7.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 59, 225, 23));

        txtGastosVarios.setEditable(false);
        txtGastosVarios.setBackground(new java.awt.Color(255, 255, 255));
        txtGastosVarios.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        txtGastosVarios.setForeground(new java.awt.Color(204, 102, 0));
        txtGastosVarios.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtGastosVarios.setText("0");
        txtGastosVarios.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel7.add(txtGastosVarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(265, 29, 130, 23));

        txtAdelantos.setEditable(false);
        txtAdelantos.setBackground(new java.awt.Color(255, 255, 255));
        txtAdelantos.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        txtAdelantos.setForeground(new java.awt.Color(204, 102, 0));
        txtAdelantos.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAdelantos.setText("0");
        txtAdelantos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel7.add(txtAdelantos, new org.netbeans.lib.awtextra.AbsoluteConstraints(265, 59, 130, 23));

        jLabel8.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel8.setText("Total de valores en liquidación de salarios:");
        jPanel7.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 89, -1, 23));

        txtLiquidacion.setEditable(false);
        txtLiquidacion.setBackground(new java.awt.Color(255, 255, 255));
        txtLiquidacion.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        txtLiquidacion.setForeground(new java.awt.Color(204, 102, 0));
        txtLiquidacion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtLiquidacion.setText("0");
        txtLiquidacion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel7.add(txtLiquidacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(265, 89, 130, 23));

        jLabel9.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel9.setText("Total General");
        jPanel7.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 120, 230, 23));

        txtGastosGeneral.setEditable(false);
        txtGastosGeneral.setBackground(new java.awt.Color(255, 255, 255));
        txtGastosGeneral.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        txtGastosGeneral.setForeground(new java.awt.Color(255, 0, 0));
        txtGastosGeneral.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtGastosGeneral.setText("0");
        txtGastosGeneral.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel7.add(txtGastosGeneral, new org.netbeans.lib.awtextra.AbsoluteConstraints(265, 120, 130, 23));

        jPanel5.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(379, 14, 409, 157));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), "RESUMEN DE PAGOS A PROVEEDORES", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Roboto", 1, 10), new java.awt.Color(0, 102, 102))); // NOI18N
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel10.setText("Total de pagos de facturas compra contado:");
        jPanel8.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 29, 240, 23));

        jLabel11.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel11.setText("Total de pagos de facturas compra credito:");
        jPanel8.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 59, 240, 23));

        jLabel12.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel12.setText("Total General");
        jPanel8.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 89, -1, 23));

        txtPagosGeneral.setEditable(false);
        txtPagosGeneral.setBackground(new java.awt.Color(255, 255, 255));
        txtPagosGeneral.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        txtPagosGeneral.setForeground(new java.awt.Color(255, 0, 0));
        txtPagosGeneral.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPagosGeneral.setText("0");
        txtPagosGeneral.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel8.add(txtPagosGeneral, new org.netbeans.lib.awtextra.AbsoluteConstraints(265, 89, 130, 23));

        txtPagosCreditos.setEditable(false);
        txtPagosCreditos.setBackground(new java.awt.Color(255, 255, 255));
        txtPagosCreditos.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        txtPagosCreditos.setForeground(new java.awt.Color(204, 102, 0));
        txtPagosCreditos.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPagosCreditos.setText("0");
        txtPagosCreditos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel8.add(txtPagosCreditos, new org.netbeans.lib.awtextra.AbsoluteConstraints(265, 59, 130, 23));

        txtCompraContado.setEditable(false);
        txtCompraContado.setBackground(new java.awt.Color(255, 255, 255));
        txtCompraContado.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        txtCompraContado.setForeground(new java.awt.Color(204, 102, 0));
        txtCompraContado.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCompraContado.setText("0");
        txtCompraContado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel8.add(txtCompraContado, new org.netbeans.lib.awtextra.AbsoluteConstraints(265, 29, 130, 23));

        jPanel5.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(379, 178, 409, 124));

        jSeparator2.setForeground(new java.awt.Color(204, 204, 204));
        jPanel5.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 310, 785, -1));

        lbResultadoNeto.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        lbResultadoNeto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel5.add(lbResultadoNeto, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 316, 785, 36));

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 165, 796, 360));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 809, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 534, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        int rpta = Mensajes.confirmar("¿Seguro que desea salir del formulario?");
        if (rpta == 0) {
            this.dispose();
        }
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnSalirKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnSalirKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_btnSalirKeyPressed

    private void btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActionPerformed
        // TODO add your handling code here:
        if (txtFDesde.getText().isEmpty()) {
            Mensajes.Sistema("Para generar el reporte es necesario parametrizar la fecha inicio.");
        } else if (txtFHasta.getText().isEmpty()) {
            Mensajes.Sistema("Para generar el reporte es necesario parametrizar la fecha hasta.");
        } else if (Date.valueOf(txtFDesdeR.getText()).after(Date.valueOf(txtFHastaR.getText()))) {
            Mensajes.Sistema("El parametro de inicio seleccionado es una fecha posterior a la fecha hasta.\nPor favor, corrija los parametros y vuelva a generar el reporte.");
        } else {
            ValoresPrecioVentaFechas();
            ValoresPrecioCostoFechas();
            CaluclarValorBruto();
            GastosVarios();
            GastosAdelanto();
            GastosLiquidacion();
            CaluclarGastosGeneral();
            CalcularNeto();
            ComprasContado();
            PagosCreditos();
            CaluclarPagosGeneral();
        }


    }//GEN-LAST:event_btnActionPerformed

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

    private void txtFDesdeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFDesdeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFDesdeActionPerformed

    private void txtFHastaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFHastaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFHastaActionPerformed

    private void rbEntreFechasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbEntreFechasActionPerformed
        // TODO add your handling code here:
        txtFDesde.setEnabled(true);
        dcFDesde.setEnabled(true);
        txtFHasta.setEnabled(true);
        dcFHasta.setEnabled(true);
    }//GEN-LAST:event_rbEntreFechasActionPerformed

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
            java.util.logging.Logger.getLogger(dlgGestionarArqueo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(dlgGestionarArqueo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(dlgGestionarArqueo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(dlgGestionarArqueo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                dlgGestionarArqueo dialog = new dlgGestionarArqueo(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel LabelTitulo1;
    private rojeru_san.rspanel.RSPanelImage PanelContenedor1;
    private javax.swing.JSeparator Separador1;
    private RSMaterialComponent.RSButtonIconUno btn;
    private RSMaterialComponent.RSButtonIconOne btnSalir;
    private javax.swing.ButtonGroup buttonGroup1;
    public static datechooser.beans.DateChooserCombo dcFDesde;
    public static datechooser.beans.DateChooserCombo dcFHasta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lbResultadoNeto;
    private rojerusan.RSRadioButton rbEntreFechas;
    private javax.swing.JTextField txtAdelantos;
    private javax.swing.JTextField txtCompraContado;
    private javax.swing.JTextField txtDiferenciaBruta;
    public static javax.swing.JTextField txtFDesde;
    public static javax.swing.JTextField txtFDesdeR;
    public static javax.swing.JTextField txtFHasta;
    public static javax.swing.JTextField txtFHastaR;
    private javax.swing.JTextField txtGastosGeneral;
    private javax.swing.JTextField txtGastosVarios;
    private javax.swing.JTextField txtLiquidacion;
    private javax.swing.JTextField txtPagosCreditos;
    private javax.swing.JTextField txtPagosGeneral;
    private javax.swing.JTextField txtValorCosto;
    private javax.swing.JTextField txtValorVenta;
    // End of variables declaration//GEN-END:variables
}
