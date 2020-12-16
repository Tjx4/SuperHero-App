package co.za.immedia.herorating

import android.app.Application
import androidx.lifecycle.MutableLiveData
import co.za.immedia.commons.base.viewmodels.BaseVieModel
import co.za.immedia.commons.models.Superhero
import co.za.immedia.repositories.DbRepository
import kotlinx.coroutines.launch

class RatingViewModel(application: Application, private val dbRepository: DbRepository) : BaseVieModel(application) {

    private var _superhero: MutableLiveData<Superhero> = MutableLiveData()
    val superhero: MutableLiveData<Superhero>
        get() = _superhero

    private var _rating: MutableLiveData<Float> = MutableLiveData()
    val rating: MutableLiveData<Float>
        get() = _rating

    fun updateHeroRating(rating: Float){
        ioScope.launch {
            _superhero.value?.let {
                it.rating = rating
                dbRepository.updateHeroDbRating(it)
            }

        }
    }

    fun setCurrentRating() {
        ioScope.launch {
           val superhero = _superhero.value?.id?.let { dbRepository.getHero(it) }

            uiScope.launch {
                _rating.value = superhero?.rating
            }
        }
    }
}