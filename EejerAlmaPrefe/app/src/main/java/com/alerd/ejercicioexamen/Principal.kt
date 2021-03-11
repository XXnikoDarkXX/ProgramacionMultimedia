package com.alerd.ejercicioexamen

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup

class Principal : Madre() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)

         this.aplicarModoOscuro()


    }


    fun IrAPruebasAlmacenamiento(view: View) {
        val intent: Intent =Intent(this,PruebasAlmacenamiento::class.java)
        startActivity(intent)
    }
    fun irAPruebasPreferencias(view: View) {
        val intent: Intent =Intent(this,Preferencias::class.java)
        startActivity(intent)
    }
}