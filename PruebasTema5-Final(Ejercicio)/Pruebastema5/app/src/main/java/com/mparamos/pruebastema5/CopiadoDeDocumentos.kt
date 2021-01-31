package com.mparamos.pruebastema5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.TextView
import lisview.ListaAlmacenamiento

class CopiadoDeDocumentos : AppCompatActivity() {
    val listaInternaPrivada: ListView by lazy { findViewById<ListView>(R.id.listaInternaPrivada) }
    val listaExternaPrivada: ListView by lazy { findViewById<ListView>(R.id.listaExternaPrivada) }
    val campoNombreCopiado:TextView by lazy { findViewById<TextView>(R.id.campoNombreCopiado) }

    val campoContenidoCopiado:TextView by lazy { findViewById<TextView>(R.id.campoContenidoCopiado) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_copiado_de_documentos)

        recargaRecyclesView( )

    }

    /**
     * @param tipoAlmacenamiento vale 0 para el almacenamiento interno privado, 1 para el externo privado, y 2 para el externo publico, no se permite ningún otro valor
     */
    fun recargaRecyclesView( ){
        //Listar en la parte derecha todos los ficheros disponibles
        var archivosInterno:Array<out String>?=null
        var archivosExternoPrivado:Array<out String>?=null


        archivosInterno= applicationContext.fileList()


        archivosExternoPrivado   =this.getExternalFilesDir(null)!!.list()
        val adapter: ListaAlmacenamiento = ListaAlmacenamiento(this,archivosInterno,"InternoPrivado")

        listaInternaPrivada.adapter=adapter


        val adapterExternoPrivado:ListaAlmacenamiento=ListaAlmacenamiento(this,archivosExternoPrivado,"ExternaPrivada")
         listaExternaPrivada.adapter=adapterExternoPrivado


        //    -------------------------------------------------------------


    }





}