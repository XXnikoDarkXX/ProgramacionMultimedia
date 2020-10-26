package com.nico.pruebakotlin.clases

import com.nico.pruebakotlin.Interface.Interfaz2

open class Persona (var nombre:String="Por defecto",
               val anioNacimiento:Int=1999): Interfaz2 {


//si es solo es String  y queremos iniciarlo despuest
//lateinit var test:String
//si es otro tipo primitivo como int, double,float, long ect...
//    var test:Int by notNull();
//de forma diferida es decir es un final y encima solo podemos sacarla mediante un get
    val test:Int by lazy{3}

    override fun prueba1(arg1: String, arg2: Float) {
        TODO("Not yet implemented")
    }

    enum class MiEnumPer{
        UNO,
        DOS,
        TRES
    }

    override fun toString(): String {
        return nombre+" : "+anioNacimiento+this.test
    }

    /**
     * Funcion para cambiar nombre
     */
    open fun cambiarNombre(n:String):Unit{
        this.nombre=n;
    }




}