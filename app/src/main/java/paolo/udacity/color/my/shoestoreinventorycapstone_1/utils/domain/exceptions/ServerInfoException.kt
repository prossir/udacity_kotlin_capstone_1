package paolo.udacity.color.my.shoestoreinventorycapstone_1.utils.domain.exceptions


class ServerInfoException(
    message: String? = null,
    cause: Throwable? = null
) : Exception(message ?: cause?.message, cause)
