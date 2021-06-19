package co.za.immedia.herorating

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import co.za.immedia.networking.API
import co.za.immedia.persistence.room.SuperheroDB
import co.za.immedia.repositories.SuperheroesRepository
import java.lang.IllegalArgumentException

class ViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(RatingViewModel::class.java) -> {
                val dbRepository = SuperheroesRepository(API.retrofit, SuperheroDB.getInstance(application))
                return RatingViewModel(application, dbRepository) as T
            }
        }

        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}