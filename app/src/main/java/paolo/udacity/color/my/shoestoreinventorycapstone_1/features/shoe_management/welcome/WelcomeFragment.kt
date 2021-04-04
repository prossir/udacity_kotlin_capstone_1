package paolo.udacity.color.my.shoestoreinventorycapstone_1.features.shoe_management.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import paolo.udacity.color.my.shoestoreinventorycapstone_1.R
import paolo.udacity.color.my.shoestoreinventorycapstone_1.databinding.FragmentWelcomeBinding


class WelcomeFragment : Fragment() {

    private lateinit var binding: FragmentWelcomeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_welcome, container, false)

        initUi()

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private fun initUi() {
        binding.bGoToInstructions.setOnClickListener {

        }
    }

}