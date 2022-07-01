package sin.android.mybusscheduler.database

import androidx.room.Dao
import androidx.room.Query
import io.reactivex.Observable
import kotlinx.coroutines.flow.Flow


@Dao
interface ScheduleDao {
    @Query("SELECT * FROM schedule")
    fun getAll(): List<Schedule>

    @Query("SELECT * FROM schedule WHERE stop_name = :stopName")
    fun trackByStopName(stopName: String): Observable<List<Schedule>>

    @Query("SELECT * FROM schedule")
    fun trackAll(): Observable<List<Schedule>>

    @Query("SELECT * FROM schedule WHERE stop_name = :stopName")
    fun trackByStopNameFlow(stopName: String): Flow<List<Schedule>>

    @Query("SELECT * FROM schedule")
    fun trackAllFlow(): Flow<List<Schedule>>

}