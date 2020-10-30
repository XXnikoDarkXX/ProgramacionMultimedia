package com.nicolasfernandez.pruebatema4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Toast.makeText(this, "onCreate", Toast.LENGTH_LONG).show()
        this.getDrawable(R.drawable.ic_launcher_foreground)
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
        Toast.makeText(this, "onRestart", Toast.LENGTH_LONG).show()

    }

    fun siguienteActividad(view: View) {
        var pasarActividad: Intent=Intent(this,Pantalla2::class.java)//Guardamos la pantalla2 como tipo Intent
        //que sirve para realizar una accion
        this.startActivity(pasarActividad)//Sirve para lanzar la actividad y se ejecute luego todo el proceso

    }
}