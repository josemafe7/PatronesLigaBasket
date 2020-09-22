
package baloncesto.Singleton;

import edi.io.*;
import baloncesto.Observador.EquipoImpl;
import baloncesto.Factoria.FactoriaSimple;
import baloncesto.Observador.MercadoImpl;
import java.util.*;


public class FederacionImpl {

    //Atributos
    private String nombre = "Federación Española de Baloncesto (FEB)";
    private SortedSet<EquipoImpl> equipos;
    private MercadoImpl mercado;
    private FactoriaSimple factoria = new FactoriaSimple();
    private static FederacionImpl federacion = null;

    //Consctrutor privado
    private FederacionImpl() {
        mercado = new MercadoImpl(factoria);
        equipos = new TreeSet<EquipoImpl>();
    }

    //Con este metodo nos aseguramos que solo se cree una instancia de la clase FederacionImpl
    public static FederacionImpl getInstance() {
        if (federacion == null) {
            federacion = new FederacionImpl();
        }
        return federacion;
    }

   
    //Buscamos el equipo que se le pasa por su nombre
    public EquipoImpl buscarEquipo(String nombre) {
        boolean enc = false;
        EquipoImpl equipo = null;
        Iterator it = equipos.iterator();
        while (it.hasNext() && !enc) {
            EquipoImpl e = (EquipoImpl) it.next();
            if (e.getNombre().equalsIgnoreCase(nombre)) {
                equipo = e;
                enc = true;
            }
        }
        return equipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    //Añadimos equipos al sistema
    public boolean añadirEquipos() {

        System.out.println("Indique el numero de equipos que quiere añadir al sistema: ");
        int i;
        double presupuesto;
        String nombre;
        i = (int) IO.readNumber();
        int j = 0;
        while (j < i) {
            System.out.println("\n Indique el nombre del "+ (j+1) +" equipo: ");
            nombre = IO.readLine();

            System.out.println("Indique el presupuesto del "+ (j+1) +" equipo: ");
            presupuesto = (int) IO.readNumber();
            if (generarEquipo(nombre, presupuesto)) {
                j++;
            } else {
                System.out.println("El nombre del equipo ya esta añadido elija otro nombre.");
            }
        }
        return true;
    }

    //Añadimos equipo a la lista de equipos
    public boolean generarEquipo(String nom, double presupuesto) {
        EquipoImpl equipo = new EquipoImpl(nom, presupuesto);
        return equipos.add(equipo);
    }

    //Metodos getter
    public SortedSet<EquipoImpl> getEquipos() {
        return equipos;
    }
    
    public FactoriaSimple getFactoria() {
        return factoria;
    }
    
    public MercadoImpl getMercado() {
        return mercado;
    }

    //Metodos setter
    public void setEquipos(SortedSet<EquipoImpl> equipos) {
        this.equipos = equipos;
    }
    

    public void setMercado(MercadoImpl mercado) {
        this.mercado = mercado;
    }

    

    public void setFactoria(FactoriaSimple factoria) {
        this.factoria = factoria;
    }

    public String toString() {
        String cad = "";
        int i = 1;
        cad += "Federacion " + nombre + "\n";
        for (EquipoImpl equipo : equipos) {
            cad += i + ". " + equipo.getNombre() + "\n";
            i++;
        }
        return cad;
    }
}
