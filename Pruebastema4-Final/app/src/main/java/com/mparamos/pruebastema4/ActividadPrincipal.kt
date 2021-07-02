package com.mparamos.pruebastema4

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.view.children

class ActividadPrincipal : AppCompatActivity() {

    val contenedor:LinearLayout by lazy{ findViewById<LinearLayout>(R.id.contenedor)}//LinearLayout con el fondo azul donde añadimos
    //los botones

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.actividad_principal)
        Toast.makeText(this,"onCreate",Toast.LENGTH_LONG).show()
        this.getDrawable(R.drawable.ic_launcher_foreground)
        var botonAniadir=findViewById<Button>(R.id.botonAniadir);
        //como usar un contexto y como usar onClickListener
        var contexto: Context =this
        botonAniadir.setOnClickListener{
            var nuevo:Button=Button(contexto);
            nuevo.setText(R.string.botonAniadido);
            contenedor.addView(nuevo);
            Toast.makeText(contexto,""+contenedor.childCount,Toast.LENGTH_LONG).show()
        }


    }
    //al inicia la aplicacion
    override fun onStart() {
        super.onStart()
        Toast.makeText(this,"onStart",Toast.LENGTH_LONG).show()

    }
    //Te pone de nuevo en dode estabas en la app
    override fun onResume() {
        super.onResume()
        Toast.makeText(this,"onResume",Toast.LENGTH_LONG).show()
    }
    //Esto se pone cuando minimizas pone de nuevo en donde estabas en la app
    override fun onPause() {
        super.onPause()
        Log.d("Pausando","pausando")
        Toast.makeText(this,"onPause",Toast.LENGTH_LONG).show()
    }

    override fun onStop() {
        super.onStop()
        Toast.makeText(this,"onStop",Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Destruyendo","destruyendo")
        Toast.makeText(this,"onDestroy",Toast.LENGTH_LONG).show()
    }
    //reinicia la app
    override fun onRestart() {
        super.onRestart()
        Toast.makeText(applicationContext,"onRestart",Toast.LENGTH_LONG).show()
    }

    /**
     * Funcion para guardar  los botones al cambiar horizontal la pantalla (girar pantalla)
     */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Toast.makeText(this,"onSaveInstanceState",Toast.LENGTH_LONG).show()
        var hijos:Sequence<View> =contenedor.children //creamos una variable hijo de tipo secuencia que contiene vistas
        //Agregamos a hijos los botones que contiene el (contenedor que es un Layout)

        val it:Iterator<View>//Creamos un iterador para iterar el contenedor
        it=hijos.iterator()
        var botones:ArrayList<String>;//meteremos en este array los botones

        botones=ArrayList<String>();

        while(it.hasNext()){
            botones.add((it.next() as Button).text.toString())//Metemos todos los botones hijos en la variable botones
        }
        outState.putStringArrayList("botones",botones)//Se lo agregamos al bundle
    }

    /**
     * Funcion para escribir los botones al girar el movil
     */
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Toast.makeText(this,"onRestoreInstanceState",Toast.LENGTH_LONG).show()
        var botones: ArrayList<String>?=savedInstanceState.getStringArrayList("botones")//recogemos la informacion
        //guardada del onSaveInstanceState
        if(botones!=null){
            for (i:Int in 0 until botones.size){
                var boton:Button=Button(this);//creamos un boton normal dentro del for para que cuando vaya iterando se vaya creando uno a uno
                boton.text=botones.get(i)//Cogemos la variable boton y le añadimos el texto
                contenedor.addView(boton)//al contenedor le añadimos el boton
            }
        }else{
            Toast.makeText(this,R.string.noPuedoRestaurar,Toast.LENGTH_LONG).show()
        }
    }

    fun siguienteActividad(view: View) {
        var pasarActividad: Intent=Intent(this,ActividadPasoDatosBundle::class.java)//Guardamos ActividadDatosBundle   como tipo Intent
        //que sirve para realizar una accion
        //   this.startActivity(pasarActividad)//Sirve para lanzar la actividad y se ejecute luego todo el proceso, en este caso es cambiar de ventana
        //Esto era otra parte que es para cambiar de ventanas pero vamos a usar la misma variable de pasarActividad
        //     ------------------------------------------------------------------------
        //vamos a ENVIAR datos a un budle funciona como un mapa donde tenemos clave y valor, se puede meter cualquier cosa
        var bundle:Bundle=Bundle()//iniciamos la variable
        var campoTexti:EditText=findViewById<EditText>(R.id.campoTexto)//asignamos una variable al id.campoTexto
        var campoNumero:EditText=findViewById<EditText>(R.id.campoNumero)//asignamos una variable al id.campoNumero

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

    fun irAActividad3(view: View) {
        var intent:Intent=Intent(this,ActividadPermisos::class.java)
        var bun:Bundle=Bundle()
        var campoTexto:EditText=findViewById(R.id.campoTexto)
        var campoNumero:EditText=findViewById(R.id.campoNumero)
        bun.putString("texto",campoTexto.text.toString())
        try {
            bun.putShort("numero", campoNumero.text.toString().toShort())
        }catch(e:Exception){
            bun.putShort("numero", -1)
        }
        intent.putExtras(bun)
        this.startActivity(intent)
    }

    fun irAActividad4(view: View) {
        val intent:Intent=Intent(this,ActividadLayoutInflater::class.java)
        startActivity(intent)
    }

    fun irAActividadArrayAdapter(view: View) {
        val intent:Intent=Intent(this,ActividadArrayAdapter::class.java)
        startActivity(intent)
    }

    fun irAActividadListView(view: View) {
        val intent:Intent=Intent(this,ActividadListView::class.java)
        startActivity(intent)
    }

    fun irActividadRecyclerView(view: View) {
        val intent:Intent=Intent(this,ActividadRecyclerView::class.java)
        startActivity(intent)
    }




}