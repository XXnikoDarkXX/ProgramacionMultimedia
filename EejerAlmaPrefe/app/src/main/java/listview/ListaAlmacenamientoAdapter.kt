package listview

import android.app.Activity
import android.os.Build
import android.os.Environment
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.alerd.ejercicioexamen.PruebasAlmacenamiento
import com.alerd.ejercicioexamen.R
import java.io.BufferedReader
import java.io.FileReader
import java.io.InputStreamReader
import java.nio.file.FileSystem
import java.nio.file.FileSystems
import java.nio.file.Files
import java.nio.file.Path

class ListaAlmacenamientoAdapter(val contexto: PruebasAlmacenamiento, val datos: Array<out String>?) : BaseAdapter() { // : heredar

    override fun getCount(): Int { //Siempre hay q implementarla
        if (datos==null){
            return 0
        }
        return datos!!.size
    }

    override fun getItemId(position: Int): Long { // : Metodo con retorno
        return position.toLong() //Si no nada->toLong(convertirlo), Si hay->as Long(castear)
    }

    override fun getItem(position: Int): Any {
        TODO("Not yet implemented")
    }

    //Se ejecuta una vez por cada elemento de la lista, basicamente hay q inflar la lista
    @RequiresApi(Build.VERSION_CODES.O)
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        //REFERENCIAR VARIABLES//
        val vista:View = contexto.layoutInflater.inflate(R.layout.elementos_listview_almacenamiento,//Lo q voy a inflar
                null)//Donde lo voy a inflar, no se lo digo xq se lo va a decir quien llama a esta funcion

        //Tenemos q sacar los objetos q hay ahi
        var nombreFichero: TextView = vista.findViewById(R.id.nombreFichero)
        var botonBorrarFichero: Button = vista.findViewById(R.id.botonBorrarFichero)



        //ESTABLECER NOMBRE, RUTA Y CONTENIDO DEL FICHERO SEGUN ALMACENAMIENTO ELEGIDO//
        nombreFichero.setText(datos!!.get(position))//Poner el nombre del fichero q le corresponde

        //Listener para conseguir NOMBRE del fichero actual (el q se esta pinchando)
        nombreFichero.setOnClickListener { it: View ->
            var nombreFicheroActual:String = (it as TextView).text.toString()

            //Con esto se consigue la RUTA del fichero actual (el q se esta pinchando)
            var lector:BufferedReader ?= null
            when(contexto.tipoAlmacenamientoSeleccionado){
                0 -> {
                    lector = BufferedReader(InputStreamReader(contexto.openFileInput(nombreFicheroActual)))//Lector para almacenamiento interno
                }

                1 -> lector = BufferedReader(FileReader(
                        contexto.getExternalFilesDir(null).toString() + "/" + nombreFicheroActual))//Lector para almacenamiento externo privado
            }

            //Obtenemos el CONTENIDO del fichero
            var contenido:String=""
            var lineaActual:String?=lector?.readLine()
            while (lineaActual!=null){
                contenido+=lineaActual
                lineaActual=lector?.readLine()
            }

            //Establecemos el contenido de los campos
            contexto.campoNombreFichero.setText(nombreFicheroActual)
            contexto.campoContenidoArchivo.setText(contenido)

            Toast.makeText(contexto, nombreFicheroActual + contexto.resources.getString(R.string.haSidoAbierto), Toast.LENGTH_LONG).show()
        }



        //BORRADO DE FICHERO SEGUN EL TIPO DE ALMACENAIENTO ELEGIDO//
        botonBorrarFichero.setOnClickListener {
            var nombreFicheroActual:String=datos[position]

            when(contexto.tipoAlmacenamientoSeleccionado){
                0 -> {//Almacenamiento interno
                    if (contexto.deleteFile(nombreFicheroActual)) {
                        Toast.makeText(contexto, nombreFicheroActual + " " + contexto.resources.getString(
                                R.string.ficheroBorrado), Toast.LENGTH_LONG).show()
                        contexto.recargarListaArchivos(contexto.tipoAlmacenamientoSeleccionado)
                    } else {
                        Toast.makeText(contexto, contexto.resources.getString(R.string.errorAlBorrar) + " " + nombreFicheroActual, Toast.LENGTH_LONG).show()
                    }
                }
                1 -> {//Almacenamiento externo privado //AÑADIR IF ELSE Y MENSAJE DE ERRROR
                    var rutaFicheroExternoPrivado: String = contexto.getExternalFilesDir(//Devuelve la ruta directorio privado por defecto del almacenamiento externo
                            null).toString() + "/" + nombreFicheroActual//String_argumento;null->carpeta_defecto

                    //Utilizo FileSystem para a partir de un String obtener el path de la ruta que deseo borrar
                    val fs: FileSystem = FileSystems.getDefault()
                    val actual: Path = fs.getPath(rutaFicheroExternoPrivado)
                    Files.delete(actual)

                    Toast.makeText(contexto, nombreFicheroActual + " " + contexto.resources.getString(
                            R.string.ficheroBorrado), Toast.LENGTH_LONG).show()

                    contexto.recargarListaArchivos(contexto.tipoAlmacenamientoSeleccionado)
                }
            }

        }
        return vista
    }





}