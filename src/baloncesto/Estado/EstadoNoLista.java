package baloncesto.Estado;

import baloncesto.Estado.Estado;

public class EstadoNoLista implements Estado {

     //Atributos de la clase JugadorImpl
    private double precioFichaje;
    private String tipo = "No esta en la lista";

    public EstadoNoLista(double precio) {
        setPrecioFichaje(precio);
    }

    public boolean fichable() {
        return true;
    }

    //Fijamos nuevo precio dependiendo de un aumento
    public void setPrecioFichaje(double precio) {
        int aumento = (int) (15 + Math.random() * 35);
        precioFichaje = precio + (precio * aumento) / 100;
    }

    
    
    public double getPrecioFichaje() {
        return precioFichaje;
    }
      public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String toString() {
        return tipo;
    }

}
