package paolo.udacity.color.my.shoestoreinventorycapstone_1.features.shoe_management.create_shoe.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import paolo.udacity.color.my.shoestoreinventorycapstone_1.R
import paolo.udacity.color.my.shoestoreinventorycapstone_1.databinding.FragmentCreateShoeBinding
import paolo.udacity.color.my.shoestoreinventorycapstone_1.features.main.view.MainViewModel
import paolo.udacity.color.my.shoestoreinventorycapstone_1.utils.extensions.hideKeyboard


class CreateShoeFragment : Fragment() {

    private val viewModel by activityViewModels<MainViewModel>()
    private val viewStateObserver = Observer<CreateShoeViewState> { state ->
        when(state) {
            is CreateShoeViewState.IsLoading -> setLoadingIndicator(state.data)
            is CreateShoeViewState.SuccessOnCreatingShoe -> setSuccessOnCreatingShoeInUi()
            is CreateShoeViewState.SuccessOnCancellingShoeCreation -> setSuccessOnCancellingShoeCreationInUi()
        }
    }
    private lateinit var binding : FragmentCreateShoeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_create_shoe, container, false)
        initObservers()
        initUI()
        return binding.root
    }

    private fun initObservers() {
        viewModel.createShoeViewState.observe(viewLifecycleOwner, viewStateObserver)
    }

    private fun initUI() {
        binding.asCreateShoeViewModel = viewModel
    }

    private fun setSuccessOnCreatingShoeInUi() {
        navigateToShoeList(true)
    }

    private fun setSuccessOnCancellingShoeCreationInUi() {
        navigateToShoeList(false)
    }

    private fun navigateToShoeList(wasShoeAdded: Boolean) {
        if(viewModel.canReturnToShoeListing.getAndSet(false)) {
            hideKeyboard()
            requireView().findNavController().navigate(CreateShoeFragmentDirections.actionCreateShoeFragmentToListShoesFragment(wasShoeAdded))
        }
    }

    private fun setLoadingIndicator(isLoading: Boolean) {
        binding.gActions.visibility = if(isLoading) View.GONE else View.VISIBLE
        binding.lavLoader.visibility = if(isLoading) View.VISIBLE else View.GONE
    }

}