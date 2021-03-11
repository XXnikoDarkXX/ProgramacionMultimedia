package com.mparamos.pruebastema5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat

class PreferenciasV2Fragment() : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {

        this.preferenceManager.sharedPreferencesName="Preferencias"
        this.setPreferencesFromResource(R.xml.pantalla_preferencias_v2,rootKey)


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


}