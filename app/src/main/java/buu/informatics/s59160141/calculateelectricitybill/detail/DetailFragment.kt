package buu.informatics.s59160141.calculateelectricitybill.detail


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import buu.informatics.s59160141.calculateelectricitybill.*
import buu.informatics.s59160141.calculateelectricitybill.databinding.FragmentDetailBinding

/**
 * A simple [Fragment] subclass.
 */
class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private lateinit var viewModel: DetailViewModel
    private lateinit var viewModelFactory: DetailViewModelFactory

//    private var eb:ElectricBill = ElectricBill()
    lateinit var args: DetailFragmentArgs

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate<FragmentDetailBinding>(
            inflater,
            R.layout.fragment_detail,container,false
        )

        args = DetailFragmentArgs.fromBundle(arguments!!)
//        binding.electricBill = eb
//        saveBinding()
//        binding.invalidateAll()
//
//        binding.btnOK.setOnClickListener { view: View ->
//            view.findNavController()
//                .navigate(R.id.action_detailFragment_to_mainFragment)
//        }

        viewModelFactory =
            DetailViewModelFactory(
                args.unit, args.unit150, args.unit250, args.unit400,
                args.serviceCharge, args.ft, args.vat, args.sum

            )
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(DetailViewModel::class.java)

        viewModel.eventOK.observe(this, Observer { oK ->
            if (oK) {
                findNavController().navigate(DetailFragmentDirections.actionDetailFragmentToMainFragment())
                viewModel.onOKComplete()
            }
        })

        binding.detailViewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

//    private  fun saveBinding() {
//        eb.unit = args.unit
//        eb.res150 = args.unit150
//        eb.res250 = args.unit250
//        eb.res400 = args.unit400
//        eb.serviceCharge = args.serviceCharge
//        binding.baseElectricChargeText.text = String.format("%1.2f", eb.res150.toFloat() + eb.res250.toFloat() + eb.res400.toFloat() + eb.serviceCharge.toFloat())
//        eb.ft = args.ft
//        eb.vat = args.vat
//        eb.sum = args.sum
//    }


}
