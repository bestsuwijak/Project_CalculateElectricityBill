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


}
