package com.nicolasfernandez.pruebatema4

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import java.security.Permission


class Pantalla4Inicio : AppCompatActivity() {
    val abajo:TextView by lazy{findViewById<TextView>(R.id.abajo)}//variable interna y usamos lazy para que no se inicialice se cree
    //hasta que no entremos en el oncreate
    val llamada:Intent= Intent(Intent.ACTION_CALL, Uri.parse("tel:1656"))
    companion object val PERMISO_LLAMADA=1000
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        this.setContentView(R.layout.layout_pantalla4)

        var bund:Bundle?=this.intent.extras
        var texto:String?=bund?.getString("texto")

            val numero: Short? = bund?.getShort("numero")

        Toast.makeText(this,"p"+texto+" : "+numero,Toast.LENGTH_LONG).show()
        abajo.text=bund?.getString("texto")//le  metemos a nuestro textview
    }




    fun pasarTexto(view: View) {
        val arriba:EditText=findViewById<EditText>(R.id.arriba)
        abajo.text=arriba.text.toString()

    }
    override fun onSaveInstanceState(outState: Bundle){
        super.onSaveInstanceState(outState)
        Toast.makeText(this,"OnsaveInstanceState",Toast.LENGTH_LONG).show()
        outState.putString("textoAbajo",abajo.text.toString())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        abajo.text=savedInstanceState.getString("textoAbajo")
    }

    fun hacerLlamada(view: View) {
        if (ActivityCompat.checkSelfPermission(this,Manifest.permission.CALL_PHONE)==PackageManager.PERMISSION_GRANTED){

            startActivity(llamada)
        }else{
            //si establezo la llamada en negativo me dara este error
            if(!ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.CALL_PHONE)){
                Toast.makeText(this,R.string.llamadaDenegada,Toast.LENGTH_LONG).show()
            }

            var permisos:Array<String> = arrayOf<String>(Manifest.permission.CALL_PHONE)

            ActivityCompat.requestPermissions(this,permisos,PERMISO_LLAMADA)
        }




    }
    //cuando yo muestre el dialogo de permisos si le he dado permiso me la hace

    //Esto se ejecutara y mostrara el error cuando no se establece por defecto la llamda
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode==PERMISO_LLAMADA){
            if(grantResults.size>0&&grantResults.get(0)==PackageManager.PERMISSION_GRANTED){
                startActivity(llamada)
            }else{
                //manejarla como Queramos
                Toast.makeText(this,R.string.permisoDenegado,Toast.LENGTH_LONG).show()
            }
        }
    }




}