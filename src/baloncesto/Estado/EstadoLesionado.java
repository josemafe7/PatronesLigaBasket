package baloncesto.Estado;

import baloncesto.Estado.Estado;

public class EstadoLesionado implements Adapter {

     //Atributos de la clase JugadorImpl
    private double precioFichaje = 0;
    private String tipo = "Lesionado";
    private int tiempoLesion;

    public EstadoLesionado(double precio) {
        setPrecioFichaje(precio);
        tiempoLesion=(int)(Math.random()*100)+1;
    }

    public boolean fichable() {
        return false;
    }

     //Fijamos el precio del fichaje
    public void setPrecioFichaje(double precio) {

        this.precioFichaje = precio;
    }

    public double getPrecioFichaje() {
        return precioFichaje;
    }
    
    

    public int getTiempoLesion() {
        return tiempoLesion;
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
