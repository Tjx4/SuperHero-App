package co.za.immedia.search

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import co.za.immedia.commons.base.viewmodels.BaseVieModel
import co.za.immedia.commons.models.Superhero
import co.za.immedia.repositories.SuperheroesRepository
import co.za.immedia.networking.Hosts
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchViewModel(application: Application, private val superheroesRepository: SuperheroesRepository) : BaseVieModel(application) {

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
        busyMessage = app.getString(R.string.fetching_heroes)
       _showLoading.value = true

        viewModelScope.launch(Dispatchers.IO) {
            val url = "${Hosts.LiveHost.url}api/191417135981966/search/$searchKeywords"
            var superheroes = superheroesRepository.searchForSuperHero(url)

            withContext(Dispatchers.Main) {
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
        viewModelScope.launch(Dispatchers.IO) {
            var saveOperation = superheroesRepository.addSuperheroToFavDB(superhero)

            withContext(Dispatchers.Main) {
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
        viewModelScope.launch(Dispatchers.IO) {
            val favSuperheroes = getFavouriteHeroes()

            withContext(Dispatchers.Main) {
                _favSuperheroes.value = favSuperheroes
            }
        }
    }

    suspend fun getFavouriteHeroes(): List<Superhero?>?  {
        return superheroesRepository.getFavHeroesFromDB()
    }

    fun setFavHeroRating(superhero: Superhero?)  {
        superhero.let {
            viewModelScope.launch(Dispatchers.IO) {
                val favSuperhero = superheroesRepository.getFavHeroFromDB(superhero?.id ?: 0)

                withContext(Dispatchers.Main) {
                    superhero?.rating = favSuperhero?.rating ?: 0.0f
                }
            }
        }
    }

}