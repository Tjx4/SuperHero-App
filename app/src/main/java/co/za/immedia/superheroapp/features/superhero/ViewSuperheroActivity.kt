package co.za.immedia.superheroapp.features.superhero

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import co.za.immedia.superheroapp.R
import co.za.immedia.superheroapp.constants.PAYLOAD_KEY
import co.za.immedia.superheroapp.constants.SUPERHERO
import co.za.immedia.superheroapp.databinding.ActivityViewSuperheroBinding
import co.za.immedia.superheroapp.features.base.activities.BaseChildActivity
import co.za.immedia.superheroapp.helpers.loadImageFromInternet
import co.za.immedia.superheroapp.models.Superhero
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.activity_view_superhero.*

class ViewSuperheroActivity : BaseChildActivity() {
    private lateinit var binding: ActivityViewSuperheroBinding
    private lateinit var viewSuperheroViewModel: ViewSuperheroViewModel
    var addFavourite: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var application = requireNotNull(this).application
        var viewModelFactory = ViewSuperheroViewModelFactory(application)

        viewSuperheroViewModel = ViewModelProviders.of(this, viewModelFactory).get(ViewSuperheroViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_view_superhero)
        binding.viewSuperheroViewModel = viewSuperheroViewModel
        binding.lifecycleOwner = this

        addObservers()

        val superhero = intent.extras?.getBundle(PAYLOAD_KEY)?.getParcelable<Superhero>(SUPERHERO)
        viewSuperheroViewModel.superhero.value = superhero

        var ab = supportActionBar
        ab?.title = superhero?.name

        superhero?.image?.url?.let {
            loadImageFromInternet(this, it, imgSuperheroPic, R.drawable.ic_place_holde_dark)
        }

        app_bar_layout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->

            if ((collapsing_toolbar!!.height + verticalOffset) < (2 * ViewCompat.getMinimumHeight(collapsing_toolbar))) {
                toolbar?.setNavigationIcon(R.drawable.ic_action_back_dark)
                addFavourite?.setIcon(R.drawable.ic_favourites_dark)
            } else {
                toolbar?.setNavigationIcon(R.drawable.ic_action_back_light)
                addFavourite?.setIcon(R.drawable.ic_favourites_light)
            }

        })

        toolbar?.setNavigationOnClickListener { onBackPressed() }
        //toolbar?.inflateMenu(R.menu.view_superhero_menu)
        setSupportActionBar(toolbar)
    }

    private fun addObservers() {
        viewSuperheroViewModel.superhero.observe(this, Observer { onSuperheroSet(it) })
        viewSuperheroViewModel.isAddToFav.observe(this, Observer { onHeroAddedToFavourites(it) })
    }

    fun onSuperheroSet(superhero: Superhero){

    }

    private fun onHeroAddedToFavourites(isAddToFav: Boolean) {
        Toast.makeText(this, "Added to favourites",  Toast.LENGTH_SHORT).show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_fav ->  {
                viewSuperheroViewModel.addSuperheroToFavourites()
                addFavourite?.isVisible = false
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.view_superhero_menu, menu)
        addFavourite = menu.findItem(R.id.action_fav)
        return super.onCreateOptionsMenu(menu)
    }
}