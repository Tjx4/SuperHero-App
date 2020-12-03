package co.za.immedia.superheroapp.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class PowerStats(
    @SerializedName("intelligence") var intelligence: String? = null,
    @SerializedName("strength") var strength: String? = null,
    @SerializedName("speed") var speed: String? = null,
    @SerializedName("durability") var durability: String? = null,
    @SerializedName("power") var power: String? = null,
    @SerializedName("combat") var combat: String? = null
): Parcelable