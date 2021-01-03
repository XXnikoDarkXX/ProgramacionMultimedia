package adaptersyholders

import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mparamos.pruebastema4.R

/**
 * Clase para diseñar el aspecto y comportamiento de cada elemento de la lista  extiende de la clase VewHolder
 */
class MiHolder (itemView: View): RecyclerView.ViewHolder(itemView)  {
        //aqui declaramos las variables del layout elementos_recycleView para luego usarlos
    val boton:Button by lazy { itemView.findViewById<Button>(R.id.botonRecicleView) }
    val textView:TextView by lazy { itemView.findViewById<TextView>(R.id.txtVistaRecicle) }
    val checkBox:CheckBox by lazy { itemView.findViewById<CheckBox>(R.id.checkBox) }
}