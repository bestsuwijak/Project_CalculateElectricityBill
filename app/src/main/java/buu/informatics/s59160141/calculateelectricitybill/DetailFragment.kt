package buu.informatics.s59160141.calculateelectricitybill


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import buu.informatics.s59160141.calculateelectricitybill.databinding.FragmentDetailBinding
import kotlinx.android.synthetic.main.fragment_detail.*

/**
 * A simple [Fragment] subclass.
 */
class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private var eb:ElectricBill = ElectricBill()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentDetailBinding>(
            inflater,R.layout.fragment_detail,container,false
        )

        binding.electricBill = eb
        binding.invalidateAll()

        return binding.root
    }


}
