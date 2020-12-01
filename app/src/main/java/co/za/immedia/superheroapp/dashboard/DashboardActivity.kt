package co.za.immedia.superheroapp.dashboard

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import co.za.immedia.superheroapp.R

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        var mediaPlayer: MediaPlayer? = MediaPlayer.create(this, R.raw.theme)
        mediaPlayer?.isLooping = false;
        //mediaPlayer?.setVolume(100f,100f);
        mediaPlayer?.start()
        mediaPlayer?.setOnCompletionListener {
            //finish()
        }
    }
}