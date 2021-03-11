package com.mparamos.pruebastema5

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.app.ActivityCompat
import listview.ListaAlmacenamientoAdapter
import java.io.BufferedReader
import java.io.FileWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class PruebasAlmacenamiento : Madre() {

    val spinnerLugarAlmacenamiento: Spinner by lazy{this.findViewById<Spinner>(R.id.spinnerLugarAlmacenamiento)}
    val switchModoApertura: Switch by lazy{ this.findViewById<Switch>(R.id.switchTipoApertura) }
    val campoNombreFichero: EditText by lazy{this.findViewById<EditText>(R.id.campoNombreFichero)}
    val campoContenido:EditText by lazy{this.findViewById<EditText>(R.id.campoContenidoArchivo)}
    val botonGuardar: Button by lazy{this.findViewById<Button>(R.id.botonGuardar)}
    val rutaActual:TextView by lazy{this.findViewById<TextView>(R.id.rutaActual)}
    val archivosDisponibles:ListView by lazy {this.findViewById<ListView>(R.id.archivosDisponibles)}
    companion object{
        val PERMISO_ESCRITURA_LECTURA=1000;
    }
    var tipoAlmacenamientoSeleccionado:Int=0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pruebas_almacenamiento)
        this.aplicarModoOscuro()
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
                       tipoAlmacenamientoSeleccionado=0;
                       recargarListaArchivos(tipoAlmacenamientoSeleccionado)
                   }
                   1 -> {
                       Toast.makeText(p1!!.context,R.string.seleccionadoExternoPrivado,Toast.LENGTH_LONG).show()
                       if(permisoAlmacenamientoPrivadoExterno()) {
                           rutaActual.setText(p1!!.context.resources.getString(R.string.rutaActual)
                                   + p1!!.context.getExternalFilesDir(null))
                           tipoAlmacenamientoSeleccionado=1;
                           recargarListaArchivos(tipoAlmacenamientoSeleccionado)

                       }
                   }
                   2 -> {
                       Toast.makeText(p1!!.context,R.string.seleccionadoExternoPublico,Toast.LENGTH_LONG).show()
                       Toast.makeText(p1!!.context,"NO IMPLEMENTADO",Toast.LENGTH_LONG).show()

                   }
                }
            }

        }

        campoNombreFichero.setText(
                this.preferencias.getString("ficheroTextoDefecto",""))

        spinnerLugarAlmacenamiento.setSelection(
                this.preferencias.getInt("modoAlmacenamientoDefecto",0))
        tipoAlmacenamientoSeleccionado=spinnerLugarAlmacenamiento.selectedItemPosition

        recargarListaArchivos(tipoAlmacenamientoSeleccionado);

    }

    /**
     * @param tipoAlmacenamiento vale 0 para el almacenamiento interno privado, 1 para el externo privado, y 2 para el externo público. No se permite ningún otro valor.
     */
    fun recargarListaArchivos(tipoAlmacenamiento:Int):Unit{
        //Listar en la parte derecha todos los ficheros disponibles
        var archivos: Array<out String>?=null;
        when(tipoAlmacenamiento){
            0 -> {
                 archivos = applicationContext.fileList()
            }
            1 -> {
                archivos=this.getExternalFilesDir(null)!!.list()
            }
        }
            val adapter:ListaAlmacenamientoAdapter =ListaAlmacenamientoAdapter(this,archivos)
        archivosDisponibles.adapter=adapter


    }

    fun guardar(view: View) {
        if(spinnerLugarAlmacenamiento.selectedItem.toString().equals(
                this.resources.getString(R.string.almacenamientoInterno))){
            var modoApertura=Context.MODE_APPEND //Asumo que va a concatenar (por poner algo)
            if(!switchModoApertura.isChecked){//si no está checkeado, machaco contenido
                modoApertura=Context.MODE_PRIVATE
            }
            val writer: OutputStreamWriter= OutputStreamWriter(
                openFileOutput(campoNombreFichero.text.toString(),modoApertura))
            writer.write(campoContenido.text.toString())
            writer.flush()
            writer.close()
            Toast.makeText(this,R.string.ficheroEscrito,Toast.LENGTH_LONG).show()
            recargarListaArchivos(tipoAlmacenamientoSeleccionado)
        }else{
            if(spinnerLugarAlmacenamiento.selectedItem.toString().equals(this.resources.getString(
                            R.string.almacenamientoExternoPrivado
                    ))){
                val writer:FileWriter=FileWriter(
                        this.getExternalFilesDir(null).toString()+"/"+
                                campoNombreFichero.text.toString())
                writer.write(campoContenido.text.toString())
                writer.flush()
                writer.close()
                Toast.makeText(this,R.string.ficheroEscrito,Toast.LENGTH_LONG).show()
                recargarListaArchivos(tipoAlmacenamientoSeleccionado)
            }else {
                Toast.makeText(this, R.string.noImplementado, Toast.LENGTH_LONG).show()
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
            return true;
        }else{
            if(!ActivityCompat.shouldShowRequestPermissionRationale(
                            this, Manifest.permission.WRITE_EXTERNAL_STORAGE)||!ActivityCompat.shouldShowRequestPermissionRationale(
                            this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                Toast.makeText(this,"Al menos uno de los permisos fué denegado. No puedo ejecutar esto.",
                        Toast.LENGTH_LONG).show()
                return false;
            }
            val permisos:Array<String> = arrayOf<String>(Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE);
            ActivityCompat.requestPermissions(this,permisos,PERMISO_ESCRITURA_LECTURA)
            Toast.makeText(this,"Esta función la hemos implementado de rapideo para probar almacenamiento." +
                    "Nos falta una función que reaccione al resultado de requestPermissions. No la implementamos" +
                    " porque es cosa del 1º trimestre. Para que funcione, selecciona otra opción en el spinner" +
                    " y vuelve a seleccionar esta.",Toast.LENGTH_LONG).show()
            return false;
        }
    }
}