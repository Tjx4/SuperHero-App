package co.za.immedia.search

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import co.za.immedia.repositories.DbRepository
import co.za.immedia.repositories.SuperheroesRepository
import co.za.immedia.networking.API
import co.za.immedia.persistence.room.SuperheroDB
import java.lang.IllegalArgumentException

class SearchViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SearchViewModel::class.java)){
            val dbRepository = DbRepository(SuperheroDB.getInstance(application))
            val superheroesRepository = SuperheroesRepository(API.retrofit)

            return SearchViewModel(application, dbRepository, superheroesRepository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}