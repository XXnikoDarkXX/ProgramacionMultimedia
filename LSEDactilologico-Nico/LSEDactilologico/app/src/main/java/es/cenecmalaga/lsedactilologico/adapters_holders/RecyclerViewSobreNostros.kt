package es.cenecmalaga.lsedactilologico.adapters_holders

import android.app.Activity
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.NicolasFernandez.lsedactilologico.R
import java.net.URI

class RecyclerViewSobreNostros (val actividad: Activity, val nombresLista:ArrayList<String>,val fotosLista:ArrayList<String>)
    : RecyclerView.Adapter<HolderElementosNosotros>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderElementosNosotros {
       return HolderElementosNosotros(actividad.layoutInflater.inflate(R.layout.elementos_recycleview_nosotros,parent,false))
    }


    /**
     * Funcion para comprobar elementos hay
     */
    override fun getItemCount(): Int {
        return nombresLista.size
    }

    override fun onBindViewHolder(holder: HolderElementosNosotros, position: Int) {
       if (fotosLista.get(position).equals("nico")){
           holder.foto.setImageResource(R.drawable.contribuidor_nico)
       }

        holder.nombre.setText(nombresLista.get(position))
    }


}