package org.iesalandalus.programacion.reservashotel.vista;

public enum Opcion {
    SALIR ("Salir"),
    INSERTAR_HUESPED ("Insertar huésped"),
    BUSCAR_HUESPED ("Buscar huésped"),
    BORRAR_HUESPED ("Borrar huésped"),
    MOSTRAR_HUESPED ("Mostrar huésped"),
    INSERTAR_HABITACION ("Insertar habitación"),
    BUSCAR_HABITACION ("Buscar habitación"),
    BORRAR_HABITACION ("Borrar habitación"),
    MOSTRAR_HABITACIONES ("Mostrar habitaciones"),
    INSERTAR_RESERVA ("Insertar reserva"),
    ANULAR_RESERVA ("Anular reserva"),
    MOSTRAR_RESERVAS ("Mostrar reservas"),
    CONSULTAR_DISPONIBILIDAD ("Consultar disponibilidad"),
    REALIZAR_CHECKIN ("Realizar check-in"),
    REALIZAR_CHECKOUT ("Realizar check-out");



    private final String mensajeAMostrar;

    Opcion(String mensajeAMostrar) {
        this.mensajeAMostrar = mensajeAMostrar;
    }

    @Override
    public String toString() {
        return (ordinal()) + ".- " + mensajeAMostrar;
    }
}