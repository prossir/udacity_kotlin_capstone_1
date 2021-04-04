package paolo.udacity.color.my.shoestoreinventorycapstone_1.features.authentication.splash.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel
import paolo.udacity.color.my.shoestoreinventorycapstone_1.R
import paolo.udacity.color.my.shoestoreinventorycapstone_1.databinding.ActivitySplashBinding
import paolo.udacity.color.my.shoestoreinventorycapstone_1.features.authentication.login.view.LoginActivity
import paolo.udacity.color.my.shoestoreinventorycapstone_1.features.shoe_management.main.view.MainActivity
import paolo.udacity.color.my.shoestoreinventorycapstone_1.utils.presentation.model.FailureModel


class SplashActivity : AppCompatActivity() {

    private val viewModel by viewModel<SplashViewModel>()
    private val viewStateObserver = Observer<SplashViewState> { state ->
        when (state) {
            is SplashViewState.SuccessInGettingUserLogged -> setSuccessInGettingUserLoggedInUi(state.data)
            is SplashViewState.Failure -> setErrorInUi(state.failure)
        }
    }
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initObservers()
        initUI()
    }

    private fun initObservers() {
        viewModel.viewState.observe(this, viewStateObserver)
    }

    private fun initUI() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)

        viewModel.isUserLogged()
    }

    private fun setSuccessInGettingUserLoggedInUi(isLoggedIn: Boolean) {
        Handler(Looper.getMainLooper()).postDelayed({
            if(isLoggedIn) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }, 1000)
    }

    private fun setErrorInUi(failure: FailureModel) {
        Snackbar.make(findViewById(android.R.id.content), getString(failure.commonMessage), Snackbar.LENGTH_LONG).show()

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }, 2000)
    }

}