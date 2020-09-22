
package baloncesto.Observador;

import baloncesto.Estado.JugadorImpl;

public interface Sujeto {

    boolean addObserver(Observador observador);
    boolean removeObserver(Observador observador);
    boolean notifyObserver(JugadorImpl jugador);
}
