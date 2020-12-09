package co.za.immedia.commons.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class Work(
    @SerializedName("occupation") var occupation: String? = null,
    @SerializedName("base") var base: String? = null,
): Parcelable