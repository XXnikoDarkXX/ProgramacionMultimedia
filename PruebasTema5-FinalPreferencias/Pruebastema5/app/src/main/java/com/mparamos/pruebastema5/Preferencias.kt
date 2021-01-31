package com.mparamos.pruebastema5

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.Toast
import androidx.core.content.ContextCompat

class Preferencias : Madre() {
    private val switchModoOscuro:Switch by lazy<Switch>{findViewById(R.id.switchModoOscuro)}
    private lateinit var  prefEditor:SharedPreferences.Editor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preferencias)
        this.aplicarModoOscuro()

        prefEditor=preferencias.edit()//inicializamos prefEditor

        val modoOscuro:Boolean=preferencias.getBoolean("modoOscuro",false)
        if (modoOscuro){
            switchModoOscuro.isChecked=true;

        }
        /*//si la preferencia modooscuro esta activa, coloco el modo Oscuro
       val elementoConFondo: ViewGroup=findViewById<ViewGroup>(R.id.elementoConFondo)
        elementoConFondo.setBackgroundColor(ContextCompat.getColor(this,R.color.transparentBlaack))
*/
    }


     fun preferenciasModoOscuro(view: View){

         if (switchModoOscuro.isChecked){
             prefEditor.putBoolean("modoOscuro",true)
         }else{
             prefEditor.putBoolean("modoOscuro",false)
         }
    }

    fun aplicarCambiosPreferencias(view: View){
        prefEditor.commit()//nos crea el fichero xml de preferencias la primera vez que aplicamos los cambios lo podemos ver en data data com...
        Toast.makeText(this,R.string.cambiosAplicados,Toast.LENGTH_LONG).show()
        val intent:Intent=Intent(this,Principal::class.java)
        startActivity(intent)
    }
}