package paolo.udacity.color.my.shoestoreinventorycapstone_1.features.shoe_management.instructions.view

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
import paolo.udacity.color.my.shoestoreinventorycapstone_1.databinding.FragmentInstructionsBinding
import paolo.udacity.color.my.shoestoreinventorycapstone_1.features.main.view.MainViewModel


class InstructionsFragment : Fragment() {

    private val viewModel by activityViewModels<MainViewModel>()
    private val viewStateObserver = Observer<InstructionsViewState> { state ->
        when(state) {
            is InstructionsViewState.GoToShoeList -> goToShoeList()
        }
    }
    private lateinit var binding: FragmentInstructionsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_instructions, container, false)
        initObservers()
        initUi()
        return binding.root
    }

    private fun initObservers() {
        viewModel.instructionsViewState.observe(viewLifecycleOwner, viewStateObserver)
    }

    private fun initUi() {
        binding.asInstructionsViewModel = viewModel
    }

    private fun goToShoeList() {
        view?.findNavController()?.navigate(InstructionsFragmentDirections.actionInstructionsFragmentToListShoesFragment())
    }

}