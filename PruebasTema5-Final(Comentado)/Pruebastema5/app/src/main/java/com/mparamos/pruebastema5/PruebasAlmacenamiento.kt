package com.mparamos.pruebastema5

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import java.io.OutputStreamWriter

class PruebasAlmacenamiento : AppCompatActivity() {
    val spinnerLugarAlmacenamiento:Spinner by lazy { this.findViewById<Spinner>(R.id.spinnerLugarAlmacenamiento) }//variable para usar el spinner
    val switchModoApertura:Switch by lazy { this.findViewById<Switch>(R.id.switchTipoApertura) }//variable declarada para utilizar cuando quiera
    val campoNombreFichero:EditText by lazy { this.findViewById<EditText>(R.id.campoNombreFichero) }
    val campoContenido:EditText by lazy { this.findViewById<EditText>(R.id.campoContenidoArchivo) }
    val botonGuardar:Button by lazy { this.findViewById<Button>(R.id.botonGuardar) }
    val rutaActual:TextView by lazy { this.findViewById<TextView>(R.id.rutaActual) }
    val archivosDisponibles:ListView by lazy { this.findViewById<ListView>(R.id.archivosDisponibles) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pruebas_almacenamiento)
        switchModoApertura.setOnCheckedChangeListener(
                CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
                    if (isChecked){
                        switchModoApertura.setText(R.string.concatenarContenido)
                    }else{
                        switchModoApertura.setText(R.string.machacarFichero)
                    }


        })
        //mostrar la ruta actual, de inicio siempre en el almacenamiento interno.

       rutaActual.setText(this.resources.getString(R.string.rutaActual)+this.filesDir.toString())
        recargarListaArchivos()
    }
    fun recargarListaArchivos(){

        //listar en la parte derecha todos los ficheros disponibles

        val archivos=applicationContext.fileList()
        val adapter:ArrayAdapter<String> =ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,archivos)
        archivosDisponibles.adapter=adapter

    }

    fun guardar(view: View) {
        //comprueba si lo seleccionado en el spinner es almacenamiento interno
       if (spinnerLugarAlmacenamiento.selectedItem.toString().equals
               (this.resources.getString(R.string.almacenamientoInterno))){

           var modoApertura= Context.MODE_APPEND //Asumo que va a concatenar
           //si no esta chekeado lo ponemos en privado
           if (!switchModoApertura.isChecked) {//si no esta checkeado, machaco contenido el (switch)

                modoApertura=Context.MODE_PRIVATE
           }
           //Creamos un outputSZtreamWriter preparado para escribir fichero
           val writer:OutputStreamWriter= OutputStreamWriter(openFileOutput(campoNombreFichero.text.toString(),modoApertura))
           writer.write((campoContenido.text.toString()))//escribimos en el fichero
           writer.flush()
           writer.close()
           Toast.makeText(this,R.string.ficheroEscrito,Toast.LENGTH_LONG).show()
           recargarListaArchivos()
       }else{
           Toast.makeText(this,R.string.noImplementado,Toast.LENGTH_LONG).show()


       }

    }


}