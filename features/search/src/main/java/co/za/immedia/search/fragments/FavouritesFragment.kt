package co.za.immedia.search.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import co.za.immedia.superheroapp.features.base.fragments.BaseDialogFragment
import co.za.immedia.commons.models.Superhero
import co.za.immedia.favourites.adapters.FavouriteHeroesAdapter
import co.za.immedia.search.SearchActivity
import kotlinx.android.synthetic.main.fragment_favourites.*
import kotlinx.coroutines.*

class FavouritesFragment : BaseDialogFragment(), FavouriteHeroesAdapter.HeroClickListener {
    private var searchActivity: SearchActivity? = null
    private lateinit var favouriteHeroesAdapter: FavouriteHeroesAdapter
    private var favSuperheroes: List<Superhero?>? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val  favouritesFragment = this
        imgBtnCloseUsers?.setOnClickListener {
            dismiss()
        }

        showLoading()

       lifecycleScope.launch(Dispatchers.IO)  {
            favSuperheroes = searchActivity?.searchViewModel?.favSuperheroes?.value

            withContext(Dispatchers.Main)  {
                hideLoading()

                when {
                    favSuperheroes.isNullOrEmpty() -> {
                        tvNoStats?.visibility = View.VISIBLE
                    }
                    else -> {
                        tvFavHeroesHeading?.visibility = View.VISIBLE
                        favouriteHeroesAdapter = FavouriteHeroesAdapter(searchActivity as Context, favSuperheroes)
                        favouriteHeroesAdapter.setOnHeroClickListener(favouritesFragment)
                        rvUsers?.layoutManager = GridLayoutManager(searchActivity, 2)
                        rvUsers?.adapter = favouriteHeroesAdapter
                    }
                }
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        searchActivity = context as SearchActivity
    }

    private fun showLoading() {
        rlParent?.visibility = View.INVISIBLE
        avlLoading?.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        rlParent?.visibility = View.VISIBLE
        avlLoading?.visibility = View.INVISIBLE
    }

    override fun onSuperheroClicked(view: View, position: Int) {
       val superhero = searchActivity?.searchViewModel?.favSuperheroes?.value?.get(position)
        searchActivity?.viewSuperhero(superhero)
        dismiss()
    }

    companion object {
        fun newInstance(): FavouritesFragment {
            val  favouritesFragment = FavouritesFragment()
            favouritesFragment.arguments = Bundle()
            return  favouritesFragment
        }
    }
}