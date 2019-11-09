package buu.informatics.s59160141.calculateelectricitybill.history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import buu.informatics.s59160141.calculateelectricitybill.R
import buu.informatics.s59160141.calculateelectricitybill.TextItemViewHolder
import buu.informatics.s59160141.calculateelectricitybill.database.History

class HistoryAdapter: RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {
var data = ArrayList<History>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
//        holder.item_text.text = item.unitData +" หน่วย " +item.priceData +" บาท "
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView){
        val item_text = itemView.findViewById(R.id.item_text) as TextView
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.text_item_view, parent, false)
                return ViewHolder(view)
            }
        }
    }

    private fun ViewHolder.bind(item: History) {
        item_text.text = item.historyId.toString() +".  " +item.unitData +" หน่วย " +item.priceData +" บาท "
    }
}