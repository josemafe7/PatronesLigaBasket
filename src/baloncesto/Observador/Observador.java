
package baloncesto.Observador;

import baloncesto.Estado.JugadorImpl;

public interface Observador {
    
    boolean update(JugadorImpl jugador, String equipo);
}
