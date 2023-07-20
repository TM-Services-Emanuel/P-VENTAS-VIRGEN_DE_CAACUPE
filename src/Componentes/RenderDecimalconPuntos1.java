package Componentes;

import java.awt.Color;
import java.awt.Component;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class RenderDecimalconPuntos1 extends DefaultTableCellRenderer {
    private static DecimalFormatSymbols simbolos;
    public static DecimalFormat formato;
    
        
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        simbolos = new DecimalFormatSymbols();
        simbolos.setDecimalSeparator('.');
        simbolos.setGroupingSeparator(',');
        formato = new DecimalFormat("#,###.##", simbolos);
        value = formato.format(Double.parseDouble(String.valueOf(value)));

        int c = Integer.parseInt((String) value);
        
        if(c < 0){
            this.setForeground(new java.awt.Color(205,0,0));
        }else if(c == 0){
            this.setForeground(new java.awt.Color(255,255,255));
        }else if(c > 0){
            this.setForeground(new java.awt.Color(0,102,102));
        }
        
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }
}

