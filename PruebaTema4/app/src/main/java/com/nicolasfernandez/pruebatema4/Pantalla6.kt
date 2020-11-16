package com.nicolasfernandez.pruebatema4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class Pantalla6 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla6)
        val valores:ArrayList<String> = arrayListOf(
            "elemento 1","elemento 2","elemento 3","elemento 4","elemento 5","elemento 6","elemento 7","elemento 8")
        val adapter:ArrayAdapter<String> = ArrayAdapter(this,R.layout.elementos_arrayadapter,valores)//ponemos al adapter el layout
        //que tenemos inflado

        val lista:ListView= findViewById<ListView>(R.id.elementoLista)//creamos una variable con el listView
        lista.adapter=adapter//añadimos a la lista el adapter

        //usar los elementos de la lista
        lista.onItemClickListener=AdapterView.OnItemClickListener{
            av: AdapterView<*>,view:View,position:Int,id:Long->
            Toast.makeText(this,"POSICION: "+position +" : "+(view as TextView).text.toString(),Toast.LENGTH_LONG).show()

        }
    }
}