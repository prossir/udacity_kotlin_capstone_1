package paolo.udacity.color.my.shoestoreinventorycapstone_1.features.shoe_management.create_shoe.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel
import paolo.udacity.color.my.shoestoreinventorycapstone_1.R
import paolo.udacity.color.my.shoestoreinventorycapstone_1.databinding.FragmentCreateShoeBinding
import paolo.udacity.color.my.shoestoreinventorycapstone_1.utils.presentation.model.FailureModel


class CreateShoeFragment : Fragment() {

    private val viewModel by viewModel<CreateShoeViewModel>()
    private val viewStateObserver = Observer<CreateShoeViewState> { state ->
        when(state) {
            is CreateShoeViewState.IsLoading -> setLoadingIndicator(state.data)
            is CreateShoeViewState.SuccessOnCreatingShoe -> setSuccessOnCreatingShoeInUi()
            is CreateShoeViewState.SuccessOnCancellingShoeCreation -> setSuccessOnCancellingShoeCreationInUi()
            is CreateShoeViewState.Failure -> setErrorInUi(state.failure)

        }
    }
    private lateinit var binding : FragmentCreateShoeBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_create_shoe, container, false)
        initObservers()
        initUI()
        return binding.root
    }

    private fun initObservers() {
        viewModel.viewState.observe(viewLifecycleOwner, viewStateObserver)
    }

    private fun initUI() {
        binding.createShoeViewModel = viewModel
    }

    private fun setSuccessOnCreatingShoeInUi() {

    }

    private fun setSuccessOnCancellingShoeCreationInUi() {

    }

    private fun setErrorInUi(failure: FailureModel) {
        setLoadingIndicator(false)
        Snackbar.make(requireActivity().findViewById(android.R.id.content), failure.exactMessage ?: getString(failure.commonMessage), Snackbar.LENGTH_LONG).show()
    }

    private fun setLoadingIndicator(isLoading: Boolean) {
        binding.gActions.visibility = if(isLoading) View.GONE else View.VISIBLE
        binding.lavLoader.visibility = if(isLoading) View.VISIBLE else View.GONE
    }

}