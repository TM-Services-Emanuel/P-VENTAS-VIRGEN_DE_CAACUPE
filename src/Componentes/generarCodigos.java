package Componentes;

import java.sql.*;

/*CLASE PARA GENERAR LOS ID's DE TODAS LAS TABLAS Y PARA EXTRAER VALORES ENTEROS*/
 /*-------------------------------------------------------------------------------*/
public class generarCodigos {

    static DataSourceService dss = new DataSourceService();
    static DataSourceService1 dss1 = new DataSourceService1();

    public static String getCodigo(String sql) {
        String codgen = "";
        try {
            try (Connection conexion = dss.getDataSource().getConnection(); Statement sentencia = conexion.createStatement(); ResultSet resultado = sentencia.executeQuery(sql);) {
                resultado.first();
                codgen = String.valueOf(resultado.getInt(1) + 1);
                resultado.close();
                sentencia.close();
                conexion.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Tabla vacía*");
        }

        return codgen;
    }

    public static String getCodigoMovil(String sql) {
        String codgen = "";
        try {
            try (Connection conexion = dss1.getDataSource().getConnection(); Statement sentencia = conexion.createStatement(); ResultSet resultado = sentencia.executeQuery(sql);) {
                resultado.first();
                codgen = String.valueOf(resultado.getInt(1) + 1);
                resultado.close();
                sentencia.close();
                conexion.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Tabla vacía*");
        }

        return codgen;
    }

    public static String ObtenerCodigo(String sql) {
        String codgen = "";
        try {
            try (Connection conexion = dss.getDataSource().getConnection(); Statement sentencia = conexion.createStatement(); ResultSet resultado = sentencia.executeQuery(sql);) {
                resultado.first();
                codgen = String.valueOf(resultado.getInt(1));
                resultado.close();
                sentencia.close();
                conexion.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Tabla vacía*");
        }

        return codgen;
    }
    
    public static String ObtenerCodigo2(String sql) {
        String codgen = null;
        try {
            try (Connection conexion = dss.getDataSource().getConnection(); Statement sentencia = conexion.createStatement(); ResultSet resultado = sentencia.executeQuery(sql);) {
                resultado.first();
                codgen = String.valueOf(resultado.getString(1));
                resultado.close();
                sentencia.close();
                conexion.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Tabla vacía*");
        }

        return codgen;
    }

    public static String getCantidad(String sql) {
        String codgen;
        String CodMaximo = "";
        int contador = 0;
        try {
            try (Connection conexion = dss.getDataSource().getConnection(); Statement sentencia = conexion.createStatement(); ResultSet resultado = sentencia.executeQuery(sql);) {
                ResultSetMetaData rmeta = resultado.getMetaData();
                int numColumnas = rmeta.getColumnCount();
                while (resultado.next()) {
                    for (int j = 1; j <= numColumnas; j++) {
                        CodMaximo = resultado.getString(j);
                    }
                    contador++;
                }
                resultado.close();
                sentencia.close();
                conexion.close();
            }
        } catch (SQLException e) {
            System.out.println("Tabla vacía/");
        }
        if (contador == 0) {
            codgen = "1";
        } else {
            int mayor = Integer.parseInt(CodMaximo.substring(0));
            codgen = String.valueOf(mayor);
        }
        return codgen;
    }

    public static String getDecimales(String sql) {
        String codgen;
        String CodMaximo = "";
        int contador = 0;
        try {
            try (Connection conexion = dss.getDataSource().getConnection(); Statement sentencia = conexion.createStatement(); ResultSet resultado = sentencia.executeQuery(sql);) {
                ResultSetMetaData rmeta = resultado.getMetaData();
                int numColumnas = rmeta.getColumnCount();
                while (resultado.next()) {
                    for (int j = 1; j <= numColumnas; j++) {
                        CodMaximo = resultado.getString(j);
                    }
                    contador++;
                }
                resultado.close();
                sentencia.close();
                conexion.close();
            }
        } catch (SQLException e) {
            System.out.println(0.0);
        }
        if (contador == 0) {
            codgen = "1";
        } else {
            int mayor = Integer.parseInt(CodMaximo.substring(0));
            codgen = String.valueOf(mayor);
        }
        return codgen;
    }
    
    public static long getDecimalesLong(String sql) {
        long valor = 0;
        try {
            try (Connection conexion = dss.getDataSource().getConnection(); Statement sentencia = conexion.createStatement(); ResultSet resultado = sentencia.executeQuery(sql);) {
                ResultSetMetaData rmeta = resultado.getMetaData();
                int numColumnas = rmeta.getColumnCount();
                while (resultado.next()) {
                    for (int j = 1; j <= numColumnas; j++) {
                        valor = resultado.getLong(j);
                    }
                }
                resultado.close();
                sentencia.close();
                conexion.close();
            }
        } catch (SQLException e) {
            System.out.println("Error obteniendo valor en getDecinalesINT: "+e.getMessage());
        }
        return valor;
    }
    

    public static String getFecha(String sql) {
        String codgen;
        String CodMaximo = "";
        int contador = 0;
        try {
            try (Connection conexion = dss.getDataSource().getConnection(); Statement sentencia = conexion.createStatement(); ResultSet resultado = sentencia.executeQuery(sql);) {
                ResultSetMetaData rmeta = resultado.getMetaData();
                int numColumnas = rmeta.getColumnCount();
                while (resultado.next()) {
                    for (int j = 1; j <= numColumnas; j++) {
                        CodMaximo = resultado.getString(j);
                    }
                    contador++;
                }
                resultado.close();
                sentencia.close();
                conexion.close();
            }
        } catch (SQLException e) {
            System.out.println("Tabla vacía-");
        }
        if (contador == 0) {
            codgen = "1";
        } else {
            String mayor = String.valueOf(CodMaximo.substring(0));
            codgen = String.valueOf(mayor);
        }
        return codgen;
    }

}
