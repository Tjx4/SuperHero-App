package co.za.immedia.superheroapp.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class Appearance(
    @SerializedName("gender") var gender: String? = null,
    @SerializedName("gender") var race: String? = null,
    @SerializedName("height") var height: List<String>? = null,
    @SerializedName("weight") var weight: List<String>? = null,
    @SerializedName("eye-color") var eyeColor: String? = null,
    @SerializedName("hair-color") var hairColor: String? = null,
): Parcelable