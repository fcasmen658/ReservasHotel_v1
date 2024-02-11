package org.iesalandalus.programacion.reservashotel.modelo.dominio;

public enum Regimen {
    SOLO_ALOJAMIENTO ("Solo Alojamiento", 0),
    ALOJAMIENTO_DESAYUNO ("Alojamiento y desayuno", 15),
    MEDIA_PENSION ("Media pensión",30),
    PENSION_COMPLETA ("Pensión completa",50);
    private final String cadenaAMostrar;
    private final Double incrementoPrecio;

    Regimen(String cadenaAMostrar, int incrementoPrecio) {
        this.cadenaAMostrar = cadenaAMostrar;
        this.incrementoPrecio = (double) incrementoPrecio;
    }
    public Double getIncrementoPrecio() {
        return incrementoPrecio;
    }

    @Override
    public String toString() {
        return "Regimen{" +
                "cadenaAMostrar='" + cadenaAMostrar + '\'' +
                ", incrementoPrecio=" + incrementoPrecio +
                '}';
    }
}