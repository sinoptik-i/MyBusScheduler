package sin.android.mybusscheduler.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.disposables.CompositeDisposable
import sin.android.mybusscheduler.R
import sin.android.mybusscheduler.adapters.NamedBusStopAdapter
import sin.android.mybusscheduler.databinding.FragmentNamedStopBusBinding
import sin.android.mybusscheduler.viewmodels.BusScheduleViewModel

class NamedStopBus : Fragment() {

    private val TAG = this.javaClass.simpleName
    private lateinit var busName: String
    private lateinit var binding: FragmentNamedStopBusBinding
    private lateinit var recyclerView: RecyclerView
    private val busScheduleViewModel: BusScheduleViewModel by viewModels()
    private val disposables = CompositeDisposable()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            busName = it.getString("BusStopName").toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNamedStopBusBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.recyclerView
        busScheduleViewModel.trackNamedShedulers(busName).subscribe({
            val adapter = NamedBusStopAdapter(it)
            recyclerView.adapter = adapter
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