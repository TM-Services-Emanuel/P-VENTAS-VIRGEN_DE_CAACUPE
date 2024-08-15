package Datos;

import Componentes.Fecha;
import Componentes.Operacion;
import Componentes.OperacionMovil;
import Componentes.generarCodigos;
import Modelo.DetalleFactura;
import Modelo.Factura;
import java.util.*;

public class GestionarFactura {

    public static String getCodigo() {
        String cod = generarCodigos.getCodigo("SELECT MAX(fac_codigo) from factura");
        return cod;
    }

    public static String getFactura() {
        String cod = generarCodigos.getCodigo("SELECT MAX(fac_factura) from factura");
        return cod;
    }

    public static String addFactura(Factura f) {
        String msg;
        StringBuilder sql = new StringBuilder("INSERT INTO factura VALUES (");
        sql.append(f.getCodFactura()).append(",");
        sql.append(f.getCodCliente()).append(",");
        sql.append(f.getCodVendedor()).append(",");
        sql.append(f.getDescuento()).append(",'");
        sql.append(f.getTipoPago()).append("','");
        sql.append(f.getFecha()).append("','");
        sql.append(Fecha.darHora()).append("','");
        sql.append(f.getNeto()).append("' ,");
        sql.append(f.getTotal()).append(",'S')");
        msg = Operacion.exeOperacion(sql.toString());
//        msg = Operacion.exeOperacion("INSERT INTO factura VALUES ("
//                + f.getCodFactura() + ","
//                + f.getCodCliente() + ","
//                + f.getCodVendedor() + ","
//                + f.getDescuento() + ",'"
//                + f.getTipoPago() + "','"
//                + f.getFecha() + "','"
//                + Fecha.darHora() + "','"
//                + f.getNeto() + "' ,"
//                + f.getTotal() + ",'S')");
        return msg;
    }

    public static String actFactura(String cod, String usuario) {
        String msg;
        StringBuilder sql = new StringBuilder("UPDATE factura SET fac_indicador='N', usu='" + usuario + "' WHERE fac_codigo=");
        sql.append(cod).append("");
        msg = Operacion.exeOperacion(sql.toString());
//        msg = Operacion.exeOperacion("UPDATE factura SET fac_indicador='N' WHERE fac_codigo=" + cod + "");
        return msg;
    }

    public static String actFacturaMovil(String cod, String idemision) {
        String msg;
        StringBuilder sql = new StringBuilder("UPDATE ventas_1 SET estado='N' WHERE idventas=");
        sql.append(cod).append(" AND idemision=").append(idemision);
        msg = OperacionMovil.exeOperacion(sql.toString());
//        msg = Operacion.exeOperacion("UPDATE factura SET fac_indicador='N' WHERE fac_codigo=" + cod + "");
        return msg;
    }

    public static String actFacturaMovil1(String cod, String idemision) {
        String msg;
        StringBuilder sql = new StringBuilder("UPDATE ventas SET estado='N' WHERE idventas=");
        sql.append(cod).append(" AND idemision=").append(idemision);
        msg = OperacionMovil.exeOperacion(sql.toString());
//        msg = Operacion.exeOperacion("UPDATE factura SET fac_indicador='N' WHERE fac_codigo=" + cod + "");
        return msg;
    }

    public static String addDetalleFactura(DetalleFactura df) {
        String msg;
        StringBuilder sql = new StringBuilder("INSERT INTO detalle_factura VALUES (0,");
        sql.append(df.getCodFactura()).append(",");
        sql.append(df.getCodArticulo()).append(",");
        sql.append(df.getCantidad()).append(",");
        sql.append(df.getPrecio()).append(",");
        sql.append(df.getTotal()).append(")");
        msg = Operacion.exeOperacion(sql.toString());
//        msg = Operacion.exeOperacion("INSERT INTO detalle_factura VALUES ("
//                + df.getCodFactura() + ","
//                + df.getCodArticulo() + ","
//                + df.getCantidad() + ","
//                + df.getPrecio() + ","
//                + df.getTotal() + ")");
        return msg;
    }

    public static Factura busFactura(String cod) {
        Factura fac = null;
        StringBuilder sql = new StringBuilder("SELECT * FROM factura WHERE fac_codigo=");
        sql.append(cod).append("");
//        String sql = "SELECT * FROM factura WHERE fac_codigo=" + cod + "";
        Object[] filaObt = Operacion.getFila(sql.toString());
        if (filaObt != null) {
            fac = new Factura();
            fac.setCodFactura(Integer.parseInt(filaObt[0].toString()));
            fac.setCodCliente(Integer.parseInt(filaObt[1].toString()));
            fac.setCodVendedor(Integer.parseInt(filaObt[2].toString()));
            fac.setDescuento(Double.parseDouble(filaObt[3].toString()));
            fac.setTipoPago(filaObt[4].toString());
            fac.setFecha(filaObt[5].toString());
            fac.setHora(filaObt[6].toString());
            fac.setTotal(Double.parseDouble(filaObt[7].toString()));
        } else {
            System.out.println("No encontrado");
        }
        return fac;
    }

    public static List listFacturas(String fecha) {
        StringBuilder sql = new StringBuilder("SELECT `p-ventabd_v_c`.factura.fac_codigo,");
        sql.append("`p-ventabd_v_c`.factura.idemision,");
        sql.append("`bd_v_c`.clientes.razon_social,");
        sql.append("`p-ventabd_v_c`.factura.fac_fecha,");
        sql.append("`p-ventabd_v_c`.factura.fac_hora,");
        sql.append("`bd_v_c`.clientes.idcliente,");
        sql.append("`p-ventabd_v_c`.factura.caja_ca_id,");
        sql.append("`p-ventabd_v_c`.factura.tipo,");
        sql.append("`p-ventabd_v_c`.factura.fac_factura,");
        sql.append("`p-ventabd_v_c`.factura.fac_tipoventa,");
        sql.append("`p-ventabd_v_c`.factura.estado,");
        sql.append("`p-ventabd_v_c`.factura.fac_totalfinal,");
        sql.append("`p-ventabd_v_c`.vendedor.ven_codigo,");
        sql.append("`p-ventabd_v_c`.factura.fac_indicador,");
        sql.append("`p-ventabd_v_c`.factura.fac_exenta,");
        sql.append("`p-ventabd_v_c`.factura.fac_iva5,");
        sql.append("`p-ventabd_v_c`.factura.fac_iva10,");
        sql.append("`bd_v_c`.timbrado.descripcion,");
        sql.append("`bd_v_c`.timbrado.fechadesde,");
        sql.append("`bd_v_c`.timbrado.fechahasta,");
        sql.append("`p-ventabd_v_c`.factura.monto_pago_efectivo,");
        sql.append("`p-ventabd_v_c`.factura.monto_pago_transferencia,");
        sql.append("`p-ventabd_v_c`.factura.boleta_nro_transferencia,");
        sql.append("`p-ventabd_v_c`.factura.monto_pago_qr,");
        sql.append("`p-ventabd_v_c`.factura.boleta_nro_qr,");
        sql.append("`p-ventabd_v_c`.factura.vuelto_pago");
        sql.append(" FROM `p-ventabd_v_c`.factura INNER JOIN `p-ventabd_v_c`.vendedor INNER JOIN `bd_v_c`.clientes INNER JOIN `bd_v_c`.timbrado INNER JOIN `bd_v_c`.puntoemision");
        sql.append(" WHERE `p-ventabd_v_c`.factura.vendedor_ven_codigo = `p-ventabd_v_c`.vendedor.ven_codigo");
        sql.append(" AND `p-ventabd_v_c`.factura.clientes_cli_codigo = `bd_v_c`.clientes.idcliente");
        sql.append(" AND `p-ventabd_v_c`.factura.idemision = `bd_v_c`.puntoemision.idemision");
        sql.append(" AND `bd_v_c`.puntoemision.idtimbrado= `bd_v_c`.timbrado.idtimbrado");
        sql.append(" AND `p-ventabd_v_c`.factura.fac_fecha='").append(fecha).append("'");
        sql.append(" ORDER BY `p-ventabd_v_c`.factura.fac_codigo ASC");
        return Operacion.getTabla(sql.toString());
    }

    public static List listFacturas1(int idV, String fecha) {
        StringBuilder sql = new StringBuilder("SELECT `p-ventabd_v_c`.factura.fac_codigo,");
        sql.append("`p-ventabd_v_c`.factura.idemision,");
        sql.append("`bd_v_c`.clientes.razon_social,");
        sql.append("`p-ventabd_v_c`.factura.fac_fecha,");
        sql.append("`p-ventabd_v_c`.factura.fac_hora,");
        sql.append("`bd_v_c`.clientes.idcliente,");
        sql.append("`p-ventabd_v_c`.factura.caja_ca_id,");
        sql.append("`p-ventabd_v_c`.factura.tipo,");
        sql.append("`p-ventabd_v_c`.factura.fac_factura,");
        sql.append("`p-ventabd_v_c`.factura.fac_tipoventa,");
        sql.append("`p-ventabd_v_c`.factura.estado,");
        sql.append("`p-ventabd_v_c`.factura.fac_totalfinal,");
        sql.append("`p-ventabd_v_c`.vendedor.ven_codigo,");
        sql.append("`p-ventabd_v_c`.factura.fac_indicador,");
        sql.append("`p-ventabd_v_c`.factura.fac_exenta,");
        sql.append("`p-ventabd_v_c`.factura.fac_iva5,");
        sql.append("`p-ventabd_v_c`.factura.fac_iva10,");
        sql.append("`bd_v_c`.timbrado.descripcion,");
        sql.append("`bd_v_c`.timbrado.fechadesde,");
        sql.append("`bd_v_c`.timbrado.fechahasta,");
        sql.append("`p-ventabd_v_c`.factura.monto_pago_efectivo,");
        sql.append("`p-ventabd_v_c`.factura.monto_pago_transferencia,");
        sql.append("`p-ventabd_v_c`.factura.boleta_nro_transferencia,");
        sql.append("`p-ventabd_v_c`.factura.monto_pago_qr,");
        sql.append("`p-ventabd_v_c`.factura.boleta_nro_qr,");
        sql.append("`p-ventabd_v_c`.factura.vuelto_pago");
        sql.append(" FROM `p-ventabd_v_c`.factura INNER JOIN `p-ventabd_v_c`.vendedor INNER JOIN `bd_v_c`.clientes INNER JOIN `bd_v_c`.timbrado INNER JOIN `bd_v_c`.puntoemision");
        sql.append(" WHERE `p-ventabd_v_c`.factura.vendedor_ven_codigo = `p-ventabd_v_c`.vendedor.ven_codigo");
        sql.append(" AND `p-ventabd_v_c`.factura.clientes_cli_codigo = `bd_v_c`.clientes.idcliente");
        sql.append(" AND `p-ventabd_v_c`.factura.idemision = `bd_v_c`.puntoemision.idemision");
        sql.append(" AND `bd_v_c`.puntoemision.idtimbrado= `bd_v_c`.timbrado.idtimbrado");
        sql.append(" AND `p-ventabd_v_c`.factura.fac_fecha='").append(fecha).append("'");
        sql.append(" AND `p-ventabd_v_c`.vendedor.ven_codigo=").append(idV);
        sql.append(" ORDER BY `p-ventabd_v_c`.factura.fac_codigo ASC");
        return Operacion.getTabla(sql.toString());
    }
        
        

    public static List listVentaContaduria(String fecha1, String fecha2) {
        StringBuilder sql = new StringBuilder("SELECT ruc, desccliente, fecha, timbrado, fac, diez, cinco, exenta, fac_totalfinal, CONCAT (condicion, indi) AS condicion FROM v_ventatotal2");
        sql.append(" WHERE v_ventatotal2.fecha >='").append(fecha1).append("'");
        sql.append(" AND v_ventatotal2.fecha <='").append(fecha2).append("'");
        sql.append(" AND v_ventatotal2.tipo='F'");
        sql.append(" ORDER BY  v_ventatotal2.idemision ASC ,  v_ventatotal2.cod ASC");
        return Operacion.getTabla(sql.toString());
    }

    /* public static List listFacturasMoviles() {
        StringBuilder sql = new StringBuilder("SELECT id,idemision,timbra,estable,pexp,nrofactura,condicion,");
        sql.append("fecha,hora,ruc,razon_social,totalfinal,nombre,estado");
        sql.append(" FROM v_ventas1");
        sql.append(" ORDER BY idemision ASC");
        return OperacionMovil.getTabla(sql.toString());
    }*/
    public static List listFacturasMovil1() {
        StringBuilder sql = new StringBuilder("SELECT id,idemision,timbra,fechadesde,fechahasta,estable,pexp,factura,condicion,");
        sql.append("fecha,hora,ruc,razon_social,totalfinal,nombre,estado,exenta,iva5,iva10");
        sql.append(" FROM v_ventas_1");
        sql.append(" ORDER BY idemision ASC, id ASC");
        return OperacionMovil.getTabla(sql.toString());
    }

    public static List listFacturasMovilesT(String idT) {
        StringBuilder sql = new StringBuilder("SELECT id,idemision,timbra,estable,pexp,nrofactura,condicion,");
        sql.append("fecha,hora,ruc,razon_social,totalfinal,nombre,estado");
        sql.append(" FROM v_ventas1");
        sql.append(" WHERE idtimbrado=").append(idT);
        sql.append(" ORDER BY id ASC");
        return OperacionMovil.getTabla(sql.toString());
    }

    public static List listFacturasMovilesT1(String idT) {
        StringBuilder sql = new StringBuilder("SELECT id,idemision,timbra,fechadesde,fechahasta,estable,pexp,factura,condicion,");
        sql.append("fecha,hora,ruc,razon_social,totalfinal,nombre,estado,exenta,iva5,iva10");
        sql.append(" FROM v_ventas_1");
        sql.append(" WHERE idtimbrado=").append(idT);
        sql.append(" ORDER BY idemision ASC, id ASC");
        return OperacionMovil.getTabla(sql.toString());
    }

    /*public static List listFacturasMovilesT1(String idT) {
        StringBuilder sql = new StringBuilder("SELECT id,idemision,timbra,estable,pexp,factura,condicion,");
        sql.append("fecha,hora,ruc,razon_social,totalfinal,nombre,estado");
        sql.append(" FROM v_ventas_1");
        sql.append(" WHERE idtimbrado=").append(idT);
        sql.append(" ORDER BY id ASC");
        return OperacionMovil.getTabla(sql.toString());
    }*/
    public static List listFacturasMovilesTPE(String idPE, String idT) {
        StringBuilder sql = new StringBuilder("SELECT id,idemision,timbra,estable,pexp,nrofactura,condicion,");
        sql.append("fecha,hora,ruc,razon_social,totalfinal,nombre,estado");
        sql.append(" FROM v_ventas1");
        sql.append(" WHERE idemision=").append(idPE);
        sql.append(" AND idtimbrado=").append(idT);
        sql.append(" ORDER BY id ASC");
        return OperacionMovil.getTabla(sql.toString());
    }

    public static List listFacturasMovilesTPE1(String idPE, String idT) {
        StringBuilder sql = new StringBuilder("SELECT id,idemision,timbra,fechadesde,fechahasta,estable,pexp,factura,condicion,");
        sql.append("fecha,hora,ruc,razon_social,totalfinal,nombre,estado,exenta,iva5,iva10");
        sql.append(" FROM v_ventas_1");
        sql.append(" WHERE idemision=").append(idPE);
        sql.append(" AND idtimbrado=").append(idT);
        sql.append(" ORDER BY id ASC");
        return OperacionMovil.getTabla(sql.toString());
    }

    /*public static List listFacturasMovilesTPE1(String idPE,String idT) {
        StringBuilder sql = new StringBuilder("SELECT id,idemision,timbra,estable,pexp,factura,condicion,");
        sql.append("fecha,hora,ruc,razon_social,totalfinal,nombre,estado");
        sql.append(" FROM v_ventas_1");
        sql.append(" WHERE idemision=").append(idPE);
        sql.append(" AND idtimbrado=").append(idT);
        sql.append(" ORDER BY id ASC");
        return OperacionMovil.getTabla(sql.toString());
    }*/
    public static List listFacturasCredito(String cliente) {
        StringBuilder sql = new StringBuilder("SELECT factura.fac_codigo,");
        sql.append("clientes.cli_razonsocial,");
        sql.append("factura.fac_fecha,");
        sql.append("factura.fac_hora,");
        sql.append("clientes.cli_codigo,");
        sql.append("factura.caja_ca_id,");
        sql.append("factura.fac_factura,");
        sql.append("factura.fac_tipoventa,");
        sql.append("factura.estado,");
        sql.append("factura.fac_totalfinal,");
        sql.append("vendedor.ven_codigo,");
        sql.append("factura.fac_indicador");
        sql.append(" FROM factura ");
        sql.append(" JOIN vendedor ON factura.vendedor_ven_codigo = vendedor.ven_codigo");
        sql.append(" JOIN clientes ON factura.clientes_cli_codigo = clientes.cli_codigo");
        sql.append(" WHERE clientes.cli_codigo=");
        sql.append(cliente);
        sql.append(" AND factura.fac_tipoventa='CREDITO'");
        sql.append(" ORDER BY factura.fac_codigo ASC");
        return Operacion.getTabla(sql.toString());
    }

    public static List listFacturasCreditoPendiente(String cliente) {
        StringBuilder sql = new StringBuilder("SELECT factura.fac_codigo,");
        sql.append("clientes.cli_razonsocial,");
        sql.append("factura.fac_fecha,");
        sql.append("factura.fac_hora,");
        sql.append("clientes.cli_codigo,");
        sql.append("factura.caja_ca_id,");
        sql.append("factura.fac_factura,");
        sql.append("factura.fac_tipoventa,");
        sql.append("factura.estado,");
        sql.append("factura.fac_totalfinal,");
        sql.append("vendedor.ven_codigo,");
        sql.append("factura.fac_indicador");
        sql.append(" FROM factura ");
        sql.append(" JOIN vendedor ON factura.vendedor_ven_codigo = vendedor.ven_codigo");
        sql.append(" JOIN clientes ON factura.clientes_cli_codigo = clientes.cli_codigo");
        sql.append(" WHERE clientes.cli_codigo=");
        sql.append(cliente);
        sql.append(" AND factura.estado='PENDIENTE'");
        sql.append(" AND factura.fac_tipoventa='CREDITO'");
        sql.append(" ORDER BY factura.fac_codigo ASC");
        return Operacion.getTabla(sql.toString());
    }

    public static List listFacturasCreditoActivo(String cliente) {
        StringBuilder sql = new StringBuilder("SELECT factura.fac_codigo,");
        sql.append("clientes.cli_razonsocial,");
        sql.append("factura.fac_fecha,");
        sql.append("factura.fac_hora,");
        sql.append("clientes.cli_codigo,");
        sql.append("factura.caja_ca_id,");
        sql.append("factura.fac_factura,");
        sql.append("factura.fac_tipoventa,");
        sql.append("factura.estado,");
        sql.append("factura.fac_totalfinal,");
        sql.append("vendedor.ven_codigo,");
        sql.append("factura.fac_indicador");
        sql.append(" FROM factura ");
        sql.append(" JOIN vendedor ON factura.vendedor_ven_codigo = vendedor.ven_codigo");
        sql.append(" JOIN clientes ON factura.clientes_cli_codigo = clientes.cli_codigo");
        sql.append(" WHERE clientes.cli_codigo=");
        sql.append(cliente);
        sql.append(" AND factura.fac_indicador='S'");
        sql.append(" AND factura.fac_tipoventa='CREDITO'");
        sql.append(" ORDER BY factura.fac_codigo ASC");
        return Operacion.getTabla(sql.toString());
    }

    public static List listFacturasCreditoPendienteActivo(String cliente) {
        StringBuilder sql = new StringBuilder("SELECT factura.fac_codigo,");
        sql.append("clientes.cli_razonsocial,");
        sql.append("factura.fac_fecha,");
        sql.append("factura.fac_hora,");
        sql.append("clientes.cli_codigo,");
        sql.append("factura.caja_ca_id,");
        sql.append("factura.fac_factura,");
        sql.append("factura.fac_tipoventa,");
        sql.append("factura.estado,");
        sql.append("factura.fac_totalfinal,");
        sql.append("vendedor.ven_codigo,");
        sql.append("factura.fac_indicador");
        sql.append(" FROM factura ");
        sql.append(" JOIN vendedor ON factura.vendedor_ven_codigo = vendedor.ven_codigo");
        sql.append(" JOIN clientes ON factura.clientes_cli_codigo = clientes.cli_codigo");
        sql.append(" WHERE clientes.cli_codigo=");
        sql.append(cliente);
        sql.append(" AND factura.fac_indicador='S'");
        sql.append(" AND factura.estado='PENDIENTE'");
        sql.append(" AND factura.fac_tipoventa='CREDITO'");
        sql.append(" ORDER BY factura.fac_codigo ASC");
        return Operacion.getTabla(sql.toString());
    }

    public static List lisFacturas2() {
        StringBuilder sql = new StringBuilder("SELECT factura.fac_codigo,");
        sql.append("clientes.cli_razonsocial,");
        sql.append("factura.fac_fecha,");
        sql.append("clientes.cli_codigo,");
        sql.append("factura.fac_tipoventa,");
        sql.append("factura.fac_total,");
        sql.append("vendedor.ven_codigo ");
        sql.append(" FROM ((factura ");
        sql.append(" JOIN vendedor ON ((factura.vendedor_ven_codigo = vendedor.ven_codigo))) ");
        sql.append(" JOIN clientes ON ((factura.clientes_cli_codigo = clientes.cli_codigo))) ");
        sql.append(" WHERE (((factura.fac_indicador) = 'S') AND (NOT (EXISTS ( SELECT factura.fac_codigo ");
        sql.append(" FROM notacredito ");
        sql.append(" WHERE (factura.fac_codigo = notacredito.nc_factura)))))");
        return Operacion.getTabla(sql.toString());
    }

//    public static List listFacturasAnuladas() {
//        String sql = "SELECT factura.fac_codigo, clientes.cli_razonSocial, factura.fac_fecha, clientes.cli_codigo, factura.fac_descuento,factura.fac_total, vendedor.ven_codigo FROM factura, clientes, vendedor where factura.fac_cliente = clientes.cli_codigo AND factura.fac_vendedor = vendedor.ven_codigo AND factura.fac_indicador='N'";
//        return Operacion.getTabla(sql);
//    }
    public static List fillCliente(String nom) {
        StringBuilder sql = new StringBuilder("SELECT factura.fac_codigo,");
        sql.append("clientes.cli_razonsocial,");
        sql.append("factura.fac_fecha,");
        sql.append("clientes.cli_codigo,");
        sql.append("factura.fac_descuento,");
        sql.append("factura.fac_total,");
        sql.append("vendedor.ven_codigo ");
        sql.append(" FROM ((factura ");
        sql.append(" JOIN vendedor ON ((factura.fac_vendedor = vendedor.ven_codigo))) ");
        sql.append(" JOIN clientes ON ((factura.fac_cliente = clientes.cli_codigo))) ");
        sql.append(" WHERE ");
        sql.append("(((factura.fac_indicador) = 'S') AND ");
        sql.append(" (NOT (EXISTS ( SELECT factura.fac_codigo ");
        sql.append(" FROM notacredito ");
        sql.append("  WHERE (factura.fac_codigo = notacredito.nc_factura)))) AND clientes.cli_razonsocial ILIKE '");
        sql.append(nom).append("%')");
//        String sql = "SELECT factura.fac_codigo,"
//                + "    clientes.cli_razonsocial,"
//                + "    factura.fac_fecha,"
//                + "    clientes.cli_codigo,"
//                + "    factura.fac_descuento,"
//                + "    factura.fac_total,"
//                + "    vendedor.ven_codigo"
//                + "   FROM ((factura"
//                + "   JOIN vendedor ON ((factura.fac_vendedor = vendedor.ven_codigo)))"
//                + "   JOIN clientes ON ((factura.fac_cliente = clientes.cli_codigo)))"
//                + "   WHERE "
//                + "(((factura.fac_indicador) = 'S') AND "
//                + "(NOT (EXISTS ( SELECT factura.fac_codigo"
//                + "   FROM notacredito"
//                + "  WHERE (factura.fac_codigo = notacredito.nc_factura)))) AND clientes.cli_razonsocial ILIKE '" + nom + "%')";
        return Operacion.getTabla(sql.toString());
    }

    public static List listDetalles(String cod) {
        StringBuilder sql = new StringBuilder("SELECT `p-ventabd_v_c`.detalle_factura.ven_cantidad,`p-ventabd_v_c`.detalle_factura.dependencia,`p-ventabd_v_c`.detalle_factura.iddependencia,");
        sql.append("`p-ventabd_v_c`.detalle_factura.articulo_art_codigo,");
        sql.append("bd_v_c.productos.cod_barra,");
        sql.append("bd_v_c.productos.descripcion,");
        sql.append("`p-ventabd_v_c`.detalle_factura.ven_precio,");
        sql.append("`p-ventabd_v_c`.detalle_factura.ven_total,");
        sql.append("`p-ventabd_v_c`.detalle_factura.promo,");
        sql.append("`p-ventabd_v_c`.detalle_factura.id_iva,");
        sql.append("`p-ventabd_v_c`.detalle_factura.exenta,");
        sql.append("`p-ventabd_v_c`.detalle_factura.5,");
        sql.append("`p-ventabd_v_c`.detalle_factura.10 ");
        sql.append(" FROM `p-ventabd_v_c`.detalle_factura");
        sql.append(" JOIN bd_v_c.productos ON `p-ventabd_v_c`.detalle_factura.articulo_art_codigo = bd_v_c.productos.idproducto");
        sql.append(" JOIN `p-ventabd_v_c`.factura ON `p-ventabd_v_c`.detalle_factura.factura_fac_codigo = `p-ventabd_v_c`.factura.fac_codigo");
        sql.append(" WHERE `p-ventabd_v_c`.factura.fac_codigo=").append(cod);
        return Operacion.getTabla(sql.toString());
    }

    public static List listDetallesVentasMovil(String cod, String idemision) {
        StringBuilder sql = new StringBuilder("SELECT cod_interno,producto,cant,precio,total");
        sql.append(" FROM v_detalleventa1");
        sql.append(" WHERE id=").append(cod);
        sql.append(" AND idemision=").append(idemision);
        return OperacionMovil.getTabla(sql.toString());
    }

    public static List listDetallesVentasMovil1(String cod, String idemision) {
        StringBuilder sql = new StringBuilder("SELECT cod_interno,cod_barra,producto,cant,precio,total,impuesto_aplicado,um,promo");
        sql.append(" FROM v_detalleventa_1");
        sql.append(" WHERE id=").append(cod);
        sql.append(" AND idemision=").append(idemision);
        return OperacionMovil.getTabla(sql.toString());
    }

    /*public static List listDetallesF(String cod) {
        StringBuilder sql = new StringBuilder("SELECT detalle_factura.df_cantidad,");
        sql.append("detalle_factura.df_articulo,");
        sql.append("articulo.art_descripcion,");
        sql.append("detalle_factura.df_precarticulo,");
        sql.append("detalle_factura.df_total ");
        sql.append(" FROM ((detalle_factura ");
        sql.append(" JOIN factura ON ((detalle_factura.fac_codigo = factura.fac_codigo))) ");
        sql.append(" JOIN articulo ON ((detalle_factura.df_articulo = articulo.art_codigo))) ");
        sql.append(" WHERE (factura.fac_codigo = ").append(cod).append(")");
        return Operacion.getTabla(sql.toString());
    }*/
}
