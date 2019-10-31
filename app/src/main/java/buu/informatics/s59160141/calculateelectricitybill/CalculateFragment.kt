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

        binding.apply {
            resultText.visibility = View.GONE
            textView6.visibility = View.GONE
            btnDetail.visibility = View.GONE
        }
        binding.btnCalculate.setOnClickListener {
            //Toast.makeText(context,"ราคา = " +R.id.input,Toast.LENGTH_LONG).show()
            //println(binding.input.text)
            calculate(Integer.parseInt(binding.input.text.toString()))
            binding.apply {
                resultText.visibility = View.VISIBLE
                textView6.visibility = View.VISIBLE
                btnDetail.visibility = View.VISIBLE
            }
        }

        return binding.root
    }


        var result = 0.00
        private fun calculate(input: Int) {
//        result = 0.00
        val unit = input
        if (unit == 0 || unit == null) {
            //snackbar show
        }else {

            if(unit > 0 && unit <= 150){
                result = unit * 3.2484
            }
            if(unit > 150 && unit <= 400){
                result = (150 * 3.2484) + ((unit - 150) * 4.2218)
            }
            if(unit > 400){
                result = (150 * 3.2484) + (250 * 4.2218) + ((unit-400) * 4.4217)
            }


            result += 38.22  //+ค่าบริการ
            //println("ค่าไฟฟ้าฐาน = " +result)
            val Ft = (unit * (-11.60))/100
            //println("ค่าไฟฟ้าผันแปร = " +Ft)
            val vat = ((result + Ft)*0.07)
            //println("ค่าภาษี = " +result7)
            result += Ft + vat

            //val ans = result + Ft + result7
            //println("รวม = " +result +"" +Ft +"" +vat +" = " +ans)

            Toast.makeText(context,"ราคา = " + result,Toast.LENGTH_LONG).show()

        }
    }


}

