package recycler


import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.mparamos.pruebastema5.R

class SQLiteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val textoNombre:TextView by lazy { itemView.findViewById<TextView>(R.id.nombreListado) }
    val botonModificar:Button by lazy { itemView.findViewById<Button>(R.id.botonModificar) }
    val botonBorrar:Button by lazy { itemView.findViewById<Button>(R.id.botonBorrar) }
}