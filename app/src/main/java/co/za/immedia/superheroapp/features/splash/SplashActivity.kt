package co.za.immedia.superheroapp.features.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import co.za.immedia.superheroapp.features.dashboard.DashboardActivity
import co.za.immedia.superheroapp.extensions.FADE_IN_ACTIVITY
import co.za.immedia.superheroapp.extensions.navigateToActivity


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_splash)
       navigateToActivity(DashboardActivity::class.java, null, FADE_IN_ACTIVITY)
    }
}