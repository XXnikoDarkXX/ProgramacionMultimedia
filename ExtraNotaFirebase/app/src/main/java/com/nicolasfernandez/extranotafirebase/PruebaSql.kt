package com.nicolasfernandez.extranotafirebase

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast
import com.nicolasfernandez.extranotafirebase.basededatos.pruebaSqliteOpenHelper
import com.nicolasfernandez.extranotafirebase.clases.Articulo

class PruebaSql : AppCompatActivity() {
    val database:SQLiteDatabase by lazy {  pruebaSqliteOpenHelper(this).writableDatabase}
    val txtNombreSql:EditText by lazy { findViewById<EditText>(R.id.txtNombreSql) }
    val txtPrecioSql:EditText by lazy { findViewById<EditText>(R.id.txtPrecioSql) }
    val switchEnVentaSql:Switch by lazy { findViewById<Switch>(R.id.switchEnVentaSql) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prueba_sql)
    }




    fun clickBontonInsertarSql(view: View) {

        val datosAinsertar:ContentValues= ContentValues()

        datosAinsertar.put(pruebaSqliteOpenHelper.nombre,txtNombreSql.text.toString())
        datosAinsertar.put(pruebaSqliteOpenHelper.precio,txtPrecioSql.text.toString().toFloat())

        datosAinsertar.put(pruebaSqliteOpenHelper.enVenta,switchEnVentaSql.isChecked)

        if(database.insert(pruebaSqliteOpenHelper.tablaArticulo,null,datosAinsertar)>0){
            Toast.makeText(this,"Inserccion correcta",Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(this,"Inserccion fallida",Toast.LENGTH_LONG).show()
        }
    }

    fun clickBotonConsultarSql(view: View) {

        var arti:ArrayList<Articulo> = ArrayList<Articulo>()
        val cursor:Cursor= database.query(pruebaSqliteOpenHelper.tablaArticulo,null,null,null,null,null,null)
    cursor.moveToFirst()

        while(!cursor.isAfterLast){
            val nombre=cursor.getString(cursor.getColumnIndex(pruebaSqliteOpenHelper.nombre))
            val precio:Float=cursor.getFloat(cursor.getColumnIndex(pruebaSqliteOpenHelper.precio))
            var enVenta:String=cursor.getString(cursor.getColumnIndex(pruebaSqliteOpenHelper.enVenta.toString()))

            if (enVenta.equals("1")){
                enVenta="true"
            }else{
                enVenta="false"
            }
            Toast.makeText(this,nombre+" "+precio+" "+enVenta,Toast.LENGTH_LONG).show()
            cursor.moveToNext()

        }
    }


}