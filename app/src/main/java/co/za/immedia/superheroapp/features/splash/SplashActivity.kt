package co.za.immedia.superheroapp.features.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import co.za.immedia.commons.extensions.FADE_IN_ACTIVITY
import co.za.immedia.commons.extensions.navigateToActivity
import co.za.immedia.dashboard.DashboardActivity
import co.za.immedia.superheroapp.features.intro.IntroActivity
import co.za.immedia.superheroapp.helpers.SharedPrefs

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPrefs = SharedPrefs.getInstance(application)

        if(sharedPrefs.skipIntro)
            navigateToActivity(co.za.immedia.dashboard.DashboardActivity::class.java, null,
                co.za.immedia.commons.extensions.FADE_IN_ACTIVITY
            )
        else
            navigateToActivity(IntroActivity::class.java, null,
                co.za.immedia.commons.extensions.FADE_IN_ACTIVITY
            )

        finish()
    }
}