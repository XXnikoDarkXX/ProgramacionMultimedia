package com.mparamos.pruebastema5

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Spinner
import android.widget.Switch
import android.widget.Toast
import androidx.core.content.ContextCompat

class PruebasPreferencias : Madre() {
    private val switchModoOscuro:Switch by lazy{findViewById<Switch>(R.id.switchModoOscuro)}
    private val campoPrefFicheroTexto: EditText by lazy{
        this.findViewById<EditText>(R.id.campoPrefFicheroTexto)}
    private val spinnerPrefsAlmacenamientoDefecto: Spinner by lazy{
        this.findViewById<Spinner>(R.id.spinnerPrefsAlmacenamientoDefecto);
    }
    private lateinit var prefEditor:SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pruebas_preferencias)
        this.aplicarModoOscuro()
        prefEditor=preferencias.edit()
        val modoOscuro:Boolean=preferencias.getBoolean("modoOscuro",false)
        if(modoOscuro) {
            switchModoOscuro.isChecked = true;
        }

        val ficheroTextoDefecto:String?=preferencias.getString("ficheroTextoDefecto","");
        campoPrefFicheroTexto.setText(ficheroTextoDefecto);

        val modoAlmacenamientoDefecto:Int=
                preferencias.getInt("modoAlmacenamientoDefecto",0);
        spinnerPrefsAlmacenamientoDefecto.setSelection(modoAlmacenamientoDefecto)

   }

    fun preferenciaModoOscuro(view: View) {
        if(switchModoOscuro.isChecked){
            prefEditor.putBoolean("modoOscuro",true);
        }else{
            prefEditor.putBoolean("modoOscuro",false);
        }
    }

    fun aplicarCambiosPreferencias(view: View) {
        prefEditor.putString("ficheroTextoDefecto",campoPrefFicheroTexto.text.toString())
        prefEditor.putInt("modoAlmacenamientoDefecto",
                spinnerPrefsAlmacenamientoDefecto.selectedItemPosition)
        prefEditor.commit();
        Toast.makeText(this,R.string.cambiosAplicados,Toast.LENGTH_LONG).show()
        val intent: Intent =Intent(this,Principal::class.java)
        startActivity(intent);
    }

    fun irAPreferenciasClasicas(view: View) {
        val intent:Intent=Intent(this,PreferenciasV2Activity::class.java)
        startActivity(intent);
    }
}