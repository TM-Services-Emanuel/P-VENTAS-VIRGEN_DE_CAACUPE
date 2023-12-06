package Datos;

import Componentes.Fecha;
import Componentes.Operacion;
import Componentes.generarCodigos;
import Modelo.Caja;
import java.util.List;

public class GestionarCaja {

    public static String getCodigo() {
        String cod = generarCodigos.getCodigo("SELECT MAX(ca_id) FROM caja");
        return cod;
    }

    public static String addCaja(Caja caja) {
        String msg;
        StringBuilder sql = new StringBuilder("INSERT INTO caja (ca_id, ca_fechainicio, ca_horainicio, ca_inicial, ca_final, ca_entregado, ca_gastos, ca_diferencia, ca_monto_salida, ca_usuarioinicio, ca_indicador) VALUES (");
        sql.append(getCodigo()).append(",'");
        sql.append(caja.getFechaI()).append("','");
        sql.append(caja.getHoraI()).append("',");
        sql.append(caja.getCaInicial()).append(",");
        sql.append(caja.getCaFinal()).append(",");
        sql.append(caja.getCaEntregado()).append(",");
        sql.append(caja.getCaGastos()).append(",");
        sql.append(caja.getCaDiferencia()).append(",");
        sql.append(caja.getCaMontoSalida()).append(",'");
        sql.append(caja.getUsuarioI()).append("','S')");
        msg = Operacion.exeOperacion(sql.toString());
        return msg;
    }

    public static String addBoca(int idBoca, int idCaja, int Entregar, int Entregado, int Gastos, int Dif) {
        String msg;
        StringBuilder sql = new StringBuilder("INSERT INTO arreglo_caja (idboca, idcaja, fecha, entregar, entregado, gastos, diferencia) VALUES (");
        sql.append(idBoca).append(",");
        sql.append(idCaja).append(",'");
        sql.append(Fecha.fechaCorrecta()).append("',");
        sql.append(Entregar).append(",");
        sql.append(Entregado).append(",");
        sql.append(Gastos).append(",");
        sql.append(Dif).append(")");
        msg = Operacion.exeOperacion(sql.toString());
        return msg;
    }

    public static String addArreglo(int idCaja, int idBoca, String Concep, int n50, int n100, int n500, int n1000, int n2000, int n5000, int n10000, int n20000, int n50000, int n100000, int VTotal) {
        String msg;
        StringBuilder sql = new StringBuilder("INSERT INTO arreglo_caja_2 VALUES (");
        sql.append(generarCodigos.getCodigo("SELECT MAX(idarreglo) FROM arreglo_caja_2")).append(",");
        sql.append(idCaja).append(",");
        sql.append(idBoca).append(",'");
        sql.append(Fecha.fechaCorrecta()).append("','");
        sql.append(Fecha.darHora()).append("','");
        sql.append(Concep).append("',");
        sql.append(n50).append(",");
        sql.append(n100).append(",");
        sql.append(n500).append(",");
        sql.append(n1000).append(",");
        sql.append(n2000).append(",");
        sql.append(n5000).append(",");
        sql.append(n10000).append(",");
        sql.append(n20000).append(",");
        sql.append(n50000).append(",");
        sql.append(n100000).append(",");
        sql.append(VTotal).append(", 'S')");
        msg = Operacion.exeOperacion(sql.toString());
        return msg;
    }

    public static String actArreglo(String Concep, int n50, int n100, int n500, int n1000, int n2000, int n5000, int n10000, int n20000, int n50000, int n100000, int VTotal, int idArreglo) {
        String msg;
        StringBuilder sql = new StringBuilder("UPDATE arreglo_caja_2 set concepto='").append(Concep).append("',");
        sql.append("n50=").append(n50).append(",");
        sql.append("n100=").append(n100).append(",");
        sql.append("n500=").append(n500).append(",");
        sql.append("n1000=").append(n1000).append(",");
        sql.append("n2000=").append(n2000).append(",");
        sql.append("n5000=").append(n5000).append(",");
        sql.append("n10000=").append(n10000).append(",");
        sql.append("n20000=").append(n20000).append(",");
        sql.append("n50000=").append(n50000).append(",");
        sql.append("n100000=").append(n100000).append(",");
        sql.append("entregado=").append(VTotal).append(" ");
        sql.append("WHERE idarreglo=").append(idArreglo);
        msg = Operacion.exeOperacion(sql.toString());
        return msg;
    }

    public static String delArreglo(int idArreglo) {
        String msg;
        String sql = "UPDATE arreglo_caja_2 set estado='N' where idarreglo=" + idArreglo;
        msg = Operacion.exeOperacion(sql);
        return msg;
    }

    public static String actBoca(int idBoca, int idCaja, int Entregar, int Entregado, int Gastos, int Dif) {
        String msg;
        StringBuilder sql = new StringBuilder("UPDATE arreglo_caja SET entregar=");
        sql.append(Entregar).append(", entregado=");
        sql.append(Entregado).append(", Gastos=");
        sql.append(Gastos).append(", diferencia=");
        sql.append(Dif).append(" WHERE idboca=");
        sql.append(idBoca).append(" AND idcaja=");
        sql.append(idCaja);
        msg = Operacion.exeOperacion(sql.toString());
        return msg;
    }

    public static String actCaja(Caja caja) {
        String msg;
        StringBuilder sql = new StringBuilder("UPDATE caja SET ca_final=");
        sql.append(caja.getCaFinal());
        sql.append(", ca_entregado=");
        sql.append(caja.getCaEntregado());
        sql.append(", ca_gastos=");
        sql.append(caja.getCaGastos());
        sql.append(", ca_diferencia=");
        sql.append(caja.getCaDiferencia());
        sql.append(", ca_monto_salida=");
        sql.append(caja.getCaMontoSalida());
        sql.append(" WHERE ca_id =");
        sql.append(caja.getCaId());
        sql.append("");
        msg = Operacion.exeOperacion(sql.toString());
        return msg;
    }

    public static String CerrarCaja(Caja caja) {
        String msg;
        StringBuilder sql = new StringBuilder("UPDATE caja SET ca_fechafin='");
        sql.append(caja.getFechaF());
        sql.append("', ca_horafin='");
        sql.append(caja.getHoraF());
        sql.append("', ca_final=");
        sql.append(caja.getCaFinal());
        sql.append(", ca_entregado=");
        sql.append(caja.getCaEntregado());
        sql.append(", ca_gastos=");
        sql.append(caja.getCaGastos());
        sql.append(", ca_diferencia=");
        sql.append(caja.getCaDiferencia());
        sql.append(", ca_usuariocierre='");
        sql.append(caja.getUsuarioF());
        sql.append("', ca_indicador='N'");
        sql.append(" WHERE ca_id =");
        sql.append(caja.getCaId());
        sql.append("");
        msg = Operacion.exeOperacion(sql.toString());
        return msg;
    }

    public static Caja busCaja(String cod) {
        Caja caj = null;
        StringBuilder s = new StringBuilder("SELECT * FROM caja WHERE ca_id=");
        s.append(cod);
        s.append("");
        Object[] filaObt = Operacion.getFila(s.toString());
        if (filaObt != null) {
            caj = new Caja();
            caj.setCaId(Integer.parseInt(filaObt[0].toString()));
            caj.setFechaI(String.valueOf(filaObt[1].toString()));
            caj.setHoraI(String.valueOf(filaObt[2].toString()));
            caj.setCaInicial(Integer.parseInt(filaObt[5].toString()));
            caj.setCaFinal(Integer.parseInt(filaObt[6].toString()));
            caj.setCaEntregado(Integer.parseInt(filaObt[7].toString()));
            caj.setCaGastos(Integer.parseInt(filaObt[8].toString()));
            caj.setCaDiferencia(Integer.parseInt(filaObt[9].toString()));
            caj.setUsuarioI(String.valueOf(filaObt[11].toString()));
            caj.setIndicador(String.valueOf(filaObt[13].toString()));
            System.out.println("Encontrado");
        } else {
            System.out.println("No encontrado");
        }
        return caj;
    }

    public static List listArreglos(String cod) {
        String sql = new StringBuffer("SELECT arreglo_caja_2.idarreglo, arreglo_caja_2.idcaja, arreglo_caja_2.idboca, laboratorio.lab_nombre, arreglo_caja_2.fecha,")
                .append("arreglo_caja_2.hora, arreglo_caja_2.concepto, arreglo_caja_2.n50, arreglo_caja_2.n100, arreglo_caja_2.n500, arreglo_caja_2.n1000,")
                .append("arreglo_caja_2.n2000, arreglo_caja_2.n5000, arreglo_caja_2.n10000, arreglo_caja_2.n20000, arreglo_caja_2.n50000, arreglo_caja_2.n100000, arreglo_caja_2.entregado")
                .append(" FROM arreglo_caja_2 INNER JOIN laboratorio")
                .append(" WHERE arreglo_caja_2.idboca = laboratorio.lab_codigo")
                .append(" AND arreglo_caja_2.idcaja=").append(cod)
                .append(" AND arreglo_caja_2.estado='S' ORDER BY arreglo_caja_2.idarreglo ASC")
                .toString();
        return Operacion.getTabla(sql);
    }
    
    public static List listArreglos2(String cod, String idBoca) {
        String sql = new StringBuffer("SELECT arreglo_caja_2.idarreglo, arreglo_caja_2.idcaja, arreglo_caja_2.idboca, laboratorio.lab_nombre, arreglo_caja_2.fecha,")
                .append("arreglo_caja_2.hora, arreglo_caja_2.concepto, arreglo_caja_2.n50, arreglo_caja_2.n100, arreglo_caja_2.n500, arreglo_caja_2.n1000,")
                .append("arreglo_caja_2.n2000, arreglo_caja_2.n5000, arreglo_caja_2.n10000, arreglo_caja_2.n20000, arreglo_caja_2.n50000, arreglo_caja_2.n100000, arreglo_caja_2.entregado")
                .append(" FROM arreglo_caja_2 INNER JOIN laboratorio")
                .append(" WHERE arreglo_caja_2.idboca = laboratorio.lab_codigo")
                .append(" AND arreglo_caja_2.idcaja=").append(cod)
                .append(" AND arreglo_caja_2.idboca=").append(idBoca)
                .append(" AND arreglo_caja_2.estado='S' ORDER BY arreglo_caja_2.idarreglo ASC")
                .toString();
        return Operacion.getTabla(sql);
    }
}
