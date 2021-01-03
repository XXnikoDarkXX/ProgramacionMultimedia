package com.nicolasfernandez.pruebabbdd

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BDPersona(val contexto:Context) : SQLiteOpenHelper (contexto,"baseDatos",null,1){

    companion object {
        //inicializo las variables de la tabla
        val tablaUsuario: String = "usuario"
        val idTablaUsuario: String = "id"
        val nombreTablaUsuario: String = "nombre"
        val contraseñaTablaUsuario: String = "contraseña"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("create table $tablaUsuario("+idTablaUsuario+" integer primary key " +
                "autoincrement," +
                "$nombreTablaUsuario varchar(100) unique,$contraseñaTablaUsuario varchar(100))")
        db?.execSQL("insert into $tablaUsuario($nombreTablaUsuario,$contraseñaTablaUsuario) values('admin','1234')")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}