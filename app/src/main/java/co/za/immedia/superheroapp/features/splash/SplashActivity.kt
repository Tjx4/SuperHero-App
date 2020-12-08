package co.za.immedia.superheroapp.features.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import co.za.immedia.superheroapp.extensions.FADE_IN_ACTIVITY
import co.za.immedia.superheroapp.extensions.navigateToActivity
import co.za.immedia.superheroapp.features.dashboard.DashboardActivity
import co.za.immedia.superheroapp.features.intro.IntroActivity
import co.za.immedia.superheroapp.helpers.SharedPrefs

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPrefs = SharedPrefs.getInstance(application)

        if(sharedPrefs.skipIntro)
            navigateToActivity(DashboardActivity::class.java, null, FADE_IN_ACTIVITY)
        else
            navigateToActivity(IntroActivity::class.java, null, FADE_IN_ACTIVITY)

        finish()
    }
}