package recycler

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import clases.Usuario
import com.mparamos.pruebastema5.R

class SQLITEAdapter(val contexto:Activity,val datos:ArrayList<Usuario>) :RecyclerView.Adapter<SQLiteViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SQLiteViewHolder {
        return SQLiteViewHolder(contexto.layoutInflater.inflate(R.layout.elementos_recycler_usuarios,parent,false))
    }

    /**
     * Contamos cuantos elementos hay
     */
    override fun getItemCount(): Int {
      return datos.size
    }

    override fun onBindViewHolder(holder: SQLiteViewHolder, position: Int) {
      holder.textoNombre.text=datos.get(position).nombre

        holder.textoNombre.setOnClickListener(View.OnClickListener {
            view:View->
            Toast.makeText(contexto,datos.get(position).contraseña,Toast.LENGTH_LONG).show()
        })
    }


}