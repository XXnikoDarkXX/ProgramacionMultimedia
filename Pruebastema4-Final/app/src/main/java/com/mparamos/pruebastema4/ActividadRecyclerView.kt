package com.mparamos.pruebastema4

import adaptersyholders.MiRecyclerViewAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import clases.Persona
import java.util.*
import kotlin.collections.HashSet

class ActividadRecyclerView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actividad_recycler_view)



        val personas:TreeSet<Persona> = TreeSet<Persona>()
        //Va a insertar como mucho 100, pero como los set no permiten nombres y apellidos repetidos
        //Si da la casualidad de que una combinacion se coge dos veces, la segunda machaca a la primera y ya es un set de 99 nombres en
        //vez de 100 depende del azar cuántas veces pasa esto
     for(i in 0 ..100){
         personas.add(Persona.nuevaPersona())
     }

        //cogemos en una variable el contenedor que es el recycleview
        val contenedorRecycler:RecyclerView = findViewById(R.id.vistaRecycleView)
        //creamos el adapter
        val adapter:MiRecyclerViewAdapter = MiRecyclerViewAdapter(this,personas)
        //a nuestro RecycleView le metemos el adapter creado que contiene la clase Miholder con todos los elementos
        contenedorRecycler.adapter=adapter
        //Establecemos el LayoutManager para que se ocupe de mostrar los datos de la lista
        contenedorRecycler.layoutManager=LinearLayoutManager(this)
    }
}