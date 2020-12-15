package co.za.immedia.persistence.room.tables

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "superheroes")
data class SuperheroesTable (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id:Long = 0L,
    @ColumnInfo(name = "name")
    var name:String? = null,

    @ColumnInfo(name = "intelligence")
    var intelligence:String? = null,
    @ColumnInfo(name = "strength")
    var strength:String? = null,
    @ColumnInfo(name = "speed")
    var speed:String? = null,

    @ColumnInfo(name = "fullName")
    var fullName:String? = null,
    @ColumnInfo(name = "alterEgos")
    var alterEgos:String? = null,
    @ColumnInfo(name = "placeOfBirth")
    var placeOfBirth:String? = null,
    @ColumnInfo(name = "publisher")
    var publisher:String? = null,

    @ColumnInfo(name = "imageUrl")
    var imageUrl:String? = null,
): Parcelable