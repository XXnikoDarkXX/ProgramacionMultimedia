package lisview

import android.os.Build
import android.os.Environment
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.mparamos.pruebastema5.CopiadoDeDocumentos
import com.mparamos.pruebastema5.R
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.InputStreamReader
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardCopyOption

class ListaAlmacenamiento(val contexto: CopiadoDeDocumentos, val datos: Array<out String>?, val tipoAlmacenamiento: String) :
    BaseAdapter() {
    override fun getCount(): Int {

        return datos!!.size
    }

    override fun getItem(position: Int): Any {
        TODO("Not yet implemented")
    }

    override fun getItemId(position: Int): Long {

        return position.toLong()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val vista:View=contexto.layoutInflater.inflate(R.layout.elementos_lista_almacenamiento_copiado, null)
        val nombreFichero:TextView=vista.findViewById<TextView>(R.id.nombrelistadoFichero)
        val btnCopiar:Button=vista.findViewById<Button>(R.id.btnCopiar)

        nombreFichero.setText(datos!!.get(position))

        nombreFichero.setOnClickListener {
            var nombreFicheroActual:String=(it as TextView).text.toString()


            var lector: BufferedReader? = null
            //Declaramos el tipo de lector
            if (tipoAlmacenamiento.equals("InternoPrivado")){
                lector=    BufferedReader(InputStreamReader(contexto.openFileInput(nombreFicheroActual)))
                Toast.makeText(contexto, "pasamos por el internoPrivado", Toast.LENGTH_LONG).show()
            }else{
                lector= BufferedReader(FileReader(contexto.getExternalFilesDir(null).toString() + "/" + nombreFicheroActual.toString()))
                Toast.makeText(contexto, "pasamos por el externoPrivado", Toast.LENGTH_LONG).show()
            }
            var contenido:String="";
            var lineaActual:String?=lector?.readLine()
            //mediante este while leemos todo lo que haya en el fichero clickeado
            //lectura de fichero
            while(lineaActual!=null){
                contenido+=lineaActual
                lineaActual=lector?.readLine()
            }
            Toast.makeText(contexto, contenido, Toast.LENGTH_LONG).show()
            //Establecemos el contenido en los campos
            contexto.campoNombreCopiado.setText(nombreFicheroActual)
         contexto.campoContenidoCopiado.setText(contenido)

        }

        btnCopiar.setOnClickListener {
            var nombreFicheroActual:String=datos[position]
            if (tipoAlmacenamiento.equals("InternoPrivado")){
                var ruta:String= Environment.getExternalStorageDirectory().absolutePath+"/"+nombreFicheroActual+".txt";
                var archivoOriginal: File? =contexto.getExternalFilesDir(nombreFicheroActual)
                Toast.makeText(contexto, "pasamos por el internoPrivado", Toast.LENGTH_LONG).show()
                var rutaCopiada:Path= Paths.get(archivoOriginal?.absolutePath);
                val rutaOriginal:Path=Paths.get(contexto.filesDir.toString(),nombreFicheroActual)
                Files.copy(rutaOriginal, rutaCopiada, StandardCopyOption.REPLACE_EXISTING);
                contexto.recargaRecyclesView()
            }else{
                var ruta:String= Environment.getExternalStorageDirectory().absolutePath+"/"+nombreFicheroActual+".txt";
               var archivoOriginal: File? =contexto.getExternalFilesDir(nombreFicheroActual)


                val rutaOriginal:Path= Paths.get(archivoOriginal?.absolutePath)
                val rutaCopiada:Path=Paths.get(contexto.filesDir.toString(),nombreFicheroActual)

                Files.copy(rutaOriginal, rutaCopiada, StandardCopyOption.REPLACE_EXISTING);
                contexto.recargaRecyclesView()
            }
        }

        return vista;
    }
}