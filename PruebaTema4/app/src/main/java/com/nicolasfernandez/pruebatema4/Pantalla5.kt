package com.nicolasfernandez.pruebatema4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import org.w3c.dom.Text

class Pantalla5 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla5)
        //obtenemos el viewgroup que vamos a inflar en este caso es el layout de Pantalla4
      var inflarAqui:  ConstraintLayout = findViewById<ConstraintLayout>(R.id.inflarAqui)
        val inflater:LayoutInflater=this.layoutInflater //metemos una variable inflater para insertar nuestro xml en otro y nos devuelva
        //una vista
       // inflater.inflate(R.layout.parainflar,inflarAqui)//le insertamos para inflar xml al layout a pantalla5
        //Podemos inflar meter todo un layout a otro o meter cosas de un layout
        //1º FORMA mas utilizada para hacerlo manual
       // val tv:ConstraintLayout=inflater.inflate(R.layout.parainflar,inflarAqui ) as ConstraintLayout
       // tv.setBackgroundColor(resources.getColor(R.color.azulUltramar))

        //2ºForma
        /*
        val tv:ConstraintLayout=inflater.inflate(R.layout.parainflar,null ) as ConstraintLayout//preparamos para inflar
        inflarAqui.addView(tv)//decimos donde vamos a inflar
         tv.setBackgroundColor(resources.getColor(R.color.azulUltramar))*/

        //2º FORMA usamos el tercer argumento por defecto es a false y ya podemos ir elegiendo que inflar
        val tv:ConstraintLayout=inflater.inflate(R.layout.parainflar,inflarAqui,false ) as ConstraintLayout
        inflarAqui.addView(tv)//decimos a donde queremos inflar
        tv.setBackgroundColor(resources.getColor(R.color.azulUltramar))



    }
}