package baloncesto.Estado;

import baloncesto.Estado.Estado;

public class EstadoTransferible implements Estado {

    //Atributos de la clase JugadorImpl
    private double precioFichaje;
    private String tipo = "Transferible";

    public EstadoTransferible(double precio) {
        setPrecioFichaje(precio);
    }

    public boolean fichable() {
        return true;
    }

    //Fijamos el precio del fichaje, realizando una disminuacion
    public void setPrecioFichaje(double precio) {
        int disminucion = (int) (15 + Math.random() * 50);
        precioFichaje = precio - (precio * disminucion) / 100;
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
        return tipo + "\n";
    }

}
