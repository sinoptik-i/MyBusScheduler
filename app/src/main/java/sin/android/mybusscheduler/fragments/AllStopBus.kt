package sin.android.mybusscheduler.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.disposables.CompositeDisposable
import sin.android.mybusscheduler.R
import sin.android.mybusscheduler.adapters.BusStopAdapter
import sin.android.mybusscheduler.database.AppDatabase
import sin.android.mybusscheduler.databinding.FragmentAllStopBusBinding
import sin.android.mybusscheduler.viewmodels.BusScheduleViewModel

class AllStopBus : Fragment() {
    private val TAG = this.javaClass.simpleName
    private lateinit var binding: FragmentAllStopBusBinding
    private lateinit var recyclerView: RecyclerView
    private val busScheduleViewModel: BusScheduleViewModel by viewModels()
    private val disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAllStopBusBinding.inflate(inflater, container, false)
        return binding.root
        //return inflater.inflate(R.layout.fragment_all_stop_bus, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.recyclerView
        busScheduleViewModel.trackSchedulers().subscribe({
            val adapter = BusStopAdapter(it)
            recyclerView.adapter = adapter
            //  adapter.notifyDataSetChanged()

        },
            {
                Log.e(TAG, "", it)
            }).also { disposables.add(it) }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        disposables.clear()
    }

}