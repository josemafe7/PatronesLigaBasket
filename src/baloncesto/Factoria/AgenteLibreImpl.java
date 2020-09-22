
package baloncesto.Factoria;

import baloncesto.Estado.JugadorImpl;

public class AgenteLibreImpl extends ContratoImpl {

    //Atributo
    public static final String tipo = "Agente Libre";

    //Constructor que llama a la clase padre ContratoImpl
    public AgenteLibreImpl(JugadorImpl jugador, String equipo) {
        super(jugador, equipo);
    }

    public String toString() {
        return "Tipo Contrato: " + tipo;
    }

}
