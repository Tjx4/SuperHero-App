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
    @ColumnInfo(name = "imageUrl")
    var imageUrl:String? = null,
): Parcelable