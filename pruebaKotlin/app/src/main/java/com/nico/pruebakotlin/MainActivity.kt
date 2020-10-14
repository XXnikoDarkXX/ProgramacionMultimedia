package com.nico.pruebakotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //practicas de variables
        var numeroLong:Long=33333333333L
        var numeroShor:Short=23243
        var algoChar:Char
        algoChar='4'
        //para realizar variables un poco mas larga
        var prueba:UByte
        prueba=30u
        Log.d("prueba",""+prueba)

        //hacer String
        var uno:Integer=Integer(3)
        var dos:Integer=Integer(3)
        Log.d("comparador==",""+(uno==dos))
        Log.d("comparador ===",""+(uno===dos))

        var esNulo:String?=null
        //para asegurarse de que si es nullo no reviente  Toast.makeText(this, "" + esNulo?.substring(3,5)?.get(0), Toast.LENGTH_LONG).show();
      //para asegurarse un nullPointer Toast.makeText(this,""+esNulo!!.get(3),Toast.LENGTH_LONG).show();
         for(i in 10 downTo 0 step 2){
              Log.d("bucle",""+i)
        }
        //array generico
        var miArray= arrayOf("hola",-3,2.5f)
        //array tipado
        var miArrayInt=arrayOf<Int>(3,2,1,-11);
        var miArray2= intArrayOf(3,2,1,3)
        var arrayString= arrayOf<String?>("aaa","bbb","ccc",null)//creamos un tipo String array y dentro le ponemos un null
        //para lo ponerles un null dentro del <tipo> le ponemos un ?

        //recorrer Gucles
        for(cont in 0 until miArray2.size){
            Log.d("arrays","posicion"+cont+" : "+miArray2[cont])
        }
        //segundo modo
        for(valor in miArray){
            Log.d("array",""+valor)
        }


    }
}