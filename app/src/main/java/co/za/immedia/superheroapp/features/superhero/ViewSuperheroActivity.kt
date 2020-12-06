package co.za.immedia.superheroapp.features.superhero

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import co.za.immedia.superheroapp.R
import co.za.immedia.superheroapp.databinding.ActivityViewSuperheroBinding
import co.za.immedia.superheroapp.models.Superhero

class ViewSuperheroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityViewSuperheroBinding
    lateinit var viewSuperheroViewModel: ViewSuperheroViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var application = requireNotNull(this).application
        var viewModelFactory = ViewSuperheroViewModelFactory(application)

        viewSuperheroViewModel = ViewModelProviders.of(this, viewModelFactory).get(ViewSuperheroViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_view_superhero)
        binding.viewSuperheroViewModel = viewSuperheroViewModel
        binding.lifecycleOwner = this

        addObservers()
    }

    private fun addObservers() {
        viewSuperheroViewModel.superhero.observe(this, Observer { onSuperheroSet(it) })
    }

    fun onSuperheroSet(superhero: Superhero){

    }
}