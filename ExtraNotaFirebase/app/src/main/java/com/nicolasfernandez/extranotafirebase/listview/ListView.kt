package com.nicolasfernandez.extranotafirebase.listview

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.nicolasfernandez.extranotafirebase.clases.Articulo

class ListView (val actividad:Activity,var datos:ArrayList<Articulo>): BaseAdapter() {
    override fun getCount(): Int {
        if (datos==null){
            return 0
        }
    return  datos.size

    }

    override fun getItem(position: Int): Any {
        return datos!!.get(position)
    }

    override fun getItemId(position: Int): Long {
      return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        TODO("Not yet implemented")
    }
}