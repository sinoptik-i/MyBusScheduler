package sin.android.mybusscheduler.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import sin.android.mybusscheduler.R
import sin.android.mybusscheduler.adapters.BusStopAdapter
import sin.android.mybusscheduler.adapters.BusStopListAdapter
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
    }

    private fun startAdapterRxJavaStyle() {
        recyclerView = binding.recyclerView
        busScheduleViewModel.trackSchedulers().subscribe({
            val adapter = BusStopAdapter(it)
            recyclerView.adapter = adapter
        },
            {
                Log.e(TAG, "", it)
            }).also { disposables.add(it) }

    }

    fun startAdapterCorutinesStyle(view: View) {
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val bundle = Bundle()
        val navController = Navigation.findNavController(view)
        val busStopListAdapter = BusStopListAdapter({
            bundle.putString("BusStopName", it.stopName)
            navController.navigate(R.id.namedStopBus, bundle)
        })
        recyclerView.adapter = busStopListAdapter
        lifecycle.coroutineScope.launch {
            busScheduleViewModel.flowSchedulers().collect {
                busStopListAdapter.submitList(it)
            }
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //startAdapterRxJavaStyle()
        startAdapterCorutinesStyle(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        disposables.clear()
    }

}