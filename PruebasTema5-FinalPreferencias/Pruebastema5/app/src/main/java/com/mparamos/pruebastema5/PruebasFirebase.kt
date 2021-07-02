package com.mparamos.pruebastema5

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import clases.Articulo
import clases.Usuario
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
        refrescarLista()
    }

    fun refrescarLista():Unit{
        var listaConsultas:ListView=findViewById<ListView>(R.id.listaConsultas)
        val selfRef=this
        //Si no esta vacia la coleccion la consultamos
        if (!coleccion.text.toString().isBlank()){
                firebaseDB.collection(coleccion.text.toString()).get().addOnCompleteListener {
                    task ->
                    if (task.isSuccessful){
                        var resultados=task.result?.documents
                    if (resultados!=null){

                        val adapter=ArrayAdapter(selfRef,android.R.layout.simple_list_item_1,resultados)
                        listaConsultas.adapter=adapter
                       /* for(elemento in resultados){
                            Toast.makeText(selfRef,elemento.getString("cl1"),Toast.LENGTH_LONG).show()

                        }*/
                    }

                    }else{
                        Toast.makeText(selfRef,task.exception?.message,Toast.LENGTH_LONG).show()
                    }

                }

        }

    }

    fun clickBotonConsultar(view: View) {

       /* var art:Articulo= Articulo("micro",50.3f,true)
        val selfRef:Activity=this;
        firebaseDB.collection(coleccion.text.toString()).document(documento.text.toString())
            .set(art).addOnCompleteListener(this,
            OnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText( selfRef,R.string.insercionOk,Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText( selfRef,R.string.insercionFallida,Toast.LENGTH_LONG).show()
                }
            })


        refrescarLista()
*/
consultarTodosDocumentos()

    }

    fun consultarTodosDocumentos(){
        firebaseDB.collection("pruebas")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {

                  Toast.makeText(this,document.toString(),Toast.LENGTH_LONG).show()

                }
            }
            .addOnFailureListener { exception ->
            }

    }
}