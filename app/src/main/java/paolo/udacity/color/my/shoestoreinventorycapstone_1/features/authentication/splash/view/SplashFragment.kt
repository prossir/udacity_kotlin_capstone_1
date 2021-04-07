package paolo.udacity.color.my.shoestoreinventorycapstone_1.features.authentication.splash.view

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
import paolo.udacity.color.my.shoestoreinventorycapstone_1.R
import paolo.udacity.color.my.shoestoreinventorycapstone_1.databinding.FragmentSplashBinding
import paolo.udacity.color.my.shoestoreinventorycapstone_1.features.main.view.MainViewModel


class SplashFragment : Fragment() {

    private val viewModel by activityViewModels<MainViewModel>()
    private val viewStateObserver = Observer<SplashViewState> { state ->
        when (state) {
            is SplashViewState.SuccessInGettingUserLogged -> setSuccessInGettingUserLoggedInUi(state.data)
        }
    }
    private lateinit var binding: FragmentSplashBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_splash, container, false)
        initObservers()
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        initUI()
    }

    private fun initObservers() {
        viewModel.splashViewState.observe(viewLifecycleOwner, viewStateObserver)
    }

    private fun initUI() {
        viewModel.isUserLogged()
    }

    private fun setSuccessInGettingUserLoggedInUi(isLoggedIn: Boolean) {
        Handler(Looper.getMainLooper()).postDelayed({
            if(isLoggedIn) {
                requireView().findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToWelcomeFragment())
            } else {
                requireView().findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToLoginFragment())
            }
        }, 3000)
    }

}