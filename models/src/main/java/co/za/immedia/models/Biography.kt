package co.za.immedia.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class Biography(
    @SerializedName("full-name") var fullName: String? = null,
    @SerializedName("alter-egos") var alterEgos: String? = null,
    @SerializedName("aliases") var aliases: List<String>? = null,
    @SerializedName("place-of-birth") var placeOfBirth: String? = null,
    @SerializedName("first-appearance") var firstAppearance: String? = null,
    @SerializedName("publisher") var publisher: String? = null,
    @SerializedName("alignment") var alignment: String? = null,
): Parcelable