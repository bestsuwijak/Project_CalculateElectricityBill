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

        binding.apply {
            resultText.visibility = View.GONE
            textView6.visibility = View.GONE
            btnDetail.visibility = View.GONE
        }

        binding.btnCalculate.setOnClickListener {
            //Toast.makeText(context,"ราคา = " +R.id.input,Toast.LENGTH_LONG).show()
            //println(binding.input.text)
            binding.electricBill = eb

            if(binding.input.text.toString() == ""){
                Snackbar.make(requireView(),"กรุณากรอกข้อมูลปริมาณการใช้ไฟฟ้า  ", Snackbar.LENGTH_SHORT).show()
            }
            else{
                calculate(Integer.parseInt(binding.input.text.toString()))
                binding.apply {
                    resultText.visibility = View.VISIBLE
                    textView6.visibility = View.VISIBLE
                    btnDetail.visibility = View.VISIBLE
                }
            }
        }

        binding.btnDetail.setOnClickListener { view: View ->
            view.findNavController()
                .navigate(CalculateFragmentDirections
                    .actionCalculateFragmentToDetailFragment(eb.unit, eb.res150, eb.res250,
                        eb.res400, eb.serviceCharge, eb.ft, eb.vat, eb.sum))
//            view.findNavController()
//                .navigate(R.id.action_calculateFragment_to_detailFragment)
    }

        return binding.root
    }



    var result = String.format("%1.2f",0.00).toFloat()
    var sumRes: Float = 0F
    private fun calculate(input: Int) {
        var unit = input
        result = 0F
        eb.unit = unit.toString()
        if (unit == 0 ) {
            Snackbar.make(requireView(),"กรุณากรอกข้อมูลปริมาณการใช้ไฟฟ้า  ", Snackbar.LENGTH_SHORT).show()
        }else {

            if(unit > 400){
                sumRes = String.format("%1.2f", (unit - 400) * 4.4217).toFloat()
                result += sumRes
                eb.res400 = sumRes.toString()
                unit -= unit - 400

            }
            if(unit > 150 && unit <= 400){
                sumRes = String.format("%1.2f", (unit - 150) * 4.2218).toFloat()
                result += sumRes
                eb.res250 = sumRes.toString()
                unit -= unit - 150

            }
            if(unit > 0 && unit <= 150){
                sumRes = String.format("%1.2f", unit * 3.2484).toFloat()
                result += sumRes
                eb.res150 = sumRes.toString()
                unit -= unit

            }


            result += String.format("%1.2f", 38.22).toFloat()  //+ค่าบริการ
            eb.serviceCharge = String.format("%1.2f", 38.22)
            //println("ค่าไฟฟ้าฐาน = " +result)
            val Ft = String.format("%1.2f", (input * (-11.60))/100).toFloat()
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
            Snackbar.make(requireView(),"ราคา  =  " + (String.format("%1.2f",result).toFloat()) + " บาท", Snackbar.LENGTH_SHORT).show();
            binding.invalidateAll()
        }
    }


}



