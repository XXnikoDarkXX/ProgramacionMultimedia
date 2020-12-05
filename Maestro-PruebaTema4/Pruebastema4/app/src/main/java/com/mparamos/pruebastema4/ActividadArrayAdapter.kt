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

        val adapter: ArrayAdapter<String> =
            ArrayAdapter(this,R.layout.elementos_arrayadapter,valores)
            val lista:ListView = findViewById<ListView>(R.id.espacioLista)
            lista.adapter=adapter

            lista.onItemClickListener= AdapterView.OnItemClickListener {
                    parent: AdapterView<*>, view:View, position:Int,id:Long ->
                        Toast.makeText(this,
                            "poscion "+position+" : "+(view as TextView).text.toString()
                        ,Toast.LENGTH_LONG).show()
            }
    }
}