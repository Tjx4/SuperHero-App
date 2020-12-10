package co.za.immedia.search

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
import co.za.immedia.commons.base.activities.BaseActivity
import co.za.immedia.commons.constants.SUPERHERO
import co.za.immedia.commons.extensions.SLIDE_IN_ACTIVITY
import co.za.immedia.commons.extensions.navigateToActivity
import co.za.immedia.commons.models.Superhero
import co.za.immedia.search.adapter.SuperheroesAdapter
import co.za.immedia.search.databinding.ActivitySearchBinding
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : BaseActivity(), SuperheroesAdapter.HeroClickListener {
    private lateinit var binding: ActivitySearchBinding
    lateinit var dashboardViewModel: DashboardViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var application = requireNotNull(this).application
        var viewModelFactory = co.za.immedia.search.DashboardViewModelFactory(application)

        dashboardViewModel = ViewModelProviders.of(this, viewModelFactory).get(co.za.immedia.search.DashboardViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search)
        binding.dashboardViewModel = dashboardViewModel
        binding.lifecycleOwner = this

        addObservers()

        init()

        supportActionBar?.elevation = 0f
        supportActionBar?.title = " Superheroes"
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setIcon(R.drawable.ic_superheroes_light)
    }

    override fun onResume() {
        super.onResume()
        dashboardViewModel.setFavSuperheroes()
    }

    private fun addObservers() {
        dashboardViewModel.showLoading.observe(this, Observer { onShowLoading(it) })
        dashboardViewModel.noHeroesMessage.observe(this, Observer { onNoHeroesFound(it) })
        dashboardViewModel.superheroes.observe(this, Observer { onHeroesFound(it) })
        dashboardViewModel.favSuperheroes.observe(this, Observer { onHeroesUpdated(it) })
        dashboardViewModel.newFavHero.observe(this, Observer { onHeroAddedToFavourites(it) })
    }

    private fun onShowLoading(isBusy: Boolean) {
        avlHeroLoader.visibility = View.VISIBLE
        rvHeroes.visibility = View.GONE
        tvNoMessage.visibility = View.GONE
    }

    private fun onHeroAddedToFavourites(superhero: Superhero) {
        Toast.makeText(this, "${superhero.name} added to favourites",  Toast.LENGTH_SHORT).show()
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
        val superheroesAdapter =
            co.za.immedia.search.adapter.SuperheroesAdapter(this, R.layout.hero_layout, superheroes)
        superheroesAdapter.setOnHeroClickListener(this)
        rvHeroes.adapter = superheroesAdapter
    }

    private fun onHeroesUpdated(superheroes: List<Superhero?>?) {
        rvHeroes.adapter?.notifyDataSetChanged()
    }

    override fun onSuperheroClicked(view: View, position: Int) {
        val superhero = dashboardViewModel.superheroes.value?.get(position)
        viewSuperhero(superhero)
    }

    fun viewSuperhero(superhero: Superhero?) {
        val payload = Bundle()
        payload.putParcelable(SUPERHERO, superhero)
        navigateToActivity("co.za.immedia.superhero.ViewSuperheroActivity", payload, SLIDE_IN_ACTIVITY)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.dashboard_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_favourites -> {
                //val favouritesFragment = FavouritesFragment.newInstance()
                // favouritesFragment?.isCancelable = true
                //showDialogFragment("Superheroes", co.za.immedia.favourites.R.layout.fragment_favourites, favouritesFragment)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true)
        }
        return super.onKeyDown(keyCode, event)
    }

}