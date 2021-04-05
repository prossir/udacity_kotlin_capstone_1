package paolo.udacity.color.my.shoestoreinventorycapstone_1.features.authentication.login.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel
import paolo.udacity.color.my.shoestoreinventorycapstone_1.R
import paolo.udacity.color.my.shoestoreinventorycapstone_1.databinding.ActivityLoginBinding
import paolo.udacity.color.my.shoestoreinventorycapstone_1.features.shoe_management.main.view.MainActivity
import paolo.udacity.color.my.shoestoreinventorycapstone_1.utils.presentation.model.FailureModel


class LoginActivity : AppCompatActivity() {

    private val viewModel by viewModel<LoginViewModel>()
    private val viewStateObserver = Observer<LoginViewState> { state ->
        when (state) {
            is LoginViewState.IsLoading -> setLoadingIndicator(state.data)
            is LoginViewState.SuccessOnLoginIn -> setSuccessOnLoginInInUi()
            is LoginViewState.SuccessOnCreatingAccount -> setSuccessOnCreatingAccountInUi()
            is LoginViewState.Failure -> setErrorInUi(state.failure)
        }
    }
    private lateinit var binding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initObservers()
        initUi()
    }

    private fun initObservers() {
        viewModel.viewState.observe(this, viewStateObserver)
    }

    private fun initUi() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.loginViewModel = viewModel
    }

    private fun setSuccessOnLoginInInUi() {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    private fun setSuccessOnCreatingAccountInUi() {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    private fun setErrorInUi(failure: FailureModel) {
        setLoadingIndicator(false)
        Snackbar.make(findViewById(android.R.id.content), failure.exactMessage ?: getString(failure.commonMessage), Snackbar.LENGTH_LONG).show()
    }

    private fun setLoadingIndicator(isLoading: Boolean) {
        binding.gActions.visibility = if(isLoading) View.GONE else View.VISIBLE
        binding.lavLoader.visibility = if(isLoading) View.VISIBLE else View.GONE
    }

}