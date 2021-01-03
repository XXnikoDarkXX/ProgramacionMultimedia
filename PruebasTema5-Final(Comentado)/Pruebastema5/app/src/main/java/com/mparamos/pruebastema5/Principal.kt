package com.mparamos.pruebastema5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Principal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)
    }

    fun irAPruebasSQLite(view: View) {
        val intent: Intent =Intent(this,PruebasSQLite::class.java)
        startActivity(intent)
    }
}