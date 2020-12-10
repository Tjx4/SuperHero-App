package co.za.immedia.superheroapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import co.za.immedia.commons.extensions.FADE_IN_ACTIVITY
import co.za.immedia.commons.extensions.navigateToActivity
import co.za.immedia.superheroapp.nav.Navigation
import co.za.immedia.persistence.sharedPrefs.SharedPrefs
import co.za.immedia.superheroapp.nav.SearchNavigation

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPrefs = SharedPrefs.getInstance(application)

        if(sharedPrefs.skipIntro)
            navigateToActivity("co.za.immedia",
                "co.za.immedia.dashboard.DashboardActivity",
                null,
                FADE_IN_ACTIVITY
            )
        else

           // SearchNavigation.getIntent()

            navigateToActivity(
                "co.za.immedia",
                "co.za.immedia.intro.IntroActivity",
                null,
                FADE_IN_ACTIVITY
            )


        finish()
    }
}