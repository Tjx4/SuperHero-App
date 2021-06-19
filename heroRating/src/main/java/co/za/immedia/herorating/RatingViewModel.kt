package co.za.immedia.herorating

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import co.za.immedia.commons.base.viewmodels.BaseVieModel
import co.za.immedia.commons.models.Superhero
import co.za.immedia.repositories.SuperheroesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RatingViewModel(application: Application, private val superheroesRepository: SuperheroesRepository) : BaseVieModel(application) {

    private var _superhero: MutableLiveData<Superhero> = MutableLiveData()
    val superhero: MutableLiveData<Superhero>
        get() = _superhero

    private var _rating: MutableLiveData<Float> = MutableLiveData()
    val rating: MutableLiveData<Float>
        get() = _rating

    fun updateHeroRating(rating: Float){
        viewModelScope.launch(Dispatchers.IO) {
            _superhero.value?.let {
                it.rating = rating
                superheroesRepository.updateHeroDbRating(it)
            }

        }
    }
}