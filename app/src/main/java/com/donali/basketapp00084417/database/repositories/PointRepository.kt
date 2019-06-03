package com.donali.basketapp00084417.database.repositories

import androidx.annotation.WorkerThread
import com.donali.basketapp00084417.database.daos.PointDao
import com.donali.basketapp00084417.database.entities.Point

class PointRepository(private val pointDao: PointDao) {
    @WorkerThread
    suspend fun insert(point:Point) = pointDao.insert(point)

    fun getAll() = pointDao.getAll()

    fun getPointsOf(idTeam:Long,matchId:Long) = pointDao.getPointsOf(idTeam,matchId)

    @WorkerThread
    suspend fun deleteAll() = pointDao.deleteAll()

    @WorkerThread
    suspend fun updatePoints(amount:Int,pointId:Long) = pointDao.updatePoints(amount,pointId)

}