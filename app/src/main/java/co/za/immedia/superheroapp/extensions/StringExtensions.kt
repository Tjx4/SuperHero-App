package co.za.immedia.superheroapp.extensions

fun nullToDefValue(value: String?, defValue: String = "Unknown"): String {
    return if(value == "null")  "$defValue" else "$value"
}
