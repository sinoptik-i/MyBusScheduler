package sin.android.mybusscheduler.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import sin.android.mybusscheduler.R
import sin.android.mybusscheduler.databinding.ItemViewBinding

class BusStopAdapterV2 : RecyclerView.Adapter<BusStopAdapterV2.BusStopViewHolder>() {

    class BusStopViewHolder(
        private var binding: ItemViewBinding
    ) : RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusStopViewHolder {
        val layout = ItemViewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        val viewHolder = BusStopViewHolder(layout)
        return viewHolder
    }

    override fun onBindViewHolder(holder: BusStopViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}