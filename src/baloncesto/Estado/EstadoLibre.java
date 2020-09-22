package baloncesto.Estado;

import baloncesto.Estado.Estado;

public class EstadoLibre implements Estado {

    //Atributos
    private double precioFichaje = 0;
    private String tipo = "Libre";

    public EstadoLibre(double precio) {
        setPrecioFichaje(precio);
    }

    public boolean fichable() {
        return true;
    }

    public void setPrecioFichaje(double precio) {

        //Fijamos nuevo precio dependiendo de la disminucion
        int disminucion = (int) (75 + Math.random() * 25);
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
        return tipo;
    }
}
