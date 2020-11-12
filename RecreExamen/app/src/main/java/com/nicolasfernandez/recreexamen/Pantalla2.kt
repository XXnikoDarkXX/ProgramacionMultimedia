package com.nicolasfernandez.recreexamen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class Pantalla2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla2)
        //Recogemos en una nueva variable budle el anterior bundle  del main Principal
        var bun:Bundle?=this.intent.extras;
        var txtPanta2:TextView=findViewById<TextView>(R.id.Pantalla2)//añadimos una variable a la id del  textoPrincipal

        var opcion:String?=bun?.getString("opcionElegida").toString()//añadimos una variable de la opcion elegida

        //vamos a crear un arrayList de los colores
        var coloresPais:ArrayList<String?>?//Inicializamos el array

        coloresPais=ArrayList<String?>()//declaramos el array

        //metemos los colores de bandera recibida por el bundle
        coloresPais = bun?.getStringArrayList("ArrayBandera") as ArrayList<String?>

        var color:String="";
        //metemos este if para que sepamos que no es null
        if (coloresPais!=null){
            for (i in 0 until coloresPais.size) {
                color+=coloresPais.get(i)+" "
            }
        }


        //agregamos el sexo + los colores de la bandera elegida
       txtPanta2.setText(opcion+"\n"+color)

    }
}