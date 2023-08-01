package Modelo;

public class Stock {
    
    private int cod;
    private double stock;
    private String user;

    //Constructor
    public Stock(int cod, double stock, String user) {
        this.cod = cod;
        this.stock = stock;
        this.user = user;
    }

    //Constructor Vacio
    public Stock() {
    }

    //Getter y Setter
    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public double getStock() {
        return stock;
    }

    public void setStock(double stock) {
        this.stock = stock;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
    
    
    
}
