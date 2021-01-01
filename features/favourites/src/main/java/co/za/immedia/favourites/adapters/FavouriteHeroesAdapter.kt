package co.za.immedia.favourites.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import co.za.immedia.commons.base.activities.BaseActivity
import co.za.immedia.favourites.R
import co.za.immedia.commons.models.Superhero
import co.za.immedia.libraries.glide.loadImageFromInternet

class FavouriteHeroesAdapter(context: Context, private val superheroes: List<Superhero?>?) : RecyclerView.Adapter<FavouriteHeroesAdapter.ViewHolder>() {
    private val activity = context as BaseActivity
    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)
    private var heroClickListener: HeroClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = layoutInflater.inflate(R.layout.fav_hero_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val superHero = superheroes?.get(position)
        holder.heroNameTv.text = superHero?.name

        superHero?.image?.url?.let {
            loadImageFromInternet(activity,  it, holder.heroImgv, R.drawable.ic_place_holde_dark)
        }

        holder.ratingRb.rating = superHero?.rating ?: 0f

        superHero?.isFav = true
    }

    fun showFav(addItem: View, favImg: View){
        addItem.visibility = View.INVISIBLE
        favImg.visibility = View.VISIBLE
    }

    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        internal var heroNameTv = itemView.findViewById<TextView>(R.id.tvFavHeroName)
        internal var heroImgv = itemView.findViewById<ImageView>(R.id.imgvFavHero)
        internal var ratingRb = itemView.findViewById<RatingBar>(R.id.rbRating)

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
