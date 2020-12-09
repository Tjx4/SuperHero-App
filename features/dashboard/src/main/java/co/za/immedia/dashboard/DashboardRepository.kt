package co.za.immedia.dashboard

import co.za.immedia.commons.extensions.toSuperhero
import co.za.immedia.commons.extensions.toSuperheroesTable
import co.za.immedia.commons.models.SearchResult
import co.za.immedia.mylib.RetrofitHelper
import co.za.immedia.commons.models.DbOperation
import co.za.immedia.commons.models.Superhero
import co.za.immedia.persistence.room.SuperheroDB

class DashboardRepository(private val retrofit: RetrofitHelper, private val database: SuperheroDB) {
    suspend fun searchForSuperHero(url: String): SearchResult?{
        return try {
            retrofit.searchSuperHero(url)
        }
        catch (ex: Exception){
            null
        }
    }

    suspend fun addSuperheroToFavDB(superhero: Superhero): DbOperation {
        return try {
            database.superheroesDAO.insert(superhero.toSuperheroesTable())
            DbOperation(true)
        }
        catch (ex: Exception){
            DbOperation(false)
        }
    }

    suspend fun getFavHeroesFromDB(): List<Superhero?>? {
        return try {
            val favHeroes = ArrayList<Superhero?>()
            database.superheroesDAO.getAllHeroes()?.forEach { superheroTable ->
                favHeroes.add(superheroTable.toSuperhero())
            }

            favHeroes
        }
        catch (ex: Exception){
            null
        }
    }
}