package com.ecirstea.creepyrabbit.services

import android.app.Service
import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import android.os.Binder
import android.os.IBinder
import android.util.Log

private const val TAG= "service"
class PlayerService : Service() {

    private val binder = MyServiceBinder()
    private lateinit var player: MediaPlayer

    override fun onBind(intent: Intent): IBinder {
        return binder;
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }

    fun startMusic(audioUrl: String){
         val headers = mutableMapOf<String, String>()

        headers["Cookie"] = "CloudFront-Policy=xxx; CloudFront-Signature=xxx; CloudFront-Key-Pair-Id=xxx; Domain=xx; Path=xxx\r\n"
        Log.d(TAG, "startMusic: audioURL: $audioUrl")
        try {
            player.stop()
            player.release()
        } catch (e: UninitializedPropertyAccessException) {
        }
        Log.i(TAG, "doTask: The service is doing something")

        player = MediaPlayer().apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )
            setDataSource(audioUrl)
            prepare()

        }
        player.start()
    }

    fun stopMusic() {
        player.stop()
    }

    inner class MyServiceBinder: Binder() {
        fun getService() =this@PlayerService

    }
}