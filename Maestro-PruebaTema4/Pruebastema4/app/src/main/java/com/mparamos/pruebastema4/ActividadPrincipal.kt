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

    val contenedor:LinearLayout by lazy{ findViewById<LinearLayout>(R.id.contenedor)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.actividad_principal)
        Toast.makeText(this,"onCreate",Toast.LENGTH_LONG).show()
        this.getDrawable(R.drawable.ic_launcher_foreground)
        var botonAniadir=findViewById<Button>(R.id.botonAniadir);
        var contexto: Context =this
        botonAniadir.setOnClickListener{
            var nuevo:Button=Button(contexto);
            nuevo.setText(R.string.botonAniadido);
            contenedor.addView(nuevo);
            Toast.makeText(contexto,""+contenedor.childCount,Toast.LENGTH_LONG).show()
        }


    }

    override fun onStart() {
        super.onStart()
        Toast.makeText(this,"onStart",Toast.LENGTH_LONG).show()

    }

    override fun onResume() {
        super.onResume()
        Toast.makeText(this,"onResume",Toast.LENGTH_LONG).show()
    }

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

    override fun onRestart() {
        super.onRestart()
        Toast.makeText(applicationContext,"onRestart",Toast.LENGTH_LONG).show()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Toast.makeText(this,"onSaveInstanceState",Toast.LENGTH_LONG).show()
        var hijos:Sequence<View> =contenedor.children
        val it:Iterator<View>
        it=hijos.iterator()
        var botones:ArrayList<String>;
        botones=ArrayList<String>();
        while(it.hasNext()){
            botones.add((it.next() as Button).text.toString())
        }
        outState.putStringArrayList("botones",botones)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Toast.makeText(this,"onRestoreInstanceState",Toast.LENGTH_LONG).show()
        var botones: ArrayList<String>?=savedInstanceState.getStringArrayList("botones")
        if(botones!=null){
            for (i:Int in 0 until botones.size){
                var boton:Button=Button(this);
                boton.text=botones.get(i)
                contenedor.addView(boton)
            }
        }else{
            Toast.makeText(this,R.string.noPuedoRestaurar,Toast.LENGTH_LONG).show()
        }
    }

    fun siguienteActividad(view: View) {
        var pasarActividad: Intent=Intent(this,ActividadPasoDatosBundle::class.java)
        var bundle:Bundle=Bundle()
        val campoTexto:EditText=findViewById(R.id.campoTexto)
        val campoNumero:EditText=findViewById(R.id.campoNumero)
        bundle.putString("info1",campoTexto.text.toString())
        bundle.putShort("short1",campoNumero.text.toString().toShort())
        pasarActividad.putExtras(bundle)
        this.startActivity(pasarActividad)
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


}