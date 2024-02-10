package org.iesalandalus.programacion.reservashotel.dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Huesped {
    private final String ER_TELEFONO = "[0-9]{9}";
    private final String ER_CORREO = "[0-9a-zA-Z-._+&]+@+[0-9a-zA-Z-._+&]+[.]+[a-zA-Z]{2,6}$";
    private static final String ER_DNI = "([\\d]{8})([a-zA-Z])";
    public static final String FORMATO_FECHA = "dd/MM/yyyy";

    private String nombre;
    private String telefono;
    private String correo;
    private String dni;
    private LocalDate fechaNacimiento;

    //Constructor con parámetros.
    public Huesped(String nombre, String dni, String correo, String telefono, LocalDate fechaNacimiento){
        setNombre(nombre);
        setDni(dni);
        setCorreo(correo);
        setTelefono(telefono);
        setFechaNacimiento(fechaNacimiento);
    }

    //Constructor copia.
    public Huesped(Huesped huesped){
        if (huesped==null)
            throw new NullPointerException("ERROR: No es posible copiar un huésped nulo.");
        setNombre(huesped.getNombre());
        setDni(huesped.getDni());
        setCorreo(huesped.getCorreo());
        setTelefono(huesped.getTelefono());
        setFechaNacimiento(huesped.getFechaNacimiento());
    }

    //Formatear Nombre.
    private String formateaNombre(String nombre) {
        if (nombre == null)
            throw new NullPointerException("ERROR: El nombre de un huésped no puede ser nulo.");
        if (nombre.isBlank())
            throw new IllegalArgumentException("ERROR: El nombre de un huésped no puede estar vacío.");
        String nombreFinal = "";
        String[] palabras = nombre.replaceAll("\\s+", " ").split(" ");
        String nuevoNombre = "";
        for (String palabra : palabras) {
            for (int i = 0; i < palabra.length(); i++)
                nuevoNombre = nuevoNombre + ((i == 0) ? palabra.substring(i, i + 1).toUpperCase() :
                        (i != palabra.length() - 1) ? palabra.substring(i, i + 1).toLowerCase() :
                                palabra.substring(i, i + 1).toLowerCase() + " ");
            nombreFinal = nuevoNombre.trim();
        }
        return nombreFinal.trim();
    }

    //Comprobar letra DNI.
    public static boolean comprobarLetraDni(String dni) {
        if (dni == null)
            throw new NullPointerException("ERROR: El dni de un huésped no puede ser nulo.");
        if ((dni.isBlank()))
            throw new IllegalArgumentException("ERROR: El dni del huésped no tiene un formato válido.");
        if (!dni.matches(ER_DNI))
            throw new IllegalArgumentException("ERROR: El dni del huésped no tiene un formato válido.");
        Pattern pDNI = Pattern.compile(ER_DNI);
        Matcher mDNI;
        mDNI = pDNI.matcher(dni);
        int numeroDni = Integer.parseInt(mDNI.group(1));
        int resultadodivision = numeroDni % 23;
        String[] letrasTabla = {"T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X", "B", "N", "J", "Z", "S", "Q", "V", "H", "L", "C", "K", "E"};
        boolean dniValido;
        dniValido = mDNI.group(2).equals(letrasTabla[resultadodivision]);
        return dniValido;
    }

    private String getIniciales(){
        String iniciales = "";
        for (int i = 0; i < nombre.length(); i++) {
            if (i == 0 || nombre.charAt(i - 1) == ' ') {
                iniciales += nombre.charAt(i);
            }
        }
        return iniciales.toUpperCase();
    }

    //Getter & Setter.

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        if(nombre==null)
            throw new NullPointerException("ERROR: El nombre de un huésped no puede ser nulo.");
        this.nombre = formateaNombre(nombre);
    }

    public String getDni() {
        return dni;
    }

    private void setDni(String dni) {
        if(dni==null)
            throw new NullPointerException("ERROR: El dni de un huésped no puede ser nulo.");
        if(dni.isBlank())
            throw new IllegalArgumentException("ERROR: El dni del huésped no tiene un formato válido.");
        if (!dni.matches(ER_DNI))
            throw new IllegalArgumentException("ERROR: El dni del huésped no tiene un formato válido.");
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        if (telefono==null)
            throw new NullPointerException("ERROR: El teléfono de un huésped no puede ser nulo.");
        if(!telefono.matches(ER_TELEFONO))
            throw new IllegalArgumentException("ERROR: El teléfono del huésped no tiene un formato válido.");
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        if(correo==null)
            throw new NullPointerException("ERROR: El correo de un huésped no puede ser nulo.");
        if(!correo.matches(ER_CORREO))
            throw new IllegalArgumentException("ERROR: El correo del huésped no tiene un formato válido.");
        this.correo = correo;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }
    private void setFechaNacimiento(LocalDate fechaNacimiento) {
        if (fechaNacimiento==null)
            throw new NullPointerException("ERROR: La fecha de nacimiento de un huésped no puede ser nula.");
        LocalDate fechaActual = LocalDate.now();
        int comparacionfechaNacimiento = fechaNacimiento.compareTo(fechaActual);
        int comparacion = fechaNacimiento.compareTo(fechaActual);
        if (comparacion > 0) {
            throw new IllegalArgumentException("ERROR: La fecha de nacimiento no puede ser posterior a hoy.");
        }
        this.fechaNacimiento = fechaNacimiento;
    }

    //equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Huesped huesped)) return false;
        return Objects.equals(getDni(), huesped.getDni());
    }

    //hashCode
    @Override
    public int hashCode() {
        return Objects.hash(getDni());
    }

    //toString
    @Override
    public String toString() {
        return  "nombre=" + getNombre() + " " + "(" + getIniciales() + ")" +
                ", DNI=" + getDni() +
                ", correo=" + getCorreo() +
                ", teléfono=" + getTelefono() +
                ", fecha nacimiento=" + fechaNacimiento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

    }
}
