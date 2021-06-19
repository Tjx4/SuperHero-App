package co.za.immedia.superhero

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import co.za.immedia.commons.constants.RATE_SUPERHERO
import co.za.immedia.commons.constants.SUPERHERO
import co.za.immedia.commons.extensions.SLIDE_IN_ACTIVITY
import co.za.immedia.commons.extensions.navigateToActivity
import co.za.immedia.commons.helpers.showConfirmAlert
import co.za.immedia.commons.models.Appearance
import co.za.immedia.commons.models.Superhero
import co.za.immedia.libraries.glide.loadImageFromInternet
import co.za.immedia.superhero.databinding.ActivityViewSuperheroBinding
import co.za.immedia.commons.base.activities.BaseChildActivity
import com.google.android.material.appbar.AppBarLayout
import com.google.android.play.core.splitinstall.*
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus
import kotlinx.android.synthetic.main.activity_view_superhero.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ViewSuperheroActivity : BaseChildActivity() {
    private lateinit var binding: ActivityViewSuperheroBinding
    private val viewSuperheroViewModel: ViewSuperheroViewModel by viewModel()
    private var addFavourite: MenuItem? = null
    private var rateHero: MenuItem? = null
    private var mySessionId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_view_superhero)
        binding.viewSuperheroViewModel = viewSuperheroViewModel
        binding.lifecycleOwner = this

        addObservers()

        val superhero = intent.extras?.getBundle(co.za.immedia.commons.constants.PAYLOAD_KEY)?.getParcelable<Superhero>(
            SUPERHERO
        )
        viewSuperheroViewModel.superhero.value = superhero

        supportActionBar?.title = superhero?.name

        superhero?.image?.url?.let {
            loadImageFromInternet(this, it, imgSuperheroPic, R.drawable.ic_place_holde_dark)
        }

        app_bar_layout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->

            if ((collapsing_toolbar!!.height + verticalOffset) < (2 * ViewCompat.getMinimumHeight(
                    collapsing_toolbar
                ))
            ) {
                toolbar?.setNavigationIcon(R.drawable.ic_action_back_dark)
                addFavourite?.setIcon(R.drawable.ic_favourites_dark)
                rateHero?.setIcon(R.drawable.ic_rate_dark)
            } else {
                toolbar?.setNavigationIcon(R.drawable.ic_action_back_light)
                addFavourite?.setIcon(R.drawable.ic_favourites_light)
                rateHero?.setIcon(R.drawable.ic_rate_light)
            }

        })

        toolbar?.setNavigationOnClickListener { onBackPressed() }
        setSupportActionBar(toolbar)
    }

    private fun addObservers() {
        viewSuperheroViewModel.isAddToFav.observe(this, Observer { onHeroAddedToFavourites(it) })
        viewSuperheroViewModel.appearance.observe(this, Observer { onHeroAppearanceSet(it) })
        viewSuperheroViewModel.appearanceErrorMessage.observe(this, Observer {
            onHeroAppearanceError(
                it
            )
        })
    }

    private fun onHeroAppearanceSet(appearance: Appearance){
        flAppearanceLoaderContainer.visibility = View.GONE
    }

    private fun onHeroAppearanceError(errorMessage: String){
        avlAppearaceLoader.visibility = View.GONE
    }

    private fun onHeroAddedToFavourites(isAddToFav: Boolean) {
        Toast.makeText(this, getString(R.string.added_to_fav), Toast.LENGTH_SHORT).show()
    }

    fun onViewMoreClicked(view: View){
        view.visibility = View.GONE
        cvAppearance.visibility = View.VISIBLE
        viewSuperheroViewModel.showHeroAppearance()
       nsvContentScroll.fullScroll(View.FOCUS_UP)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_fav -> {
                viewSuperheroViewModel.addSuperheroToFavourites()
                addFavourite?.isVisible = false
                rateHero?.isVisible = true
            }
            R.id.action_rating -> {

                try {
                    navigateToDynamicFeature()

                } catch (ex: Exception) {
                    showDynamicFeatureAlert()
                }
            }
        }

        return super.onOptionsItemSelected(item)
    }

    fun showDynamicFeatureAlert() {

        showConfirmAlert(this,
            "Dynamic feature",
            "This feature is currently unavailable, would you like to download it?",
            "Download",
            "",
            "Cancel",
            {
                downloadDynamicModule()
            }, {}, {

            })
    }

    private fun navigateToDynamicFeature() {
        val payload = Bundle()
        payload.putParcelable(SUPERHERO, viewSuperheroViewModel.superhero.value)
        navigateToActivity(RATE_SUPERHERO, payload, SLIDE_IN_ACTIVITY)
    }

    private fun downloadDynamicModule() {
        val splitInstallManager: SplitInstallManager = SplitInstallManagerFactory.create(this)
        val request: SplitInstallRequest = SplitInstallRequest
            .newBuilder()
            .addModule("heroRating")
            .build()
        val listener: SplitInstallStateUpdatedListener = SplitInstallStateUpdatedListener { splitInstallSessionState ->

            if (splitInstallSessionState.sessionId() == mySessionId) {

                    when (splitInstallSessionState.status()) {
                        SplitInstallSessionStatus.INSTALLED -> {

                            Log.d("TAG", "Dynamic Module downloaded")
                            Toast.makeText(
                                this@ViewSuperheroActivity,
                                "Dynamic Module downloaded",
                                Toast.LENGTH_SHORT
                            ).show()

                            navigateToDynamicFeature()
                        }
                    }
                }
            }

        splitInstallManager.registerListener(listener)
        splitInstallManager.startInstall(request)
            .addOnFailureListener { e ->
                Toast.makeText(
                    this@ViewSuperheroActivity,
                    "Error downloading feature",
                    Toast.LENGTH_SHORT
                ).show()
            }
            .addOnSuccessListener {
                fun onSuccess(sessionId: Int) {
                    mySessionId = sessionId
                }
            }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.view_superhero_menu, menu)
        addFavourite = menu.findItem(R.id.action_fav)
        rateHero = menu.findItem(R.id.action_rating)

        viewSuperheroViewModel.superhero.value?.isFav?.let {
            addFavourite?.isVisible = !it
            rateHero?.isVisible = it
        }

        return super.onCreateOptionsMenu(menu)
    }
}