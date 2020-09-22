
package baloncesto.Factoria;

import baloncesto.Estado.JugadorImpl;


public class ReservaImpl extends ContratoImpl{

    public static final String tipo = "Reserva";
    public static final int sueldo = 300;

    public ReservaImpl(JugadorImpl jugador, String equipo) {
        super(jugador, equipo);
    }

    public String toString() {
        return "Tipo contrato: " + tipo + "\n\tVinculado: " + equipo + "\tSueldo: " + sueldo;
    }
}
