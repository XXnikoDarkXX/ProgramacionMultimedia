package com.nicolasfernandez.pruebatema4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Pantalla2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla2)
    }

    fun irPantalla3(view: View) {

        var pasarActividad3: Intent =Intent(this,Pantalla3::class.java)//creamos una variable de tipo Intent(Activida)
        //de pantalla3
        this.startActivity(pasarActividad3)

    }
}