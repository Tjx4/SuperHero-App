package co.za.immedia.superheroapp.features.superhero

import android.app.Application
import androidx.lifecycle.MutableLiveData
import co.za.immedia.superheroapp.features.base.viewmodels.BaseVieModel
import co.za.immedia.superheroapp.models.Superhero

class ViewSuperheroViewModel(application: Application, val viewSuperheroRepository: ViewSuperheroRepository) : BaseVieModel(application) {

    private var _superhero: MutableLiveData<Superhero> = MutableLiveData()
    val superhero: MutableLiveData<Superhero>
        get() = _superhero

}