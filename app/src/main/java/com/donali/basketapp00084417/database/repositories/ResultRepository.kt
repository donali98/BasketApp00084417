package com.donali.basketapp00084417.database.repositories

import com.donali.basketapp00084417.database.daos.ResultDao
import com.donali.basketapp00084417.database.entities.Result

class ResultRepository(val resultDao:ResultDao) {
    suspend fun insert(result: Result) = resultDao.insert(result)
    fun getAll() = resultDao.getAll()
    suspend fun deleteAll() = resultDao.deleteAll()
}