package buu.informatics.s59160141.calculateelectricitybill.calculate


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import buu.informatics.s59160141.calculateelectricitybill.calculate.CalculateFragmentDirections
import buu.informatics.s59160141.calculateelectricitybill.ElectricBill
import buu.informatics.s59160141.calculateelectricitybill.R
import buu.informatics.s59160141.calculateelectricitybill.database.History
import buu.informatics.s59160141.calculateelectricitybill.database.HistoryDatabase
import buu.informatics.s59160141.calculateelectricitybill.databinding.FragmentCalculateBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_calculate.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * A simple [Fragment] subclass.
 */
@Suppress("SENSELESS_COMPARISON")
class CalculateFragment : Fragment() {

    private lateinit var binding: FragmentCalculateBinding
    private lateinit var calculateViewModel: CalculateViewModel
    private lateinit var viewModelFactory: CalculateViewModelFactory

    private var eb: ElectricBill =
        ElectricBill()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate<FragmentCalculateBinding>(
            inflater,
            R.layout.fragment_calculate, container, false
        )

        val application = requireNotNull(this.activity).application
        val dataSource = HistoryDatabase.getInstance(application).historyDatabaseDao
        viewModelFactory = CalculateViewModelFactory(dataSource, application)
        calculateViewModel = ViewModelProviders.of(this, viewModelFactory).get(CalculateViewModel::class.java)

        binding.apply {
            resultText.visibility = View.GONE
            textView6.visibility = View.GONE
            btnDetail.visibility = View.GONE

            //Calculate OnClick
            btnCalculate.setOnClickListener {
                //Toast.makeText(context,"ราคา = " +R.id.input,Toast.LENGTH_LONG).show()

                if(input.text.toString() == "" || input.text.isEmpty()){
                    Snackbar.make(requireView(),"กรุณากรอกข้อมูลปริมาณการใช้ไฟฟ้า  ", Snackbar.LENGTH_SHORT).show()
                }
                else{
                   calculate(Integer.parseInt(input.text.toString()))
                    resultText.visibility = View.VISIBLE
                    textView6.visibility = View.VISIBLE
                    btnDetail.visibility = View.VISIBLE
                    //insert
                    calculateViewModel!!.insertEb(eb)

                    calculateViewModel!!.price?.observe(this.lifecycleOwner!!, Observer { ok ->
                        binding.resultText.text = ok
                    })


                }
            }

            //Detail OnClick
            btnDetail.setOnClickListener { view: View ->
                view.findNavController()
                    .navigate(
                        CalculateFragmentDirections.actionCalculateFragmentToDetailFragment(
                            eb.unit, eb.res150, eb.res250,
                            eb.res400, eb.serviceCharge, eb.ft, eb.vat, eb.sum
                        )
                    )
            }
            //Binding Eletricbill
            electricBill = eb
        }

        binding.setLifecycleOwner (this)
        binding.calculateViewModel = calculateViewModel
        setHasOptionsMenu(true)
        return binding.root
    }


    //Function Calculate

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
            //println("ค่าภาษี = " +vat)

            result += String.format("%1.2f", (Ft + vat)).toFloat()
            val ans = String.format("%1.2f", result).toFloat()
            eb.sum = ans.toString()
            //println("รวม = " +result +"" +Ft +"" +vat +" = " +ans)

            Snackbar.make(requireView(),"ราคา  =  " + (String.format("%1.2f",result).toFloat()) + " บาท", Snackbar.LENGTH_SHORT).show();
            binding.invalidateAll()
        }
    }



    //Share
    private fun getShareIntent() : Intent {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain")
            .putExtra(Intent.EXTRA_TEXT, eb.unit +" หน่วย  " +eb.sum +" บาท ")
        return shareIntent
    }
    private fun shareSuccess() {
        startActivity(getShareIntent())
    }

    //Menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.share_menu, menu)
        // check if the activity resolves
        if (null == getShareIntent().resolveActivity(activity!!.packageManager)) {
            // hide the menu item if it doesn't resolve
            menu.findItem(R.id.share)?.setVisible(false)
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.share -> shareSuccess()
        }
        return super.onOptionsItemSelected(item)
    }




}



