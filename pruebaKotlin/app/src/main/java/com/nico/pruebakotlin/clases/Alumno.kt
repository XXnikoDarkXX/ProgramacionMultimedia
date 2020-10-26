package com.nico.pruebakotlin.clases

import com.nico.pruebakotlin.Interface.Interfaz2

class Alumno(nombre:String,anioNacimiento:Int,var nLista:Int):
    Persona( nombre, anioNacimiento), Interfaz2 {
    //Tiene un delegado que es el this que llama al constructor principal por eso ponemos a los 3
    constructor(nombre:String="Alumno por defecto",anioNacimiento: Int=2000,nLista:Int=9,iventado:Int=9):this(nombre,anioNacimiento,nLista) {

    }

    /**
     * Para sobreescribir necesitamos poenr un override y en la funcion padre poner open para dejarlo abierto
     */
    override  fun cambiarNombre(n:String):Unit{
        this.nombre=n +" el alumno";
    }
    //funcion de la interfaz
    override fun prueba1(arg1: String, arg2: Float) {
        TODO("Not yet implemented")
    }

}