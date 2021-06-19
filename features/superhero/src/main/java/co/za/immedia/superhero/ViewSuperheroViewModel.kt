package co.za.immedia.superhero

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import co.za.immedia.commons.base.viewmodels.BaseVieModel
import co.za.immedia.commons.models.Appearance
import co.za.immedia.commons.models.Connections
import co.za.immedia.commons.models.Superhero
import co.za.immedia.commons.models.Work
import co.za.immedia.networking.Hosts
import co.za.immedia.repositories.SuperheroesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ViewSuperheroViewModel(application: Application, private val superheroesRepository: SuperheroesRepository) : BaseVieModel(application) {

    private var _superhero: MutableLiveData<Superhero> = MutableLiveData()
    val superhero: MutableLiveData<Superhero>
        get() = _superhero

    private var _appearance: MutableLiveData<Appearance> = MutableLiveData()
    val appearance: MutableLiveData<Appearance>
        get() = _appearance

    private var _work: MutableLiveData<Work> = MutableLiveData()
    val work: MutableLiveData<Work>
        get() = _work

    private var _connections: MutableLiveData<Connections> = MutableLiveData()
    val connections: MutableLiveData<Connections>
        get() = _connections

    private var _isAddToFav: MutableLiveData<Boolean> = MutableLiveData()
    val isAddToFav: MutableLiveData<Boolean>
        get() = _isAddToFav

    private var _appearanceErrorMessage: MutableLiveData<String> = MutableLiveData()
    val appearanceErrorMessage: MutableLiveData<String>
        get() = _appearanceErrorMessage

    private var _workErrorsMessage: MutableLiveData<String> = MutableLiveData()
    val workErrorsMessage: MutableLiveData<String>
        get() = _workErrorsMessage

    private var _connectionsErrorsMessage: MutableLiveData<String> = MutableLiveData()
    val connectionsErrorsMessage: MutableLiveData<String>
        get() = _connectionsErrorsMessage

    fun addSuperheroToFavourites(){
        _superhero.value?.let { superhero ->
            viewModelScope.launch(Dispatchers.IO) {
                var saveOperation = superheroesRepository.addSuperheroToFavDB(superhero)

                withContext(Dispatchers.Main) {
                    if(saveOperation.isSuccessful){
                        _isAddToFav.value = true
                    }
                }
            }
        }
    }

    fun showHeroAppearance(){
        viewModelScope.launch(Dispatchers.IO) {
            val url = "${Hosts.LiveHost.url}api/191417135981966/${_superhero.value?.id}/appearance"
            var heroesAppearance = superheroesRepository.fetchHeroAppearance(url)

            withContext(Dispatchers.Main) {
                if(heroesAppearance != null){
                    _appearance.value = heroesAppearance
                }
                else{
                    _appearanceErrorMessage.value = "No info found"
                }
            }
        }
    }

}