package es.cenecmalaga.lsedactilologico

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.NicolasFernandez.lsedactilologico.R
import es.cenecmalaga.lsedactilologico.adapters_holders.RecyclerViewSobreNostros
import java.net.URI

class ActividadSobreNosotros : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.actividad_sobre_nosotros)

        val nombres:ArrayList<String> = ArrayList<String>()

        nombres.add("Nicolas Fernandez Heredia")
        val imagenesContribuidores:ArrayList<String> = ArrayList<String>()
        imagenesContribuidores.add("nico")

        val contenedorRecycler: RecyclerView = findViewById(R.id.listaContribuidores)

        val adapter:RecyclerViewSobreNostros= RecyclerViewSobreNostros(this,nombres,imagenesContribuidores)

        contenedorRecycler.adapter=adapter

        contenedorRecycler.layoutManager=LinearLayoutManager(this)
    }
}