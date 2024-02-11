package org.iesalandalus.programacion.reservashotel.vista;

import org.iesalandalus.programacion.reservashotel.dominio.*;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.*;
import org.iesalandalus.programacion.utilidades.Entrada;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Consola {
    public static void mostrarMenu(){

        System.out.println(Opcion.INSERTAR_HUESPED);
        System.out.println(Opcion.BUSCAR_HUESPED);
        System.out.println(Opcion.BORRAR_HUESPED);
        System.out.println(Opcion.MOSTRAR_HUESPED);
        System.out.println(Opcion.INSERTAR_HABITACION);
        System.out.println(Opcion.BUSCAR_HABITACION);
        System.out.println(Opcion.BORRAR_HABITACION);
        System.out.println(Opcion.MOSTRAR_HABITACIONES);
        System.out.println(Opcion.INSERTAR_RESERVA);
        System.out.println(Opcion.ANULAR_RESERVA);
        System.out.println(Opcion.MOSTRAR_RESERVAS);
        System.out.println(Opcion.CONSULTAR_DISPONIBILIDAD);
        System.out.println(Opcion.SALIR);
    }

    public static Opcion elegirOpcion(){
        System.out.println("---------------");
        System.out.println(" ");
        int opcion;
        do{
            System.out.print("Elegir una opción del rango (0-12): ");
            opcion = Entrada.entero();
    } while (opcion < 0 || opcion > Opcion.values().length-1);

        return switch (opcion) {

            case 1 -> Opcion.INSERTAR_HUESPED;
            case 2 -> Opcion.BUSCAR_HUESPED;
            case 3 -> Opcion.BORRAR_HUESPED;
            case 4 -> Opcion.MOSTRAR_HUESPED;
            case 5 -> Opcion.INSERTAR_HABITACION;
            case 6 -> Opcion.BUSCAR_HABITACION;
            case 7 -> Opcion.BORRAR_HABITACION;
            case 8 -> Opcion.MOSTRAR_HABITACIONES;
            case 9 -> Opcion.INSERTAR_RESERVA;
            case 10 -> Opcion.ANULAR_RESERVA;
            case 11 -> Opcion.MOSTRAR_RESERVAS;
            case 12 -> Opcion.CONSULTAR_DISPONIBILIDAD;
            case 0 -> Opcion.SALIR;
            default -> throw new IllegalStateException("Valor fuera de rango (0-12)");
        };
    }
    public static Huesped leerHuesped(){

        String nombre, dni, correo, telefono;
        LocalDate fechaNacimiento;
        System.out.print("Introduzca el nombre del huésped: ");
        nombre=Entrada.cadena();
        System.out.print("Introduzca DNI del huésped: ");
        dni=Entrada.cadena();
        System.out.print("Introduzca Correo del huésped: ");
        correo=Entrada.cadena();
        System.out.print("Introduzca teléfono del huésped: ");
        telefono=Entrada.cadena();
        System.out.print("Introduzca fecha de nacimiento del huésped siguiendo el patrón (dd/MM/yyyy): ");
        fechaNacimiento= leerFecha(Entrada.cadena());

        return new Huesped(nombre, dni, correo, telefono, fechaNacimiento);
    }

    public static Huesped getHuespedPorDni(){
        System.out.print("Introduzca el DNI: ");
            String dni = Entrada.cadena();
        return new Huesped("Francisco CaSaS", dni, "fcasmen658@gmail.com","722700080", LocalDate.of(1990, 9, 9));
    }

    public static LocalDate leerFecha(String mensaje){

        Pattern p = Pattern.compile("[0-3][0-9]/[0-1][0-9]/[1-2][0-9]{3}");
        Matcher m;
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern(Huesped.FORMATO_FECHA); //GUARDADO PARA OTRAS FECHAS
        m = p.matcher(mensaje);

        while(!m.matches()){
            System.out.println("Introduzca una fecha según el patrón (dd/MM/yyyy): ");
            mensaje = Entrada.cadena();
            m = p.matcher(mensaje);
        }

        return LocalDate.parse(mensaje, formatoFecha);
    }
    public static Habitacion leerHabitacion(){
        int puerta, planta;
        double precio;
        TipoHabitacion tipoHabitacion;
        System.out.print("Introduzca la planta (1-3) de la habitación: ");
        try{
            planta = Entrada.entero();
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            System.out.println("Prueba otra vez: ");
            planta = Entrada.entero();
        }
        System.out.println("Introduzca la puerta de la habitación (0-14): ");
        puerta=Entrada.entero();
        System.out.println("Introduzca precio de la habitación (40.0-150.0): ");
        precio=Entrada.realDoble();
        tipoHabitacion = leerTipoHabitacion();
        return new Habitacion(planta, puerta, precio, tipoHabitacion);
    }
    public static Habitacion leerHabitacionPorIdentificador(){
        int puerta, planta;
        System.out.print("Introduzca la planta de la habitación: ");
        try{
            planta = Entrada.entero();
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            System.out.println("Prueba otra vez: ");
            planta = Entrada.entero();

        }
        System.out.println("Introduzca la puerta de la habitación: ");
        puerta=Entrada.entero();
        return new Habitacion(planta, puerta, 100);
    }
    public static TipoHabitacion leerTipoHabitacion(){
        TipoHabitacion tipoHabitacion = null;
        int opcion;
        System.out.println("Elige un tipo de habitación: ");
        System.out.println("1- "+TipoHabitacion.SIMPLE);
        System.out.println("2- "+TipoHabitacion.DOBLE);
        System.out.println("3- "+TipoHabitacion.TRIPLE);
        System.out.println("4- "+TipoHabitacion.SUITE);
        do{
            opcion = Entrada.entero();
        }while (opcion<1 || opcion > TipoHabitacion.values().length-1);

        switch (opcion){
            case 1 -> tipoHabitacion=TipoHabitacion.SIMPLE;
            case 2 -> tipoHabitacion=TipoHabitacion.DOBLE;
            case 3 -> tipoHabitacion=TipoHabitacion.TRIPLE;
            case 4 -> tipoHabitacion=TipoHabitacion.SUITE;
        }
        return tipoHabitacion;
    }
    public static Regimen leerRegimen(){
        Regimen regimen = null;

        int opcion;

        System.out.println("Elige tipo de régimen: ");
        System.out.println("1- "+Regimen.SOLO_ALOJAMIENTO);
        System.out.println("2- "+Regimen.ALOJAMIENTO_DESAYUNO);
        System.out.println("3- "+Regimen.MEDIA_PENSION);
        System.out.println("4- "+Regimen.PENSION_COMPLETA);
        do{
            opcion = Entrada.entero();
        }while (opcion<1 || opcion > Regimen.values().length-1);

        switch (opcion){
            case 1 -> regimen = Regimen.SOLO_ALOJAMIENTO;
            case 2 -> regimen = Regimen.ALOJAMIENTO_DESAYUNO;
            case 3 -> regimen = Regimen.MEDIA_PENSION;
            case 4 -> regimen = Regimen.PENSION_COMPLETA;
        }
        return regimen;
    }
    public static Reserva leerReserva(){
        Huesped huesped;
        Habitacion habitacion;
        Regimen regimen;
        LocalDate fechaInicio, fechaFin;
        int numeroHuespedes;
        huesped=leerHuesped();
        habitacion=leerHabitacion();
        regimen=leerRegimen();
        System.out.print("Fecha de entrada (CheckIn): ");
        fechaInicio=leerFecha(Entrada.cadena());
        System.out.print("Fecha de salida (CheckOut): ");
        fechaFin=leerFecha(Entrada.cadena());
        System.out.print("Introduce cuantos huéspedes van a ser: ");
        numeroHuespedes=Entrada.entero();
        return new Reserva(huesped, habitacion, regimen, fechaInicio, fechaFin, numeroHuespedes);
    }
}

