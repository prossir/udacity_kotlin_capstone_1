package paolo.udacity.color.my.shoestoreinventorycapstone_1.domain.entity


data class Shoe(
    var name: String,
    var size: Double,
    var company: String,
    var description: String,
    val images: List<String> = mutableListOf()
)