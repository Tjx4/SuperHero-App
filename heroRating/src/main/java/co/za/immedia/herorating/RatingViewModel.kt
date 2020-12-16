package co.za.immedia.herorating

import android.app.Application
import androidx.lifecycle.MutableLiveData
import co.za.immedia.commons.base.viewmodels.BaseVieModel
import co.za.immedia.commons.models.Superhero
import co.za.immedia.repositories.DbRepository

class RatingViewModel(application: Application, dbRepository: DbRepository) : BaseVieModel(application) {

    private var _superhero: MutableLiveData<Superhero> = MutableLiveData()
    val superhero: MutableLiveData<Superhero>
        get() = _superhero

}