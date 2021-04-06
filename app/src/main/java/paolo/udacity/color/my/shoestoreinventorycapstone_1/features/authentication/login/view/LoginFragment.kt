package paolo.udacity.color.my.shoestoreinventorycapstone_1.features.authentication.login.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import paolo.udacity.color.my.shoestoreinventorycapstone_1.R
import paolo.udacity.color.my.shoestoreinventorycapstone_1.databinding.FragmentLoginBinding
import paolo.udacity.color.my.shoestoreinventorycapstone_1.features.main.view.MainViewModel


class LoginFragment : Fragment() {

    private val viewModel by activityViewModels<MainViewModel>()
    private val viewStateObserver = Observer<LoginViewState> { state ->
        when (state) {
            is LoginViewState.IsLoading -> setLoadingIndicator(state.data)
            is LoginViewState.SuccessOnLoginIn -> setSuccessOnLoginInInUi()
            is LoginViewState.SuccessOnCreatingAccount -> setSuccessOnCreatingAccountInUi()
        }
    }
    private lateinit var binding : FragmentLoginBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        initObservers()
        initUi()
        return binding.root
    }

    private fun initObservers() {
        viewModel.loginViewState.observe(viewLifecycleOwner, viewStateObserver)
    }

    private fun initUi() {
        binding.asLoginViewModel = viewModel
    }

    private fun setSuccessOnLoginInInUi() {
        setLoadingIndicator(true)
        Snackbar.make(requireActivity().findViewById(android.R.id.content),
                "Welcome back!", Snackbar.LENGTH_LONG).show()
        Handler(Looper.getMainLooper()).postDelayed({
            view?.findNavController()?.navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment())
        }, 3000)
    }

    private fun setSuccessOnCreatingAccountInUi() {
        setLoadingIndicator(true)
        Snackbar.make(requireActivity().findViewById(android.R.id.content),
                "Welcome! We hope you enjoy your visit.", Snackbar.LENGTH_LONG).show()
        Handler(Looper.getMainLooper()).postDelayed({
            view?.findNavController()?.navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment())
        }, 5000)
    }

    private fun setLoadingIndicator(isLoading: Boolean) {
        binding.gActions.visibility = if(isLoading) View.GONE else View.VISIBLE
        binding.lavLoader.visibility = if(isLoading) View.VISIBLE else View.GONE
    }

}