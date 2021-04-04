package paolo.udacity.color.my.shoestoreinventorycapstone_1.utils.provider.di

import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.threeten.bp.OffsetDateTime
import paolo.udacity.color.my.shoestoreinventorycapstone_1.utils.provider.DateTimeProvider
import paolo.udacity.color.my.shoestoreinventorycapstone_1.utils.provider.OffsetDateTimeProvider


const val NAMED_OFFSET_PROVIDER = "OffsetDateTimeProvider"

internal val provideModule = module {
    single<DateTimeProvider<OffsetDateTime>>(named(NAMED_OFFSET_PROVIDER)) { OffsetDateTimeProvider() }
}