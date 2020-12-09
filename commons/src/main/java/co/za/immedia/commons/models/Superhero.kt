package co.za.immedia.commons.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class Superhero(
    @SerializedName("id")var id: Long = 0,
    @SerializedName("name")var name: String? = null,
    @SerializedName("powerstats") var powerstats: PowerStats? = null,
    @SerializedName("biography") var biography: Biography? = null,
    @SerializedName("appearance") var appearance: Appearance? = null,
    @SerializedName("work") var work: Work? = null,
    @SerializedName("connections")var Connections: Work? = null,
    @SerializedName("image")var image: Image? = null,
    var isFav: Boolean? = false,
): Parcelable









