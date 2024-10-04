package Controladores;

import Componentes.Config;
import Componentes.DataSourceService;
import Componentes.DataSourceService1;
import Componentes.Empresa;
import Componentes.Fecha;
import Componentes.Login;
import Componentes.Mensajes;
import Componentes.Notif;
import Componentes.Tickets;
import Componentes.Timbrado;
import Componentes.traerIP;
import Datos.Logeo;
import IU.frmAcceso;
import IU.frmPrincipal;
import Modelo.Usuario;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ControlLogeo {
    static Usuario u;
    static String user;
    static String pass;
    public static String UsuarioL = "";
    static DataSourceService dss = new DataSourceService();
    static DataSourceService1 dss1 = new DataSourceService1();

    public static String logear() {
        user = frmAcceso.txtUsuario.getText().trim();
        pass = String.valueOf(frmAcceso.psPasword.getPassword());
        u = Logeo.logear(user, pass);

        if (u.getPefil().equalsIgnoreCase("ADMINISTRADOR")) {
            Logeo.acceso(u);
            abrirPrincipal();
            frmPrincipal.lblUsuario.setText(u.getNomUsuario());
            frmPrincipal.lbUsuario.setText(u.getUsuario());
            Login.setUsuarioLogueado(u.getUsuario());
            Login.setIDUsuarioLogueado(String.valueOf(u.getCodUsuario()));
            Login.setContraLogueado(u.getPassword());
            Login.setPerfil(u.getPefil());
            frmPrincipal.lbPerfil.setText(u.getPefil());
        } else if (u.getPefil().equalsIgnoreCase("VENTA")) {
            Logeo.acceso(u);
            abrirPrincipal();
            frmPrincipal.lblUsuario.setText(u.getNomUsuario());
            frmPrincipal.lbUsuario.setText(u.getUsuario());
            Login.setUsuarioLogueado(u.getUsuario());
            Login.setIDUsuarioLogueado(String.valueOf(u.getCodUsuario()));
            Login.setContraLogueado(u.getPassword());
            Login.setPerfil(u.getPefil());
            frmPrincipal.lbPerfil.setText(u.getPefil());
        } else if (u.getPefil().equalsIgnoreCase("COMPRA")) {
            Logeo.acceso(u);
            abrirPrincipal();
            frmPrincipal.lblUsuario.setText(u.getNomUsuario());
            frmPrincipal.lbUsuario.setText(u.getUsuario());
            Login.setUsuarioLogueado(u.getUsuario());
            Login.setIDUsuarioLogueado(String.valueOf(u.getCodUsuario()));
            Login.setContraLogueado(u.getPassword());
            Login.setPerfil(u.getPefil());
            frmPrincipal.lbPerfil.setText(u.getPefil());
        } else if (u.getPefil().equalsIgnoreCase("ALMACEN")) {
            Logeo.acceso(u);
            abrirPrincipal();
            frmPrincipal.lblUsuario.setText(u.getNomUsuario());
            frmPrincipal.lbUsuario.setText(u.getUsuario());
            Login.setUsuarioLogueado(u.getUsuario());
            Login.setIDUsuarioLogueado(String.valueOf(u.getCodUsuario()));
            Login.setContraLogueado(u.getPassword());
            Login.setPerfil(u.getPefil());
            frmPrincipal.lbPerfil.setText(u.getPefil());
        } else if (u.getPefil().equalsIgnoreCase("DESARROLLADOR")) {
            Logeo.acceso(u);
            abrirPrincipal();
            frmPrincipal.lblUsuario.setText(u.getNomUsuario());
            frmPrincipal.lbUsuario.setText(u.getUsuario());
            Login.setUsuarioLogueado(u.getUsuario());
            Login.setIDUsuarioLogueado(String.valueOf(u.getCodUsuario()));
            Login.setContraLogueado(u.getPassword());
            Login.setPerfil(u.getPefil());
            frmPrincipal.lbPerfil.setText(u.getPefil());
        }
        return String.valueOf(u.getNomUsuario());
    }

    public static void Timbrado_Ticket() {
        try {
            String sql = "SELECT * FROM v_puntoemision4 WHERE ip='" + Config.getIPSoft() + "' AND tipo='L' AND tipo2='F' AND estado='Activo'";
            try (Connection cn = dss1.getDataSource().getConnection(); Statement st = cn.createStatement(); ResultSet rs = st.executeQuery(sql);) {
                rs.last();
                if (rs.getRow() != 0) {
                    Timbrado.setIdEmision(rs.getInt(1));
                    System.out.println("ID EMISION FACTURA LEGAL: " + Timbrado.getIdEmision());
                    Timbrado.setIdTimbrado(rs.getInt(2));
                    System.out.println("ID TIMBRADO: " + Timbrado.getIdTimbrado());
                    Timbrado.setTimbrado(rs.getString(3));
                    System.out.println("TIMBRADO: " + Timbrado.getTimbrado());
                    Timbrado.setDesde(rs.getString(18));
                    System.out.println("VALIDEZ DESDE: " + Timbrado.getDesde());
                    Timbrado.setHasta(rs.getString(19));
                    System.out.println("VALIDEZ HASTA: " + Timbrado.getHasta());
                    Timbrado.setEstablecimiento(rs.getString(4));
                    System.out.println("ESTABLECIMIENTO: " + Timbrado.getEstablecimiento());
                    Timbrado.setPuntoExpedicion(rs.getString(5));
                    System.out.println("PUNTO DE EXPEDICIÓN: " + Timbrado.getPuntoExpedicion());
                    Timbrado.setFacturaFin(rs.getInt(8));
                    System.out.println("FACTURAR HASTA NRO.: " + Timbrado.getFacturaFin());
                    Timbrado.setIdBoca(rs.getInt(15));
                    System.out.println("ID BOCA: " + Timbrado.getIdBoca());
                    Timbrado.setImpresora(rs.getString(17));
                    System.out.println("IMPRESORA: " + Timbrado.getImpresora());
                    Timbrado.setHabilitado("SI");
                    System.out.println("FACTURA LEGAL HABILITADO: " + Timbrado.getHabilitado());

                    try {
                        SimpleDateFormat fe = new SimpleDateFormat("dd/MM/yyyy");
                        Date FechaA = fe.parse(Fecha.fechaFormulario());
                        Date FechaT = fe.parse(Timbrado.getHasta());
                        if (FechaA.equals(FechaT)) {
                            Timbrado.setValidado("SI");
                            System.out.println("TIMBRADO VALIDADO: " + Timbrado.getValidado());
                        } else if (FechaA.after(FechaT)) {
                            Timbrado.setValidado("NO");
                            System.out.println("TIMBRADO VALIDADO: " + Timbrado.getValidado());
                            Notif.NotifyWarning("Notificación del sistema", "EMISIÓN DE FACTURA LEGAL NO HABILITADO:\nEl Timbrado actual ha expirado.\nPara retomar las facturaciones legales sera necesario configurar un nuevo Timbrado.");
                            //Mensajes.Sistema("EMISIÓN DE FACTURA LEGAL NO HABILITADO:\nEl Timbrado actual ha expirado.\nPara retomar las facturaciones legales sera necesario configurar un nuevo Timbrado.");
                        } else if (FechaA.before(FechaT)) {
                            Timbrado.setValidado("SI");
                            System.out.println("TIMBRADO VALIDADO: " + Timbrado.getValidado());
                        }
                    } catch (ParseException es) {
                        System.out.println("Error comparando validez de timbrado: " + es.getMessage());
                    }
                    System.out.println("-------------------------------------------------------");

                } else {
                    //Mensajes.Sistema("EMISIÓN DE FACTURA LEGAL NO HABILITADO:\nNo se encuentra Punto de expedición para la emisión de facturas legales.");
                    Notif.NotifyFail("Notificación del sistema", "EMISIÓN DE FACTURA LEGAL NO HABILITADO:\nNo se encuentra Punto de expedición para la emisión de facturas legales.");
                    Timbrado.setHabilitado("NO");
                    System.out.println("FACTURA LEGAL HABILITADO: " + Timbrado.getHabilitado());
                    Timbrado.setValidado("NO");
                    System.out.println("TIMBRADO VALIDADO: " + Timbrado.getValidado());
                    System.out.println("-------------------------------------------------------");

                }
                rs.close();
                st.close();
                cn.close();
            }
        } catch (SQLException e) {
            System.out.println("Error obteniendo Timbrado: " + e.getMessage());
        }

        try {
            String sql = "SELECT * FROM v_puntoemision4 WHERE ip='" + Config.getIPSoft() + "' AND tipo='L' AND tipo2='T' AND estado='Activo'";
            try (Connection cn = dss1.getDataSource().getConnection(); Statement st = cn.createStatement(); ResultSet rs = st.executeQuery(sql);) {
                rs.last();
                if (rs.getRow() != 0) {
                    Tickets.setIdEmision(rs.getInt(1));
                    System.out.println("ID EMISION TICKET: " + Tickets.getIdEmision());
                    Tickets.setEstablecimiento(rs.getString(4));
                    System.out.println("ESTABLECIMIENTO: " + Tickets.getEstablecimiento());
                    Tickets.setPuntoExpedicion(rs.getString(5));
                    System.out.println("PUNTO DE EXPEDICIÓN: " + Tickets.getPuntoExpedicion());
                    Tickets.setImpresora(rs.getString(17));
                    System.out.println("IMPRESORA: " + Tickets.getImpresora());
                    Tickets.setHabilitado("SI");
                    System.out.println("TICKET HABILITADO: " + Tickets.getHabilitado());
                    System.out.println("-------------------------------------------------------");
                } else {
                    Notif.NotifyFail("Notificación del sistema", "EMISIÓN DE TICKET NO HABILITADO:\nNo se encuentra un Punto de expedición para emisión de tickets.");
                    //Mensajes.Sistema("EMISIÓN DE TICKET NO HABILITADO:\nNo se encuentra un Punto de expedición para emisión de tickets.");
                    Tickets.setHabilitado("NO");
                    System.out.println("TICKET HABILITADO: " + Tickets.getHabilitado());
                    System.out.println("-------------------------------------------------------");
                }
                rs.close();
                st.close();
                cn.close();
            }
        } catch (SQLException e) {
            System.out.println("Error obteniendo Ticket: " + e.getMessage());
        }
    }

    public static void Empresa() {
        try {
            String sql = "select * from v_sucursal where suc_indicador='S'";
            try (Connection cn = dss.getDataSource().getConnection(); Statement st = cn.createStatement(); ResultSet rs = st.executeQuery(sql);) {
                rs.last();
                if (rs.getRow() != 0) {
                    Empresa.setEmpresa(rs.getString(3));
                    System.out.println("NOMBRE FANTASIA: " + Empresa.getEmpresa());
                    Empresa.setRazonSocial(rs.getString(3));
                    System.out.println("RAZON SOCIAL: " + Empresa.getRazonSocial());
                    Empresa.setRUC(rs.getString(4));
                    System.out.println("RUC: " + Empresa.getRUC());
                    Empresa.setSucursal(rs.getString(5));
                    System.out.println("RUC: " + Empresa.getSucursal());
                    Empresa.setCelular(rs.getString(8));
                    System.out.println("CELULAR: " + Empresa.getCelular());
                    Empresa.setDireccion(rs.getString(9));
                    System.out.println("DIRECCION: " + Empresa.getDireccion());
                    Empresa.setHabilitado("SI");
                    System.out.println("EMPRESA HABILITADA: " + Empresa.getHabilitado());
                    System.out.println("-------------------------------------------------------");
                } else {
                    System.out.println("EMPRESA HABILITADO: " + Empresa.getHabilitado());
                    System.out.println("-------------------------------------------------------");
                }
                rs.close();
                st.close();
                cn.close();
            }
        } catch (SQLException e) {
            System.out.println("Error obteniendo Empresa: " + e.getMessage());
        }
    }

    public static String perfil() {
        return String.valueOf(u.getPefil());
    }

    public static String desLogeo() {
        String msg = null;
        msg = Logeo.salida(u);
        if (msg == null) {
            System.out.println("Se inserto Salida");
        } else {
            System.out.println("No se inserto salida");
        }
        return msg;
    }

    public static void abrirPrincipal() {
        frmPrincipal p = new frmPrincipal();
        p.setExtendedState(frmPrincipal.MAXIMIZED_BOTH);
        p.setVisible(true);
    }

}
