package co.za.immedia.superheroapp.features.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import co.za.immedia.superheroapp.extensions.FADE_IN_ACTIVITY
import co.za.immedia.superheroapp.extensions.navigateToActivity
import co.za.immedia.superheroapp.features.intro.IntroActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigateToActivity(IntroActivity::class.java, null, FADE_IN_ACTIVITY)
    }
}