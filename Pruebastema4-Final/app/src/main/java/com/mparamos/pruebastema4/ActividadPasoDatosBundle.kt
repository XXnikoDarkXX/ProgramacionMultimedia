package com.mparamos.pruebastema4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast

class ActividadPasoDatosBundle : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.actividad_paso_datos_bundle)


        //Recepcion!!!!
        //debemos crear una variable de tipo bundle ?por si es null, y que = a intent.extras que es el bundle que guardamos en el envio del intent pasarActividad
        var bundle: Bundle? = this.intent.extras
        //con esto obtenemos el valor de la clave info1
        var info1: String? = bundle?.getString("info1")
        //con esto obtenemos el valor de la clave short1
        var short1: Short? = bundle?.getShort("short1")
        //Referenciamos a textoCentral una variable
        var textoCentral: TextView = findViewById<TextView>(R.id.textoCentral)
        //Por ultimo escribimos en textoCentral las variables que tenemos del bundle
        textoCentral.setText(info1 + " : " + short1)
        var arrayRecibido: ArrayList<String?>
        arrayRecibido = ArrayList<String?>()
        arrayRecibido = bundle?.getStringArrayList("ArrayPrimero") as ArrayList<String?>

        for (i in 0 until arrayRecibido.size) {
            var res:String?=arrayRecibido.get(i)
            Toast.makeText(this,"tamaño del array "+arrayRecibido.size+res, Toast.LENGTH_LONG).show()
        }

    }
}
