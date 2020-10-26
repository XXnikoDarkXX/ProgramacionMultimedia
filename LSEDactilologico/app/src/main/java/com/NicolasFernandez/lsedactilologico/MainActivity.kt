package com.NicolasFernandez.lsedactilologico

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import com.NicolasFernandez.lsedactilologico.R.drawable.ic_launcher_background

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var zonaSignos:LinearLayout =this.findViewById<LinearLayout>(R.id.zonaSignos)
        var cadena:String="Perro amarillo"
        for (i in 0 until cadena.length){
            cadena.get(i)
            var imagen:ImageView= ImageView(this)
            imagen.setImageResource(R.drawable.ic_launcher_foreground)

            zonaSignos.addView(imagen)
        }


    }
}