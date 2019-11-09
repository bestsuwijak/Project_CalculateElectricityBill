package buu.informatics.s59160141.calculateelectricitybill.history

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import buu.informatics.s59160141.calculateelectricitybill.ElectricBill
import buu.informatics.s59160141.calculateelectricitybill.R
import buu.informatics.s59160141.calculateelectricitybill.calculate.CalculateViewModel
import buu.informatics.s59160141.calculateelectricitybill.calculate.CalculateViewModelFactory
import buu.informatics.s59160141.calculateelectricitybill.database.History
import buu.informatics.s59160141.calculateelectricitybill.database.HistoryDatabase
import buu.informatics.s59160141.calculateelectricitybill.databinding.FragmentHistoryBinding

/**
 * A simple [Fragment] subclass.
 */
class HistoryFragment : Fragment() {

//    private lateinit var historyViewModel: HistoryViewModel
//    private lateinit var viewModelFactory: HistoryViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentHistoryBinding>(
            inflater,
            R.layout.fragment_history,container,false
        )

        val application = requireNotNull(this.activity).application
        val dataSource = HistoryDatabase.getInstance(application).historyDatabaseDao
        val viewModelFactory = HistoryViewModelFactory(dataSource, application)
        val historyViewModel = ViewModelProviders.of(this, viewModelFactory).get(HistoryViewModel::class.java)

//        val list = arrayListOf<History>()
//        var arr = historyViewModel.getData()
//
//        list.add(
//            History(
//                1,"150","150")
//        )
//        list.add(
//            History(
//                2,"222","222")
//        )
//        list.add(
//            History(
//                3,"333","333")
//        )

        binding.setLifecycleOwner (this)
        historyViewModel.getData()
        historyViewModel.history.observe(this, Observer { it ->
            it.let {
                var adapter = HistoryAdapter()
                adapter.data = it as ArrayList<History>
                binding.historyList.adapter = adapter
            }
        })

        binding.historyViewModel = historyViewModel
        return binding.root
    }


}
