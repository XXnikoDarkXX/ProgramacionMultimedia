package clases

import java.util.*
import kotlin.collections.ArrayList

class Persona(var nombre:String, var imagenURL:String) : Comparable<Persona> {
    companion object Factory{
        fun nuevaPersona():Persona{
            var nombresPosibles:ArrayList<String> = arrayListOf<String>("Putin","Sara","Paco","Alfonso", "Pepe", "Rodolfo","Ignacio","Ataulfo","Rascamonas", "Sandra","Repugnancia","Jerónima","Nemesio","Desgraciao","Makinavaja","Manolo","caillou","aladin","MiniYoda","Marciana","elSeco","cabezaNuca");
            var apellidosPosibles:ArrayList<String> = arrayListOf<String>("Kuznetsov", "Cacharro","Martínez","Cañas","Chancleto","Connor","McFlannagan","Páramos","Napialarga","Cañizares","Casillas","Molina","Zubizarreta","Jiménez","Santos","Cintado","Krugger","Smith","García","cabezaAlcaparra","Ramón","Cañas","Perez")
            val ran:Random=Random()
            return Persona(nombresPosibles.get(ran.nextInt(nombresPosibles.size))+" "+apellidosPosibles.get(ran.nextInt(apellidosPosibles.size)),
                    "")
        }
    }
    override fun compareTo(other: Persona): Int {
        return nombre.compareTo(other.nombre)
    }
}