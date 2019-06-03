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
import com.donali.basketapp00084417.database.daos.TeamDao
import com.donali.basketapp00084417.database.entities.BasketMatch
import com.donali.basketapp00084417.database.entities.Team
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.*

@Database(
    entities = [Team::class, BasketMatch::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(TimeStampConverter::class)
public abstract class RoomDB : RoomDatabase() {

    abstract fun teamDao(): TeamDao
    abstract fun basketMatchDao(): BasketMatchDao

    private class RoomDBCallback(private val scope: CoroutineScope) : RoomDatabase.Callback() {
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch(Dispatchers.IO) {
                    populateDB(database.teamDao(), database.basketMatchDao())
                }
            }
        }

        suspend fun populateDB(
            teamDao: TeamDao,
            basketMatchDao: BasketMatchDao
        ) {
            basketMatchDao.deleteAll()
            teamDao.deleteAll()

            val calendar = Calendar.getInstance()

            val teamInsertedId = teamDao.insert(Team("Golden State Warriors"))
            val teamSecondInsertedId = teamDao.insert(Team("Miami Heat"))

            val matchInserted = basketMatchDao.insert(BasketMatch(teamInsertedId, teamSecondInsertedId, calendar.time))

            Log.d("CUSTOM", matchInserted.toString())

        }
    }

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


}
