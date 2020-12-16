package co.za.immedia.repositories

import co.za.immedia.commons.extensions.toSuperhero
import co.za.immedia.commons.extensions.toSuperheroesTable
import co.za.immedia.commons.models.DbOperation
import co.za.immedia.commons.models.Superhero
import co.za.immedia.persistence.room.SuperheroDB

class DbRepository(private val database: SuperheroDB) {

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

    suspend fun updateHeroDbRating(superhero: Superhero) : DbOperation {
        return try {
            database.superheroesDAO.update(superhero.toSuperheroesTable())
            DbOperation(true)
        }
        catch (ex: Exception){
            DbOperation(false)
        }
    }

    suspend fun getHero(id: Long) : Superhero? {
        return try {
            val superheroTable = database.superheroesDAO.get(id)
            superheroTable?.toSuperhero()
        }
        catch (ex: Exception){
            null
        }
    }
}