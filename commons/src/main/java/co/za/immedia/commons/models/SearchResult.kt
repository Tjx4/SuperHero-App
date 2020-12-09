package co.za.immedia.commons.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class SearchResult(
    @SerializedName("response") var response:  Boolean = false,
    @SerializedName("results-for") var resultsFor:  String? = null,
    @SerializedName("results") var results:  List<Superhero?>? = null
): Parcelable