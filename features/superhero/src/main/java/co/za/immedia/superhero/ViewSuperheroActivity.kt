package co.za.immedia.superhero

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import co.za.immedia.commons.models.Appearance
import co.za.immedia.commons.models.Connections
import co.za.immedia.commons.models.Superhero
import co.za.immedia.commons.models.Work
import co.za.immedia.libraries.glide.loadImageFromInternet
import co.za.immedia.superhero.databinding.ActivityViewSuperheroBinding
import co.za.immedia.superheroapp.features.base.activities.BaseChildActivity
import com.google.android.material.appbar.AppBarLayout
import com.wang.avi.AVLoadingIndicatorView
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
        setSupportActionBar(toolbar)
    }

    private fun addObservers() {
        viewSuperheroViewModel.isAddToFav.observe(this, Observer { onHeroAddedToFavourites(it)})
        viewSuperheroViewModel.appearance.observe(this, Observer { onHeroAppearanceSet(it)})
        viewSuperheroViewModel.appearanceErrorMessage.observe(this, Observer { onHeroAppearanceError(it)})
    }

    private fun onHeroAppearanceSet(appearance: Appearance){
        flAppearanceLoaderContainer.visibility = View.GONE
    }

    private fun onHeroAppearanceError(errorMessage: String){
        avlAppearaceLoader.visibility = View.GONE
    }

    private fun onHeroAddedToFavourites(isAddToFav: Boolean) {
        Toast.makeText(this, getString(R.string.added_to_fav),  Toast.LENGTH_SHORT).show()
    }

    fun onViewMoreClicked(view: View){
        view.visibility = View.GONE
        cvAppearance.visibility = View.VISIBLE
        viewSuperheroViewModel.showHeroAppearance()
       nsvContentScroll.fullScroll(View.FOCUS_UP)
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