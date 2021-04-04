package paolo.udacity.color.my.shoestoreinventorycapstone_1.utils.provider


interface DateTimeProvider<out T> {
    fun now(): T
}
