package paolo.udacity.color.my.shoestoreinventorycapstone_1.features.shoe_management.list_shoes.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import paolo.udacity.color.my.shoestoreinventorycapstone_1.R
import paolo.udacity.color.my.shoestoreinventorycapstone_1.databinding.FragmentListShoesBinding
import paolo.udacity.color.my.shoestoreinventorycapstone_1.features.main.view.MainViewModel
import paolo.udacity.color.my.shoestoreinventorycapstone_1.features.shoe_management.common.model.ShoeModel


class ListShoesFragment : Fragment() {

    private val viewModel by activityViewModels<MainViewModel>()
    private val viewStateObserver = Observer<ListShoesViewState> { state ->
        when(state) {
            is ListShoesViewState.IsLoading -> setLoadingIndicator(state.data)
            is ListShoesViewState.SuccessOnGettingRegisteredShoes -> setSuccessOnGettingRegisteredShoesOnUi(state.data)
            is ListShoesViewState.GoToAddShoes -> goToAddShoes()
            is ListShoesViewState.OnLogoutReady -> setOnLogoutReadyInUi()
        }
    }
    private lateinit var binding : FragmentListShoesBinding
    private val arguments: ListShoesFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_shoes, container, false)
        initObservers()
        initUi()
        return binding.root
    }

    override fun onResume() {
        if(arguments.shoeWasAdded) {
            viewModel.getShoes()
        }
        super.onResume()
    }

    private fun initObservers() {
        viewModel.listShoesViewState.observe(viewLifecycleOwner, viewStateObserver)
    }

    private fun initUi() {
        binding.asListShoesViewModel = viewModel
        viewModel.getUser()
        viewModel.getShoes()
    }

    private fun setSuccessOnGettingRegisteredShoesOnUi(data: List<ShoeModel>) {
        binding.llShoeListHolder.removeAllViews()
        if(data.isEmpty()) {
            binding.tvEmptyList.visibility = View.VISIBLE
            binding.llShoeListHolder.visibility = View.GONE
        } else {
            binding.tvEmptyList.visibility = View.GONE
            binding.llShoeListHolder.visibility = View.VISIBLE
            for(shoe in data) {
                val viewToAdd = layoutInflater.inflate(R.layout.row_shoe, binding.llShoeListHolder, false)
                viewToAdd.findViewById<TextView>(R.id.tv_shoe_name).text = shoe.name
                viewToAdd.findViewById<TextView>(R.id.tv_shoe_size).text = shoe.sizeAsString + " in inches"
                viewToAdd.findViewById<TextView>(R.id.tv_shoe_company).text = shoe.company
                viewToAdd.findViewById<TextView>(R.id.tv_shoe_description).text = shoe.description
                binding.llShoeListHolder.addView(viewToAdd)
            }
        }
    }

    private fun goToAddShoes() {
        if(viewModel.canGoToShoeCreation) {
            requireView().findNavController().navigate(ListShoesFragmentDirections.actionListShoesFragmentToCreateShoeFragment())
            viewModel.canGoToShoeCreation = false
        }
    }

    private fun setOnLogoutReadyInUi() {
        if(viewModel.canContinueToNext.getAndSet(false)) {
            requireView().findNavController().navigate(ListShoesFragmentDirections.actionListShoesFragmentToLoginFragment())
        }
    }

    private fun setLoadingIndicator(isLoading: Boolean) {
        binding.llShoeListHolder.visibility = if(isLoading) View.GONE else View.VISIBLE
        binding.fabAddShoe.visibility = if(isLoading) View.GONE else View.VISIBLE
        binding.lavLoader.visibility = if(isLoading) View.VISIBLE else View.GONE
    }

}