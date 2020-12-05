package com.tutorialesprogramacionya.proyecto031

import android.app.Activity
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val peticion = 1
    private lateinit var url1: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        boton1.setOnClickListener {
            val intent = Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION)
            startActivityForResult(intent, peticion)
        }

        boton2.setOnClickListener {
            val mediaPlayer = MediaPlayer.create(this, url1)
            mediaPlayer.start()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (resultCode == Activity.RESULT_OK && requestCode == peticion) {
            url1 = data.data
        }
    }
}
