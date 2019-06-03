package com.donali.basketapp00084417.database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.donali.basketapp00084417.database.converters.TimeStampConverter
import com.donali.basketapp00084417.database.daos.BasketMatchDao
import com.donali.basketapp00084417.database.daos.PointDao
import com.donali.basketapp00084417.database.daos.ResultDao
import com.donali.basketapp00084417.database.daos.TeamDao
import com.donali.basketapp00084417.database.entities.BasketMatch
import com.donali.basketapp00084417.database.entities.Point
import com.donali.basketapp00084417.database.entities.Result
import com.donali.basketapp00084417.database.entities.Team
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

@Database(
    entities = [Team::class, BasketMatch::class, Point::class, Result::class],
    version = 8,
    exportSchema = false
)
@TypeConverters(TimeStampConverter::class)
public abstract class RoomDB : RoomDatabase() {

    abstract fun teamDao(): TeamDao
    abstract fun basketMatchDao(): BasketMatchDao
    abstract fun pointDao(): PointDao
    abstract fun resultDao(): ResultDao

    companion object {
        @Volatile
        private var INSTANCE: RoomDB? = null

        fun getInstance(
            appContext: Context,
            scope: CoroutineScope
        )
                : RoomDB {
            val tmp = INSTANCE
            if (tmp != null) return tmp
            synchronized(this) {
                val instance = Room.databaseBuilder(appContext, RoomDB::class.java, "BasketApp")
                    .fallbackToDestructiveMigration()
                    .addCallback(RoomDBCallback(scope))
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

    private class RoomDBCallback(private val scope: CoroutineScope) : RoomDatabase.Callback() {
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch(Dispatchers.IO) {
                    populateDB(database.teamDao(), database.basketMatchDao(), database.pointDao(), database.resultDao())
                }
            }
        }

        suspend fun populateDB(
            teamDao: TeamDao,
            basketMatchDao: BasketMatchDao,
            pointDao: PointDao,
            resultDao: ResultDao
        ) {
            pointDao.deleteAll()
            resultDao.deleteAll()
            basketMatchDao.deleteAll()
            teamDao.deleteAll()

            val id = teamDao.insert(Team("Golden State Warriors"))
            teamDao.insert(Team("Miami Heat"))
            teamDao.insert(Team("Salvadorian Pacutsos"))


            Log.d("DB",id.toString())

/*            val teamInsertedId = teamDao.insert(Team("Golden State Warriors"))
            val teamSecondInsertedId = teamDao.insert(Team("Miami Heat"))
            val cal = Calendar.getInstance()
            cal.add(Calendar.DATE,1)
            val dateFormat = SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy")
            val match_id = basketMatchDao.insert(BasketMatch(teamInsertedId, teamSecondInsertedId, dateFormat.parse(dateFormat.format(cal.time))))
            pointDao.insert(Point(match_id,teamSecondInsertedId))
//            pointDao.insert(Point(match_id,teamInsertedId))
            resultDao.insert(Result(match_id,teamInsertedId))*/
        }
    }

}
