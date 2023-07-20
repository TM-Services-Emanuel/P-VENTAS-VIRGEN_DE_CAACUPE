/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Componentes;

public class Login {
    
    /**
     * @return the IDLogueado
     */
    public static String getIDUsuarioLogueado() {
        return UsuarioIDLogueado;
    }

    /**
     * @param aIDUsuarioLogueado the UsuarioLogueado to set
     */
    public static void setIDUsuarioLogueado(String aIDUsuarioLogueado) {
        UsuarioIDLogueado = aIDUsuarioLogueado;
    }
    
    private static String UsuarioIDLogueado="";

    /**
     * @return the UsuarioLogueado
     */
    public static String getUsuarioLogueado() {
        return UsuarioLogueado;
    }

    /**
     * @param aUsuarioLogueado the UsuarioLogueado to set
     */
    public static void setUsuarioLogueado(String aUsuarioLogueado) {
        UsuarioLogueado = aUsuarioLogueado;
    }
    
    private static String UsuarioLogueado="";
    
    /**
     * @return the ContraseñaLogueado
     */
    public static String getContraLogueado() {
        return ContraLogueado;
    }

    /**
     * @param aContraLogueado the UsuarioLogueado to set
     */
    public static void setContraLogueado(String aContraLogueado) {
        ContraLogueado = aContraLogueado;
    }
    
    private static String ContraLogueado="";
    
    /**
     * @return the PerfilLogueado
     */
    public static String getPerfil() {
        return Perfil;
    }
    
    /**
     * @param aPerfil the UsuarioLogueado to set
     */

    public static void setPerfil(String aPerfil) {
        Perfil = aPerfil;
    }
    
    private static String Perfil="";
    
    
    
}
