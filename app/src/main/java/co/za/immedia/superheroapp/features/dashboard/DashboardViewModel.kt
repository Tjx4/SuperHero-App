package co.za.immedia.superheroapp.features.dashboard

import android.app.Application
import androidx.lifecycle.MutableLiveData
import co.za.immedia.superheroapp.features.base.viewmodels.BaseVieModel
import co.za.immedia.superheroapp.models.SuperHero

class DashboardViewModel(application: Application, val dashboardRepository: DashboardRepository) : BaseVieModel(application) {

    private var _superHero: MutableLiveData<SuperHero> = MutableLiveData()
    val superHero: MutableLiveData<SuperHero>
        get() = _superHero

    init {
        _superHero.value = SuperHero("Iron man")
    }

}