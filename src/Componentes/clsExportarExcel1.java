/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Componentes;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author AdminOmarGuevara
 */
public class clsExportarExcel1 {

    public void exportarExcel(JTable t) throws IOException {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Hoja de cálculo", "xls");
        chooser.setFileFilter(filter);
        chooser.setDialogTitle("Guardar archivo");
        chooser.setAcceptAllFileFilterUsed(false);
        if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            String ruta = chooser.getSelectedFile().toString().concat(".xls");
            try {
                File archivoXLS = new File(ruta);
                if (archivoXLS.exists()) {
                    archivoXLS.delete();
                }
                archivoXLS.createNewFile();
                Workbook libro = new HSSFWorkbook();
                try (FileOutputStream archivo = new FileOutputStream(archivoXLS)) {
                    Sheet hoja = libro.createSheet("LISTADO");
                    hoja.setDisplayGridlines(true);
                    for (int f = 0; f < t.getRowCount(); f++) {
                        Row fila = hoja.createRow(f);
                        for (int c = 0; c < t.getColumnCount(); c++) {
                            hoja.setColumnWidth(c, 10000);
                            Cell celda = fila.createCell(c);
                            if (f == 0) {
                                celda.setCellValue(t.getColumnName(c));
                            }
                        }
                    }
                    int filaInicio = 1;
                    for (int f = 0; f < t.getRowCount(); f++) {
                        Row fila = hoja.createRow(filaInicio);
                        filaInicio++;
                        for (int c = 0; c < t.getColumnCount(); c++) {
                            Cell celda = fila.createCell(c);
                            if (t.getValueAt(f, c) instanceof Double) {
                                celda.setCellValue(Double.parseDouble(t.getValueAt(f, c).toString()));
                            } else if (t.getValueAt(f, c) instanceof Float) {
                                celda.setCellValue(Float.parseFloat((String) t.getValueAt(f, c)));
                            } else {
                                celda.setCellValue(String.valueOf(t.getValueAt(f, c)));
                            }
                        }
                    }
                    libro.write(archivo);
                }
                Desktop.getDesktop().open(archivoXLS);
            } catch (IOException | NumberFormatException e) {
                throw e;
            }
        }
    }

    public void exportarExcelClientes(JTable t, JDialog jd) throws IOException {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Hoja de cálculo", "xls");
        chooser.setFileFilter(filter);
        chooser.setDialogTitle("Guardar archivo");
        chooser.setAcceptAllFileFilterUsed(false);
        if (chooser.showSaveDialog(jd) == JFileChooser.APPROVE_OPTION) {
            String ruta = chooser.getSelectedFile().toString().concat(".xls");
            try {
                File archivoXLS = new File(ruta);
                if (archivoXLS.exists()) {
                    archivoXLS.delete();
                }
                archivoXLS.createNewFile();
                Workbook libro = new HSSFWorkbook();

                //configuracion de nuevas paletas de colores
                /* HSSFWorkbook hwb = new HSSFWorkbook();
                HSSFPalette palette = hwb.getCustomPalette();
                HSSFColor myColor = palette.findSimilarColor(204, 204, 204);
                HSSFColor myColor2 = palette.findSimilarColor(153, 153, 153);
                short palIndex204 = myColor.getIndex();
                short palIndex153 = myColor2.getIndex();*/
 /*  // estilo General
                CellStyle styleG = libro.createCellStyle(); // objeto de estilo (cuerpo)
                styleG.setFillPattern(FillPatternType.SOLID_FOREGROUND); // Rellenar celdas     
                styleG.setFillForegroundColor(palIndex204); // Establezca el color de fondo de la celda en blanco
                //fin estilo General*/
                // estilo de cabecera
                CellStyle style = libro.createCellStyle(); // objeto de estilo (cuerpo)
                // style.setFillForegroundColor(palIndex153);
                //style.setFillForegroundColor(palIndex204);

                /*  style.setBorderBottom(BorderStyle.THIN); // Borde inferior
                style.setBottomBorderColor(palIndex153);
                style.setBorderLeft(BorderStyle.THIN); // Borde izquierdo
                style.setLeftBorderColor(palIndex153);
                style.setBorderTop(BorderStyle.THIN); // Borde superior
                style.setTopBorderColor(palIndex153);
                style.setBorderRight(BorderStyle.THIN); // Borde derecho
                style.setRightBorderColor(palIndex153);*/
                //style.setFillPattern(FillPatternType.SOLID_FOREGROUND); // Rellenar celdas     
                //style.setFillForegroundColor(palIndex204); // Establezca el color de fondo de la celda en azul claro
                style.setVerticalAlignment(VerticalAlignment.CENTER); // vertical
                style.setAlignment(HorizontalAlignment.CENTER); // horizontal
                style.setWrapText(true); // Especifica el ajuste de la palabra cuando no se puede mostrar el contenido de la celda
                Font font = libro.createFont();
                font.setFontName("Tahoma");
                font.setFontHeight((short) 160);
                //font.setBold(true);
                style.setFont(font);
                //fin estilo de cabecera

                // estilo de texto
                CellStyle style1 = libro.createCellStyle();
                //style1.setVerticalAlignment(VerticalAlignment.CENTER); // vertical
                //style1.setAlignment(HorizontalAlignment.CENTER); // horizontal
                style1.setWrapText(true); // Especifica el ajuste de la palabra cuando no se puede mostrar el contenido de la celda
                Font font1 = libro.createFont();
                font1.setFontName("Tahoma");
                font1.setFontHeight((short) 160);
                style1.setFont(font1);
                //fin estilo de texto

                try (FileOutputStream archivo = new FileOutputStream(archivoXLS)) {
                    Sheet hoja = libro.createSheet("CLIENTES");
                    hoja.setDisplayGridlines(true);
                    for (int f = 0; f < t.getRowCount(); f++) {
                        Row fila = hoja.createRow(f);
                        for (int c = 0; c < t.getColumnCount(); c++) {
                            switch (c) {
                                case 0 ->
                                    hoja.setColumnWidth(c, 12800);
                                case 1 ->
                                    hoja.setColumnWidth(c, 21500);
                                case 2 ->
                                    hoja.setColumnWidth(c, 9550);
                                case 3 ->
                                    hoja.setColumnWidth(c, 5500);
                                default -> {
                                }
                            }
                            Cell celda = fila.createCell(c);
                            if (f == 0) {
                                celda.setCellValue(t.getColumnName(c));
                                celda.setCellStyle(style);
                            }
                        }
                    }
                    int filaInicio = 1;
                    for (int f = 0; f < t.getRowCount(); f++) {
                        Row fila = hoja.createRow(filaInicio);
                        filaInicio++;
                        for (int c = 0; c < t.getColumnCount(); c++) {
                            Cell celda = fila.createCell(c);
                            celda.setCellStyle(style1);
                            switch (c) {
                                case 0 ->
                                    celda.setCellValue(Integer.parseInt(String.valueOf(t.getValueAt(f, c).toString())));
                                case 1 ->
                                    celda.setCellValue(String.valueOf(t.getValueAt(f, c)));
                                case 2 ->
                                    celda.setCellValue(Integer.parseInt(String.valueOf(t.getValueAt(f, c).toString())));
                                case 3 -> {
                                    if (t.getValueAt(f, c) == null) {
                                        celda.setCellValue(String.valueOf(""));
                                    } else {
                                        celda.setCellValue(Integer.parseInt(String.valueOf(t.getValueAt(f, c).toString())));
                                    }
                                }
                                default -> {
                                }
                            }
                        }
                    }
                    libro.write(archivo);
                }
                Desktop.getDesktop().open(archivoXLS);
            } catch (IOException | NumberFormatException e) {
                throw e;
            }
        }
    }

    public void exportarExcelActosGravados(JTable t, JDialog jd) throws IOException {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Hoja de cálculo", "xls");
        chooser.setFileFilter(filter);
        chooser.setDialogTitle("Guardar archivo");
        chooser.setAcceptAllFileFilterUsed(false);
        if (chooser.showSaveDialog(jd) == JFileChooser.APPROVE_OPTION) {
            String ruta = chooser.getSelectedFile().toString().concat(".xls");
            try {
                File archivoXLS = new File(ruta);
                if (archivoXLS.exists()) {
                    archivoXLS.delete();
                }
                archivoXLS.createNewFile();
                Workbook libro = new HSSFWorkbook();

                // estilo de cabecera
                CellStyle style = libro.createCellStyle(); // objeto de estilo (cuerpo)
                //style.setBorderBottom(BorderStyle.THIN); // Borde inferior
                //style.setBorderLeft(BorderStyle.THIN); // Borde izquierdo
                //style.setBorderTop(BorderStyle.THIN); // Borde superior
                // style.setBorderRight(BorderStyle.THIN); // Borde derecho
                //style.setFillPattern(FillPatternType.SOLID_FOREGROUND); // Rellenar celdas     
                //style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index); // Establezca el color de fondo de la celda en azul claro
                style.setVerticalAlignment(VerticalAlignment.CENTER); // vertical
                style.setAlignment(HorizontalAlignment.CENTER); // horizontal
                style.setWrapText(true); // Especifica el ajuste de la palabra cuando no se puede mostrar el contenido de la celda
                Font font = libro.createFont();
                font.setFontName("Tahoma");
                font.setFontHeight((short) 160);
                //font.setBold(true);
                style.setFont(font);
                //fin estilo de cabecera

                // estilo de texto
                CellStyle style1 = libro.createCellStyle();
                //style1.setVerticalAlignment(VerticalAlignment.CENTER); // vertical
                //style1.setAlignment(HorizontalAlignment.CENTER); // horizontal
                style1.setWrapText(true); // Especifica el ajuste de la palabra cuando no se puede mostrar el contenido de la celda
                Font font1 = libro.createFont();
                font1.setFontName("Tahoma");
                font1.setFontHeight((short) 160);
                style1.setFont(font1);
                //fin estilo de texto

                try (FileOutputStream archivo = new FileOutputStream(archivoXLS)) {
                    Sheet hoja = libro.createSheet("ACTOS GRAVADOS");
                    hoja.setDisplayGridlines(true);
                    for (int f = 0; f < t.getRowCount(); f++) {
                        Row fila = hoja.createRow(f);
                        //fila.setHeight((short) 300);                   
                        for (int c = 0; c < t.getColumnCount(); c++) {
                            switch (c) {
                                case 0 ->
                                    hoja.setColumnWidth(c, 12800);
                                case 1 ->
                                    hoja.setColumnWidth(c, 21500);
                                case 2 ->
                                    hoja.setColumnWidth(c, 9550);
                                default -> {
                                }
                            }
                            Cell celda = fila.createCell(c);
                            if (f == 0) {
                                celda.setCellValue(t.getColumnName(c));
                                celda.setCellStyle(style);
                            }
                        }
                    }
                    int filaInicio = 1;
                    for (int f = 0; f < t.getRowCount(); f++) {
                        Row fila = hoja.createRow(filaInicio);
                        filaInicio++;
                        for (int c = 0; c < t.getColumnCount(); c++) {
                            Cell celda = fila.createCell(c);
                            celda.setCellStyle(style1);
                            switch (c) {
                                case 0 ->
                                    celda.setCellValue(Integer.parseInt(String.valueOf(t.getValueAt(f, c).toString())));
                                case 1 ->
                                    celda.setCellValue(String.valueOf(t.getValueAt(f, c)));
                                case 2 ->
                                    celda.setCellValue(Integer.parseInt(String.valueOf(t.getValueAt(f, c).toString())));
                                default -> {
                                }
                            }
                        }
                    }
                    libro.write(archivo);
                }
                Desktop.getDesktop().open(archivoXLS);
            } catch (IOException | NumberFormatException e) {
                throw e;
            }
        }
    }

    public void exportarExcelVentaCabecera(JTable t, JDialog jd) throws IOException {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Hoja de cálculo", "xls");
        chooser.setFileFilter(filter);
        chooser.setDialogTitle("Guardar archivo");
        chooser.setAcceptAllFileFilterUsed(false);
        if (chooser.showSaveDialog(jd) == JFileChooser.APPROVE_OPTION) {
            String ruta = chooser.getSelectedFile().toString().concat(".xls");
            try {
                File archivoXLS = new File(ruta);
                if (archivoXLS.exists()) {
                    archivoXLS.delete();
                }
                archivoXLS.createNewFile();
                Workbook libro = new HSSFWorkbook();

                // estilo de cabecera
                CellStyle style = libro.createCellStyle(); // objeto de estilo (cuerpo)
                style.setVerticalAlignment(VerticalAlignment.CENTER); // vertical
                style.setAlignment(HorizontalAlignment.CENTER); // horizontal
                style.setWrapText(true); // Especifica el ajuste de la palabra cuando no se puede mostrar el contenido de la celda
                Font font = libro.createFont();
                font.setFontName("Tahoma");
                font.setBold(true);
                font.setFontHeight((short) 160);
                style.setFont(font);
                //fin estilo de cabecera

                // estilo de texto
                CellStyle style1 = libro.createCellStyle();
                style1.setWrapText(true); // Especifica el ajuste de la palabra cuando no se puede mostrar el contenido de la celda
                Font font1 = libro.createFont();
                font1.setFontName("Tahoma");
                font1.setFontHeight((short) 160);
                style1.setFont(font1);
                //fin estilo de texto

                try (FileOutputStream archivo = new FileOutputStream(archivoXLS)) {
                    Sheet hoja = libro.createSheet("VENTA CABECERA");
                    hoja.setDisplayGridlines(true);
                    for (int f = 0; f < t.getRowCount(); f++) {
                        Row fila = hoja.createRow(f);
                        //fila.setHeight((short) 300);                   
                        for (int c = 0; c < t.getColumnCount(); c++) {
                            switch (c) {
                                case 0 ->
                                    hoja.setColumnWidth(c, 5000);
                                case 1 ->
                                    hoja.setColumnWidth(c, 10000);
                                case 2 ->
                                    hoja.setColumnWidth(c, 5000);
                                case 3 ->
                                    hoja.setColumnWidth(c, 5000);
                                case 4 ->
                                    hoja.setColumnWidth(c, 5000);
                                case 5 ->
                                    hoja.setColumnWidth(c, 3000);
                                case 6 ->
                                    hoja.setColumnWidth(c, 3000);
                                case 7 ->
                                    hoja.setColumnWidth(c, 3000);
                                case 8 ->
                                    hoja.setColumnWidth(c, 3000);
                                case 9 ->
                                    hoja.setColumnWidth(c, 5000);
                                default -> {
                                }
                            }
                            Cell celda = fila.createCell(c);
                            if (f == 0) {
                                celda.setCellValue(t.getColumnName(c));
                                celda.setCellStyle(style);
                            }
                        }
                    }
                    int filaInicio = 1;
                    for (int f = 0; f < t.getRowCount(); f++) {
                        Row fila = hoja.createRow(filaInicio);
                        filaInicio++;
                        for (int c = 0; c < t.getColumnCount(); c++) {
                            Cell celda = fila.createCell(c);
                            celda.setCellStyle(style1);
                            celda.setCellValue((String.valueOf(t.getValueAt(f, c).toString())));
                            switch (c) {
                                case 0 ->
                                    celda.setCellValue((String.valueOf(t.getValueAt(f, c).toString())));
                                case 1 ->
                                    celda.setCellValue((String.valueOf(t.getValueAt(f, c).toString())));
                                case 2 ->
                                    celda.setCellValue((String.valueOf(t.getValueAt(f, c).toString())));
                                case 3 ->
                                    celda.setCellValue((String.valueOf(t.getValueAt(f, c).toString())));
                                case 4 ->
                                    celda.setCellValue((String.valueOf(t.getValueAt(f, c).toString())));
                                case 5 ->
                                    celda.setCellValue(Integer.parseInt(String.valueOf(t.getValueAt(f, c).toString())));
                                case 6 ->
                                    celda.setCellValue(Integer.parseInt(String.valueOf(t.getValueAt(f, c).toString())));    
                                    case 7 ->
                                    celda.setCellValue(Integer.parseInt(String.valueOf(t.getValueAt(f, c).toString())));
                                    case 8 ->
                                    celda.setCellValue(Integer.parseInt(String.valueOf(t.getValueAt(f, c).toString())));
                                    case 9 ->
                                    celda.setCellValue((String.valueOf(t.getValueAt(f, c).toString())));

                                default -> {
                                }
                            }
                        }
                    }
                    libro.write(archivo);
                }
                Desktop.getDesktop().open(archivoXLS);
            } catch (IOException | NumberFormatException e) {
                throw e;
            }
        }
    }

    public void exportarExcelVentaDetalle(JTable t, JDialog jd) throws IOException {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Hoja de cálculo", "xls");
        chooser.setFileFilter(filter);
        chooser.setDialogTitle("Guardar archivo");
        chooser.setAcceptAllFileFilterUsed(false);
        if (chooser.showSaveDialog(jd) == JFileChooser.APPROVE_OPTION) {
            String ruta = chooser.getSelectedFile().toString().concat(".xls");
            try {
                File archivoXLS = new File(ruta);
                if (archivoXLS.exists()) {
                    archivoXLS.delete();
                }
                archivoXLS.createNewFile();
                Workbook libro = new HSSFWorkbook();

                // estilo de cabecera
                CellStyle style = libro.createCellStyle(); // objeto de estilo (cuerpo)
                //style.setBorderBottom(BorderStyle.THIN); // Borde inferior
                //style.setBorderLeft(BorderStyle.THIN); // Borde izquierdo
                //style.setBorderTop(BorderStyle.THIN); // Borde superior
                // style.setBorderRight(BorderStyle.THIN); // Borde derecho
                //style.setFillPattern(FillPatternType.SOLID_FOREGROUND); // Rellenar celdas     
                //style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index); // Establezca el color de fondo de la celda en azul claro
                style.setVerticalAlignment(VerticalAlignment.CENTER); // vertical
                style.setAlignment(HorizontalAlignment.CENTER); // horizontal
                style.setWrapText(true); // Especifica el ajuste de la palabra cuando no se puede mostrar el contenido de la celda
                Font font = libro.createFont();
                font.setFontName("Tahoma");
                font.setFontHeight((short) 160);
                //font.setBold(true);
                style.setFont(font);
                //fin estilo de cabecera

                // estilo de texto
                CellStyle style1 = libro.createCellStyle();
                //style1.setVerticalAlignment(VerticalAlignment.CENTER); // vertical
                //style1.setAlignment(HorizontalAlignment.CENTER); // horizontal
                style1.setWrapText(true); // Especifica el ajuste de la palabra cuando no se puede mostrar el contenido de la celda
                Font font1 = libro.createFont();
                font1.setFontName("Tahoma");
                font1.setFontHeight((short) 160);
                style1.setFont(font1);
                //fin estilo de texto

                try (FileOutputStream archivo = new FileOutputStream(archivoXLS)) {
                    Sheet hoja = libro.createSheet("VENTA DETALLE");
                    hoja.setDisplayGridlines(true);
                    for (int f = 0; f < t.getRowCount(); f++) {
                        Row fila = hoja.createRow(f);
                        //fila.setHeight((short) 300);                   
                        for (int c = 0; c < t.getColumnCount(); c++) {
                            switch (c) {
                                case 0 ->
                                    hoja.setColumnWidth(c, 4100);
                                case 1 ->
                                    hoja.setColumnWidth(c, 3000);
                                case 2 ->
                                    hoja.setColumnWidth(c, 3500);
                                case 3 ->
                                    hoja.setColumnWidth(c, 3300);
                                case 4 ->
                                    hoja.setColumnWidth(c, 3350);
                                case 5 ->
                                    hoja.setColumnWidth(c, 3350);
                                case 6 ->
                                    hoja.setColumnWidth(c, 2500);
                                case 7 ->
                                    hoja.setColumnWidth(c, 6000);
                                case 8 ->
                                    hoja.setColumnWidth(c, 6000);
                                case 9 ->
                                    hoja.setColumnWidth(c, 5000);
                                case 10 ->
                                    hoja.setColumnWidth(c, 5000);
                                default -> {
                                }
                            }
                            Cell celda = fila.createCell(c);
                            if (f == 0) {
                                celda.setCellValue(t.getColumnName(c));
                                celda.setCellStyle(style);
                            }
                        }
                    }
                    int filaInicio = 1;
                    for (int f = 0; f < t.getRowCount(); f++) {
                        Row fila = hoja.createRow(filaInicio);
                        filaInicio++;
                        for (int c = 0; c < t.getColumnCount(); c++) {
                            Cell celda = fila.createCell(c);
                            celda.setCellStyle(style1);
                            switch (c) {
                                case 0 ->
                                    celda.setCellValue((String.valueOf(t.getValueAt(f, c).toString())));
                                case 1 ->
                                    celda.setCellValue(String.valueOf(t.getValueAt(f, c)));
                                case 2 ->
                                    celda.setCellValue((String.valueOf(t.getValueAt(f, c).toString())));
                                case 3 ->
                                    celda.setCellValue(Integer.parseInt(String.valueOf(t.getValueAt(f, c).toString())));
                                case 4 ->
                                    celda.setCellValue(Integer.parseInt(String.valueOf(t.getValueAt(f, c).toString())));
                                case 5 -> {
                                    if (String.valueOf(t.getValueAt(f, c).toString()).isEmpty()) {
                                        celda.setCellValue(String.valueOf(""));
                                    } else {
                                        celda.setCellValue(Integer.parseInt(String.valueOf(t.getValueAt(f, c).toString())));
                                    }
                                }
                                case 6 ->
                                    celda.setCellValue(Integer.parseInt(String.valueOf(t.getValueAt(f, c).toString())));
                                case 7 ->
                                    celda.setCellValue((String.valueOf(t.getValueAt(f, c).toString())));
                                case 8 ->
                                    celda.setCellValue(Integer.parseInt(String.valueOf(t.getValueAt(f, c).toString())));
                                case 9 ->
                                    celda.setCellValue(Integer.parseInt(String.valueOf(t.getValueAt(f, c).toString())));
                                case 10 ->
                                    celda.setCellValue(Integer.parseInt(String.valueOf(t.getValueAt(f, c).toString())));

                                default -> {
                                }
                            }
                        }
                    }
                    libro.write(archivo);
                }
                Desktop.getDesktop().open(archivoXLS);
            } catch (IOException | NumberFormatException e) {
                throw e;
            }
        }
    }

}
