package com.nico.pruebakotlin.actividades

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.nico.pruebakotlin.R
import com.nico.pruebakotlin.clases.Alumno
import com.nico.pruebakotlin.clases.Persona
import com.nico.pruebakotlin.enums.MiEnum
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Usando Coleeciones: Treemap
        var mapa:TreeMap<Float,Double>
        mapa=TreeMap<Float,Double>()
        mapa.put(3.5f,3.5)

        //USANDO lista
        var lista:ArrayList<Int>
        //para meter valores directamente en un arrayList
        lista= arrayListOf(3,4,5,6,7,8,9)
        //USANDO HashMap
        var mapaHash:HashMap<Int,String>
        //para añadir valores directamente
        mapaHash= hashMapOf(6 to "seis",9 to "patata")

        //usando colleciones de kotlin
        //con el ? detras del tipo podemos ponerle null
        var listaKotlin:Collection<Int?>
        listaKotlin= arrayListOf(3,4,5,6,null)

        //Usando Stack
        var pila:Collection<Int?>
        pila=Stack<Int?>()

        //Usando hashMap
        var listaMapaSet:MutableCollection<Int?>
        pila= hashSetOf<Int?>(3)
        //vamos a recorrer una coleccion
        var it:Iterator<Int?>
        it=lista.iterator()

        while(it.hasNext()){
           val actual:Int?= it.next()
        }
        //tenemos que usar mutable para que se puedan cambiar es decir se pueda usar el .add
        var listaTradi:MutableList<String>
        listaTradi=ArrayList<String>()
        listaTradi.add("a")

        //haciendo un enum class aparte
        var patata: MiEnum = MiEnum.UNO
        //Haciendo un enum dentro de una clase
        var masPatata: Persona.MiEnumPer= Persona.MiEnumPer.UNO


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

        var per: Persona = Persona("Nico",1986);
        per.nombre="Nico";

        Toast.makeText(this,""+per.test,Toast.LENGTH_LONG).show()
        //usando polimofirmo en kotlin de Persona=Alumno
        var alu: Persona = Alumno();
        Toast.makeText(this,""+alu.nombre+" : "+alu.anioNacimiento+" : "+(alu as Alumno).nLista+" : ",Toast.LENGTH_LONG).show()

        cuadroTexto.text


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

