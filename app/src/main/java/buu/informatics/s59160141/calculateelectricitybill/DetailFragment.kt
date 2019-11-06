package buu.informatics.s59160141.calculateelectricitybill


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import buu.informatics.s59160141.calculateelectricitybill.databinding.FragmentDetailBinding
import kotlinx.android.synthetic.main.fragment_detail.*

/**
 * A simple [Fragment] subclass.
 */
class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private var eb:ElectricBill = ElectricBill()
    lateinit var args: DetailFragmentArgs

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentDetailBinding>(
            inflater,R.layout.fragment_detail,container,false
        )

        args = DetailFragmentArgs.fromBundle(arguments!!)
        binding.electricBill = eb
        saveBinding()
        binding.invalidateAll()

        return binding.root
    }

    private  fun saveBinding() {
        eb.unit = args.unit
        eb.res150 = args.unit150
        eb.res250 = args.unit250
        eb.res400 = args.unit400
        eb.serviceCharge = args.serviceCharge
        binding.baseElectricChargeText.text = String.format("%1.2f", eb.res150.toFloat() + eb.res250.toFloat() + eb.res400.toFloat() + eb.serviceCharge.toFloat())
        eb.ft = args.ft
        eb.vat = args.vat
        eb.sum = args.sum
    }


}
