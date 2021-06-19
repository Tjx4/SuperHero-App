package co.za.immedia.herorating

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import co.za.immedia.commons.models.Superhero
import co.za.immedia.herorating.databinding.ActivityRatingBinding
import co.za.immedia.libraries.glide.loadImageFromInternet
import co.za.immedia.commons.base.activities.BaseChildActivity
import kotlinx.android.synthetic.main.activity_rating.*

class RatingActivity : BaseChildActivity()  {
    private lateinit var binding: ActivityRatingBinding
    private val ratingViewModel: RatingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_rating)
        binding.ratingViewModel = ratingViewModel
        binding.lifecycleOwner = this

        addObservers()

        val superhero = intent.extras?.getBundle(co.za.immedia.commons.constants.PAYLOAD_KEY)?.getParcelable<Superhero>(co.za.immedia.commons.constants.SUPERHERO)
        ratingViewModel.superhero.value = superhero

        supportActionBar?.title = superhero?.name
        supportActionBar?.setHomeAsUpIndicator(co.za.immedia.commons.R.drawable.ic_action_back_light)

        superhero?.image?.url?.let {
            loadImageFromInternet(
                this,
                it,
                imgvHero,
                co.za.immedia.commons.R.drawable.ic_place_holde_dark
            )
        }
    }

    private fun addObservers() {
        ratingViewModel.superhero.observe(this, Observer { onSuperheroSet(it)})
    }

    fun onSuperheroSet(superhero: Superhero){
        rbRating.rating = superhero.rating
        rbRating.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            ratingViewModel.updateHeroRating(rating)
        }
    }
}