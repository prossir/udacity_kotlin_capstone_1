package paolo.udacity.color.my.shoestoreinventorycapstone_1.features.shoe_management.common.mapper

import paolo.udacity.color.my.shoestoreinventorycapstone_1.domain.entity.Shoe
import paolo.udacity.color.my.shoestoreinventorycapstone_1.features.shoe_management.common.model.ShoeModel
import paolo.udacity.color.my.shoestoreinventorycapstone_1.utils.mapper.Mapper


class ShoeMapper : Mapper<Shoe, ShoeModel>() {

    override fun reverseMap(value: ShoeModel) = Shoe(
            name = value.name,
            size = value.size,
            company = value.company,
            description = value.description
    )

    override fun map(value: Shoe) = ShoeModel(
            name = value.name,
            size = value.size,
            company = value.company,
            description = value.description
    )

}