package org.iesalandalus.programacion.reservashotel.modelo.dominio;

public enum TipoHabitacion {
    SIMPLE ("Habitación Simple", 1),
    DOBLE ("Habitación Doble", 2),
    TRIPLE ("Habitación Triple", 3),
    SUITE ("Habitación Suite", 4);
    private final String cadenaAMostrar;
    private final int numeroMaximoPersonas;
    TipoHabitacion (String cadenaAMostrar, int numeroMaximoPersonas) {
        this.cadenaAMostrar = cadenaAMostrar;
        this.numeroMaximoPersonas = numeroMaximoPersonas;
    }
    public int getNumeroMaximoPersonas() {
        return numeroMaximoPersonas;
    }

    @Override
    public String toString() {
    System.out.println("Tipo de habitación: " + SIMPLE.ordinal());
    return "";
    /*    return "TipoHabitación{" +
                "cadenaAMostrar='" + cadenaAMostrar + '\'' +
                ", numeroMáximoPersonas=" + numeroMaximoPersonas +
                '}';
     */
    }
}
