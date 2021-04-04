package paolo.udacity.color.my.shoestoreinventorycapstone_1.features.shoe_management.main.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import paolo.udacity.color.my.shoestoreinventorycapstone_1.domain.use_case.main.GetUserUseCase
import paolo.udacity.color.my.shoestoreinventorycapstone_1.domain.use_case.main.LogOutUseCase
import paolo.udacity.color.my.shoestoreinventorycapstone_1.features.authentication.common.mapper.UserMapper
import paolo.udacity.color.my.shoestoreinventorycapstone_1.features.shoe_management.list_shoes.mapper.MainFailureMapper
import paolo.udacity.color.my.shoestoreinventorycapstone_1.utils.extensions.safeLaunch
import paolo.udacity.color.my.shoestoreinventorycapstone_1.utils.extensions.with


class MainViewModel(
    private val getUserUseCase: GetUserUseCase,
    private val logOutUseCase: LogOutUseCase,
    private val userMapper: UserMapper,
    private val failureMapper: MainFailureMapper,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _viewState = MutableLiveData<MainViewState>()
    val viewState: LiveData<MainViewState>
        get() = _viewState

    fun getUser() {
        viewModelScope.safeLaunch(::exceptionHandler) {
            val result = with(dispatcher) { getUserUseCase() }
            _viewState.value = MainViewState.SuccessInGettingUser(userMapper.map(result))
        }
    }

    fun logout() {
        viewModelScope.safeLaunch(::exceptionHandler) {
            with(dispatcher) { logOutUseCase() }
            _viewState.value = MainViewState.SuccessInLogOut()
        }
    }

    private fun exceptionHandler(t: Throwable) {
        val failure = failureMapper.map(t)
        _viewState.postValue(MainViewState.Failure(failure))
    }

}