package sin.android.mybusscheduler.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import sin.android.mybusscheduler.R
import sin.android.mybusscheduler.database.Schedule
import sin.android.mybusscheduler.databinding.ItemViewBinding
import java.text.SimpleDateFormat
import java.util.*

class BusStopListAdapter(
    private val onItemClicked: (Schedule) -> Unit
) : ListAdapter<Schedule,  BusStopViewHolder>(DiffCallback()) {
//    ) : ListAdapter<Schedule,  BusStopListAdapter.BusStopViewHolder>(DiffCallback()) {

    private class DiffCallback : DiffUtil.ItemCallback<Schedule>() {

        //2
        override fun areItemsTheSame(oldItem: Schedule, newItem: Schedule) =
            oldItem.id == newItem.id

        //3
        override fun areContentsTheSame(oldItem: Schedule, newItem: Schedule) =
            oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusStopViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_view, parent, false)
        val viewHolder = BusStopViewHolder(layout)

       /* val viewHolder = BusStopViewHolder(
            ItemViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )      */


        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            onItemClicked(getItem(position))
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: BusStopViewHolder, position: Int) {
        val item = getItem(position)
       /* holder.binding.busStopNameTv.text = item.stopName
        holder.binding.busStopTimeTv.text=SimpleDateFormat(
            "h.mm "
        ).format(Date(item.arrivalTime.toLong() * 1000))*/
        holder.busNametv.text = item.stopName
        holder.busTimetv.text = SimpleDateFormat(
            "h.mm "
        ).format(Date(item.arrivalTime.toLong() * 1000))
    }

  /*  class BusStopViewHolder(
         var binding: ItemViewBinding
    ) : RecyclerView.ViewHolder(binding.root) {}*/


}