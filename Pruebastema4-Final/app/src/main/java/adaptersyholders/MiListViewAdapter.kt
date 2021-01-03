package adaptersyholders

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import clases.Persona
import com.mparamos.pruebastema4.R
import java.io.BufferedInputStream
import java.io.IOException
import java.io.InputStream
import java.net.URL
import java.net.URLConnection

/**
 * Crear un constructor que reciba el contexto que es la actividad y los elementos de la lista
 */
class MiListViewAdapter (val actividad:Activity, val datos:ArrayList<Persona>): BaseAdapter() {
    //contamos  el numero de elementos que tememos
    override fun getCount(): Int {
        return datos.size;//necesitamos pasarle el tamaño de nuestra estructura para que muestre todo lo que tenga
    }

    override fun getItem(position: Int): Any {
        TODO("Not yet implemented")
    }

    override fun getItemId(position: Int): Long {
        TODO("Not yet implemented")
    }
    /*
    Dentro del getView inflamos el contenido de la lista
     */
    override fun getView(pos: Int, convertView: View?, parent: ViewGroup?): View {
        //Emparejamos el view con el layout de elementoslistView
        val inflater: LayoutInflater = actividad.layoutInflater//creamos una variable para inflar a nuestro elementoListView



        //lo inflamos pero no tenemos al padre porque se lo pasaremos luego al listView
        val vista:View=inflater.inflate(R.layout.elementos_listview,null)//gracias al inflant ahora tenemos una variable view inflada
        //una vez cogido
        val cuadroTexto: TextView = vista.findViewById(R.id.texto)//metemos en una variable el textView del layout: elementos_listView
        val imagen: ImageView = vista.findViewById(R.id.imagen)//metemos en una variable la imagen del layout:elementos_listView
        val boton: Button =vista.findViewById(R.id.boton)//metemos en una variable el boton del layout: elementos_listView
        //en cada fila cogemos los datos que son los nombres del arrayList<Persona> iteramos gracias a pos
        //ya que pos es la posicion que se va iterando en cada lista
        cuadroTexto.text=datos.get(pos).nombre
        //y se lo incluimos al boton
        boton.text=datos.get(pos).nombre

        boton.setOnClickListener { view:View ->
            Toast.makeText(actividad,"Pulsada posicion "+pos,Toast.LENGTH_LONG).show()
        }
        return vista
    }

    fun getImageBitmap(url:String): Bitmap? {
        var bm:Bitmap? = null;
        try {
            var aURL:URL = URL(url);
            var conn:URLConnection  = aURL.openConnection();
            conn.connect();
            var ins:InputStream  = conn.getInputStream();
            var bis:BufferedInputStream  = BufferedInputStream(ins)
            bm = BitmapFactory.decodeStream(bis)
            bis.close()
            ins.close();
        } catch ( ex: IOException) {
            Log.e("eror",ex.toString());
            Toast.makeText(actividad,"Error: "+ex,Toast.LENGTH_LONG).show()
        }
        return bm;
    }

}