package com.mparamos.pruebastema4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class ActividadArrayAdapter : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.actividad_array_adapter)
        val valores:ArrayList<String> = arrayListOf(
            "elemento 1","elemento 2","elemento 3","elemento 4",
            "elemento 5","elemento 6","elemento 7","elemento 8")

        //creamos una variable adapter y usando por ejemplo ArrayAdapter con this de contexto, le metemos la vista que vamos a inflar(coger)
        //Y el ultimo parametro el array de elementos String
        val adapter: ArrayAdapter<String> = ArrayAdapter(this,R.layout.elementos_arrayadapter,valores) //ponemos al adapter el layout
        //que tenemos inflado
            val lista:ListView = findViewById<ListView>(R.id.espacioLista)//creamos una variable con el listView
            lista.adapter=adapter//añadimos a la lista el adapter que es la variable inflada que contendra el arrayList de elementos
        //esto lo hace automatico por cada elemento añadira una lista(fila)

        //usar los elementos de la lista
            lista.onItemClickListener= AdapterView.OnItemClickListener {
                    parent: AdapterView<*>, view:View, position:Int,id:Long ->
                        Toast.makeText(this,
                            "poscion "+position+" : "+(view as TextView).text.toString()
                        ,Toast.LENGTH_LONG).show()
            }
    }
}