package paolo.udacity.color.my.shoestoreinventorycapstone_1.features.shoe_management.common.model


data class ShoeModel(
        var name: String,
        var company: String,
        var size: Double,
        var description: String
) {

    var sizeAsString : String
        get() = "$size"
        set(value) { size = if(value.isBlank()) 0.0 else value.toDouble() }

    fun reset() {
        name = ""
        company = ""
        size = 0.0
        description = ""
    }

}