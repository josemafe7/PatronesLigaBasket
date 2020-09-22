
package baloncesto.Factoria;

import baloncesto.Estado.JugadorImpl;


public class TitularImpl extends ContratoImpl {

    public static final String tipo = "Titular";
    public static final int sueldo = 1200;

    public TitularImpl(JugadorImpl jugador, String equipo) {
        super(jugador, equipo);
    }

    public String toString() {
        return "Tipo contrato: " + tipo + "\n\tVinculado: " + equipo + "\tSueldo: " + sueldo;
    }

}
