package paolo.udacity.color.my.shoestoreinventorycapstone_1.features.authentication.di

import org.koin.core.module.Module
import paolo.udacity.color.my.shoestoreinventorycapstone_1.features.authentication.common.di.authCommonPartModule
import paolo.udacity.color.my.shoestoreinventorycapstone_1.features.authentication.login.di.loginPartModule
import paolo.udacity.color.my.shoestoreinventorycapstone_1.features.authentication.splash.di.splashPartModule
import paolo.udacity.color.my.shoestoreinventorycapstone_1.utils.extensions.listByElementsOf


val authenticationFeatureModule by lazy {
    listByElementsOf<Module>(
        authCommonPartModule,
        loginPartModule,
        splashPartModule
    )
}
