
package baloncesto.Factoria;

import baloncesto.Estado.JugadorImpl;

public class SuplenteImpl extends ContratoImpl {

    public static final String tipo = "Suplente";
    public static final int sueldo = 800;

    public SuplenteImpl(JugadorImpl jugador, String equipo) {
        super(jugador, equipo);
    }

    public String toString() {
        return "Tipo contrato: " + tipo + "\n\tVinculado: " + equipo + "\tSueldo: " + sueldo;
    }

}

