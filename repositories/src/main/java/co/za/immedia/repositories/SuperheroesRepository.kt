package co.za.immedia.repositories

import co.za.immedia.commons.models.Appearance
import co.za.immedia.commons.models.SearchResult
import co.za.immedia.networking.RetrofitHelper

class SuperheroesRepository(private val retrofit: RetrofitHelper) {

    suspend fun searchForSuperHero(url: String): SearchResult?{
        return try {
            retrofit.searchSuperHero(url)
        }
        catch (ex: Exception){
            null
        }
    }

    suspend fun fetchHeroAppearance(url: String): Appearance?{
        return try {
            retrofit.getHeroAppearance(url)
        }
        catch (ex: Exception){
            null
        }
    }

}