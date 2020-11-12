package com.nicolasfernandez.calculadora

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    var numerosMasAltos:ArrayList<String> = ArrayList()//Inicializamos el array


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    /**
     * Funcion para calcular dos numeros y elegir mediante el selector
     */
    fun CalcularOperacion(view: View) {
        //pasamos a una variable el contenido de las dos operaciones
        var operador1: EditText = findViewById(R.id.oper1)
        var operador2: EditText = findViewById(R.id.oper2)

        //cojemos el textView de resultado a una variable
        var resultado: TextView = findViewById(R.id.resultados)


        //Creamos variable Spinnner referenciando el selector
        var selectorOperador: Spinner = findViewById(R.id.selectorOperaciones)
        //añadimos la seleccion elegida en una variable String
        var tipoOperacion: String = selectorOperador.selectedItem.toString()

        //vamos a comprobar cual ha elegido mediante el string y convertiremos a int los operadores
        if (tipoOperacion.toLowerCase().equals("suma")) {
           var numero1:Int=operador1.text.toString().toInt()
           var numero2:Int=operador2.text.toString().toInt()
            var suma:Int=numero1+numero2

            resultado.setText(""+numero1+" + "+numero2+" = "+suma  )

            //Vaciamos los campos del operando 1 y operando 2
            operador1.setText("")
            operador2.setText("")
        } else if (tipoOperacion.equals("resta")) {
            var numero1:Int=operador1.text.toString().toInt()
            var numero2:Int=operador2.text.toString().toInt()
            var resta:Int=numero1-numero2
            resultado.setText(""+numero1+" - "+numero2+" = "+resta  )

            //Vaciamos los campos del operando 1 y operando 2
            operador1.setText("")
            operador2.setText("")
        } else if (tipoOperacion.equals("multiplicacion")) {
            var numero1:Int=operador1.text.toString().toInt()
            var numero2:Int=operador2.text.toString().toInt()
            var multiplicacion:Int=numero1*numero2
            resultado.setText(""+numero1+" * "+numero2+" = "+multiplicacion  )
            operador1.setText("")
            operador2.setText("")
        }else if(tipoOperacion.equals("division")){
            var numero1:Int=operador1.text.toString().toInt()
            var numero2:Int=operador2.text.toString().toInt()
            var division:Int=numero1/numero2
            resultado.setText(""+numero1+" / "+numero2+" = "+division  )
            operador1.setText("")
            operador2.setText("")
        }

        numerosMasAltos.add(resultado.toString())


    }

    fun enviarResultadosAltos(view: View) {
        //Creamos el Inten
        var pasarPantalla: Intent = Intent(this,ResultadoCalculo::class.java)//creamos la accion
        //Creamos el bundle
        var bun:Bundle=Bundle()//incializacion + declaracion

        //vamos a pasar primero total de numero de operaciones

        var nOperaciones:Int=1;
        //cada vez que le de al boton le meteremos uno al bundle
        bun.putString("numeroOperaciones",numerosMasAltos.size.toString())

        //te paso rapido el putStingARRy por si vale algo

        bun.putStringArrayList("arayTotales",numerosMasAltos)





        pasarPantalla.putExtras(bun)//al intent le enviamos el bundle

        this.startActivity(pasarPantalla)




    }
}