/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Componentes;

public class Tickets {

    /**
     * @return the idEmision
     */
    public static int getIdEmision() {
        return IdEmision;
    }

    /**
     * @param aIdEmision the IdEmision to set
     */
    public static void setIdEmision(int aIdEmision) {
        IdEmision = aIdEmision;
    }
    private static int IdEmision;
    
    /**
     * @return the Establecimiento
     */
    public static String getEstablecimiento() {
        return Establecimiento;
    }

    /**
     * @param aEstablecimiento the Establecimiento to set
     */
    public static void setEstablecimiento(String aEstablecimiento) {
        Establecimiento = aEstablecimiento;
    }

    private static String Establecimiento = "";

    /**
     * @return the PuntoExpedicion
     */
    public static String getPuntoExpedicion() {
        return PuntoExpedicion;
    }

    /**
     * @param aPuntoExpedicion the PuntoEmision to set
     */
    public static void setPuntoExpedicion(String aPuntoExpedicion) {
        PuntoExpedicion = aPuntoExpedicion;
    }

    private static String PuntoExpedicion = "";

    /**
     * @return the Impresora
     */
    public static String getImpresora() {
        return Impresora;
    }

    /**
     * @param aImpresora the Impresora to set
     */
    public static void setImpresora(String aImpresora) {
        Impresora = aImpresora;
    }

    private static String Impresora = "";

    /**
     * @return the Habilitado
     */
    public static String getHabilitado() {
        return Habilitado;
    }

    /**
     * @param aHabilitado the Habilitado to set
     */
    public static void setHabilitado(String aHabilitado) {
        Habilitado = aHabilitado;
    }

    private static String Habilitado = "";

}
