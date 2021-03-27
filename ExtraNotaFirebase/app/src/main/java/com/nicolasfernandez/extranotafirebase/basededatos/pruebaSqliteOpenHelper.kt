package com.nicolasfernandez.extranotafirebase.basededatos

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class pruebaSqliteOpenHelper (val contexto: Context) :SQLiteOpenHelper(contexto,"Pruebas SQlite",null,1){
   companion object{
       val tablaArticulo:String ="Articulo"
       val nombre:String ="nombre"
       val precio:String="precio"
       val enVenta:String="enVenta"
   }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("create table $tablaArticulo($nombre varchar(30) primary key, $precio decimal (6,2),enVenta boolean)")
        db?.execSQL("insert into $tablaArticulo ($nombre,$precio,$enVenta)values ('articulo',33.33,'false') ")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }


}
