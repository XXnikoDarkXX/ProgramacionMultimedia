package com.mparamos.pruebastema4

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat

class ActividadPermisos : AppCompatActivity() {
    val abajo:TextView by lazy{findViewById<TextView>(R.id.abajo)}
    val llamada: Intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:111"))
    companion object val PERMISO_LLAMADA=1000;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.actividad_permisos)
        var bund:Bundle?=this.intent.extras
        val texto:String?=bund?.getString("texto")
        val numero:Short?=bund?.getShort("numero")
        Toast.makeText(this,texto+" : "+numero,Toast.LENGTH_LONG).show()
        abajo.text=bund?.getString("texto")
    }

    fun pasarTexto(view: View) {
        val arriba: EditText =findViewById<EditText>(R.id.arriba)
        abajo.text=arriba.text.toString()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("textoAbajo",abajo.text.toString())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        abajo.text=savedInstanceState.getString("textoAbajo")
    }

    fun hacerLlamada(view: View) {
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
            == PackageManager.PERMISSION_GRANTED) {
            startActivity(llamada)
        }else{
            if(!ActivityCompat.shouldShowRequestPermissionRationale(
                            this,Manifest.permission.CALL_PHONE)) {
                Toast.makeText(this,R.string.llamadaDenegada,
                        Toast.LENGTH_LONG).show()
            }
            val permisos:Array<String> = arrayOf<String>(Manifest.permission.CALL_PHONE);
            ActivityCompat.requestPermissions(this,permisos,PERMISO_LLAMADA)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<out String>,
                                            grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode==PERMISO_LLAMADA){
            if(grantResults.size>0&&grantResults.get(0)==
                    PackageManager.PERMISSION_GRANTED){
                    startActivity(llamada)
            }else{
                //Manejarla como queramos.
                Toast.makeText(this,R.string.permisoDenegado,Toast.LENGTH_LONG).show()
            }
        }

    }


}