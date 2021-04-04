package paolo.udacity.color.my.shoestoreinventorycapstone_1.utils.db.provider

import paolo.udacity.color.my.shoestoreinventorycapstone_1.utils.db.dao.UserDao


class DaoProvider(private val database: DatabaseProvider) {

    fun getUserDao(): UserDao = database.getInstance().userDao()

}