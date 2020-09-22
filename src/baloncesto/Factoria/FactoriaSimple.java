
package baloncesto.Factoria;

import baloncesto.Estado.JugadorImpl;


public class FactoriaSimple {

    private ContratoImpl contrato;

    //Realizamos el contrato, dependiendo del tipo de jugador que es
    public ContratoImpl setContrato(String tipo, JugadorImpl jugador, String equipo) {
        contrato = null;
        if (tipo.equalsIgnoreCase("Titular")) {
            contrato = new TitularImpl(jugador, equipo);
        } else if (tipo.equalsIgnoreCase("Suplente")) {
            contrato = new SuplenteImpl(jugador, equipo);
        } else if (tipo.equalsIgnoreCase("Reserva")) {
            contrato = new ReservaImpl(jugador, equipo);
        } else {
            contrato = new AgenteLibreImpl(jugador, null);
        }
        return contrato;
    }

    //Devuelve el contrato
    public ContratoImpl getContrato() {
        return contrato;
    }

    //Devuelve el jugador
    public JugadorImpl getJugador() {
        return contrato.getJugador();
    }

}

