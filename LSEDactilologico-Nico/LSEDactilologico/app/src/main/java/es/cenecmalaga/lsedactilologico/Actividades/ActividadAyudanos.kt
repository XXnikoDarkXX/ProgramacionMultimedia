package es.cenecmalaga.lsedactilologico.Actividades

import android.Manifest
import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.NicolasFernandez.lsedactilologico.R
import java.io.IOException

class ActividadAyudanos : AppCompatActivity() {


    private var mediaRecorder: MediaRecorder? = null
    private var mediaPlayer: MediaPlayer? = null
    val PERMISO_ALMACENAMIENTO=400
    private var audioFilePath: String? = null
    private var isRecording = false
    companion object val PERMISO_MICROFONO=1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.actividad_ayudanos)

        audioSetup()


    }


    /**
     * Funcion que comprueba los botones y crea el archivo
     */
    private fun audioSetup() {
    val stopButton:Button=findViewById(R.id.btnParar)

    val playButton:Button=findViewById(R.id.btnReproducir)
        val recordButton:Button=findViewById(R.id.btnGrabar)
        if (!hasMicrophone()) {
            stopButton.isEnabled = false
            playButton.isEnabled = false
            recordButton.isEnabled = false
        } else {
            playButton.isEnabled = false
            stopButton.isEnabled = false
        }

        audioFilePath = Environment.getExternalStorageDirectory()
            .absolutePath + "/myaudio.3gp"
    }

    /**
     * Funcion para usar el microfono
     */
    private fun hasMicrophone(): Boolean {
        val pmanager = this.packageManager
        return pmanager.hasSystemFeature(
            PackageManager.FEATURE_MICROPHONE)
    }

    /**
     * Funcion para reproducir el audio usando mediaPlayer
     */
    fun playAudio(view: View) {
        val stopButton:Button=findViewById(R.id.btnParar)

        val playButton:Button=findViewById(R.id.btnReproducir)
        val recordButton:Button=findViewById(R.id.btnGrabar)
        playButton.isEnabled = false
        recordButton.isEnabled = false
        stopButton.isEnabled = true

        mediaPlayer = MediaPlayer()
        mediaPlayer?.setDataSource(audioFilePath)
        mediaPlayer?.prepare()
        mediaPlayer?.start()


    }

    /**
     * Funcion para grabar el audio se grabara en caso de que tengamos los permisos de almacenamiento y microfono concedidos a la aplicacion
     * Si estamos grabando se parara el boton de grabar y reproducir
     */
    fun recordAudio(view: View) {
        if (permisos()==true){
            val stopButton:Button=findViewById(R.id.btnParar)

            val playButton:Button=findViewById(R.id.btnReproducir)
            val recordButton:Button=findViewById(R.id.btnGrabar)

            isRecording = true
            stopButton.isEnabled = true
            playButton.isEnabled = false
            recordButton.isEnabled = false

            try {
                mediaRecorder = MediaRecorder()
                mediaRecorder?.setAudioSource(MediaRecorder.AudioSource.MIC)
                mediaRecorder?.setOutputFormat(
                    MediaRecorder.OutputFormat.THREE_GPP)
                mediaRecorder?.setOutputFile(audioFilePath)
                mediaRecorder?.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
                mediaRecorder?.prepare()
            } catch (e: Exception) {
                e.printStackTrace()
            }
            mediaRecorder?.start()
        }else{

        }



    }

    /**
     * Funcion para parar el audio su uso es tanto para terminar de reproducir como de grabar
     */
    fun stopAudio(view: View) {
        val stopButton:Button=findViewById(R.id.btnParar)

        val playButton:Button=findViewById(R.id.btnReproducir)
        val recordButton:Button=findViewById(R.id.btnGrabar)
        stopButton.isEnabled = false
        playButton.isEnabled = true

        if (isRecording) {
            recordButton.isEnabled = false
            mediaRecorder?.stop()
            mediaRecorder?.release()
            mediaRecorder = null
            isRecording = false
        } else {
            mediaPlayer?.release()
            mediaPlayer = null
            recordButton.isEnabled = true
        }


    }

    /**
     * Funcion para pedir permisos de almacenamiento y microfono
     */
    private fun permisos(): Boolean {
            var  comprobacionAudio:Boolean
            var comprobacionAlmacenamiento:Boolean
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
            == PackageManager.PERMISSION_GRANTED )

        {
            comprobacionAudio=true


        }else{
            if (!ActivityCompat.shouldShowRequestPermissionRationale(
                    this,Manifest.permission.RECORD_AUDIO)) {
                Toast.makeText(this,R.string.permisoDenegado,
                    Toast.LENGTH_LONG).show()

            }
            val permisos:Array<String> = arrayOf<String>(Manifest.permission.RECORD_AUDIO);
            ActivityCompat.requestPermissions(this,permisos,PERMISO_MICROFONO)
            comprobacionAudio=false

        }



        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            == PackageManager.PERMISSION_GRANTED ){

            comprobacionAlmacenamiento=true

        }else{
            if (!ActivityCompat.shouldShowRequestPermissionRationale(
                    this,Manifest.permission.RECORD_AUDIO)) {
                Toast.makeText(this,R.string.permisoDenegado,
                    Toast.LENGTH_LONG).show()

            }
            val permisos:Array<String> = arrayOf<String>(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            ActivityCompat.requestPermissions(this,permisos,PERMISO_ALMACENAMIENTO)
            comprobacionAlmacenamiento=false

        }



        if (comprobacionAlmacenamiento==true&&comprobacionAudio==true){
            return true
        }
        return false
    }
}