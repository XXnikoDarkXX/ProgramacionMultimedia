package com.nicolasfernandez.pruebatema4

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.view.children
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val contenedor:LinearLayout by lazy{ findViewById<LinearLayout>(R.id.contenedor)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Toast.makeText(this, "onCreate", Toast.LENGTH_LONG).show()
        this.getDrawable(R.drawable.ic_launcher_foreground)
        //Metemos una variable de tipo button cogiendo la id del boton de aniadir
        var botonAñadir:Button=findViewById(R.id.botonAniadir)
        //Añadimos un metodo para poder hacer un onclickListener
        //Al hacer click a este boton se le añade al contenedor otros botones
        var contexto: Context =this
        botonAñadir.setOnClickListener{
            vista:View->
            var nuevo:Button= Button(this)//creamos los botones (imaginario)
            nuevo.setText(R.string.botonAniadido)//le escribimos texto al boton
            contenedor.addView(nuevo)//se lo añadimos al contenedor para que se visualice


        }
    }
    //al iniciar  la aplicacion
    override fun onStart() {
        super.onStart()
        Toast.makeText(this, "onStart", Toast.LENGTH_LONG).show()

    }
    //te pone de nuevo en donde estabas en la app
    override fun onResume() {
        super.onResume()
        Toast.makeText(this, "onResume", Toast.LENGTH_LONG).show()

    }

    /**
     * Esto pone cuando minimas y abres la aplicacion
     */
    override fun onPause() {
        super.onPause()
        Toast.makeText(this, "onPause", Toast.LENGTH_LONG).show()

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Destruyendo", "destruyendo")
        Toast.makeText(this, "onDestroy", Toast.LENGTH_LONG).show()

    }
    //reinicia la app
    override fun onRestart() {
        super.onRestart()
        Toast.makeText(applicationContext, "onRestart", Toast.LENGTH_LONG).show()

    }

    fun siguienteActividad(view: View) {
        var pasarActividad: Intent=Intent(this,Pantalla2::class.java)//Guardamos la pantalla2 como tipo Intent
        //que sirve para realizar una accion
    //   this.startActivity(pasarActividad)//Sirve para lanzar la actividad y se ejecute luego todo el proceso, en este caso es cambiar de ventana
        //Esto era otra parte que es para cambiar de ventanas pero vamos a usar la misma variable de pasarActividad
 //     ------------------------------------------------------------------------
    //vamos a ENVIAR datos a un budle funciona como un mapa donde tenemos clave y valor, se puede meter cualquier cosa
        var bundle:Bundle=Bundle()//iniciamos la variable
        var campoTexti:EditText=findViewById<EditText>(R.id.campoTexto)//asignamos una variable al id.campoTexto
        var campoNumero:EditText=findViewById<EditText>(R.id.CampoNumero)//asignamos una variable al id.campoNumero

        //mediante el bundle podemos pasar la informacion de una actividad a otra.
        //--> ENVIO esto es la parte del envio de un bundle
        //bundle.putString("info1",""+campoTexto.text)//forma irregular o harcodeado
        bundle.putString("info1",campoTexti.text.toString())//metemos al bundle la clave y el valor que es el campoTexti

        //Metemos al bundle clave y valor que es la variable campoNumero
        bundle.putShort("short1",campoNumero.text.toString().toShort())


        var array2:ArrayList<String>;//declaracion
       array2=ArrayList<String>();//Lo inicializacion para que no sea null ahora mismo esta vacio
        array2.add("nico")//añadimos nico a la lista
        array2.add("hola")//añadimos hola a la lista



        bundle.putStringArrayList("ArrayPrimero",array2)
        pasarActividad.putExtras(bundle)//mediante este metodo metemos a pasarActividad que es un Intent el budle
        this.startActivity(pasarActividad)//y iniciamos la segunda pantalla + metemos el budle




    }

    fun irActiviadad3(view: View) {
        var pantalla4: Intent=Intent(this,Pantalla4Inicio::class.java)//Guardamos la pantalla2 como tipo Intent

        var bun:Bundle=Bundle()
        var campoTexto:EditText=findViewById(R.id.campoTexto)
        var campoNumero:EditText=findViewById(R.id.campoTexto)
        
        bun.putString("texto",campoTexto.text.toString())

        try {
            bun.putShort("numero", campoNumero.text.toString().toShort())

        }catch(e:Exception){
            bun.putShort("numero",-1)
        }
        pantalla4.putExtras(bun)

        this.startActivity(pantalla4)


    }

    /**
     * Funcion para guardar los los botones al cambiar horizontal la pantalla (girar pantalla)
     */
    override fun onSaveInstanceState(outState: Bundle){
        super.onSaveInstanceState(outState)
        Toast.makeText(this,"OnsaveInstanceState",Toast.LENGTH_LONG).show()
        var hijos:Sequence<View>
        hijos=contenedor.children

        val it:Iterator<View>//creamos un iterador para iterar el contenedor
        it=hijos.iterator()
        var botones:ArrayList<String> //meteremos en este  array los botones

        botones=ArrayList<String>();

        while(it.hasNext()){
           botones.add((it.next() as Button).text.toString())
        }
       outState.putStringArrayList("botones",botones)
    }

    /**
     * Funcion para escribir los botones al girar el movil
     */
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Toast.makeText(this,"onRestoreInstanceState",Toast.LENGTH_LONG).show()
        var botones: ArrayList<String>? = savedInstanceState.getStringArrayList("botones")//recogemos la informacion guardada del
        //onSaveInstanceState
      if (botones!=null) {
          for (i in 0 until botones.size) {
        var boton:Button=Button(this)
              boton.text=botones.get(i)
              contenedor.addView(boton)
          }

      }else{
          Toast.makeText(this,R.string.noPuedoRestaurar,Toast.LENGTH_LONG).show()
      }
    }


}