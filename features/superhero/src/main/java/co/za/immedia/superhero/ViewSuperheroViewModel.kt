package co.za.immedia.superhero

import android.app.Application
import androidx.lifecycle.MutableLiveData
import co.za.immedia.commons.base.viewmodels.BaseVieModel
import co.za.immedia.commons.models.Appearance
import co.za.immedia.commons.models.Connections
import co.za.immedia.commons.models.Superhero
import co.za.immedia.commons.models.Work
import co.za.immedia.networking.Hosts
import kotlinx.coroutines.launch

class ViewSuperheroViewModel(application: Application, private val viewSuperheroRepository: ViewSuperheroRepository) : BaseVieModel(application) {

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
            ioScope.launch {
                var saveOperation = viewSuperheroRepository.addSuperheroToFavDB(superhero)

                uiScope.launch {
                    if(saveOperation.isSuccessful){
                        _isAddToFav.value = true
                    }
                }
            }
        }

    }

    fun showHeroAppearance(){
        ioScope.launch {
            val url = "${Hosts.LiveHost.url}api/191417135981966/${_superhero.value?.id}/appearance"
            var heroesAppearance = viewSuperheroRepository.fetchHeroAppearance(url)

            uiScope.launch {
                if(heroesAppearance != null){
                    _appearance.value = heroesAppearance
                }
                else{
                    _appearanceErrorMessage.value = "No info found"
                }
            }
        }
    }

    fun showHeroWork(){
        ioScope.launch {
            val url = "${Hosts.LiveHost.url}api/191417135981966/${_superhero.value?.id}/work"
            var superheroesWork = viewSuperheroRepository.fetchHeroWork(url)

            uiScope.launch {
                if(superheroesWork != null){
                    _superhero.value?.work = superheroesWork
                }
                else{
                    _workErrorsMessage.value = "No info found"
                }
            }
        }
    }

    fun showHeroConnections(){
        ioScope.launch {
            val url = "${Hosts.LiveHost.url}api/191417135981966/${_superhero.value?.id}/connections"
            var heroesConnections = viewSuperheroRepository.fetchHeroConnections(url)

            uiScope.launch {
                if(heroesConnections != null){
                    _connections.value = heroesConnections
                }
                else{
                    _connectionsErrorsMessage.value = "No info found"
                }
            }
        }
    }

}