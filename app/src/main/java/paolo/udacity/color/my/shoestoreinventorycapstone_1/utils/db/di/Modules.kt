package paolo.udacity.color.my.shoestoreinventorycapstone_1.utils.db.di

import org.koin.dsl.module
import paolo.udacity.color.my.shoestoreinventorycapstone_1.utils.db.provider.DaoProvider
import paolo.udacity.color.my.shoestoreinventorycapstone_1.utils.db.provider.DatabaseProvider


internal val databaseModule = module {
    single { DatabaseProvider(get()) }
    single { DaoProvider(get()) }
}