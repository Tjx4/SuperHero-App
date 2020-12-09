package co.za.immedia.persistence.room.tables

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface SuperheroesDAO {
    @Insert
    fun insert(superheroesTable: SuperheroesTable)

    @Update
    fun update(superheroesTable: SuperheroesTable)

    @Delete
    fun delete(superheroesTable: SuperheroesTable)

    @Query("SELECT * FROM superheroes WHERE id = :key")
    fun get(key: Long): SuperheroesTable?

    @Query("SELECT * FROM superheroes ORDER BY id DESC LIMIT 1")
    fun getLastUser(): SuperheroesTable?

    @Query("SELECT * FROM superheroes ORDER BY id DESC")
    fun getAllUsersLiveData(): LiveData<List<SuperheroesTable>>

    @Query("SELECT * FROM superheroes ORDER BY id DESC")
    fun getAllHeroes():List<SuperheroesTable>?

    @Query("DELETE  FROM superheroes")
    fun clear()
}