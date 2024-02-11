package org.iesalandalus.programacion.reservashotel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import javax.naming.OperationNotSupportedException;
import org.iesalandalus.programacion.reservashotel.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.dominio.Reserva;
import org.iesalandalus.programacion.reservashotel.dominio.TipoHabitacion;
import org.iesalandalus.programacion.reservashotel.negocio.Habitaciones;
import org.iesalandalus.programacion.reservashotel.negocio.Huespedes;
import org.iesalandalus.programacion.reservashotel.negocio.Reservas;
import org.iesalandalus.programacion.reservashotel.vista.Consola;
import org.iesalandalus.programacion.reservashotel.vista.Opcion;
import org.iesalandalus.programacion.utilidades.Entrada;

public class MainApp {

    public static final int CAPACIDAD = 10;
    private final Huespedes huespedes = new Huespedes(CAPACIDAD);
    private final Habitaciones habitaciones = new Habitaciones(CAPACIDAD);
    private final Reservas reservas = new Reservas(CAPACIDAD);
    public static void main(String[] args) {
        MainApp app = new MainApp();
        Opcion opcion;
        do {
            Consola.mostrarMenu();
            opcion = Consola.elegirOpcion();
            app.ejecutarOpcion(opcion);
        } while (opcion != Opcion.SALIR);
    }
    private void ejecutarOpcion(Opcion opcion) {
        switch (opcion) {
            case SALIR -> {
                System.out.println("Cerrando la aplicación...");
                System.out.println("Gracias y ¡Hasta pronto!");
                System.exit(0);
            }
            case INSERTAR_HUESPED -> insertarHuesped();
            case BUSCAR_HUESPED -> buscarHuesped();
            case BORRAR_HUESPED -> borrarHuesped();
            case MOSTRAR_HUESPED -> mostrarHuespedes();
            case INSERTAR_HABITACION -> insertarHabitacion();
            case BUSCAR_HABITACION -> buscarHabitacion();
            case BORRAR_HABITACION -> borrarHabitacion();
            case MOSTRAR_HABITACIONES -> mostrarHabitaciones();
            case INSERTAR_RESERVA -> insertarReserva();
            case ANULAR_RESERVA -> anularReserva();
            case MOSTRAR_RESERVAS -> mostrarReservas();
            case CONSULTAR_DISPONIBILIDAD -> {
                TipoHabitacion tipoHabitacion = Consola.leerTipoHabitacion();
                System.out.print("Fecha de inicio de reserva: ");
                LocalDate fechaInicio = Consola.leerFecha("Fecha de inicio de reserva: ");
                System.out.print("Fecha de fin de reserva: ");
                LocalDate fechaFin = Consola.leerFecha("Fecha de fin de reserva: ");
                consultarDisponibilidad(tipoHabitacion, fechaInicio, fechaFin);
            }
        }
    }
    public void insertarHuesped() {
        try {
            Huesped nuevoHuesped = Consola.leerHuesped();
            if (huespedes.buscar(nuevoHuesped) == null) {
                try {
                    huespedes.insertar(nuevoHuesped);
                    System.out.println("Huésped insertado correctamente.");
                } catch (OperationNotSupportedException e) {
                    System.out.println("Error al insertar el huésped: " + e.getMessage());
                }
            } else {
                System.out.println("El huésped ya está registrado en el sistema.");
            }
        } catch (IllegalArgumentException | NullPointerException e) {
            System.out.println("Error al leer el huésped: " + e.getMessage());
        }
    }

    private void buscarHuesped() {
        try {
            Huesped huespedFicticio = Consola.getHuespedPorDni();
            Huesped huespedEncontrado = huespedes.buscar(huespedFicticio);
            if (huespedEncontrado != null) {
                System.out.println("Huésped encontrado: " + huespedEncontrado);
            } else {
                System.out.println("No se encontró ningún huésped con el DNI proporcionado.");
            }
        } catch (IllegalArgumentException | NullPointerException e) {
            System.out.println("Error al buscar el huésped: " + e.getMessage());
        }
    }

    private void borrarHuesped() {
        try {
            Huesped huespedFicticio = Consola.getHuespedPorDni();
            Huesped huespedBorrado = huespedes.buscar(huespedFicticio);
            if (huespedBorrado != null) {
                huespedes.borrar(huespedBorrado);
                System.out.println("Huésped borrado: " + huespedBorrado);
            } else {
                System.out.println("No se encontró ningún huésped con el DNI proporcionado.");
            }
        } catch (IllegalArgumentException | NullPointerException | OperationNotSupportedException e) {
            System.out.println("Error al buscar el huésped: " + e.getMessage());
        }
    }

    public void mostrarHuespedes() {
        if (huespedes.getTamano() == 0) {
            System.out.println("No hay huéspedes almacenados.");
        } else {
            System.out.println("Lista de huéspedes almacenados:");
            for (Huesped huesped : huespedes.get()) {
                System.out.println(huesped.toString());
            }
        }
    }

    private void insertarHabitacion() {
        try {
            Habitacion nuevaHabitacion = Consola.leerHabitacion();
            if (habitaciones.buscar(nuevaHabitacion) == null) {
                try {
                    habitaciones.insertar(nuevaHabitacion);
                    System.out.println("Habitación insertada correctamente.");
                } catch (OperationNotSupportedException | NullPointerException e) {
                    System.out.println("Error al insertar la habitación: " + e.getMessage());
                }
            } else {
                System.out.println("La habitación ya está registrada en el sistema.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error al leer la habitación: " + e.getMessage());
        }
    }

    private void buscarHabitacion() {
        try {
            Habitacion habitacionFicticia = Consola.leerHabitacionPorIdentificador();
            Habitacion habitacionEncontrada = habitaciones.buscar(habitacionFicticia);
            if (habitacionEncontrada != null) {
                System.out.println("Habitación encontrada: " + habitacionEncontrada);
            } else {
                System.out.println("La habitación buscada no se encuentra en la colección.");
            }
        } catch (IllegalArgumentException | NullPointerException e) {
            System.out.println("Error al buscar la habitación: " + e.getMessage());
        }
    }

    private void borrarHabitacion() {
        try {
            Habitacion habitacionFicticia = Consola.leerHabitacionPorIdentificador();
            Habitacion habitacionBorrada = habitaciones.buscar(habitacionFicticia);
            if (habitacionBorrada != null) {
                habitaciones.borrar(habitacionBorrada);
                System.out.println("Habitación borrada: " + habitacionBorrada);
            } else {
                System.out.println("No se encontró ninguna habitación con el identificador proporcionado.");
            }
        } catch (IllegalArgumentException | NullPointerException | OperationNotSupportedException e) {
            System.out.println("Error al buscar la habitación: " + e.getMessage());
        }
    }

    private void mostrarHabitaciones() {
        if (habitaciones.getTamano() == 0) {
            System.out.println("No hay habitaciones almacenadas.");
        } else {
            System.out.println("Lista de habitaciones almacenadas:");
            for (Habitacion habitacion : habitaciones.get()) {
                System.out.println(habitacion.toString());
            }
        }
    }

    private void insertarReserva() {

        try {
            Reserva reservaFicticia = Consola.leerReserva();
            Huesped huespedFicticio = reservaFicticia.getHuesped();
            Habitacion habitacionFicticia = reservaFicticia.getHabitacion();
            Huesped huespedReal = huespedes.buscar(huespedFicticio);
            Habitacion habitacionReal = habitaciones.buscar(habitacionFicticia);
            if (huespedReal == null) {
                throw new NoSuchElementException("El huésped con el DNI proporcionado no se encuentra en el sistema.");
            }
            if (habitacionReal == null) {
                throw new NoSuchElementException("La habitación con el identificador proporcionado no se encuentra en el sistema.");
            }
            Reserva nuevaReserva = new Reserva(huespedReal, habitacionReal, reservaFicticia.getRegimen(),
                    reservaFicticia.getFechaInicioReserva(), reservaFicticia.getFechaFinReserva(), reservaFicticia.getNumeroPersonas());
            Habitacion habitacionDeseada = nuevaReserva.getHabitacion();
            Habitacion habitacionDisponible = consultarDisponibilidad(habitacionDeseada.getTipoHabitacion(), nuevaReserva.getFechaInicioReserva(), nuevaReserva.getFechaFinReserva());
            if (habitacionDisponible != null) {
                nuevaReserva.setHabitacion(habitacionDisponible);
                if (reservas.buscar(nuevaReserva) == null) {
                    try {
                        reservas.insertar(nuevaReserva);
                        System.out.println("Reserva insertada correctamente.");
                    } catch (OperationNotSupportedException e) {
                        System.out.println("Error al insertar la reserva: " + e.getMessage());
                    }
                } else {
                    System.out.println("La reserva ya está registrada en el sistema.");
                }
            } else {
                System.out.println("No hay disponibilidad para el tipo de habitación deseada en el periodo indicado.");
            }
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
    }

    private void listarReservas(Huesped huesped) {
        boolean hayReservas = false;
        for (Reserva reserva : reservas.get()) {
            if (reserva.getHuesped().equals(huesped)) {
                if (!hayReservas) {
                    System.out.println("Lista de reservas del huésped:");
                    hayReservas = true;
                }
//                System.out.println(reserva.toString());
                System.out.println(reserva);
            }
        }
        if (!hayReservas) {
            System.out.println("No hay reservas para el huésped indicado.");
        }
    }

    private void listarReservas(TipoHabitacion tipoHabitacion) {
        boolean hayReservas = false;
        for (Reserva reserva : reservas.get()) {
            if (reserva.getHabitacion().getTipoHabitacion().equals(tipoHabitacion)) {
                if (!hayReservas) {
                    System.out.println("Lista de reservas para el tipo de habitación " + tipoHabitacion + ":");
                    hayReservas = true;
                }
                System.out.println(reserva);
            }
        }
        if (!hayReservas) {
            System.out.println("No hay reservas para el tipo de habitación " + tipoHabitacion + ".");
        }
    }

    private Reserva[] getReservasAnulables(Reserva[] reservasAAnular) {
        List<Reserva> reservasAnulables = new ArrayList<>();
        LocalDate hoy = LocalDate.now();

        for (Reserva reserva : reservasAAnular) {
            if (reserva.getFechaInicioReserva().isAfter(hoy)) {
                reservasAnulables.add(reserva);
            }
        }

        return reservasAnulables.toArray(new Reserva[0]);
    }

    private void anularReserva() {
        try {
            System.out.println("Anular su reserva:");
            Huesped huespedFicticio = Consola.getHuespedPorDni();
            Reserva[] reservasAnulables = getReservasAnulables(reservas.getReservas(huespedFicticio));
            if (reservasAnulables.length == 0) {
                throw new NoSuchElementException("El huésped no tiene reservas anulables.");
            } else if (reservasAnulables.length == 1) {
                System.out.println("El huésped tiene una reserva anulable: " + reservasAnulables[0]);
                System.out.print("¿Desea anular esta reserva? (S/N): ");
                String respuesta = Entrada.cadena();
                if (respuesta.equalsIgnoreCase("S")) {
                    reservas.borrar(reservasAnulables[0]);
                    System.out.println("La reserva ha sido anulada.");
                } else {
                    System.out.println("Anulación cancelada.");
                }
            } else {
                System.out.println("El huésped tiene varias reservas anulables:");
                for (int i = 0; i < reservasAnulables.length; i++) {
                    System.out.println((i + 1) + ".- " + reservasAnulables[i]);
                }
                System.out.print("Introduce el número de la reserva que deseas anular: ");
                int indice = Entrada.entero() - 1;
                if (indice >= 0 && indice < reservasAnulables.length) {
                    reservas.borrar(reservasAnulables[indice]);
                    System.out.println("La reserva seleccionada ha sido anulada.");
                } else {
                    System.out.println("Número de reserva no válido.");
                }
            }
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al anular la reserva: " + e.getMessage());
        }
    }
    public void mostrarReservas() {
        if (reservas.getTamano() == 0) {
            System.out.println("No hay reservas almacenadas.");
        } else {
            System.out.println("Lista de todas las reservas almacenadas:");
            for (Reserva reserva : reservas.get()) {
                System.out.println(reserva.toString());
            }
        }
    }
    private Habitacion consultarDisponibilidad(TipoHabitacion tipoHabitacion, LocalDate fechaInicio, LocalDate fechaFin) {
        for (Habitacion habitacion : habitaciones.get()) {
            if (habitacion.getTipoHabitacion().equals(tipoHabitacion)) {
                boolean estaDisponible = true;
                for (Reserva reserva : reservas.get()) {
                    if (reserva.getHabitacion().equals(habitacion) &&
                            !reserva.getFechaFinReserva().isBefore(fechaInicio) &&
                            !reserva.getFechaInicioReserva().isAfter(fechaFin)) {
                        estaDisponible = false;
                        break;
                    }
                }
                if (estaDisponible) {
                    return habitacion;
                }
            }
        }
        System.out.println("Esa habitación no está disponible.");
        return null;
    }
}
