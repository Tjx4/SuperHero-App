package co.za.immedia.superheroapp.features.dashboard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import co.za.immedia.superheroapp.R

class DashboardActivity : AppCompatActivity() {
    // https://superheroapi.com/api/3899774550042021

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        supportActionBar?.elevation = 0f
        supportActionBar?.title = "Heros"
    }
}