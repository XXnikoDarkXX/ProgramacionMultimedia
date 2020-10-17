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

        //podemos crear matrices se hacen asi y pueden ser irregulares:
      var matriz= arrayOf(arrayOf(3,-1,'1',"literal",null), arrayOf("3af299",7,false),1)
     //   (matriz[0] as Array<Int>)[1]=4

     //  Log.d("matriz",""+matriz[0][1])
        //una matriz normal como se cambia:
        var matrizEnteros=arrayOf(intArrayOf(3,2,1),intArrayOf(3,2),intArrayOf(1))
        matrizEnteros[0][2]=0;
        matrizEnteros[0].set(2,0);


        Log.d("matriz",""+matrizEnteros[0][1])
        //una matriz con diferntes tipos
        var matriz2=arrayOf(floatArrayOf(3f,2f,1.4f),intArrayOf(3,2),intArrayOf(1))
        (matriz2[0] as FloatArray).set(2,1.5f);//para cambiar un valor

        var contenidoMatriz:String=" \n";
        //imprimir matrices con un solo tipo
        for (fila in matrizEnteros){
            for(valor in fila){
                contenidoMatriz+=""+valor+" "
            }
            contenidoMatriz+="\n"

        }

        Log.d("recorrerMatriz",""+contenidoMatriz)

        Log.d("matrizIrregular",""+matriz.contentDeepToString())


        for(fila in matrizEnteros){
            for (valor in fila){
                Log.d("suma",""+this.sumaNumeros(valor,1))
            }
            Log.d("Viva la recursividad",imprimeMatrizRecursiva(matrizEnteros,0))


        }






        }
    fun sumaNumeros( n1:Int,  n2:Int):Int{
        return n1+n2

    }


    fun imprimeArrayRecursiva(array:IntArray,cont:Int):String{
        var res:String="";
        if(cont>=array.size){
            //Caso base: He terminado de recorrer
        }else{
            res+=""+array[cont]+" "+imprimeArrayRecursiva(array,cont+1)
        }
        return res;
    }

    fun imprimeMatrizRecursiva(matriz:Array<*>,nFila:Int):String{
        var res:String="";
        if(nFila>=matriz.size){
            //Caso base, me he pasado, no hago nada
        }else{
            res+=""+imprimeArrayRecursiva(matriz[nFila] as IntArray,
                0)+"\n"+imprimeMatrizRecursiva(matriz,nFila+1)
        }
        return res;
    }


}

