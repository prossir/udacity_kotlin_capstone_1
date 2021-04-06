package paolo.udacity.color.my.shoestoreinventorycapstone_1.features.main.view

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel
import paolo.udacity.color.my.shoestoreinventorycapstone_1.R
import paolo.udacity.color.my.shoestoreinventorycapstone_1.databinding.ActivityMainBinding
import paolo.udacity.color.my.shoestoreinventorycapstone_1.features.authentication.common.model.UserModel
import paolo.udacity.color.my.shoestoreinventorycapstone_1.features.authentication.splash.view.SplashFragment
import paolo.udacity.color.my.shoestoreinventorycapstone_1.utils.extensions.hideKeyboard
import paolo.udacity.color.my.shoestoreinventorycapstone_1.utils.presentation.model.FailureModel


class MainActivity : AppCompatActivity() {

    private val viewModel by viewModel<MainViewModel>()
    private val viewStateObserver = Observer<MainViewState> { state ->
        when (state) {
            is MainViewState.SuccessInGettingUser -> setSuccessInGettingUserInUi(state.data)
            is MainViewState.SuccessInLogOut -> setSuccessInLogOutInUi()
            is MainViewState.Failure -> setErrorInUi(state.failure)
        }
    }
    private lateinit var binding: ActivityMainBinding
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initObservers()
        initUI()
    }

    override fun onSupportNavigateUp(): Boolean {
        val navigationController = findNavController(R.id.f_nav_host)
        return NavigationUI.navigateUp(navigationController, appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun initObservers() {
        viewModel.viewState.observe(this, viewStateObserver)
    }

    override fun onStart() {
        super.onStart()
        binding.toolbar.navigationIcon = null
    }

    private fun initUI() {
        setSupportActionBar(binding.toolbar)
        drawerLayout = binding.drawerLayout
        val navigationController = findNavController(R.id.f_nav_host)
        navigationController.addOnDestinationChangedListener { _, destination, _ ->
            when(destination.id) {
                R.id.splashFragment, R.id.loginFragment, R.id.welcomeFragment,
                R.id.instructionsFragment -> {
                    supportActionBar?.hide()
                    drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
                }
                else -> {
                    supportActionBar?.show()
                    drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
                }
            }
        }
        // Who would have know? One can simply control which drawer items are used through this for the drawer layout
        appBarConfiguration = AppBarConfiguration(setOf(R.id.listShoesFragment), drawerLayout)
        NavigationUI.setupActionBarWithNavController(this, navigationController, appBarConfiguration)
        NavigationUI.setupWithNavController(binding.nvMain, navigationController)
    }

    private fun setSuccessInGettingUserInUi(data: UserModel) {
        val navigationHeaderView = binding.nvMain.getHeaderView(0)
        navigationHeaderView.findViewById<TextView>(R.id.tv_user_email).text = data.email
    }

    private fun setSuccessInLogOutInUi() {
        val intent = Intent(this, SplashFragment::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    private fun setErrorInUi(failure: FailureModel) {
        hideKeyboard()
        Snackbar.make(findViewById(android.R.id.content),
            failure.exactMessage ?: getString(failure.commonMessage), Snackbar.LENGTH_LONG).show()
    }

}