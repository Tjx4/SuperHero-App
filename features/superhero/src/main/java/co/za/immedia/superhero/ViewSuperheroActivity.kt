package co.za.immedia.superhero

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import co.za.immedia.commons.models.Superhero
import co.za.immedia.libraries.glide.loadImageFromInternet
import co.za.immedia.superhero.databinding.ActivityViewSuperheroBinding
import co.za.immedia.superheroapp.features.base.activities.BaseChildActivity
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

        viewSuperheroViewModel = ViewModelProviders.of(this, viewModelFactory).get(
            ViewSuperheroViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_view_superhero)
        binding.viewSuperheroViewModel = viewSuperheroViewModel
        binding.lifecycleOwner = this

        addObservers()

        val superhero = intent.extras?.getBundle(co.za.immedia.commons.constants.PAYLOAD_KEY)?.getParcelable<Superhero>(
            co.za.immedia.commons.constants.SUPERHERO
        )
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

    fun onViewMoreClicked(view: View){

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

        viewSuperheroViewModel.superhero.value?.isFav?.let {
            addFavourite?.isVisible = !it
        }

        return super.onCreateOptionsMenu(menu)
    }
}