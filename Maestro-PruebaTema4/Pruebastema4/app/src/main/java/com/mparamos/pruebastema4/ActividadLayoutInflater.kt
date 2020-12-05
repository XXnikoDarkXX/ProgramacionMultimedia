package com.mparamos.pruebastema4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout

class ActividadLayoutInflater : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.actividad_layoutinflater)

        val inflarAqui:ConstraintLayout=
            findViewById<ConstraintLayout>(R.id.inflarAqui)

        val inflater: LayoutInflater =this.layoutInflater
        val tv: ConstraintLayout =inflater.inflate(R.layout.parainflar,inflarAqui,false) as ConstraintLayout
        tv.setBackgroundColor(resources.getColor(R.color.teal_200))
        inflarAqui.addView(tv)
    }
}