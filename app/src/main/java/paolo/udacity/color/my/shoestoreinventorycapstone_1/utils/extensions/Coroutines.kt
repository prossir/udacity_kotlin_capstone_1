package paolo.udacity.color.my.shoestoreinventorycapstone_1.utils.extensions

import android.util.Log
import kotlinx.coroutines.*


fun globalLaunch(block: suspend () -> Unit) = GlobalScope.launch { block() }

fun CoroutineScope.safeLaunch(
    exception: Throwable.() -> Unit = {},
    bock: suspend CoroutineScope.() -> Unit
): Job {
    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.d("Coroutine Error", "handleException = $throwable")
        exception(throwable)
    }
    return this.launch(exceptionHandler) { bock(this) }
}

suspend fun <T> CoroutineScope.with(
    dispatcher: CoroutineDispatcher,
    block: suspend CoroutineScope.() -> T
) = withContext(dispatcher) { block() }

suspend fun <T> CoroutineScope.io(block: suspend CoroutineScope.() -> T) =
    withContext(Dispatchers.IO) { block() }

suspend fun <T> CoroutineScope.ui(block: suspend CoroutineScope.() -> T) =
    withContext(Dispatchers.Main) { block() }
