/*Trabajo Patrones
*/


/**
 *
 * @author Daniel Barciela Rueda
 * @author José Manuel Fernández Labrador
 * @author Manuel Herrera Pulido
 * 
 */
package baloncesto;

import baloncesto.Fachada.Fachada;
import edi.io.*;

public class Baloncesto {

    public static void main(String[] args) {
        //Creamos la nueva fachada
        baloncesto.Fachada.Fachada f = new Fachada();
        
        int eleccion = 0;
        
        do {
            try {
                System.out.println("   ####################");
                System.out.println("   ## MENU PRINCIPAL ##"
                        +"\n   ####################"
                        + "\n ---------------------------"
                        + "\n|\t1.Añadir equipos.   |"
                        + "\n|\t2.Elegir equipo.    |"
                        + "\n|\t3.Ver equipos.      |"
                        + "\n|\t4.Gestionar equipo. |"
                        + "\n|\t0.Salir.            |"
                        +"\n ---------------------------"
                        + "\nEliga una opcion: ");
                eleccion = (int) IO.readNumber();
                switch (eleccion) {
                    //OPCION AÑADIR EQUIPOS
                    case 1:
                        
                        System.out.println("Has accedido a añadir equipos.");
                        if (f.añadirEquipos()) {
                            System.out.println("Los equipos han sidos añadidos correctamente.");
                        } else {
                            System.out.println("No  ha sido  posible, añadir los equipos al sistema.");
                        }
                        
                        break;
                    //OPCION ELEGIR EQUIPO
                    case 2:
                        
                        System.out.println("Has accedido a elegir un equipo.");
                        if (f.elegirEquipo()) {
                            System.out.println("El equipo ha sido elegido correctamente.");
                        } else {
                            System.out.println("No se ha podido elegir el equipo.");
                        }
                        
                        break;
                    //OPCION VER EQUIPOS
                    case 3:
                        
                        System.out.println("Ha accedido a ver equipos");
                        f.verEquipos();
                        
                        break;
                    //OPCION GESTIONAR EQUIPO
                    case 4:
                        
                        boolean elegir = true;
                        System.out.println("Has accedido a gestionar un equipo.");
                        if (f.equipo == null) {
                            System.out.println("Primero debes elegir un equipo");
                            if (!f.elegirEquipo()) {
                                System.out.println("No hay equipos actualmente en la federación");
                                elegir = false;
                            }else{
                                System.out.println("Equipo elegido correctamente.");
                            }
                        }
                        
                        if (elegir) {
                            int opcion = 0;
                            do {
                                try {
                                    System.out.println("   ################## ");
                                    System.out.println("   ## MENU EQUIPO  ## "
                                                      +"\n   ################## "
                                            + "\n -----------------------"
                                            + "\n|\t1.Fichar jugador.   |"
                                            + "\n|\t2.Vender jugador.   |"
                                            + "\n|\t3.Despedir jugador. |"
                                            + "\n|\t4.Ver mercado.      |"
                                            + "\n|\t5.Ver plantilla.    |"
                                            + "\n|\t0.Salir.            |"
                                            + "\n ------------------------"
                                            + "\nEliga una opcion: ");
                                    opcion = (int) IO.readNumber();
                                    switch (opcion) {
                                        //OPCION FICHAR JUGADOR
                                        case 1:
                                            System.out.println("Ha accedido a fichar a un jugador.");
                                            if (f.ficharJugador()) {
                                                System.out.println("El fichaje se ha realizado con exito");
                                            } else {
                                                System.out.println("El fichaje no se ha podido llevar acabo.");
                                            }
                                            break;
                                        //OPCION VENDER JUGADOR
                                        case 2:
                                            
                                            System.out.println("Va  a vender un jugador.");
                                            if (f.venderJugador()) {
                                                System.out.println("El jugador se ha puesto a la venta");
                                            } else {
                                                System.out.println("No se ha podido vender al jugador");
                                            }
                                            break;
                                        //OPCION DESPEDIR JUGADOR 
                                        case 3:
                                            
                                            System.out.println("Ha accedido a despedir un jugador.");
                                            if (f.despedirJugador()) {
                                                System.out.println("El despido ha sido un exito");
                                            } else {
                                                System.out.println("El despido no ha podido llevarse acabo");
                                            }
                                            break;
                                        //OPCION VER MERCADO
                                        case 4:
                                            
                                            System.out.println("Ha accedido a ver el mercado.");
                                            f.verMercado();
                                            break;
                                        //OPCION VER PLANTILLA
                                        case 5:
                                            
                                            System.out.println("Ha accedido a ver la plantilla.");
                                            f.verPlantilla();
                                            break;
                                        //OPCION PARA MENU SUPERIOR
                                        case 0:
                                            
                                            System.out.println("Cerrando Menu Equipo");
                                            break;
                                            
                                        default:
                                            
                                            System.out.println("Opcion erronea.");
                                            break;
                                    }
                                } catch (NumberFormatException e) {
                                    System.out.println(e.getMessage());
                                }

                            } while (opcion != 0);
                        }
                        break;

                    case 0:
                        
                        System.out.println("Cerrando el programa.");
                        break;
                        
                    default:
                        
                        System.out.println("Opcion incorrecta.");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }

        } while (eleccion != 0);

    }
}
