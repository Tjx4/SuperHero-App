package co.za.immedia.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import co.za.immedia.commons.extensions.FADE_IN_ACTIVITY
import co.za.immedia.commons.extensions.navigateToActivity
import co.za.immedia.intro.IntroActivity
import co.za.immedia.persistence.sharedPrefs.SharedPrefs

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPrefs = SharedPrefs.getInstance(application)

        if (sharedPrefs.skipIntro){
            navigateToActivity(
                "co.za.immedia",
                "co.za.immedia.dashboard.DashboardActivity",
                null,
                FADE_IN_ACTIVITY
            )
        }
        else {
            navigateToActivity(IntroActivity::class.java,null,
                FADE_IN_ACTIVITY
            )
            /*
            navigateToActivity(
                "co.za.immedia",
                "co.za.immedia.intro.IntroActivity",
                null,
                FADE_IN_ACTIVITY
            )
            */
        }

        finish()
    }
}