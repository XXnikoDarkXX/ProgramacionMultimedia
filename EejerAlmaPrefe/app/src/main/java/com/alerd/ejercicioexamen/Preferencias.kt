package com.alerd.ejercicioexamen

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Switch
import android.widget.Toast


class Preferencias : Madre (){

    private val switchModoOscuro: Switch by lazy { findViewById<Switch>(R.id.switchModoOscuro) }
    private lateinit var preEditor: SharedPreferences.Editor


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preferencias)

        this.aplicarModoOscuro()

        preEditor=preferencias.edit()//Nos devuelve un objeto de tipo SharedPreferences.Editor, con el que se pueden introducir valores en el SharedPreferences.

        //Establecemos valor inicial de la preferencias
        //.getBoolean -> Busca la preferencia con esta clave, y si no la encuentra, devuelve el valor defValue
        val modoOscuro: Boolean = preferencias.getBoolean("modoOscuro", false)
        if (modoOscuro){
            switchModoOscuro.isChecked=true
        }

    }

    //Switch
    fun preferenciasModoOscuro(view: View) {
        if (switchModoOscuro.isChecked){
            preEditor.putBoolean("modoOscuro",true)// coloca en las preferencias esa clave y ese valor en el xml.
        }else{
            preEditor.putBoolean("modoOscuro", false)// coloca en las preferencias esa clave y ese valor en el xml.
        }

    }


    //Botton aplicar cambios
    fun aplicarCambiosPreferencias(view: View) {

        preEditor.commit()//Hace efectiva la escritura en el fichero de XML

        Toast.makeText(this,"Cambios aplicados", Toast.LENGTH_LONG).show()

        val intent: Intent = Intent(this,Principal::class.java)
        startActivity(intent)
    }
}