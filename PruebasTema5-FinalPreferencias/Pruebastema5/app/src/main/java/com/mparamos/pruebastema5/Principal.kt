package com.mparamos.pruebastema5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class Principal : Madre() {

    val campoEmail: EditText by lazy { findViewById<EditText>(R.id.campoEmail) }
    val campoPassword: EditText by lazy { findViewById<EditText>(R.id.campoPassword) }
    val panelUsuarioLogado:LinearLayout by lazy { findViewById<LinearLayout>(R.id.panelUsuarioLogado) }
    val  formularioRegitro:LinearLayout by lazy { findViewById<LinearLayout>(R.id.formularioRegistro) }
    val infoUsuario:TextView by lazy { findViewById<TextView>(R.id.infoUsuario) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)

        this.aplicarModoOscuro()
    }


    fun irAPruebasSQLite(view: View) {
        val intent: Intent = Intent(this, PruebasSQLite::class.java)
        startActivity(intent)
    }

    fun irAPruebasAlmacenamiento(view: View) {
        val intent: Intent = Intent(this, PruebasAlmacenamiento::class.java)
        startActivity(intent)
    }

    fun irAPruebasPreferencias(view: View) {
        val intent: Intent = Intent(this, PruebasPreferencias::class.java)
        startActivity(intent)
    }

    fun clickBotonRegistrarse(view: View) {
        this.formularioRegitro.visibility=View.INVISIBLE
        val estaActividad = this

        val accion = firebaseAuth.createUserWithEmailAndPassword(
            campoEmail.text.toString(),
            campoPassword.text.toString()
        )
        accion.addOnCompleteListener(this, object : OnCompleteListener<AuthResult> {
            override fun onComplete(p0: Task<AuthResult>) {
                if (accion.isSuccessful) {
                    Toast.makeText(estaActividad, ""+firebaseAuth.currentUser?.email+" "+resources.getString(R.string.registradoConExito), Toast.LENGTH_LONG).show()


                    infoUsuario.setText(infoUsuario.text.toString()+" "+firebaseAuth.currentUser?.email)
                    panelUsuarioLogado.visibility=View.VISIBLE
                }else{
                    if (p0.exception?.message.toString().contains("exits")){
                        Toast.makeText(estaActividad, ""+resources.getString(R.string.errorUsuarioYaExiste), Toast.LENGTH_LONG).show()
                    }

                    if (p0.exception?.message.toString().contains("formatted")){
                        Toast.makeText(estaActividad, ""+resources.getString(R.string.emailMalFormateado), Toast.LENGTH_LONG).show()

                    }
                   Log.d("excepcion", p0.exception.toString())
                    formularioRegitro.visibility=View.VISIBLE


                }
            }
        })

    }

    fun accionLogout(view: View) {

        firebaseAuth.signOut()
        panelUsuarioLogado.visibility=View.GONE

        formularioRegitro.visibility=View.VISIBLE
    }
    fun accionIniciarSesion(view: View) {
        var estaActividad=this;
        formularioRegitro.visibility=View.VISIBLE
        firebaseAuth.signInWithEmailAndPassword(this.campoEmail.text.toString(),this.campoPassword.text.toString()).addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
              //  infoUsuario.setText(infoUsuario.text.toString()+" "+firebase.currentUser?.email)
                formularioRegitro.visibility=View.GONE
                panelUsuarioLogado.visibility=View.VISIBLE
            }else{
                formularioRegitro.visibility=View.VISIBLE
                Toast.makeText(estaActividad,"Error iniciando sesion", Toast.LENGTH_LONG).show()
            }
        }


    }

    fun irAPruebasFireBase(view: View) {
        if(firebaseAuth.currentUser!=null){
            val i:Intent=Intent(this,PruebasFirebase::class.java)
            startActivity(i)
        }else{
            Toast.makeText(this,R.string.soloPasaLogado,Toast.LENGTH_LONG).show()
        }

    }
}