package co.za.immedia.superheroapp.features.superhero

import co.za.immedia.superheroapp.database.SuperheroDB
import co.za.immedia.superheroapp.extensions.toSuperheroesTable
import co.za.immedia.superheroapp.helpers.RetrofitHelper
import co.za.immedia.superheroapp.models.DbOperation
import co.za.immedia.superheroapp.models.Superhero

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