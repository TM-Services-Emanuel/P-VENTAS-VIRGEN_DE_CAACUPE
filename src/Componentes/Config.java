/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Componentes;

public class Config {

    /**
     * @return the Server
     */
    public static String getServer() {
        return Server;
    }

    /**
     * @param aServer the Server to set
     */
    public static void setServer(String aServer) {
        Server = aServer;
    }

    private static String Server = "";
    
    /**
     * @return the Port
     */
    public static String getPort() {
        return Port;
    }

    /**
     * @param aPort the Port to set
     */
    public static void setPort(String aPort) {
        Port = aPort;
    }

    private static String Port = "";
    
    /**
     * @return the User
     */
    public static String getUser() {
        return User;
    }

    /**
     * @param aUser the User to set
     */
    public static void setUser(String aUser) {
        User = aUser;
    }

    private static String User = "";
    
    /**
     * @return the Password
     */
    public static String getPassword() {
        return Password;
    }

    /**
     * @param aPassword the Password to set
     */
    public static void setPassword(String aPassword) {
        Password = aPassword;
    }

    private static String Password = "";
    
    
    /**
     * @return the BD
     */
    public static String getBD() {
        return BD;
    }

    /**
     * @param aBD the BD to set
     */
    public static void setBD(String aBD) {
        BD = aBD;
    }

    private static String BD = "";
    
    /**
     * @return the BDM
     */
    public static String getBDM() {
        return BDM;
    }

    /**
     * @param aBDM the BDM to set
     */
    public static void setBDM(String aBDM) {
        BDM = aBDM;
    }

    private static String BDM = "";

}
