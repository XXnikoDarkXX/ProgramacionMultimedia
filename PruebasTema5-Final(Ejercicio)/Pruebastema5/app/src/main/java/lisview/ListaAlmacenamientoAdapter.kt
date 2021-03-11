package lisview

import android.os.Environment
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.mparamos.pruebastema5.CopiadoDeDocumentos
import com.mparamos.pruebastema5.PruebasAlmacenamiento
import com.mparamos.pruebastema5.R
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.InputStreamReader

class ListaAlmacenamientoAdapter(val contexto: PruebasAlmacenamiento, val datos: Array<out String>?) : BaseAdapter() {
    override fun getCount(): Int {

        return datos!!.size
    }

    override fun getItem(position: Int): Any {
        TODO("Not yet implemented")
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val vista: View = contexto.layoutInflater.inflate(R.layout.elementos_listview_almacenamiento, null)
        val nombreFichero: TextView = vista.findViewById<TextView>(R.id.nombreFichero)
        val botonBorrarFichero: Button = vista.findViewById<Button>(R.id.botonBorrarFichero)

        nombreFichero.setText(datos!!.get(position))


        nombreFichero.setOnClickListener {
            var nombreFicheroActual: String = (it as TextView).text.toString()

            //declaramos un lector
            var lector: BufferedReader? = null

            when (contexto.tipoAlmacenamientoSeleccionado) {

                0 -> lector = BufferedReader(InputStreamReader(contexto.openFileInput(nombreFicheroActual)))

                1 -> {
                    //Esto es para saber que lo que cojamos no es un directorio
                    val fichero: File = File(contexto.getExternalFilesDir(null).toString() + "/" + nombreFicheroActual.toString())
                    if (!fichero.isDirectory) {
                        lector = BufferedReader(FileReader(contexto.getExternalFilesDir(null).toString() + "/" + nombreFicheroActual.toString()))

                    }

                }
                2 -> {
                    val ruta: String ="/storage/emulated/0/"+Environment.DIRECTORY_DOWNLOADS
                    var archivo: File? =File(ruta+"/"+nombreFicheroActual.toString())
                      lector = BufferedReader(FileReader(archivo))

                }
            }

            var contenido: String = "";
            var lineaActual: String? = lector?.readLine()
            //mediante este while leemos todo lo que haya en el fichero clickeado
            //lectura de fichero
            while (lineaActual != null) {
                contenido += lineaActual
                lineaActual = lector?.readLine()
            }

            //Establecemos el contenido en los campos
            contexto.campoNombreFichero.setText(nombreFicheroActual)
            contexto.campoContenido.setText(contenido)

            Toast.makeText(contexto, nombreFicheroActual + " " + contexto.resources.getString(R.string.haSidoAbierto), Toast.LENGTH_LONG).show()


        }
        /**
         * Desde aqui borramos los archivos segun sea el tipo de almacenamiento usado.
         * Normalmente usamos el Files.delete
         */
        botonBorrarFichero.setOnClickListener {
            when (contexto.tipoAlmacenamientoSeleccionado) {

                0 -> {
                    var nombreFicheroActual: String = datos[position]
                    if (contexto.deleteFile(nombreFicheroActual)) {
                        Toast.makeText(contexto, nombreFicheroActual + " " + contexto.resources.getString(R.string.nombrFicheroBorrado), Toast.LENGTH_LONG).show()
                        contexto.recargarListaArchivos(contexto.tipoAlmacenamientoSeleccionado)
                    } else {
                        Toast.makeText(contexto, contexto.resources.getString(R.string.errorAlBorrar) + " " + nombreFicheroActual, Toast.LENGTH_LONG).show()
                    }

                }
                1 -> {
                    var nombreFicheroActual: String = datos[position]

                    var ruta: String = contexto.getExternalFilesDir(null).toString() + "/" + nombreFicheroActual + ".txt"
                    var archivo: File? = contexto.getExternalFilesDir(nombreFicheroActual)

                    //si el archivo existe lo eliminamos
                    if (archivo?.exists() == true) {
                        archivo?.delete()
                        Toast.makeText(contexto, nombreFicheroActual + " " + contexto.resources.getString(R.string.nombrFicheroBorrado), Toast.LENGTH_LONG).show()

                    } else {
                        Toast.makeText(contexto, contexto.resources.getString(R.string.errorAlBorrar) + " " + nombreFicheroActual, Toast.LENGTH_LONG).show()
                    }
                    contexto.recargarListaArchivos(contexto.tipoAlmacenamientoSeleccionado)

                }

                2 -> {
                    var nombreFicheroActual: String = datos[position]

                    val ruta: String ="/storage/emulated/0/"+Environment.DIRECTORY_DOWNLOADS
                    var archivo: File? =File(ruta+"/"+nombreFicheroActual.toString())

                    if (archivo?.exists() == true) {
                        archivo?.delete()
                        Toast.makeText(contexto, nombreFicheroActual + " " + contexto.resources.getString(R.string.nombrFicheroBorrado), Toast.LENGTH_LONG).show()

                    } else {
                        Toast.makeText(contexto, contexto.resources.getString(R.string.errorAlBorrar) + " " + nombreFicheroActual, Toast.LENGTH_LONG).show()
                    }
                    contexto.recargarListaArchivos(contexto.tipoAlmacenamientoSeleccionado)


                }

            }


        }

        return vista
    }


}
