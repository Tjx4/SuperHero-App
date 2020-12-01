package co.za.immedia.superheroapp.features.intro

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

        //findViewById<ImageView>(R.id.imgLogo).blinkView(0.6f, 1.0f, 1000, 2, Animation.ABSOLUTE, 0)

        Handler().postDelayed(Runnable {
            findViewById<TextView>(R.id.tvAppname).fadeIn(5000) {

            }

            var mediaPlayer: MediaPlayer? = MediaPlayer.create(this, R.raw.theme)
            mediaPlayer?.isLooping = false;
            //mediaPlayer?.setVolume(100f,100f);
            mediaPlayer?.start()
            mediaPlayer?.setOnCompletionListener {
                // navigateToActivity(DashboardActivity::class.java, null, FADE_IN_ACTIVITY)
            }
        }, 1500)

    }
}