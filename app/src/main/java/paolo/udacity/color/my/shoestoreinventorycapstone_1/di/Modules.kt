package paolo.udacity.color.my.shoestoreinventorycapstone_1.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import paolo.udacity.color.my.shoestoreinventorycapstone_1.data.di.dataModules
import paolo.udacity.color.my.shoestoreinventorycapstone_1.domain.di.domainModules
import paolo.udacity.color.my.shoestoreinventorycapstone_1.features.di.featuresModules
import paolo.udacity.color.my.shoestoreinventorycapstone_1.utils.di.utilsModules
import paolo.udacity.color.my.shoestoreinventorycapstone_1.utils.extensions.listByElementsOf


internal fun injectKoinModules(app: Application) {
    startKoin {
        androidLogger()
        androidContext(app)
        modules(baseModules)
    }
}

private val baseModules by lazy {
    listByElementsOf<Module>(
        appModules
    )
}

val appModules by lazy {
    listByElementsOf<Module>(
        featuresModules,
        domainModules,
        dataModules,
        utilsModules
    )
}