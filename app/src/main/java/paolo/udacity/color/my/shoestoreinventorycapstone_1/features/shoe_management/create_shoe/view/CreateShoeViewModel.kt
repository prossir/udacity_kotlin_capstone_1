package paolo.udacity.color.my.shoestoreinventorycapstone_1.features.shoe_management.create_shoe.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import paolo.udacity.color.my.shoestoreinventorycapstone_1.domain.use_case.shoe_management.CreateShoeUseCase
import paolo.udacity.color.my.shoestoreinventorycapstone_1.features.shoe_management.common.mapper.ShoeMapper
import paolo.udacity.color.my.shoestoreinventorycapstone_1.features.shoe_management.common.model.ShoeModel
import paolo.udacity.color.my.shoestoreinventorycapstone_1.features.shoe_management.create_shoe.mapper.CreateShoeFailureMapper
import paolo.udacity.color.my.shoestoreinventorycapstone_1.utils.extensions.safeLaunch
import paolo.udacity.color.my.shoestoreinventorycapstone_1.utils.extensions.with


class CreateShoeViewModel(
        private val useCase: CreateShoeUseCase,
        private val shoeMapper: ShoeMapper,
        private val failureMapper: CreateShoeFailureMapper,
        private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _viewState = MutableLiveData<CreateShoeViewState>()
    val viewState: LiveData<CreateShoeViewState>
        get() = _viewState

    val shoe = ShoeModel(name = "", company = "", size = 0.0, description = "")

    fun cancelShoeCreation() {
        _viewState.postValue(CreateShoeViewState.SuccessOnCancellingShoeCreation())
    }

    fun createShoe() {
        viewModelScope.safeLaunch(::exceptionHandler) {
            if(shoe.name.isBlank()) {
                throw Exception("The shoe must have a name.")
            }

            if(shoe.name.length > 40) {
                throw Exception("The shoe name must have less than 40 characters.")
            }

            if(shoe.company.isBlank()) {
                throw Exception("The shoe must have a company that manufactures it.")
            }

            if(shoe.company.length > 40) {
                throw Exception("The company name must have less than 40 characters.")
            }

            if(shoe.size < 4 || shoe.size > 14) {
                throw Exception("The shoe must have a size between 4 to 14")
            }

            if(shoe.description.isBlank()) {
                throw Exception("The shoe must have a description.")
            }

            if(shoe.description.length > 200) {
                throw Exception("The description must have less than 200 characters.")
            }

            with(dispatcher) { useCase(shoeMapper.reverseMap(shoe)) }
            _viewState.postValue(CreateShoeViewState.SuccessOnCreatingShoe())
        }
    }

    private fun exceptionHandler(t: Throwable) {
        val failure = failureMapper.map(t)
        _viewState.postValue(CreateShoeViewState.Failure(failure))
    }

}