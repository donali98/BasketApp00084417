package com.donali.basketapp00084417.database.repositories

import androidx.annotation.WorkerThread
import com.donali.basketapp00084417.database.daos.ResultDao
import com.donali.basketapp00084417.database.entities.Result

class ResultRepository(val resultDao:ResultDao) {

    @WorkerThread
    suspend fun insert(result: Result) = resultDao.insert(result)
    fun getAll() = resultDao.getAll()

    @WorkerThread
    suspend fun deleteAll() = resultDao.deleteAll()
}