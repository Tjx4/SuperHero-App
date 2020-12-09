package co.za.immedia.commons.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class Connections(
    @SerializedName("group-affiliation") var groupAffiliation: String? = null,
    @SerializedName("relatives") var relatives: String? = null,
): Parcelable