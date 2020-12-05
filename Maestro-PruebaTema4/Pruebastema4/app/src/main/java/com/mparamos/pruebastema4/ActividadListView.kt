package com.mparamos.pruebastema4

import adapters.MiListViewAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import clases.Persona

class ActividadListView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.actividad_list_view)

        var personas:ArrayList<Persona> = ArrayList<Persona>()
        personas.add(Persona("Jose Manuel","https://cdn.pixabay.com/photo/2020/11/06/05/33/woman-5716875_960_720.png"))
        personas.add(Persona("Javier","https://cdn.pixabay.com/photo/2020/11/06/05/33/woman-5716875_960_720.png"))
        personas.add(Persona("Crisitna","https://cdn.pixabay.com/photo/2020/11/06/05/33/woman-5716875_960_720.png"))
        personas.add(Persona("Raúl","https://cdn.pixabay.com/photo/2020/11/06/05/33/woman-5716875_960_720.png"))
        personas.add(Persona("Miguel","https://cdn.pixabay.com/photo/2020/11/06/05/33/woman-5716875_960_720.png"))

        val adapter:MiListViewAdapter= MiListViewAdapter(this,personas)
        val lista: ListView =findViewById(R.id.espacioLista)
        lista.setOnItemClickListener(AdapterView.OnItemClickListener{
            adapterView,view:View,pos,id ->
            Toast.makeText(this,"Pulsado un elemento : "+pos,Toast.LENGTH_LONG).show()
        })

        lista.adapter=adapter

    }
}