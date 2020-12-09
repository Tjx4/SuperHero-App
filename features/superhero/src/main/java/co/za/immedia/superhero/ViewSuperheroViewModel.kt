package co.za.immedia.superhero

import android.app.Application
import androidx.lifecycle.MutableLiveData
import co.za.immedia.commons.base.viewmodels.BaseVieModel
import co.za.immedia.commons.models.Superhero
import kotlinx.coroutines.launch

class ViewSuperheroViewModel(application: Application, private val viewSuperheroRepository: ViewSuperheroRepository) : BaseVieModel(application) {

    private var _superhero: MutableLiveData<Superhero> = MutableLiveData()
    val superhero: MutableLiveData<Superhero>
        get() = _superhero

    private var _isAddToFav: MutableLiveData<Boolean> = MutableLiveData()
    val isAddToFav: MutableLiveData<Boolean>
        get() = _isAddToFav

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

}