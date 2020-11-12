package com.nicolasfernandez.calculadora

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast

class ResultadoCalculo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado_calculo)
        //Recogemos en una nueva variable budle el anterior bundle  del main Principal
        var bun:Bundle?=this.intent.extras;
        var txtPanta2: TextView =findViewById<TextView>(R.id.numeroOperacionesAltos)//añadimos una variable a la id del  textoPrincipal
        var operacionesTotal: String? =bun?.getString("numeroOperaciones")
        Toast.makeText(this,""+operacionesTotal,Toast.LENGTH_LONG).show()
        txtPanta2.setText("NºOperaciones :"+operacionesTotal)
        var resultados:ArrayList<String?>?//Inicializamos el array
        resultados=ArrayList<String?>()//declaramos el array

        //metemos los colores de bandera recibida por el bundle
        resultados = bun?.getStringArrayList("arayTotales") as ArrayList<String?>
        

    }
}