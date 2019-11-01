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
import com.google.android.material.snackbar.Snackbar

/**
 * A simple [Fragment] subclass.
 */
@Suppress("SENSELESS_COMPARISON")
class CalculateFragment : Fragment() {

    private lateinit var binding: FragmentCalculateBinding
    private var eb:ElectricBill = ElectricBill()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentCalculateBinding>(
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
            binding.electricBill = eb

            calculate(Integer.parseInt(binding.input.text.toString()))
            binding.apply {
                resultText.visibility = View.VISIBLE
                textView6.visibility = View.VISIBLE
                btnDetail.visibility = View.VISIBLE
            }
        }

        return binding.root
    }



    var result = String.format("%1.2f",0.00).toFloat()
    private fun calculate(input: Int) {
        val unit = input
        eb.unit = unit.toString()
        if (unit == 0) {
            //snackbar show
        }else {

            if(unit > 0 && unit <= 150){
                result = String.format("%1.2f", unit * 3.2484).toFloat()
                eb.res150 = result.toString()
            }
            if(unit > 150 && unit <= 400){
                result = String.format("%1.2f", (150 * 3.2484) + ((unit - 150) * 4.2218)).toFloat()
                eb.res250 = result.toString()
            }
            if(unit > 400){
                result = String.format("%1.2f", (150 * 3.2484) + (250 * 4.2218) + ((unit-400) * 4.4217)).toFloat()
                eb.res400 = result.toString()
            }


            result += String.format("%1.2f", 38.22).toFloat()  //+ค่าบริการ
            eb.serviceCharge = String.format("%1.2f", 38.22)
            //println("ค่าไฟฟ้าฐาน = " +result)
            val Ft = String.format("%1.2f", (unit * (-11.60))/100).toFloat()
            eb.ft = Ft.toString()
            //println("ค่าไฟฟ้าผันแปร = " +Ft)
            val vat = String.format("%1.2f", ((result + Ft)*0.07)).toFloat()
            eb.vat = vat.toString()
            //println("ค่าภาษี = " +result7)
            result += String.format("%1.2f", (Ft + vat)).toFloat()

            val ans = String.format("%1.2f", result).toFloat()
            eb.sum = ans.toString()
            //println("รวม = " +result +"" +Ft +"" +vat +" = " +ans)


            //Toast.makeText(context,"ราคา = " + (String.format("%1.2f",result).toFloat()), Toast.LENGTH_LONG).show()
            Snackbar.make(requireView(),"ราคา  =  " + (String.format("%1.2f",result).toFloat()), Snackbar.LENGTH_SHORT).show();
            binding.invalidateAll()
        }
    }


}

