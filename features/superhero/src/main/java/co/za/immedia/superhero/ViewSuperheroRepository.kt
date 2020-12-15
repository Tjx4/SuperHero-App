package co.za.immedia.superhero

import co.za.immedia.commons.extensions.toSuperheroesTable
import co.za.immedia.commons.models.*
import co.za.immedia.networking.RetrofitHelper
import co.za.immedia.persistence.room.SuperheroDB

class ViewSuperheroRepository(private val retrofit: RetrofitHelper, private val database: SuperheroDB) {

    suspend fun addSuperheroToFavDB(superhero: Superhero): DbOperation {
        return try {
            database.superheroesDAO.insert(superhero.toSuperheroesTable())
            DbOperation(true)
        }
        catch (ex: Exception){
            DbOperation(false)
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

    suspend fun fetchHeroWork(url: String): Work?{
        return try {
            retrofit.getHeroWork(url)
        }
        catch (ex: Exception){
            null
        }
    }

    suspend fun fetchHeroConnections(url: String): Connections?{
        return try {
            retrofit.getHeroConnections(url)
        }
        catch (ex: Exception){
            null
        }
    }
}