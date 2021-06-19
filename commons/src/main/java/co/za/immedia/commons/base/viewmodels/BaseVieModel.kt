package co.za.immedia.commons.base.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel

abstract class BaseVieModel(val app: Application) : AndroidViewModel(app){
}