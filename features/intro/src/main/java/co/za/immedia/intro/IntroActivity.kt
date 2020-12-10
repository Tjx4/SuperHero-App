package co.za.immedia.intro

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import co.za.immedia.commons.extensions.fadeIn
import co.za.immedia.commons.extensions.navigateToActivity
import co.za.immedia.persistence.sharedPrefs.SharedPrefs

class IntroActivity : AppCompatActivity(){
    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        val resID: Int = resources.getIdentifier("theme", "raw", packageName)
        mediaPlayer = MediaPlayer.create(this, resID)

        try {
            mediaPlayer?.setVolume(30f, 30f)
            mediaPlayer?.setOnPreparedListener {
                Handler().postDelayed({
                    findViewById<TextView>(R.id.tvAppname).fadeIn(8000) {}
                    mediaPlayer?.start()
                }, 1500)

            }

            mediaPlayer?.setOnCompletionListener {
               navigateToActivity("co.za.immedia", "co.za.immedia.dashboard.DashboardActivity", null,
                   co.za.immedia.commons.extensions.FADE_IN_ACTIVITY
               )
               finish()
            }

        } catch (e: Exception) {
            navigateToActivity("co.za.immedia", "co.za.immedia.dashboard.DashboardActivity", null,
                co.za.immedia.commons.extensions.FADE_IN_ACTIVITY
            )
            finish()
        }
    }

    fun onSkipIntroClicked(view: View){
        val sharedPrefs = SharedPrefs.getInstance(application)
        sharedPrefs.skipIntro = true
        navigateToActivity( "co.za.immedia", "co.za.immedia.dashboard.DashboardActivity", null,
            co.za.immedia.commons.extensions.FADE_IN_ACTIVITY
        )
        mediaPlayer?.stop()
        finish()
    }

}