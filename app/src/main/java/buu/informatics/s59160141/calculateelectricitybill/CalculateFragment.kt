package buu.informatics.s59160141.calculateelectricitybill


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import buu.informatics.s59160141.calculateelectricitybill.databinding.FragmentCalculateBinding

/**
 * A simple [Fragment] subclass.
 */
class CalculateFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentCalculateBinding>(
            inflater,R.layout.fragment_calculate,container,false
        )

        binding.btnDetail.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_calculateFragment_to_detailFragment)
        }

        return binding.root
    }


}
