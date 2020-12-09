package co.za.immedia.persistence.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import co.za.immedia.persistence.room.tables.SuperheroesDAO
import co.za.immedia.persistence.room.tables.SuperheroesTable

@Database(entities = [SuperheroesTable::class], version = 1, exportSchema = false)
abstract class SuperheroDB : RoomDatabase() {
    abstract val superheroesDAO: SuperheroesDAO

    companion object{
        @Volatile
        private var INSTANCE: SuperheroDB? = null

        fun getInstance(context: Context): SuperheroDB {
            synchronized(this){
                var instance = INSTANCE

                if(instance == null){
                    instance = Room.databaseBuilder(context.applicationContext, SuperheroDB::class.java, "superheroes_db")
                            .fallbackToDestructiveMigration()
                            .build()
                    INSTANCE = instance
                }

                return  instance
            }
        }
    }

}