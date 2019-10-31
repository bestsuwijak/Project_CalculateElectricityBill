package buu.informatics.s59160141.calculateelectricitybill


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import buu.informatics.s59160141.calculateelectricitybill.databinding.FragmentCalculateBinding
import kotlinx.android.synthetic.main.fragment_calculate.*
import kotlinx.android.synthetic.main.fragment_calculate.view.*

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
            inflater, R.layout.fragment_calculate, container, false
        )

        binding.btnDetail.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_calculateFragment_to_detailFragment)
        }

        binding.btnCalculate.setOnClickListener {
            //Toast.makeText(context,"ราคา = " +R.id.input,Toast.LENGTH_LONG).show()
            println(input)
            calculate(R.id.input)
        }

        return binding.root
    }


//        var result = 0.00
        private fun calculate(input: Int) {
//        result = 0.00
//        val unit = input
//        var Ft = (unit * (-11.60))/100
//        if (unit == 0 || unit == null) {
//            //snackbar show
//        }else {
//
//            if(unit > 0 && unit <= 150){
//                result = unit * 3.2484
//            }
//            if(unit > 150 && unit <= 400){
//                result = (150 * 3.2484) + ((unit - 150) * 4.2218)
//            }
//            if(unit > 400){
//                result = (150 * 3.2484) + (250 * 4.2218) + ((unit-400) * 3.2484)
//            }

//            if(unit > 400 ){
//                result += (unit - 400) * 2.9780
//                unit -= unit - 400
//            }
//            if (unit > 150 ) {
//                result += (unit - 150) * 2.1329
//                unit -= unit - 250
//            }
//            if(unit > 0 ){
//                result += unit * 1.8047
//            }
//            result += 38.22  //+ค่าบริการ
//            result += (result + Ft)*0.07

            //Toast.makeText(context,"ราคา = " +input,Toast.LENGTH_LONG).show()

//        }
    }


}

