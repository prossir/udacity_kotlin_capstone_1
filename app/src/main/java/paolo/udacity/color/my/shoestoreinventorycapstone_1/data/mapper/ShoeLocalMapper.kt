package paolo.udacity.color.my.shoestoreinventorycapstone_1.data.mapper

import paolo.udacity.color.my.shoestoreinventorycapstone_1.domain.entity.Shoe
import paolo.udacity.color.my.shoestoreinventorycapstone_1.utils.db.models.ShoeEntity
import paolo.udacity.color.my.shoestoreinventorycapstone_1.utils.mapper.Mapper


class ShoeLocalMapper : Mapper<ShoeEntity, Shoe>() {

    override fun reverseMap(value: Shoe) = ShoeEntity(
            id = 0,
            name = value.name,
            size = value.size,
            company = value.company,
            description = value.description,
            userId = 0,
            createdAt = null
    )

    override fun map(value: ShoeEntity) = Shoe(
            name = value.name,
            size = value.size,
            company = value.company,
            description = value.description
    )

}