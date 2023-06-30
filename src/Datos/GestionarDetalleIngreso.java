package Datos;

import Componentes.Operacion;
import Componentes.generarCodigos;
import Modelo.DetalleIngreso;
import java.util.List;

public class GestionarDetalleIngreso {

    public static String getCodigo() {
        String cod = generarCodigos.getCodigo("SELECT MAX(di_codigo) FROM detalleingreso");
        return cod;
    }
    
    public static String addDetalleIngreso(DetalleIngreso di) {
        String msg = null;
        StringBuilder sql = new StringBuilder("INSERT INTO detalleingreso VALUES (");
        sql.append(di.getDgCodigo()).append(",'").append(di.getDgDescripcion()).append("','S','").append(di.getUsuario()).append("')");
        msg = Operacion.exeOperacion(sql.toString());
        return msg;
    }

    public static String actDetalleIngreso(DetalleIngreso di) {
        String msg;
        StringBuilder sql = new StringBuilder("UPDATE detalleingreso SET di_descripcion ='");
        sql.append(di.getDgDescripcion());
        sql.append("', usu='");
        sql.append(di.getUsuario());
        sql.append("' WHERE di_codigo=");
        sql.append(di.getDgCodigo());
        sql.append("");
        msg = Operacion.exeOperacion(sql.toString());
        return msg;
    }

    public static String delDetalleIngreso(String cod, String usuario) {
        String msg;
        StringBuilder sql = new StringBuilder("UPDATE detalleingreso SET di_indicador='N', usu='"+usuario+"' WHERE di_codigo=");
        sql.append(cod);
        sql.append("");
        msg = Operacion.exeOperacion(sql.toString());
        return msg;
    }

    public static List listDetalleIngreso() {
        String sql = "SELECT * FROM detalleingreso WHERE di_indicador = 'S'";
        return Operacion.getTabla(sql);
    }

}
