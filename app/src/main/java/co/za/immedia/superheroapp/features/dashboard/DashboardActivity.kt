package co.za.immedia.superheroapp.features.dashboard

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import co.za.immedia.superheroapp.R
import co.za.immedia.superheroapp.databinding.ActivityDashboardBinding
import co.za.immedia.superheroapp.features.base.activities.BaseActivity

class DashboardActivity : BaseActivity() {
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
        supportActionBar?.title = "Heros"
    }

    private fun addObservers() {
        // dashboardViewModel.showLoading.observe(this, Observer { toggleShow(it) })
    }
}