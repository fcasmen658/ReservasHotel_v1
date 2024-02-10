package org.iesalandalus.programacion.reservashotel.negocio;

import org.iesalandalus.programacion.reservashotel.dominio.Habitacion;
import javax.naming.OperationNotSupportedException;

public class Habitaciones {
    private int capacidad = 10;
    private int tamano;
    private final Habitacion[] coleccionHabitaciones;

    public Habitaciones() {
        this.coleccionHabitaciones = new Habitacion[0];
        this.capacidad = 0;
        this.tamano = 0;
    }
    public Habitaciones(int capacidad) {
        if (capacidad < 1)
            throw new IllegalArgumentException("ERROR: La capacidad de la habitación no puede ser inferior a 1.");
        this.coleccionHabitaciones = new Habitacion[capacidad];
        this.capacidad = capacidad;
        this.tamano = 0;
    }

    public Habitacion[] get() {
        return copiaProfundaHabitaciones();
    }
    private Habitacion[] copiaProfundaHabitaciones() {
        Habitacion[] copia = new Habitacion[getCapacidad()];
        for (int i = 0; i < getCapacidad(); i++)
            copia[i] = new Habitacion(coleccionHabitaciones[i]);

        return copia;
    }
    public int getCapacidad() {
        return capacidad;
    }
    public int getTamano() {
        return tamano;
    }
    public void insertar(Habitacion habitacion) throws OperationNotSupportedException {
        if (habitacion == null)
            throw new NullPointerException("ERROR: La habitación no puede ser nula.");
//TODO        if (buscarIndice(habitacion.getIdentificador())
//TODO            throw new IllegalArgumentException("ERROR: La habitación ya existe.");
        if (tamano >= getCapacidad())
            throw new OperationNotSupportedException("ERROR: Se ha excedido la capacidad de la habitación.");
        coleccionHabitaciones[tamano] = new Habitacion(habitacion);
        tamano++;
    }
    private int buscarIndice(Habitacion habitacion) {
        if (habitacion == null)
            throw new NullPointerException("ERROR: El índice de la  habitación no puede ser nulo.");
        int indice = -1;
        for (int i = 0; i < getTamano(); i++)
            if (habitacion.equals(coleccionHabitaciones[i]))
                indice = i;
        return indice;
    }
    private boolean tamanoSuperado(int indice) {
        if (indice >= tamano)
            return true;
        return false;
        }
    private boolean capacidadSuperada(int indice) {
        if (indice >= capacidad)
            return true;
        return false;
        }
    public Habitacion buscar(Habitacion habitacion) {
        if (habitacion == null)
            throw new NullPointerException("ERROR: La habitación a buscar no puede ser nula.");
        int busqueda = buscarIndice(habitacion);
        if (busqueda == -1)
            return null;
        return habitacion;
    }
    public void borrar(Habitacion habitacion) throws OperationNotSupportedException {
        if (habitacion == null)
            throw new NullPointerException("ERROR: No se puede borrar una habitación nula.");
        if (buscar(habitacion) == null)
            throw new IllegalArgumentException("ERROR: La habitación a buscar es nula.");
        int indiceBorrado = buscarIndice(habitacion);
        coleccionHabitaciones[indiceBorrado] = null;
        desplazarUnaPosicionHaciaIzquierda(indiceBorrado);
        tamano--;
    }
    private void desplazarUnaPosicionHaciaIzquierda(int indice) throws OperationNotSupportedException {
        if (indice < 0 || indice > capacidad)
            throw new OperationNotSupportedException("ERROR: Al desplazar a la izquierda.");
        for (int i = indice; i < getTamano(); i++)
            coleccionHabitaciones[i] = new Habitacion(coleccionHabitaciones[i+1]);
        coleccionHabitaciones[getTamano()] = null;
    }
}
