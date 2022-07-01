package sin.android.mybusscheduler.adapters

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import sin.android.mybusscheduler.R

class BusStopViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val busNametv = view.findViewById<TextView>(R.id.bus_stop_name_tv)
    val busTimetv = view.findViewById<TextView>(R.id.bus_stop_time_tv)
}