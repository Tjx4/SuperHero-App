package co.za.immedia.superheroapp.features.dashboard

import android.app.Application
import androidx.lifecycle.MutableLiveData
import co.za.immedia.superheroapp.enums.Hosts
import co.za.immedia.superheroapp.features.base.viewmodels.BaseVieModel
import co.za.immedia.superheroapp.models.Superhero
import kotlinx.coroutines.launch

class DashboardViewModel(application: Application, val dashboardRepository: DashboardRepository) : BaseVieModel(application) {

    private var _showLoading: MutableLiveData<Boolean> = MutableLiveData()
    val showLoading: MutableLiveData<Boolean>
        get() = _showLoading

    private var _superheroes: MutableLiveData<List<Superhero?>?> = MutableLiveData()
    val superheroes: MutableLiveData<List<Superhero?>?>
        get() = _superheroes

    private var _searchKeyWord: MutableLiveData<String> = MutableLiveData()
    val searchKeyWord: MutableLiveData<String>
        get() = _searchKeyWord

    private var _noHeroesMessage: MutableLiveData<String> = MutableLiveData()
    val noHeroesMessage: MutableLiveData<String>
        get() = _noHeroesMessage

    private var _isHeroAdded: MutableLiveData<Boolean> = MutableLiveData()
    val isHeroAdded: MutableLiveData<Boolean>
        get() = _isHeroAdded



    var busyMessage: String = ""

    init {

    }


    fun searchForHero(searchKeywords: String){
        busyMessage = "fetching outlets, please wait..."
       _showLoading.value = true

        ioScope.launch {
            val url = "${Hosts.LiveHost.url}api/191417135981966/search/$searchKeywords"
            var superheroes = dashboardRepository.searchForSuperHero(url)

            uiScope.launch {
                if(superheroes != null && !superheroes.results.isNullOrEmpty()){
                    _superheroes.value = superheroes.results
                }
                else{
                    _noHeroesMessage.value = "No heroes found that match :${_searchKeyWord.value}"
                }

            }
        }
    }

    fun addSuperheroToFavourites(superhero: Superhero){
        _isHeroAdded.value = true
    }
}