package com.donali.basketapp00084417.database.repositories

import com.donali.basketapp00084417.database.daos.PointDao
import com.donali.basketapp00084417.database.entities.Point

class PointRepository(private val pointDao: PointDao) {
    suspend fun insert(point:Point) = pointDao.insert(point)
    fun getAll() = pointDao.getAll()
    suspend fun deleteAll() = pointDao.deleteAll()
}