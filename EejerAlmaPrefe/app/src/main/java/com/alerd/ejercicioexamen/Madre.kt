package com.alerd.ejercicioexamen

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import java.lang.Exception


abstract class Madre : AppCompatActivity() {
    lateinit var preferencias: SharedPreferences//variables para preferencia

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    /**
     * Funcion que obtiene el valor del xml modoOscuro si es false ponermos claro el fondo si es trues ponemos el fondo oscuro
     * lo mismo para los textView
     */
    fun aplicarModoOscuro(){
        preferencias=this.getSharedPreferences("Preferencias", Context.MODE_PRIVATE);

        //Establecemos valor inicial de las preferencias
        val modoOscuro:Boolean=preferencias.getBoolean("modoOscuro", false)
        if(modoOscuro){

            val elementoConFondo: ViewGroup =findViewById<ViewGroup>(R.id.elementoConFondo)
       elementoConFondo.setBackgroundColor(ContextCompat.getColor(this,R.color.transparentBlack))

            ponerTextosClaros(findViewById(R.id.contenedorPrincipal))


        }

    }


    /**
     * Poner los textos claros
     */
    private fun ponerTextosClaros(v: View) {
        val viewgroup = v as ViewGroup
        for (i in 0 until viewgroup.childCount) {
            val v1: View = viewgroup.getChildAt(i)
            if (v1 is ViewGroup){
                ponerTextosClaros(v1)
            }else {
                try {
                    //Intento convertir a TextView
                    (v1 as TextView).setTextColor(
                            ContextCompat.getColor(
                                    this,
                                    R.color.white
                            )
                    )
                } catch (e: Exception) {
                    //Si falla, no hago nada, y continúo el programa. Simplemente esa vista no era un textView
                }
            }
        }
    }


}