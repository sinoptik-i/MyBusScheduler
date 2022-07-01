package sin.android.mybusscheduler.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import sin.android.mybusscheduler.R
import sin.android.mybusscheduler.database.Schedule
import java.text.SimpleDateFormat
import java.util.*

class NamedBusStopAdapter(list: List<Schedule>) :
    RecyclerView.Adapter<BusStopViewHolder>() {

    val allOneNamedScehdulers = list



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusStopViewHolder {
        return BusStopViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_view, parent, false)
        )
    }

    override fun onBindViewHolder(holder: BusStopViewHolder, position: Int) {
        val schedule = allOneNamedScehdulers.get(position)
        holder.busNametv.text = schedule.stopName
        holder.busTimetv.text = SimpleDateFormat(
            "h.mm "
        ).format(Date(schedule.arrivalTime.toLong() * 1000))

    }

    override fun getItemCount()=allOneNamedScehdulers.size
}