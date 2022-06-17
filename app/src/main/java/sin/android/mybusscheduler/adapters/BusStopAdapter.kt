package sin.android.mybusscheduler.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import sin.android.mybusscheduler.R
import sin.android.mybusscheduler.database.AppDatabase
import sin.android.mybusscheduler.database.Schedule
import sin.android.mybusscheduler.databinding.ItemViewBinding
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.Executors

//class BusStopAdapter(context: Context) : RecyclerView.Adapter<BusStopAdapter.BusStopViewHolder>() {
    class BusStopAdapter(list :List<Schedule>) : RecyclerView.Adapter<BusStopAdapter.BusStopViewHolder>() {

    //val binding=

//    var allSchedules = AppDatabase.getDatabase(context).scheduleDao().getAll()
    var allSchedules = list

    class BusStopViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val busNametv = view.findViewById<TextView>(R.id.bus_stop_name_tv)
        val busTimetv = view.findViewById<TextView>(R.id.bus_stop_time_tv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusStopViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_view, parent, false)
        return BusStopViewHolder(layout)
    }

    override fun onBindViewHolder(holder: BusStopViewHolder, position: Int) {
        val item=allSchedules.get(position)
        holder.busNametv.text=item.stopName
        holder.busTimetv.text=SimpleDateFormat(
"h.mm "
        ).format(Date(item.arrivalTime.toLong()*1000))
    }

    override fun getItemCount(): Int = allSchedules.size

 /*   private fun getAllSchedulers():List<Schedule>{
        Executors.newCachedThreadPool().submit(Runnable {
            return@Runnable AppDatabase.getDatabase(context).scheduleDao().getAll()
        })
    }
*/
}