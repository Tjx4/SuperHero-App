package co.za.immedia.superheroapp.features.dashboard

import android.os.Bundle
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import co.za.immedia.superheroapp.R
import co.za.immedia.superheroapp.adapters.SuperheroesAdapter
import co.za.immedia.superheroapp.constants.SUPERHERO
import co.za.immedia.superheroapp.databinding.ActivityDashboardBinding
import co.za.immedia.superheroapp.extensions.FADE_IN_ACTIVITY
import co.za.immedia.superheroapp.extensions.SLIDE_IN_ACTIVITY
import co.za.immedia.superheroapp.extensions.navigateToActivity
import co.za.immedia.superheroapp.features.base.activities.BaseActivity
import co.za.immedia.superheroapp.features.superhero.ViewSuperheroActivity
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

        init()

        supportActionBar?.elevation = 0f
        supportActionBar?.title = " Heroes"
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setIcon(R.drawable.ic_superheroes_light)
    }

    private fun addObservers() {
        dashboardViewModel.showLoading.observe(this, Observer { onShowLoading(it) })
        dashboardViewModel.noHeroesMessage.observe(this, Observer { onNoHeroesFound(it) })
        dashboardViewModel.superheroes.observe(this, Observer { onHeroesFound(it) })
        dashboardViewModel.isHeroAdded.observe(this, Observer { onHeroAddedToFavourites(it) })
    }

    private fun onShowLoading(isBusy: Boolean) {
        avlHeroLoader.visibility = View.VISIBLE
        rvHeroes.visibility = View.GONE
        tvNoMessage.visibility = View.GONE
    }

    private fun onHeroAddedToFavourites(isBusy: Boolean) {
        Toast.makeText(this, "onHeroAddedToFavourites",  Toast.LENGTH_SHORT).show()
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
        navigateToActivity(ViewSuperheroActivity::class.java, payload, SLIDE_IN_ACTIVITY)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.dashboard_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_favourites -> {

            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        moveTaskToBack(true)
        return super.onKeyDown(keyCode, event)
    }

}