
package baloncesto.Observador;

import baloncesto.Factoria.ContratoImpl;
import baloncesto.Factoria.FactoriaSimple;
import baloncesto.Singleton.FederacionImpl;
import java.util.*;
import edi.io.*;
import baloncesto.Estado.JugadorImpl;

public class MercadoImpl implements Observador {

    //Atributos
    private SortedSet<JugadorImpl> jugadores;
    private FactoriaSimple factoria;
    private int numeroMinimoJugadoresLibres = 0;
    String[] nombres = {"Pau", "Sergio", "Felipe", "Marc", "Juancho", "José Manuel",
        "Fernando", "Marcus", "Luka", "Rudy", "Victor", "Lebron", "Stephen", "Michael", "Kobe", "Ricky", "Juan Carlos", "Jorge", "Gustavo", "Facundo", "Jaycee", "Alex", "Serge", "Andrés", "Fabien"};
    String[] apellidos = {"Gasol", "Llull", "Reyes", "Rodriguez", "Hernangomez", "Calderon",
        "San Emeterio", "Slaughter", "Doncic", "Fernandez", "Claver", "James", "Curry", "Jordan", "Bryant", "Rubio", "Navarro", "Garbajosa", "Ayon", "Campazzo", "Carroll", "Abrines", "Ibaka", "Nocioni", "Causeur"};
    
    //Constructor
    public MercadoImpl(FactoriaSimple factoria) {
        jugadores = new TreeSet<JugadorImpl>();
        this.factoria = factoria;
        numeroMinimoJugadoresLibres = inicializarMercado() / 2;

    }

    
    //Metodo con el que inizializaremos el mercado con los jugadores que indiquemos, este proceso sera aleatorio
    public int inicializarMercado() {

        System.out.println("Introduca el numero de jugadores que desee que haya en el mercado:");
        int i = (int) IO.readNumber();

        for (int j = 0; j < i; j++) {
            String nombre, apellido, dni = "", posicion = "";
            int edad, calidad;
            double precio;
            for (int k = 0; k < 8; k++) {
                dni += (int) (Math.random() * 10);
            }
            int ramdon = (int) (Math.random() * 26);
            char letraDni = 'a';
            for (int c = 0; c < ramdon; c++) {
                letraDni++;
            }
            dni += ("" + letraDni).toUpperCase();

            nombre = nombres[(int) (Math.random() * 11)];
            apellido = apellidos[(int) (Math.random() * 11)];

            int h = (int) (Math.random() * 5);
            switch (h) {
                case 0:
                    posicion = "base";
                    break;
                case 1:
                    posicion = "escolta";
                    break;
                case 2:
                    posicion = "alero";
                    break;
                case 3:
                    posicion = "ala-pivot";
                    break;
                case 4:
                    posicion = "pivot";
                    break;
            }
            edad = (int) (16 + Math.random() * 25);
            calidad = (int) (20 + Math.random() * 80);
            precio = (calidad -((100 - calidad)*calidad/100)) -(20*calidad/100);

            JugadorImpl jugador = new JugadorImpl(nombre, apellido, dni, edad, posicion, precio, calidad);
            añadirJugador(jugador);

        }
        return i;
    }

    //Añadimos jugador a la lista de jugadores, y le creamos el contrato
    public void añadirJugador(JugadorImpl jugador) {
        jugadores.add(jugador);
        jugador.setContrato(factoria.setContrato("", jugador, null));
    }

    public ContratoImpl realizarContrato(String tipo, JugadorImpl jugador, String equipo) {
        ContratoImpl con = null;
        con = factoria.setContrato(tipo, jugador, equipo);
        return con;
    }

    //Buscamos el jugador por el DNI que introduzcamos
    public JugadorImpl buscarJugador(String dni) {
        boolean encontrado = false;
        JugadorImpl jugador = null;
        Iterator it = jugadores.iterator();
        while (it.hasNext() && !encontrado) {
            JugadorImpl j = (JugadorImpl) it.next();
            if (j.equals(dni)) {
                encontrado = true;
                jugador = j;
            }
        }
        return jugador;
    }
    
    
    //Mostramos el mercado on todos los jugadores disponibles
    public void mostrarMercado() {
        System.out.println("¿Desea filtrar de alguna forma los jugadores?. Si/No.");
        String eleccion = IO.readLine();
        if (eleccion.equalsIgnoreCase("Si")) {
            filtrarMercado();
        } else {
            for (JugadorImpl jugador : jugadores) {
                System.out.println(jugador);
            }
        }
    }

    //Con este metodo realizamos filtros a la hora de buscar jugadores en el mercado
    public void filtrarMercado() {

        System.out.println("Indique la forma en la que quiere filtrar."
                + "\n\t1.Equipo."
                + "\n\t2.Precio."
                + "\n\t3.Calidad");
        int forma = (int) IO.readNumber();
        int eleccion;
        switch (forma) {
            case 1:
                System.out.println("Ha elegido filtrar por equipo.\n Indique que tipo de filtrado por equipo quiere."
                        + "\n\t1.Pertenece."
                        + "\n\t2.No pertenece.");
                eleccion = (int) IO.readNumber();
                System.out.println("Introduzca el nombre del equipo.");
                String equipo = IO.readLine();
                if (eleccion == 1) {
                    for (JugadorImpl jugador : jugadores) {
                        if (FederacionImpl.getInstance().buscarEquipo(equipo) != null) {
                            if (FederacionImpl.getInstance().buscarEquipo(equipo).buscarJugador(jugador.getDNI())) {
                                System.out.println(jugador);
                            }
                        }
                    }
                } else {
                    for (JugadorImpl jugador : jugadores) {
                        if (FederacionImpl.getInstance().buscarEquipo(equipo) != null) {
                            if (!FederacionImpl.getInstance().buscarEquipo(equipo).buscarJugador(jugador.getDNI())) {
                                System.out.println(jugador);
                            }
                        }
                    }
                }
                break;
            case 2:
                System.out.println("Ha elegido filtrar por precio.\n Indique que tipo de filtrado por precio quiere."
                        + "\n\t1.Maximo."
                        + "\n\t2.Minimo.");
                eleccion = (int) IO.readNumber();
                System.out.println("Introduzca un precio.");
                double precio = IO.readNumber();
                if (eleccion == 1) {
                    for (JugadorImpl jugador : jugadores) {
                        if (jugador.getPrecio() <= precio) {
                            System.out.println(jugador);
                        }
                    }
                } else {
                    for (JugadorImpl jugador : jugadores) {
                        if (jugador.getPrecio() >= precio) {
                            System.out.println(jugador);
                        }
                    }
                }
                break;
            case 3:
                System.out.println("Ha elegido filtrar por calidad.\n Indique que tipo de filtrado por calidad quiere."
                        + "\n\t1.Maximo."
                        + "\n\t2.Minimo.");
                eleccion = (int) IO.readNumber();
                System.out.println("Introduzca una calidad.");
                int calidad = (int) IO.readNumber();
                if (eleccion == 1) {
                    for (JugadorImpl jugador : jugadores) {
                        if (jugador.getCalidad() <= calidad) {
                            System.out.println(jugador);
                        }
                    }
                } else {
                    for (JugadorImpl jugador : jugadores) {
                        if (jugador.getCalidad() >= calidad) {
                            System.out.println(jugador);
                        }
                    }
                }
                break;
        }
    }

    //Este metodo lo utilizamos a la hora de los fichajes, para gestionar el tipo de contrato que se va a realizar
    public boolean update(JugadorImpl jugador, String equipo) {
        ContratoImpl con = null;
        String tipo;
        System.out.println("Indique el tipo de contrato que quiere hacer: (Reserva / Suplente / Titular)");
        tipo = IO.readLine();
        con = realizarContrato(tipo, jugador, equipo);
        jugador.setContrato(con);
        return true;
    }

    public SortedSet<JugadorImpl> getJugadores() {
        return jugadores;
    }
    //Metodos setter
    public void setJugadores(SortedSet<JugadorImpl> jugadores) {
        this.jugadores = jugadores;
    }

    public void setNumeroMinimoJugadores(int numeroMinimoJugadores) {
        this.numeroMinimoJugadoresLibres = numeroMinimoJugadores;
    }
    
    //Metodos getter
    public int getNumeroMinimoJugadoresLibres() {
        return numeroMinimoJugadoresLibres;
    }

    

    //Este metodo devolvemos el numero de jugadores libres que hay en el mercado actualmente
    public int numeroActualJugadoresLibres() {
        int cont = 0;
        for (JugadorImpl jugador : jugadores) {
            if (jugador.getEstado().getTipo().equals("Libre")) {
                cont++;
            }
        }
        return cont;
    }
    
    
    //Este metodo introduciremos nuevos jugadores si el mercado esta a niveles minimos, añadiendo jugadores automaticamente.
    public void rellenarMercado() {
        int i = (getNumeroMinimoJugadoresLibres() - numeroActualJugadoresLibres());
        for (int j = 0; j < i; j++) {
            String nombre, apellido, dni = "", posicion = "";
            int edad, calidad;
            double precio;
            for (int k = 0; k < 8; k++) {
                dni += (int) (Math.random() * 10);
            }
            int ramdon = (int) (Math.random() * 26);
            char letraDni = 'a';
            for (int c = 0; c < ramdon; c++) {
                letraDni++;
            }
            dni += ("" + letraDni).toUpperCase();

            nombre = nombres[(int) (Math.random() * 11)];
            apellido = apellidos[(int) (Math.random() * 11)];

            int h = (int) (Math.random() * 5);
            switch (h) {
                               case 0:
                    posicion = "base";
                    break;
                case 1:
                    posicion = "escolta";
                    break;
                case 2:
                    posicion = "alero";
                    break;
                case 3:
                    posicion = "ala-pivot";
                    break;
                case 4:
                    posicion = "pivot";
                    break;
            }
            edad = (int) (16 + Math.random() * 25);
            calidad = (int) (20 + Math.random() * 80);
            precio = (30 * calidad) / 100;

            JugadorImpl jugador = new JugadorImpl(nombre, apellido, dni, edad, posicion, precio, calidad);
            añadirJugador(jugador);
        }
    }

}
