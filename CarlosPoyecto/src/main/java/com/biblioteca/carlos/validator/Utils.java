package com.biblioteca.carlos.validator;

import com.biblioteca.carlos.model.*;

import java.util.Date;

public class Utils {

    public static boolean esNombreValido(String nombre) {
        return !nombre.isEmpty();
    }
    public static boolean esDireccionValida(String direccion) {
        return !direccion.isEmpty();
    }

    public static boolean esTelefonoValido(String telefono) {
        return telefono.matches("\\d{10}");
    }

    public static boolean esCorreoValido(String correo) {
        return correo.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
    }

    public static boolean esNumeroValido(Integer valor) {
        return valor != null && valor > 0;
    }

    public static boolean esLibroValido(Libro libro) {
        return libro != null && libro.getId() != null && libro.getId() > 0;
    }

    public static boolean esUsuarioValido(Usuario usuario) {
        return usuario != null && usuario.getId() != null && usuario.getId() > 0;
    }
    public static boolean esGeneroValido(Genero genero) {
        return genero != null && genero.getId() != null && genero.getId() > 0;
    }
    public static boolean esEditorialValido(Editorial editorial) {
        return editorial != null && editorial.getId() != null && editorial.getId() > 0;
    }
    public static boolean esPrestamoValido(Prestamo prestamo) {
        return prestamo != null && prestamo.getId() != null && prestamo.getId() > 0;
    }

    public static boolean esFechaValida(Date fecha) {
        return fecha != null;
    }
    public static boolean esTextoValido(String texto) {
        return texto != null && !texto.trim().isEmpty();
    }

    public static boolean esAnoValido(Integer ano) {
        return ano != null && ano >= 0;
    }
}
