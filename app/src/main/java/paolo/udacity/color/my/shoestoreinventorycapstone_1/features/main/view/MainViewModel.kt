package paolo.udacity.color.my.shoestoreinventorycapstone_1.features.main.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import paolo.udacity.color.my.shoestoreinventorycapstone_1.domain.use_case.authentication.AttemptLoginUseCase
import paolo.udacity.color.my.shoestoreinventorycapstone_1.domain.use_case.authentication.CreateUserUseCase
import paolo.udacity.color.my.shoestoreinventorycapstone_1.domain.use_case.authentication.IsUserLoggedUseCase
import paolo.udacity.color.my.shoestoreinventorycapstone_1.domain.use_case.main.GetUserUseCase
import paolo.udacity.color.my.shoestoreinventorycapstone_1.domain.use_case.main.LogOutUseCase
import paolo.udacity.color.my.shoestoreinventorycapstone_1.domain.use_case.shoe_management.CreateShoeUseCase
import paolo.udacity.color.my.shoestoreinventorycapstone_1.domain.use_case.shoe_management.GetShoesUseCase
import paolo.udacity.color.my.shoestoreinventorycapstone_1.features.authentication.common.mapper.UserMapper
import paolo.udacity.color.my.shoestoreinventorycapstone_1.features.authentication.common.model.UserModel
import paolo.udacity.color.my.shoestoreinventorycapstone_1.features.authentication.login.view.LoginViewState
import paolo.udacity.color.my.shoestoreinventorycapstone_1.features.authentication.splash.view.SplashViewState
import paolo.udacity.color.my.shoestoreinventorycapstone_1.features.main.mapper.MainFailureMapper
import paolo.udacity.color.my.shoestoreinventorycapstone_1.features.shoe_management.common.mapper.ShoeMapper
import paolo.udacity.color.my.shoestoreinventorycapstone_1.features.shoe_management.common.model.ShoeModel
import paolo.udacity.color.my.shoestoreinventorycapstone_1.features.shoe_management.create_shoe.view.CreateShoeViewState
import paolo.udacity.color.my.shoestoreinventorycapstone_1.features.shoe_management.instructions.view.InstructionsViewState
import paolo.udacity.color.my.shoestoreinventorycapstone_1.features.shoe_management.list_shoes.view.ListShoesViewState
import paolo.udacity.color.my.shoestoreinventorycapstone_1.features.shoe_management.welcome.view.WelcomeViewState
import paolo.udacity.color.my.shoestoreinventorycapstone_1.utils.extensions.isValidEmail
import paolo.udacity.color.my.shoestoreinventorycapstone_1.utils.extensions.safeLaunch
import paolo.udacity.color.my.shoestoreinventorycapstone_1.utils.extensions.with
import java.util.concurrent.atomic.AtomicBoolean


class MainViewModel(
    // Splash related actions and mappers
    private val isUserLoggedUseCase: IsUserLoggedUseCase,
    // Login related actions and mappers
    private val attemptLoginUseCase: AttemptLoginUseCase,
    private val createUserUseCase: CreateUserUseCase,
    private val userMapper: UserMapper,
    // Welcome related actions and mappers
    // Instructions related actions and mappers
    // List shoes related actions and mappers
    private val getShoesUseCase: GetShoesUseCase,
    private val shoeMapper: ShoeMapper,
    // Create shoes related actions and mappers
    private val createShoeUseCase: CreateShoeUseCase,
    // Main related actions and mappers
    private val getUserUseCase: GetUserUseCase,
    private val logOutUseCase: LogOutUseCase,
    private val failureMapper: MainFailureMapper,
    // Common usages
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    val user = UserModel(email = "", password = "")

    private val _splashViewState = MutableLiveData<SplashViewState>()
    val splashViewState: LiveData<SplashViewState>
        get() = _splashViewState

    fun isUserLogged() {
        viewModelScope.safeLaunch(::exceptionHandler) {
            val result = with(dispatcher) { isUserLoggedUseCase() }
            canContinueToNext.set(true)
            _splashViewState.value = SplashViewState.SuccessInGettingUserLogged(result)
        }
    }

    // Login related variables
    private val _loginViewState = MutableLiveData<LoginViewState>()
    val loginViewState: LiveData<LoginViewState>
        get() = _loginViewState

    fun login() {
        viewModelScope.safeLaunch(::exceptionHandler) {
            if(!user.email.isValidEmail()) {
                throw Exception("Your email is invalid.")
            }

            if(user.password.isBlank()) {
                throw Exception("Your password is invalid.")
            }

            with(dispatcher) { attemptLoginUseCase(userMapper.reverseMap(user)) }
            _loginViewState.postValue(LoginViewState.SuccessOnLoginIn())
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

            //_loginViewState.postValue(LoginViewState.IsLoading(true))
            with(dispatcher) { createUserUseCase(userMapper.reverseMap(user)) }
            _loginViewState.postValue(LoginViewState.SuccessOnCreatingAccount())
        }
    }

    // Welcome related variables
    private val _welcomeViewState = MutableLiveData<WelcomeViewState>()
    val welcomeViewState: LiveData<WelcomeViewState>
        get() = _welcomeViewState
    var canGoToInstructions: AtomicBoolean = AtomicBoolean(false)
    fun goToInstructions(canAct : Boolean = false) {
        canGoToInstructions.set(canAct)
        _welcomeViewState.postValue(WelcomeViewState.GoToInstructions())
    }

    // Instructions related variables
    private val _instructionsViewState = MutableLiveData<InstructionsViewState>()
    val instructionsViewState: LiveData<InstructionsViewState>
        get() = _instructionsViewState

    fun goToShoeList() {
        _instructionsViewState.postValue(InstructionsViewState.GoToShoeList())
    }

    // List shoes related variables
    private val _listShoesViewState = MutableLiveData<ListShoesViewState>()
    val listShoesViewState: LiveData<ListShoesViewState>
        get() = _listShoesViewState
    var canGoToShoeCreation: AtomicBoolean = AtomicBoolean(false)
    lateinit var shoes : List<ShoeModel>

    fun getShoes() {
        viewModelScope.safeLaunch(::exceptionHandler) {
            val result = with(dispatcher) { getShoesUseCase() }
            shoes = shoeMapper.map(result)
            _listShoesViewState.postValue(ListShoesViewState.SuccessOnGettingRegisteredShoes(shoes))
        }
    }

    fun goToAddShoes() {
        canGoToShoeCreation.set(true)
        _listShoesViewState.postValue(ListShoesViewState.GoToAddShoes())
    }

    // Create shoes related variables
    private val _createShoeViewState = MutableLiveData<CreateShoeViewState>()
    val createShoeViewState: LiveData<CreateShoeViewState>
        get() = _createShoeViewState
    var canReturnToShoeListing: AtomicBoolean = AtomicBoolean(false)
    val newShoe = ShoeModel(name = "", company = "", size = 0.0, description = "")

    fun cancelShoeCreation() {
        canReturnToShoeListing.set(true)
        _createShoeViewState.postValue(CreateShoeViewState.SuccessOnCancellingShoeCreation())
    }

    fun createShoe() {
        viewModelScope.safeLaunch(::exceptionHandler) {
            if(newShoe.name.isBlank()) {
                throw Exception("The shoe must have a name.")
            }

            if(newShoe.name.length > 40) {
                throw Exception("The shoe name must have less than 40 characters.")
            }

            if(newShoe.company.isBlank()) {
                throw Exception("The shoe must have a company that manufactures it.")
            }

            if(newShoe.company.length > 40) {
                throw Exception("The company name must have less than 40 characters.")
            }

            if(newShoe.size < 4 || newShoe.size > 14) {
                throw Exception("The shoe must have a size between 4 to 14")
            }

            if(newShoe.description.isBlank()) {
                throw Exception("The shoe must have a description.")
            }

            if(newShoe.description.length > 200) {
                throw Exception("The description must have less than 200 characters.")
            }

            with(dispatcher) { createShoeUseCase(shoeMapper.reverseMap(newShoe)) }
            canReturnToShoeListing.set(true)
            _createShoeViewState.postValue(CreateShoeViewState.SuccessOnCreatingShoe())
        }
    }

    fun resetShoeData() {
        newShoe.reset()
    }

    // Main related variables
    private val _viewState = MutableLiveData<MainViewState>()
    val viewState: LiveData<MainViewState>
        get() = _viewState
    var canContinueToNext: AtomicBoolean = AtomicBoolean(false)

    fun getUser() {
        viewModelScope.safeLaunch(::exceptionHandler) {
            val result = with(dispatcher) { getUserUseCase() }
            _viewState.value = MainViewState.SuccessInGettingUser(userMapper.map(result))
        }
    }

    fun logout() {
        viewModelScope.safeLaunch(::exceptionHandler) {
            with(dispatcher) { logOutUseCase() }
            _listShoesViewState.postValue(ListShoesViewState.OnLogoutReady())
            canContinueToNext.set(true)
        }
    }

    private fun exceptionHandler(t: Throwable) {
        val failure = failureMapper.map(t)
        _viewState.postValue(MainViewState.Failure(failure))
    }

}