package com.mparamos.pruebastema5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class PreferenciasV2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preferencias_v2)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentPreferencias,
                PreferenciasV2Fragment()).commit()

    }
}