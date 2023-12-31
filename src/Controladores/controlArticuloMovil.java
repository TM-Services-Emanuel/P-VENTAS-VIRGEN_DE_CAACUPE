package Controladores;

import Componentes.DataSourceService1;
import Componentes.Fecha;
import Componentes.Login;
import Componentes.Mensajes;
import Componentes.Notif;
import Datos.GestionarArticulosMovil;
import IU.dlgArticulosMovil;
import IU.dlgGestArticulosMovil;
import IU.dlgGestArticulosMovil11;
import Modelo.ArticuloMovil;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class controlArticuloMovil {

    static DataSourceService1 dss = new DataSourceService1();
    static int codClasificacion;
    static int codUM;
    static int codImpuesto;

    public static void aModifcar() {
        try {
            int x = dlgArticulosMovil.tbProductos.getSelectedRow();
            String cod = dlgArticulosMovil.tbProductos.getValueAt(x, 0).toString();
            System.out.println("articulo a mod: " + cod);
            ArticuloMovil ar = GestionarArticulosMovil.busArticulo(cod);
            dlgGestArticulosMovil.txtCodProducto.setText(String.valueOf(ar.getIdproducto()));
            if (ar.getDependencia().equals("S")) {
                dlgGestArticulosMovil.ckDependencia.setSelected(true);
                dlgGestArticulosMovil.txtidDependencia.setText(String.valueOf(ar.getIddependencia()));
            } else if (ar.getDependencia().equals("N")) {
                dlgGestArticulosMovil.ckDependencia.setSelected(false);
                dlgGestArticulosMovil.txtidDependencia.setText(String.valueOf(ar.getIddependencia()));
            }
            dlgGestArticulosMovil.txtCodInterno.setText((ar.getCodinterno()));
            dlgGestArticulosMovil.txtCodBarra.setText((ar.getCodBarra()));
            dlgGestArticulosMovil.txtDescripcion.setText(ar.getDescripcion());
            DecimalFormat df = new DecimalFormat("#,###");
            dlgGestArticulosMovil.txtPrecioCostoL.setText(String.valueOf(ar.getPrecio_costo()));
            dlgGestArticulosMovil.txtCosto.setText(df.format(Integer.valueOf(dlgGestArticulosMovil.txtPrecioCostoL.getText().trim().replace(".", "").replace(",", ""))));
            dlgGestArticulosMovil.txtGanancia.setText(String.valueOf(df.format(ar.getGanancia())));
            dlgGestArticulosMovil.txtPrecioVentaL.setText(String.valueOf(ar.getPrecio_venta()));
            dlgGestArticulosMovil.txtPrecioVenta.setText(df.format(Integer.valueOf(dlgGestArticulosMovil.txtPrecioVentaL.getText().trim().replace(".", "").replace(",", ""))));
            dlgGestArticulosMovil.txtStock.setText(String.valueOf(ar.getStock()).trim().replace(".0", "").replace(",", ""));
            dlgGestArticulosMovil.txtCodClasificacion.setText(String.valueOf(ar.getDivision()));
            dlgGestArticulosMovil.txtCodImpuesto.setText(String.valueOf(ar.getIva()));
            dlgGestArticulosMovil.txtCodUM.setText(String.valueOf(ar.getUm()));
            if (ar.getVentam().equals("SI")) {
                dlgGestArticulosMovil.ckHabilitar.setSelected(true);
                dlgGestArticulosMovil.txtCantM.setEnabled(true);
            } else {
                dlgGestArticulosMovil.ckHabilitar.setSelected(false);
                dlgGestArticulosMovil.txtCantM.setEnabled(false);
            }
            dlgGestArticulosMovil.txtCantM.setText(String.valueOf(ar.getCantm()).trim().replace(".0", "").replace(",", ""));

            if (ar.getProm().equals("S")) {
                dlgGestArticulosMovil.ckPromocion.setSelected(true);
                dlgGestArticulosMovil.txtCantPromocion.setEnabled(true);
                dlgGestArticulosMovil.txtPrecioPromocion.setEnabled(true);
                dlgGestArticulosMovil.txtPorcPromocion.setEnabled(true);
            } else {
                dlgGestArticulosMovil.ckPromocion.setSelected(false);
                dlgGestArticulosMovil.txtCantPromocion.setEnabled(false);
                dlgGestArticulosMovil.txtPrecioPromocion.setEnabled(false);
                dlgGestArticulosMovil.txtPorcPromocion.setEnabled(false);
            }

            dlgGestArticulosMovil.txtCantPromocion.setText(String.valueOf(ar.getCant_prom()).trim().replace(".0", "").replace(",", ""));
            dlgGestArticulosMovil.txtPrecioPromocion.setText(String.valueOf(df.format(ar.getPrecio_prom())));
            dlgGestArticulosMovil.txtPorcPromocion.setText(ar.getPorc_prom());

            dlgGestArticulosMovil.txtGananciaMinorista.setText(String.valueOf(df.format(ar.getGananciaminorista())));
            dlgGestArticulosMovil.txtPrecioVentaMinorista.setText(String.valueOf(df.format(ar.getPreciominorista())));

        } catch (NumberFormatException ee) {
            System.out.println("Error: " + ee.getMessage());
        }

    }

    public static ArticuloMovil capturarCampos() {
        ArticuloMovil art;
        int codA = Integer.parseInt(dlgGestArticulosMovil.txtCodProducto.getText());
        String codInt;
        String dependencia = null;
        int iddependencia = 0;
        if (dlgGestArticulosMovil.ckDependencia.isSelected()) {
            dependencia = "S";
            iddependencia = Integer.parseInt(dlgGestArticulosMovil.txtidDependencia.getText().trim());
        } else if (!dlgGestArticulosMovil.ckDependencia.isSelected()) {
            dependencia = "N";
            iddependencia = 0;
        }
        if (dlgGestArticulosMovil.txtCodInterno.getText().trim().isEmpty()) {
            codInt = (dlgGestArticulosMovil.txtCodProducto.getText());
        } else {
            codInt = (dlgGestArticulosMovil.txtCodInterno.getText().trim().toUpperCase());
        }
        String codBar;
        if (dlgGestArticulosMovil.txtCodBarra.getText().trim().isEmpty()) {
            codBar = "SIN CODIGO";
        } else {
            codBar = (dlgGestArticulosMovil.txtCodBarra.getText().trim().toUpperCase());
        }

        String descripcion = dlgGestArticulosMovil.txtDescripcion.getText().toUpperCase();

        String sql = "SELECT * FROM division WHERE descripcion='" + dlgGestArticulosMovil.cboClasificacion.getSelectedItem().toString() + "'";
        try (Connection cn = dss.getDataSource().getConnection(); Statement st = cn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            rs.last();
            codClasificacion = rs.getInt("iddivision");
            rs.close();
            st.close();
            cn.close();
        } catch (SQLException ex) {
            Mensajes.error("Error al querer obtener valor de la clasificación: " + ex.getMessage());
        }

        String sql1 = "SELECT * FROM unidad_medida WHERE unidadmedida='" + dlgGestArticulosMovil.cboUM.getSelectedItem().toString() + "'";
        try (Connection cn = dss.getDataSource().getConnection(); Statement st = cn.createStatement(); ResultSet rs = st.executeQuery(sql1)) {
            rs.last();
            codUM = rs.getInt("idunidad");
            rs.close();
            st.close();
            cn.close();
        } catch (SQLException ex) {
            Mensajes.error("Error al querer obtener valor de la Unidad de medida: " + ex.getMessage());
        }

        String sql2 = "SELECT * FROM iva WHERE descripcion='" + dlgGestArticulosMovil.cboImpuesto.getSelectedItem().toString() + "'";
        try (Connection cn = dss.getDataSource().getConnection(); Statement st = cn.createStatement(); ResultSet rs = st.executeQuery(sql2)) {
            rs.last();
            codImpuesto = rs.getInt("idiva");
            rs.close();
            st.close();
            cn.close();
        } catch (SQLException ex) {
            Mensajes.error("Error al querer obtener valor del impuesto: " + ex.getMessage());
        }

        int Pcosto = Integer.parseInt(dlgGestArticulosMovil.txtPrecioCostoL.getText().trim());
        int Ganancia = Integer.parseInt(dlgGestArticulosMovil.txtGanancia.getText().trim().replace(".", "").replace(",", ""));
        int Pventa = Integer.parseInt(dlgGestArticulosMovil.txtPrecioVentaL.getText().trim());
        double stock = Double.parseDouble(dlgGestArticulosMovil.txtStock.getText().trim());
        String ventam;
        if (dlgGestArticulosMovil.ckHabilitar.isSelected()) {
            ventam = "SI";
        } else {
            ventam = "NO";
        }
        double cantM = Double.parseDouble(dlgGestArticulosMovil.txtCantM.getText().trim().replace(".", "").replace(",", ""));
        int GananciaM = Integer.parseInt(dlgGestArticulosMovil.txtGananciaMinorista.getText().trim().replace(".", "").replace(",", ""));
        int PventaM = Integer.parseInt(dlgGestArticulosMovil.txtPrecioVentaMinorista.getText().trim().replace(".", "").replace(",", ""));

        String Prom;
        if (dlgGestArticulosMovil.ckPromocion.isSelected()) {
            Prom = "S";
        } else {
            Prom = "N";
        }
        double Cant_Prom = Double.parseDouble(dlgGestArticulosMovil.txtCantPromocion.getText());
        int Precio_Prom = Integer.parseInt(dlgGestArticulosMovil.txtPrecioPromocion.getText().replace(",", "").replace(".", ""));
        String Porc_Prom = dlgGestArticulosMovil.txtPorcPromocion.getText();
        String User = Login.getUsuarioLogueado();
        art = new ArticuloMovil(codA, dependencia, iddependencia, codInt, codBar, descripcion, Pcosto, Ganancia, Pventa, stock, codUM, codClasificacion, codImpuesto, ventam, cantM, PventaM, GananciaM, Prom, Cant_Prom, Precio_Prom, Porc_Prom, User);
        return art;
    }

    public static ArticuloMovil capturarCampos1() {
        ArticuloMovil art;
        int codA = Integer.parseInt(dlgGestArticulosMovil11.txtCodProducto.getText());
        String codInt;
        String dependencia = null;
        int iddependencia = 0;
        if (dlgGestArticulosMovil11.ckDependencia.isSelected()) {
            dependencia = "S";
            iddependencia = Integer.parseInt(dlgGestArticulosMovil11.txtidDependencia.getText().trim());
        } else if (!dlgGestArticulosMovil11.ckDependencia.isSelected()) {
            dependencia = "N";
            iddependencia = 0;
        }
        if (dlgGestArticulosMovil11.txtCodInterno.getText().trim().isEmpty()) {
            codInt = (dlgGestArticulosMovil11.txtCodProducto.getText());
        } else {
            codInt = (dlgGestArticulosMovil11.txtCodInterno.getText().trim().toUpperCase());
        }
        String codBar;
        if (dlgGestArticulosMovil11.txtCodBarra.getText().trim().isEmpty()) {
            codBar = "SIN CODIGO";
        } else {
            codBar = (dlgGestArticulosMovil11.txtCodBarra.getText().trim().toUpperCase());
        }

        String descripcion = dlgGestArticulosMovil11.txtDescripcion.getText().toUpperCase();

        String sql = "SELECT * FROM division WHERE descripcion='" + dlgGestArticulosMovil.cboClasificacion.getSelectedItem().toString() + "'";
        try (Connection cn = dss.getDataSource().getConnection(); Statement st = cn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            rs.last();
            codClasificacion = rs.getInt("iddivision");
            rs.close();
            st.close();
            cn.close();
        } catch (SQLException ex) {
            Mensajes.error("Error al querer obtener valor de la clasificación: " + ex.getMessage());
        }

        String sql1 = "SELECT * FROM unidad_medida WHERE unidadmedida='" + dlgGestArticulosMovil.cboUM.getSelectedItem().toString() + "'";
        try (Connection cn = dss.getDataSource().getConnection(); Statement st = cn.createStatement(); ResultSet rs = st.executeQuery(sql1)) {
            rs.last();
            codUM = rs.getInt("idunidad");
            rs.close();
            st.close();
            cn.close();
        } catch (SQLException ex) {
            Mensajes.error("Error al querer obtener valor de la Unidad de medida: " + ex.getMessage());
        }

        String sql2 = "SELECT * FROM iva WHERE descripcion='" + dlgGestArticulosMovil.cboImpuesto.getSelectedItem().toString() + "'";
        try (Connection cn = dss.getDataSource().getConnection(); Statement st = cn.createStatement(); ResultSet rs = st.executeQuery(sql2)) {
            rs.last();
            codImpuesto = rs.getInt("idiva");
            rs.close();
            st.close();
            cn.close();
        } catch (SQLException ex) {
            Mensajes.error("Error al querer obtener valor del impuesto: " + ex.getMessage());
        }

        int Pcosto = Integer.parseInt(dlgGestArticulosMovil11.txtPrecioCostoL.getText().trim());
        int Ganancia = Integer.parseInt(dlgGestArticulosMovil11.txtGanancia.getText().trim().replace(".", "").replace(",", ""));
        int Pventa = Integer.parseInt(dlgGestArticulosMovil11.txtPrecioVentaL.getText().trim());
        double stock = Double.parseDouble(dlgGestArticulosMovil11.txtStock.getText().trim());
        String ventam;
        if (dlgGestArticulosMovil11.ckHabilitar.isSelected()) {
            ventam = "SI";
        } else {
            ventam = "NO";
        }
        double cantM = Double.parseDouble(dlgGestArticulosMovil11.txtCantM.getText().trim().replace(".", "").replace(",", ""));
        int GananciaM = Integer.parseInt(dlgGestArticulosMovil11.txtGananciaMinorista.getText().trim().replace(".", "").replace(",", ""));
        int PventaM = Integer.parseInt(dlgGestArticulosMovil11.txtPrecioVentaMinorista.getText().trim().replace(".", "").replace(",", ""));

        String Prom;
        if (dlgGestArticulosMovil11.ckPromocion.isSelected()) {
            Prom = "S";
        } else {
            Prom = "N";
        }
        double Cant_Prom = Double.parseDouble(dlgGestArticulosMovil11.txtCantPromocion.getText());
        int Precio_Prom = Integer.parseInt(dlgGestArticulosMovil11.txtPrecioPromocion.getText().replace(",", "").replace(".", ""));
        String Porc_Prom = dlgGestArticulosMovil11.txtPorcPromocion.getText();
        String User = Login.getUsuarioLogueado();
        art = new ArticuloMovil(codA, dependencia, iddependencia, codInt, codBar, descripcion, Pcosto, Ganancia, Pventa, stock, codUM, codClasificacion, codImpuesto, ventam, cantM, PventaM, GananciaM, Prom, Cant_Prom, Precio_Prom, Porc_Prom, User);
        return art;
    }

    public static String addArticulo() {
        String msg;
        ArticuloMovil art = capturarCampos();
        msg = GestionarArticulosMovil.addArticulo(art);
        if (msg == null) {
            Mensajes.informacion("Artículo Registrado");
        } else {
            Mensajes.error(msg);
        }
        return msg;
    }

    public static String addArticulo1() {
        String msg;
        ArticuloMovil art = capturarCampos1();
        msg = GestionarArticulosMovil.addArticulo(art);
        if (msg == null) {
           // Mensajes.informacion("Artículo Registrado");
            Notif.NotifySuccess("Notificación del sistema", "Artículo Registrado");
        } else {
            Notif.NotifyError("Notificación del sistema", "Error agregando producto: "+msg);
            //Mensajes.error(msg);
        }
        return msg;
    }

    public static String actArticulo() {
        String msg;
        String msgg;
        ArticuloMovil art = capturarCampos();
        msg = GestionarArticulosMovil.actArticulo(art);
        if (msg == null) {
            //Mensajes.informacion("Artículo Modifcado");
            Notif.NotifySuccess("Notificación del sistema", "Artículo Modifcado");
           
        } else {
            Notif.NotifyError("Notificación del sistema", "Error actualizando producto: "+msg);
            //Mensajes.error(msg);
        }
        
        msgg = GestionarArticulosMovil.actArticuloDependencia(art);
        if (msgg == null) {
             Notif.NotifyInformation("Notificación del sistema", "DEPENDENCIAS APLICADAS SATISFACTORIAMENTE!\r\nSi existen Acticulos que dependen directamente de este item, las mismas seras actualizadas automáticamente.");
            //Mensajes.Sistema("DEPENDENCIAS APLICADAS SATISFACTORIAMENTE!\nSi existen Acticulos que dependen directamente de este item, las mismas seras actualizadas automáticamente.");
        } else {
            Notif.NotifyError("Notificación del sistema","Error actualizando dependencias: "+msgg);
            //Mensajes.error(msgg);
        }
        return msg;
    }

    public static ArticuloMovil busArticulo(String cod) {
        ArticuloMovil art;
        art = GestionarArticulosMovil.busArticulo(cod);
        return art;
    }

    public static String delArticulo() {
        int x = dlgArticulosMovil.tbProductos.getSelectedRow();
        String msg;
        String cod = dlgArticulosMovil.tbProductos.getValueAt(x, 0).toString();
        String User = Login.getUsuarioLogueado();
        msg = GestionarArticulosMovil.delArticulo(cod, User);
        if (msg == null) {
            Notif.NotifySuccess("Notificación del sistema", "Artículo Eliminado");
            //Mensajes.informacion("Artículo Eliminado");
        } else {
            Notif.NotifyError("Notificación del sistema", "Error eliminando producto: "+msg);
            //Mensajes.error(msg);
        }
        return msg;
    }

    public static void listArticulo(JTable tabla, String cod) {
        List lista;
        lista = GestionarArticulosMovil.listArticulo(cod);
        for (int i = 1; i < lista.size(); i++) {
            DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
            Object[] fila = (Object[]) lista.get(i);
            fila[0].toString();
            if (fila[1].toString().equals("N")) {
                fila[1] = "INHABILITADO";
            } else if (fila[1].toString().equals("S")) {
                fila[1] = "HABILITADO";
            }
            fila[2].toString();
            fila[3].toString();
            fila[4].toString();
            fila[5].toString();
            fila[6].toString();
            fila[7].toString();
            fila[8].toString();
            fila[9].toString();
            fila[10].toString();
            fila[11].toString();
            fila[12].toString();
            fila[13].toString();
            fila[14].toString();
            if (fila[15].toString().equals("N")) {
                fila[15] = "NO";
            } else if (fila[15].toString().equals("S")) {
                fila[15] = "SI";
            }
            tb.addRow(fila);
        }
    }

    public static void listArticulo2(JTable tabla, String cod) {
        List lista;
        lista = GestionarArticulosMovil.listArticulo2(cod);
        for (int i = 1; i < lista.size(); i++) {
            DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
            Object[] fila = (Object[]) lista.get(i);
            fila[0].toString();
            if (fila[1].toString().equals("N")) {
                fila[1] = "INHABILITADO";
            } else if (fila[1].toString().equals("S")) {
                fila[1] = "HABILITADO";
            }
            fila[2].toString();
            fila[3].toString();
            fila[4].toString();
            fila[5].toString();
            fila[6].toString();
            fila[7].toString();
            fila[8].toString();
            fila[9].toString();
            fila[10].toString();
            fila[11].toString();
            if (fila[12].toString().equals("N")) {
                fila[12] = "NO";
            } else if (fila[12].toString().equals("S")) {
                fila[12] = "SI";
            }
            tb.addRow(fila);
        }
    }

    public static void listArticuloconStock(JTable tabla, String cod) {
        List lista;
        lista = GestionarArticulosMovil.listArticuloconStock(cod);
        for (int i = 1; i < lista.size(); i++) {
            DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
            Object[] fila = (Object[]) lista.get(i);
            fila[0].toString();
            if (fila[1].toString().equals("N")) {
                fila[1] = "INHABILITADO";
            } else if (fila[1].toString().equals("S")) {
                fila[1] = "HABILITADO";
            }
            fila[2].toString();
            fila[3].toString();
            fila[4].toString();
            fila[5].toString();
            fila[6].toString();
            fila[7].toString();
            fila[8].toString();
            fila[9].toString();
            fila[10].toString();
            fila[11].toString();
            fila[12].toString();
            fila[13].toString();
            fila[14].toString();
            if (fila[15].toString().equals("N")) {
                fila[15] = "NO";
            } else if (fila[15].toString().equals("S")) {
                fila[15] = "SI";
            }
            tb.addRow(fila);
        }
    }

    /*   public static void listArticuloActivo(JTable tabla, String cod) {
        List lista;
        lista = GestionarArticulos.listArticuloActivo(cod);
        for (int i = 1; i < lista.size(); i++) {
            DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
            Object[] fila = (Object[]) lista.get(i);
            tb.addRow(fila);
        }
    }*/
    public static void filtrarGral(JTable tabla, String cod) {
        String C = cod;
        List lista1;
        lista1 = GestionarArticulosMovil.filtrarGral(cod);
        if (lista1 != null) {
            for (int i = 1; i < lista1.size(); i++) {
                DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
                Object[] fila = (Object[]) lista1.get(i);
                fila[0].toString();
                if (fila[1].toString().equals("N")) {
                    fila[1] = "INHABILITADO";
                } else if (fila[1].toString().equals("S")) {
                    fila[1] = "HABILITADO";
                }
                fila[2].toString();
                fila[3].toString();
                fila[4].toString();
                fila[5].toString();
                fila[6].toString();
                fila[7].toString();
                fila[8].toString();
                fila[9].toString();
                fila[10].toString();
                fila[11].toString();
                fila[12].toString();
                fila[13].toString();
                fila[14].toString();
                if (fila[15].toString().equals("N")) {
                    fila[15] = "NO";
                } else if (fila[15].toString().equals("S")) {
                    fila[15] = "SI";
                }
                tb.addRow(fila);
            }
        }
    }

    public static void filtrarGral2(JTable tabla, String cod) {
        String C = cod;
        List lista1;
        lista1 = GestionarArticulosMovil.filtrarGral2(cod);
        if (lista1 != null) {
            for (int i = 1; i < lista1.size(); i++) {
                DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
                Object[] fila = (Object[]) lista1.get(i);
                fila[0].toString();
                if (fila[1].toString().equals("N")) {
                    fila[1] = "INHABILITADO";
                } else if (fila[1].toString().equals("S")) {
                    fila[1] = "HABILITADO";
                }
                fila[2].toString();
                fila[3].toString();
                fila[4].toString();
                fila[5].toString();
                fila[6].toString();
                fila[7].toString();
                fila[8].toString();
                fila[9].toString();
                fila[10].toString();
                fila[11].toString();
                if (fila[12].toString().equals("N")) {
                    fila[12] = "NO";
                } else if (fila[12].toString().equals("S")) {
                    fila[12] = "SI";
                }
                tb.addRow(fila);
            }
        }
    }

    /*public static void filtrarDescripcion(JTable tabla, String cod) {
        List lista = null;
        lista = GestionarArticulos.filtrarDescripcion(cod);
        for (int i = 1; i < lista.size(); i++) {
            DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
            Object[] fila = (Object[]) lista.get(i);
            tb.addRow(fila);
        }
    }*/
 /*public static void filtrarDescripcionActivo(JTable tabla, String cod) {
        List lista = null;
        lista = GestionarArticulos.filtrarDescripcionActivo(cod);
        for (int i = 1; i < lista.size(); i++) {
            DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
            Object[] fila = (Object[]) lista.get(i);
            //fila[0].toString();
            //fila[1].toString();
            tb.addRow(fila);
        }
    }*/

 /*    public static void filrarPrincipio(JTable tabla, String pr) {
        List lista = null;
        lista = GestionarArticulos.filtrarPrincipio(pr);
        for (int i = 1; i < lista.size(); i++) {
            DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
            Object[] fila = (Object[]) lista.get(i);
            //fila[0].toString();
            //fila[1].toString();
            tb.addRow(fila);
        }
    }*/
 /* public static void filrarPrincipioActivo(JTable tabla, String pr) {
        List lista = null;
        lista = GestionarArticulos.filtrarPrincipioActivo(pr);
        for (int i = 1; i < lista.size(); i++) {
            DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
            Object[] fila = (Object[]) lista.get(i);
            //fila[0].toString();
            //fila[1].toString();
            tb.addRow(fila);
        }
    }*/

 /*public static void filtrarCodBarra(JTable tabla, String rb) {
        List lista = null;
        lista = GestionarArticulos.filtrarCodBarra(rb);
        for (int i = 1; i < lista.size(); i++) {
            DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
            Object[] fila = (Object[]) lista.get(i);
            //fila[0].toString();
            //fila[1].toString();
            tb.addRow(fila);
        }
    }*/
    public static void filtrarCodBarraActivo(JTable tabla, String rb) {
        List lista = null;
        lista = GestionarArticulosMovil.filtrarCodBarraActivo(rb);
        for (int i = 1; i < lista.size(); i++) {
            DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
            Object[] fila = (Object[]) lista.get(i);
            tb.addRow(fila);
        }
    }

    public static void filtrarArticulosMovActivoAuxReparto(JTable tabla, String rb) {
        List lista = null;
        lista = GestionarArticulosMovil.filtrarArticulosActivoAuxiliarReparto(rb);
        for (int i = 1; i < lista.size(); i++) {
            DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
            Object[] fila = (Object[]) lista.get(i);
            //fila[0].toString();
            //fila[1].toString();
            tb.addRow(fila);
        }
    }

    public static void listAuditoriaProductos(JTable tabla, String idproducto, String fecha1, String fecha2)//Lista las facturas realizadas
    {
        List lista;
        lista = GestionarArticulosMovil.listAuditoriaProductos(idproducto, fecha1, fecha2);
        for (int i = 1; i < lista.size(); i++) {
            DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
            Object[] fila = (Object[]) lista.get(i);
            fila[0] = fila[0].toString();
            if(fila[1] == null){
                fila[1]=0;
            }else{
                fila[1] = fila[1].toString();
            }
            //fila[1] = fila[1].toString();
            fila[2] = fila[2].toString();
            fila[3] = String.valueOf(Double.parseDouble(fila[2].toString()) - Double.parseDouble(fila[1].toString()));
            fila[4] = Fecha.formatoFechaFFHH(fila[4].toString());
            fila[5] = fila[5].toString();
            tb.addRow(fila);
        }
    }
}
