package baloncesto.Estado;

import baloncesto.Factoria.ContratoImpl;
import baloncesto.Estado.EstadoLibre;
import baloncesto.Estado.EstadoLesionado;
import java.util.*;
import baloncesto.Estado.Estado;

public class JugadorImpl implements Comparable {
    
    //Atributos de la clase JugadorImpl
    private String nombre;
    private String apellido;
    private int edad;
    private String dni;
    private String posicion;
    private double precio;
    private int calidad;
    private ContratoImpl contrato = null;
    private Estado estado;
    private static final double probLesionado = 0.2;

    //Constructor
    public JugadorImpl(String nombre, String apellido, String dni, int edad, String posicion, double precio, int calidad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.dni = dni;
        this.posicion = posicion;
        this.precio = precio;
        this.calidad = calidad;
        setEstado(estado);
    }

    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.dni);
        return hash;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final JugadorImpl other = (JugadorImpl) obj;
        if (!Objects.equals(this.dni, other.dni)) {
            return false;
        }
        return true;
    }

    //metodos getter
    
    public String getApellido() {
        return apellido;
    }
    
    public double getPrecio() {
        return precio;
    }

    
    public int getCalidad() {
        return calidad;
    }

    public void setCalidad(int calidad) {
        this.calidad = calidad;
    }

    public String getNombre() {
        return nombre;
    }
    
    public String getPosicion() {
        return posicion;
    }
    
    public Estado getEstado() {
        return estado;
    }

    public int getEdad() {
        return edad;
    }

    public String getDNI() {
        return dni;
    }
    
    public ContratoImpl getContrato() {
        return contrato;
    }

    //metodos setter
    public void setNombre(String nom) {
        nombre = nom;
    }
    
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setDNI(String dni) {
        this.dni = dni;
    }
    
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }


    public void setContrato(ContratoImpl contrato) {
        this.contrato = contrato;
    }
     
    public String toString() {
        return "Jugador: " + nombre + " " + apellido
                + "\n\tDni: " + dni + "\tEdad: " + edad + "\tPosicion: " + posicion
                + "\n\tCalidad/precio: " + calidad + " / " + precio + " M"
                + "\n\t" + contrato
                + "\n\tEstado: " + estado;
    }


    public void setEstado(Estado estado) {
        double random = Math.random();
        if (random <= probLesionado) {
            this.estado = new EstadoLesionado(precio);
        } else if (estado == null) {
            this.estado = new EstadoLibre(precio);
        } else {
            this.estado = estado;
        }

    }
    
    public boolean equals(String dni) {

        return dni.equals(this.dni);

    }
    
    //ComparaTo con el que comparamos el jugador dependiendo del nombre y el dni
    public int compareTo(Object t) {
        JugadorImpl e = (JugadorImpl) t;
        if (this.getNombre().compareTo(e.getNombre()) > 0) {
            return 1;
        } else if (this.getNombre().compareTo(e.getNombre()) < 0) {
            return -1;
        } else if (this.getDNI().compareTo(e.getDNI()) > 0) {
            return 1;
        } else if (this.getDNI().compareTo(e.getDNI()) < 0) {
            return -1;
        } else {
            return 0;
        }
    }

}
