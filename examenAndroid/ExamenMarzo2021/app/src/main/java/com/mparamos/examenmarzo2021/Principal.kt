package com.mparamos.examenmarzo2021

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Source


class Principal : AppCompatActivity() {


    val firebaseDB: FirebaseFirestore = FirebaseFirestore.getInstance()
    val nombreyApellidos: EditText by lazy { findViewById<EditText>(R.id.campoNombreApellidosAlumno) }
    val dni: EditText by lazy { findViewById<EditText>(R.id.campoDNIAlumno) }
    val texto: EditText by lazy { findViewById<EditText>(R.id.campoMensajeAlProfesor) }
    val aprobat: Switch by lazy { findViewById<Switch>(R.id.campoAprobare) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.principal)

    }

    fun clickGuardar(view: View) {


        val selfRef = this


        if (!(this.nombreyApellidos.text.toString().equals("")||this.dni.text.toString().equals("")||this.texto.text.toString().equals(""))
        ) {
            val n: String = nombreyApellidos.text.toString()
            val d: String = dni.text.toString()
            val apro: Boolean
            val t: String = texto.text.toString()
            apro = aprobat.isChecked

            val alumno: Alumno = Alumno(n, d, t, apro)

            firebaseDB.collection("examen").document(d).set(alumno).addOnCompleteListener(this,
                OnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(selfRef, "Insertado correctamente", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(selfRef, "no fucka", Toast.LENGTH_LONG).show()
                    }
                })
        }else{
           Toast.makeText(selfRef,"falta un campo rellenado",Toast.LENGTH_LONG).show()
        }
    }

    fun clickConsultar(view: View) {
        val selfRef = this;
        if (!this.dni.text.toString().equals("")) {

            val docRef = firebaseDB.collection("examen")
                .document(this.dni.text.toString())//Hacemos referencia a la coleccion
            //y al documento de la firebase

            val source = Source.CACHE//creamos un source para el cache
            docRef.get(source).addOnCompleteListener { task ->

                if (task.isSuccessful) {
                    var resultados = task.result

                    if (resultados != null) {

                        var hola = resultados.data
                        var datos: ArrayList<String> = ArrayList<String>()

                        hola?.forEach {
                            it.key
                            it.value

                            Toast.makeText(
                                selfRef,
                                "" + it.key + "  " + it.value,
                                Toast.LENGTH_LONG
                            ).show()
                        }

                        //  Toast.makeText(selfRef,""+resultados.data,Toast.LENGTH_LONG).show()

                    }
                } else {
                    Toast.makeText(
                        selfRef,
                        "Vaya pasada que no funciona consultar",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }


        }else{

            Toast.makeText(selfRef,"Vaya pasada que no funciona consultar porque dni esta vacio",Toast.LENGTH_LONG).show()

        }
    }
}


