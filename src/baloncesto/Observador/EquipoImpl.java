
package baloncesto.Observador;

import baloncesto.Singleton.FederacionImpl;
import baloncesto.Estado.EstadoNoLista;
import baloncesto.Estado.EstadoTransferible;
import baloncesto.Estado.EstadoLibre;
import java.util.*;
import edi.io.*;
import baloncesto.Estado.JugadorImpl;
import baloncesto.Estado.Estado;


public class EquipoImpl implements Sujeto, Comparable {

    //Atributos
    private String nombre;
    private SortedSet<JugadorImpl> plantilla;
    private double presupuesto;
    private Set<Observador> observadores = new HashSet<Observador>();
    private FederacionImpl feb = FederacionImpl.getInstance();

    //Construtor
    public EquipoImpl(String nom, double presupuesto) {
        nombre = nom;
        this.presupuesto = presupuesto;
        observadores.add(feb.getMercado());
        plantilla = new TreeSet();
    }

    //Metodo con el que realizaremos el fichaje de un jugador
    public boolean ficharJugador() {
        boolean fichado = false;
        MercadoImpl m = feb.getMercado();
        m.mostrarMercado();
        boolean ok = false;
        JugadorImpl jugador;
        String dni = "";
        do {
            System.out.println("Elija un jugador mediante su DNI.");
            dni = IO.readLine();
            ok = dni.length() == 9;
            if (!ok) {
                System.out.println("DNI no valido");
            }
        } while (!ok);
        
        if (ok) {
            jugador = m.buscarJugador(dni);
            if (jugador.getEstado().fichable() && jugador.getEstado().getTipo().equals("Libre")) {
                System.out.println("El jugador es fichable." + " Su precio seria: "+ jugador.getEstado().getPrecioFichaje());
                notifyObserver(jugador);
                actualizarPresupuesto(-jugador.getEstado().getPrecioFichaje());
                plantilla.add(jugador);
                Estado estado = new EstadoNoLista(jugador.getPrecio());
                jugador.setEstado(estado);
                fichado = true;
            } else if (jugador.getEstado().fichable()) {
                EquipoImpl equipo = feb.buscarEquipo(jugador.getContrato().getEquipo());
                equipo.getPlantilla().remove(jugador);
                equipo.actualizarPresupuesto(jugador.getEstado().getPrecioFichaje());
                System.out.println("El jugador es fichable." + " Su precio seria: " + jugador.getEstado().getPrecioFichaje());
                notifyObserver(jugador);
                actualizarPresupuesto(-jugador.getEstado().getPrecioFichaje());
                plantilla.add(jugador);
                Estado estado = new EstadoNoLista(jugador.getPrecio());
                jugador.setEstado(estado);
                fichado = true;
            } else {
                System.out.println("No se ha podido fichar al jugador.");
            }
        }
        return fichado;
    }
    
    

    //Metodo con el que vendemos a un jugador
    public boolean venderJugador() {

        System.out.println(toString() + "\nElija un jugador mediante su dni.");
        String dni = IO.readLine();
        JugadorImpl jug = feb.getMercado().buscarJugador(dni);
        Estado estado = new EstadoTransferible(jug.getPrecio());
        jug.setEstado(estado);
        return true;
    }

    //Metodo con el que rescindimos el contrato de un jugador
    public boolean rescindirContrato() {
        System.out.println(toString() + "\nElija un jugador mediante su dni.");
        String dni = IO.readLine();
        JugadorImpl jug = feb.getMercado().buscarJugador(dni);
        Estado estado = new EstadoLibre(jug.getPrecio());
        jug.setEstado(estado);
        jug.setContrato(feb.getFactoria().setContrato("", jug, null));
        plantilla.remove(jug);
        return true;
    }

    //Metodo con el que realizamos una busqueda de un jugador a partir de su DNI
    public boolean buscarJugador(String dni) {
        boolean encontrado = false;

        Iterator it = plantilla.iterator();
        while (it.hasNext() && !encontrado) {
            JugadorImpl jug = (JugadorImpl) it.next();
            if (dni.equals(jug.getDNI())) {
                encontrado = true;
            }
        }
        return encontrado;
    }

    //Metodos getter
    public String getNombre() {
        return nombre;
    }

    public SortedSet<JugadorImpl> getPlantilla() {
        return plantilla;
    }
    
    public double getPresupuesto() {
        return presupuesto;
    }

    //Metodos setter
    public void setPlantilla(SortedSet<JugadorImpl> plantilla) {
        this.plantilla = plantilla;
    }

    public void setPresupuesto(double presupuesto) {
        this.presupuesto = presupuesto;
    }

    
    
    
    public void actualizarPresupuesto(double dinero) {
        presupuesto += dinero;
    }

    public boolean addObserver(Observador observador) {
        return observadores.add(observador);
    }

    public boolean removeObserver(Observador observador) {
        return observadores.remove(observador);
    }

    public boolean notifyObserver(JugadorImpl jugador) {
        boolean result = true;
        for (Observador observador : observadores) {
            result &= observador.update(jugador, nombre);
        }
        return result;
    }
    
    //Metodo que nos devuelve el tamaño de la plantilla
    public int size(){
        int con = 0;
        for(JugadorImpl jugador : plantilla){
            con++;
        }
        return con;
    }
    
    public String toString() {
        String cad = "";
        int i = 1;
        cad += "Equipo: " + nombre
                + "\n\tPresupuesto: " + presupuesto
                + "\n\tPlantilla: ";
        for (JugadorImpl jugador : plantilla) {
            cad += "\n";
            cad += "\n\t" + i + ". " + jugador;
            i++;
        }
        return cad;
    }

    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.nombre);
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
        final EquipoImpl other = (EquipoImpl) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        return true;
    }

    public int compareTo(Object t) {
        EquipoImpl e = (EquipoImpl) t;
        if (this.getNombre().compareTo(e.getNombre()) > 0) {
            return 1;
        } else if (this.getNombre().compareTo(e.getNombre()) < 0) {
            return -1;
        } else {
            return 0;
        }
    }

}
