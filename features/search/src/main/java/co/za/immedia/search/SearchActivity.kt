package co.za.immedia.search

import android.os.Bundle
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import co.za.immedia.commons.base.activities.BaseActivity
import co.za.immedia.commons.constants.SUPERHERO
import co.za.immedia.commons.constants.VIEW_SUPRERHERO_ACTIVITY
import co.za.immedia.commons.extensions.SLIDE_IN_ACTIVITY
import co.za.immedia.commons.extensions.navigateToActivity
import co.za.immedia.commons.extensions.showDialogFragment
import co.za.immedia.commons.models.Superhero
import co.za.immedia.search.adapter.SuperheroesAdapter
import co.za.immedia.search.databinding.ActivitySearchBinding
import co.za.immedia.search.fragments.FavouritesFragment
import kotlinx.android.synthetic.main.activity_search.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchActivity : BaseActivity(), SuperheroesAdapter.HeroClickListener {
    private lateinit var binding: ActivitySearchBinding
    val searchViewModel: SearchViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search)
        binding.searchViewModel = searchViewModel
        binding.lifecycleOwner = this

        addObservers()
        init()
        supportActionBar?.elevation = 0f
        supportActionBar?.title = getString(R.string.search_title)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setIcon(R.drawable.ic_superheroes_light)
    }

    override fun onResume() {
        super.onResume()
        searchViewModel.setFavSuperheroes()
    }

    private fun addObservers() {
        searchViewModel.showLoading.observe(this, Observer { onShowLoading(it) })
        searchViewModel.noHeroesMessage.observe(this, Observer { onNoHeroesFound(it) })
        searchViewModel.superheroes.observe(this, Observer { onHeroesFound(it) })
        searchViewModel.favSuperheroes.observe(this, Observer { onHeroesUpdated(it) })
        searchViewModel.newFavHero.observe(this, Observer { onHeroAddedToFavourites(it) })
    }

    private fun onShowLoading(isBusy: Boolean) {
        avlHeroLoader.visibility = View.VISIBLE
        rvHeroes.visibility = View.GONE
        tvNoMessage.visibility = View.GONE
    }

    private fun onHeroAddedToFavourites(superhero: Superhero) {
        Toast.makeText(this, "${superhero.name} added to favourites", Toast.LENGTH_SHORT).show()
    }

    private fun onNoHeroesFound(message: String) {
        avlHeroLoader.visibility = View.GONE
        tvNoMessage.visibility = View.VISIBLE
        rvHeroes.visibility = View.GONE
    }

    private fun init() {
        txtSearch.onTextUpdatedCallBackFunction = {
            searchViewModel.searchForHero(it)
        }
    }

    private fun onHeroesFound(superheroes: List<Superhero?>?) {
        avlHeroLoader.visibility = View.GONE
        tvNoMessage.visibility = View.GONE
        rvHeroes.visibility = View.VISIBLE

        val searchTypeLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        searchTypeLayoutManager.initialPrefetchItemCount = superheroes?.size ?: 0
        rvHeroes?.layoutManager = searchTypeLayoutManager
        val superheroesAdapter = SuperheroesAdapter(this, R.layout.hero_layout, superheroes)
        superheroesAdapter.setOnHeroClickListener(this)
        rvHeroes.adapter = superheroesAdapter
    }

    private fun onHeroesUpdated(superheroes: List<Superhero?>?) {
        rvHeroes.adapter?.notifyDataSetChanged()
    }

    override fun onSuperheroClicked(view: View, position: Int) {
        val superhero = searchViewModel.superheroes.value?.get(position)
        viewSuperhero(superhero)
    }

    fun viewSuperhero(superhero: Superhero?) {
        val payload = Bundle()
        payload.putParcelable(SUPERHERO, superhero)
        navigateToActivity(VIEW_SUPRERHERO_ACTIVITY, payload, SLIDE_IN_ACTIVITY)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.dashboard_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_favourites -> {
                val favouritesFragment = FavouritesFragment.newInstance()
                favouritesFragment?.isCancelable = true
                showDialogFragment(
                    getString(R.string.superheroes),
                    R.layout.fragment_favourites,
                    favouritesFragment
                )
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