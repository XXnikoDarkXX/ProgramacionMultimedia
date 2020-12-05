package com.mparamos.pruebastema5

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import basededatos.Tema5OpenHelper
import clases.Usuario
import recycler.SQLITEAdapter

class PruebasSQLite : AppCompatActivity() {
    val database:SQLiteDatabase by lazy{ Tema5OpenHelper(this).writableDatabase}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pruebassqlite)


        val usuarios:ArrayList<Usuario> = arrayListOf <Usuario>()
        //sacamos una consulta de la bbdd

      var cursor:Cursor= this.database.query(Tema5OpenHelper.tablaUsuario,null,null,null,
               null,null,Tema5OpenHelper.idTablaUsuario+" desc")
        cursor.moveToFirst()
        while (!cursor.isAfterLast){
            val id:Int=cursor.getInt(cursor.getColumnIndex(Tema5OpenHelper.idTablaUsuario))
            val nombre:String = cursor.getString(cursor.getColumnIndex(Tema5OpenHelper.nombreTablaUsuario))
            val contraseña:String=cursor.getString(cursor.getColumnIndex(Tema5OpenHelper.contraseñaTablaUsuario))

            usuarios.add(Usuario(id,nombre,contraseña))
            cursor.moveToNext()
        }
        val adapter:SQLITEAdapter= SQLITEAdapter(this,usuarios)
        val recycler:RecyclerView=findViewById<RecyclerView>(R.id.recyclerUsuarios)
        recycler.adapter=adapter
        recycler.layoutManager=LinearLayoutManager(this)
    }

    fun insertarModificar(view: View) {
        val campoUsuario:EditText=findViewById<EditText>(R.id.campoUsuario)
        val campoContraseña:EditText=findViewById<EditText>(R.id.campoContraseña)
        val datosAInsertar:ContentValues= ContentValues()
        datosAInsertar.put(Tema5OpenHelper.nombreTablaUsuario,campoUsuario.text.toString())
        datosAInsertar.put(Tema5OpenHelper.contraseñaTablaUsuario,campoContraseña.text.toString())
        if (database.insert(Tema5OpenHelper.tablaUsuario,null,datosAInsertar)>0){
            Toast.makeText(this,R.string.usuarioInsertado,Toast.LENGTH_LONG).show()

        }else{
            Toast.makeText(this,R.string.errorInsertandoUsuario,Toast.LENGTH_LONG).show()
        }



    }
}