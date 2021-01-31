package com.nicolasfernandez.pruebaalmacenamientoexternopublico

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.Button
import android.widget.EditText
import java.util.*

class MainActivity : AppCompatActivity() {
    val enterText:EditText by lazy { findViewById<EditText>(R.id.campoTexto) }
    companion object val PERMISSION_CODE=100;
    val save:Button by lazy { findViewById(R.id.btnGuardar) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun pulsaste(view: View) {
            if (!enterText.getText().toString().isEmpty()){
                var state:String=Environment.getExternalStorageState();
                if (Environment.MEDIA_MOUNTED.equals(state)){
                    if (Build.VERSION.SDK_INT>23){
                        if (checkPermission()){

                        }
                    }
                }
            }



    }
}