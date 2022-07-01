package sin.android.mybusscheduler.adapters

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.Navigation


import androidx.recyclerview.widget.RecyclerView
import sin.android.mybusscheduler.R

import sin.android.mybusscheduler.database.Schedule

import java.text.SimpleDateFormat
import java.util.*


//class BusStopAdapter(context: Context) : RecyclerView.Adapter<BusStopAdapter.BusStopViewHolder>() {
class BusStopAdapter(list: List<Schedule>) :
    RecyclerView.Adapter<BusStopViewHolder>() {

    //val binding=

    //    var allSchedules = AppDatabase.getDatabase(context).scheduleDao().getAll()
    private var allSchedules = list

    var navController: NavController? = null



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusStopViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_view, parent, false)
        val viewHolder = BusStopViewHolder(layout)
        val bundle = Bundle()
        viewHolder.itemView.setOnClickListener {
            bundle.putString("BusStopName", allSchedules.get(viewHolder.adapterPosition).stopName)
            navController = Navigation.findNavController(it)
            navController!!.navigate(R.id.namedStopBus,bundle)

        }
        return viewHolder//BusStopViewHolder(layout)
    }

    override fun onBindViewHolder(holder: BusStopViewHolder, position: Int) {
        val item = allSchedules.get(position)
        holder.busNametv.text = item.stopName
        holder.busTimetv.text = SimpleDateFormat(
            "h.mm "
        ).format(Date(item.arrivalTime.toLong() * 1000))
    }

    override fun getItemCount(): Int = allSchedules.size

    /*   private fun getAllSchedulers():List<Schedule>{
           Executors.newCachedThreadPool().submit(Runnable {
               return@Runnable AppDatabase.getDatabase(context).scheduleDao().getAll()
           })
       }
   */
}