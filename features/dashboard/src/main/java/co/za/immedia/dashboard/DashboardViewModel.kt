package co.za.immedia.dashboard

import android.app.Application
import androidx.lifecycle.MutableLiveData
import co.za.immedia.mylib.enums.Hosts
import co.za.immedia.superheroapp.features.base.viewmodels.BaseVieModel
import co.za.immedia.commons.models.Superhero
import kotlinx.coroutines.launch

class DashboardViewModel(application: Application, private val dashboardRepository: DashboardRepository) : BaseVieModel(application) {

    private var _showLoading: MutableLiveData<Boolean> = MutableLiveData()
    val showLoading: MutableLiveData<Boolean>
        get() = _showLoading

    private var _superheroes: MutableLiveData<List<Superhero?>?> = MutableLiveData()
    val superheroes: MutableLiveData<List<Superhero?>?>
        get() = _superheroes

    private var _favSuperheroes: MutableLiveData<List<Superhero?>?> = MutableLiveData()
    val favSuperheroes: MutableLiveData<List<Superhero?>?>
        get() = _favSuperheroes

    private var _searchKeyWord: MutableLiveData<String> = MutableLiveData()
    val searchKeyWord: MutableLiveData<String>
        get() = _searchKeyWord

    private var _noHeroesMessage: MutableLiveData<String> = MutableLiveData()
    val noHeroesMessage: MutableLiveData<String>
        get() = _noHeroesMessage

    private var _newFavHero: MutableLiveData<Superhero> = MutableLiveData()
    val newFavHero: MutableLiveData<Superhero>
        get() = _newFavHero

    var busyMessage: String = ""

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
                    _noHeroesMessage.value = "No heroes found that match - ${_searchKeyWord.value}"
                }
            }
        }
    }

    fun addSuperheroToFavourites(superhero: Superhero){
        ioScope.launch {
            var saveOperation = dashboardRepository.addSuperheroToFavDB(superhero)

            uiScope.launch {
                if(saveOperation.isSuccessful){
                    _newFavHero.value = superhero
                    ((_favSuperheroes.value) as ArrayList)?.add(superhero)
                    _favSuperheroes.value = _favSuperheroes.value
                }
                else{
                   // Do something on save to DB fail
                }

            }
        }
    }


    fun setFavSuperheroes() {
        ioScope.launch {
            val favSuperheroes = getFavouriteHeroes()

            uiScope.launch {
                _favSuperheroes.value = favSuperheroes
            }
        }
    }

    suspend fun getFavouriteHeroes(): List<Superhero?>?  {
        return dashboardRepository.getFavHeroesFromDB()
    }
}