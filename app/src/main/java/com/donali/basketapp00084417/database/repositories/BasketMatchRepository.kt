package com.donali.basketapp00084417.database.repositories

import com.donali.basketapp00084417.database.daos.BasketMatchDao

class BasketMatchRepository(private val basketMatchDao: BasketMatchDao) {

    fun getAll() = basketMatchDao.getAll()
}