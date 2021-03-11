package com.mparamos.pruebastema5

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore

abstract class Madre : AppCompatActivity() {
    lateinit var preferencias:SharedPreferences
  companion object{  val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
        val firebaseDB:FirebaseFirestore= FirebaseFirestore.getInstance()}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun aplicarModoOscuro(){
        preferencias=this.getSharedPreferences("Preferencias", Context.MODE_PRIVATE);

        //Establecemos valor inicial de las preferencias
        val modoOscuro:Boolean=preferencias.getBoolean("modoOscuro",false)
        if(modoOscuro){
            val elementoConFondo: ViewGroup =findViewById<ViewGroup>(R.id.elementoConFondo)
            elementoConFondo.setBackgroundColor(
                ContextCompat.getColor(this,
                    R.color.transparentBlack))
            ponerTextosClaros(findViewById(R.id.botonRegistrado))
        }
    }

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
                        ContextCompat.getColor(this,
                        R.color.white))
                } catch (e: Exception) {
                    //Si falla, no hago nada, y continúo el programa. Simplemente esa vista no era un textView
                }
            }
        }
    }
}