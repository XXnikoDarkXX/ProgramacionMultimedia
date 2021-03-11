package com.mparamos.pruebastema5

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.os.ParcelFileDescriptor
import android.view.View
import android.widget.*
import androidx.core.app.ActivityCompat
import lisview.ListaAlmacenamientoAdapter
import java.io.*
import java.nio.file.Path

class PruebasAlmacenamiento : AppCompatActivity() {
    val spinnerLugarAlmacenamiento: Spinner by lazy{this.findViewById<Spinner>(R.id.spinnerLugarAlmacenamiento)}
    val switchModoApertura: Switch by lazy{ this.findViewById<Switch>(R.id.switchTipoApertura) }
    val campoNombreFichero: EditText by lazy{this.findViewById<EditText>(R.id.campoNombreCopiado)}
    val campoContenido:EditText by lazy{this.findViewById<EditText>(R.id.campoContenidoCopiado)}
    val botonGuardar: Button by lazy{this.findViewById<Button>(R.id.btnMostrar)}
    val rutaActual:TextView by lazy{this.findViewById<TextView>(R.id.txtAlmacenamientoExternoPrivado)}
    val archivosDisponibles:ListView by lazy {this.findViewById<ListView>(R.id.recyclerExternoPrivado)}
    companion object {
        val PERMISO_ESCRITURA_LECTURA = 1000;
        val REQUEST_CODE=50//lo usaremos para peticion

    }
    var  tipoAlmacenamientoSeleccionado:Int =0;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pruebas_almacenamiento)
        switchModoApertura.setOnCheckedChangeListener(
                CompoundButton.OnCheckedChangeListener { compoundButton, checked ->
                    if(checked){
                        switchModoApertura.setText(R.string.concatenarContenido)
                    }else{
                        switchModoApertura.setText(R.string.machacarFichero)
                    }
                })

        spinnerLugarAlmacenamiento.onItemSelectedListener= object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                when(p2){
                    0 -> {
                        Toast.makeText(p1!!.context,R.string.seleccionadoInternoPrivado,Toast.LENGTH_LONG).show()
                        rutaActual.setText(p1!!.context.resources.getString(R.string.rutaActual)
                                +p1!!.context.filesDir.toString())
                        tipoAlmacenamientoSeleccionado=0;//usamos esta varibale para saber que hay que recargar en el listView
                        //segun haya seleccionado el spinner mediante un byte
                        recargarListaArchivos(0)
                    }
                    1 -> {
                        Toast.makeText(p1!!.context,R.string.seleccionadoExternoPrivado,Toast.LENGTH_LONG).show()
                        permisoAlmacenamientoPrivadoExterno()
                        if (permisoAlmacenamientoPrivadoExterno()) {
                            rutaActual.setText(p1!!.context.resources.getString(R.string.rutaActual)
                                    + p1!!.context.getExternalFilesDir(null))
                            tipoAlmacenamientoSeleccionado=1;
                            recargarListaArchivos(1)

                        }
                    }
                    2 -> {
                        if ( permisoAlmacenamientoPrivadoExterno()==true){
                        Toast.makeText(p1!!.context,R.string.seleccionadoExternoPublico,Toast.LENGTH_LONG).show()

                        tipoAlmacenamientoSeleccionado=2;
                        recargarListaArchivos(tipoAlmacenamientoSeleccionado)
                        rutaActual.setText(""+Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS))
                    }
                        }
                }
            }

        }
       if ( permisoAlmacenamientoPrivadoExterno()==true){
           recargarListaArchivos(tipoAlmacenamientoSeleccionado);
       }


    }

    /**
     * @param tipoAlmacenamiento vale 0 para el almacenamiento interno privado, 1 para el externo privado, y 2 para el externo publico, no se permite ningún otro valor
     */
    fun recargarListaArchivos( tipoAlmacenamiento:Int):Unit{
        //Listar en la parte derecha todos los ficheros disponibles
        var archivos:Array<out String>?=null
            when(tipoAlmacenamiento){
           0 -> {
                archivos= applicationContext.fileList()

           }
                1->{
                 archivos   =this.getExternalFilesDir(null)!!.list()

                }
                2->{
                    val ruta: String ="/storage/emulated/0/"+Environment.DIRECTORY_DOWNLOADS

                    archivos=File(ruta).list()
                    //el obsoleto
                }
        }
        val adapter:ListaAlmacenamientoAdapter =ListaAlmacenamientoAdapter(this,archivos)
        archivosDisponibles.adapter=adapter



    }

    fun guardar(view: View) {
        //comprueba si lo seleccionado en el spinner es almacenamiento interno
        if(spinnerLugarAlmacenamiento.selectedItem.toString().equals(
                        this.resources.getString(R.string.almacenamientoInterno))){//si hemos seleccionado el almacenamiento privado interno
            var modoApertura=Context.MODE_APPEND //Asumo que va a concatenar (por poner algo)
            if(!switchModoApertura.isChecked){//si no está checkeado, machaco contenido
                modoApertura=Context.MODE_PRIVATE
            }
            //Creamos un outputStreamWriter preparado para escribir fichero
            val writer: OutputStreamWriter= OutputStreamWriter(
                    openFileOutput(campoNombreFichero.text.toString(),modoApertura))
            writer.write(campoContenido.text.toString())
            writer.flush()
            writer.close()
            Toast.makeText(this,R.string.ficheroEscrito,Toast.LENGTH_LONG).show()
            recargarListaArchivos(tipoAlmacenamientoSeleccionado)
        }else {
            //si el spinner tiene el mismo texto el externo privado
            //es decir para guardar y escribir el fichero en la opcion ALMACENAMIENTO EXTERNO PRIVADO
            if (spinnerLugarAlmacenamiento.selectedItem.equals(this.resources.getString(R.string.almacenamientoExternoPrivado))) {//asumo que va a concatenar(poner algo mas)
               var switchSeleccionado:Boolean=true//este booleano lo usamos para el boleano del FileWriter
                if (!switchModoApertura.isChecked){//si no está checkeado machaco el contenido
                   switchSeleccionado=false
                }

                val writer:FileWriter= FileWriter(this.getExternalFilesDir(null).toString()+"/"+campoNombreFichero.text.toString(),switchSeleccionado)
                writer.write(campoContenido.text.toString())
                writer.flush()
                writer.close()

                recargarListaArchivos(tipoAlmacenamientoSeleccionado)
            } else {
            if (spinnerLugarAlmacenamiento.selectedItem.equals(this.resources.getString(R.string.almacenamientoExternoPublico))) {
                if (!switchModoApertura.isChecked){//si no esta chekeado machaco el contenido
                 /// val intent = Intent(Intent.ACTION_CREATE_DOCUMENT)
                   // intent.addCategory(Intent.CATEGORY_OPENABLE)
                 ///   intent.type = "*/*"

                  /*  startActivityForResult(intent, REQUEST_CODE)
                    Toast.makeText(this,R.string.ficheroEscrito,Toast.LENGTH_LONG).show()
                    recargarListaArchivos(tipoAlmacenamientoSeleccionado)*/

                    val ruta: String ="/storage/emulated/0/"+Environment.DIRECTORY_DOWNLOADS
                    var archivo: File? =File(ruta+"/"+campoNombreFichero.text.toString())
                    val writer:FileWriter= FileWriter(archivo)
                    writer.write(campoContenido.text.toString())
                    writer.flush()
                    writer.close()
                    recargarListaArchivos(tipoAlmacenamientoSeleccionado)
                    Toast.makeText(this,R.string.ficheroEscrito,Toast.LENGTH_LONG).show()
                }else{
                    val ruta: String ="/storage/emulated/0/"+Environment.DIRECTORY_DOWNLOADS
                    var archivo: File? =File(ruta+"/"+campoNombreFichero.text.toString())
                    //    var archivo: File? = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS+"/"+campoNombreFichero.text.toString())
                    val writer:FileWriter= FileWriter(archivo,true)
                    writer.write(campoContenido.text.toString())
                    writer.flush()
                    writer.close()
                    recargarListaArchivos(tipoAlmacenamientoSeleccionado)
                    Toast.makeText(this,R.string.ficheroEscrito,Toast.LENGTH_LONG).show()
                }


            }
         }
        }
    }

    fun permisoAlmacenamientoPrivadoExterno():Boolean{
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED&&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED) {
            //llamar a función que corresponda
            Toast.makeText(this,"Permisos Concedidos!",Toast.LENGTH_LONG).show()
            return true
        }else{
            if(!ActivityCompat.shouldShowRequestPermissionRationale(
                            this, Manifest.permission.WRITE_EXTERNAL_STORAGE)||!ActivityCompat.shouldShowRequestPermissionRationale(
                            this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                Toast.makeText(this,"Al menos uno de los permisos fué denegado. No puedo ejecutar esto.",
                        Toast.LENGTH_LONG).show()
                return false
            }
            val permisos:Array<String> = arrayOf<String>(Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE);
            ActivityCompat.requestPermissions(this,permisos,PERMISO_ESCRITURA_LECTURA)
            Toast.makeText(this,"vuelve a pregunta",Toast.LENGTH_LONG).show()
           //Esta función la hemos implementado de rapideo para probar almacenamiento." +
            //"Nos falta una funcion que reaccione a requesPermissions
            return false;
        }
    }


}




