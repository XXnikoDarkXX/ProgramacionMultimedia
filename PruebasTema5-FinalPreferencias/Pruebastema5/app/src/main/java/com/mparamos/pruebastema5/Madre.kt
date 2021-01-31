package com.mparamos.pruebastema5

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat

abstract class Madre : AppCompatActivity() {
   lateinit var  preferencias: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun aplicarModoOscuro(){
        preferencias=this.getSharedPreferences("Preferencias", Context.MODE_PRIVATE)

        //Establecemos valor inicial de las preferencias
        val modoOscuro:Boolean=preferencias.getBoolean("modoOscuro",false)
        if (modoOscuro){
            val elementoConFondo: ViewGroup =findViewById<ViewGroup>(R.id.elementoConFondo)
            elementoConFondo.setBackgroundColor(ContextCompat.getColor(this,R.color.transparentBlaack))
            ponerTextosClaros(findViewById(R.id.elementoRaiz))
        }


    }
    private fun ponerTextosClaros(v: View){
        val viewGroup=v as ViewGroup
        for (i in 0 until viewGroup.childCount){
            val v1: View = viewGroup.getChildAt(i)
        if (v1 is ViewGroup){
            ponerTextosClaros(v1)
        }else{
            try{
                (v1 as TextView).setTextColor(ContextCompat.getColor(this,R.color.white))
            }catch (e:Exception){
                //Si falla, no hago nada ya continuo el programa simplemente esa vista no era un textview
            }
        }
        }
    }
}