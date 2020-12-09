package co.za.immedia.superheroapp.features.favourites

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.za.immedia.superheroapp.R
import co.za.immedia.superheroapp.adapters.FavouriteHeroesAdapter
import co.za.immedia.superheroapp.features.base.fragments.BaseDialogFragment
import co.za.immedia.superheroapp.features.dashboard.DashboardActivity
import co.za.immedia.models.Superhero
import com.wang.avi.AVLoadingIndicatorView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class FavouritesFragment : BaseDialogFragment(), FavouriteHeroesAdapter.HeroClickListener {
    private var dashboardActivity: DashboardActivity? = null
    private var parentCl: ConstraintLayout? = null
    private var avlProgressBarLoading: AVLoadingIndicatorView? = null
    private var btnCloseUsersImg: ImageButton? = null
    lateinit var favouriteHeroesAdapter: FavouriteHeroesAdapter
    private var titleTv: TextView? = null
    private var noFavSuperheroes: TextView? = null
    private var favSuperheroesRv: RecyclerView? = null
    private var favSuperheroes: List<Superhero?>? = null
    private val job =  Job()
    private val ioScope = CoroutineScope(Dispatchers.IO + job)
    private val uiScope = CoroutineScope(Dispatchers.Main + job)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val parentView = super.onCreateView(inflater, container, savedInstanceState)
        initViews(parentView)
        return parentView
    }

    private fun initViews(parentView: View) {
        val  favouritesFragment = this
        btnCloseUsersImg = parentView.findViewById(R.id.imgBtnCloseUsers)
        btnCloseUsersImg?.setOnClickListener {
            dismiss()
        }

        parentCl = parentView.findViewById(R.id.rlParent)
        avlProgressBarLoading = parentView.findViewById(R.id.avlLoading)
        titleTv = parentView.findViewById(R.id.tvFavHeroesHeading)

        showLoading()

        ioScope.launch {
            favSuperheroes = dashboardActivity?.dashboardViewModel?.favSuperheroes?.value

            uiScope.launch {
                hideLoading()

                if(favSuperheroes.isNullOrEmpty()){
                    noFavSuperheroes = parentView.findViewById(R.id.tvNoStats)
                    noFavSuperheroes?.visibility = View.VISIBLE

                    return@launch
                }

                titleTv?.visibility = View.VISIBLE

                favouriteHeroesAdapter = FavouriteHeroesAdapter(dashboardActivity as Context, favSuperheroes)
                favouriteHeroesAdapter.setOnHeroClickListener(favouritesFragment)

                favSuperheroesRv = parentView.findViewById(R.id.rvUsers)
                favSuperheroesRv?.layoutManager = GridLayoutManager(dashboardActivity, 2)
                favSuperheroesRv?.adapter = favouriteHeroesAdapter
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        dashboardActivity = context as DashboardActivity
    }

    fun showLoading() {
        parentCl?.visibility = View.INVISIBLE
        avlProgressBarLoading?.visibility = View.VISIBLE
    }

    fun hideLoading() {
        parentCl?.visibility = View.VISIBLE
        avlProgressBarLoading?.visibility = View.INVISIBLE
    }

    override fun onSuperheroClicked(view: View, position: Int) {
        val superhero = dashboardActivity?.dashboardViewModel?.favSuperheroes?.value?.get(position)
        dashboardActivity?.viewSuperhero(superhero)
        dismiss()
    }

    companion object {
        fun newInstance():  FavouritesFragment {
            val  favouritesFragment = FavouritesFragment()
            favouritesFragment.arguments = Bundle()
            return  favouritesFragment
        }
    }
}