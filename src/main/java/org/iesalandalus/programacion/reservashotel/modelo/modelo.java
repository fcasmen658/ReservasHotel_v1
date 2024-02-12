package org.iesalandalus.programacion.reservashotel.modelo;

import org.iesalandalus.programacion.reservashotel.modelo.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.TipoHabitacion;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.Habitaciones;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.Huespedes;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.Reservas;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDateTime;

public class modelo {

    private final static int  CAPACIDAD = 10;
    public static Huespedes huespedes;
    private static Habitaciones habitaciones;
    private static Reservas reservas;


    public void comenzar(){

    }

    public void terminar(){
        System.out.println("*** Modelo ha pasado a mejor vida! ***");
    }

    public modelo(){
        huespedes = new Huespedes(CAPACIDAD);
        habitaciones = new Habitaciones(CAPACIDAD);
        reservas = new Reservas(CAPACIDAD);
    }

//HUÉSPED
    public void insertar(Huesped huesped) throws OperationNotSupportedException {
        huespedes.insertar(huesped);
    }
    public Huesped buscar(Huesped huesped){
        return huespedes.buscar(huesped);
    }
    public void borrar(Huesped huesped) throws OperationNotSupportedException {
        huespedes.borrar(huesped);
    }
    public Huesped[] getHuespedes(){
        return huespedes.get();
    }

//HABITACIÓN
    public void insertar(Habitacion habitacion) throws OperationNotSupportedException {
        habitaciones.insertar(habitacion);
    }
    public Habitacion buscar(Habitacion habitacion){
        return habitaciones.buscar(habitacion);
    }
    public void borrar(Habitacion habitacion) throws OperationNotSupportedException {
        habitaciones.borrar(habitacion);
    }
    public Habitacion[] getHabitaciones(){
        return habitaciones.get();
    }

//RESERVA
    public void insertar(Reserva reserva) throws OperationNotSupportedException {
        reservas.insertar(reserva);
    }
    public Reserva buscar(Reserva reserva){
        return reservas.buscar(reserva);
    }
    public void borrar(Reserva reserva) throws OperationNotSupportedException {
        reservas.borrar(reserva);
    }
    public Reserva[] getReservas(){
        return reservas.get();
    }
    public Reserva[] getReservas(Huesped huesped){
        return reservas.getReservas(huesped);
    }
    public Reserva[] getReservas(TipoHabitacion tipoHabitacion){
        return reservas.getReservas(tipoHabitacion);
    }
    public Reserva[] getReservas(Habitacion habitacion){
        return reservas.getReservasFuturas(habitacion);
    }

//REALIZAR CHECKIN Y CHECKOUT
    public void realizarCheckin(Reserva reserva, LocalDateTime fecha){
        reservas.realizarCheckIn(reserva,fecha);
    }
    public void realizarCheckout(Reserva reserva, LocalDateTime fecha){
        reservas.realizarCheckOut(reserva,fecha);
    }
}