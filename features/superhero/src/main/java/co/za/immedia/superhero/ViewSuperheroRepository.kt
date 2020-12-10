package co.za.immedia.superhero

import co.za.immedia.commons.extensions.toSuperheroesTable
import co.za.immedia.networking.RetrofitHelper
import co.za.immedia.commons.models.DbOperation
import co.za.immedia.commons.models.Superhero
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

}