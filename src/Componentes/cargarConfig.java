package Componentes;

import IU.frmCargaInicial;
import java.io.*;

public class cargarConfig {

    Object[] lineas; // arreglo que almacenará las líneas de tu archivo

    public void contarLineas() {
        try {
            if (lineas.length != 0) {
                System.out.println("Este archivo tiene " + lineas.length + " lineas");
            } else {
                System.out.println("Documento vacío.");
            }
        } catch (Exception e) {
        }

    }

    public void cargarArchivo(String archivo) {
        try {
            FileReader fr = new FileReader(archivo);
            BufferedReader br = new BufferedReader(fr);

            lineas = br.lines().toArray(); // inicializa el arreglo con las líneas de tu archivo

            // Se cierran los lectores puesto que ya no se necesitarán
            br.close();
            fr.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void leerArchivo() {
        try {
            for (int i = 0; i < lineas.length; i++) {
                switch (i) {
                    case 0 ->
                        Config.setServer((String) lineas[i]);
                    case 1 ->
                        Config.setPort((String) lineas[i]);
                    case 2 ->
                        Config.setUser((String) lineas[i]);
                    case 3 -> {
                        Config.setPassword((String) lineas[i]);
                    }
                    case 4 ->
                        Config.setBD((String) lineas[i]);
                    case 5 ->
                        Config.setBDM((String) lineas[i]);
                    default -> {
                    }
                }

            }
        } catch (Exception e) {
        }

    }

    public static void main(String[] args) {
        cargarConfig me = new cargarConfig();
        me.cargarArchivo("C:\\P-VENTAS\\Config.properties");
        me.contarLineas();
        me.leerArchivo();

        java.awt.EventQueue.invokeLater(() -> {
            new frmCargaInicial().setVisible(true);
        });
    }
}
