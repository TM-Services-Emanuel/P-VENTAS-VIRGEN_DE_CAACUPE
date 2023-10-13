package Datos;

import Componentes.Fecha;
import Componentes.Operacion;
import Componentes.generarCodigos;
import Modelo.Gasto;
import java.util.List;

public class GestionarGasto {
    
    public static String getCodigo() {
        String cod = generarCodigos.getCodigo("SELECT MAX(ga_codigo) FROM gastos");
        return cod;
    }
    
    public static String addGasto(Gasto g) {
        String msg;
        StringBuilder sql = new StringBuilder("INSERT INTO gastos VALUES (");
        sql.append(getCodigo()).append(", ").append(g.getCaja_ca_id()).append(", '").append(g.getGa_fecha()).append("','").append(Fecha.darHora()).append("',");
        sql.append(g.getMotivo_g()).append(",").append(g.getOtorgado()).append(",");
        sql.append(g.getGa_importe()).append(",'").append(g.getGaObservacion()).append("','S','").append(g.getUsu()).append("','").append(g.getTipo()).append("')");;
        msg = Operacion.exeOperacion(sql.toString());
        return msg;
    }
    
    public static List listarGastos(String fecha) {
        StringBuilder sql = new StringBuilder("SELECT ga_codigo,caja_ca_id,ga_fecha, ga_hora,tipo,motivo_gasto,nombre_vendedor,ga_importe,ga_observacion, usu FROM v_gastos_gral");
        sql.append(" WHERE ga_fecha>='").append(fecha).append("' AND ga_fecha<='").append(fecha).append("' order by ga_codigo ASC");
        return Operacion.getTabla(sql.toString());
    }
    
    public static List listarGastosReparto(String desde, String hasta, int id) {
        StringBuilder sql = new StringBuilder("SELECT ga_codigo,caja_ca_id,ga_fecha, ga_hora,tipo,motivo_gasto,nombre_vendedor,ga_importe,ga_observacion, usu FROM v_gastos_gral");
        sql.append(" WHERE ga_fecha>='").append(desde).append("' AND ga_fecha<='").append(hasta).append("' AND otorgado_a=").append(id).append(" AND motivo_gasto>=2 AND motivo_gasto<=3");
        sql.append(" order by ga_codigo ASC");
        return Operacion.getTabla(sql.toString());
    }
    
    public static String delGasto(int cod, String usuario) {
        String msg;
        StringBuilder sql = new StringBuilder("UPDATE gastos SET ga_indicador='N', usu='" + usuario + "' WHERE ga_codigo=");
        sql.append(cod);
//        String sql = "UPDATE compras SET com_indicador='N' WHERE com_codigo = " + cod + "";
        msg = Operacion.exeOperacion(sql.toString());
        return msg;
    }
    
}
