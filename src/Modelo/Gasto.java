package Modelo;

public class Gasto {

    private int caja_ca_id;
    private String ga_fecha;
    private int motivo_g;
    private int otorgado;
    private int ga_importe;
    private String gaObservacion;
    private String usu;
    private String tipo;

    /*Constructo vacio*/
    public Gasto() {
    }

    /*Constructor Lleno*/

    public Gasto(int caja_ca_id, String ga_fecha, int motivo_g, int otorgado, int ga_importe, String gaObservacion, String usu, String tipo) {
        this.caja_ca_id = caja_ca_id;
        this.ga_fecha = ga_fecha;
        this.motivo_g = motivo_g;
        this.otorgado = otorgado;
        this.ga_importe = ga_importe;
        this.gaObservacion = gaObservacion;
        this.usu = usu;
        this.tipo = tipo;
    }
    

    /*Getter y Setter*/

    public int getCaja_ca_id() {
        return caja_ca_id;
    }

    public void setCaja_ca_id(int caja_ca_id) {
        this.caja_ca_id = caja_ca_id;
    }

    public String getGa_fecha() {
        return ga_fecha;
    }

    public void setGa_fecha(String ga_fecha) {
        this.ga_fecha = ga_fecha;
    }

    public int getMotivo_g() {
        return motivo_g;
    }

    public void setMotivo_g(int motivo_g) {
        this.motivo_g = motivo_g;
    }

    public int getOtorgado() {
        return otorgado;
    }

    public void setOtorgado(int otorgado) {
        this.otorgado = otorgado;
    }

    public int getGa_importe() {
        return ga_importe;
    }

    public void setGa_importe(int ga_importe) {
        this.ga_importe = ga_importe;
    }

    public String getGaObservacion() {
        return gaObservacion;
    }

    public void setGaObservacion(String gaObservacion) {
        this.gaObservacion = gaObservacion;
    }

    public String getUsu() {
        return usu;
    }

    public void setUsu(String usu) {
        this.usu = usu;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    

}
