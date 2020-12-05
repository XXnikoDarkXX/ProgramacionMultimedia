package adapters

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


class MiListViewAdapter(val actividad: Activity,val datos:ArrayList<Persona>) : BaseAdapter() {


    override fun getView(pos: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater: LayoutInflater =actividad.layoutInflater
        val vista:View=inflater.inflate(R.layout.elementos_listview,null)

        val cuadroTexto:TextView= vista.findViewById(R.id.texto)
        val imagen:ImageView = vista.findViewById(R.id.imagen)
        val boton: Button = vista.findViewById(R.id.boton)

        cuadroTexto.text=datos.get(pos).nombre
        boton.text=datos.get(pos).nombre

        boton.setOnClickListener { view:View ->
            Toast.makeText(actividad,"Pulsada posicion "+pos,Toast.LENGTH_LONG).show()
        }

        return vista
    }

    override fun getItem(p0: Int): Any {
        TODO("Not yet implemented")
    }

    override fun getItemId(p0: Int): Long {
        TODO("Not yet implemented")
    }

    override fun getCount(): Int {
        return datos.size;
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
