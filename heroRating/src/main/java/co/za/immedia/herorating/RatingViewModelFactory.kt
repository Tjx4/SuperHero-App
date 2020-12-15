package co.za.immedia.herorating

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class RatingViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(RatingViewModel::class.java)){
            //var database = SuperheroDB.getInstance(application)
            //val searchRepository = SearchRepository(retrofitHelper, database)
            return RatingViewModel(application) as T
        }

        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}