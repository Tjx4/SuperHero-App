package co.za.immedia.superhero

import co.za.immedia.superheroapp.database.SuperheroDB
import co.za.immedia.commons.extensions.toSuperheroesTable
import co.za.immedia.mylib.RetrofitHelper
import co.za.immedia.models.DbOperation
import co.za.immedia.models.Superhero

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