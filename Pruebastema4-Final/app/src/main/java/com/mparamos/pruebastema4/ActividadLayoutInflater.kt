package com.mparamos.pruebastema4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout

/**
 * En esta actividad cogemos inflamos(cogemos el layout de actividad_layoutinflater) y se lo ponemos a parainflar
 */
class ActividadLayoutInflater : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.actividad_layoutinflater)
        //obtenemos el el contraintView que vamos a inflar en este caso es el layout de actividad_layoutinflater
        //Es decir el padre
        val inflarAqui:ConstraintLayout=findViewById<ConstraintLayout>(R.id.inflarAqui)

        val inflater: LayoutInflater =this.layoutInflater//metemos una variable inflater para insertar nuestro xml en otro y nos devuelva
        //una vista

        /*
        //Podemos inflar meter todo un layout a otro o meter cosas de un layout
        //1º FORMA mas utilizada para hacerlo manual
         inflater.inflate(R.layout.parainflar,inflarAqui)//el primer parametro es el hijo donde inflamos(lo ponemos) al segundo parametro que es el padre o actividad que estamos usando
        val tv:ConstraintLayout=inflater.inflate(R.layout.parainflar,inflarAqui ) as ConstraintLayout //lo pasamos todo a una varialble de tipo Constraint para poder manejarla
         tv.setBackgroundColor(resources.getColor(R.color.verceClaro))//le metemos el fondo a verde
        */

         /*
        //2ºForma
        //creamos una variable tv donde le meteremos lo queremos inflar( coger) si le ponemos el segundo parametro null (que es el padre donde vamos a meter) no se visualizara ningun cambio hasta que no lo metamos
        val tv:ConstraintLayout=inflater.inflate(R.layout.parainflar,null ) as ConstraintLayout//preparamos para inflar

        inflarAqui.addView(tv)//usando inflarAqui que es el padre le añadimos un view que es el hijo lo que vamos a inflar
         tv.setBackgroundColor(resources.getColor(R.color.purple_200))//le metemos un color
        */







        //3º FORMA usamos el tercer argumento por defecto es a false y ya podemos ir elegiendo que inflar
        //creamos una variable tv donde le meteremos lo queremos inflar( coger) si le ponemos el segundo parametro el padre que es a donde lo vamos a mete le ponemos false esto significa que no lo añadimos visualizamos
        //solo lo prepara
        val tv: ConstraintLayout =inflater.inflate(R.layout.parainflar,inflarAqui,false) as ConstraintLayout
        tv.setBackgroundColor(resources.getColor(R.color.teal_200))

        inflarAqui.addView(tv)//ahora lo metemos
    }
}