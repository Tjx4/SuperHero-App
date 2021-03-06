package co.za.immedia.search.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import co.za.immedia.commons.models.Superhero
import co.za.immedia.libraries.glide.loadImageFromInternet
import co.za.immedia.search.R
import co.za.immedia.search.SearchActivity

class SuperheroesAdapter(context: Context, private val layout: Int, private val superheroes: List<Superhero?>?) : RecyclerView.Adapter<SuperheroesAdapter.ViewHolder>() {
    private val searchActivity = context as SearchActivity
    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)
    private var heroClickListener: HeroClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = layoutInflater.inflate(layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val superHero = superheroes?.get(position)
        holder.heroNameTv.text = superHero?.name

        superHero?.image?.url?.let {
          loadImageFromInternet(searchActivity,  it, holder.heroImgv, R.drawable.ic_place_holde_dark)
        }

        val isFavourite = searchActivity.searchViewModel?.favSuperheroes?.value?.any { currentHero ->
            currentHero?.id == superHero?.id && currentHero?.name == superHero?.name
        } ?: false

        superHero?.isFav = isFavourite

        if(isFavourite) {
            setFav(holder.setFavImg, holder.favouriteImg)
            searchActivity.searchViewModel.setFavHeroRating(superheroes?.get(position))
        }
        else {
            setDef(holder.setFavImg, holder.favouriteImg)

            holder.setFavImg.setOnClickListener {
                superHero?.let { hero ->
                    setFav(it, holder.favouriteImg)
                    searchActivity.searchViewModel.addSuperheroToFavourites(hero)
                }
            }
        }
    }

    private fun setFav(setFavImg: View, favImg: View){
        setFavImg.visibility = View.GONE
        favImg.visibility = View.VISIBLE
    }

    private fun setDef(setFavImg: View, favImg: View){
        setFavImg.visibility = View.VISIBLE
        favImg.visibility = View.GONE
    }

    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        internal var heroNameTv = itemView.findViewById<TextView>(R.id.tvHeroName)
        internal var heroImgv = itemView.findViewById<ImageView>(R.id.imgvHero)
        internal var setFavImg = itemView.findViewById<View>(R.id.imgSetFav)
        internal var favouriteImg = itemView.findViewById<View>(R.id.imgFavourite)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            heroClickListener?.onSuperheroClicked(view, adapterPosition)
        }
    }

    internal fun getItem(id: Int): Superhero? {
        return superheroes?.get(id)
    }

    interface HeroClickListener {
        fun onSuperheroClicked(view: View, position: Int)
    }

    fun setOnHeroClickListener(heroClickListener: HeroClickListener) {
        this.heroClickListener = heroClickListener
    }

    override fun getItemCount() = superheroes?.size ?: 0

}
