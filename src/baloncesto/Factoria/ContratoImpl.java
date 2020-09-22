
package baloncesto.Factoria;

import baloncesto.Estado.JugadorImpl;


public abstract class ContratoImpl {

    protected JugadorImpl jugador;
    protected String equipo;

    public ContratoImpl(JugadorImpl jugador, String equipo) {
        this.jugador = jugador;
        setEquipo(equipo);
    }

    //Metodos getter
    public JugadorImpl getJugador() {
        return jugador;
    }

    public String getEquipo() {
        return equipo;
    }

    //Metodos setter
    public void setEquipo(String equipo) {
        
        this.equipo = equipo;
    }
    
    

    public boolean equals(Object o) {
        return jugador.equals((JugadorImpl) o);
    }
}

