package org.iesalandalus.programacion.reservashotel.negocio;

import org.iesalandalus.programacion.reservashotel.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.dominio.Reserva;
import org.iesalandalus.programacion.reservashotel.dominio.TipoHabitacion;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Reservas {
    private final Reserva[] coleccionReservas;
    private final int capacidad;
    private int tamano;
    public Reservas(int capacidad) {
        if (capacidad < 0)
            throw new IllegalArgumentException("ERROR: La capacidad de la habitación no puede ser negativa.");
        this.coleccionReservas = new Reserva[capacidad];
        this.capacidad = capacidad;
        this.tamano = 0;
    }
    public Reserva[] get() {
        return copiaProfundaReservas();
    }
    private Reserva[] copiaProfundaReservas() {
        Reserva[] copia = new Reserva[getCapacidad()];
        for (int i = 0; i < getCapacidad(); i++)
            copia[i] = new Reserva(coleccionReservas[i]);
        return copia;
    }
    public int getCapacidad() {
        return capacidad;
    }
    public int getTamano() {
        return tamano;
    }
    public void insertar(Reserva reserva) throws OperationNotSupportedException {
        Habitaciones hab = new Habitaciones();
        if (reserva == null)
            throw new NullPointerException("ERROR: La reserva no puede ser nula.");
        if(hab.buscar(reserva.getHabitacion()) == null)
            throw new IllegalArgumentException("ERROR: La habitación no existe.");
        if (tamano > getCapacidad())
            throw new OperationNotSupportedException("ERROR: Se ha excedido la capacidad de las reservas.");
        coleccionReservas[tamano] = new Reserva(reserva);
        tamano++;
    }

    private boolean existeHabitacion(String numeroHabitacion) {

        //todo buscar el numero de habitacion dentro del array habitaciones



        return true;
    }
    private int buscarIndice(Reserva reserva) {
        if (reserva == null)
            throw new NullPointerException("ERROR: El índice de la  reserva no puede ser nulo.");
        int indice = -1;
        for (int i = 0; i < getTamano(); i++)
            if (reserva.equals(coleccionReservas[i]))
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
    public Reserva buscar(Reserva reserva) {
        if (reserva == null)
            throw new NullPointerException("ERROR: La reserva a buscar no puede ser nula.");
        int busqueda = buscarIndice(reserva);
        if (busqueda == -1)
            return null;
        return reserva;
    }
    public void borrar(Reserva reserva) throws OperationNotSupportedException {
        if (reserva == null)
            throw new NullPointerException("ERROR: No se puede borrar una reserva nula.");
        if (buscar(reserva) == null)
            throw new IllegalArgumentException("ERROR: La reserva a buscar es nula.");
        int indiceBorrado = buscarIndice(reserva);
        coleccionReservas[indiceBorrado] = null;
        desplazarUnaPosicionHaciaIzquierda(indiceBorrado);
        tamano--;
    }
    private void desplazarUnaPosicionHaciaIzquierda(int indice) throws OperationNotSupportedException {
        if (indice < 0 || indice > capacidad)
            throw new OperationNotSupportedException("ERROR: Al desplazar a la izquierda.");
        for (int i = indice; i < getTamano(); i++)
            coleccionReservas[i] = new Reserva(coleccionReservas[i+1]);
        coleccionReservas[getTamano()] = null;
    }
/*
    public Reserva[] getColeccionReservas(Huesped huesped) {
        return coleccionReservas;
    }
    public Reserva[] getColeccionReservas(TipoHabitacion tipoHabitacion) {
        return coleccionReservas;
    }
    public Reserva[] getColeccionReservas(Habitacion habitacion) {
        return coleccionReservas;
    }
*/
    public Reserva[] getReservas(Huesped huesped1) {
        return coleccionReservas;
    }
    public Reserva[] getReservas(TipoHabitacion tipoHabitacion){
        if (tipoHabitacion == null)
            throw new NullPointerException("ERROR: No se pueden buscar reservas si el tipo de habitación es nula.");
        Reserva[] copiaEspecial = new Reserva[this.capacidad];
        for (int i=0; i < tamano; i++){
            if (coleccionReservas[i].getHabitacion().getTipoHabitacion().equals(tipoHabitacion))
                copiaEspecial[i]=new Reserva(coleccionReservas[i]);
        }
        return copiaEspecial;
    }
    public Reserva[] getReservasFuturas(Habitacion habitacion){
        if (habitacion==null)
            throw new NullPointerException("ERROR: No se pueden buscar reservas si el número de habitación es nulo.");
        Reserva[] copiaEspecial = new Reserva[this.capacidad];
        int posicionVacia=0;
        for (int i=0; i < tamano; i++){
            if (coleccionReservas[i].getFechaInicioReserva().isAfter(LocalDate.now()) && coleccionReservas[i].getHabitacion().equals(habitacion)) {
                copiaEspecial[posicionVacia] = new Reserva(coleccionReservas[i]);
                posicionVacia++;
            }
        }
        return copiaEspecial;
    }

    public void realizarCheckIn(Reserva reserva, LocalDateTime fecha){
    if (reserva == null)
        throw new NullPointerException("ERROR: La reserva no puede ser nula.");
    if (fecha == null)
        throw new NullPointerException("ERROR: La fecha no puede ser nula.");
    if (fecha.isBefore(reserva.getFechaInicioReserva().atStartOfDay()) || fecha.isAfter(reserva.getFechaFinReserva().atStartOfDay()))
        throw new IllegalArgumentException("ERROR: La fecha introducida no es correcta.");
    reserva.setCheckIn(fecha);
    }
    public void realizarCheckOut(Reserva reserva, LocalDateTime fecha){
        if (reserva == null)
            throw new NullPointerException("ERROR: La reserva no puede ser nula.");
        if (fecha == null)
            throw new NullPointerException("ERROR: La fecha no puede ser nula.");
        if (fecha.isBefore(reserva.getFechaInicioReserva().isBefore()) || fecha.isAfter(reserva.getFechaFinReserva().atStartOfDay()))
            throw new IllegalArgumentException("ERROR: La fecha introducida no es correcta.");
        reserva.setCheckOut(fecha);
    }
}