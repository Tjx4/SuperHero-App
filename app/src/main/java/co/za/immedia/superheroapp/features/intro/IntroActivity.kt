package co.za.immedia.superheroapp.features.intro

import android.media.AudioManager
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.widget.ImageView
import android.widget.TextView
import co.za.immedia.superheroapp.R
import co.za.immedia.superheroapp.extensions.FADE_IN_ACTIVITY
import co.za.immedia.superheroapp.extensions.blinkView
import co.za.immedia.superheroapp.extensions.fadeIn
import co.za.immedia.superheroapp.extensions.navigateToActivity
import co.za.immedia.superheroapp.features.dashboard.DashboardActivity

class IntroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

            findViewById<TextView>(R.id.tvAppname).fadeIn(7000) {

            }

            var mediaPlayer: MediaPlayer? = MediaPlayer.create(this, R.raw.theme)
            mediaPlayer?.setVolume(70f,70f)
            mediaPlayer?.isLooping = false
            mediaPlayer?.setAudioStreamType(AudioManager.STREAM_MUSIC)
            mediaPlayer?.reset()


            mediaPlayer?.setOnPreparedListener {
                mediaPlayer?.start()
            }
            mediaPlayer?.setOnCompletionListener {
                navigateToActivity(DashboardActivity::class.java, null, FADE_IN_ACTIVITY)
                finish()
            }

            mediaPlayer?.prepareAsync()
    }
}