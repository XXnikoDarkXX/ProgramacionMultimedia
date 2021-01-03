package basededatos

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * Clase principal de la base de datos
 * @param contexto es el contesto de la base de datos
 * @param name "bd tema 5" nombre de la bd
 * @param factory lo de jamos a null es el manejodor de cursores
 * @param version version de la bd
 */
class Tema5OpenHelper(val contexto:Context):
        SQLiteOpenHelper(contexto,"bd Tema 5",null,1) {
        companion object {
            //inicializo las variables de la tabla
            val tablaUsuario: String = "usuario"
            val idTablaUsuario: String = "id"
            val nombreTablaUsuario: String = "nombre"
            val contraseñaTablaUsuario: String = "contraseña"
        }

    /**
     * Solo se llama la primera vez que se referencia a la base de datos desde el probrama tras instalar la app
     * nos sirve para crear la estructura necesaria es decir donde creamos las tablas
      */
    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL("create table $tablaUsuario("+idTablaUsuario+" integer primary key " +
                "autoincrement," +
                "$nombreTablaUsuario varchar(100) unique,$contraseñaTablaUsuario varchar(100))")
        p0?.execSQL("insert into $tablaUsuario($nombreTablaUsuario,$contraseñaTablaUsuario) values('admin','1234')")
    }

    /**
     * Funcion que sirve para crear y pasar los datos de una bd version anterior a la version nueva, sin tener
     * que borrar ningún dato
     * @param p0 tipo SQLiteDatabase se puede hacer consultas de todo tipo referencia a nuestra bd
     * @param p1 version antigua
     * @param p2 version nueva
     *
     */
    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }
}