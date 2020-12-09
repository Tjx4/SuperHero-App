package co.za.immedia.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class DbOperation(
        var isSuccessful: Boolean = false
): Parcelable