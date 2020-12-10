package co.za.immedia.superhero

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import co.za.immedia.networking.API
import co.za.immedia.persistence.room.SuperheroDB
import java.lang.IllegalArgumentException

class ViewSuperheroViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ViewSuperheroViewModel::class.java)){
            val retrofitHelper = API.retrofit
            var database = SuperheroDB.getInstance(application)
            val viewSuperheroRepository = ViewSuperheroRepository(retrofitHelper, database)
            return ViewSuperheroViewModel(application, viewSuperheroRepository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}