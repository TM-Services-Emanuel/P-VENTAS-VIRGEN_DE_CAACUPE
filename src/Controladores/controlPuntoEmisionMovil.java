package Controladores;

import Componentes.ConexionBD;
import Componentes.Mensajes;
import Componentes.cargarComboBoxMovil;
import Datos.GestionarPuntoEmisionMovil;
import IU.dlgPuntoEmisionMovil;
import Modelo.PuntoEmisionMovil;
import Modelo.refMovil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.mariadb.jdbc.MariaDbConnection;
import org.mariadb.jdbc.MariaDbStatement;

public class controlPuntoEmisionMovil {
    
    public static ResultSet rs;
    public static MariaDbStatement sentencia;
    public static MariaDbConnection con;
    
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
        }
    }
    
    
    public static String addPuntoEmision()
    {
        String msg;
        int cod = Integer.parseInt(dlgPuntoEmisionMovil.txtCod.getText().trim());
        int idTimbrado = Integer.parseInt(cargarComboBoxMovil.getCodidgo(dlgPuntoEmisionMovil.cboTimbrado));
        String establecimiento = dlgPuntoEmisionMovil.txtEstablecimiento.getText().trim();
        String puntoEmision = dlgPuntoEmisionMovil.txtEmision.getText().trim();
        String direccion = dlgPuntoEmisionMovil.txtDireccion.getText().trim();
        int facturainicio= Integer.parseInt(dlgPuntoEmisionMovil.txtFInicio.getText().trim());
        int facturafin= Integer.parseInt(dlgPuntoEmisionMovil.txtFFin.getText().trim());
        int facturaactual= Integer.parseInt(dlgPuntoEmisionMovil.txtFActual.getText().trim());
        String tipo;
        String ip;
        String tipo2 = null;
        if(dlgPuntoEmisionMovil.cbAMovil.isSelected()){
            tipo="M";
            ip="0.0.0.0";
        }else{
            tipo="L";
            ip=dlgPuntoEmisionMovil.txtIP.getText().trim();
        }
        if(dlgPuntoEmisionMovil.cboTipo2.getSelectedIndex()== 0){
            tipo2="T";
        }else if(dlgPuntoEmisionMovil.cboTipo2.getSelectedIndex()== 1){
            tipo2="F";
        }
        String estado;
        if(dlgPuntoEmisionMovil.rbActivo.isSelected()){
            estado="Activo";
        }else{
            estado="Inactivo";
        }
        int idBoca = 0;
        try {
            prepararBD();
            String boca = dlgPuntoEmisionMovil.cbBoca_de_cobranza.getSelectedItem().toString();
            try {
                rs = sentencia.executeQuery("SELECT * FROM laboratorio WHERE lab_nombre='" + boca + "'");
                rs.last();
                idBoca = rs.getInt("lab_codigo");
                rs.close();
            } catch (SQLException ex) {
                Mensajes.error("Error al querer obtener valor de la boca de cobranza: " + ex.getMessage());
            }
            con.close();
        } catch (Exception pr) {
        }
        
        String impresora = dlgPuntoEmisionMovil.txtImpresora.getText().trim();
        
        PuntoEmisionMovil pm = new PuntoEmisionMovil(cod, idTimbrado, establecimiento,puntoEmision,direccion, facturainicio, facturafin, facturaactual, tipo, tipo2, ip, estado, idBoca, impresora);
        msg = GestionarPuntoEmisionMovil.addPuntoEmision(pm);
        if(msg==null)
        {
            Mensajes.informacion("Punto de Emisión Registrado");
        }
        else{
            Mensajes.error(msg);
        }
        return msg;
    }
    
    public static String addRef()
    {
        String msg;
        int cod = Integer.parseInt(dlgPuntoEmisionMovil.txtCod.getText().trim());
        int nventa= Integer.parseInt(dlgPuntoEmisionMovil.txtNVenta.getText().trim());        
        refMovil rf = new refMovil(cod,nventa);
        msg = GestionarPuntoEmisionMovil.addRef(rf);
        if(msg==null)
        {
            Mensajes.informacion("Referencia Registrado");
        }
        else{
            Mensajes.error(msg);
        }
        return msg;
    }
    
    public static String actPuntoEmision()
    {
        String msg;
        int cod = Integer.parseInt(dlgPuntoEmisionMovil.txtCod.getText().trim());
        String establecimiento = dlgPuntoEmisionMovil.txtEstablecimiento.getText().trim();
        String puntoEmision = dlgPuntoEmisionMovil.txtEmision.getText().trim();
        String direccion = dlgPuntoEmisionMovil.txtDireccion.getText().trim();
        int facturainicio= Integer.parseInt(dlgPuntoEmisionMovil.txtFInicio.getText().trim());
        int facturafin= Integer.parseInt(dlgPuntoEmisionMovil.txtFFin.getText().trim());
        int facturaactual= Integer.parseInt(dlgPuntoEmisionMovil.txtFActual.getText().trim());
        String tipo;
        String ip;
        String tipo2 = null;
        if(dlgPuntoEmisionMovil.cbAMovil.isSelected()){
            tipo="M";
            ip="0.0.0.0";
        }else{
            tipo="L";
            ip=dlgPuntoEmisionMovil.txtIP.getText().trim();
        }
        if(dlgPuntoEmisionMovil.cboTipo2.getSelectedIndex()== 0){
            tipo2="T";
        }else if(dlgPuntoEmisionMovil.cboTipo2.getSelectedIndex()== 1){
            tipo2="F";
        }
        String estado;
        if(dlgPuntoEmisionMovil.rbActivo.isSelected()){
            estado="Activo";
        }else{
            estado="Inactivo";
        }
        int idBoca = 0;
        try {
            prepararBD();
            String boca = dlgPuntoEmisionMovil.cbBoca_de_cobranza.getSelectedItem().toString();
            try {
                rs = sentencia.executeQuery("SELECT * FROM laboratorio WHERE lab_nombre='" + boca + "'");
                rs.last();
                idBoca = rs.getInt("lab_codigo");
                rs.close();
            } catch (SQLException ex) {
                Mensajes.error("Error al querer obtener valor de la boca de cobranza: " + ex.getMessage());
            }
            con.close();
        } catch (Exception pr) {
        }
        
        String impresora = dlgPuntoEmisionMovil.txtImpresora.getText().trim();
        
        PuntoEmisionMovil pm = new PuntoEmisionMovil(cod, establecimiento,puntoEmision,direccion, facturainicio, facturafin, facturaactual, tipo, tipo2, ip, estado, idBoca, impresora);
        msg = GestionarPuntoEmisionMovil.actPuntoEmision(pm);
        if(msg==null)
        {
            Mensajes.informacion("Punto de Emisión Actualizado");
        }
        else{
            Mensajes.error(msg);
        }
        return msg;
    }
    
    public static String actRef()
    {
        String msg;
        int cod = Integer.parseInt(dlgPuntoEmisionMovil.txtCod.getText().trim());
        int nventa= Integer.parseInt(dlgPuntoEmisionMovil.txtNVenta.getText().trim());        
        refMovil rf = new refMovil(cod,nventa);
        msg = GestionarPuntoEmisionMovil.actRef(rf);
        if(msg==null)
        {
            Mensajes.informacion("Referencia Actualizada");
        }
        else{
            Mensajes.error(msg);
        }
        return msg;
    }
    
    public static String delPuntoEmision()    
    {
        String msg;
        int cod = Integer.parseInt(dlgPuntoEmisionMovil.txtCod.getText().trim());
        msg = GestionarPuntoEmisionMovil.delPuntoEmision(cod);
        if(msg==null)
        {
            Mensajes.informacion("Punto de Emisión eliminado");
        }
        else{
            Mensajes.error(msg);
        }
        return msg;
    }
    
    public static void listPuntoEmision(JTable tabla)
    {
        List lista;
        lista = GestionarPuntoEmisionMovil.listPuntoEmision();
        for(int i=1;i<lista.size();i++)
        {
            DefaultTableModel tb = (DefaultTableModel)tabla.getModel();
            Object[]fila = (Object[])lista.get(i);
            tb.addRow(fila);
        }
    }
}