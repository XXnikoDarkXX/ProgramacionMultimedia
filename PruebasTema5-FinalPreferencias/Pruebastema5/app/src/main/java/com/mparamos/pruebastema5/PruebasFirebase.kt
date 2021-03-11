package com.mparamos.pruebastema5

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener

class PruebasFirebase : Madre() {
    val clave1:EditText by lazy { findViewById<EditText>(R.id.campoClave1) }
    val valor1:EditText by lazy { findViewById<EditText>(R.id.campoValor1) }
    val clave2:EditText by lazy { findViewById<EditText>(R.id.campoClave2) }
    val valor2:EditText by lazy { findViewById<EditText>(R.id.campoValor2) }
    val coleccion:EditText by lazy { findViewById<EditText>(R.id.campoColeccion) }
    val documento:EditText by lazy { findViewById<EditText>(R.id.campoNombreDocumento) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pruebas_firebase)

    }

    fun guardar(view: View) {

        val datos:HashMap<String,Any> = HashMap<String,Any>()
        datos.put(clave1.text.toString(),valor1.text.toString())
        datos.put(clave2.text.toString(),valor2.text.toString())
        val selfRef:Activity=this;
        firebaseDB.collection(coleccion.text.toString()).document(documento.text.toString()).set(datos).addOnCompleteListener(this,
            OnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText( selfRef,R.string.insercionOk,Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText( selfRef,R.string.insercionFallida,Toast.LENGTH_LONG).show()
                }
            })
    }
}