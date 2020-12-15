package co.za.immedia.herorating

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import co.za.immedia.herorating.databinding.ActivityRatingBinding

class RatingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRatingBinding
    lateinit var ratingViewModel: RatingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var application = requireNotNull(this).application
        var viewModelFactory = RatingViewModelFactory(application)

        ratingViewModel = ViewModelProviders.of(this, viewModelFactory).get(RatingViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_rating)
        binding.ratingViewModel = ratingViewModel
        binding.lifecycleOwner = this

    }
}