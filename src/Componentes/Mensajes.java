package Componentes;

import static Controladores.controlFactura.sumaSTDEp;
import Datos.GestionarArticulos;
import Datos.GestionarArticulosMovil;
import IU.dlgVentas;
import Modelo.Articulo;
import Modelo.ArticuloMovil;
import java.awt.Color;
import java.awt.HeadlessException;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Mensajes {

    public static void informacion(String mensaje)//Mensaje de informacion
    {
        JOptionPane.showMessageDialog(null, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
//        //Necesario
//        UIManager UI=new UIManager(); 
//        //Borde
//        UI.put("OptionPane.background", Color.blue); 
//        //Fondo
//        UI.put("Panel.background", Color.red); 
//        //Lanzar el Joptionpane
//        JOptionPane.showMessageDialog(null,mensaje,"Titulo del Cuadro",JOptionPane.INFORMATION_MESSAGE); 
    }

    public static void Alerta(String mensaje)//Mensaje de informacion
    {
        JOptionPane.showMessageDialog(null, mensaje, "Información", JOptionPane.WARNING_MESSAGE);
//        //Necesario
//        UIManager UI=new UIManager(); 
//        //Borde
//        UI.put("OptionPane.background", Color.blue); 
//        //Fondo
//        UI.put("Panel.background", Color.red); 
//        //Lanzar el Joptionpane
//        JOptionPane.showMessageDialog(null,mensaje,"Titulo del Cuadro",JOptionPane.INFORMATION_MESSAGE); 
    }

    public static void Sistema(String mensaje)//Mensaje de informacion
    {
        JOptionPane.showMessageDialog(null, mensaje, "Sistema P-VENTA", JOptionPane.INFORMATION_MESSAGE);
//        //Necesario
        // UIManager UI=new UIManager(); 
//        //Borde
        //UIManager.put("OptionPane.background", Color.blue); 
//        //Fondo
        // UIManager.put("Panel.background", Color.red); 
//        //Lanzar el Joptionpane
        //JOptionPane.showMessageDialog(null,mensaje,"Sistema P-VENTA",JOptionPane.INFORMATION_MESSAGE); 
    }

    public static void error(String mensaje)//Mensaje de Error
    {
        JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static int confirmar(String mensaje)//Mensaje de confirmacion
    {
        int res = JOptionPane.showConfirmDialog(null, mensaje, "Confirmar", 0, JOptionPane.OK_CANCEL_OPTION);
        return res;
    }

    public static int confirmar2(String mensaje)//Mensaje de confirmacion
    {
        int res = JOptionPane.showConfirmDialog(null, mensaje, "Emisión de Ticket", 0, JOptionPane.OK_CANCEL_OPTION);
        return res;
    }

    public static int confirmar3(String mensaje)//Mensaje de confirmacion
    {
        int res = JOptionPane.showConfirmDialog(null, mensaje, "Emisión de Factura Legal", 0, JOptionPane.OK_CANCEL_OPTION);
        return res;
    }

    public static double ingresarNumerosV(String cod, double ca)//JoptionPane que solo acepte numeros
    {
        double numero = ca;
        try {
            ArticuloMovil Ar = GestionarArticulosMovil.busArticulo(cod);
            numero = Double.parseDouble(JOptionPane.showInputDialog("Ingrese la nueva cantidad", ca));
            double StockDepencencia = Ar.getStock();
            double StockT = sumaSTDEp(cod);
            if ((StockT + numero) > StockDepencencia) {
                numero = Double.parseDouble(JOptionPane.showInputDialog("La cantidad que estas intentando vender supera el stock actual del producto.\nIngrese la nueva cantidad", (StockDepencencia - StockT)));
                //Mensajes.error("ERROR:\nLa cantidad que estas intentando vender supera el stock actual del producto.");
            } else if ((StockT + numero) <= 0) {
                // Mensajes.error("ERROR:\nAcabas de ingresar un número que dara como resultado: " + (StockT + Double.parseDouble(dlgVentas.txtCant.getText().trim())));
                numero = Double.parseDouble(JOptionPane.showInputDialog("Cant. no válido.\nIngrese la nueva cantidad", ca));
            }

            /*if (numero <= 0) {
                    numero = Double.parseDouble(JOptionPane.showInputDialog("Cant. no válido.\nIngrese la nueva cantidad", ca));
                }else if(numero > Ar.getStock()) {
                    numero = Double.parseDouble(JOptionPane.showInputDialog("Stock insuficiente.\nIngrese la nueva cantidad", Ar.getStock()));
                    //bandera = true;
                }*/
        } catch (HeadlessException | NumberFormatException e) {
            //informacion("Solo se permiten números");
            //bandera = false;
        }
        return numero;
    }

    public static double ingresarNumerosC(double ca)//JoptionPane que solo acepte numeros
    {
        double numero = ca;
        //boolean bandera = false;
        //do
        {
            try {
                numero = Double.valueOf(JOptionPane.showInputDialog("Ingrese la nueva cantidad", ca));
                if (numero <= 0) {
                    numero = Double.valueOf(JOptionPane.showInputDialog("Ingrese la nueva cantidad", ca));
                }
            } catch (HeadlessException | NumberFormatException e) {
                //informacion("Solo se permiten números");
                //bandera = false;
            }
        }//while(!bandera);
        return numero;
    }

    public static int ingresarPrecioC(int pre)//JoptionPane que solo acepte numeros
    {
        int precio = pre;
        //boolean bandera = false;
        //do
        {
            try {
                precio = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el nuevo precio", pre));
                if (precio <= 0) {
                    precio = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el nuevo precio", pre));
                }
            } catch (HeadlessException | NumberFormatException e) {
                //informacion("Solo se permiten números");
                //bandera = false;
            }
        }//while(!bandera);
        return precio;
    }

    public static int ingresarNumeros()//JoptionPane que solo acepte numeros
    {
        int numero = 0;
        boolean bandera = false;
        do {
            try {
                numero = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la nueva cantidad", 1));
                if (numero <= 0) {
                    numero = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la nueva cantidad", 1));
                } else {
                    int x = dlgVentas.tbDetalle.getSelectedRow();
                    String cod = dlgVentas.tbDetalle.getValueAt(x, 0).toString();
                    Articulo Ar = GestionarArticulos.busArticulo(cod);
                    if (numero > Ar.getStock()) {
                        numero = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la nueva cantidad", Ar.getStock()));
                    }
                    bandera = true;
                }
            } catch (HeadlessException | NumberFormatException e) {
                informacion("Solo se permiten números");
                bandera = false;
            }
        } while (!bandera);
        return numero;
    }

    public static double ingresarDecimales()//JoptionPane que solo acepte decimales
    {
        double numero = 0;
        boolean bandera = false;
        do {
            try {
                numero = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el nuevo precio"));
                bandera = true;
            } catch (HeadlessException e) {
                informacion("Solo se permiten números");
                bandera = false;
            } catch (NumberFormatException e) {
                informacion("Solo se permiten números");
                bandera = false;
            }
        } while (!bandera);
        return numero;
    }

}
