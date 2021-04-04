package paolo.udacity.color.my.shoestoreinventorycapstone_1

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import paolo.udacity.color.my.shoestoreinventorycapstone_1.di.injectKoinModules


class CapstoneApp: Application() {

    override fun onCreate() {
        super.onCreate()
        injectModules()
        AndroidThreeTen.init(this)
    }

    private fun injectModules() {
        injectKoinModules(this)
    }

}