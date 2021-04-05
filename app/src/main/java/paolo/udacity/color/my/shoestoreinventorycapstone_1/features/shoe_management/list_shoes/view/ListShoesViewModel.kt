package paolo.udacity.color.my.shoestoreinventorycapstone_1.features.shoe_management.list_shoes.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import paolo.udacity.color.my.shoestoreinventorycapstone_1.domain.use_case.shoe_management.GetShoesUseCase
import paolo.udacity.color.my.shoestoreinventorycapstone_1.features.shoe_management.common.mapper.ShoeMapper
import paolo.udacity.color.my.shoestoreinventorycapstone_1.features.shoe_management.common.model.ShoeModel
import paolo.udacity.color.my.shoestoreinventorycapstone_1.features.shoe_management.list_shoes.mapper.ListShoesFailureMapper
import paolo.udacity.color.my.shoestoreinventorycapstone_1.utils.extensions.safeLaunch
import paolo.udacity.color.my.shoestoreinventorycapstone_1.utils.extensions.with


class ListShoesViewModel(
    private val useCase: GetShoesUseCase,
    private val shoeMapper: ShoeMapper,
    private val failureMapper: ListShoesFailureMapper,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _viewState = MutableLiveData<ListShoesViewState>()
    val viewState: LiveData<ListShoesViewState>
        get() = _viewState

    lateinit var shoes : List<ShoeModel>

    fun getShoes() {
        viewModelScope.safeLaunch(::exceptionHandler) {
            val result = with(dispatcher) { useCase() }
            shoes = shoeMapper.map(result)
            _viewState.postValue(ListShoesViewState.SuccessOnGettingRegisteredShoes(shoes))
        }
    }

    fun goToAddShoes() {
        _viewState.postValue(ListShoesViewState.GoToAddShoes())
    }

    private fun exceptionHandler(t: Throwable) {
        val failure = failureMapper.map(t)
        _viewState.postValue(ListShoesViewState.Failure(failure))
    }

}