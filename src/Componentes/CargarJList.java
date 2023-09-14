package Componentes;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JList;
import java.sql.*;

public class CargarJList {
    static DefaultComboBoxModel modeloCombo;
    static DataSourceService dss = new DataSourceService();
    
    public static void cargar(JList cb, String sql)
    {
        try {
            try (Connection con = dss.getDataSource().getConnection() //Nos conectamos
            ) {
                Statement st = con.createStatement();
                try (ResultSet rs = (ResultSet) st.executeQuery(sql)) {
                    modeloCombo = new DefaultComboBoxModel();
                    cb.setModel(modeloCombo);
                    while (rs.next()) {
                        modeloCombo.addElement(new Combo(Integer.parseInt(rs.getString(1)), rs.getString(2)));
                    }
                    rs.close();
                    st.close();
                    con.close();
                }
            }
        } catch (NumberFormatException | SQLException e) {
            //Excepcion en caso haya conexion
            System.out.println("Algunos formularios no estan activos, para actualizarse, o no hay conexión");
        }
    }
    
    public static String getCodidgo(JList cb)//Metodo para Obtener el id
    {
        Combo c = (Combo)cb.getSelectedValue();//Seleccionamos
        int id = c.getCodigo();//Obtenermos el id
        System.out.println("getCodigo: "+id);
        return String.valueOf(id);//Retornamos el codigo
    }
}
