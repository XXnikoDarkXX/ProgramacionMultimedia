package com.nicolasfernandez.pruebatema4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast

class Pantalla2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla2)
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
            Toast.makeText(this,""+res,Toast.LENGTH_LONG).show()
        }

        fun irPantalla3(view: View) {

            var pasarActividad3: Intent =
                Intent(this, Pantalla3::class.java)//creamos una variable de tipo Intent(Activida)
            //de pantalla3
            this.startActivity(pasarActividad3)

        }
    }
}
