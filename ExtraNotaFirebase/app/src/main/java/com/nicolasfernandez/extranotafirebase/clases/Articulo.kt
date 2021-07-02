package com.nicolasfernandez.extranotafirebase.clases

class Articulo
    (val nombre:String,val precio:Float,val enVenta:Boolean) {



    override fun toString(): String {
        return "Articulo(nombre='$nombre', precio=$precio, enVenta=$enVenta)"
    }
    constructor():this("",0.0f,false)

}


