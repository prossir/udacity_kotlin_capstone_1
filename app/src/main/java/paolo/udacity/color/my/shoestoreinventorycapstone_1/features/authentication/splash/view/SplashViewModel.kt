package paolo.udacity.color.my.shoestoreinventorycapstone_1.features.authentication.splash.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import paolo.udacity.color.my.shoestoreinventorycapstone_1.domain.use_case.authentication.IsUserLoggedUseCase
import paolo.udacity.color.my.shoestoreinventorycapstone_1.features.authentication.splash.mapper.IsUserLoggedFailureMapper
import paolo.udacity.color.my.shoestoreinventorycapstone_1.utils.extensions.safeLaunch
import paolo.udacity.color.my.shoestoreinventorycapstone_1.utils.extensions.with


class SplashViewModel(
    private val isUserLoggedUseCase: IsUserLoggedUseCase,
    private val failureMapper: IsUserLoggedFailureMapper,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _viewState = MutableLiveData<SplashViewState>()
    val viewState: LiveData<SplashViewState>
        get() = _viewState

    fun isUserLogged() {
        viewModelScope.safeLaunch(::exceptionHandler) {
            val result = with(dispatcher) { isUserLoggedUseCase() }
            _viewState.value = SplashViewState.SuccessInGettingUserLogged(result)
        }
    }

    private fun exceptionHandler(t: Throwable) {
        val failure = failureMapper.map(t)
        _viewState.postValue(SplashViewState.Failure(failure))
    }

}