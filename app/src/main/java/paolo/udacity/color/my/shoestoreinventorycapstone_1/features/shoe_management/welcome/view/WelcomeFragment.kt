package paolo.udacity.color.my.shoestoreinventorycapstone_1.features.shoe_management.welcome.view

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
import paolo.udacity.color.my.shoestoreinventorycapstone_1.databinding.FragmentWelcomeBinding
import paolo.udacity.color.my.shoestoreinventorycapstone_1.features.main.view.MainViewModel


class WelcomeFragment : Fragment() {

    private val viewModel by activityViewModels<MainViewModel>()
    private val viewStateObserver = Observer<WelcomeViewState> { state ->
        when(state) {
            is WelcomeViewState.GoToInstructions -> goToInstructions()
        }
    }
    private lateinit var binding: FragmentWelcomeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_welcome, container, false)
        initObservers()
        initUi()
        return binding.root
    }

    private fun initObservers() {
        viewModel.welcomeViewState.observe(viewLifecycleOwner, viewStateObserver)
    }

    private fun initUi() {
        binding.asWelcomeViewModel = viewModel
    }

    private fun goToInstructions() {
        view?.findNavController()?.navigate(WelcomeFragmentDirections.actionWelcomeFragmentToInstructionsFragment())
    }

}