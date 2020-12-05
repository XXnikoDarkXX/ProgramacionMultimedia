package com.mparamos.pruebastema5

import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import basededatos.Tema5OpenHelper

class PruebasSQLite : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pruebassqlite)
        val openHelper:Tema5OpenHelper=Tema5OpenHelper(this)
        val query:SQLiteDatabase=openHelper.writableDatabase
    }
}