package co.za.immedia.superheroapp.features.superhero

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class ViewSuperheroViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ViewSuperheroViewModel::class.java)){
            val viewSuperheroRepository = ViewSuperheroRepository()
            return ViewSuperheroViewModel(application, viewSuperheroRepository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}