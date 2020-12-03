package co.za.immedia.superheroapp.features.dashboard

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import co.za.immedia.superheroapp.R
import co.za.immedia.superheroapp.adapters.SuperheroesAdapter
import co.za.immedia.superheroapp.databinding.ActivityDashboardBinding
import co.za.immedia.superheroapp.features.base.activities.BaseActivity
import co.za.immedia.superheroapp.models.SuperHero
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity : BaseActivity() {
    // https://superheroapi.com/api/3899774550042021

    private lateinit var binding: ActivityDashboardBinding
    lateinit var dashboardViewModel: DashboardViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var application = requireNotNull(this).application
        var viewModelFactory = DashboardViewModelFactory(application)

        dashboardViewModel = ViewModelProviders.of(this, viewModelFactory).get(DashboardViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard)
        binding.dashboardViewModel = dashboardViewModel
        binding.lifecycleOwner = this

        addObservers()

        supportActionBar?.elevation = 0f
        supportActionBar?.title = "Heroes"

        setHeroesList()
    }

    private fun addObservers() {
        dashboardViewModel.showLoading.observe(this, Observer { onShowLoading(it) })
        dashboardViewModel.noHeroesMessage.observe(this, Observer { onNoHeroesFound(it) })
        dashboardViewModel.superheroes.observe(this, Observer { onHeroesFound(it) })
    }

    private fun onShowLoading(isBusy: Boolean) {
        avlHeroLoader.visibility = View.VISIBLE
        rvHeroes.visibility = View.GONE
    }

    private fun onNoHeroesFound(message: String) {
        avlHeroLoader.visibility = View.GONE
    }

    private fun setHeroesList() {
        val superheroesAdapter = SuperheroesAdapter(this, R.layout.hero_layout, dashboardViewModel.superheroes.value)
        rvHeroes.adapter = superheroesAdapter

        txtSearch.onTextUpdatedCallBackFunction = {
            hideKeyboard(txtSearch)
            dashboardViewModel.searchForHero(it)
        }
    }

    private fun onHeroesFound(superheroes: List<SuperHero?>?) {
        rvHeroes.visibility = View.VISIBLE
        rvHeroes.adapter?.notifyDataSetChanged()
    }

}