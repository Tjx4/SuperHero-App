package co.za.immedia.superheroapp.converters

class NullToStringConverter {

    fun noNull(value: String?): String {
        return value ?: "Uknown"
    }


    fun String.noNull(value: String?): String {
        return value ?: "Uknown"
    }

}
