package com.mparamos.examenmarzo2021

class Alumno(var nombre: String, var dni: String, var texto: String, var isAprobar: Boolean) {
    override fun toString(): String {
        return "Alumno{" +
                "nombre='" + nombre + '\'' +
                ", dni='" + dni + '\'' +
                ", texto='" + texto + '\'' +
                ", aprobar=" + isAprobar +
                '}'
    }
}