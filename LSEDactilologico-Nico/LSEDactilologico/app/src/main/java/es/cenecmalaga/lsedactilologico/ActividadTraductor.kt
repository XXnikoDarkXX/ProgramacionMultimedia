package com.NicolasFernandez.lsedactilologico

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.view.children
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import es.cenecmalaga.lsedactilologico.ActividadSobreNosotros
import es.cenecmalaga.lsedactilologico.Actividades.ActividadAyudanos
import es.cenecmalaga.lsedactilologico.adapters_holders.MiRecyclerViewAdapter

class ActividadTraductor : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.actividad_traductor)


    }

    //funcion para que cuando le demos un click traduzca el texto que hayamos escrito mediante imagenes
    fun TraducirTexto(view: View) {


        //es nuestro contener vertical
        /*  var contenedor:LinearLayout =this.findViewById<LinearLayout>(R.id.contenedor)//buscar la id del xml y pasarlo a una variable
          //nuestro texto principal
          contenedor.removeAllViewsInLayout()//borrar las imagenes del scrollview ya puestas
          var  texto:EditText=this.findViewById(R.id.txtTraducir)
          //Convertimos a String el EditText donde hemos escrito las palabras
          var cadena:String= texto.text.toString()
          //para realizar una prueba lo hemos sacado por pantalla
          Toast.makeText(this,cadena,Toast.LENGTH_LONG).show()
          //probar este for mediante este for vamos a intentar ir cogiendo las imagenes  e ir añadiendolas
          for (i in 0 until cadena.length){
              var imagen:ImageView= ImageView(this)
              when(cadena.get(i)){
                  'a'-> {
                      imagen.setImageResource(R.drawable.aa)
                      contenedor.addView(imagen)
                  }
                  'b'-> {
                      imagen.setImageResource(R.drawable.b)
                      contenedor.addView(imagen)
                  }

                  'c'->{
                      imagen.setImageResource(R.drawable.c)
                      contenedor.addView(imagen)
                  }
                  'd'->{
                      imagen.setImageResource(R.drawable.d)
                      contenedor.addView(imagen)
                  }
                  'e'->{
                      imagen.setImageResource(R.drawable.e)
                      contenedor.addView(imagen)
                  }
                  'f'->{
                      imagen.setImageResource(R.drawable.f)
                      contenedor.addView(imagen)
                  }
                  'g'->{
                      imagen.setImageResource(R.drawable.g)
                      contenedor.addView(imagen)
                  }
                  'h'->{
                      imagen.setImageResource(R.drawable.h)
                      contenedor.addView(imagen)
                  }
                  'i'->{
                      imagen.setImageResource(R.drawable.i)
                      contenedor.addView(imagen)

                  }
                  'j'->{
                      imagen.setImageResource(R.drawable.j)
                      contenedor.addView(imagen)
                  }
                  'k'->{
                      imagen.setImageResource(R.drawable.k)
                      contenedor.addView(imagen)
                  }
                  'l'->{
                      imagen.setImageResource(R.drawable.l)
                      contenedor.addView(imagen)
                  }
                  //  'll'->imagen.setImageResource(R.drawable.ll) corregir esto de la ll
                  'ñ'->{
                      imagen.setImageResource(R.drawable.enie)
                      contenedor.addView(imagen)
                  }
                  'n'->{
                      imagen.setImageResource(R.drawable.n)
                      contenedor.addView(imagen)
                  }
                  'm'->{
                      imagen.setImageResource(R.drawable.m)
                      contenedor.addView(imagen)
                  }
                  'o'->{
                      imagen.setImageResource(R.drawable.o)
                      contenedor.addView(imagen)

                  }
                  'p'-> {
                      imagen.setImageResource(R.drawable.p)
                      contenedor.addView(imagen)
                  }
                  'q'->{

                      imagen.setImageResource(R.drawable.q)
                      contenedor.addView(imagen)
                  }
                  'r'->{
                      imagen.setImageResource(R.drawable.r)
                      contenedor.addView(imagen)
                  }
                  // 'rr'->imagen.setImageResource(R.drawable.rr)
                  's'->{
                      imagen.setImageResource(R.drawable.s)
                      contenedor.addView(imagen)
                  }
                  't'->{
                      imagen.setImageResource(R.drawable.t)
                      contenedor.addView(imagen)
                  }
                  'u'-> {
                      imagen.setImageResource(R.drawable.u)
                      contenedor.addView(imagen)
                  }
                  'v'->{
                      imagen.setImageResource(R.drawable.v)
                      contenedor.addView(imagen)
                  }

                  'w'->{
                      imagen.setImageResource(R.drawable.w)
                      contenedor.addView(imagen)
                  }
                  'x'->{
                      imagen.setImageResource(R.drawable.x)
                      contenedor.addView(imagen)
                  }
                  'y'->{
                      imagen.setImageResource(R.drawable.y)
                      contenedor.addView(imagen)
                  }
                  'z'->{
                      imagen.setImageResource(R.drawable.z)
                      contenedor.addView(imagen)

                  }


              }

  }
  */


    }

    /**
     * Funcion que usaremos para traducir el texto y pasarlo a imagenes en el recycleView Principal
     */
    fun irAlista(view: View) {

        var texto: EditText = this.findViewById(R.id.txtTraducir)
        //Convertimos a String el EditText donde hemos escrito las palabras
        val cadena: String = texto.text.toString().toLowerCase()

        var datos: ArrayList<Char> = ArrayList()

        if (cadena != null) {
            for (i in 0 until cadena.length) {

                datos.add(cadena.get(i))
            }


        }

        //cogemos en una variable el contenedor que es el recycleview
        val contenedorRecycler: RecyclerView = findViewById(R.id.listaRecycle)
        //creamos el adapter
        val adapter: MiRecyclerViewAdapter = MiRecyclerViewAdapter(this, datos)
        //a nuestro RecycleView le metemos el adapter creado que contiene la clase Miholder con todos los elementos
        contenedorRecycler.adapter = adapter
        //Establecemos el LayoutManager para que se ocupe de mostrar los datos de la lista
        contenedorRecycler.layoutManager = LinearLayoutManager(this)

    }


    /**
     * Funcion para recoger el texto escrito
      */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)


        var texto: EditText = this.findViewById(R.id.txtTraducir)
        val cadena: String = texto.text.toString().toLowerCase()

        outState.putString("texto",cadena)


    }


    /**
     * Funicion para recoger el bundle de girar la pantalla y meter las imagenes al recycleView
     */
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        var texto = savedInstanceState.getString("texto")

        var datos: ArrayList<Char> = ArrayList()

        if (texto != null) {
            for (i in 0 until texto.length) {

                datos.add(texto.get(i))
            }
            //cogemos en una variable el contenedor que es el recycleview
            val contenedorRecycler: RecyclerView = findViewById(R.id.listaRecycle)
            //creamos el adapter
            val adapter: MiRecyclerViewAdapter = MiRecyclerViewAdapter(this, datos)
            //a nuestro RecycleView le metemos el adapter creado que contiene la clase Miholder con todos los elementos
            contenedorRecycler.adapter = adapter
            //Establecemos el LayoutManager para que se ocupe de mostrar los datos de la lista
            contenedorRecycler.layoutManager = LinearLayoutManager(this)


        }
    }



    /**
     * Funcion para cambiar de ventana sobre nosotros
     */
    fun irSobreNosotros(view: View) {
        var intent:Intent=Intent(this,ActividadSobreNosotros::class.java)
        startActivity(intent)
    }

    /**
     * Funcion para cambiar de ventana sobre Ayudanos a mejorar
     */
    fun irAyuda(view: View) {
        var intent: Intent = Intent(this, ActividadAyudanos::class.java)
        startActivity(intent)
    }


}


