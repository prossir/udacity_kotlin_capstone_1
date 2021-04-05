package paolo.udacity.color.my.shoestoreinventorycapstone_1.features.shoe_management.instructions.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import paolo.udacity.color.my.shoestoreinventorycapstone_1.R
import paolo.udacity.color.my.shoestoreinventorycapstone_1.databinding.FragmentInstructionsBinding


class InstructionsFragment : Fragment() {

    private lateinit var binding: FragmentInstructionsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_instructions, container, false)
        initUi()
        return binding.root
    }

    private fun initUi() {
        binding.bGoToMyShoeList.setOnClickListener {
            view?.findNavController()?.navigate(InstructionsFragmentDirections.actionInstructionsFragmentToListShoesFragment())
        }
    }

}