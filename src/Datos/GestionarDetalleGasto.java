package Datos;

import Componentes.Operacion;
import Componentes.generarCodigos;
import Modelo.DetalleGasto;
import java.util.List;

public class GestionarDetalleGasto {

    public static String getCodigo() {
        String cod = generarCodigos.getCodigo("SELECT MAX(dg_codigo) FROM detallegasto");
        return cod;
    }

    public static String addDetalleGasto(DetalleGasto dg) {
        String msg = null;
        StringBuilder sql = new StringBuilder("INSERT INTO detallegasto VALUES (");
        sql.append(dg.getDgCodigo()).append(",'").append(dg.getDgDescripcion()).append("','S','").append(dg.getUsuario()).append("')");
        msg = Operacion.exeOperacion(sql.toString());
        return msg;
    }

    public static String actDetalleGasto(DetalleGasto dg) {
        String msg;
        StringBuilder sql = new StringBuilder("UPDATE detallegasto SET dg_descripcion ='");
        sql.append(dg.getDgDescripcion());
        sql.append("', usu='");
        sql.append(dg.getUsuario());
        sql.append("' WHERE dg_codigo=");
        sql.append(dg.getDgCodigo());
        sql.append("");
        msg = Operacion.exeOperacion(sql.toString());
        return msg;
    }

    public static String delDetalleGasto(String cod, String usuario) {
        String msg;
        StringBuilder sql = new StringBuilder("UPDATE detallegasto SET dg_indicador='N', usu='");
        sql.append(usuario);
        sql.append("' WHERE dg_codigo =");
        sql.append(cod);
        sql.append("");
        msg = Operacion.exeOperacion(sql.toString());
        return msg;
    }

    public static List listDetalleGasto() {
        String sql = "SELECT * FROM detallegasto WHERE dg_indicador='S'";
        return Operacion.getTabla(sql);
    }
}
