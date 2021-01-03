package adaptersyholders

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import clases.Persona
import com.mparamos.pruebastema4.R
import java.util.*

/**
 * Vamos a definir el adapter para asociarlo con las vistas del viewHolder
 * Esta clase hereda de RecycleView.ADAPTER!!!
 */
class MiRecyclerViewAdapter (val actividad: Activity,val datos:TreeSet<Persona>): RecyclerView.Adapter<MiHolder>() {

    /**
     * Metodo que infla cada uno de los elementos en el Layout, a traves del ViewHolder que creamos
     * devuelve un objeto MiHolder
     * @param parent es la lista
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MiHolder {
     return MiHolder( actividad.layoutInflater.inflate(R.layout.elementos_recycleview,parent,false))
    }

    /**
     * Funcion para comprobar cuantos elementos hay
     */
    override fun getItemCount(): Int {
        return datos.size //es importante ponerlo para que coja todo los elementos de la lista
    }

    /**
     * Este metodo se ejecuta por cada vez que se infla un elemento en la lista
     * @param holder objeto de tipo MiHolder que hemos creado
     * @param position el atributo del de arriba nos indica que elemento de la lista se está enlazando empezando desde cero
     */
    override fun onBindViewHolder(holder: MiHolder, position: Int) {
        //le metemos a holder que es donde contiene los elementos Lazy los valores del array para insertar en la lista
        holder.boton.text = (datos.toArray().get(position) as Persona).nombre //los datos del nombre de las personas por cada iteracion
        holder.checkBox.text=""
        holder.checkBox.isChecked=true//ponemos los checkbox a true
        holder.textView.text = (datos.toArray().get(position) as Persona).imagenURL//cogemos la url de la persona
        //funcion que hacer click en el boton del holder (que es donde tenemos los elementos) nos dara la posicion y el nombre
        holder.boton.setOnClickListener { view: View ->
            Toast.makeText(actividad,"Pulsada posicion : : "+position+". nombre: : "+holder.boton.text,Toast.LENGTH_LONG).show()
        }

    }


}