package com.alerd.ejercicioexamen

import android.content.Context
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.*
import androidx.core.app.ActivityCompat
import listview.ListaAlmacenamientoAdapter
import java.io.File
import java.io.FileWriter
import java.io.OutputStreamWriter


class PruebasAlmacenamiento : AppCompatActivity() {


    //REFERENCIAR VARIABLES//
    val spinnerLugarAlmacenamiento: Spinner by lazy { this.findViewById<Spinner>(R.id.spinnerLugarAlmacenamiento) }
    val switchModoApertura: Switch by lazy { this.findViewById<Switch>(R.id.switchTipoApertura) }
    val campoNombreFichero: EditText by lazy { this.findViewById<EditText>(R.id.campoPrefFicheroTexto) }
    val campoContenidoArchivo: EditText by lazy { this.findViewById<EditText>(R.id.campoContenidoArchivo) }
    val botonGuardar: Button by lazy { this.findViewById<Button>(R.id.botonGuardar) }
    val rutaActual: TextView by lazy { this.findViewById<TextView>(R.id.rutaActual) }
    val archivosDisponibles: ListView by lazy { this.findViewById<ListView>(R.id.achivosDisponibles) }
    companion object {//Necesario para el permiso
    val PERMISO_ESCRITURA_LECTURA = 1000
    }
    var tipoAlmacenamientoSeleccionado: Int = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pruebas_almacenamiento)


        //////DETERMINAR MODO DE ESCRITURA Y TIPO DE ALMACENAMIENTO//////

        //Listener del Switch, para elegir el MODO DE ESCRITURA (concatenar o machacar)
        switchModoApertura.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { compoundButton, checked ->
            if (checked) {
                switchModoApertura.setText(R.string.concatenarContenido)
            } else {
                switchModoApertura.setText(R.string.machacarFichero)
            }
        })

        //TIPO DE ALMACENAMIENTO//

        //Listener del Spinner, evento para capturar el cambio (segun eleccion del tipo de almacenamiento)
        spinnerLugarAlmacenamiento.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onNothingSelected(p0: AdapterView<*>?) { //1 Metodo, no se implementa
                TODO("Not yet implemented")
            }

            //2 Metodo, si se implementa
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {//p2->Posicion elegida
                when (p2) { // p2 Elemento seleccionado
                    0 -> { //Almacenamiento interno
                        Toast.makeText(p1!!.context, R.string.seleccionadoInternoPrivado, Toast.LENGTH_LONG).show()

                        //MOSTRAR LA RUTA ACTUAL
                        rutaActual.setText(p1!!.context.resources.getString(R.string.rutaActual)
                                + p1!!.context.filesDir.toString())//filesDir->Para almacenamiento interno

                        tipoAlmacenamientoSeleccionado = 0;//usamos esta varibale para saber que hay que recargar en el listView
                        //segun haya seleccionado el spinner
                        recargarListaArchivos(0)
                    }
                    1 -> {//Almacenamiento externo privado
                        Toast.makeText(p1!!.context, R.string.seleccionadoExternoPrivado, Toast.LENGTH_LONG).show()

                        //Hay q pedir permiso para almacenamiento externo
                        if (permisoAlmacenamientoPrivadoExterno()) {

                            //MOSTRAR LA RUTA ACTUAL
                            rutaActual.setText(p1!!.context.resources.getString(R.string.rutaActual)
                                    + p1!!.context.getExternalFilesDir(null))//getExternalFilesDir->Almacenamiento Externo

                            tipoAlmacenamientoSeleccionado = 1
                            recargarListaArchivos(1)
                        }
                    }
                }
            }
        }

        tipoAlmacenamientoSeleccionado=spinnerLugarAlmacenamiento.selectedItemPosition

        recargarListaArchivos(tipoAlmacenamientoSeleccionado)

    }


    /**
     * Funcion para guardar, COMPROBANDO EL TIPO DE ALMACENAMIENTO ELEGIDO y EL TIPO DE ESCRITURA
     */
    fun guardar(view: View) { //resources->Nos permite hacer la comparacion con todos los idiomas

        //Comprobacion de tipo de almacenamiento seleccionado
        if (spinnerLugarAlmacenamiento.selectedItem.toString().equals(this.resources.getString(R.string.almacenamientoInterno))){

            //Escritura del archivo segun el modo de escritura elegido (concatenar o machacar)
            val writer: OutputStreamWriter = OutputStreamWriter(
                    openFileOutput(campoNombreFichero.text.toString(),// Argumento 1 -> nombre del fichero
                            modoAperturaElegido()))// Argumento 2 -> modo de apertura elegido

            escribirFichero(writer, campoContenidoArchivo)

        }else { //Almacenamiento externo privado
            if (spinnerLugarAlmacenamiento.selectedItem.equals(this.resources.getString(R.string.almacenamientoExternoPrivado))) {
                if (modoAperturaElegido()==MODE_APPEND){

                    //Escritura del archivo segun el modo de escritura elegido (CONCATENAR)
                    val writer: FileWriter = FileWriter(
                            this.getExternalFilesDir(//Devuelve la ruta directorio privado por defecto del almacenamiento externo
                                    null).toString() + "/" + campoNombreFichero.text.toString(), true)//String_argumento;null->carpeta_defecto

                    escribirFichero(writer, campoContenidoArchivo)
                }else{

                    //Escritura del archivo segun el modo de escritura elegido (MACHACAR)
                    val writer: FileWriter = FileWriter(
                            this.getExternalFilesDir(//Devuelve la ruta directorio privado por defecto del almacenamiento externo
                                    null).toString() + "/" + campoNombreFichero.text.toString())//String_argumento;null->carpeta_defecto

                    escribirFichero(writer, campoContenidoArchivo)
                }

                Toast.makeText(this, R.string.ficheroEscrito, Toast.LENGTH_LONG).show()
                recargarListaArchivos(tipoAlmacenamientoSeleccionado)
            }
        }
    }

    fun modoAperturaElegido() : Int { //Determinar el tipo de escritura anteriormente elegido
        var modoApertura= Context.MODE_PRIVATE //Asumo q va a machacar (por defecto)

        if (switchModoApertura.isChecked){ //Si esta checkeado, es concatenar contenido
            modoApertura= Context.MODE_APPEND//Concatenar (archivo interno privado)
            return modoApertura
        }
        return modoApertura
    }



    /**
     * @param tipoAlmacenamiento vale 0 para el almacenamiento interno privado,
     * 1 para el externo privado, y 2 para el externo publico, no se permite ningún otro valor
     * Para actualizar la lista de archivos de nuestro directorio interno de memoria
     */
    fun recargarListaArchivos(tipoAlmacenamiento: Int):Unit{
        //Listar en la parte derecha todos los ficheros disponibles
        var archivos:Array<out String>?=null

        when(tipoAlmacenamiento){
            0 -> {//almacenamiento interno privado
                archivos = applicationContext.fileList()
            }
            1 -> {//almacenamiento externo privado
                archivos = this.getExternalFilesDir(null)!!.list()
            }
        }

        val adapter: ListaAlmacenamientoAdapter = ListaAlmacenamientoAdapter(this, archivos)
        archivosDisponibles.adapter=adapter

    }


    fun escribirFichero(writer: FileWriter, campoContenidoArchivo: EditText){
        writer.write(campoContenidoArchivo.text.toString())//Escribo lo q hay en campoContenidoArchivo
        writer.flush()
        writer.close()

        Toast.makeText(this, R.string.ficheroEscrito, Toast.LENGTH_LONG).show()
        recargarListaArchivos(tipoAlmacenamientoSeleccionado)//Una vez guardado con exito, recargamos la lista de archivos
    }

    fun escribirFichero(write: OutputStreamWriter, campoContenidoArchivo: EditText){
        write.write(campoContenidoArchivo.text.toString())//Escribo lo q hay en campoContenidoArchivo
        write.flush()
        write.close()

        Toast.makeText(this, R.string.ficheroEscrito, Toast.LENGTH_LONG).show()
        recargarListaArchivos(tipoAlmacenamientoSeleccionado)//Una vez guardado con exito, recargamos la lista de archivos
    }




    fun permisoAlmacenamientoPrivadoExterno():Boolean{
        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED) {

            //llamar a función que corresponda, de momento Toast
            Toast.makeText(this, "Permisos Concedidos!", Toast.LENGTH_LONG).show()
            return true

        }else{//Si no concede el permiso se lanza un Toast
            if(!ActivityCompat.shouldShowRequestPermissionRationale(
                            this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)||!ActivityCompat.shouldShowRequestPermissionRationale(
                            this, android.Manifest.permission.READ_EXTERNAL_STORAGE)) {
                Toast.makeText(this, "Al menos uno de los permisos fué denegado. No puedo ejecutar esto.",
                        Toast.LENGTH_LONG).show()
                return false
            }
            val permisos:Array<String> = arrayOf<String>(android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE)

            ActivityCompat.requestPermissions(this, permisos, PERMISO_ESCRITURA_LECTURA)

            Toast.makeText(this, "Para que funcione, selecciona otra opcion en el spinner y vuelve a seleccionar esta",
                    Toast.LENGTH_LONG).show()
            //Esta función la hemos implementado de rapideo para probar almacenamiento
            //Nos falta una funcion que reaccione al resultado de  requestPermissions
            return false
        }
    }



}
