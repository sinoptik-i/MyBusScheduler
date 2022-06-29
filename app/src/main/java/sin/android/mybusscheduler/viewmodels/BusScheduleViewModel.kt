package sin.android.mybusscheduler.viewmodels

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import sin.android.mybusscheduler.database.AppDatabase
import sin.android.mybusscheduler.database.Schedule
import sin.android.mybusscheduler.database.ScheduleDao

class BusScheduleViewModel(application: Application) : AndroidViewModel(application) {

    private var _allSchedulers: List<Schedule>? = null
    val allschedulers: List<Schedule>?
        get() = _allSchedulers

    private val scheduleDao: ScheduleDao
        get() = AppDatabase.getDatabase(getApplication()).scheduleDao()


    fun trackSchedulers()=scheduleDao.trackAll().subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    fun trackNamedShedulers(busStopName: String)=scheduleDao.trackByStopName(busStopName)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())


    /*fun createObs() {
        val appDatabase = AppDatabase.getDatabase(requireContext())
        appDatabase.scheduleDao().getAll()
    }
*/

    // private val allSchedulers=MutableLiveData(listOf(Schedule))
}