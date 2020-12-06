package co.za.immedia.superheroapp.features.dashboard

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import co.za.immedia.superheroapp.R
import co.za.immedia.superheroapp.adapters.SuperheroesAdapter
import co.za.immedia.superheroapp.constants.SUPERHERO
import co.za.immedia.superheroapp.databinding.ActivityDashboardBinding
import co.za.immedia.superheroapp.extensions.FADE_IN_ACTIVITY
import co.za.immedia.superheroapp.extensions.navigateToActivity
import co.za.immedia.superheroapp.features.base.activities.BaseActivity
import co.za.immedia.superheroapp.models.Superhero
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity : BaseActivity(), SuperheroesAdapter.HeroClickListener {
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

        init()
    }

    private fun addObservers() {
        dashboardViewModel.showLoading.observe(this, Observer { onShowLoading(it) })
        dashboardViewModel.noHeroesMessage.observe(this, Observer { onNoHeroesFound(it) })
        dashboardViewModel.superheroes.observe(this, Observer { onHeroesFound(it) })
    }

    private fun onShowLoading(isBusy: Boolean) {
        avlHeroLoader.visibility = View.VISIBLE
        rvHeroes.visibility = View.GONE
        tvNoMessage.visibility = View.GONE
    }

    private fun onNoHeroesFound(message: String) {
        avlHeroLoader.visibility = View.GONE
        tvNoMessage.visibility = View.VISIBLE
        rvHeroes.visibility = View.GONE
    }

    private fun init() {
/*
        val searchTypeLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        //searchTypeLayoutManager.initialPrefetchItemCount = dashboardViewModel.superheroes.value?.size ?: 0
        rvHeroes?.layoutManager = searchTypeLayoutManager
        val superheroesAdapter = SuperheroesAdapter(this, R.layout.hero_layout, dashboardViewModel.superheroes.value)
        superheroesAdapter.setOnHeroClickListener(this)
        rvHeroes.adapter = superheroesAdapter
*/
        txtSearch.onTextUpdatedCallBackFunction = {
            dashboardViewModel.searchForHero(it)
        }
    }

    private fun onHeroesFound(superheroes: List<Superhero?>?) {
        avlHeroLoader.visibility = View.GONE
        tvNoMessage.visibility = View.GONE
        rvHeroes.visibility = View.VISIBLE

        //hideKeyboard(txtSearch)

        //rvHeroes.adapter?.notifyDataSetChanged()
        val searchTypeLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        searchTypeLayoutManager.initialPrefetchItemCount = superheroes?.size ?: 0
        rvHeroes?.layoutManager = searchTypeLayoutManager
        val superheroesAdapter = SuperheroesAdapter(this, R.layout.hero_layout, superheroes)
        superheroesAdapter.setOnHeroClickListener(this)
        rvHeroes.adapter = superheroesAdapter
    }

    override fun onHostClicked(view: View, position: Int) {
        val superhero = dashboardViewModel.superheroes.value?.get(position)
        val payload = Bundle()
        payload.putParcelable(SUPERHERO, superhero)
        navigateToActivity(DashboardActivity::class.java, payload, FADE_IN_ACTIVITY)
    }

}