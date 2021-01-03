package recycler

import android.app.Activity
import android.database.sqlite.SQLiteDatabase
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import basededatos.Tema5OpenHelper
import clases.Usuario
import com.mparamos.pruebastema5.PruebasSQLite
import com.mparamos.pruebastema5.R

class SQLiteAdapter(val contexto: PruebasSQLite,val datos:ArrayList<Usuario>): RecyclerView.Adapter<SQLiteViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SQLiteViewHolder {
        return SQLiteViewHolder(
                contexto.layoutInflater.inflate(R.layout.elementos_recycler_usuarios,
                        parent,false))
    }

    override fun getItemCount(): Int {
        return datos.size
    }

    override fun onBindViewHolder(holder: SQLiteViewHolder, position: Int) {
        holder.textoNombre.text=datos.get(position).nombre


        holder.textoNombre.setOnClickListener(View.OnClickListener {
            view:View ->
            Toast.makeText(contexto,datos.get(position).contraseña,Toast.LENGTH_LONG).show()
        })

        holder.botonBorrar.setOnClickListener(View.OnClickListener {
            view:View ->
            val usuarioABorrar:Usuario=datos.get(position)
            //delete from usuario where id=usuarioABorrar.id
            contexto.database.delete(Tema5OpenHelper.tablaUsuario,"id=${usuarioABorrar.id}",null)

            datos.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, datos.size)

        })

        holder.botonModificar.setOnClickListener(View.OnClickListener {
            view:View ->
            val usuarioAModificar:Usuario=datos.get(position)
            contexto.campoUsuario.setText(usuarioAModificar.nombre)
            contexto.campoContraseña.setText(usuarioAModificar.contraseña)
            contexto.botonInsertarModificar.setText(R.string.modificar)
            contexto.tituloActividad.setText(R.string.tituloModificar)
            contexto.idUsuarioAModificar=usuarioAModificar.id
        })



    }
}