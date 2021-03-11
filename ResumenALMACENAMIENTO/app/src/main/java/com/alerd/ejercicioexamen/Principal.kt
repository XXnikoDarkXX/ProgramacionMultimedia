package com.alerd.ejercicioexamen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Principal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)
    }

    fun IrAPruebasAlmacenamiento(view: View) {
        val intent: Intent =Intent(this,PruebasAlmacenamiento::class.java)
        startActivity(intent)
    }
    fun irAPruebasPreferencias(view: View) {

    }
}