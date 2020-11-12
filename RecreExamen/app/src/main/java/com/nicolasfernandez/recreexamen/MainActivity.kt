package com.nicolasfernandez.recreexamen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    val textoPrinci:TextView by lazy{findViewById<TextView>(R.id.textoPrincipal)}



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }
    fun EnviarOpcionElegida(view: View) {

        var rdOpcion1:RadioButton=findViewById<RadioButton>(R.id.Opcion1)//Creamos una variable RadioButton indicando la id de la opcion1

        var estadoOpcion1:Boolean=rdOpcion1.isChecked;//el estado true si esta pinchado
        //--------------------------------------------------


        var rdOpcion2:RadioButton=findViewById<RadioButton>(R.id.Opcion2)//Creamos una variable RadioButton indicando la id de la opcion1

        var estadoOpcion2:Boolean=rdOpcion2.isChecked;//el estado true si esta pinchado
        //--------------------------------------------------


        var rdOpcion3:RadioButton=findViewById<RadioButton>(R.id.Opcion3)//Creamos una variable RadioButton indicando la id de la opcion1

        var estadoOpcion3:Boolean=rdOpcion3.isChecked;//el estado true si esta pinchado
        //--------------------------------------------------
        var bun:Bundle=Bundle()//incializacion + declaracion
        if (estadoOpcion1==true){
            textoPrinci.setText(R.string.hombre)
            bun.putString("opcionElegida", "hombre")

        }else if(estadoOpcion2==true){
            textoPrinci.setText(R.string.mujer)
            bun.putString("opcionElegida", "mujer")

        }else if(estadoOpcion3==true){
            textoPrinci.setText(R.string.otros)
            bun.putString("opcionElegida","Otros")

        }


        //Creamos variable Spinnner referenciando el selector
        var selectorPais:Spinner=findViewById(R.id.selectorBandera)

        //añadimos el nombre del pais que es opcion elegida del selector en un String
        var txtBandera:String=selectorPais.selectedItem.toString()

        //vamos a crear un arrayList donde meteremos los colores de la bandera de los paises
        var coloresBandera:ArrayList<String?>//Inicializamos el array

        coloresBandera=ArrayList<String?>()//declaramos el array

        if(txtBandera.toLowerCase().equals("españa")){
            coloresBandera.add("Rojo")
            coloresBandera.add("Amarillo")
            coloresBandera.add("Rojo")

        }else if(txtBandera.toLowerCase().equals("francia")){
            coloresBandera.add("Azul")
            coloresBandera.add("Blanco")
            coloresBandera.add("Rojo")
        }else if(txtBandera.toLowerCase().equals("portugal")){
            coloresBandera.add("verde")
            coloresBandera.add("Rojo")
        }else if(txtBandera.toLowerCase().equals("alemania")){
            coloresBandera.add("Negro")
            coloresBandera.add("Rojo")
            coloresBandera.add("Amarillo")
        }

        bun.putStringArrayList("ArrayBandera",coloresBandera)//metemos al bundle el array de colores

       var pasarPantalla:Intent=Intent(this,Pantalla2::class.java)//creamos la accion

        pasarPantalla.putExtras(bun)//al intent le enviamos el bundle

        this.startActivity(pasarPantalla)



    }


}