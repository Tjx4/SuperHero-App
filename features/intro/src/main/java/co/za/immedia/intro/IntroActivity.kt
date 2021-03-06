package co.za.immedia.intro

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import co.za.immedia.commons.constants.SEARCH_ACTIVITY
import co.za.immedia.commons.extensions.FADE_IN_ACTIVITY
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
            mediaPlayer?.setVolume(20f, 20f)
            mediaPlayer?.setOnPreparedListener {
                Handler().postDelayed({
                    findViewById<TextView>(R.id.tvAppname).fadeIn(8000) {}
                    mediaPlayer?.start()
                }, 1500)

            }

            mediaPlayer?.setOnCompletionListener {
                navigateToSearchActivity()
            }

        } catch (e: Exception) {
            navigateToSearchActivity()
        }
    }

    fun onSkipIntroClicked(view: View){
        val sharedPrefs = SharedPrefs.getInstance(application)
        sharedPrefs.skipIntro = true

        mediaPlayer?.stop()

        navigateToSearchActivity()
    }

    private fun navigateToSearchActivity() {
        navigateToActivity(
            SEARCH_ACTIVITY, null,
            FADE_IN_ACTIVITY
        )
        finish()
    }

}