package co.za.immedia.superhero

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import co.za.immedia.repositories.DbRepository
import co.za.immedia.repositories.SuperheroesRepository
import co.za.immedia.networking.API
import co.za.immedia.persistence.room.SuperheroDB
import java.lang.IllegalArgumentException

class ViewSuperheroViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ViewSuperheroViewModel::class.java)){
            val dbRepository =
                co.za.immedia.repositories.DbRepository(SuperheroDB.getInstance(application))
            val superheroesRepository =
                co.za.immedia.repositories.SuperheroesRepository(API.retrofit)

            return ViewSuperheroViewModel(application, dbRepository, superheroesRepository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}