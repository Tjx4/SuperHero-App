package co.za.immedia.superheroapp.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class Image(
    @SerializedName("url") var url: String? = null
): Parcelable