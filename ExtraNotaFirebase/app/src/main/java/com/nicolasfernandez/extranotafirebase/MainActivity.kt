package com.nicolasfernandez.extranotafirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Source
import com.nicolasfernandez.extranotafirebase.clases.Articulo

class MainActivity : AppCompatActivity() {

    val firebaseDB: FirebaseFirestore = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    /**
     * Mediante esta funcion insertamos y actualizamos la coleccion prueba, documento b a los valores de una clase Articulo
     */
    fun clickBotonInsertar(view: View) {

        val nombre: EditText = findViewById<EditText>(R.id.txtNombreSql)
        val precio: EditText = findViewById<EditText>(R.id.txtPrecioSql)
        val enVenta: Switch = findViewById<Switch>(R.id.switchEnVentaSql)
        //val nombre:String,val precio:Float,val enVenta:Boolean
        val n: String = nombre.text.toString()
        val p: Float = precio.text.toString().toFloat()

        val venta: Boolean;
        venta = enVenta.isChecked


        val arti: Articulo = Articulo(n, p, venta)
        val selfRef = this;
        firebaseDB.collection("prueba").document("b").set(arti).addOnCompleteListener(this,
            OnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(selfRef, "Insertado correctamente", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(selfRef, "no fucka", Toast.LENGTH_LONG).show()
                }
            })
    }

    /**
     * Funcion para consultar una coleccion y obtener los valores de un documento
     */
    fun clickBotonConsultar(view: View) {
        /*  val selfRef=this;
        val docRef = firebaseDB.collection("prueba").document("b")//Hacemos referencia a la coleccion
        //y al documento de la firebase
        var lista:ListView=findViewById<ListView>(R.id.listaConsulta)
        val source = Source.CACHE//creamos un source para el cache
        docRef.get(source).addOnCompleteListener { task->

            if (task.isSuccessful){
                var resultados=task.result
          
                if (resultados!=null){

                  var hola=  resultados.data
                    var datos:ArrayList<String> = ArrayList<String>()

                  hola?.forEach{
                      it.key
                      it.value
                      datos.add(it.key+" : "+it.value)
                      Toast.makeText(selfRef,""+it.key+"  "+it.value,Toast.LENGTH_LONG).show()
                  }
                    val adapter=ArrayAdapter(selfRef,android.R.layout.simple_list_item_1,datos)
                    lista.adapter=adapter
              //  Toast.makeText(selfRef,""+resultados.data,Toast.LENGTH_LONG).show()

                }
            }else{
                Toast.makeText(selfRef,"Vaya pasada que no funciona consultar",Toast.LENGTH_LONG).show()
            }
        }
*/
        consultaPrueba()
    }

    /**
     * Funcion para borrar el documento de una coleccion
     */
    fun clickBotonBorrar(view: View) {
        val selfRef = this;
        firebaseDB.collection("prueba").document("a").delete().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(
                    selfRef,
                    "Se ha borrado el documento a correctamente",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                Toast.makeText(selfRef, "Algo ha pasado !!", Toast.LENGTH_LONG).show()

            }
        }
    }

    fun clickBotonIrSqlLite(view: View) {

        val intent: Intent = Intent(this, PruebaSql::class.java)
        startActivity(intent)
    }


    fun consultaPrueba() {
       /* val docRef = firebaseDB.collection("prueba").document("a")
        docRef.get().addOnSuccessListener { documentSnapshot ->
            val city = documentSnapshot.get
*/


        firebaseDB.collection("prueba")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                //    Toast.makeText(this,""+document.data,Toast.LENGTH_LONG).show()
                val articul= document.toObject(Articulo::class.java)
                    Toast.makeText(this,""+articul.toString(),Toast.LENGTH_LONG).show()
                    println("")
                //    Log.d(TAG, "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                //Log.d(TAG, "Error getting documents: ", exception)
            }

    }

}