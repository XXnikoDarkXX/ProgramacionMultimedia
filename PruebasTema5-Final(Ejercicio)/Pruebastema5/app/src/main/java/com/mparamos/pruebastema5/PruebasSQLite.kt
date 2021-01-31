package com.mparamos.pruebastema5

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import basededatos.Tema5OpenHelper
import clases.Usuario
import recycler.SQLiteAdapter

class PruebasSQLite : AppCompatActivity() {
    //Inicializamos la base de datos y la referenciamos
    val database:SQLiteDatabase by lazy{ Tema5OpenHelper(this).writableDatabase }
    val campoUsuario:EditText by lazy{ findViewById<EditText>(R.id.campoUsuario)}
    val campoContraseña:EditText by lazy{ findViewById<EditText>(R.id.campoContraseña)}
    val botonInsertarModificar:Button by lazy{findViewById<Button>(R.id.botonInsMod)}
    val tituloActividad: TextView by lazy{findViewById<TextView>(R.id.tituloInsertar)}
    /** esta variable vale -1 si estoy insertando, y la id del usuario a modificar si estoy modificando **/
    var idUsuarioAModificar:Int=-1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pruebassqlite)
        refrescarRecyclerDatos()

    }

    fun refrescarRecyclerDatos(){
        val usuarios:ArrayList<Usuario> = arrayListOf<Usuario>()
    //creamos un cursos que sera el que se encargue de ir moviendo los registros
        //Usamos la funcion database.query para decir que base de datos vamoms a iterar
        var cursor: Cursor =database.query(Tema5OpenHelper.tablaUsuario,null,null,
                null,null,null,Tema5OpenHelper.idTablaUsuario+" desc")
        cursor.moveToFirst()//movemos el cursos al primer registro
        while(!cursor.isAfterLast){//iteramos el cursos hasta el ultimo registro
            //iremos añadiendo las tablas a nuestro arrayList
            val id:Int=cursor.getInt(cursor.getColumnIndex(Tema5OpenHelper.idTablaUsuario))
            val nombre:String=cursor.getString(cursor.getColumnIndex(Tema5OpenHelper.nombreTablaUsuario))
            val contraseña:String=cursor.getString(cursor.getColumnIndex(Tema5OpenHelper.contraseñaTablaUsuario))

            usuarios.add(Usuario(id,nombre,contraseña))

            cursor.moveToNext()
        }

        val adapter:SQLiteAdapter=SQLiteAdapter(this,usuarios);
        val recycler:RecyclerView=findViewById<RecyclerView>(R.id.recyclerUsuarios)
        recycler.adapter=adapter
        recycler.layoutManager=LinearLayoutManager(this)
    }

    fun insertarModificar(view: View) {
        //Si el texto es modificar, modifico
        val datosAInsertar: ContentValues = ContentValues()

        datosAInsertar.put(Tema5OpenHelper.nombreTablaUsuario, campoUsuario.text.toString())
        datosAInsertar.put(Tema5OpenHelper.contraseñaTablaUsuario, campoContraseña.text.toString())

        if(this.botonInsertarModificar.text.toString().
                equals(this.resources.getString(R.string.modificar))){
            //update usuario set nombre=X, contraseña=Y where id=Z
            try{
                if(database.update(Tema5OpenHelper.tablaUsuario,datosAInsertar,
                                "id=${this.idUsuarioAModificar}",null) >0){
                    Toast.makeText(this, R.string.usuarioModificado, Toast.LENGTH_LONG).show()
                    //Como ya he modifcado, y ha salido bien vuelvo a poner los valores para insertar
                    botonInsertarModificar.setText(this.resources.getString(R.string.insertar))
                    tituloActividad.setText(this.resources.getString(R.string.tituloInsertar))
                    this.idUsuarioAModificar=-1
                    campoContraseña.setText("")
                    campoUsuario.setText("")
                }else{
                    Toast.makeText(this, R.string.errorModificandoUsuario, Toast.LENGTH_LONG).show()
                }
            }catch (e: SQLiteConstraintException){
                Toast.makeText(this, R.string.errorModificandoUsuario, Toast.LENGTH_LONG).show()
            }
        }else {
            if (database.insert(Tema5OpenHelper.tablaUsuario, null, datosAInsertar) > 0) {
                Toast.makeText(this, R.string.usuarioInsertado, Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, R.string.errorInsertandoUsuario, Toast.LENGTH_LONG).show()
            }
        }
        refrescarRecyclerDatos()

    }


}