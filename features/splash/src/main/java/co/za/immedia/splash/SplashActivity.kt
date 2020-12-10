package co.za.immedia.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import co.za.immedia.commons.constants.INTRO_ACTIVITY
import co.za.immedia.commons.constants.SEARCH_ACTIVITY
import co.za.immedia.commons.extensions.FADE_IN_ACTIVITY
import co.za.immedia.commons.extensions.navigateToActivity
import co.za.immedia.persistence.sharedPrefs.SharedPrefs

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPrefs = SharedPrefs.getInstance(application)

        if (sharedPrefs.skipIntro){
            navigateToActivity(
                SEARCH_ACTIVITY,
                null,
                FADE_IN_ACTIVITY
            )
        }
        else {
            navigateToActivity(
                INTRO_ACTIVITY,
                null,
                FADE_IN_ACTIVITY
            )
        }

        finish()
    }


}