package com.donali.basketapp00084417.database.repositories

import androidx.annotation.WorkerThread
import com.donali.basketapp00084417.database.daos.BasketMatchDao
import com.donali.basketapp00084417.database.entities.BasketMatch

class BasketMatchRepository(private val basketMatchDao: BasketMatchDao) {

    @WorkerThread
    suspend fun insert(basketMatch: BasketMatch):Long = basketMatchDao.insert(basketMatch)
    fun getAll() = basketMatchDao.getAll()
    fun getLastMatchLive() = basketMatchDao.getLastMatchLive()
}