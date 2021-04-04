package paolo.udacity.color.my.shoestoreinventorycapstone_1.features.authentication.login.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import paolo.udacity.color.my.shoestoreinventorycapstone_1.domain.use_case.authentication.AttemptLoginUseCase
import paolo.udacity.color.my.shoestoreinventorycapstone_1.domain.use_case.authentication.CreateUserUseCase
import paolo.udacity.color.my.shoestoreinventorycapstone_1.features.authentication.common.mapper.UserMapper
import paolo.udacity.color.my.shoestoreinventorycapstone_1.features.authentication.common.model.UserModel
import paolo.udacity.color.my.shoestoreinventorycapstone_1.features.authentication.login.mapper.LoginFailureMapper
import paolo.udacity.color.my.shoestoreinventorycapstone_1.utils.extensions.isValidEmail
import paolo.udacity.color.my.shoestoreinventorycapstone_1.utils.extensions.safeLaunch
import paolo.udacity.color.my.shoestoreinventorycapstone_1.utils.extensions.with


class LoginViewModel(
    private val attemptLoginUseCase: AttemptLoginUseCase,
    private val createUserUseCase: CreateUserUseCase,
    private val mapper: UserMapper,
    private val failureMapper: LoginFailureMapper,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    val user = UserModel(email = "", password = "")

    private val _viewState = MutableLiveData<LoginViewState>()
    val viewState: LiveData<LoginViewState>
        get() = _viewState

    fun login() {
        viewModelScope.safeLaunch(::exceptionHandler) {
            if(!user.email.isValidEmail()) {
                throw Exception("Your email is invalid.")
            }

            if(user.password.isBlank()) {
                throw Exception("Your password is invalid.")
            }

            _viewState.postValue(LoginViewState.IsLoading(true))

            with(dispatcher) { attemptLoginUseCase(mapper.reverseMap(user)) }
            _viewState.postValue(LoginViewState.SuccessOnLoginIn())
        }
    }

    fun createAccount() {
        viewModelScope.safeLaunch(::exceptionHandler) {
            if(!user.email.isValidEmail()) {
                throw Exception("Your email is invalid.")
            }

            if(user.password.isBlank()) {
                throw Exception("Your password is invalid.")
            }

            _viewState.postValue(LoginViewState.IsLoading(true))

            with(dispatcher) { createUserUseCase(mapper.reverseMap(user)) }
            _viewState.postValue(LoginViewState.SuccessOnCreatingAccount())
        }
    }

    private fun exceptionHandler(t: Throwable) {
        val failure = failureMapper.map(t)
        _viewState.postValue(LoginViewState.Failure(failure))
    }

}