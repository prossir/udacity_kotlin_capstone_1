package paolo.udacity.color.my.shoestoreinventorycapstone_1.features.shoe_management.list_shoes.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import paolo.udacity.color.my.shoestoreinventorycapstone_1.R
import paolo.udacity.color.my.shoestoreinventorycapstone_1.databinding.FragmentListShoesBinding

class ListShoesFragment : Fragment() {

    private lateinit var binding : FragmentListShoesBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_shoes, container, false)

        return super.onCreateView(inflater, container, savedInstanceState)
    }



}