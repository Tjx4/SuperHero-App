package co.za.immedia.commons.converters

class NullToStringConverter {

    fun noNull(value: String?): String {
        return value ?: "Uknown"
    }


    fun String.noNull(value: String?): String {
        return value ?: "Uknown"
    }

}
