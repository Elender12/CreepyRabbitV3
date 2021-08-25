package com.ecirstea.creepyrabbit.ui.view

import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ecirstea.creepyrabbit.R
import com.ecirstea.creepyrabbit.R.string.startTime
import kotlinx.android.synthetic.main.activity_player.*


private const val TAG= "player"
class PlayerActivity : AppCompatActivity() {
    private lateinit var mediaPlayer : MediaPlayer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)
        val extras = intent.extras
        val title = extras!!.get("title") as String
        val audioUrl: String = extras.get("audioURL") as String
        val author: String = extras.get("author") as String
        val narrator: String =  extras.get("narrator")  as String
        tvAudioTitle.text = title
        tvNarratedBy.text = resources.getString(R.string.narrated, narrator)
        tvWrittenBy.text = resources.getString(R.string.author, author)

        try {
            mediaPlayer = MediaPlayer().apply {
                setAudioAttributes(
                    AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .build()
                )
                setDataSource(audioUrl)
                prepare()
            }
            mediaPlayer.setOnCompletionListener {
                MediaPlayer.OnCompletionListener { mp ->
                    Log.d(TAG, "onCompleteSong: ++")
                    mp?.release()
                }
            }
        } catch (ex: Exception) {
            Log.d(TAG, "onCreateMediaPlayer:  ${ex.message}")
        }

        seekbar.max = mediaPlayer.duration
        val totalTime = createTime(mediaPlayer.duration)
        tvAudioStart.text = resources.getString(startTime)
        tvAudioStop.text = totalTime

        //colors for progress bar
        val colorFilter = PorterDuffColorFilter(Color.GREEN, PorterDuff.Mode.MULTIPLY)
        seekbar.progressDrawable.colorFilter = colorFilter
        seekbar.thumb.colorFilter = PorterDuffColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN)


        //Progress BAR
        seekbar?.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    mediaPlayer.seekTo(progress)
                }
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                seekBar?.progress?.let { mediaPlayer.seekTo(it) }
            }
        }
        )


        //Thread to update progress bar
        Thread{
            val totalDuration = mediaPlayer.duration
            var currentPosition = 0
            while (currentPosition < totalDuration){
                try{
                    // sleep(100)
                    currentPosition = mediaPlayer.currentPosition
                    seekbar.progress = currentPosition

                }catch(ex: InterruptedException){
                    ex.printStackTrace()
                }
            }
            Log.d(TAG, "onCreate: current position $currentPosition")
            Log.d(TAG, "onCreate: total duration $totalDuration")
            if(currentPosition == totalDuration){
                seekbar.setProgress(0,true)
                onCompleteSong()
            }
        }.start()
    }
    fun playAudio( view: View){
        if(mediaPlayer.isPlaying){
            Toast.makeText(this@PlayerActivity, "AudioIS NOT Playing RIGHT NOW", Toast.LENGTH_SHORT).show()
            playBtn.setBackgroundResource(R.drawable.ic_baseline_play_circle_filled_24)
            mediaPlayer.pause()
        }else{
            Toast.makeText(this@PlayerActivity, "Audio is playing RIGHT NOW", Toast.LENGTH_SHORT).show()
            playBtn.setBackgroundResource(R.drawable.ic_baseline_pause_circle_filled_24)
            mediaPlayer.start()
            //onCompleteSong()
            updateSongTime.run()
        }
    }
    private fun createTime(duration: Int): String{
        var time: String
        val min: Int = duration/1000/60
        val sec: Int = duration/1000%60
        time = "$min:"
        if(sec<10){
            time += "0"
        }
        time += sec
        return time
    }
    private val updateSongTime: Runnable = object : Runnable {
        override fun run() {
            tvAudioStart.text = createTime(mediaPlayer.currentPosition)
            if(mediaPlayer.currentPosition == mediaPlayer.duration){
                Log.d(TAG, "run: =????")
            }
            Handler(Looper.getMainLooper()).postDelayed(this, 1000)
        }
    }

    private fun onCompleteSong() {
        tvAudioStart.text = startTime.toString()

    }
}