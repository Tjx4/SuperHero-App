package co.za.immedia.superheroapp.features.dashboard

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import co.za.immedia.superheroapp.database.SuperheroDB
import co.za.immedia.mylib.API
import java.lang.IllegalArgumentException

class DashboardViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(DashboardViewModel::class.java)){
            val retrofitHelper = API.retrofit
            var database = SuperheroDB.getInstance(application)
            val dashboardRepository = DashboardRepository(retrofitHelper, database)
            return DashboardViewModel(application, dashboardRepository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}