package com.mparamos.pruebastema4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ActividadPasoDatosBundle : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.actividad_paso_datos_bundle)
        var bundle: Bundle? =this.intent.extras
        val info1:String?= bundle?.getString("info1")
        val short1:Short?= bundle?.getShort("short1")
        var textoCentral:TextView=findViewById<TextView>(R.id.textoCentral)
        textoCentral.setText(info1+" : "+short1)
    }
}
