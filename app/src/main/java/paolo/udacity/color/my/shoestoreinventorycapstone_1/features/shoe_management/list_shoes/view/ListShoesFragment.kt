package paolo.udacity.color.my.shoestoreinventorycapstone_1.features.shoe_management.list_shoes.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel
import paolo.udacity.color.my.shoestoreinventorycapstone_1.R
import paolo.udacity.color.my.shoestoreinventorycapstone_1.databinding.FragmentListShoesBinding
import paolo.udacity.color.my.shoestoreinventorycapstone_1.features.shoe_management.common.model.ShoeModel
import paolo.udacity.color.my.shoestoreinventorycapstone_1.utils.presentation.model.FailureModel


class ListShoesFragment : Fragment() {

    private val viewModel by viewModel<ListShoesViewModel>()
    private val viewStateObserver = Observer<ListShoesViewState> { state ->
        when(state) {
            is ListShoesViewState.IsLoading -> setLoadingIndicator(state.data)
            is ListShoesViewState.SuccessOnGettingRegisteredShoes -> setSuccessOnGettingRegisteredShoesOnUi(state.data)
            is ListShoesViewState.Failure -> setErrorInUi(state.failure)
            is ListShoesViewState.GoToAddShoes -> goToAddShoes()
        }
    }
    private lateinit var binding : FragmentListShoesBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_shoes, container, false)
        initObservers()
        initUi()
        return binding.root
    }

    private fun initObservers() {
        viewModel.viewState.observe(viewLifecycleOwner, viewStateObserver)
    }

    private fun initUi() {
        viewModel.getShoes()
    }

    private fun setSuccessOnGettingRegisteredShoesOnUi(data: List<ShoeModel>) {
        binding.llShoeListHolder.removeAllViews()
        for(shoe in data) {
            val viewToAdd = layoutInflater.inflate(R.layout.row_shoe, binding.llShoeListHolder)
            viewToAdd.findViewById<TextView>(R.id.tv_shoe_name).text = shoe.name
            viewToAdd.findViewById<TextView>(R.id.tv_shoe_size).text = shoe.sizeAsString + " in inches"
            viewToAdd.findViewById<TextView>(R.id.tv_shoe_company).text = shoe.company
            viewToAdd.findViewById<TextView>(R.id.tv_shoe_description).text = shoe.description
        }
    }

    private fun setErrorInUi(failure: FailureModel) {
        setLoadingIndicator(false)
        Snackbar.make(requireActivity().findViewById(android.R.id.content), failure.exactMessage ?: getString(failure.commonMessage), Snackbar.LENGTH_LONG).show()
    }

    private fun goToAddShoes() {
        view?.findNavController()?.navigate(ListShoesFragmentDirections.actionListShoesFragmentToCreateShoeFragment())
    }

    private fun setLoadingIndicator(isLoading: Boolean) {
        binding.llShoeListHolder.visibility = if(isLoading) View.GONE else View.VISIBLE
        binding.fabAddShoe.visibility = if(isLoading) View.GONE else View.VISIBLE
        binding.lavLoader.visibility = if(isLoading) View.VISIBLE else View.GONE
    }

}