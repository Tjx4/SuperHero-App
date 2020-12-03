package co.za.immedia.superheroapp.features.intro

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import co.za.immedia.superheroapp.R
import co.za.immedia.superheroapp.extensions.FADE_IN_ACTIVITY
import co.za.immedia.superheroapp.extensions.fadeIn
import co.za.immedia.superheroapp.extensions.navigateToActivity
import co.za.immedia.superheroapp.features.dashboard.DashboardActivity
import java.io.IOException

class IntroActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)


            //var mediaPlayer: MediaPlayer? = MediaPlayer.create(this, R.raw.theme)
        val resID: Int = resources.getIdentifier("theme", "raw", packageName)
        val mediaPlayer = MediaPlayer.create(this, resID)

        try {
            mediaPlayer?.setVolume(70f, 70f)
            mediaPlayer?.setOnPreparedListener {
                Handler().postDelayed({
                    findViewById<TextView>(R.id.tvAppname).fadeIn(8000) {}
                    mediaPlayer?.start()
                }, 1500)

            }

            mediaPlayer?.setOnCompletionListener {
               navigateToActivity(DashboardActivity::class.java, null, FADE_IN_ACTIVITY)
               finish()
            }

        } catch (e: Exception) {
            navigateToActivity(DashboardActivity::class.java, null, FADE_IN_ACTIVITY)
            finish()
        }
    }

}