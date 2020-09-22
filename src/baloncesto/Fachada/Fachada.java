package baloncesto.Fachada;

import baloncesto.Observador.EquipoImpl;
import baloncesto.Observador.MercadoImpl;
import baloncesto.Singleton.FederacionImpl;
import edi.io.IO;

public class Fachada {

    //Atributos
    FederacionImpl feb;
    public EquipoImpl equipo;
    MercadoImpl mercado;

    
    //Constructor
    public Fachada() {
        feb = FederacionImpl.getInstance();
        mercado = feb.getMercado();
    }

    //Añadimos equipo llamando a la clase federacion
    public boolean añadirEquipos() {
        return feb.añadirEquipos();
    }

    //Elegimos el equipo mediante el nombre
    public boolean elegirEquipo() {
        String nombre = "";
        if (!feb.getEquipos().isEmpty()) {
            verEquipos();
            System.out.println("Elija un equipo mediante su nombre.");
            nombre = (String) IO.readLine();
            equipo = feb.buscarEquipo(nombre);
            if (equipo != null) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    //Fichamos a un jugador 
    public boolean ficharJugador() {
        System.out.println("Procedemos ha fichar un jugador.");
        boolean fichado = equipo.ficharJugador();
        //Si el numero de jugadores en el mercado es muy bajo, se meteran nuevos bajadores
        if (mercado.getNumeroMinimoJugadoresLibres() > mercado.numeroActualJugadoresLibres()) {
            mercado.rellenarMercado();
        }
        return fichado;
    }

    
    public boolean venderJugador() {
        if (equipo.size() != 0) {
            System.out.println("Procedemos ha vender un jugador.");
            boolean vendido = equipo.venderJugador();
            return vendido;
        } else {
            System.out.println("No hay jugadores en el equipo.");
            return false;
        }
    }

    public boolean despedirJugador() {
        if (equipo.size() != 0) {
            System.out.println("Procedemos ha despedir a un jugador.");
            boolean despedido = equipo.rescindirContrato();
            return despedido;
        } else {
            System.out.println("No hay jugadores en el equipo.");
            return false;
        }
    }
    
    
    //Nos muestra un string con los jugadores actuales en el mercado de fichajes
    public void verMercado() {
        mercado.mostrarMercado();
    }

    public void verEquipos() {
        System.out.println(feb.toString());

    }

    //Nos mostrara un string con todos los jugadores del equipo
    public void verPlantilla() {
        if (equipo.size() != 0) {
            System.out.println(equipo.toString());
        } else {
            System.out.println("No hay jugadores en el equipo.");
        }
    }

}
