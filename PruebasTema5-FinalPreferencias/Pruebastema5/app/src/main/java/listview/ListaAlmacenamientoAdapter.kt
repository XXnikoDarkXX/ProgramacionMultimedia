package listview

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.mparamos.pruebastema5.PruebasAlmacenamiento
import com.mparamos.pruebastema5.R
import java.io.BufferedReader
import java.io.FileReader
import java.io.InputStreamReader

class ListaAlmacenamientoAdapter(val contexto: PruebasAlmacenamiento, val datos:Array<out String>?):BaseAdapter() {
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val vista:View=contexto.layoutInflater.inflate(R.layout.elementos_listview_almacenamiento,
                null)
        val nombreFichero:TextView=vista.findViewById<TextView>(R.id.nombreFichero)
        val botonBorrarFichero: Button =vista.findViewById<Button>(R.id.botonBorrarFichero)

        nombreFichero.setText(datos!!.get(p0))

        nombreFichero.setOnClickListener {it: View ->
            var nombreFicheroActual:String=(it as TextView).text.toString();

            var lector: BufferedReader? = null;
            when(contexto.tipoAlmacenamientoSeleccionado){
                0-> lector=BufferedReader(InputStreamReader(
                        contexto.openFileInput(nombreFicheroActual)))
                1-> lector= BufferedReader(FileReader(
                        contexto.getExternalFilesDir(null).toString()+"/"+nombreFicheroActual))


            }
            var contenido:String="";
            var lineaActual:String?=lector?.readLine();
            while(lineaActual!=null){
                contenido+=lineaActual
                lineaActual=lector?.readLine()
            }

            //Establecemos el contenido en los campos
            contexto.campoNombreFichero.setText(nombreFicheroActual)
            contexto.campoContenido.setText(contenido)

            Toast.makeText(contexto,nombreFicheroActual+" "+
                    contexto.resources.getString(R.string.haSidoAbierto), Toast.LENGTH_LONG).show()

        }

        botonBorrarFichero.setOnClickListener {
            var nombreFicheroActual:String=datos[p0];
           if(contexto.deleteFile(nombreFicheroActual)){
               Toast.makeText(contexto,
                       nombreFicheroActual+" "+
                               contexto.resources.getString(R.string.ficheroBorrado)
                       ,Toast.LENGTH_LONG).show()
               contexto.recargarListaArchivos(contexto.tipoAlmacenamientoSeleccionado)
           }else{
               Toast.makeText(contexto,
               contexto.resources.getString(R.string.errorAlBorrar)+
                       " "+nombreFicheroActual,Toast.LENGTH_LONG).show()
           }

        }

        return vista
    }

    override fun getItem(p0: Int): Any {
        return datos!!.get(p0)
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        if(datos==null){
            return 0;
        }
        return datos!!.size
    }
}